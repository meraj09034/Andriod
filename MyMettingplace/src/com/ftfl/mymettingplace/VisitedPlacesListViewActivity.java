package com.ftfl.mymettingplace;

import java.lang.reflect.Field;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.ftfl.mymettingplace.adapter.Customadapter;
import com.ftfl.mymettingplace.helper.MyMettingPlacesDataSource;
import com.ftfl.mymettingplace.helper.DatabaseSQLiteHelper;
import com.ftfl.mymettingplace.model.PlaceInfo;
import com.ftfl.mymettingplace.util.GPSTracker;



public class VisitedPlacesListViewActivity extends Activity {

	DatabaseSQLiteHelper mDBHelper;
	TextView mTvMyCurrentPositionLat;
	TextView mTvMyCurrentPositionLong;
	MyMettingPlacesDataSource mPlacesDataSource;
	ListView mListView;
	Button mButton=null;
	TextView mId_tv = null;
	TextView mLatitude_tv = null;
	TextView mLongitude_tv = null;
	String mId = "";
	String mLatitude = "";
	String mLongitude = "";
	LocationManager lm;
	String provider;
	Location l;
	GPSTracker gps;
	Integer mPosition = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_place_detail_list_view);
		mButton=(Button) findViewById(R.id.btn_home);
		
		final String[] option = new String[] { "View Detail","Edit Data", "Delete Data"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.select_dialog_item, option);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle("Select Option");
		builder.setAdapter(adapter, new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Log.e("Selected Item", String.valueOf(which));
				if (which == 0) {
					//viewData(mPosition);
				}
				if (which == 1) {
					//editData(mPosition);
				}

				if (which == 2) {
					//deleteData(mPosition);
				}
				if (which == 3) {
					//googleMap(mPosition);
				}
			}

		});
		final AlertDialog dialog = builder.create();

	
		mTvMyCurrentPositionLat = (TextView) findViewById(R.id.tvMyCurrentPositionLat);
		mTvMyCurrentPositionLong = (TextView) findViewById(R.id.tvMyCurrentPositionLong);
		mDBHelper = new DatabaseSQLiteHelper(this);
		mPlacesDataSource = new MyMettingPlacesDataSource(this);
		List<PlaceInfo> places = mPlacesDataSource.allPlaces();
		Customadapter arrayAdapter = new Customadapter(this, places);
		mListView = (ListView) findViewById(R.id.lvPlacesList);
		

		// create class object
		gps = new GPSTracker(VisitedPlacesListViewActivity.this);
		// check if GPS enabled
		if (gps.canGetLocation()) {
			double latitude = gps.getLatitude();
			double longitude = gps.getLongitude();
			// \n is for new line

			mTvMyCurrentPositionLat.setText("LAT: "+String.valueOf(latitude));
			mTvMyCurrentPositionLong.setText("LNG: "+String.valueOf(longitude));
		} else {
			// can't get location
			// GPS or Network is not enabled
			// Ask user to enable GPS/network in settings
			gps.showSettingsAlert();
		}
		
		mListView.setAdapter(arrayAdapter);
		
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				mPosition = position;
				dialog.show();
			}
		});
		
		getOverflowMenu();
		
		

	}

	public void backToHome(View v){
		Intent i = new Intent(getApplicationContext(),
				MyMettingPlacesHomeActivity.class);
		startActivity(i);
		
	}

	private void getOverflowMenu() {

		try {
			ViewConfiguration config = ViewConfiguration.get(this);
			Field menuKeyField = ViewConfiguration.class
					.getDeclaredField("sHasPermanentMenuKey");
			if (menuKeyField != null) {
				menuKeyField.setAccessible(true);
				menuKeyField.setBoolean(config, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
