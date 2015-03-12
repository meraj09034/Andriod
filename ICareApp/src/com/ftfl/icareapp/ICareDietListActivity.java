package com.ftfl.icareapp;

import java.util.List;

import com.ftfl.icareapp.adapter.DailyProfileViewTwoAdapter;
import com.ftfl.icareapp.database.DatabaseSQLiteHelper;
import com.ftfl.icareapp.database.ICareDietDataSource;
import com.ftfl.icareapp.model.ICareActivityDietChart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class ICareDietListActivity extends ActionBarActivity {
	ListView mListViewOne;
	ListView mListViewTwo;
	ICareDietDataSource mDietDataSource;
	DatabaseSQLiteHelper mDBHelper;
	ICareActivityDietChart dailyDietChart;
	int id_To_Update = 0;
	String mDate;
	TextView textDate;
	TextView tvDate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_icare_diet_list);
		
		mDBHelper = new DatabaseSQLiteHelper(this);	
		mDietDataSource = new ICareDietDataSource(this);
		//textDate = (TextView) findViewById(R.id.tvDailyDietChartDate);
		//mDate = textDate.getText().toString();
		mDate = "18/1/2015";
		List<ICareActivityDietChart> allDailyDietChart = mDietDataSource.todayDietChart();

		DailyProfileViewTwoAdapter arrayAdapterOne = new DailyProfileViewTwoAdapter(this, allDailyDietChart);

		// adding it to the list view.
		mListViewOne = (ListView) findViewById(R.id.lvTodayDietChart);
		mListViewOne.setAdapter(arrayAdapterOne);
		
		
		List<String> upcomingDates = mDietDataSource.upcomingDates();
		//DailyProfileViewThreeAdapter arrayAdapterTwo = new DailyProfileViewThreeAdapter(this, allDailyDietChart);
		ArrayAdapter<String> mDatesAdapter = new ArrayAdapter<String>(this,
		        android.R.layout.simple_list_item_1, upcomingDates);
		// adding it to the list view.
		mListViewTwo = (ListView) findViewById(R.id.lvUpComingDietChart);
		mListViewTwo.setAdapter(mDatesAdapter);
		
		mListViewTwo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent,
					android.view.View view, int position, long id) {

				tvDate = (TextView) view.findViewById(android.R.id.text1);
				String dateValue = tvDate.getText().toString(); // get id
																	// from text
																	// view

				/*
				 * create an intent and send data
				 */
				Intent mPreviewIntent = new Intent(getApplicationContext(),
						ICareDailyDietChartViewActivity.class);
				mPreviewIntent.putExtra("cDate", dateValue);

				startActivity(mPreviewIntent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.icare_diet_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
        case R.id.history:
        	startActivity(new Intent(ICareDietListActivity.this, ICareDietHistoryActivity.class));
            return true;
        case R.id.createDiet:
        	startActivity(new Intent(ICareDietListActivity.this, ICareCreateDietChartActivity.class));
            return true;

        
        default:
            return super.onOptionsItemSelected(item);
    }
	}
}
