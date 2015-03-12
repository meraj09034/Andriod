package com.ftfl.icareapp.adapter;

import java.util.List;

import com.ftfl.icareapp.R;
import com.ftfl.icareapp.model.DoctorProfile;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class DoctorAdapter extends ArrayAdapter<DoctorProfile> {

	private static LayoutInflater mInflater = null;

	List<DoctorProfile> mDoctors;
	String mDoctorName;
	String mMobile;
	String mEmail;
	String mSpeciality;

	public DoctorAdapter(Activity context, List<DoctorProfile> ePlaces) {
		super(context, R.layout.activity_doctor_custome, ePlaces);
		this.mDoctors = ePlaces;

		/*********** Layout inflator to call external xml layout () ***********/
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/********* Create a holder Class to contain inflated xml file elements *********/
	public static class ViewHolder {

		public TextView mId;
		public TextView mEmail;
		public TextView mMobile;
		public TextView mSpeciality;
		public TextView mName;

	}

	// @SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View vi = convertView;
		ViewHolder holder;
		if (convertView == null) {

			/****** Inflate tabitem.xml file for each row ( Defined below ) *******/
			vi = mInflater.inflate(R.layout.activity_doctor_custome,
					null);

			/****** View Holder Object to contain tabitem.xml file elements ******/

			holder = new ViewHolder();
			holder.mId = (TextView) vi.findViewById(R.id.tvdoctorId);
			holder.mEmail = (TextView) vi
					.findViewById(R.id.tvDoctorCustomeEmail);
			holder.mMobile = (TextView) vi
					.findViewById(R.id.tvDoctorCustomeMobile);
			holder.mName = (TextView) vi
					.findViewById(R.id.tvDoctorCustomeName);
			holder.mSpeciality = (TextView) vi
					.findViewById(R.id.tvDoctorCustomeSpeciality);

			/************ Set holder with LayoutInflater ************/
			vi.setTag(holder);
		} else {
			holder = (ViewHolder) vi.getTag();
		}

		DoctorProfile doctors;
		doctors = mDoctors.get(position);

		holder.mId.setText(doctors.getId().toString());
		holder.mEmail.setText("Email: " + doctors.getEmail().toString());
		holder.mMobile.setText("Mobile: " + doctors.getPhone().toString());
		holder.mName.setText("Name: " + doctors.getDoctorName().toString());
		holder.mSpeciality.setText("Speciality: "
				+ doctors.getSpeciality().toString());

		// create class object

		return vi;
	}

}
