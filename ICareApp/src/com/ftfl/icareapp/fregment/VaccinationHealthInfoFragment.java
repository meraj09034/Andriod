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
public class VaccinationHealthInfoFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_create_vaccination, container, false);
		TextView tvVaccineInfoTitel=(TextView) rootView.findViewById(R.id.tvvaccineinfoTitel);
		//String mVeccineInfo=tvVaccineInfo.getText().toString();
		tvVaccineInfoTitel.setText(R.string.tv_vaccineInfo_titel);

		TextView tvVaccineInfo=(TextView) rootView.findViewById(R.id.tvvaccineinfo);
		//String mVeccineInfo=tvVaccineInfo.getText().toString();
		tvVaccineInfo.setText(R.string.tv_vaccineInfo);

        
        return rootView;
	}
  /*public void click(View v){
	  GrowthFragment ldf = new GrowthFragment ();
	  Bundle args = new Bundle();
	  args.putString("YourKey", "YourValue");
	  ldf.setArguments(args);

	  //Inflate the fragment
	  getFragmentManager().beginTransaction().add(R.id.container, ldf).commit();
  }*/
}
