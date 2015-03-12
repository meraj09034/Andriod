package com.ftfl.icareapp.database;

import java.util.ArrayList;
import java.util.List;

import com.ftfl.icareapp.model.Profile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ProfileDataSource {

	// Database fields
	private SQLiteDatabase iCareProfileDatabase;
	private DatabaseSQLiteHelper iCareDbHelper;
	List<Profile> iCareProfilesList = new ArrayList<Profile>();

	public ProfileDataSource(Context context) {
		iCareDbHelper = new DatabaseSQLiteHelper(context);
	}

	/*
	 * open a method for writable database
	 */
	public void open() throws SQLException {
		iCareProfileDatabase = iCareDbHelper.getWritableDatabase();
	}

	/*
	 * close database connection
	 */
	public void close() {
		iCareDbHelper.close();
	}

	/*
	 * insert data into the database.
	 */

	public boolean insert(Profile iprofile) {

		this.open();

		ContentValues cv = new ContentValues();

		cv.put(DatabaseSQLiteHelper.COL_ICARE_PROFILE_NAME, iprofile.getName());

		cv.put(DatabaseSQLiteHelper.COL_ICARE_PROFILE_AGE, iprofile.getAge());
		cv.put(DatabaseSQLiteHelper.COL_ICARE_PROFILE_WEIGHT,
				iprofile.getWeight());
		cv.put(DatabaseSQLiteHelper.COL_ICARE_PROFILE_HEIGHT,
				iprofile.getHeight());
		cv.put(DatabaseSQLiteHelper.COL_ICARE_PROFILE_GENDER,
				iprofile.getGender());
		cv.put(DatabaseSQLiteHelper.COL_ICARE_PROFILE_BLOOD_GROUP,
				iprofile.getBloodGroup());

		Long check = iCareProfileDatabase.insert(
				DatabaseSQLiteHelper.I_CARE_PROFILE, null, cv);
		iCareProfileDatabase.close();

		this.close();
		if (check < 0)
			return false;
		else
			return true;
	}

	// Updating database by id
	public boolean updateData(long profileId, Profile updateprofile) {
		this.open();
		ContentValues cvUpdate = new ContentValues();

		cvUpdate.put(DatabaseSQLiteHelper.COL_ICARE_PROFILE_NAME,
				updateprofile.getName());
		cvUpdate.put(DatabaseSQLiteHelper.COL_ICARE_PROFILE_AGE,
				updateprofile.getAge());
		cvUpdate.put(DatabaseSQLiteHelper.COL_ICARE_PROFILE_WEIGHT,
				updateprofile.getWeight());
		cvUpdate.put(DatabaseSQLiteHelper.COL_ICARE_PROFILE_HEIGHT,
				updateprofile.getHeight());
		cvUpdate.put(DatabaseSQLiteHelper.COL_ICARE_PROFILE_GENDER,
				updateprofile.getGender());
		cvUpdate.put(DatabaseSQLiteHelper.COL_ICARE_PROFILE_BLOOD_GROUP,
				updateprofile.getBloodGroup());

		int check = iCareProfileDatabase.update(
				DatabaseSQLiteHelper.I_CARE_PROFILE, cvUpdate,
				DatabaseSQLiteHelper.COL_ICARE_PROFILE_ID + "=" + profileId,
				null);
		iCareProfileDatabase.close();

		this.close();
		if (check == 0)
			return false;
		else
			return true;
	}

	// delete data form database.
	public boolean deleteData(long profileId) {
		this.open();
		try {
			iCareProfileDatabase
					.delete(DatabaseSQLiteHelper.I_CARE_PROFILE,
							DatabaseSQLiteHelper.COL_ICARE_PROFILE_ID + "="
									+ profileId, null);
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
	public List<Profile> iCareProfilesList() {
		this.open();

		Cursor mCursor = iCareProfileDatabase.query(
				DatabaseSQLiteHelper.I_CARE_PROFILE, new String[] {
						DatabaseSQLiteHelper.COL_ICARE_PROFILE_ID,
						DatabaseSQLiteHelper.COL_ICARE_PROFILE_NAME,
						DatabaseSQLiteHelper.COL_ICARE_PROFILE_AGE,
						DatabaseSQLiteHelper.COL_ICARE_PROFILE_WEIGHT,
						DatabaseSQLiteHelper.COL_ICARE_PROFILE_HEIGHT,
						DatabaseSQLiteHelper.COL_ICARE_PROFILE_GENDER,
						DatabaseSQLiteHelper.COL_ICARE_PROFILE_BLOOD_GROUP },
				null, null, null, null, null);

		if (mCursor != null) {
			if (mCursor.moveToFirst()) {

				do {

					String mId = mCursor
							.getString(mCursor.getColumnIndex("id"));
					String mName = mCursor.getString(mCursor
							.getColumnIndex("name"));
					String mAge = mCursor.getString(mCursor
							.getColumnIndex("age"));
					String mWeight = mCursor.getString(mCursor
							.getColumnIndex("weight"));
					String mHeight = mCursor.getString(mCursor
							.getColumnIndex("height"));
					String mGender = mCursor.getString(mCursor
							.getColumnIndex("gender"));
					String mBloodGroup = mCursor.getString(mCursor
							.getColumnIndex("blood_group"));
					// long mmId = Long.parseLong(mId);
					iCareProfilesList.add(new Profile(mId, mName, mAge,
							mWeight, mHeight, mGender, mBloodGroup));

				} while (mCursor.moveToNext());
			}
			mCursor.close();
		}
		this.close();
		return iCareProfilesList;
	}

	/*
	 * create a profile of ICareProfile. Here the data of the database according
	 * to the given id is set to the profile and return a profile.
	 */
	public Profile singleProfileData(long mActivityId) {
		this.open();
		Profile informationObject;
		//int profileID = 1;
		String mId;
		String mName;
		String mAge;
		String mBloodGroup;
		String mWeight;
		String mHeight;
		String mGender;

		Cursor mUpdateCursor = iCareProfileDatabase.query(
				DatabaseSQLiteHelper.I_CARE_PROFILE, new String[] {
						DatabaseSQLiteHelper.COL_ICARE_PROFILE_ID,
						DatabaseSQLiteHelper.COL_ICARE_PROFILE_NAME,
						DatabaseSQLiteHelper.COL_ICARE_PROFILE_AGE,
						DatabaseSQLiteHelper.COL_ICARE_PROFILE_WEIGHT,
						DatabaseSQLiteHelper.COL_ICARE_PROFILE_HEIGHT,
						DatabaseSQLiteHelper.COL_ICARE_PROFILE_GENDER,
						DatabaseSQLiteHelper.COL_ICARE_PROFILE_BLOOD_GROUP, },
				DatabaseSQLiteHelper.COL_ICARE_PROFILE_ID + "=" + mActivityId,
				null, null, null, null);

		mUpdateCursor.moveToFirst();

		mId = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(DatabaseSQLiteHelper.COL_ICARE_PROFILE_ID));
		mName = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(DatabaseSQLiteHelper.COL_ICARE_PROFILE_NAME));
		mAge = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(DatabaseSQLiteHelper.COL_ICARE_PROFILE_AGE));
		mWeight = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(DatabaseSQLiteHelper.COL_ICARE_PROFILE_WEIGHT));
		mHeight = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(DatabaseSQLiteHelper.COL_ICARE_PROFILE_HEIGHT));
		mGender = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(DatabaseSQLiteHelper.COL_ICARE_PROFILE_GENDER));
		mBloodGroup = mUpdateCursor
				.getString(mUpdateCursor
						.getColumnIndex(DatabaseSQLiteHelper.COL_ICARE_PROFILE_BLOOD_GROUP));
		mUpdateCursor.close();
		informationObject = new Profile(mId, mName, mAge, mWeight, mHeight,
				mGender, mBloodGroup);
		this.close();
		return informationObject;
	}

	public boolean isEmpty() {
		this.open();
		Cursor mCursor = iCareProfileDatabase.query(
				DatabaseSQLiteHelper.I_CARE_PROFILE, new String[] {
						DatabaseSQLiteHelper.COL_ICARE_PROFILE_ID,
						DatabaseSQLiteHelper.COL_ICARE_PROFILE_NAME,
						DatabaseSQLiteHelper.COL_ICARE_PROFILE_AGE,
						DatabaseSQLiteHelper.COL_ICARE_PROFILE_WEIGHT,
						DatabaseSQLiteHelper.COL_ICARE_PROFILE_HEIGHT,
						DatabaseSQLiteHelper.COL_ICARE_PROFILE_GENDER,
						DatabaseSQLiteHelper.COL_ICARE_PROFILE_BLOOD_GROUP },
				null, null, null, null, null);
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
