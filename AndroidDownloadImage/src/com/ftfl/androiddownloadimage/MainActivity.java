package com.ftfl.androiddownloadimage;

import java.io.InputStream;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity {

	// Set your Image URL into a string
	String URL = "http://www.androidbegin.com/wp-content/uploads/2013/07/HD-Logo.gif";
	ImageView image;
	Button button;
	ProgressDialog mProgressDialog;
	 
	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	// Get the layout from image.xml
	setContentView(R.layout.activity_main);
	 
	// Locate the ImageView in activity_main.xml
	image = (ImageView) findViewById(R.id.image);
	 
	// Locate the Button in activity_main.xml
	button = (Button) findViewById(R.id.button);
	 
	// Capture button click
	button.setOnClickListener(new OnClickListener() {
	public void onClick(View arg0) {
	 
	// Execute DownloadImage AsyncTask
	new DownloadImage().execute(URL);
	}
	});
	}
	 
	// DownloadImage AsyncTask
	private class DownloadImage extends AsyncTask<String, Void, Bitmap> {
	 
	@Override
	protected void onPreExecute() {
	super.onPreExecute();
	// Create a progressdialog
	mProgressDialog = new ProgressDialog(MainActivity.this);
	// Set progressdialog title
	mProgressDialog.setTitle("Download Image Tutorial");
	// Set progressdialog message
	mProgressDialog.setMessage("Loading...");
	mProgressDialog.setIndeterminate(false);
	// Show progressdialog
	mProgressDialog.show();
	}
	 
	@Override
	protected Bitmap doInBackground(String... URL) {
	 
	String imageURL = URL[0];
	 
	Bitmap bitmap = null;
	try {
	// Download Image from URL
	InputStream input = new java.net.URL(imageURL).openStream();
	// Decode Bitmap
	bitmap = BitmapFactory.decodeStream(input);
	} catch (Exception e) {
	e.printStackTrace();
	}
	return bitmap;
	}
	 
	@Override
	protected void onPostExecute(Bitmap result) {
	// Set the bitmap into ImageView
	image.setImageBitmap(result);
	// Close progressdialog
	mProgressDialog.dismiss();
	}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
