package com.ftfl.mymettingplace.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseSQLiteHelper extends SQLiteOpenHelper {

	  
	// Database Name
	public static final String DATABASE_NAME = "myvisitingplaces.db";
	private static final int DATABASE_VERSION = 3;

	// Table HOSPITAL
	public static final String TABLE_PLACES = "visitingplaces";
	public static final String COL_PLACE_ID = "id";
	public static final String COL_PLACE_DATE = "date";
	public static final String COL_PLACE_TIME = "time";
	public static final String COL_PLACE_LATITUDE = "latitude";
	public static final String COL_PLACE_LONGITUDE = "longitude";
	public static final String COL_PLACE_DESCRIPTION = "description";
	public static final String COL_PLACE_IMAGE = "image";

	// Database creation sql statement
	private static final String TABLE_CREATE_HOSPITAL = "create table "
			+ TABLE_PLACES + "(" + COL_PLACE_ID
			+ " integer primary key autoincrement, " + COL_PLACE_DATE
			+ " text not null," + COL_PLACE_TIME + " text not null,"
			+ COL_PLACE_LATITUDE + " text not null,"
			+ COL_PLACE_LONGITUDE + " text not null," + COL_PLACE_DESCRIPTION
			+ " text not null ," + COL_PLACE_IMAGE
			+ " BLOB not null);";

	public DatabaseSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {

		database.execSQL(TABLE_CREATE_HOSPITAL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(DatabaseSQLiteHelper.class.getName(), "Upgrading database from version "
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");

		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLACES);
		onCreate(db);
	}

}
