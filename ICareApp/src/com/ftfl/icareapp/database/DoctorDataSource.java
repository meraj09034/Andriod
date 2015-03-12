package com.ftfl.icareapp.database;

import java.util.ArrayList;
import java.util.List;

import com.ftfl.icareapp.model.DoctorProfile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class DoctorDataSource {

	// Database fields
		private SQLiteDatabase iCareDoctorProfileDatabase;
		private DatabaseSQLiteHelper iCareDbHelper;
		List<DoctorProfile> iCareDoctorProfilesList = new ArrayList<DoctorProfile>();

		public DoctorDataSource(Context context) {
			iCareDbHelper = new DatabaseSQLiteHelper(context);
		}

		/*
		 * open a method for writable database
		 */
		public void open() throws SQLException {
			iCareDoctorProfileDatabase = iCareDbHelper.getWritableDatabase();
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

		public boolean insert(DoctorProfile iprofile) {

			this.open();

			ContentValues cv = new ContentValues();

			cv.put(DatabaseSQLiteHelper.COL_ICARE_DOCTOR_PROFILE_NAME, iprofile.getDoctorName());

			cv.put(DatabaseSQLiteHelper.COL_ICARE_DOCTOR_PROFILE_SPECIALITY, iprofile.getSpeciality());
			cv.put(DatabaseSQLiteHelper.COL_ICARE_DOCTOR_PROFILE_PHONE,
					iprofile.getPhone());
			cv.put(DatabaseSQLiteHelper.COL_ICARE_DOCTOR_PROFILE_EMAIL,
					iprofile.getEmail());
			cv.put(DatabaseSQLiteHelper.COL_ICARE_DOCTOR_PROFILE_CHAMBER,
					iprofile.getChamber());
		

			Long check = iCareDoctorProfileDatabase.insert(
					DatabaseSQLiteHelper.I_CARE_DOCTOR_PROFILE, null, cv);
			iCareDoctorProfileDatabase.close();

			this.close();
			if (check < 0)
				return false;
			else
				return true;
		}

		// Updating database by id
		public boolean updateData(long profileId, DoctorProfile updateprofile) {
			this.open();
			ContentValues cvUpdate = new ContentValues();

			cvUpdate.put(DatabaseSQLiteHelper.COL_ICARE_DOCTOR_PROFILE_NAME,
					updateprofile.getDoctorName());
			cvUpdate.put(DatabaseSQLiteHelper.COL_ICARE_DOCTOR_PROFILE_SPECIALITY,
					updateprofile.getSpeciality());
			cvUpdate.put(DatabaseSQLiteHelper.COL_ICARE_DOCTOR_PROFILE_PHONE,
					updateprofile.getPhone());
			cvUpdate.put(DatabaseSQLiteHelper.COL_ICARE_DOCTOR_PROFILE_EMAIL,
					updateprofile.getEmail());
			cvUpdate.put(DatabaseSQLiteHelper.COL_ICARE_DOCTOR_PROFILE_CHAMBER,
					updateprofile.getChamber());
			

			int check = iCareDoctorProfileDatabase.update(
					DatabaseSQLiteHelper.I_CARE_DOCTOR_PROFILE, cvUpdate,
					DatabaseSQLiteHelper.COL_ICARE_DOCTOR_PROFILE_ID+ "=" + profileId,
					null);
			iCareDoctorProfileDatabase.close();

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
				iCareDoctorProfileDatabase
						.delete(DatabaseSQLiteHelper.I_CARE_DOCTOR_PROFILE,
								DatabaseSQLiteHelper.COL_ICARE_DOCTOR_PROFILE_ID + "="
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
		public List<DoctorProfile> iCareDoctorProfilesList() {
			this.open();

			Cursor mCursor = iCareDoctorProfileDatabase.query(
					DatabaseSQLiteHelper.I_CARE_DOCTOR_PROFILE, new String[] {
							DatabaseSQLiteHelper.COL_ICARE_DOCTOR_PROFILE_ID,
							DatabaseSQLiteHelper.COL_ICARE_DOCTOR_PROFILE_NAME,
							DatabaseSQLiteHelper.COL_ICARE_DOCTOR_PROFILE_SPECIALITY,
							DatabaseSQLiteHelper.COL_ICARE_DOCTOR_PROFILE_PHONE,
							DatabaseSQLiteHelper.COL_ICARE_DOCTOR_PROFILE_EMAIL,
							DatabaseSQLiteHelper.COL_ICARE_DOCTOR_PROFILE_CHAMBER },
					null, null, null, null, null);

			if (mCursor != null) {
				if (mCursor.moveToFirst()) {

					do {

						String mDOctorId = mCursor
								.getString(mCursor.getColumnIndex("id"));
						String mNDoctorame = mCursor.getString(mCursor
								.getColumnIndex("doctor_name"));
						String mSpecilaity = mCursor.getString(mCursor
								.getColumnIndex("speciality"));
						String mPhone = mCursor.getString(mCursor
								.getColumnIndex("phone"));
						String mEmail = mCursor.getString(mCursor
								.getColumnIndex("email"));
						String mChamber = mCursor.getString(mCursor
								.getColumnIndex("chamber"));
						// long mmId = Long.parseLong(mId);
						iCareDoctorProfilesList.add(new DoctorProfile(mDOctorId, mNDoctorame, mSpecilaity,
								mPhone, mEmail, mChamber));

					} while (mCursor.moveToNext());
				}
				mCursor.close();
			}
			this.close();
			return iCareDoctorProfilesList;
		}

		/*
		 * create a profile of ICareProfile. Here the data of the database according
		 * to the given id is set to the profile and return a profile.
		 */
		public DoctorProfile singleDoctorProfileData(long mActivityId) {
			this.open();
			DoctorProfile informationObject;
			
			String mDOctorId;
			String mNDoctorame;
			String mSpecilaity;
			String mPhone;
			String mEmail;
			String mChamber;
		

			Cursor mUpdateCursor = iCareDoctorProfileDatabase.query(
					DatabaseSQLiteHelper.I_CARE_DOCTOR_PROFILE, new String[] {
							DatabaseSQLiteHelper.COL_ICARE_DOCTOR_PROFILE_ID,
							DatabaseSQLiteHelper.COL_ICARE_DOCTOR_PROFILE_NAME,
							DatabaseSQLiteHelper.COL_ICARE_DOCTOR_PROFILE_SPECIALITY,
							DatabaseSQLiteHelper.COL_ICARE_DOCTOR_PROFILE_PHONE,
							DatabaseSQLiteHelper.COL_ICARE_DOCTOR_PROFILE_EMAIL,
							DatabaseSQLiteHelper.COL_ICARE_DOCTOR_PROFILE_CHAMBER, },
					DatabaseSQLiteHelper.COL_ICARE_PROFILE_ID + "=" + mActivityId,
					null, null, null, null);

			mUpdateCursor.moveToFirst();

			mDOctorId = mUpdateCursor.getString(mUpdateCursor
					.getColumnIndex(DatabaseSQLiteHelper.COL_ICARE_DOCTOR_PROFILE_ID));
			mNDoctorame = mUpdateCursor.getString(mUpdateCursor
					.getColumnIndex(DatabaseSQLiteHelper.COL_ICARE_DOCTOR_PROFILE_NAME));
			mSpecilaity = mUpdateCursor.getString(mUpdateCursor
					.getColumnIndex(DatabaseSQLiteHelper.COL_ICARE_DOCTOR_PROFILE_SPECIALITY));
			mPhone = mUpdateCursor.getString(mUpdateCursor
					.getColumnIndex(DatabaseSQLiteHelper.COL_ICARE_DOCTOR_PROFILE_PHONE));
			mEmail = mUpdateCursor.getString(mUpdateCursor
					.getColumnIndex(DatabaseSQLiteHelper.COL_ICARE_DOCTOR_PROFILE_EMAIL));
		
			mChamber = mUpdateCursor
					.getString(mUpdateCursor
							.getColumnIndex(DatabaseSQLiteHelper.COL_ICARE_DOCTOR_PROFILE_CHAMBER));
			mUpdateCursor.close();
			informationObject = new DoctorProfile(mDOctorId, mNDoctorame, mSpecilaity,
					mPhone, mEmail, mChamber);
			this.close();
			return informationObject;
		}

		public boolean isEmpty() {
			this.open();
			Cursor mCursor = iCareDoctorProfileDatabase.query(
					DatabaseSQLiteHelper.I_CARE_DOCTOR_PROFILE, new String[] {
							DatabaseSQLiteHelper.COL_ICARE_DOCTOR_PROFILE_ID,
							DatabaseSQLiteHelper.COL_ICARE_DOCTOR_PROFILE_NAME,
							DatabaseSQLiteHelper.COL_ICARE_DOCTOR_PROFILE_SPECIALITY,
							DatabaseSQLiteHelper.COL_ICARE_DOCTOR_PROFILE_PHONE,
							DatabaseSQLiteHelper.COL_ICARE_DOCTOR_PROFILE_EMAIL,
							DatabaseSQLiteHelper.COL_ICARE_DOCTOR_PROFILE_CHAMBER },
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