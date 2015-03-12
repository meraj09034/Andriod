package com.ftfl.icareapp.fregment;

import com.ftfl.icareapp.CreateDoctorActivity;

import com.ftfl.icareapp.R;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class DietInformationFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.fragment_diet_imformation_profile, container, false);

		TextView tvVaccineInfoTitel = (TextView) rootView
				.findViewById(R.id.tvDietHealthTitel);
		// String mVeccineInfo=tvVaccineInfo.getText().toString();
		tvVaccineInfoTitel.setText(R.string.tv_DietInfo_titel);

		TextView tvVaccineInfo = (TextView) rootView
				.findViewById(R.id.tvDietHealthinfo);
		// String mVeccineInfo=tvVaccineInfo.getText().toString();
		tvVaccineInfo.setText(R.string.tv_DietfregmentInfo);

		return rootView;
	}
}
