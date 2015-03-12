package com.ftfl.hospital;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.ftfl.hospital.adapter.Customadapter;
import com.ftfl.hospital.database.CameraDataSource;
import com.ftfl.hospital.model.Camera;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class CameraActivity extends ActionBarActivity {

	Button addImage;
	ArrayList<Camera> imageArry = new ArrayList<Camera>();
	Customadapter imageAdapter;
	private static final int CAMERA_REQUEST = 1;
	private static final int PICK_FROM_GALLERY = 2;
	ListView dataList;
	byte[] imageName;
	int imageId;
	Bitmap theImage;
	
	CameraDataSource mcameraDs;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera_main);
		dataList = (ListView) findViewById(R.id.list);
		/**
		 * create DatabaseHandler object
		 */
		mcameraDs = new CameraDataSource(this);
		/**
		 * Reading and getting all records from database
		 */
		List<Camera> cameras = mcameraDs.getAllCameras();
		for (Camera cn : cameras) {
			String log = "ID:" + cn.getIid() + " Name: " + cn.getName()
					+ " ,Image: " + cn.getImage();

			// Writing Contacts to log
			Log.d("Result: ", log);
			// add contacts data in arrayList
			imageArry.add(cn);

		}
		/**
		 * Set Data base Item into listview
		 */
		imageAdapter = new Customadapter(this, R.layout.activity_camera_list,
				imageArry);
		dataList.setAdapter(imageAdapter);
		/**
		 * go to next activity for detail image
		 */
		dataList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					final int position, long id) {
				imageName = imageArry.get(position).getImage();
				imageId = imageArry.get(position).getIid();

				Log.d("Before Send:****", imageName + "-" + imageId);
				// convert byte to bitmap
				ByteArrayInputStream imageStream = new ByteArrayInputStream(
						imageName);
				theImage = BitmapFactory.decodeStream(imageStream);
				Intent intent = new Intent(CameraActivity.this,
						DisplayImageActivity.class);
				intent.putExtra("imagename", theImage);
				intent.putExtra("imageid", imageId);
				startActivity(intent);

			}
		});
		/**
		 * open dialog for choose camera/gallery
		 */

		final String[] option = new String[] { "Take from Camera",
				"Select from Gallery" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.select_dialog_item, option);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle("Select Option");
		builder.setAdapter(adapter, new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Log.e("Selected Item", String.valueOf(which));
				if (which == 0) {
					callCamera();
				}
				if (which == 1) {
					callGallery();
				}

			}
		});
		final AlertDialog dialog = builder.create();

		addImage = (Button) findViewById(R.id.btnAdd);

		addImage.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				dialog.show();
			}
		});

	}

	/**
	 * On activity result
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != RESULT_OK)
			return;

		switch (requestCode) {
		case CAMERA_REQUEST:

			Bundle extras = data.getExtras();

			if (extras != null) {
				Bitmap yourImage = extras.getParcelable("data");
				// convert bitmap to byte
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				yourImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
				byte imageInByte[] = stream.toByteArray();
				Log.e("output before conversion", imageInByte.toString());
				// Inserting Contacts
				Log.d("Insert: ", "Inserting ..");
				mcameraDs.insert(new Camera("Android", imageInByte));
				Intent i = new Intent(CameraActivity.this,
						CameraActivity.class);
				startActivity(i);
				finish();

			}
			break;
		case PICK_FROM_GALLERY:
			Bundle extras2 = data.getExtras();

			if (extras2 != null) {
				Bitmap yourImage = extras2.getParcelable("data");
				// convert bitmap to byte
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				yourImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
				byte imageInByte[] = stream.toByteArray();
				Log.e("output before conversion", imageInByte.toString());
				// Inserting Contacts
				Log.d("Insert: ", "Inserting ..");
				mcameraDs.insert(new Camera("Android", imageInByte));
				Intent i = new Intent(CameraActivity.this,
						CameraActivity.class);
				startActivity(i);
				finish();
			}

			break;
		}
	}

	/**
	 * open camera method
	 */
	public void callCamera() {
		Intent cameraIntent = new Intent(
				android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		cameraIntent.putExtra("crop", "true");
		cameraIntent.putExtra("aspectX", 0);
		cameraIntent.putExtra("aspectY", 0);
		cameraIntent.putExtra("outputX", 200);
		cameraIntent.putExtra("outputY", 150);
		startActivityForResult(cameraIntent, CAMERA_REQUEST);

	}

	/**
	 * open gallery method
	 */

	public void callGallery() {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 0);
		intent.putExtra("aspectY", 0);
		intent.putExtra("outputX", 200);
		intent.putExtra("outputY", 150);
		intent.putExtra("return-data", true);
		startActivityForResult(
				Intent.createChooser(intent, "Complete action using"),
				PICK_FROM_GALLERY);

	}

}
