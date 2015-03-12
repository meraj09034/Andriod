package com.ftfl.hospital;

import com.ftfl.hospital.database.HospitalDataSource;
import com.ftfl.hospital.model.Hospital;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateHospitalActivity extends ActionBarActivity implements OnClickListener{
	
	Button btns_save = null;
	EditText etName = null;
	EditText etAddress = null;
	EditText etLatitude = null;
	EditText etLongitude = null;
	EditText etDescription = null;
	
	String mName = "";
	String mAddress = "";
	String mLatitude = "";
	String mLongitude = "";
	String mDescription = "";
	
	TextView tvItem = null;
	String mID = "";
	Long mLId ;
	HospitalDataSource mHospitalDS = null;
	Hospital mUpdateHospital = null;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_hospital);
		
		etName = (EditText) findViewById(R.id.addName);
		etAddress = (EditText) findViewById(R.id.addAddress);
		etLatitude = (EditText) findViewById(R.id.addLatitude);
		etLongitude = (EditText) findViewById(R.id.addLongitude);
		
		etDescription = (EditText) findViewById(R.id.addServiceDescription);
		btns_save = (Button) findViewById(R.id.btnSave);

		btns_save.setOnClickListener(this);

		
		Intent mActivityIntent = getIntent();
		mID = mActivityIntent.getStringExtra("id");

		if (mID != null) {
			mLId = Long.parseLong(mID);

			/*
			 * get the activity which include all data from database according
			 * profileId of the clicked item.
			 */
			mHospitalDS = new HospitalDataSource(this);
			mUpdateHospital = mHospitalDS
					.singleHospitalData(mLId);

			String mName = mUpdateHospital.getName();
			String mAddress = mUpdateHospital.getAddress();
			String mLatitude = mUpdateHospital.getLatitude();
			String mLongitude = mUpdateHospital.getLongitude();
			String mDescription = mUpdateHospital.getServiceDescription();
	

			// set the value of database to the text field.
	
			etName.setText(mName);
			etAddress.setText(mAddress);
			etLatitude.setText(mLatitude);
			etLongitude.setText(mLongitude);
			etDescription.setText(mDescription);

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

		
		case R.id.btnSave:
		
			mName = etName.getText().toString();
			mAddress = etAddress.getText().toString();
			mLatitude = etLatitude.getText().toString();
			mLongitude = etLongitude.getText().toString();
			mDescription = etDescription.getText().toString();

			Hospital hospitalInsert = new Hospital();
					hospitalInsert.setName(mName);
					hospitalInsert.setAddress(mAddress);
					hospitalInsert.setLatitude(mLatitude);
					hospitalInsert.setLongitude(mLongitude);
					hospitalInsert.setServiceDescription(mDescription);
				

			/*
			 * if update is needed then update otherwise submit
			 */
			if (mID != null) {

				mLId = Long.parseLong(mID);

				mHospitalDS = new HospitalDataSource(this);

				if (mHospitalDS.updateData(mLId, hospitalInsert) == true) {
					toast = Toast.makeText(this, "Successfully Updated.",
							Toast.LENGTH_LONG);
					toast.show();
					startActivity(new Intent(CreateHospitalActivity.this,
							HospitalListActivity.class));
					finish();
				} else {
					toast = Toast.makeText(this, "Not Updated.",
							Toast.LENGTH_LONG);
					toast.show();
				}
			} else {
				
				mHospitalDS = new HospitalDataSource(this);
				if (mHospitalDS.insert(hospitalInsert) == true) {
					toast = Toast.makeText(this, "Successfully Saved.",
							Toast.LENGTH_LONG);
					toast.show();

					startActivity(new Intent(CreateHospitalActivity.this,
							HospitalListActivity.class));

					//finish();
				} else {
					toast = Toast.makeText(this, "Not Saved.",
							Toast.LENGTH_LONG);
					toast.show();
				}
			
			break;
		}		}

	}}

		


