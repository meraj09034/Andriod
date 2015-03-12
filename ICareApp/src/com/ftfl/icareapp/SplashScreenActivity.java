package com.ftfl.icareapp;

import com.ftfl.icareapp.database.ProfileDataSource;
import com.ftfl.icareapp.fregment.HomeScreenFragment;
import com.ftfl.icareapp.model.Profile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class SplashScreenActivity extends Activity {
	ProfileDataSource mDataSource = null;
	Profile mProfile = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);

		mDataSource = new ProfileDataSource(this);

		Thread background = new Thread() {
			public void run() {

				try {
					// Thread will sleep for 3 seconds
					sleep(2 * 1000);

					// After 2 seconds redirect to another intent
					// Intent i=new
					// Intent(getBaseContext(),FTFLICareProfileActivity.class);

					// Bellow code will do the same thing....

					if (mDataSource.isEmpty()) {
						Intent i = new Intent(SplashScreenActivity.this,
								CreateProfileActivity.class);
						startActivity(i);
					} else {
						Intent i = new Intent(SplashScreenActivity.this,
								HomeScreenActivity.class);
						startActivity(i);
					}

					// Remove activity
					finish(); // so that, it will not get back in the previous
								// file.

				} catch (Exception e) {

				}
			}
		};

		// start thread
		background.start();

	}

}
