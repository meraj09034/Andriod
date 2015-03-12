package com.ftfl.hospital.model;

public class Hospital {
	
	String mId = "";
	String mName = "";
	String mAddress = "";
	String mServiceDescription = "";
	String mLatitude = "";
	String mLongitude = "";
	

	public Hospital() {
		super();
	}
	
	public Hospital(String mId, String mName, String mAddress,
			String mServiceDescription, String mLatitude, String mLongitude) {
		super();
		this.mId = mId;
		this.mName = mName;
		this.mAddress = mAddress;
		this.mServiceDescription = mServiceDescription;
		this.mLatitude = mLatitude;
		this.mLongitude = mLongitude;
	}
	
	public String getId() {
		return mId;
	}
	public void setId(String mId) {
		this.mId = mId;
	}
	public String getName() {
		return mName;
	}
	public void setName(String mName) {
		this.mName = mName;
	}
	public String getAddress() {
		return mAddress;
	}
	public void setAddress(String mAddress) {
		this.mAddress = mAddress;
	}
	public String getServiceDescription() {
		return mServiceDescription;
	}
	public void setServiceDescription(String mServiceDescription) {
		this.mServiceDescription = mServiceDescription;
	}
	public String getLatitude() {
		return mLatitude;
	}
	public void setLatitude(String mLatitude) {
		this.mLatitude = mLatitude;
	}
	public String getLongitude() {
		return mLongitude;
	}
	public void setLongitude(String mLongitude) {
		this.mLongitude = mLongitude;
	}
	

}
