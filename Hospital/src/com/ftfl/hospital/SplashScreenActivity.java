package com.ftfl.hospital;

import java.util.Timer;
import java.util.TimerTask;

import com.ftfl.hospital.database.HospitalDataSource;
import com.ftfl.hospital.model.Hospital;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class SplashScreenActivity extends Activity {

	
	HospitalDataSource mDataSource = null;
	//Hospital mHospital = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		
		mDataSource = new HospitalDataSource(this);

		new Timer().schedule(new TimerTask() {
			public void run() {
				SplashScreenActivity.this.runOnUiThread(new Runnable() {
					public void run() {
						
						if(mDataSource.isEmpty())
						{
							startActivity(new Intent(SplashScreenActivity.this,
									CreateHospitalActivity.class));
						}
						else{
							startActivity(new Intent(SplashScreenActivity.this,
									HospitalListActivity.class));
							
						}

						finish();
					}
				});
			}
		}, 2000);
	}

}
