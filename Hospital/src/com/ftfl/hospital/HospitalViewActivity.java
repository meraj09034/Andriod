package com.ftfl.hospital;

import com.ftfl.hospital.database.HospitalDataSource;
import com.ftfl.hospital.model.Hospital;
import com.ftfl.hospital.util.HospitalConstants;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class HospitalViewActivity extends ActionBarActivity{
	
	TextView tvName = null;
	TextView tvAddress = null;
	TextView tvLatitude = null;
	TextView tvLongitude = null;
	TextView tvCloseDay = null;
	TextView tvOpenTime = null;
	TextView tvDescription = null;
	
	HospitalDataSource mHospitalDS = null;
	Hospital mUpdateHospital = null;
	
	String mID = "";
	Long mLId ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hospital_view_detail);
		
		tvName = (TextView) findViewById(R.id.viewName);
		tvAddress = (TextView) findViewById(R.id.viewAddress);
		tvLatitude = (TextView) findViewById(R.id.viewLatitude);
		tvLongitude = (TextView) findViewById(R.id.viewLongitude);
		tvDescription = (TextView) findViewById(R.id.viewServiceDescription);
		
		Intent mActivityIntent = getIntent();
		mID = mActivityIntent.getStringExtra("id");

		if (mID != null) {
			mLId = Long.parseLong(mID);

			/*
			 * get the activity which include all data from database according
			 * hospitalId of the clicked item.
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
	
			tvName.setText(mName);
			tvAddress.setText(mAddress);
			tvLatitude.setText(mLatitude);
			tvLongitude.setText(mLongitude);
			tvDescription.setText(mDescription);

		}
	}
	
	public void viewMap(View view) {
		Intent intent = new Intent(HospitalViewActivity.this,
				ShowHospitalGoogleMap.class);
		Bundle bundle = new Bundle();

		bundle.putString(HospitalConstants.LATITUDE, mUpdateHospital.getLatitude());
		bundle.putString(HospitalConstants.LONGITUDE, mUpdateHospital.getLongitude());
		bundle.putString(HospitalConstants.HOSPITALNAME, mUpdateHospital.getName());
		intent.putExtras(bundle);
		startActivity(intent);

		/*
		 * getData(); RestaurantInformation res_Info_obj=new
		 * RestaurantInformation(mName, mAddress, mLatitude, mLongitude, mMenus,
		 * mSpecialMenu, mCloseDay, mDailyOpenTime, mDescription);
		 * data_source_obj.editRestaurant(mSelectedId, res_Info_obj);
		 */
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_hospital_manu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
        case R.id.addHospitalmanu:
        	startActivity(new Intent(HospitalViewActivity.this, CreateHospitalActivity.class));
            return true;
        case R.id.camera:
        	startActivity(new Intent(HospitalViewActivity.this, CameraCropActivity.class));
            return true;   

        
        default:
            return super.onOptionsItemSelected(item);
    }
	}

}
