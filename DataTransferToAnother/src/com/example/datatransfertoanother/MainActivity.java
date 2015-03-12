package com.example.datatransfertoanother;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class MainActivity extends ActionBarActivity {

	public EditText name, phone_no, age;
	public CheckBox married;

	public String fullname, phoneNumber, ageBirth;
	public boolean isMarried = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// initialize edit texts
			        initializeEditTexts();
		 
			        Button send = (Button) findViewById(R.id.send);
			        send.setOnClickListener(new OnClickListener() {
									
			        
			            public void onClick(View arg0) {
			 
			                // Getting data from the form
			                getDataFromForm();
			 
			                if (name.length() == 0 || phone_no.length() == 0 || age.length() == 0) {
			                    //show toast when not correctly completed
			                    Toast.makeText(getApplicationContext(), "Complete the form correctly", Toast.LENGTH_SHORT).show();
			                } else {
			                    // Converting phoneNumber to long type
			                    long phone = Long.parseLong(phoneNumber);
			                    // Converting ageBirth to double type
			                    double ageDouble = Long.parseLong(ageBirth);
			                     
			                    // Creating Bundle object
			                    Bundle b = new Bundle();
			                     
			                    // Storing data into bundle
			                    b.putString("fullname", fullname);
			                    b.putLong("phoneNumber", phone);
			                    b.putDouble("age", ageDouble);
			                    b.putBoolean("married", isMarried);
		 
			                    // Creating Intent object
			                    
			                    Intent in = new Intent(MainActivity.this, SecoundActivity.class);
			 
			                    // Storing bundle object into intent
			                    in.putExtras(b);
			                    startActivity(in);
			                }
			            }
			        });
			        
		
		
	}

	public void initializeEditTexts() {
			        name = (EditText) findViewById(R.id.name);
		       phone_no = (EditText) findViewById(R.id.phone_no);
		        age = (EditText) findViewById(R.id.age);
			        married = (CheckBox) findViewById(R.id.married);
			    }
	
	
	
	public void getDataFromForm() {
			        fullname = name.getText().toString();
		        phoneNumber = phone_no.getText().toString();
			        ageBirth = age.getText().toString();
			        isMarried = married.isChecked();
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
