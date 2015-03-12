package com.ftfl.hospital;

import com.ftfl.hospital.database.CameraDataSource;
import com.ftfl.hospital.model.Camera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class DisplayImageActivity extends Activity {
	Button btnDelete;
	ImageView imageDetail;
	int imageId;
	Bitmap theImage;
	CameraDataSource mCameraDS = null;
	Camera mImageid;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera_display);
		btnDelete = (Button) findViewById(R.id.btnDelete);
		imageDetail = (ImageView) findViewById(R.id.imageView1);
		/**
		 * getting intent data from search and previous screen
		 */
		Intent intnt = getIntent();
		theImage = (Bitmap) intnt.getParcelableExtra("imagename");
		imageId = intnt.getIntExtra("imageid", 20);
		Log.d("Image ID:****", String.valueOf(imageId));
		imageDetail.setImageBitmap(theImage);
		btnDelete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				/**
				 * create DatabaseHandler object
				 */
				mCameraDS = new CameraDataSource(DisplayImageActivity.this);
				
				/**
				 * Deleting records from database
				 */
				Log.d("Delete Image: ", "Deleting.....");
				mCameraDS.deleteCamera((mImageid));
				// /after deleting data go to main page
				Intent i = new Intent(DisplayImageActivity.this,
						CameraActivity.class);
				startActivity(i);
				finish();
			}
		});

	}
}