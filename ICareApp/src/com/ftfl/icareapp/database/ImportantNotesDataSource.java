package com.ftfl.icareapp.database;

import java.util.ArrayList;
import java.util.List;

import com.ftfl.icareapp.model.ImportentNotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class ImportantNotesDataSource {

	private SQLiteDatabase iCareImportantNotesDatabase;
	private DatabaseSQLiteHelper iCareDbHelper;
	List<ImportentNotes> iCareImportantNotesList = new ArrayList<ImportentNotes>();

	public ImportantNotesDataSource(Context context) {
		iCareDbHelper = new DatabaseSQLiteHelper(context);
	}

	/*
	 * open a method for writable database
	 */
	public void open() throws SQLException {
		iCareImportantNotesDatabase = iCareDbHelper.getWritableDatabase();
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

	public boolean insert(ImportentNotes iprofile) {

		this.open();

		ContentValues cv = new ContentValues();

		cv.put(DatabaseSQLiteHelper.COL_ICARE_IMPORTENT_NOTES_TITEL, iprofile.getNotesTitel());

		cv.put(DatabaseSQLiteHelper.COL_ICARE_IMPORTENT_NOTES_DATE, iprofile.getNotesDate());
		cv.put(DatabaseSQLiteHelper.COL_ICARE_IMPORTENT_NOTES_DESCRIPTION,
				iprofile.getNotesDescription());
	
	

		Long check = iCareImportantNotesDatabase.insert(
				DatabaseSQLiteHelper.ICARE_IMPORTANT_NOTES, null, cv);
		iCareImportantNotesDatabase.close();

		this.close();
		if (check < 0)
			return false;
		else
			return true;
	}

	// Updating database by id
	public boolean updateData(long profileId, ImportentNotes updateprofile) {
		this.open();
		ContentValues cvUpdate = new ContentValues();

		cvUpdate.put(DatabaseSQLiteHelper.COL_ICARE_IMPORTENT_NOTES_TITEL,
				updateprofile.getNotesTitel());
		cvUpdate.put(DatabaseSQLiteHelper.COL_ICARE_IMPORTENT_NOTES_DATE,
				updateprofile.getNotesDate());
		cvUpdate.put(DatabaseSQLiteHelper.COL_ICARE_IMPORTENT_NOTES_DESCRIPTION,
				updateprofile.getNotesDescription());
		
		

		int check = iCareImportantNotesDatabase.update(
				DatabaseSQLiteHelper.ICARE_IMPORTANT_NOTES, cvUpdate,
				DatabaseSQLiteHelper.COL_ICARE_IMPORTENT_NOTES_ID+ "=" + profileId,
				null);
		iCareImportantNotesDatabase.close();

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
			iCareImportantNotesDatabase
					.delete(DatabaseSQLiteHelper.ICARE_IMPORTANT_NOTES,
							DatabaseSQLiteHelper.COL_ICARE_IMPORTENT_NOTES_ID + "="
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
	public List<ImportentNotes> iCareImportantNotesList() {
		this.open();

		Cursor mCursor = iCareImportantNotesDatabase.query(
				DatabaseSQLiteHelper.ICARE_IMPORTANT_NOTES, new String[] {
						DatabaseSQLiteHelper.COL_ICARE_IMPORTENT_NOTES_ID,
						DatabaseSQLiteHelper.COL_ICARE_IMPORTENT_NOTES_TITEL,
						DatabaseSQLiteHelper.COL_ICARE_IMPORTENT_NOTES_DATE,
						DatabaseSQLiteHelper.COL_ICARE_IMPORTENT_NOTES_DESCRIPTION },
				null, null, null, null, null);

		if (mCursor != null) {
			if (mCursor.moveToFirst()) {

				do {

					String mNotesId = mCursor
							.getString(mCursor.getColumnIndex("notes_id"));
					String mNotesTitel = mCursor.getString(mCursor
							.getColumnIndex("notes_titel"));
					String mNotesDate = mCursor.getString(mCursor
							.getColumnIndex("notes_date"));
					String mNotesDescription = mCursor.getString(mCursor
							.getColumnIndex("notes_description"));
					
					// long mmId = Long.parseLong(mId);
					iCareImportantNotesList.add(new ImportentNotes(mNotesId, mNotesTitel, mNotesDate,
							mNotesDescription));

				} while (mCursor.moveToNext());
			}
			mCursor.close();
		}
		this.close();
		return iCareImportantNotesList;
	}

	/*
	 * create a profile of ICareProfile. Here the data of the database according
	 * to the given id is set to the profile and return a profile.
	 */
	public ImportentNotes singleImportentNotesData(long mActivityId) {
		this.open();
		ImportentNotes informationObject;
		
		String mNotesId;
		String mNotesTitel;
		String mNotesDate;
		String mNotesDescription;
		
		
	

		Cursor mUpdateCursor = iCareImportantNotesDatabase.query(
				DatabaseSQLiteHelper.ICARE_IMPORTANT_NOTES, new String[] {
						DatabaseSQLiteHelper.COL_ICARE_IMPORTENT_NOTES_ID,
						DatabaseSQLiteHelper.COL_ICARE_IMPORTENT_NOTES_TITEL,
						DatabaseSQLiteHelper.COL_ICARE_IMPORTENT_NOTES_DATE,
						DatabaseSQLiteHelper.COL_ICARE_IMPORTENT_NOTES_DESCRIPTION, },
				DatabaseSQLiteHelper.COL_ICARE_IMPORTENT_NOTES_ID + "=" + mActivityId,
				null, null, null, null);

		mUpdateCursor.moveToFirst();

		mNotesId = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(DatabaseSQLiteHelper.COL_ICARE_IMPORTENT_NOTES_ID));
		mNotesTitel = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(DatabaseSQLiteHelper.COL_ICARE_IMPORTENT_NOTES_TITEL));
		mNotesDate = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex(DatabaseSQLiteHelper.COL_ICARE_IMPORTENT_NOTES_DATE));
		mNotesDescription = mUpdateCursor.getString(mUpdateCursor
						.getColumnIndex(DatabaseSQLiteHelper.COL_ICARE_IMPORTENT_NOTES_DESCRIPTION));
		mUpdateCursor.close();
		informationObject = new ImportentNotes(mNotesId, mNotesTitel, mNotesDate,
				mNotesDescription);
		this.close();
		return informationObject;
	}

	public boolean isEmpty() {
		this.open();
		Cursor mCursor = iCareImportantNotesDatabase.query(
				DatabaseSQLiteHelper.ICARE_IMPORTANT_NOTES, new String[] {
						DatabaseSQLiteHelper.COL_ICARE_IMPORTENT_NOTES_ID,
						DatabaseSQLiteHelper.COL_ICARE_IMPORTENT_NOTES_TITEL,
						DatabaseSQLiteHelper.COL_ICARE_IMPORTENT_NOTES_DATE,
						DatabaseSQLiteHelper.COL_ICARE_IMPORTENT_NOTES_DESCRIPTION },
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
