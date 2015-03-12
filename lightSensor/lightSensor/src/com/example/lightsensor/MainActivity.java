package com.example.lightsensor;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {
	private SensorManager mgr;
	private Sensor light;
	private TextView text;
	private StringBuilder msg = new StringBuilder(2048);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mgr = (SensorManager) this.getSystemService(SENSOR_SERVICE);

		light = mgr.getDefaultSensor(Sensor.TYPE_LIGHT);

	}

	@Override
	protected void onResume() {
		mgr.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
		super.onResume();
	}

	@Override
	protected void onPause() {
		mgr.unregisterListener(this, light);
		super.onPause();
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
			float currentLight = event.values[0];
			if (currentLight < 1) {
				AudioManager am;
				am = (AudioManager) getBaseContext().getSystemService(
						Context.AUDIO_SERVICE);
				am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
			} else if (currentLight < 5) {
				AudioManager am;
				am = (AudioManager) getBaseContext().getSystemService(
						Context.AUDIO_SERVICE);
				am.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
			} else {
				AudioManager am;
				am = (AudioManager) getBaseContext().getSystemService(
						Context.AUDIO_SERVICE);
				am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
			}
		}
	}

}