package com.ftfl.icareapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class CallEmergencyNumberActivity extends Activity {

	TextView phone;

	public static final String MyPREFERENCES = "MyPrefs";
	public static final String PHONE = "phoneKey";

	SharedPreferences sharedpreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_call_emergency_sensor);

		phone = (TextView) findViewById(R.id.editTextPhone);

		sharedpreferences = getSharedPreferences(MyPREFERENCES,
				Context.MODE_PRIVATE);

		if (sharedpreferences.contains(PHONE)) {
			Intent intent = new Intent(this, OnShakeActivity.class);
			startActivity(intent);
		}

	}

	public void run(View view) {
		String mPhone = phone.getText().toString();
		Editor editor = sharedpreferences.edit();

		editor.putString(PHONE, mPhone);

		editor.commit();

	}


}
