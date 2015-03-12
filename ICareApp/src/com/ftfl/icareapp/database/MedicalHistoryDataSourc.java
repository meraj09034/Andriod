package com.ftfl.icareapp.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ftfl.icareapp.model.MedicalHistory;

public class MedicalHistoryDataSourc {
	// Database fields
			private SQLiteDatabase iCareMedicalHistoryProfileDatabase;
			private DatabaseSQLiteHelper iCareDbHelper;
			List<MedicalHistory> iCareDoctorProfilesList = new ArrayList<MedicalHistory>();

			public MedicalHistoryDataSourc(Context context) {
				iCareDbHelper = new DatabaseSQLiteHelper(context);
			}

			/*
			 * open a method for writable database
			 */
			public void open() throws SQLException {
				iCareMedicalHistoryProfileDatabase = iCareDbHelper.getWritableDatabase();
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

			public boolean insert(MedicalHistory iprofile) {

				this.open();

				ContentValues cv = new ContentValues();

				cv.put(DatabaseSQLiteHelper.COL_ICARE_MEDICAL_HISTORY_NAME, iprofile.getMedicalDoctorName());

				cv.put(DatabaseSQLiteHelper.COL_ICARE_MEDICAL_HISTORY_DATE, iprofile.getDate());
				cv.put(DatabaseSQLiteHelper.COL_ICARE_MEDICAL_HISTORY_IMAGE,
						iprofile.getPhotoPath());
				cv.put(DatabaseSQLiteHelper.COL_ICARE_MEDICAL_HISTORY_PURPOSE,
						iprofile.getPurpose());
				
			

				Long check = iCareMedicalHistoryProfileDatabase.insert(
						DatabaseSQLiteHelper.ICARE_MEDICAL_HISTORY, null, cv);
				iCareMedicalHistoryProfileDatabase.close();

				this.close();
				if (check < 0)
					return false;
				else
					return true;
			}

			// Updating database by id
			public boolean updateData(long profileId, MedicalHistory updateprofile) {
				this.open();
				ContentValues cvUpdate = new ContentValues();

				cvUpdate.put(DatabaseSQLiteHelper.COL_ICARE_MEDICAL_HISTORY_NAME,
						updateprofile.getMedicalDoctorName());
				cvUpdate.put(DatabaseSQLiteHelper.COL_ICARE_MEDICAL_HISTORY_DATE,
						updateprofile.getDate());
				cvUpdate.put(DatabaseSQLiteHelper.COL_ICARE_MEDICAL_HISTORY_IMAGE,
						updateprofile.getPhotoPath());
				cvUpdate.put(DatabaseSQLiteHelper.COL_ICARE_MEDICAL_HISTORY_PURPOSE,
						updateprofile.getPurpose());
				
				

				int check = iCareMedicalHistoryProfileDatabase.update(
						DatabaseSQLiteHelper.ICARE_MEDICAL_HISTORY, cvUpdate,
						DatabaseSQLiteHelper.COL_ICARE_MEDICAL_HISTORY_ID+ "=" + profileId,
						null);
				iCareMedicalHistoryProfileDatabase.close();

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
					iCareMedicalHistoryProfileDatabase
							.delete(DatabaseSQLiteHelper.ICARE_MEDICAL_HISTORY,
									DatabaseSQLiteHelper.COL_ICARE_MEDICAL_HISTORY_ID + "="
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
			public List<MedicalHistory> iCareMedicalProfilesList() {
				this.open();

				Cursor mCursor = iCareMedicalHistoryProfileDatabase.query(
						DatabaseSQLiteHelper.ICARE_MEDICAL_HISTORY, new String[] {
								DatabaseSQLiteHelper.COL_ICARE_MEDICAL_HISTORY_ID,
								DatabaseSQLiteHelper.COL_ICARE_MEDICAL_HISTORY_NAME,
								DatabaseSQLiteHelper.COL_ICARE_MEDICAL_HISTORY_DATE,
								DatabaseSQLiteHelper.COL_ICARE_MEDICAL_HISTORY_IMAGE,
								DatabaseSQLiteHelper.COL_ICARE_MEDICAL_HISTORY_PURPOSE },
						null, null, null, null, null);

				if (mCursor != null) {
					if (mCursor.moveToFirst()) {

						do {

							String mMedicalId = mCursor
									.getString(mCursor.getColumnIndex("medical_id"));
							String mMedicalName = mCursor.getString(mCursor
									.getColumnIndex("medical_name"));
							String mMedicalDate = mCursor.getString(mCursor
									.getColumnIndex("medical_date"));
							String mMedicalPurpose = mCursor.getString(mCursor
									.getColumnIndex("medical_purpose"));
							String mMedicalImage = mCursor.getString(mCursor
									.getColumnIndex("image"));
							// long mmId = Long.parseLong(mId);
							iCareDoctorProfilesList.add(new MedicalHistory(mMedicalId, mMedicalImage, mMedicalDate,
									mMedicalName, mMedicalPurpose));

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
			public MedicalHistory singleMedicalProfileData(long mActivityId) {
				this.open();
				MedicalHistory informationObject;
				
				String mMedicalId;
				String mMedicalName;
				String mMedicalDate;
				String mMedicalImage;
				String mMedicalpurpose;
				
			

				Cursor mUpdateCursor = iCareMedicalHistoryProfileDatabase.query(
						DatabaseSQLiteHelper.ICARE_MEDICAL_HISTORY, new String[] {
								DatabaseSQLiteHelper.COL_ICARE_MEDICAL_HISTORY_ID,
								DatabaseSQLiteHelper.COL_ICARE_MEDICAL_HISTORY_NAME,
								DatabaseSQLiteHelper.COL_ICARE_MEDICAL_HISTORY_DATE,
								DatabaseSQLiteHelper.COL_ICARE_MEDICAL_HISTORY_IMAGE,
								DatabaseSQLiteHelper.COL_ICARE_MEDICAL_HISTORY_PURPOSE, },
						DatabaseSQLiteHelper.COL_ICARE_MEDICAL_HISTORY_ID + "=" + mActivityId,
						null, null, null, null);

				mUpdateCursor.moveToFirst();

				mMedicalId = mUpdateCursor.getString(mUpdateCursor
						.getColumnIndex(DatabaseSQLiteHelper.COL_ICARE_MEDICAL_HISTORY_ID));
				mMedicalName = mUpdateCursor.getString(mUpdateCursor
						.getColumnIndex(DatabaseSQLiteHelper.COL_ICARE_MEDICAL_HISTORY_NAME));
				mMedicalDate = mUpdateCursor.getString(mUpdateCursor
						.getColumnIndex(DatabaseSQLiteHelper.COL_ICARE_MEDICAL_HISTORY_DATE));
				mMedicalImage = mUpdateCursor.getString(mUpdateCursor
						.getColumnIndex(DatabaseSQLiteHelper.COL_ICARE_MEDICAL_HISTORY_IMAGE));
				mMedicalpurpose = mUpdateCursor.getString(mUpdateCursor
								.getColumnIndex(DatabaseSQLiteHelper.COL_ICARE_MEDICAL_HISTORY_PURPOSE));
				mUpdateCursor.close();
				informationObject = new MedicalHistory(mMedicalId, mMedicalImage, mMedicalDate,
						mMedicalName, mMedicalpurpose);
				this.close();
				return informationObject;
			}

			public boolean isEmpty() {
				this.open();
				Cursor mCursor = iCareMedicalHistoryProfileDatabase.query(
						DatabaseSQLiteHelper.ICARE_MEDICAL_HISTORY, new String[] {
								DatabaseSQLiteHelper.COL_ICARE_MEDICAL_HISTORY_ID,
								DatabaseSQLiteHelper.COL_ICARE_MEDICAL_HISTORY_NAME,
								DatabaseSQLiteHelper.COL_ICARE_MEDICAL_HISTORY_DATE,
								DatabaseSQLiteHelper.COL_ICARE_MEDICAL_HISTORY_IMAGE,
								DatabaseSQLiteHelper.COL_ICARE_MEDICAL_HISTORY_PURPOSE },
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
