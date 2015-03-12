package com.ftfl.cameaapp.model;

public class LocationAndImage {
	int mId = 0;
	String mLatitude = "";
	String mLongitude = "";
	String mRemarks = "";
	String mPhotoPath = "";
	String mDate = "";
	String mTime = "";

	public int getmId() {
		return mId;
	}

	public void setmId(int mId) {
		this.mId = mId;
	}

	public String getmLatitude() {
		return mLatitude;
	}

	

	public String getmLongitude() {
		return mLongitude;
	}

	

	public String getmRemarks() {
		return mRemarks;
	}

	

	public String getmPhotoPath() {
		return mPhotoPath;
	}

	

	public String getmDate() {
		return mDate;
	}
	public String getmTime() {
		return mTime;
	}
	

	
public LocationAndImage(String eLatitude, String eLongitude, String eRemarks,String ePhotoPath,String eDate,String eTime) {
		super();
		this.mLatitude = eLatitude;
		this.mLongitude = eLongitude;
		this.mRemarks = eRemarks;
		this.mPhotoPath = ePhotoPath;
		this.mDate = eDate;
		this.mTime = eTime;
	}

}
