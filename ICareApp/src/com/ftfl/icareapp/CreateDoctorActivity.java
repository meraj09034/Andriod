package com.ftfl.icareapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ftfl.icareapp.database.DoctorDataSource;
import com.ftfl.icareapp.model.DoctorProfile;

public class CreateDoctorActivity extends Activity implements OnClickListener {

	Button btns_save = null;
	EditText etDoctorName = null;
	EditText etSpeciality = null;
	EditText etPhone = null;
	EditText etEmail = null;
	EditText etChamber = null;

	String mDoctorName = "";
	String mSpeciality = "";
	String mPhone = "";
	String mEmail = "";
	String mChamber = "";

	TextView tvItem = null;
	String mDoctorID = "";
	Long mLId;

	DoctorDataSource mProfileDS = null;
	DoctorProfile mUpdateProfile = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctor_profile);

		etDoctorName = (EditText) findViewById(R.id.etDoctorName);
		etSpeciality = (EditText) findViewById(R.id.etDoctorSpeciality);
		etPhone = (EditText) findViewById(R.id.etDoctorPhone);
		etEmail = (EditText) findViewById(R.id.etDoctorEmail);

		// etGender = (EditText) findViewById(R.id.addServiceDescription);
		etChamber = (EditText) findViewById(R.id.etDoctorChamber);
		btns_save = (Button) findViewById(R.id.btnDoctorSubmit);

		btns_save.setOnClickListener(this);

		Intent mActivityIntent = getIntent();
		mDoctorID = mActivityIntent.getStringExtra("id");

		if (mDoctorID != null) {
			mLId = Long.parseLong(mDoctorID);

			/*
			 * get the activity which include all data from database according
			 * profileId of the clicked item.
			 */
			mProfileDS = new DoctorDataSource(this);
			mUpdateProfile = mProfileDS.singleDoctorProfileData(mLId);

			String mDoctorName = mUpdateProfile.getDoctorName();
			String mSpaciality = mUpdateProfile.getSpeciality();
			String mPhone = mUpdateProfile.getPhone();
			String mEmail = mUpdateProfile.getEmail();
			String mChamber = mUpdateProfile.getChamber();

			// set the value of database to the text field.

			etDoctorName.setText(mDoctorName);
			etSpeciality.setText(mSpaciality);
			etPhone.setText(mPhone);
			etEmail.setText(mEmail);

			etChamber.setText(mChamber);

			/*
			 * change button name
			 */
			btns_save.setText("Update");
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Toast toast = null;
		switch (v.getId()) {

		case R.id.btnDoctorSubmit:

			mDoctorName = etDoctorName.getText().toString();
			mSpeciality = etSpeciality.getText().toString();
			mPhone = etPhone.getText().toString();
			mEmail = etEmail.getText().toString();
			mChamber = etChamber.getText().toString();

			DoctorProfile profileInsert = new DoctorProfile();
			profileInsert.setDoctorName(mDoctorName);
			profileInsert.setSpeciality(mSpeciality);
			profileInsert.setPhone(mPhone);
			profileInsert.setEmail(mEmail);
			profileInsert.setChamber(mChamber);

			/*
			 * if update is needed then update otherwise submit
			 */
			if (mDoctorID != null) {

				mLId = Long.parseLong(mDoctorID);

				mProfileDS = new DoctorDataSource(this);

				if (mProfileDS.updateData(mLId, profileInsert) == true) {
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

				mProfileDS = new DoctorDataSource(this);
				if (mProfileDS.insert(profileInsert) == true) {
					toast = Toast.makeText(this, "Successfully Saved.",
							Toast.LENGTH_LONG);
					toast.show();

					startActivity(new Intent(CreateDoctorActivity.this,
							DoctorListViewActivity.class));

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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_doctor_profile_actionbar, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
        case R.id.addDoctorProfilemanu:
        	startActivity(new Intent(CreateDoctorActivity.this, DoctorListViewActivity.class));
            return true;
        

        
        default:
            return super.onOptionsItemSelected(item);
    }
	}
}
