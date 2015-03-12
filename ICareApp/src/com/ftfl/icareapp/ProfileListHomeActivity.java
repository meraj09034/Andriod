package com.ftfl.icareapp;

import java.util.ArrayList;
import java.util.List;

import com.ftfl.icareapp.adapter.MedicalHistoryCustomAdapter;
import com.ftfl.icareapp.adapter.ProfileAdapter;
import com.ftfl.icareapp.database.DoctorDataSource;
import com.ftfl.icareapp.database.ICareDietDataSource;
import com.ftfl.icareapp.database.MedicalHistoryDataSourc;
import com.ftfl.icareapp.database.ProfileDataSource;
import com.ftfl.icareapp.database.VaccinationDataSourc;
import com.ftfl.icareapp.model.DoctorProfile;
import com.ftfl.icareapp.model.MedicalHistory;
import com.ftfl.icareapp.model.Profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class ProfileListHomeActivity extends ActionBarActivity {

	ImageView imgDietChaet = null;
	ImageView imgDoctor = null;
	ImageView imgMediccal = null;
	ImageView imgVaccination = null;
	DoctorDataSource mDoctorDataSource = null;
	DoctorProfile mDoctorProfile = null;
	
	MedicalHistoryDataSourc mMedicalDs = null;
	VaccinationDataSourc mVaccineDs = null;
	ICareDietDataSource mDietDS = null;
	ProfileDataSource mProfileDS = null;
	List<Profile> mProfileList = new ArrayList<Profile>();
	List<String> mNamesList = new ArrayList<String>();
	List<String> mIdList = new ArrayList<String>();
	ListView mListView = null;
	Integer mPosition = 0;
	ImageView imgEmergencyCall, mAbout, mCareCenter, mImportantNotes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview_user_profile);

		mDoctorDataSource = new DoctorDataSource(this);
		mMedicalDs = new MedicalHistoryDataSourc(this);
		mVaccineDs = new VaccinationDataSourc(this);
		mDietDS = new ICareDietDataSource(this);
		mProfileDS=new ProfileDataSource(this);
		setListData();
		List<Profile> galery_list = mProfileDS
				.iCareProfilesList();
		ProfileAdapter arrayAdapter = new ProfileAdapter(
				this, galery_list);
		final String[] option = new String[] { "Edit Data",
				"Delete Data" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.select_dialog_item, option);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
	
		builder.setTitle("Select Option");
		builder.setAdapter(adapter, new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Log.e("Selected Item", String.valueOf(which));
				if (which == 0) {
					editData(mPosition);
				}
				if (which == 1) {
					deleteData(mPosition);
				}

				
			}

		});
		final AlertDialog dialog = builder.create();

		
		mListView = (ListView) findViewById(R.id.lv_profilelistview);

//		ArrayAdapter<String> mProfileAdapter = new ArrayAdapter<String>(this,
//				android.R.layout.simple_list_item_1, mNamesList);

		mListView.setAdapter(arrayAdapter);

		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mPosition = position;
				dialog.show();
				// startActivity(new Intent(ProfileListHomeActivity.this,
				// DiseViewHomeActivity.class));
			}
		});

		imgDietChaet = (ImageView) findViewById(R.id.imageViewDietChart);
		imgDietChaet.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(ProfileListHomeActivity.this,
						ICareCreateDietChartActivity.class);
				startActivity(i);
			}
		});

		imgDoctor = (ImageView) findViewById(R.id.ImageViewDoctor);
		imgDoctor.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(ProfileListHomeActivity.this,
						CreateDoctorActivity.class);
				startActivity(i);
			}
		});

		imgMediccal = (ImageView) findViewById(R.id.ImageViewMedical);
		imgMediccal.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent i = new Intent(ProfileListHomeActivity.this,
						CreateMedicalHistoryActivity.class);
				startActivity(i);
			}
		});
		// super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_home);
		//
		imgVaccination = (ImageView) findViewById(R.id.imageViewVeccination);
		imgVaccination.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent i = new Intent(ProfileListHomeActivity.this,
						CreateVaccinationProfileActivity.class);
				startActivity(i);
			}
		});
	}

	private void setListData() {
		ProfileDataSource profiledata = new ProfileDataSource(this);
		mProfileList = profiledata.iCareProfilesList();
		for (int i = 0; i < mProfileList.size(); i++) {
			Profile mProfile = mProfileList.get(i);
			mIdList.add(mProfile.getId());
			mNamesList.add(mProfile.getName());
		}

	}

//	public void viewData(Integer ePosition) {
//		Intent mEditIntent = new Intent(getApplicationContext(),
//				ProfileViewActivity.class);
//		Long id = Long.parseLong(mIdList.get(ePosition));
//		mEditIntent.putExtra("id", id.toString());
//		startActivity(mEditIntent);
//	}

	public void editData(Integer ePosition) {
		Intent mEditIntent = new Intent(getApplicationContext(),
				CreateProfileActivity.class);
		Long id = Long.parseLong(mIdList.get(ePosition));
		mEditIntent.putExtra("id", id.toString());
		startActivity(mEditIntent);

	}

	public void deleteData(Integer ePosition) {
		mProfileDS = new ProfileDataSource(this);

		Long mId = Long.parseLong(mIdList.get(ePosition));
		mProfileDS.deleteData(mId);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dise_view_actionbar, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.addProfile:
			startActivity(new Intent(ProfileListHomeActivity.this,
					CreateProfileActivity.class));
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
