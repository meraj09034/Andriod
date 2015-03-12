package com.ftfl.callhistory;



import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	 ListView list;
	  String[] web = {
	    "Meraj",
	      "Nasher",
	      "Isthiqe",
	      "Shimon",
	      "Kanta",
	      "Farabi",
	      "Nipa"
	  } ;
	  
	  Integer[] imageId = {
	      R.drawable.incomingcall,
	      R.drawable.missed,
	      R.drawable.incomingcall,
	      R.drawable.outgoing,
	      R.drawable.outgoing,
	      R.drawable.missed,
	      R.drawable.missed,	    
	  };
	  
	  String[] num = {
			    "(01722666881)",
			    "(01722666882)",
			    "(01722666883)",
			    "(01722666884)",
			    "(01722666888)",
			    "(01722666889)",
			    "(01722666880)"
			  } ;
	  
	  String[] date = {
			    "1/1/15",
			    "2/12/14",
			    "3/12/14",
			    "4/12/14",
			    "10/12/14",
			    "15/12/14",
			    "24/12/14"			    
			  } ;
	  
	  String[] time = {
			    "7:00 Am",
			    "4:50 Pm",
			    "1:00 Pm",
			    "8:30 Am",
			    "9:00 Am",
			    "2:19 Pm",
			    "5:55 Pm"
			  } ;
	  
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
	    CallHistory adapter = new
	    		CallHistory(MainActivity.this, web, imageId, num, date, time);
	    list=(ListView)findViewById(R.id.list);
	        list.setAdapter(adapter);
	        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	                @Override
	                public void onItemClick(AdapterView<?> parent, View view,
	                                        int position, long id) {
	                    Toast.makeText(MainActivity.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();
	                }
	            });
	  }
}
