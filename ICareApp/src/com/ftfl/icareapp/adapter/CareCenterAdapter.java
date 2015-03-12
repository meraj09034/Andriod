package com.ftfl.icareapp.adapter;

import com.ftfl.icareapp.R;
import com.ftfl.icareapp.R.id;
import com.ftfl.icareapp.R.layout;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CareCenterAdapter extends ArrayAdapter<String> {

	private final Activity context;
	private final String[] mCareName;

	private final double[] mLatitude;
	private final double[] mLongitude;

	public CareCenterAdapter(Activity context, String[] mCareName,
			double[] mLatitude, double[] mLongitude) {
		super(context, R.layout.activity_carecenterlistview, mCareName);
		this.context = context;
		this.mCareName = mCareName;
		this.mLatitude = mLatitude;
		this.mLongitude = mLongitude;

	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = inflater.inflate(R.layout.activity_carecenterlistview, null, true);

		TextView txtNum = (TextView) rowView
				.findViewById(R.id.tvCareCenterName);
		TextView tvLatitude = (TextView) rowView.findViewById(R.id.tvLat);
		TextView tvLongitude = (TextView) rowView.findViewById(R.id.tvLong);

		txtNum.setText(mCareName[position]);
		tvLatitude.setText("Latitude :"+mLatitude[position]);
		tvLongitude.setText("Longitude :"+mLongitude[position]);
		return rowView;
	}

}
