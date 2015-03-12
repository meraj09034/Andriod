package com.ftfl.icareapp.adapter;

import java.util.List;

import com.ftfl.icareapp.R;
import com.ftfl.icareapp.model.MedicalHistory;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.database.MergeCursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MedicalHistoryCustomAdapter extends ArrayAdapter<MedicalHistory> {
	private static LayoutInflater mInflater = null;
	List<MedicalHistory> mPlaces;
	public String mImage;
	String yourLatitude;
	String yourlongitude;
	String provider;
	Location location;

	public MedicalHistoryCustomAdapter(Activity context,
			List<MedicalHistory> ePlaces) {
		super(context, R.layout.activity_medicsalhistory_custopm_list, ePlaces);
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

		public TextView mName;

		public ImageView mIvImage;

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
			vi = mInflater.inflate(
					R.layout.activity_medicsalhistory_custopm_list, null);

			/****** View Holder Object to contain tabitem.xml file elements ******/

			holder = new ViewHolder();
			holder.mId = (TextView) vi.findViewById(R.id.tvPlaceId);
			holder.mDate = (TextView) vi
					.findViewById(R.id.tvCustomeMedicalDate);
			holder.mName = (TextView) vi
					.findViewById(R.id.tvCustomeMedicalDoctorName);
			holder.mIvImage = (ImageView) vi
					.findViewById(R.id.ivprepciptioin_Image);

			/************ Set holder with LayoutInflater ************/
			vi.setTag(holder);
		} else {
			holder = (ViewHolder) vi.getTag();
		}

		MedicalHistory places;
		places = mPlaces.get(position);

		holder.mId.setText(places.getId().toString());
		holder.mDate.setText("Date: " + places.getDate().toString());
		holder.mName.setText("Doctor Name: "
				+ places.getMedicalDoctorName().toString());

		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 4;
		Bitmap bitmap = BitmapFactory
				.decodeFile(places.getPhotoPath(), options);
		holder.mIvImage.setImageBitmap(bitmap);

	

		return vi;
	}
}
