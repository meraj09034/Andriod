package com.ftfl.icareapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.ftfl.icareapp.database.ImportantNotesDataSource;
import com.ftfl.icareapp.fregment.HomeScreenFragment;
import com.ftfl.icareapp.model.ImportentNotes;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CreateImportentNotesActivity extends Activity implements OnClickListener,
		OnDateSetListener {
	
	EditText mEtTitel = null;
	EditText mEtDescription = null;
	EditText etDate = null;
	Boolean mCam = false;
	Button btns_save, btns_capture;
	String mDate = "";
	String mTitel = "";
	String mDescription = "";
	
	public String mCurrentDate = "";
	String mNotesID = "";
	Long mLId;
	
	ImportentNotes mUpdatePlaces = null;
	ImportantNotesDataSource mNotesDS = null;
	int mYear = 0;
	int mDay = 0;
	int mMonth = 0;

	final Calendar mCalendar = Calendar.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_importentnote);

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		mCurrentDate = dateFormat.format(date);

		etDate = (EditText) findViewById(R.id.editImporrtentNoteDate);
		mEtTitel = (EditText) this.findViewById(R.id.editTitelImporrtentNote);
		mEtDescription = (EditText) this.findViewById(R.id.editImporrtentNoteDescription);
		
		btns_save = (Button) findViewById(R.id.btnImporrtentNoteSave);
		
		etDate.setOnClickListener(this);
		btns_save.setOnClickListener(this);

		Intent mActivityIntent = getIntent();
		mNotesID = mActivityIntent.getStringExtra("id");
		if (mNotesID != null) {
			mLId = Long.parseLong(mNotesID);
			mNotesDS = new ImportantNotesDataSource(this);
			mUpdatePlaces = mNotesDS.singleImportentNotesData(mLId);

			String mNotesTitel = mUpdatePlaces.getNotesTitel();
			String mDate = mUpdatePlaces.getNotesDate();
			String mDescription = mUpdatePlaces.getNotesDescription();
			

			etDate.setText(mDate);
			mEtTitel.setText(mNotesTitel);
			mEtDescription.setText(mDescription);

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
		case R.id.editImporrtentNoteDate:
			mYear = mCalendar.get(Calendar.YEAR);
			mMonth = mCalendar.get(Calendar.MONTH);
			mDay = mCalendar.get(Calendar.DAY_OF_MONTH);

			DatePickerDialog dialog = new DatePickerDialog(this, this, mYear,
					mMonth, mDay);
			dialog.show();
			break;

		case R.id.btnImporrtentNoteSave:

			mTitel = mEtTitel.getText().toString();
			mDescription = mEtDescription.getText().toString();
			mDate = etDate.getText().toString();

			ImportentNotes profileInsert = new ImportentNotes();
			profileInsert.setNotesTitel(mTitel);
			profileInsert.setNotesDate(mDate);
			profileInsert.setNotesDescription(mDescription);
			
			/*
			 * if update is needed then update otherwise submit
			 */
			if (mNotesID != null) {

				mLId = Long.parseLong(mNotesID);

				mNotesDS = new ImportantNotesDataSource(this);

				if (mNotesDS.updateData(mLId, profileInsert) == true) {
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

				mNotesDS = new ImportantNotesDataSource(this);
				if (mNotesDS.insert(profileInsert) == true) {
					toast = Toast.makeText(this, "Successfully Saved.",
							Toast.LENGTH_LONG);
					toast.show();

					

					// finish();
				} else {
					toast = Toast.makeText(this, "Not Saved.",
							Toast.LENGTH_LONG);
					toast.show();
				}

				break;
			}
		}

	}
}
