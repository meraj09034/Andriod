package com.ftfl.mobilecontactlist;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;



public class ContactList extends ArrayAdapter<String>{

	private final Activity context;
	private final String[] web;
	private final Integer[] imageId;
	private final String[] num;
	public ContactList(Activity context,
	String[] web, Integer[] imageId,  String[] num) {
	super(context, R.layout.activity_contactlist, web);
	this.context = context;
	this.web = web;
	this.imageId = imageId;
	this.num = num;
	}
	@Override
	public View getView(int position, View view, ViewGroup parent) {
	LayoutInflater inflater = context.getLayoutInflater();
	View rowView= inflater.inflate(R.layout.activity_contactlist, null, true);
	TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
	ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
	TextView txtNum = (TextView) rowView.findViewById(R.id.phone);
	txtTitle.setText(web[position]);
	imageView.setImageResource(imageId[position]);
	txtNum.setText(num[position]);
	return rowView;
	}
}
