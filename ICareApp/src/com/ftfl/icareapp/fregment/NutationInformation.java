package com.ftfl.icareapp.fregment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ftfl.icareapp.R;

public class NutationInformation extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fregment_nutationinfo,
				container, false);

		TextView tvVaccineInfoTitel=(TextView) rootView.findViewById(R.id.tvNutationHealthTitel);
		//String mVeccineInfo=tvVaccineInfo.getText().toString();
		tvVaccineInfoTitel.setText(R.string.tv_NutationInfo_titel);

		TextView tvVaccineInfo=(TextView) rootView.findViewById(R.id.tvNutationHealthinfo);
		//String mVeccineInfo=tvVaccineInfo.getText().toString();
		tvVaccineInfo.setText(R.string.tv_NutaionfregmentInfo);
		return rootView;
	}
}
