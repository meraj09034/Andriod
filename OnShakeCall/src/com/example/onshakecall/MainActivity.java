package com.example.onshakecall;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView phone;

	public static final String MyPREFERENCES = "MyPrefs";
	public static final String Phone = "phoneKey";

	SharedPreferences sharedpreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		phone = (TextView) findViewById(R.id.editTextPhone);

		sharedpreferences = getSharedPreferences(MyPREFERENCES,
				Context.MODE_PRIVATE);

		if (sharedpreferences.contains(Phone)) {
			Intent intent = new Intent(this, OnShake.class);
			startActivity(intent);
		}

	}

	public void run(View view) {
		String ph = phone.getText().toString();
		Editor editor = sharedpreferences.edit();

		editor.putString(Phone, ph);

		editor.commit();

	}
	/*
	 * @Override public boolean onCreateOptionsMenu(Menu menu) { // Inflate the
	 * menu; this adds items to the action bar if it is present.
	 * getMenuInflater().inflate(R.menu.main, menu); return true; }
	 */

}