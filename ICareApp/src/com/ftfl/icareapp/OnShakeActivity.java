package com.ftfl.icareapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class OnShakeActivity extends Activity implements SensorEventListener {
	private SensorManager sensorManager;

	TextView tvPhone;
	SharedPreferences sharedpreferences;
	public static final String MyPREFERENCES = "MyPrefs";
	public static final String PHONE = "phoneKey";

	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		sharedpreferences = getSharedPreferences(MyPREFERENCES,
				Context.MODE_PRIVATE);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensor);
		tvPhone = (TextView) findViewById(R.id.tv_EmergengyNumber);
		tvPhone.setText(sharedpreferences.getString(PHONE, ""));
		tvPhone.setFocusable(false);
		tvPhone.setEnabled(false);
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			getAccelerometer(event);
		}

	}

	private void getAccelerometer(SensorEvent event) {
		float[] values = event.values;
		// Movement
		float x = values[0];
		float y = values[1];
		float z = values[2];
		
		
		String mPhoneNumber = sharedpreferences.getString(PHONE, "");
		float accelationSquareRoot = (x * x + y * y + z * z)
				/ (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);

		if (accelationSquareRoot >= 2) //
		{
			Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
					+ mPhoneNumber));
			startActivity(intent);
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	@Override
	protected void onResume() {
		super.onResume();
		// register this class as a listener for the orientation and
		// accelerometer sensors
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onPause() {
		// unregister listener
		super.onPause();
		sensorManager.unregisterListener(this);
	}
}
