package com.ftfl.mymettingplace.helper;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.ftfl.mymettingplace.model.PlaceInfo;

public class MyMettingPlacesDataSource {

	// Database fields
	private SQLiteDatabase mPlaceDatabase;
	private DatabaseSQLiteHelper mPlacesDbHelper;

	PlaceInfo mSinglePlace;
	List<PlaceInfo> mPlaces = new ArrayList<PlaceInfo>();

	public MyMettingPlacesDataSource(Context context) {
		mPlacesDbHelper = new DatabaseSQLiteHelper(context);
	}

	/*
	 * open a method for writable database
	 */
	public void open() throws SQLException {
		mPlaceDatabase = mPlacesDbHelper.getWritableDatabase();
	}

	/*
	 * close database connection
	 */
	public void close() {
		mPlacesDbHelper.close();
	}

	/*
	 * insert data into the database.
	 */

	public boolean insert(PlaceInfo place) {

		this.open();

		ContentValues cv = new ContentValues();

		cv.put(DatabaseSQLiteHelper.COL_PLACE_DATE, place.getPlaceDate());
		cv.put(DatabaseSQLiteHelper.COL_PLACE_TIME, place.getPlaceTime());
		cv.put(DatabaseSQLiteHelper.COL_PLACE_LATITUDE, place.getPlaceLatitude());
		cv.put(DatabaseSQLiteHelper.COL_PLACE_LONGITUDE, place.getPlaceLongitude());
		cv.put(DatabaseSQLiteHelper.COL_PLACE_DESCRIPTION, place.getPlaceDescription());
		cv.put(DatabaseSQLiteHelper.COL_PLACE_IMAGE, place.getPlaceImage());

		Long check = mPlaceDatabase.insert(DatabaseSQLiteHelper.TABLE_PLACES, null, cv);
		mPlaceDatabase.close();

		this.close();

		if (check < 0)
			return false;
		else
			return true;
	}

	// Updating database by id
	public boolean updateData(long eActivityId, PlaceInfo place) {
		this.open();
		ContentValues cvUpdate = new ContentValues();

		cvUpdate.put(DatabaseSQLiteHelper.COL_PLACE_DATE, place.getPlaceDate());
		cvUpdate.put(DatabaseSQLiteHelper.COL_PLACE_TIME, place.getPlaceTime());
		cvUpdate.put(DatabaseSQLiteHelper.COL_PLACE_LATITUDE, place.getPlaceLatitude());
		cvUpdate.put(DatabaseSQLiteHelper.COL_PLACE_LONGITUDE,
				place.getPlaceLongitude());
		cvUpdate.put(DatabaseSQLiteHelper.COL_PLACE_DESCRIPTION,
				place.getPlaceDescription());
		cvUpdate.put(DatabaseSQLiteHelper.COL_PLACE_IMAGE, place.getPlaceImage());

		int check = mPlaceDatabase.update(DatabaseSQLiteHelper.TABLE_PLACES, cvUpdate,
				DatabaseSQLiteHelper.COL_PLACE_ID + "=" + eActivityId, null);
		mPlaceDatabase.close();

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
			mPlaceDatabase.delete(DatabaseSQLiteHelper.TABLE_PLACES,
					DatabaseSQLiteHelper.COL_PLACE_ID + "=" + eActivityId, null);
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
	public PlaceInfo singlePlace(String id) {
		this.open();

		Cursor mCursor = mPlaceDatabase.query(DatabaseSQLiteHelper.TABLE_PLACES,
				new String[] { DatabaseSQLiteHelper.COL_PLACE_ID,
						DatabaseSQLiteHelper.COL_PLACE_DATE,
						DatabaseSQLiteHelper.COL_PLACE_TIME,
						DatabaseSQLiteHelper.COL_PLACE_LATITUDE,
						DatabaseSQLiteHelper.COL_PLACE_LONGITUDE,
						DatabaseSQLiteHelper.COL_PLACE_DESCRIPTION,
						DatabaseSQLiteHelper.COL_PLACE_IMAGE,

				}, DatabaseSQLiteHelper.COL_PLACE_ID + "= '" + id + "' ", null, null,
				null, null);

		if (mCursor != null) {
			if (mCursor.moveToFirst()) {

				do {

					String mActivityId = mCursor.getString(mCursor
							.getColumnIndex("id"));
					String mActivityDate = mCursor.getString(mCursor
							.getColumnIndex("date"));
					String mActivityTime = mCursor.getString(mCursor
							.getColumnIndex("time"));
					String mActivityLatitude = mCursor.getString(mCursor
							.getColumnIndex("latitude"));
					String mActivityLongitude = mCursor.getString(mCursor
							.getColumnIndex("longitude"));
					String mActivityDescription = mCursor.getString(mCursor
							.getColumnIndex("description"));
					byte[] mImage = mCursor.getBlob(mCursor
							.getColumnIndex("image"));

					mSinglePlace = new PlaceInfo(mActivityId,
							mActivityDate, mActivityTime, mActivityLatitude,
							mActivityLongitude, mActivityDescription, mImage);

				} while (mCursor.moveToNext());
			}
			mCursor.close();
		}
		this.close();
		return mSinglePlace;
	}

	/*
	 * create a Diet chart of ICareProfile. Here the data of the database
	 * according to the given id is set to the Diet chart and return a Diet
	 * chart object.
	 */

	public PlaceInfo updateActivityData(long eActivityId) {
		this.open();
		PlaceInfo UpdateActivity;

		Cursor mUpdateCursor = mPlaceDatabase.query(DatabaseSQLiteHelper.TABLE_PLACES,
				new String[] { DatabaseSQLiteHelper.COL_PLACE_ID,
						DatabaseSQLiteHelper.COL_PLACE_DATE,
						DatabaseSQLiteHelper.COL_PLACE_TIME,
						DatabaseSQLiteHelper.COL_PLACE_LATITUDE,
						DatabaseSQLiteHelper.COL_PLACE_LONGITUDE,
						DatabaseSQLiteHelper.COL_PLACE_DESCRIPTION,
						DatabaseSQLiteHelper.COL_PLACE_IMAGE,

				}, DatabaseSQLiteHelper.COL_PLACE_ID + "= '" + eActivityId + "' ",
				null, null, null, null);

		mUpdateCursor.moveToFirst();

		String mPlaceId = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex("id"));
		String mPlaceDate = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex("date"));
		String mPlaceTime = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex("time"));
		String mActivityLatitude = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex("latitude"));
		String mActivityLongitude = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex("longitude"));
		String mActivityDescription = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex("description"));
		byte[] mImage = mUpdateCursor.getBlob(mUpdateCursor
				.getColumnIndex("image"));

		mUpdateCursor.close();
		UpdateActivity = new PlaceInfo(mPlaceId, mPlaceDate,
				mPlaceTime, mActivityLatitude, mActivityLongitude,
				mActivityDescription, mImage);

		this.close();
		return UpdateActivity;
	}

	/*
	 * using cursor for display data from database.
	 */
	public List<PlaceInfo> allPlaces() {
		this.open();

		Cursor mCursor = mPlaceDatabase.query(DatabaseSQLiteHelper.TABLE_PLACES,
				new String[] { DatabaseSQLiteHelper.COL_PLACE_ID,
						DatabaseSQLiteHelper.COL_PLACE_DATE,
						DatabaseSQLiteHelper.COL_PLACE_TIME,
						DatabaseSQLiteHelper.COL_PLACE_LATITUDE,
						DatabaseSQLiteHelper.COL_PLACE_LONGITUDE,
						DatabaseSQLiteHelper.COL_PLACE_DESCRIPTION,
						DatabaseSQLiteHelper.COL_PLACE_IMAGE,

				}, null, null, null, null, null);

		if (mCursor != null) {
			if (mCursor.moveToFirst()) {

				do {

					String mPlaceId = mCursor.getString(mCursor
							.getColumnIndex("id"));
					String mPlaceDate = mCursor.getString(mCursor
							.getColumnIndex("date"));
					String mPlaceTime = mCursor.getString(mCursor
							.getColumnIndex("time"));
					String mPlaceLatitude = mCursor.getString(mCursor
							.getColumnIndex("latitude"));
					String mPlaceLongitude = mCursor.getString(mCursor
							.getColumnIndex("longitude"));
					String mPlaceDescription = mCursor.getString(mCursor
							.getColumnIndex("description"));
					byte[] mImage = mCursor.getBlob(mCursor
							.getColumnIndex("image"));

					mPlaces.add(new PlaceInfo(mPlaceId, mPlaceDate,
							mPlaceTime, mPlaceLatitude,
							mPlaceLongitude, mPlaceDescription, mImage));

				} while (mCursor.moveToNext());
			}
			mCursor.close();
		}
		this.close();
		return mPlaces;
	}

}
