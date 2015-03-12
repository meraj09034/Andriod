package com.ftfl.icareapp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.ftfl.icareapp.database.VaccinationDataSourc;
import com.ftfl.icareapp.model.Vaccination;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class CreateVaccinationProfileActivity extends Activity implements
		OnClickListener, OnDateSetListener, OnTimeSetListener {

	Button btns_save, btn_cancel, btn_plus, btn_minus;
	TextView tvItem = null;
	EditText etDate = null;
	public String mDate = "";
	EditText etTime = null;
	public String mTime = "";
	EditText etName = null;
	public String mName = "";
	EditText etDescription = null;
	public String mDescription = "";
	CheckBox cbAlarm = null;
	public String mAlarm = "";
	public String mCurrentDate = "";
	int mHour = 0;
	int mMinute = 0;
	int mYear = 0;
	int mDay = 0;
	int mMonth = 0;
	String mStrProfileID = "";
	String mStrActivityID = "";
	long mActivityId = 0;
	VaccinationDataSourc activityDS = null;
	Vaccination mUpdateActivity = null;
	VaccinationDataSourc mActivityDataSource = null;
	String mActivityCurrentDate = "";
	String mActivityProfileId = "";
	Integer mSetHour = 0;
	Integer mSetMinute = 0;
	Integer check = 1;

	final Calendar mCalendar = Calendar.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_vaccination);

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		mCurrentDate = dateFormat.format(date);
		mAlarm = "0";

		etDate = (EditText) findViewById(R.id.etVaccinationDate);
		etTime = (EditText) findViewById(R.id.etVaccinationTime);
		etName = (EditText) findViewById(R.id.etVaccinationName);
		etDescription = (EditText) findViewById(R.id.etVaccinationDescription);

		cbAlarm = (CheckBox) findViewById(R.id.VaccinationAlarm);
		btns_save = (Button) findViewById(R.id.btnVaccineSave);

		// btn_plus = (Button) findViewById(R.id.btnPlus);
		// btn_minus = (Button) findViewById(R.id.btnMinus);
		etDate.setOnClickListener(this);
		etTime.setOnClickListener(this);

		cbAlarm.setOnClickListener(this);
		btns_save.setOnClickListener(this);

		Intent mActivityIntent = getIntent();
		mStrActivityID = mActivityIntent.getStringExtra("id");

		if (mStrActivityID != null) {
			mActivityId = Long.parseLong(mStrActivityID);

			/*
			 * get the activity which include all data from database according
			 * profileId of the clicked item.
			 */
			mActivityDataSource = new VaccinationDataSourc(this);
			mUpdateActivity = mActivityDataSource
					.updateActivityData(mActivityId);

			String mDate = mUpdateActivity.getDate();
			String mTime = mUpdateActivity.getTime();
			String mName = mUpdateActivity.getVaccineName();
			String mDescription = mUpdateActivity.getDescription();
			String mAlarm = mUpdateActivity.getVaccineAlarm();
			long mActivityAlarm = Long.parseLong(mAlarm);

			// set the value of database to the text field.
			etDate.setText(mDate);
			etTime.setText(mTime);
			etName.setText(mName);
			etDescription.setText(mDescription);
			// cbAlarm.setText("Alarm"+mAlarm);
			if (mActivityAlarm == 1) {
				cbAlarm.setChecked(!cbAlarm.isChecked());
			}

			/*
			 * change button name
			 */
			btns_save.setText("Update");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.icare_create_diet_chart, menu);
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

	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		// TODO Auto-generated method stub

		mSetHour = hourOfDay;
		mSetMinute = minute;
		int hour = 0;
		String st = "";
		if (hourOfDay > 12) {
			hour = hourOfDay - 12;
			st = "PM";
		}

		else if (hourOfDay == 12) {
			hour = hourOfDay;
			st = "PM";
		}

		else if (hourOfDay == 0) {
			hour = hourOfDay + 12;
			st = "AM";
		} else {
			hour = hourOfDay;
			st = "AM";
		}
		etTime.setText(new StringBuilder().append(hour).append(" : ")
				.append(minute).append(" ").append(st));

	}

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		// TODO Auto-generated method stub
		etDate.setText(new StringBuilder()
				// Month is 0 based so add 1
				.append(dayOfMonth).append("/").append(monthOfYear + 1)
				.append("/").append(year));
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Toast toast = null;
		switch (v.getId()) {
		case R.id.etVaccinationDate:
			mYear = mCalendar.get(Calendar.YEAR);
			mMonth = mCalendar.get(Calendar.MONTH);
			mDay = mCalendar.get(Calendar.DAY_OF_MONTH);

			DatePickerDialog dialog = new DatePickerDialog(this, this, mYear,
					mMonth, mDay);
			dialog.show();
			break;
		case R.id.etVaccinationTime:
			// Process to get Current Time

			mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
			mMinute = mCalendar.get(Calendar.MINUTE);

			// Launch Time Picker Dialog
			TimePickerDialog tpd = new TimePickerDialog(this, this, mHour,
					mMinute, false);
			tpd.show();
			break;

		case R.id.btnVaccineSave:

			mDate = etDate.getText().toString();
			mTime = etTime.getText().toString();
			mName = etName.getText().toString();

			mDescription = etDescription.getText().toString();

			if (cbAlarm.isChecked()) {
				mAlarm = "1";

				Intent alarmIntent = new Intent(AlarmClock.ACTION_SET_ALARM);
				alarmIntent.putExtra(AlarmClock.EXTRA_MESSAGE, mDescription);
				alarmIntent.putExtra(AlarmClock.EXTRA_HOUR, mSetHour);
				alarmIntent.putExtra(AlarmClock.EXTRA_MINUTES, mSetMinute);
				alarmIntent.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
				alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(alarmIntent);
			}

			Vaccination activityDataInsert = new Vaccination();
			activityDataInsert.setDate(mDate);
			activityDataInsert.setTime(mTime);
			activityDataInsert.setVaccineName(mName);
			activityDataInsert.setDescription(mDescription);

			activityDataInsert.setVaccineAlarm(mAlarm);

			/*
			 * if update is needed then update otherwise submit
			 */
			if (mStrActivityID != null) {

				mActivityId = Long.parseLong(mStrActivityID);

				activityDS = new VaccinationDataSourc(this);

				if (activityDS.updateData(mActivityId, activityDataInsert) == true) {
					toast = Toast.makeText(this, "Successfully Updated.",
							Toast.LENGTH_LONG);
					toast.show();

					startActivity(new Intent(CreateVaccinationProfileActivity.this,
							VaccinationListActivity.class));
					finish();
				} else {
					toast = Toast.makeText(this, "Not Updated.",
							Toast.LENGTH_LONG);
					toast.show();
				}
			} else {
				activityDS = new VaccinationDataSourc(this);
				if (activityDS.insert(activityDataInsert) == true) {
					toast = Toast.makeText(this, "Successfully Saved.",
							Toast.LENGTH_LONG);
					toast.show();

					startActivity(new Intent(CreateVaccinationProfileActivity.this,
							ProfileListHomeActivity.class));
					/*
					 * startActivity(new Intent(
					 * ICareCreateDietChartActivity.this,
					 * ICareDietChartListActivity.class));
					 */
					// finish();
				} else {
					toast = Toast.makeText(this, "Not Saved.",
							Toast.LENGTH_LONG);
					toast.show();
				}
			}
			break;

		}

	}
}
