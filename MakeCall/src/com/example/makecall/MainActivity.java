package com.example.makecall;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	private Button button;
	private EditText etPhoneno;
	TextView tx;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button) findViewById(R.id.buttonCall);
		tx = (TextView) findViewById(R.id.textView1);
		// add button listener
		button.setOnClickListener(new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			tx.setText("01722666881");
		String phnum = tx.getText().toString();
		tx.setText("01722666881");
		Intent callIntent = new Intent(Intent.ACTION_CALL);
		callIntent.setData(Uri.parse("tel:" + phnum));
		startActivity(callIntent);
		}
		});
}}
