package com.ftfl.mobilecontactlist;



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
	      R.drawable.sno,
	      R.drawable.syes,
	      R.drawable.sno,
	      R.drawable.syes,
	      R.drawable.sno,
	      R.drawable.syes,
	      R.drawable.sno,
	    
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
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
	    ContactList adapter = new
	    		ContactList(MainActivity.this, web, imageId, num);
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
