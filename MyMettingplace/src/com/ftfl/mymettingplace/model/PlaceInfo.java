package com.ftfl.mymettingplace.model;

public class PlaceInfo {

	String mPlaceId;
	String mPlaceDate;
	String mPlaceTime;
	String mPlaceLatitude;
	String mPlaceLongitude;
	String mPlaceDescription;
	byte[] mPlaceImage;

	public String getPlaceLongitude() {
		return mPlaceLongitude;
	}

	public void setHospitalDate(String mHospitalDate) {
		this.mPlaceLongitude = mHospitalDate;
	}

	public PlaceInfo(String ePlaceId, String ePlaceDate,
			String ePlaceTime, String ePlaceLatitude,
			String ePlaceLongitude, String ePlaceDescription,
			byte[] ePlaceImage) {
		super();

		mPlaceId = ePlaceId;
		mPlaceDate = ePlaceDate;
		mPlaceTime = ePlaceTime;
		mPlaceLatitude = ePlaceLatitude;
		mPlaceLongitude = ePlaceLongitude;
		mPlaceDescription = ePlaceDescription;
		mPlaceImage = ePlaceImage;

	}

	public PlaceInfo(String ePlaceDate, String ePlaceTime,
			String ePlaceLatitude, String ePlaceLongitude,
			String ePlaceDescription, byte[] ePlaceImage) {
		super();

		mPlaceDate = ePlaceDate;
		mPlaceTime = ePlaceTime;
		mPlaceLatitude = ePlaceLatitude;
		mPlaceLongitude = ePlaceLongitude;
		mPlaceDescription = ePlaceDescription;
		mPlaceImage = ePlaceImage;
	}

	public String getPlaceId() {
		return mPlaceId;
	}

	public void setHospitalId(String dietId) {
		mPlaceId = dietId;
	}

	public String getPlaceDate() {
		return mPlaceDate;
	}

	public String getPlaceTime() {
		return mPlaceTime;
	}

	public String getPlaceLatitude() {
		return mPlaceLatitude;
	}

	public String getPlaceDescription() {
		return mPlaceDescription;
	}

	public byte[] getPlaceImage() {
		return mPlaceImage;
	}

}
