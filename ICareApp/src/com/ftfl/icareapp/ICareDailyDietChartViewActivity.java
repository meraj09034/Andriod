package com.ftfl.icareapp;

import java.util.List;

import com.ftfl.icareapp.adapter.DailyProfileViewOneAdapter;
import com.ftfl.icareapp.database.DatabaseSQLiteHelper;
import com.ftfl.icareapp.database.ICareDietDataSource;
import com.ftfl.icareapp.model.ICareActivityDietChart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;


public class ICareDailyDietChartViewActivity extends Activity {
	ListView lv;
	ICareDietDataSource mDietDataSource;
	DatabaseSQLiteHelper mDBHelper;
	ICareActivityDietChart dailyDietChart;
	int id_To_Update = 0;
	String mDate;
	TextView textDate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_icare_daily_diet_chart_view);

		mDBHelper = new DatabaseSQLiteHelper(this);	
		mDietDataSource = new ICareDietDataSource(this);
		//textDate = (TextView) findViewById(R.id.tvDailyDietChartDate);
		//mDate = textDate.getText().toString();
		
		Intent mIntent = getIntent();
		mDate = mIntent.getStringExtra("cDate");
		//mDate = "18/1/2015";
		TextView mTitel=(TextView) findViewById(R.id.tvDailyDietChartDate);
		mTitel.getText().toString();
		mTitel.setText("Date is :"+ mDate);
		List<ICareActivityDietChart> allDailyDietChart = mDietDataSource.dailyDietChart(mDate);

		DailyProfileViewOneAdapter arrayAdapter = new DailyProfileViewOneAdapter(this, allDailyDietChart);

		// adding it to the list view.
		lv = (ListView) findViewById(R.id.lvDietChaetList);
		lv.setAdapter(arrayAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.icare_daily_diet_chart_view, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
