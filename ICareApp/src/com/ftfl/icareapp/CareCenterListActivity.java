package com.ftfl.icareapp;


import com.ftfl.icareapp.adapter.CareCenterAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class CareCenterListActivity extends Activity {

	 ListView listCare;
	  String[] mCareName = {
	    "Apollo Hospital",
	    "Square Hospital",
	    "National Heart Foundation Hospital & Research ",
	    "Al Helal Specialized Hospital",
	    "Bangabandhu Sheikh Mujib Medical University",
	    "Bangladesh Medical College",
	    "Lion Foundation Eye Hospital"
	  } ;

	  
	  double[] mLatitude = {
			    23.8108949,
			    23.752997,
			    23.803745,
			    23.802677,
			    23.738914,
			    23.750306,
			    23.779461
			  } ;
	  
	  double[] mLongitude = {
			    90.4305911,
			    90.381461,
			    90.361955,
			    90.370751,
			    90.394778,
			    90.369881,
			    90.379176			    
			  } ;
	
	  
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_carecentermain);
	    CareCenterAdapter adapter = new
	    		CareCenterAdapter(CareCenterListActivity.this, mCareName, mLatitude, mLongitude);
	    listCare=(ListView)findViewById(R.id.listCare);
	    listCare.setAdapter(adapter);
	    listCare.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	                @Override
	                public void onItemClick(AdapterView<?> parent, View view,
	                                        int position, long id) {
	                	
	                	Bundle b = new Bundle();
						b.putStringArray("care", mCareName);
						b.putDoubleArray("lat", mLatitude);
						b.putDoubleArray("long", mLongitude);
					
						Intent in = new Intent(CareCenterListActivity.this, CareCenterGoogleMapActivity.class);

						// Storing bundle object into intent
						in.putExtras(b);
						startActivity(in);
//	                	startActivity(new Intent(CareCenterListActivity.this,
//	                			CareCenterGoogleMapActivity.class));
	                }
	            });
	  }
}
