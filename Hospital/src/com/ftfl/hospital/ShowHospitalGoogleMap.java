package com.ftfl.hospital;

import com.ftfl.hospital.util.HospitalConstants;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ShowHospitalGoogleMap extends Activity  implements
				LocationListener {
		private GoogleMap mGoogleMap;
		private Double mLatitude;
		private Double mLongitude;
		String mRestarantName;
		private LatLng latLng;

// private GoogleMap mGoogleMap;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hospital_google_map);
		Bundle getData = getIntent().getExtras();
		mLatitude = Double.valueOf(getData
				.getString(HospitalConstants.LATITUDE));
		mLongitude = Double.valueOf(getData
				.getString(HospitalConstants.LONGITUDE));
		mRestarantName = getData.getString(HospitalConstants.HOSPITALNAME);
		latLng = new LatLng(mLatitude, mLongitude);
		
		loadMap();
		
		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		
		// Creating a criteria object to retrieve provider
		Criteria criteria = new Criteria();
		
		// Getting the name of the best provider
		String provider = locationManager.getBestProvider(criteria, true);
		
		// Getting Current Location
		Location location = locationManager.getLastKnownLocation(provider);
		
				if (location != null) {
							onLocationChanged(location);
					}
					locationManager.requestLocationUpdates(provider, 20000, 0, this);
		}
		
		@Override
		protected void onResume() {
			super.onResume();
			loadMap();
			}
		
		
		
		private void loadMap() {
		// TODO Auto-generated method stub
				if (mGoogleMap != null) {
					return;
				}
		mGoogleMap = ((MapFragment) getFragmentManager().findFragmentById(
				R.id.map)).getMap();
		if (mGoogleMap == null) {
			return;
		}
		Marker TP = mGoogleMap.addMarker(new MarkerOptions().position(latLng)
				.title(mRestarantName));
		mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		mGoogleMap.setMyLocationEnabled(true);
		
		/*
		 * Marker TP = mGoogleMap.addMarker(new MarkerOptions().
		 * position(latLng).title("MyLocation"));
		 */
		/*
		 * Marker TP = mGoogleMap.addMarker(new MarkerOptions().
		 * position(t1).title("MyLocation"));
		 */
		
		}
		
		@Override
		public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		double latitude = location.getLatitude();
		
		// Getting longitude of the current location
		double longitude = location.getLongitude();
		
		// Creating a LatLng object for the current location
		LatLng latLng = new LatLng(latitude, longitude);
		
		mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
		
		// Zoom in, animating the camera.
		mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(6), 2000, null);
		}
		
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
		}
		
		@Override
		public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
		}
		
		@Override
		public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
		}

}
