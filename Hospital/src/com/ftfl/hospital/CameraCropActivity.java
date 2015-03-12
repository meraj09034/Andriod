package com.ftfl.hospital;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class CameraCropActivity extends Activity {

	ImageView imVCature_pic;
	 Button btnCapture;
	 
	 @Override
	  protected void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_camera_control);
	  
	  Context context = this;
	  PackageManager packageManager = context.getPackageManager();

		// if device support camera?
	  if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
			//yes
			initializeControls();
		}else{
			//no
			Log.i("camera", "This device has no camera!");
		}

	    initializeControls();
	 }
	 
	 private void initializeControls() {
		 
     try{
   	 
	  imVCature_pic=(ImageView)findViewById(R.id.imVCature_pic);
	  btnCapture=(Button)findViewById(R.id.btnCapture);
	  btnCapture.setOnClickListener(new OnClickListener() {
	 
	   @Override
	   public void onClick(View v) {
	    /* create an instance of intent
	     * pass action android.media.action.IMAGE_CAPTURE
	     * as argument to launch camera
	     */
	    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
	    /*create instance of File with name img.jpg*/
	    File file = new File(Environment.getExternalStorageDirectory()+File.separator + "img.jpg");
	    /*put uri as extra in intent object*/
	    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
	    /*start activity for result pass intent as argument and request code */
	    startActivityForResult(intent, 1);
	   }
	  });}
		 catch(ActivityNotFoundException anfe){
			    //display an error message
			    String errorMessage = "Whoops - your device doesn't support capturing images!";
			    Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
			    toast.show();
			}
	 
	 }
	 
	 @Override
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	 super.onActivityResult(requestCode, resultCode, data);
	 
	  if(requestCode==1){
		  
	   //create instance of File with same name we created before to get image from storage
		  
	   File file = new File(Environment.getExternalStorageDirectory()+File.separator + "img.jpg");
	   
	   //Crop the captured image using an other intent
	   
	   try {
		   
	    /*the user's device may not support cropping*/
		   
	    cropCapturedImage(Uri.fromFile(file));
	   }
	   catch(ActivityNotFoundException aNFE){
		   
	    //display an error message if user device doesn't support
		   
	    String errorMessage = "Sorry - your device doesn't support the crop action!";
	    Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
	    toast.show();
	   }
	 }
	  
	  else if(requestCode==2){
	   //Create an instance of bundle and get the returned data
		  if(data!=null){
	   Bundle extras = data.getExtras();
	   //get the cropped bitmap from extras
	   Bitmap thePic = extras.getParcelable("data");
	   //set image bitmap to image view
	   imVCature_pic.setImageBitmap(thePic);
		  }
	  }
	}
	 
	 //create helping method cropCapturedImage(Uri picUri)
	 
	 public void cropCapturedImage(Uri picUri){
		 
		 
		
	 
	  //call the standard crop action intent
		 
	  Intent cropIntent = new Intent("com.android.camera.action.CROP");
	  
	  //indicate image type and Uri of image
	  
	  cropIntent.setDataAndType(picUri, "image/*");
	  
	  //set crop properties
	  cropIntent.putExtra("crop", "true");
	  //indicate aspect of desired crop
	  cropIntent.putExtra("aspectX", 1);
	  cropIntent.putExtra("aspectY", 1);
	  //indicate output X and Y
	  cropIntent.putExtra("outputX", 256);
	  cropIntent.putExtra("outputY", 256);
	  //retrieve data on return
	  cropIntent.putExtra("return-data", true);
	  //start the activity - we handle returning in onActivityResult
	  startActivityForResult(cropIntent, 2);

	 
	 }

}
