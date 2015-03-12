package com.ftfl.icareapp.fregment;

import com.ftfl.icareapp.CallEmergencyNumberActivity;
import com.ftfl.icareapp.CareCenterListActivity;
import com.ftfl.icareapp.CreateDoctorActivity;
import com.ftfl.icareapp.CreateMedicalHistoryActivity;
import com.ftfl.icareapp.CreateVaccinationProfileActivity;

import com.ftfl.icareapp.DoctorListViewActivity;
import com.ftfl.icareapp.HomeScreenActivity;
import com.ftfl.icareapp.ICareCreateDietChartActivity;
import com.ftfl.icareapp.ICareDietListActivity;
import com.ftfl.icareapp.ImportantNotesListViewActivity;
import com.ftfl.icareapp.MedicalHistoryListViewActivity;
import com.ftfl.icareapp.ProfileListHomeActivity;
import com.ftfl.icareapp.R;
import com.ftfl.icareapp.VaccinationListActivity;
import com.ftfl.icareapp.database.DoctorDataSource;
import com.ftfl.icareapp.database.ICareDietDataSource;
import com.ftfl.icareapp.database.MedicalHistoryDataSourc;
import com.ftfl.icareapp.database.ProfileDataSource;
import com.ftfl.icareapp.database.VaccinationDataSourc;
import com.ftfl.icareapp.model.DoctorProfile;
import com.ftfl.icareapp.model.Profile;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeScreenFragment extends Fragment {

	ImageView imgDietChaet = null;
	ImageView imgDoctor = null;
	ImageView imgMediccal = null;
	ImageView imgVaccination = null;
	ImageView imgEmergencyCall, mUserProfile, mCareCenter, mImportantNotes;
	Context thiscontext;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_create_hhome_screent,
				container, false);

		imgDoctor = (ImageView) rootView.findViewById(R.id.ImageViewDoctor);
		imgDoctor.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(getActivity(),
						DoctorListViewActivity.class);
				startActivity(intent);
			}
		});
		imgDietChaet = (ImageView) rootView
				.findViewById(R.id.imageViewDietChart);
		imgDietChaet.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(getActivity(),
						ICareDietListActivity.class);
				startActivity(intent);
			}
		});
		imgMediccal = (ImageView) rootView.findViewById(R.id.ImageViewMedical);
		imgMediccal.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(getActivity(),
						MedicalHistoryListViewActivity.class);
				startActivity(intent);
			}
		});
		imgVaccination = (ImageView) rootView
				.findViewById(R.id.imageViewVeccination);
		imgVaccination.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getActivity(),
						VaccinationListActivity.class);
				startActivity(intent);
			}
		});

		imgEmergencyCall = (ImageView) rootView
				.findViewById(R.id.imgVEmergencyCall);
		imgEmergencyCall.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getActivity(),
						CallEmergencyNumberActivity.class);
				startActivity(intent);

			}
		});
		mUserProfile = (ImageView) rootView.findViewById(R.id.img_About);
		mUserProfile.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getActivity(),
						ProfileListHomeActivity.class);
				startActivity(intent);

			}
		});

		mCareCenter = (ImageView) rootView.findViewById(R.id.img_carecenter);
		mCareCenter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getActivity(),
						CareCenterListActivity.class);
				startActivity(intent);

			}
		});

		mImportantNotes = (ImageView) rootView
				.findViewById(R.id.img_importantnotes);
		mImportantNotes.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getActivity(),
						ImportantNotesListViewActivity.class);
				startActivity(intent);
			}
		});
		return rootView;

	}
}
