package com.ftfl.icareapp;

import com.ftfl.icareapp.database.MedicalHistoryDataSourc;
import com.ftfl.icareapp.model.MedicalHistory;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MedicalDetailViewActivity extends Activity {

	TextView tvName = null;
	TextView tvDate = null;
	TextView tvPurpose = null;
	String mPhotoPath = "";
	Bitmap mBitmap = null;
	ImageView imImage = null;
	ImageView mShowImage = null;
	
	MedicalHistoryDataSourc mHospitalDS = null;
	MedicalHistory mUpdateHospital = null;

	String mID = "";
	Long mLId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_medicalhistory_view_detail);

		tvName = (TextView) findViewById(R.id.viewMedicalDoctorName);
		tvDate = (TextView) findViewById(R.id.viewMedicalDate);
		tvPurpose = (TextView) findViewById(R.id.viewMedicalPurpose);
		mShowImage = (ImageView) findViewById(R.id.viewMedicalImage);

		Intent mActivityIntent = getIntent();
		mID = mActivityIntent.getStringExtra("id");

		if (mID != null) {
			mLId = Long.parseLong(mID);

			/*
			 * get the activity which include all data from database according
			 * hospitalId of the clicked item.
			 */
			mHospitalDS = new MedicalHistoryDataSourc(this);
			mUpdateHospital = mHospitalDS.singleMedicalProfileData(mLId);

			String mName = mUpdateHospital.getMedicalDoctorName();
			String mDate = mUpdateHospital.getDate();
			String mPurpose = mUpdateHospital.getPurpose();
			mPhotoPath =mUpdateHospital.getPhotoPath();
			previewCapturedImage();
			mShowImage.setImageBitmap(mBitmap);
			//String mHeight = mUpdateHospital.getPhotoPath();

			// set the value of database to the text field.

			tvName.setText(mName);
			tvDate.setText(mDate);
			tvPurpose.setText(mPurpose);
			//imImage.setImageBitmap(mHeight);

		}

	}
	private void previewCapturedImage() {
		 try {
			 
	            // bimatp factory
	            BitmapFactory.Options options = new BitmapFactory.Options();
	 
	            // downsizing image as it throws OutOfMemory Exception for larger
	            // images
	            options.inSampleSize = 8 ;
	 
	            mBitmap = BitmapFactory.decodeFile(mPhotoPath, options);
	 
	            
	        } catch (NullPointerException e) {
	            e.printStackTrace();
	        }
	}
}
