package com.example.ftflcustomview;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
		int width = displayMetrics.widthPixels;
		int height = displayMetrics.heightPixels;
		
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.rootLayout);

		
		FTFLCircleImageView m_ImageViewOne = new FTFLCircleImageView(this , width, height);

		layout.addView(m_ImageViewOne);
		
		FTFLRectengleImageView m_ImageViewTwo = new FTFLRectengleImageView(this , width, height);

		layout.addView(m_ImageViewTwo);
		
		FTFLCircleImageView m_ImageViewThree = new FTFLCircleImageView(this , width, height);

		layout.addView(m_ImageViewThree);
		
		FTFLRectengleImageView m_ImageViewFour = new FTFLRectengleImageView(this , width, height);

		layout.addView(m_ImageViewFour);
		
		FTFLCircleImageView m_ImageViewFive = new FTFLCircleImageView(this , width, height);

		layout.addView(m_ImageViewFive);
		
		FTFLRectengleImageView m_ImageViewSix = new FTFLRectengleImageView(this , width, height);

		layout.addView(m_ImageViewSix);
		
		FTFLCircleImageView m_ImageViewSeven = new FTFLCircleImageView(this , width, height);

		layout.addView(m_ImageViewSeven);
		
		FTFLRectengleImageView m_ImageViewEight = new FTFLRectengleImageView(this , width, height);

		layout.addView(m_ImageViewEight);
		
	}
}
