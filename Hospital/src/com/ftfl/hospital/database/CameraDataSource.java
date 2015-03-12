package com.ftfl.hospital.database;



import java.util.ArrayList;
import java.util.List;

import com.ftfl.hospital.model.Camera;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class CameraDataSource {

	// Database fields
				private SQLiteDatabase cameraDatabase;
				private DatabaseSQLiteHelper cameraDbHelper;
				//List<Hospital> hospitalList = new ArrayList<Hospital>();
				
				public CameraDataSource(Context context) {
					cameraDbHelper = new DatabaseSQLiteHelper(context);
				}

				/*
				 * open a method for writable database
				 */
				public void open() throws SQLException {
					cameraDatabase = cameraDbHelper.getWritableDatabase();
				}

				/*
				 * close database connection
				 */
				public void close() {
					cameraDbHelper.close();
				}
				
				public void insert(Camera eInsertObject) {

					this.open();

					ContentValues cv = new ContentValues();

					cv.put(DatabaseSQLiteHelper.KEY_CAMERA_NAME, eInsertObject.getName());
					cv.put(DatabaseSQLiteHelper.KEY_CAMERA_IMAGE, eInsertObject.getImage());
					

					cameraDatabase.insert(DatabaseSQLiteHelper.TABLE_NAME_CAMERA, null, cv);
					cameraDatabase.close();
				
					this.close();
					
				}
				
				// Getting single contact
				Camera getCamera(int id) {
					this.open();
					Cursor mCursor = cameraDatabase.query(DatabaseSQLiteHelper.TABLE_NAME_CAMERA,
							new String[] { DatabaseSQLiteHelper.KEY_CAMERA_ID,
							        DatabaseSQLiteHelper.KEY_CAMERA_NAME,
							        DatabaseSQLiteHelper.KEY_CAMERA_IMAGE
							        }, DatabaseSQLiteHelper.KEY_CAMERA_ID + "=?",
									new String[] { String.valueOf(id) }, null, null, null, null);
					if (mCursor != null)
						mCursor.moveToFirst();

					Camera camera = new Camera(Integer.parseInt(mCursor.getString(0)),
							mCursor.getString(1), mCursor.getBlob(1));

					// return contact
					return camera;

				}

				// Getting All Contacts
				public List<Camera> getAllCameras() {
					List<Camera> contactList = new ArrayList<Camera>();
					// Select All Query
					String selectQuery = "SELECT  * FROM " + DatabaseSQLiteHelper.TABLE_NAME_CAMERA;

					this.open();
					Cursor cursor = cameraDatabase.rawQuery(selectQuery, null);
					// looping through all rows and adding to list
					if (cursor.moveToFirst()) {
						do {
							Camera camera = new Camera();
							camera.setIid(Integer.parseInt(cursor.getString(0)));
							camera.setName(cursor.getString(1));
							camera.setImage(cursor.getBlob(2));
							// Adding contact to list
							contactList.add(camera);
						} while (cursor.moveToNext());
					}
					// close inserting data from database
					cameraDatabase.close();
					// return contact list
					return contactList;

				}

				// Updating single contact
				public int updateCamera(Camera camera) {
					this.open();

					ContentValues values = new ContentValues();
					values.put(DatabaseSQLiteHelper.KEY_CAMERA_NAME, camera.getName());
					values.put(DatabaseSQLiteHelper.KEY_CAMERA_IMAGE, camera.getImage());

					// updating row
					return cameraDatabase.update(DatabaseSQLiteHelper.TABLE_NAME_CAMERA, values, DatabaseSQLiteHelper.KEY_CAMERA_ID + " = ?",
							new String[] { String.valueOf(camera.getIid()) });

				}

				// Deleting single contact
				public void deleteCamera(Camera camera) {
					this.open();
					cameraDatabase.delete(DatabaseSQLiteHelper.TABLE_NAME_CAMERA, DatabaseSQLiteHelper.KEY_CAMERA_ID + " = ?",
							new String[] { String.valueOf(camera.getIid()) });
					cameraDatabase.close();
				}

				// Getting contacts Count
				public int getCamerasCount() {
					String countQuery = "SELECT  * FROM " + DatabaseSQLiteHelper.TABLE_NAME_CAMERA;
					this.open();
					Cursor cursor = cameraDatabase.rawQuery(countQuery, null);
					cursor.close();

					// return count
					return cursor.getCount();
				}
	

}
