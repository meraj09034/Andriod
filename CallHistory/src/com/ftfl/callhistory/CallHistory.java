package com.ftfl.callhistory;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;



public class CallHistory extends ArrayAdapter<String>{
	
	private final Activity context;
	private final String[] web;
	private final Integer[] imageId;
	private final String[] num;
	private final String[] date;
	private final String[] time;
	public CallHistory(Activity context,
	String[] web, Integer[] imageId, String[] num, String[] date, String[] time) {
	super(context, R.layout.activity_list, web);
	this.context = context;
	this.web = web;
	this.imageId = imageId;
	this.num = num;
	this.date = date;
	this.time = time;
	}
	@Override
	public View getView(int position, View view, ViewGroup parent) {
	LayoutInflater inflater = context.getLayoutInflater();
	View rowView= inflater.inflate(R.layout.activity_list, null, true);
	TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
	ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
	TextView txtNum = (TextView) rowView.findViewById(R.id.phone);
	TextView txtDate = (TextView) rowView.findViewById(R.id.date);
	TextView txtTime = (TextView) rowView.findViewById(R.id.time);
	txtTitle.setText(web[position]);
	imageView.setImageResource(imageId[position]);
	txtNum.setText(num[position]);
	txtDate.setText(date[position]);
	txtTime.setText(time[position]);
	return rowView;
	}

}
