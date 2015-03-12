package com.ftfl.icareapp.model;

public class MedicalHistory {
	
	String mId;
	String mPhotoPath;
	String mDate;
	String mMedicalDoctorName;
	String mPurpose;
	
	
	public MedicalHistory() {
		super();
	}
	public MedicalHistory(String mId, String mPhotoPath, String mDate,
			String mDoctorName, String mPurpose) {
		super();
		this.mId = mId;
		this.mPhotoPath = mPhotoPath;
		this.mDate = mDate;
		this.mMedicalDoctorName = mDoctorName;
		this.mPurpose = mPurpose;
	}
	public String getId() {
		return mId;
	}
	public void setId(String mId) {
		this.mId = mId;
	}
	public String getPhotoPath() {
		return mPhotoPath;
	}
	public void setPhotoPath(String mPhotoPath) {
		this.mPhotoPath = mPhotoPath;
	}
	public String getDate() {
		return mDate;
	}
	public void setDate(String mDate) {
		this.mDate = mDate;
	}
	public String getMedicalDoctorName() {
		return mMedicalDoctorName;
	}
	public void setMedicalDoctorName(String mDoctorName) {
		this.mMedicalDoctorName = mDoctorName;
	}
	public String getPurpose() {
		return mPurpose;
	}
	public void setPurpose(String mPurpose) {
		this.mPurpose = mPurpose;
	}
	
}
