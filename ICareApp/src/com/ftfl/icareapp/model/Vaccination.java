package com.ftfl.icareapp.model;

public class Vaccination {

	String mId = "";
	String mDate = "";
	String mTime = "";
	String mVaccineName = "";
	String mDescription = "";
	String mVaccineAlarm = "";
	
	public Vaccination() {
		super();
	}

	public Vaccination(String mId, String mDate, String mTime,
			String mVaccineName, String mDescription, String mVaccineAlarm) {
		super();
		this.mId = mId;
		this.mDate = mDate;
		this.mTime = mTime;
		this.mVaccineName = mVaccineName;
		this.mDescription = mDescription;
		this.mVaccineAlarm = mVaccineAlarm;
	}

	public String getId() {
		return mId;
	}

	public void setId(String mId) {
		this.mId = mId;
	}

	public String getDate() {
		return mDate;
	}

	public void setDate(String mDate) {
		this.mDate = mDate;
	}

	public String getTime() {
		return mTime;
	}

	public void setTime(String mTime) {
		this.mTime = mTime;
	}

	public String getVaccineName() {
		return mVaccineName;
	}

	public void setVaccineName(String mVaccineName) {
		this.mVaccineName = mVaccineName;
	}

	public String getDescription() {
		return mDescription;
	}

	public void setDescription(String mDescription) {
		this.mDescription = mDescription;
	}

	public String getVaccineAlarm() {
		return mVaccineAlarm;
	}

	public void setVaccineAlarm(String mVaccineAlarm) {
		this.mVaccineAlarm = mVaccineAlarm;
	}

		
	
}
