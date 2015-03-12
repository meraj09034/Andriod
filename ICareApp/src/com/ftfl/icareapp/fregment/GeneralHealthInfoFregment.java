package com.ftfl.icareapp.fregment;

import com.ftfl.icareapp.R;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

@SuppressLint("NewApi")
public class GeneralHealthInfoFregment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_general_healthinfo_history, container, false);
		
		TextView tvVaccineInfoTitel=(TextView) rootView.findViewById(R.id.tvGeneralHealthTitel);
		//String mVeccineInfo=tvVaccineInfo.getText().toString();
		tvVaccineInfoTitel.setText(R.string.tv_GeneralInfo_titel);

		TextView tvVaccineInfo=(TextView) rootView.findViewById(R.id.tvGeneralHealthinfo);
		//String mVeccineInfo=tvVaccineInfo.getText().toString();
		tvVaccineInfo.setText(R.string.tv_GeneralInfo);

        
        return rootView;
	}
  
}
