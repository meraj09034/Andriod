package com.ftfl.icareapp;

import com.ftfl.icareapp.database.VaccinationDataSourc;
import com.ftfl.icareapp.model.Vaccination;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class VaccinationShowProfileActivity extends Activity {
	TextView tvVaccineName = null;
	TextView tvVaccineDate = null;
	TextView tvVaccineTime = null;
	TextView tvVaccineDescription = null;
	TextView tvVaccineAlarm = null;

	ImageView mAlarm;
	
	VaccinationDataSourc mVaccineDS = null;
	Vaccination mShowHospital = null;
	String mVaccineName;
	String mVaccineDate;
	String mVaccineDescription;
	String mVaccineAlarm;
	String mVaccineTime;
	String mID = "";
	Long mLId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vactionation_view);

		tvVaccineName = (TextView) findViewById(R.id.viewVaccinationName);
		tvVaccineDate = (TextView) findViewById(R.id.viewVaccinationDate);
		tvVaccineTime = (TextView) findViewById(R.id.viewVaccinationTime);
		tvVaccineDescription = (TextView) findViewById(R.id.viewVaccinationDescription);
		tvVaccineAlarm = (TextView) findViewById(R.id.viewVaccinationaLARM);
		mAlarm = (ImageView) findViewById(R.id.imgvcssinshow);
		
		Intent mActivityIntent = getIntent();
		mID = mActivityIntent.getStringExtra("id");

		if (mID != null) {
			mLId = Long.parseLong(mID);

			/*
			 * get the activity which include all data from database according
			 * hospitalId of the clicked item.
			 */
			mVaccineDS = new VaccinationDataSourc(this);
			mShowHospital = mVaccineDS.singleVaccineProfileData(mLId);

			mVaccineName = mShowHospital.getVaccineName();
			mVaccineDate = mShowHospital.getDate();
			mVaccineTime = mShowHospital.getTime();
			mVaccineDescription = mShowHospital.getDescription();
			mVaccineAlarm = mShowHospital.getVaccineAlarm();

			// set the value of database to the text field.

			tvVaccineName.setText(mVaccineName);
			tvVaccineDate.setText(mVaccineDate);
			tvVaccineTime.setText(mVaccineTime);
			tvVaccineDescription.setText(mVaccineDescription);
			if (mShowHospital.getVaccineAlarm().equals(1))
			{
				mAlarm.setImageResource(R.drawable.alarm);
			}
			else{
				tvVaccineAlarm.setText(mVaccineAlarm);}

		}
	}
}
