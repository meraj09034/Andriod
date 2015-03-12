package com.ftfl.icareapp.database;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ftfl.icareapp.model.Vaccination;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class VaccinationDataSourc {

	// Database fields
	private SQLiteDatabase iCareVacccineDatabase;
	private DatabaseSQLiteHelper iCareVaccineDbHelper;
	List<Vaccination> dailyVaccineDietChart = new ArrayList<Vaccination>();
	List<Vaccination> todayVaccineDietChart = new ArrayList<Vaccination>();
	List<Vaccination> mPlaces = new ArrayList<Vaccination>();
	List<String> upcomingVaccineDates = new ArrayList<String>();
	List<Vaccination> iCareDoctorProfilesList = new ArrayList<Vaccination>();
	List<String> allVaccineDates = new ArrayList<String>();
	public String mCurrentDate = "";

	public VaccinationDataSourc(Context context) {
		iCareVaccineDbHelper = new DatabaseSQLiteHelper(context);
	}

	/*
	 * open a method for writable database
	 */
	public void open() throws SQLException {
		iCareVacccineDatabase = iCareVaccineDbHelper.getWritableDatabase();
	}

	/*
	 * close database connection
	 */
	public void close() {
		iCareVaccineDbHelper.close();
	}

	public void cDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/M/yyyy");
		Date date = new Date();
		mCurrentDate = dateFormat.format(date);
	}

	/*
	 * insert data into the database.
	 */

	public boolean insert(Vaccination eActivityChart) {

		this.open();

		ContentValues cv = new ContentValues();

		cv.put(DatabaseSQLiteHelper.COL_ICARE_VACCINE_DATE,
				eActivityChart.getDate());
		cv.put(DatabaseSQLiteHelper.COL_ICARE_VACCINE_TIME,
				eActivityChart.getTime());
		cv.put(DatabaseSQLiteHelper.COL_ICARE_VACCINE_EVENT_NAME,
				eActivityChart.getVaccineName());
		cv.put(DatabaseSQLiteHelper.COL_ICARE_VACCINE_DESCRIPTION,
				eActivityChart.getDescription());
		cv.put(DatabaseSQLiteHelper.COL_ICARE_VACCINE__ALARM,
				eActivityChart.getVaccineAlarm());

		Long check = iCareVacccineDatabase.insert(
				DatabaseSQLiteHelper.ICARE_VACCINE_CHART, null, cv);
		iCareVacccineDatabase.close();

		this.close();

		if (check < 0)
			return false;
		else
			return true;
	}

	// Updating database by id
	public boolean updateData(long eActivityId, Vaccination eActivityChart) {
		this.open();
		ContentValues cvUpdate = new ContentValues();

		cvUpdate.put(DatabaseSQLiteHelper.COL_ICARE_VACCINE_DATE,
				eActivityChart.getDate());
		cvUpdate.put(DatabaseSQLiteHelper.COL_ICARE_VACCINE_TIME,
				eActivityChart.getTime());
		cvUpdate.put(DatabaseSQLiteHelper.COL_ICARE_VACCINE_EVENT_NAME,
				eActivityChart.getVaccineName());
		cvUpdate.put(DatabaseSQLiteHelper.COL_ICARE_VACCINE_DESCRIPTION,
				eActivityChart.getDescription());
		cvUpdate.put(DatabaseSQLiteHelper.COL_ICARE_VACCINE__ALARM,
				eActivityChart.getVaccineAlarm());

		int check = iCareVacccineDatabase.update(
				DatabaseSQLiteHelper.ICARE_VACCINE_CHART, cvUpdate,
				DatabaseSQLiteHelper.COL_ICARE_VACCINE_ID + "=" + eActivityId,
				null);
		iCareVacccineDatabase.close();

		this.close();
		if (check == 0)
			return false;
		else
			return true;
	}

	// delete data form database.
	public boolean deleteData(long eActivityId) {
		this.open();
		try {
			iCareVacccineDatabase.delete(
					DatabaseSQLiteHelper.ICARE_VACCINE_CHART,
					DatabaseSQLiteHelper.COL_ICARE_VACCINE_ID + "="
							+ eActivityId, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data insertion problem");
			return false;
		}
		this.close();
		return true;
	}

	/*
	 * using cursor for display data from database.
	 */
	public List<Vaccination> dailyVaccineDietChart(String eDate) {
		this.open();
		this.cDate();

		Cursor mCursor = iCareVacccineDatabase.query(
				DatabaseSQLiteHelper.ICARE_VACCINE_CHART, new String[] {
						DatabaseSQLiteHelper.COL_ICARE_VACCINE_ID,
						DatabaseSQLiteHelper.COL_ICARE_VACCINE_DATE,
						DatabaseSQLiteHelper.COL_ICARE_VACCINE_TIME,
						DatabaseSQLiteHelper.COL_ICARE_VACCINE_EVENT_NAME,
						DatabaseSQLiteHelper.COL_ICARE_VACCINE_DESCRIPTION,
						DatabaseSQLiteHelper.COL_ICARE_VACCINE__ALARM,

				}, DatabaseSQLiteHelper.COL_ICARE_VACCINE_DATE + "= '" + eDate
						+ "' ", null, null, null, null);

		if (mCursor != null) {
			if (mCursor.moveToFirst()) {

				do {

					String mActivityId = mCursor.getString(mCursor
							.getColumnIndex("vaccine_id"));
					String mDate = mCursor.getString(mCursor
							.getColumnIndex("vaccine_date"));
					String mTime = mCursor.getString(mCursor
							.getColumnIndex("vaccine_time"));
					String mDescription = mCursor.getString(mCursor
							.getColumnIndex("description"));
					String mName = mCursor.getString(mCursor
							.getColumnIndex("vaccine__name"));
					String mSetAlarm = mCursor.getString(mCursor
							.getColumnIndex("vaccine_set_alarm"));

					dailyVaccineDietChart.add(new Vaccination(mActivityId,
							mDate, mTime, mName, mDescription, mSetAlarm));

				} while (mCursor.moveToNext());
			}
			mCursor.close();
		}
		this.close();
		return dailyVaccineDietChart;
	}

	/*
	 * using cursor for display data from database.
	 */
	public List<Vaccination> todayDietChart() {
		this.open();
		this.cDate();

		Cursor mCursor = iCareVacccineDatabase.query(
				DatabaseSQLiteHelper.ICARE_VACCINE_CHART, new String[] {
						DatabaseSQLiteHelper.COL_ICARE_VACCINE_ID,
						DatabaseSQLiteHelper.COL_ICARE_VACCINE_DATE,
						DatabaseSQLiteHelper.COL_ICARE_VACCINE_TIME,
						DatabaseSQLiteHelper.COL_ICARE_VACCINE_EVENT_NAME,
						DatabaseSQLiteHelper.COL_ICARE_VACCINE_DESCRIPTION,
						DatabaseSQLiteHelper.COL_ICARE_VACCINE__ALARM,

				}, DatabaseSQLiteHelper.COL_ICARE_VACCINE_DATE + "= '"
						+ mCurrentDate + "' ", null, null, null, null);

		if (mCursor != null) {
			if (mCursor.moveToFirst()) {

				do {

					String mActivityId = mCursor.getString(mCursor
							.getColumnIndex("vaccine_id"));
					String mActivityDate = mCursor.getString(mCursor
							.getColumnIndex("vaccine_date"));
					String mActivityTime = mCursor.getString(mCursor
							.getColumnIndex("vaccine_time"));
					String mDesciption = mCursor.getString(mCursor
							.getColumnIndex("description"));
					String mDietEventName = mCursor.getString(mCursor
							.getColumnIndex("vaccine__name"));
					String mSetAlarm = mCursor.getString(mCursor
							.getColumnIndex("vaccine_set_alarm"));

					todayVaccineDietChart.add(new Vaccination(mActivityId,
							mActivityDate, mActivityTime, mDesciption,
							mDietEventName, mSetAlarm));

				} while (mCursor.moveToNext());
			}
			mCursor.close();
		}
		this.close();
		return todayVaccineDietChart;
	}

	/*
	 * using cursor for display data from database.
	 */
	public List<String> upcomingDates() {
		this.open();
		this.cDate();

		Cursor mCursor = iCareVacccineDatabase.rawQuery(
				"SELECT DISTINCT vaccine_date FROM icarevacinationchart  WHERE date > '"
						+ mCurrentDate + "'", null);

		if (mCursor != null) {
			if (mCursor.moveToFirst()) {

				do {
					String mActivityDate = mCursor.getString(mCursor
							.getColumnIndex("vaccine_date"));
					upcomingVaccineDates.add(mActivityDate);

				} while (mCursor.moveToNext());
			}
			mCursor.close();
		}
		this.close();
		return upcomingVaccineDates;
	}

	/*
	 * using cursor for display data from database.
	 */
	public List<String> allDates() {
		this.open();
		this.cDate();

		Cursor mCursor = iCareVacccineDatabase.rawQuery(
				"SELECT DISTINCT vaccine_date FROM icarevacinationchart", null);

		if (mCursor != null) {
			if (mCursor.moveToFirst()) {

				do {
					String mActivityDate = mCursor.getString(mCursor
							.getColumnIndex("vaccine_date"));
					allVaccineDates.add(mActivityDate);

				} while (mCursor.moveToNext());
			}
			mCursor.close();
		}
		this.close();
		return allVaccineDates;
	}

	/*
	 * create a profile of ICareProfile. Here the data of the database according
	 * to the given id is set to the profile and return a profile.
	 */

	public Vaccination updateActivityData(long eActivityId) {
		this.open();
		Vaccination iCareUpdateActivity;

		Cursor mUpdateCursor = iCareVacccineDatabase.query(
				DatabaseSQLiteHelper.ICARE_VACCINE_CHART, new String[] {
						DatabaseSQLiteHelper.COL_ICARE_VACCINE_ID,
						DatabaseSQLiteHelper.COL_ICARE_VACCINE_DATE,
						DatabaseSQLiteHelper.COL_ICARE_VACCINE_TIME,
						DatabaseSQLiteHelper.COL_ICARE_VACCINE_DESCRIPTION,
						DatabaseSQLiteHelper.COL_ICARE_VACCINE_EVENT_NAME,
						DatabaseSQLiteHelper.COL_ICARE_VACCINE__ALARM,

				}, DatabaseSQLiteHelper.COL_ICARE_VACCINE_ID + "="
						+ eActivityId, null, null, null, null);

		mUpdateCursor.moveToFirst();

		String mActivityId = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex("vaccine_id"));
		String mActivityDate = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex("vaccine_date"));
		String mActivityTime = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex("vaccine_time"));
		String mDietFoodMenu = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex("description"));
		String mDietEventName = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex("vaccine__name"));
		String mSetAlarm = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex("vaccine_set_alarm"));

		mUpdateCursor.close();
		iCareUpdateActivity = new Vaccination(mActivityId, mActivityDate,
				mActivityTime, mDietEventName, mDietFoodMenu, mSetAlarm);

		this.close();
		return iCareUpdateActivity;
	}

	public List<Vaccination> iCareVaccineProfilesList() {
		this.open();

		Cursor mCursor = iCareVacccineDatabase.query(
				DatabaseSQLiteHelper.ICARE_VACCINE_CHART, new String[] {
						DatabaseSQLiteHelper.COL_ICARE_VACCINE_ID,
						DatabaseSQLiteHelper.COL_ICARE_VACCINE_DATE,
						DatabaseSQLiteHelper.COL_ICARE_VACCINE_TIME,
						DatabaseSQLiteHelper.COL_ICARE_VACCINE_EVENT_NAME,
						DatabaseSQLiteHelper.COL_ICARE_VACCINE_DESCRIPTION,
						DatabaseSQLiteHelper.COL_ICARE_VACCINE__ALARM }, null,
				null, null, null, null);

		if (mCursor != null) {
			if (mCursor.moveToFirst()) {

				do {

					String mDOctorId = mCursor.getString(mCursor
							.getColumnIndex("vaccine_id"));
					String mNDoctorame = mCursor.getString(mCursor
							.getColumnIndex("vaccine_date"));
					String mSpecilaity = mCursor.getString(mCursor
							.getColumnIndex("vaccine_time"));
					String mPhone = mCursor.getString(mCursor
							.getColumnIndex("description"));
					String mEmail = mCursor.getString(mCursor
							.getColumnIndex("vaccine__name"));
					String mChamber = mCursor.getString(mCursor
							.getColumnIndex("vaccine_set_alarm"));
					// long mmId = Long.parseLong(mId);
					iCareDoctorProfilesList
							.add(new Vaccination(mDOctorId, mNDoctorame,
									mSpecilaity, mEmail, mPhone, mChamber));

				} while (mCursor.moveToNext());
			}
			mCursor.close();
		}
		this.close();
		return iCareDoctorProfilesList;
	}

	public Vaccination singleVaccineProfileData(long mActivityId) {
		this.open();
		Vaccination informationObject;

		String mDOctorId;
		String mNDoctorame;
		String mSpecilaity;
		String mPhone;
		String mEmail;
		String mChamber;

		Cursor mUpdateCursor = iCareVacccineDatabase.query(
				DatabaseSQLiteHelper.ICARE_VACCINE_CHART, new String[] {
						DatabaseSQLiteHelper.COL_ICARE_VACCINE_ID,
						DatabaseSQLiteHelper.COL_ICARE_VACCINE_EVENT_NAME,
						DatabaseSQLiteHelper.COL_ICARE_VACCINE_DATE,
						DatabaseSQLiteHelper.COL_ICARE_VACCINE_TIME,
						DatabaseSQLiteHelper.COL_ICARE_VACCINE_DESCRIPTION,
						DatabaseSQLiteHelper.COL_ICARE_VACCINE__ALARM, },
				DatabaseSQLiteHelper.COL_ICARE_VACCINE_ID + "=" + mActivityId,
				null, null, null, null);

		mUpdateCursor.moveToFirst();

		mDOctorId = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(DatabaseSQLiteHelper.COL_ICARE_VACCINE_ID));
		mNDoctorame = mUpdateCursor
				.getString(mUpdateCursor
						.getColumnIndex(DatabaseSQLiteHelper.COL_ICARE_VACCINE_EVENT_NAME));
		mSpecilaity = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(DatabaseSQLiteHelper.COL_ICARE_VACCINE_DATE));
		mPhone = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(DatabaseSQLiteHelper.COL_ICARE_VACCINE_TIME));
		mEmail = mUpdateCursor
				.getString(mUpdateCursor
						.getColumnIndex(DatabaseSQLiteHelper.COL_ICARE_VACCINE_DESCRIPTION));

		mChamber = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(DatabaseSQLiteHelper.COL_ICARE_VACCINE__ALARM));
		mUpdateCursor.close();
		informationObject = new Vaccination(mDOctorId, mSpecilaity, mPhone,
				mNDoctorame, mEmail, mChamber);
		this.close();
		return informationObject;
	}

	public boolean isEmpty() {
		this.open();
		Cursor mCursor = iCareVacccineDatabase.query(
				DatabaseSQLiteHelper.ICARE_VACCINE_CHART, new String[] {
						DatabaseSQLiteHelper.COL_ICARE_VACCINE_ID,
						DatabaseSQLiteHelper.COL_ICARE_VACCINE_EVENT_NAME,
						DatabaseSQLiteHelper.COL_ICARE_VACCINE_DATE,
						DatabaseSQLiteHelper.COL_ICARE_VACCINE_TIME,
						DatabaseSQLiteHelper.COL_ICARE_VACCINE_DESCRIPTION,
						DatabaseSQLiteHelper.COL_ICARE_VACCINE__ALARM }, null,
				null, null, null, null);
		if (mCursor.getCount() == 0) {
			this.close();
			return true;
		}

		else {
			this.close();
			return false;
		}
	}
}
