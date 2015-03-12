package com.ftfl.icareapp;

import java.util.ArrayList;
import java.util.List;

import com.ftfl.icareapp.adapter.DoctorAdapter;
import com.ftfl.icareapp.adapter.ProfileAdapter;
import com.ftfl.icareapp.database.DoctorDataSource;
import com.ftfl.icareapp.model.DoctorProfile;
import com.ftfl.icareapp.model.Profile;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DoctorListViewActivity extends ActionBarActivity {
	DoctorDataSource mDoctorProfileDS = null;
	List<DoctorProfile> mProfileList = new ArrayList<DoctorProfile>();
	List<String> mNamesList = new ArrayList<String>();
	List<String> mIdList = new ArrayList<String>();
	ListView mListView = null;
	Integer mPosition = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctor_listview);
		mDoctorProfileDS = new DoctorDataSource(this);

		setListData();
		List<DoctorProfile> galery_list = mDoctorProfileDS
				.iCareDoctorProfilesList();
		DoctorAdapter arrayAdapter = new DoctorAdapter(this, galery_list);
		final String[] option = new String[] { "View Detail", "Edit Data",
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
					viewData(mPosition);
				}
				if (which == 1) {
					editData(mPosition);
				}

				if (which == 2) {
					deleteData(mPosition);
				}
			}

		});
		final AlertDialog dialog = builder.create();

		mListView = (ListView) findViewById(R.id.lvDoctorList);

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
	}

	private void setListData() {
		DoctorDataSource profiledata = new DoctorDataSource(this);
		mProfileList = profiledata.iCareDoctorProfilesList();
		for (int i = 0; i < mProfileList.size(); i++) {
			DoctorProfile mProfile = mProfileList.get(i);
			mIdList.add(mProfile.getId());
			mNamesList.add(mProfile.getDoctorName());
		}

	}

	public void viewData(Integer ePosition) {
		Intent mEditIntent = new Intent(getApplicationContext(),
				DoctorShowDetailActivity.class);
		Long id = Long.parseLong(mIdList.get(ePosition));
		mEditIntent.putExtra("id", id.toString());
		startActivity(mEditIntent);
	}

	public void editData(Integer ePosition) {
		Intent mEditIntent = new Intent(getApplicationContext(),
				CreateDoctorActivity.class);
		Long id = Long.parseLong(mIdList.get(ePosition));
		mEditIntent.putExtra("id", id.toString());
		startActivity(mEditIntent);

	}

	public void deleteData(Integer ePosition) {
		mDoctorProfileDS = new DoctorDataSource(this);

		Long mId = Long.parseLong(mIdList.get(ePosition));
		mDoctorProfileDS.deleteData(mId);

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
			startActivity(new Intent(DoctorListViewActivity.this,
					CreateDoctorActivity.class));
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
