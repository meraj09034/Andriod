
package com.ftfl.cameaapp.database;
import java.util.ArrayList;

import com.ftfl.cameaapp.model.LocationAndImage;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataSource {

	private SQLiteHelper dbHelper;

	public DataSource(Context context) {
		dbHelper = new SQLiteHelper(context);
	}

	// Create diet
	public long insertImageInfo(LocationAndImage imgObj) {

		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues contentValues = new ContentValues();

		contentValues.put(SQLiteHelper.COLUMN_LATITUDE,
				imgObj.getmLatitude());
		contentValues.put(SQLiteHelper.COLUMN_LONGITUDE,
				imgObj.getmLongitude());
		contentValues.put(SQLiteHelper.COLUMN_REMARKS,
				imgObj.getmRemarks());
		contentValues.put(SQLiteHelper.COLUMN_IMAGE_PATH,
				imgObj.getmPhotoPath());
		contentValues.put(SQLiteHelper.COLUMN_DATE,
				imgObj.getmDate());
		contentValues.put(SQLiteHelper.COLUMN_TIME,
				imgObj.getmTime());
		

		return db.insert(dbHelper.TABLE_NAME, null, contentValues);

	}
	
	public ArrayList<LocationAndImage> getImageList() {
			ArrayList<LocationAndImage> img_list  = new ArrayList<LocationAndImage>();
			
			SQLiteDatabase db = dbHelper.getReadableDatabase();
			Cursor result = db.rawQuery("select * from image ", null);
			if (result.moveToFirst()) {
			do {
			int id = result.getInt(0);
			String latitude = result.getString(1);
			String longitude = result.getString(2);
			String remarks = result.getString(3);
			String image_path= result.getString(4);
			String date=result.getString(5);
			String time=result.getString(6);
			
		//	LocationAndImage imgInfo = new LocationAndImage(latitude, longitude, remarks,date,time);
			//imgInfo.setmId(id);
			
			} while (result.moveToNext());
			}
			return img_list;
			}
	
	public LocationAndImage getRestaurantById(int eId) {
		LocationAndImage imgObj = null;
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor result = db.rawQuery("select * from imageandlocation where id='" + eId + "'",
		null);
		if (result.moveToFirst()) {
		do {
			int id = result.getInt(0);
			String latitude = result.getString(1);
			String longitude = result.getString(2);
			String remarks = result.getString(3);
			String image_path= result.getString(4);
			String date=result.getString(5);
			String time=result.getString(6);
			
		//	imgObj = new LocationAndImage(latitude, longitude, remarks,date,time);
		//	imgObj.setmId(id);
			
			
		} while (result.moveToNext());
		}
		return imgObj;
		}
		/* public void deleteImage (Integer id)
	   {
	      SQLiteDatabase db = dbHelper.getWritableDatabase();
	      db.delete("image", 
	      "id = ? ", 
	      new String[] { Integer.toString(id) });
	   }*/
}