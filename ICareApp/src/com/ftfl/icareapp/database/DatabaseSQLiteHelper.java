package com.ftfl.icareapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class DatabaseSQLiteHelper extends SQLiteOpenHelper {
	// ICare Profile Table
	public static final String I_CARE_PROFILE = "icareprofiles";
	public static final String COL_ICARE_PROFILE_ID = "id";
	public static final String COL_ICARE_PROFILE_NAME = "name";
	public static final String COL_ICARE_PROFILE_AGE = "age";
	public static final String COL_ICARE_PROFILE_WEIGHT = "weight";
	public static final String COL_ICARE_PROFILE_HEIGHT = "height";
	public static final String COL_ICARE_PROFILE_GENDER = "gender";
	public static final String COL_ICARE_PROFILE_BLOOD_GROUP = "blood_group";;

	// ICare Activity Chart Table
	public static final String ICARE_DIET_CHART = "icaredietchart";
	public static final String COL_ICARE_DIET_ID = "diet_id";
	public static final String COL_ICARE_DIET_DATE = "date";
	public static final String COL_ICARE_DIET_TIME = "time";
	public static final String COL_ICARE_DIET_FOOD_MENU = "food_menu";
	public static final String COL_ICARE_DIET_EVENT_NAME = "event_name";
	public static final String COL_ICARE_DIET_ALARM = "set_alarm";
	
	// ICare Doctor Profile Table
		public static final String I_CARE_DOCTOR_PROFILE = "icaredoctorprofiles";
		public static final String COL_ICARE_DOCTOR_PROFILE_ID = "id";
		public static final String COL_ICARE_DOCTOR_PROFILE_NAME = "doctor_name";
		public static final String COL_ICARE_DOCTOR_PROFILE_SPECIALITY = "speciality";
		public static final String COL_ICARE_DOCTOR_PROFILE_PHONE = "phone";
		public static final String COL_ICARE_DOCTOR_PROFILE_EMAIL = "email";
		public static final String COL_ICARE_DOCTOR_PROFILE_CHAMBER = "chamber";
	
		// ICare Vaccineation Chart Table
		public static final String ICARE_VACCINE_CHART = "icarevacinationchart";
		public static final String COL_ICARE_VACCINE_ID = "vaccine_id";
		public static final String COL_ICARE_VACCINE_DATE = "vaccine_date";
		public static final String COL_ICARE_VACCINE_TIME = "vaccine_time";
		public static final String COL_ICARE_VACCINE_DESCRIPTION = "description";
		public static final String COL_ICARE_VACCINE_EVENT_NAME = "vaccine__name";
		public static final String COL_ICARE_VACCINE__ALARM = "vaccine_set_alarm";
		
		// ICare Vaccineation Chart Table
				public static final String ICARE_MEDICAL_HISTORY = "icaremedicalhistory";
				public static final String COL_ICARE_MEDICAL_HISTORY_ID = "medical_id";
				public static final String COL_ICARE_MEDICAL_HISTORY_DATE = "medical_date";
				public static final String COL_ICARE_MEDICAL_HISTORY_NAME = "medical_name";
				public static final String COL_ICARE_MEDICAL_HISTORY_PURPOSE = "medical_purpose";
				public static final String COL_ICARE_MEDICAL_HISTORY_IMAGE = "image";
				
				// ICare Vaccineation Chart Table
				public static final String ICARE_IMPORTANT_NOTES = "icareimportentnotes";
				public static final String COL_ICARE_IMPORTENT_NOTES_ID = "notes_id";
				public static final String COL_ICARE_IMPORTENT_NOTES_DATE = "notes_date";
				public static final String COL_ICARE_IMPORTENT_NOTES_TITEL = "notes_titel";
				public static final String COL_ICARE_IMPORTENT_NOTES_DESCRIPTION = "notes_description";
	private static final String DATABASE_NAME = "iCareDatabase.db";
	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement
	private static final String DATABASE_CREATE_PROFILE = "create table "
			+ I_CARE_PROFILE + "( " + COL_ICARE_PROFILE_ID
			+ " integer primary key autoincrement, " + " "
			+ COL_ICARE_PROFILE_NAME + " text not null," + " "
			+ COL_ICARE_PROFILE_AGE + " text not null," + " "
			+ COL_ICARE_PROFILE_WEIGHT + " text not null," + " "
			+ COL_ICARE_PROFILE_HEIGHT + " text not null," + " "
			+ COL_ICARE_PROFILE_GENDER + " text not null," + " "
			+ COL_ICARE_PROFILE_BLOOD_GROUP + " text not null);";

	private static final String DATABASE_CREATE_DACTIVITY = "create table "
			+ ICARE_DIET_CHART + "(" + COL_ICARE_DIET_ID
			+ " integer primary key autoincrement, "
			+ COL_ICARE_DIET_DATE + " text not null,"
			+ COL_ICARE_DIET_TIME + " text not null,"
			+ COL_ICARE_DIET_FOOD_MENU + " text not null,"
			+ COL_ICARE_DIET_EVENT_NAME + " text not null,"
			+ COL_ICARE_DIET_ALARM + " text not null);";
	
	// Database creation sql statement
		private static final String DATABASE_CREATE_DOCTOR_PROFILE = "create table "
				+ I_CARE_DOCTOR_PROFILE + "( " + COL_ICARE_DOCTOR_PROFILE_ID
				+ " integer primary key autoincrement, " + " "
				+ COL_ICARE_DOCTOR_PROFILE_NAME + " text not null," + " "
				+ COL_ICARE_DOCTOR_PROFILE_SPECIALITY + " text not null," + " "
				+ COL_ICARE_DOCTOR_PROFILE_PHONE + " text not null," + " "
				+ COL_ICARE_DOCTOR_PROFILE_EMAIL + " text not null," + " "
				+ COL_ICARE_DOCTOR_PROFILE_CHAMBER + " text not null);";
		
		private static final String DATABASE_CREATE_VACCINE = "create table "
				+ ICARE_VACCINE_CHART + "(" + COL_ICARE_VACCINE_ID
				+ " integer primary key autoincrement, "
				+ COL_ICARE_VACCINE_DATE + " text not null,"
				+ COL_ICARE_VACCINE_TIME + " text not null,"
				+ COL_ICARE_VACCINE_DESCRIPTION + " text not null,"
				+ COL_ICARE_VACCINE_EVENT_NAME + " text not null,"
				+ COL_ICARE_VACCINE__ALARM + " text not null);";
		
		private static final String DATABASE_CREATE_MEDICAL_HISTORY = "create table "
				+ ICARE_MEDICAL_HISTORY + "(" + COL_ICARE_MEDICAL_HISTORY_ID
				+ " integer primary key autoincrement, "
				+ COL_ICARE_MEDICAL_HISTORY_DATE + " text not null,"
				+ COL_ICARE_MEDICAL_HISTORY_NAME + " text not null,"
				+ COL_ICARE_MEDICAL_HISTORY_PURPOSE + " text not null,"
				+ COL_ICARE_MEDICAL_HISTORY_IMAGE + " text not null);";
		
		private static final String DATABASE_CREATE_IMPORTANT_NOTES = "create table "
				+ ICARE_IMPORTANT_NOTES + "(" + COL_ICARE_IMPORTENT_NOTES_ID
				+ " integer primary key autoincrement, "
				+ COL_ICARE_IMPORTENT_NOTES_DATE + " text not null,"
				+ COL_ICARE_IMPORTENT_NOTES_TITEL + " text not null,"
				+ COL_ICARE_IMPORTENT_NOTES_DESCRIPTION + " text not null);";
		public DatabaseSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE_PROFILE);
		database.execSQL(DATABASE_CREATE_DACTIVITY);
		database.execSQL(DATABASE_CREATE_DOCTOR_PROFILE);
		database.execSQL(DATABASE_CREATE_VACCINE);
		database.execSQL(DATABASE_CREATE_MEDICAL_HISTORY);
		database.execSQL(DATABASE_CREATE_IMPORTANT_NOTES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(DatabaseSQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + I_CARE_PROFILE);
		db.execSQL("DROP TABLE IF EXISTS " + ICARE_DIET_CHART);
		db.execSQL("DROP TABLE IF EXISTS " + I_CARE_DOCTOR_PROFILE);
		db.execSQL("DROP TABLE IF EXISTS " + ICARE_VACCINE_CHART);
		db.execSQL("DROP TABLE IF EXISTS " + ICARE_MEDICAL_HISTORY);
		db.execSQL("DROP TABLE IF EXISTS " + ICARE_IMPORTANT_NOTES);
		onCreate(db);
	}



}
