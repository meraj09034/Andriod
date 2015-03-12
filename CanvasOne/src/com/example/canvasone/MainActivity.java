package com.example.canvasone;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.rootLayout);
	//	RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(150,150);
		
		FTFLImageView m_ImageViewOne = new FTFLImageView(this);
	//	m_ImageViewOne.setLayoutParams(layoutParams);
		layout.addView(m_ImageViewOne);
		
		FTFLImageView m_ImageViewTwo = new FTFLImageView(this);
		
		//m_ImageViewTwo.setImageResource(R.drawable.imgapple);

		//layout.addView(m_ImageViewTwo);
		
		FTFLImageView m_ImageViewThree = new FTFLImageView(this);
		//m_ImageViewThree.setImageResource(R.drawable.imgice);
		//layout.addView(m_ImageViewThree);
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
