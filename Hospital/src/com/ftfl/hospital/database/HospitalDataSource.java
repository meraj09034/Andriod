package com.ftfl.hospital.database;

import java.util.ArrayList;
import java.util.List;

import com.ftfl.hospital.model.Hospital;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class HospitalDataSource {

	// Database fields
		private SQLiteDatabase hospitalDatabase;
		private DatabaseSQLiteHelper hospitalDbHelper;
		List<Hospital> hospitalList = new ArrayList<Hospital>();
		
		public HospitalDataSource(Context context) {
			hospitalDbHelper = new DatabaseSQLiteHelper(context);
		}

		/*
		 * open a method for writable database
		 */
		public void open() throws SQLException {
			hospitalDatabase = hospitalDbHelper.getWritableDatabase();
		}

		/*
		 * close database connection
		 */
		public void close() {
			hospitalDbHelper.close();
		}

		/*
		 * insert data into the database.
		 */

		public boolean insert(Hospital eInsertObject) {

			this.open();

			ContentValues cv = new ContentValues();

			cv.put(DatabaseSQLiteHelper.COL_NAME, eInsertObject.getName());
			cv.put(DatabaseSQLiteHelper.COL_ADDRESS, eInsertObject.getAddress());
			cv.put(DatabaseSQLiteHelper.COL_LATTITUDE,
					eInsertObject.getLatitude());
			cv.put(DatabaseSQLiteHelper.COL_LONGITUDE, eInsertObject.getLongitude());
			
			cv.put(DatabaseSQLiteHelper.COL_SERVICE_DESCRIPTION,
					eInsertObject.getServiceDescription());


			long check = hospitalDatabase.insert(DatabaseSQLiteHelper.TABLE_NAME_HOSPITALS, null, cv);
			hospitalDatabase.close();
		
			this.close();
			if(check <0)
				return false;
			else
				return true;
		}
		
		
		// Updating database by id
		public boolean updateData(long eId, Hospital eUpdateObject) {
			this.open();
			ContentValues cvUpdate = new ContentValues();

			cvUpdate.put(DatabaseSQLiteHelper.COL_NAME, eUpdateObject.getName());
			cvUpdate.put(DatabaseSQLiteHelper.COL_ADDRESS, eUpdateObject.getAddress());
			cvUpdate.put(DatabaseSQLiteHelper.COL_LATTITUDE,
					eUpdateObject.getLatitude());
			cvUpdate.put(DatabaseSQLiteHelper.COL_LONGITUDE, eUpdateObject.getLongitude());
			;
			cvUpdate.put(DatabaseSQLiteHelper.COL_SERVICE_DESCRIPTION,
					eUpdateObject.getServiceDescription());

			
			int check =  hospitalDatabase.update(DatabaseSQLiteHelper.TABLE_NAME_HOSPITALS, cvUpdate,
					        DatabaseSQLiteHelper.COL_ID + "=" + eId,
						null);
					hospitalDatabase.close();
		
			this.close();
			if(check ==0)
				return false;
			else
				return true;
		}

		
		/*
		 * using cursor for display data from database.
		 */
		public List<Hospital> hospitalData() {
			this.open();

			Cursor mCursor = hospitalDatabase.query(DatabaseSQLiteHelper.TABLE_NAME_HOSPITALS,
					new String[] { DatabaseSQLiteHelper.COL_ID,
					        DatabaseSQLiteHelper.COL_NAME,
					        DatabaseSQLiteHelper.COL_ADDRESS,
					        DatabaseSQLiteHelper.COL_LATTITUDE,
					        DatabaseSQLiteHelper.COL_LONGITUDE,
							DatabaseSQLiteHelper.COL_SERVICE_DESCRIPTION}, null,
					null, null, null, null);

			if (mCursor != null) {
				if (mCursor.moveToFirst()) {

					do {

						String mId = mCursor
								.getString(mCursor.getColumnIndex(DatabaseSQLiteHelper.COL_ID));
						String mName = mCursor.getString(mCursor
								.getColumnIndex(DatabaseSQLiteHelper.COL_NAME));
						String mAddress = mCursor.getString(mCursor
								.getColumnIndex(DatabaseSQLiteHelper.COL_ADDRESS));
						String mLatitude = mCursor.getString(mCursor
								.getColumnIndex(DatabaseSQLiteHelper.COL_LATTITUDE));
						String mLongitude = mCursor.getString(mCursor
								.getColumnIndex(DatabaseSQLiteHelper.COL_LONGITUDE));
						String mDescription = mCursor.getString(mCursor
								.getColumnIndex(DatabaseSQLiteHelper.COL_SERVICE_DESCRIPTION));
						// long mmId = Long.parseLong(mId);
						hospitalList.add(new Hospital(mId, mName,
								mAddress, mLatitude,
								mLongitude,	mDescription));

					} while (mCursor.moveToNext());
				}
				mCursor.close();
			}
			this.close();
			return hospitalList;
		}
		
		// delete data form database.

		public boolean deleteData(long eId) {
			this.open();
			try {
				hospitalDatabase.delete(DatabaseSQLiteHelper.TABLE_NAME_HOSPITALS,
						DatabaseSQLiteHelper.COL_ID + "=" + eId,
						null);
			} catch (Exception ex) {
				Log.e("ERROR", "data insertion problem");
				return false;
			}
			this.close();
			return true;
		}
			
		// check databased empity or not 
		
		public boolean isEmpty(){
			this.open();
			Cursor mCursor = hospitalDatabase.query(DatabaseSQLiteHelper.TABLE_NAME_HOSPITALS,
					new String[] { DatabaseSQLiteHelper.COL_ID,
							DatabaseSQLiteHelper.COL_NAME,
							DatabaseSQLiteHelper.COL_ADDRESS,
							DatabaseSQLiteHelper.COL_LATTITUDE,
							DatabaseSQLiteHelper.COL_LONGITUDE,
							DatabaseSQLiteHelper.COL_SERVICE_DESCRIPTION}, null,
					null, null, null, null);
	        if(mCursor.getCount() == 0) {
	        	this.close();
	        	return true;
	        }
	        
	        else
	        {
	        	this.close();
	        	return false;
	        }
	    }
		
		/*
		 * create a profile of ICareProfile. Here the data of the database according
		 * to the given id is set to the profile and return a profile.
		 */
		public Hospital singleHospitalData(long mActivityId ) {
			this.open();
			Hospital informationObject;
			String mId;
			String mName;
			String mAddress;
			String mLatitude;
			String mLongitude;
			
		
			String mDescription;
		

			Cursor mUpdateCursor = hospitalDatabase.query(
					DatabaseSQLiteHelper.TABLE_NAME_HOSPITALS, new String[] {
							DatabaseSQLiteHelper.COL_ID,
							DatabaseSQLiteHelper.COL_NAME,
							DatabaseSQLiteHelper.COL_ADDRESS,
							DatabaseSQLiteHelper.COL_LATTITUDE,
							DatabaseSQLiteHelper.COL_LONGITUDE,
					     	DatabaseSQLiteHelper.COL_SERVICE_DESCRIPTION, },
					     	DatabaseSQLiteHelper.COL_ID + "=" + mActivityId, null,
					null, null, null);

			mUpdateCursor.moveToFirst();

			mId = mUpdateCursor.getString(mUpdateCursor.getColumnIndex(DatabaseSQLiteHelper.COL_ID));
			mName = mUpdateCursor.getString(mUpdateCursor.getColumnIndex(DatabaseSQLiteHelper.COL_NAME));
			mAddress = mUpdateCursor.getString(mUpdateCursor.getColumnIndex(DatabaseSQLiteHelper.COL_ADDRESS));
			mLatitude = mUpdateCursor.getString(mUpdateCursor
					.getColumnIndex(DatabaseSQLiteHelper.COL_LATTITUDE));
			mLongitude = mUpdateCursor.getString(mUpdateCursor
					.getColumnIndex(DatabaseSQLiteHelper.COL_LONGITUDE));
			mDescription = mUpdateCursor.getString(mUpdateCursor
					.getColumnIndex(DatabaseSQLiteHelper.COL_SERVICE_DESCRIPTION));
			mUpdateCursor.close();
			informationObject = new Hospital(mId, mName, mAddress, mLatitude, mLongitude, mDescription);
			this.close();
			return informationObject;
		}

}
