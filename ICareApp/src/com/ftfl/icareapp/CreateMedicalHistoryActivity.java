package com.ftfl.icareapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.ftfl.icareapp.database.DoctorDataSource;
import com.ftfl.icareapp.database.ICareDietDataSource;
import com.ftfl.icareapp.database.MedicalHistoryDataSourc;
import com.ftfl.icareapp.model.DoctorProfile;
import com.ftfl.icareapp.model.MedicalHistory;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CreateMedicalHistoryActivity extends Activity implements OnClickListener,
		OnDateSetListener {

	static final int REQUEST_IMAGE_CAPTURE = 1;
	ImageView mIvPhotoView = null;
	static String mCurrentPhotoPath = "";

	EditText mEtName = null;
	EditText mEtPurpose = null;
	EditText etDate = null;
	Boolean mCam = false;
	Button btns_save, btns_capture;
	String mDate = "";
	String mName = "";
	String mPurpose = "";
	String mImage;

	public String mCurrentDate = "";
	MedicalHistory mGalery = null;
	String mMedicalID = "";
	Long mLId;
	MedicalHistory mUpdatePlaces = null;
	MedicalHistoryDataSourc mImageDS = null;
	int mYear = 0;
	int mDay = 0;
	int mMonth = 0;
	String mPhotoPath;
	private Uri mFileUri = null;
	final Calendar mCalendar = Calendar.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_medicalhistory_info);

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		mCurrentDate = dateFormat.format(date);

		etDate = (EditText) findViewById(R.id.etMedicalHistoryDate);
		mEtName = (EditText) this.findViewById(R.id.etMedicalHistoryDoctor);
		mEtPurpose = (EditText) this.findViewById(R.id.etMedicalHistoryPurpose);
		mIvPhotoView = (ImageView) this.findViewById(R.id.ivTakePhoto);
		btns_save = (Button) findViewById(R.id.btnMedicalHistoryInsert);
		btns_capture = (Button) findViewById(R.id.btnetMedicalHistoryTakePhoto);

		etDate.setOnClickListener(this);
		btns_save.setOnClickListener(this);

		Intent mActivityIntent = getIntent();
		mMedicalID = mActivityIntent.getStringExtra("id");
		if (mMedicalID != null) {
			mLId = Long.parseLong(mMedicalID);
			mImageDS = new MedicalHistoryDataSourc(this);
			mUpdatePlaces = mImageDS.singleMedicalProfileData(mLId);

			String mName = mUpdatePlaces.getMedicalDoctorName();
			String mEmail = mUpdatePlaces.getDate();
			String mDescription = mUpdatePlaces.getPurpose();
			String mPhotoPath = mUpdatePlaces.getPhotoPath();
			mCurrentPhotoPath = mPhotoPath;
			if (mPhotoPath != null) {
				File f = new File(mPhotoPath);
				BitmapFactory.Options option = new BitmapFactory.Options();
				option.inSampleSize = 16;

				try {
					Bitmap image = BitmapFactory.decodeStream(
							new FileInputStream(f), null, option);

					mIvPhotoView.setImageBitmap(image);

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}

			etDate.setText(mDescription);
			mEtName.setText(mName);
			mEtPurpose.setText(mEmail);

			/*
			 * change button name
			 */
			btns_save.setText("Update");

		}
	}

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		// TODO Auto-generated method stub
		etDate.setText(new StringBuilder()
				// Month is 0 based so add 1
				.append(dayOfMonth).append("/").append(monthOfYear + 1)
				.append("/").append(year));
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Toast toast = null;
		switch (v.getId()) {
		case R.id.etMedicalHistoryDate:
			mYear = mCalendar.get(Calendar.YEAR);
			mMonth = mCalendar.get(Calendar.MONTH);
			mDay = mCalendar.get(Calendar.DAY_OF_MONTH);

			DatePickerDialog dialog = new DatePickerDialog(this, this, mYear,
					mMonth, mDay);
			dialog.show();
			break;

		case R.id.btnMedicalHistoryInsert: {
			if (mCam) {
				mName = mEtName.getText().toString();
				mPurpose = mEtPurpose.getText().toString();
				mDate = etDate.getText().toString();

				MedicalHistory profileInsert = new MedicalHistory();
				profileInsert.setMedicalDoctorName(mName);
				profileInsert.setDate(mDate);
				profileInsert.setPurpose(mPurpose);

				profileInsert.setPhotoPath(mCurrentPhotoPath);
				/*
				 * if update is needed then update otherwise submit
				 */
				if (mMedicalID != null) {

					mLId = Long.parseLong(mMedicalID);

					mImageDS = new MedicalHistoryDataSourc(this);

					if (mImageDS.updateData(mLId, profileInsert) == true) {
						toast = Toast.makeText(this, "Successfully Updated.",
								Toast.LENGTH_LONG);
						toast.show();
						// startActivity(new Intent(CreateHospitalActivity.this,
						// HospitalListActivity.class));
						finish();
					} else {
						toast = Toast.makeText(this, "Not Updated.",
								Toast.LENGTH_LONG);
						toast.show();
					}
				} else {

					mImageDS = new MedicalHistoryDataSourc(this);
					if (mImageDS.insert(profileInsert) == true) {
						toast = Toast.makeText(this, "Successfully Saved.",
								Toast.LENGTH_LONG);
						toast.show();

						startActivity(new Intent(CreateMedicalHistoryActivity.this,
								MedicalHistoryListViewActivity.class));

						// finish();
					} else {
						toast = Toast.makeText(this, "Not Saved.",
								Toast.LENGTH_LONG);
						toast.show();
					}

					break;
				}
			} else {
				toast = Toast.makeText(this, "Take Picture, Please!",
						Toast.LENGTH_LONG);
				toast.show();

			}
		}

		}

	}

	public void dispatchTakePictureIntent(View v) {
		captureImage();
	}

	private void captureImage() {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		mFileUri = getOutputMediaFileUri(1);

		intent.putExtra(MediaStore.EXTRA_OUTPUT, mFileUri);

		// start the image capture Intent
		startActivityForResult(intent, 100);

	}

	/**
	 * Here we store the file url as it will be null after returning from camera
	 * app
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		// save file url in bundle as it will be null on screen orientation
		// changes
		outState.putParcelable("file_uri", mFileUri);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

		// get the file url
		mFileUri = savedInstanceState.getParcelable("file_uri");
	}

	/**
	 * Receiving activity result method will be called after closing the camera
	 * */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// if the result is capturing Image
		if (requestCode == 100) {
			if (resultCode == RESULT_OK) {
				// successfully captured the image
				// display it in image view
				previewCapturedImage();
				mCam = true;
				// carry out the crop operation

			} // user is returning from cropping the image
			else if (resultCode == RESULT_CANCELED) {
				// user cancelled Image capture
				Toast.makeText(getApplicationContext(),
						"User cancelled image capture", Toast.LENGTH_SHORT)
						.show();
			} else {
				// failed to capture image
				Toast.makeText(getApplicationContext(),
						"Sorry! Failed to capture image", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

	/**
	 * Display image from a path to ImageView
	 */
	private void previewCapturedImage() {
		try {
			// mImgPreview.setVisibility(View.VISIBLE);
			// bimatp factory
			BitmapFactory.Options options = new BitmapFactory.Options();
			/**
			 * downsizing image as it throws OutOfMemory Exception for larger
			 * images
			 */
			options.inSampleSize = 8; // should be power of 2.
			Bitmap bitmap = BitmapFactory.decodeFile(mFileUri.getPath(),
					options);

			mIvPhotoView.setImageBitmap(bitmap);

		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ------------ Helper Methods ----------------------
	 * */

	/**
	 * Creating file uri to store image/video
	 */
	public Uri getOutputMediaFileUri(int type) {
		return Uri.fromFile(getOutputMediaFile(type));
	}

	/**
	 * returning image / video
	 */
	private static File getOutputMediaFile(int type) {

		// External sdcard mLocation
		File mediaStorageDir = new File(
				Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
				"Places Gallery");

		// Create the storage directory if it does not exist
		if (!mediaStorageDir.exists()) {
			if (!mediaStorageDir.mkdirs()) {
				Log.d("Places Gallery", "Oops! Failed create "
						+ "Places Gallery" + " directory");
				return null;
			}
		}

		// Create a media file mName
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
				Locale.getDefault()).format(new Date());

		File mediaFile;

		if (type == 1) {

			mediaFile = new File(mediaStorageDir.getPath() + File.separator
					+ "IMG_" + timeStamp + ".jpg");
			mCurrentPhotoPath = mediaFile.getAbsolutePath();

		} else if (type == 2) {

			mediaFile = new File(mediaStorageDir.getPath() + File.separator
					+ "IMG_" + "CROPPED_" + timeStamp + ".jpg");

			// Save a file: path for use with ACTION_VIEW intents
			mCurrentPhotoPath = mediaFile.getAbsolutePath();
		} else {
			return null;
		}

		return mediaFile;
	}

}
