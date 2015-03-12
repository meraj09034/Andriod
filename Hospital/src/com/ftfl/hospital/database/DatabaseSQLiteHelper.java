package com.ftfl.hospital.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseSQLiteHelper extends SQLiteOpenHelper {
	// ICare Profile Table
	public static final String TABLE_NAME_HOSPITALS = "hospitals";
	public static final String COL_ID = "id";
	public static final String COL_NAME = "name";
	public static final String COL_ADDRESS = "address";
	public static final String COL_LATTITUDE = "latitude";
	public static final String COL_LONGITUDE = "longitude";
	public static final String COL_SERVICE_DESCRIPTION = "service_description";

	// Camera table name
	public  static final String TABLE_NAME_CAMERA = "camera";
		// Camera Table Columns names
	public static final String KEY_CAMERA_ID = "id";
	public static final String KEY_CAMERA_NAME = "name";
	public 	static final String KEY_CAMERA_IMAGE = "image";

	private static final String DATABASE_NAME = "hospital.db";
	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement
	private static final String DATABASE_CREATE_HOSPITAL_PROFILE = "create table "
			+ TABLE_NAME_HOSPITALS + "( " + COL_ID
			+ " integer primary key autoincrement, " + " "
			+ COL_NAME + " text not null," + " "
			+ COL_ADDRESS + " text not null," + " "
			+ COL_LATTITUDE + " text not null," + " "
			+ COL_LONGITUDE + " text not null," + " "
			+ COL_SERVICE_DESCRIPTION + " text not null);";


	// Creating Tables
	
	// Database creation sql statement
	  public	static final String DATABASE_CREATE_CAMERA_TABLE= "create table "
				+ TABLE_NAME_CAMERA + "( " + KEY_CAMERA_ID
				+ " integer primary key autoincrement, " + " "
				+ KEY_CAMERA_NAME + " text not null," + " "
				+ KEY_CAMERA_IMAGE + " BLOB);";
	
			
		
	public DatabaseSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE_HOSPITAL_PROFILE);
		database.execSQL(DATABASE_CREATE_CAMERA_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(DatabaseSQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_HOSPITALS);
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_CREATE_CAMERA_TABLE);
		onCreate(db);
	}

}
