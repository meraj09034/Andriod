package com.ftfl.icareapp.adapter;

import java.util.List;

import com.ftfl.icareapp.R;
import com.ftfl.icareapp.model.Profile;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileAdapter extends ArrayAdapter<Profile> {
	private static LayoutInflater mInflater = null;
	List<Profile> mPofiles;

	public ProfileAdapter(Activity context, List<Profile> ePlaces) {
		super(context, R.layout.activity_profile_custome, ePlaces);
		this.mPofiles = ePlaces;

		/*********** Layout inflator to call external xml layout () ***********/
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/********* Create a holder Class to contain inflated xml file elements *********/
	public static class ViewHolder {

		public TextView mId;
		public TextView mAge;
		public TextView mWeight;

		public TextView mHeight;
		public TextView mGender;
		public TextView mName;
		public TextView mBGroup;

	}

	// convert from byte array to bitmap
	public static Bitmap getImage(byte[] image) {
		return BitmapFactory.decodeByteArray(image, 0, image.length);
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View vi = convertView;
		ViewHolder holder;
		if (convertView == null) {

			/****** Inflate tabitem.xml file for each row ( Defined below ) *******/
			vi = mInflater.inflate(R.layout.activity_profile_custome, null);

			/****** View Holder Object to contain tabitem.xml file elements ******/

			holder = new ViewHolder();
			holder.mId = (TextView) vi.findViewById(R.id.tvProfileId);
			holder.mAge = (TextView) vi.findViewById(R.id.tvProfileCustomeAge);
			holder.mName = (TextView) vi
					.findViewById(R.id.tvProfileCustomeName);
			holder.mHeight = (TextView) vi
					.findViewById(R.id.tvProfileCustomeHeight);
			holder.mWeight = (TextView) vi
					.findViewById(R.id.tvProfileCustomeWeight);
			holder.mGender = (TextView) vi
					.findViewById(R.id.tvProfileCustomeGender);
			holder.mBGroup = (TextView) vi
					.findViewById(R.id.tvProfileCustomeBlDGroup);

			/************ Set holder with LayoutInflater ************/
			vi.setTag(holder);
		} else {
			holder = (ViewHolder) vi.getTag();
		}

		Profile mProfiles;
		mProfiles = mPofiles.get(position);

		holder.mId.setText(mProfiles.getId().toString());
		holder.mAge.setText("Age: " + mProfiles.getAge().toString());
		holder.mName.setText("Name: " + mProfiles.getName().toString());
		holder.mHeight.setText("Height: " + mProfiles.getHeight().toString());
		holder.mWeight.setText("Weight: " + mProfiles.getWeight().toString());
		holder.mGender.setText("Gender: " + mProfiles.getGender().toString());
		holder.mBGroup.setText("Blood Group: "
				+ mProfiles.getBloodGroup().toString());

		return vi;
	}
}
