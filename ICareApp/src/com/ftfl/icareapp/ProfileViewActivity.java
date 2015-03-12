package com.ftfl.icareapp;

import com.ftfl.icareapp.database.ProfileDataSource;
import com.ftfl.icareapp.model.Profile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class ProfileViewActivity extends Activity{
	
	TextView tvName = null;
	TextView tvAge = null;
	TextView tvWeight = null;
	TextView tvHeight = null;
	TextView tvBloodGroup = null;
	TextView tvGender = null;
	
	ProfileDataSource mHospitalDS = null;
	Profile mUpdateHospital = null;
	
	String mID = "";
	Long mLId ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_user_profile);
		
		tvName = (TextView) findViewById(R.id.viewProfileName);
		tvAge = (TextView) findViewById(R.id.viewProfileAge);
		tvWeight = (TextView) findViewById(R.id.viewProfileWeight);
		tvHeight = (TextView) findViewById(R.id.viewProfileHeight);
		tvGender = (TextView) findViewById(R.id.viewProfileGender);
		tvBloodGroup = (TextView) findViewById(R.id.viewProfileBirthGroup);
		
		Intent mActivityIntent = getIntent();
		mID = mActivityIntent.getStringExtra("id");

		if (mID != null) {
			mLId = Long.parseLong(mID);

			/*
			 * get the activity which include all data from database according
			 * hospitalId of the clicked item.
			 */
			mHospitalDS = new ProfileDataSource(this);
			mUpdateHospital = mHospitalDS
					.singleProfileData(mLId);

			String mName = mUpdateHospital.getName();
			String mAge = mUpdateHospital.getAge();
			String mWeight = mUpdateHospital.getWeight();
			String mHeight = mUpdateHospital.getHeight();
			String mGender = mUpdateHospital.getGender();
			String mBloodGroup = mUpdateHospital.getBloodGroup();

			// set the value of database to the text field.
	
			tvName.setText(mName);
			tvAge.setText(mAge);
			tvWeight.setText(mWeight);
			tvHeight.setText(mHeight);
			tvGender.setText(mGender);
			tvBloodGroup.setText(mBloodGroup);

		
	}
	}
	

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.create_hospital_manu, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//	    switch (item.getItemId()) {
//        case R.id.addHospitalmanu:
//        	startActivity(new Intent(HospitalViewActivity.this, CreateHospitalActivity.class));
//            return true;
//        case R.id.camera:
//        	startActivity(new Intent(HospitalViewActivity.this, CameraActivity.class));
//            return true;   
//
//        
//        default:
//            return super.onOptionsItemSelected(item);
//    }
//	}


}
