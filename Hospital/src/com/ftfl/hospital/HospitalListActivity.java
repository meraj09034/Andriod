package com.ftfl.hospital;

import java.util.ArrayList;
import java.util.List;

import com.ftfl.hospital.database.HospitalDataSource;
import com.ftfl.hospital.model.Hospital;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class HospitalListActivity extends ActionBarActivity {
	
	HospitalDataSource mHospitalDS = null;
	List<Hospital> mHospitalList = new ArrayList<Hospital>();
	List<String> mNamesList = new ArrayList<String>();
	List<String> mIdList = new ArrayList<String>();
	ListView mListView = null;
	Integer mPosition = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_hospital_list);
		
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
					viewData(mPosition);
				}
				if (which == 1) {
					editData(mPosition);
				}

				if (which == 2) {
					deleteData(mPosition);
				}
//				if (which == 3) {
//					googleMap(mPosition);
//				}
			}

		});
		final AlertDialog dialog = builder.create();

		
		setListData();
		mListView = (ListView) findViewById(R.id.hospitalhomeList);
		
		ArrayAdapter<String> mHospitalAdapter = new ArrayAdapter<String>(this,
		        android.R.layout.simple_list_item_1, mNamesList);
		
		mListView.setAdapter(mHospitalAdapter);
		
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				mPosition = position;
				dialog.show();
			}
		});
	}

	private void setListData() {
		HospitalDataSource hospitaldata = new HospitalDataSource(this);
		mHospitalList = hospitaldata.hospitalData();
		for (int i = 0; i < mHospitalList.size(); i++) {
			Hospital mHospital = mHospitalList.get(i);
			mIdList.add(mHospital.getId());
			mNamesList.add(mHospital.getName());
		}
		
	}
	
	
	public void viewData(Integer ePosition)
	{
		Intent mEditIntent = new Intent(getApplicationContext(),
				HospitalViewActivity.class);
		Long id = Long.parseLong(mIdList.get(ePosition));
		mEditIntent.putExtra("id", id.toString());
		startActivity(mEditIntent);
	}
	
	public void editData(Integer ePosition)
	{
		Intent mEditIntent = new Intent(getApplicationContext(),
				CreateHospitalActivity.class);
		Long id = Long.parseLong(mIdList.get(ePosition));
		mEditIntent.putExtra("id", id.toString());
		startActivity(mEditIntent);
		
	}
	

	public void deleteData(Integer ePosition)
	{
		mHospitalDS = new HospitalDataSource(this);
		
		Long mId = Long.parseLong(mIdList.get(ePosition));
		mHospitalDS.deleteData(mId);

	 } 
//	public void googleMap(Integer ePosition)
//	{
//		Intent mEditIntent = new Intent(getApplicationContext(),
//				GoogleMapActivity.class);
//		Long id = Long.parseLong(mIdList.get(ePosition));
//		mEditIntent.putExtra("id", id.toString());
//		startActivity(mEditIntent);
//	}

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
        	startActivity(new Intent(HospitalListActivity.this, CreateHospitalActivity.class));
            return true;
        case R.id.camera:
        	startActivity(new Intent(HospitalListActivity.this, CameraActivity.class));
            return true;   

        
        default:
            return super.onOptionsItemSelected(item);
    }
	}

}
