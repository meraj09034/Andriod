package com.ftfl.icareapp.adapter;

import java.util.List;

import com.ftfl.icareapp.R;
import com.ftfl.icareapp.model.Vaccination;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class VacccinationAdapter extends ArrayAdapter<Vaccination> {

	private static LayoutInflater mInflater = null;

	List<Vaccination> mPlaces;
	String mVAcccineName;
	String mVaccineDate;
	String mVaccineTime;
	String mVaccineDescription;

	public VacccinationAdapter(Activity context, List<Vaccination> ePlaces) {
		super(context, R.layout.activity_vaccination_customeview, ePlaces);
		this.mPlaces = ePlaces;

		/*********** Layout inflator to call external xml layout () ***********/
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/********* Create a holder Class to contain inflated xml file elements *********/
	public static class ViewHolder {

		public TextView mId;
		public TextView mDate;
		public TextView mTime;
		public TextView mDescription;
		public TextView mName;

	}

	// @SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View vi = convertView;
		ViewHolder holder;
		if (convertView == null) {

			/****** Inflate tabitem.xml file for each row ( Defined below ) *******/
			vi = mInflater.inflate(R.layout.activity_vaccination_customeview,
					null);

			/****** View Holder Object to contain tabitem.xml file elements ******/

			holder = new ViewHolder();
			holder.mId = (TextView) vi.findViewById(R.id.tvVaccineId);
			holder.mDate = (TextView) vi
					.findViewById(R.id.tvVaccineCustomeDate);
			holder.mTime = (TextView) vi
					.findViewById(R.id.tvVaccineCustomeTime);
			holder.mName = (TextView) vi
					.findViewById(R.id.tvVaccineCustomeName);
			holder.mDescription = (TextView) vi
					.findViewById(R.id.tvVaccineCustomeDescription);

			/************ Set holder with LayoutInflater ************/
			vi.setTag(holder);
		} else {
			holder = (ViewHolder) vi.getTag();
		}

		Vaccination places;
		places = mPlaces.get(position);

		holder.mId.setText(places.getId().toString());
		holder.mDate.setText("Date: " + places.getDate().toString());
		holder.mTime.setText("Time: " + places.getTime().toString());
		holder.mName.setText("Name: " + places.getVaccineName().toString());
		holder.mDescription.setText("Description: "
				+ places.getDescription().toString());

		// create class object

		return vi;
	}

}
