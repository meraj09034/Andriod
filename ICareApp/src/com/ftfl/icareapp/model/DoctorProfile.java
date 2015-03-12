package com.ftfl.icareapp.model;

public class DoctorProfile {

	String mId;
	String mDoctorName;
	String mSpeciality;
	String mPhone;
	String mEmail;
	String mChamber;
	
	public DoctorProfile() {
		super();
	}

	public DoctorProfile(String mId, String mDoctorName, String mSpeciality,
			String mPhone, String mEmail, String mChamber) {
		super();
		this.mId = mId;
		this.mDoctorName = mDoctorName;
		this.mSpeciality = mSpeciality;
		this.mPhone = mPhone;
		this.mEmail = mEmail;
		this.mChamber = mChamber;
	}

	public String getId() {
		return mId;
	}

	public void setId(String mId) {
		this.mId = mId;
	}

	public String getDoctorName() {
		return mDoctorName;
	}

	public void setDoctorName(String mDoctorName) {
		this.mDoctorName = mDoctorName;
	}

	public String getSpeciality() {
		return mSpeciality;
	}

	public void setSpeciality(String mSpeciality) {
		this.mSpeciality = mSpeciality;
	}

	public String getPhone() {
		return mPhone;
	}

	public void setPhone(String mPhone) {
		this.mPhone = mPhone;
	}

	public String getEmail() {
		return mEmail;
	}

	public void setEmail(String mEmail) {
		this.mEmail = mEmail;
	}

	public String getChamber() {
		return mChamber;
	}

	public void setChamber(String mChamber) {
		this.mChamber = mChamber;
	}
	
}
