package com.ftfl.icareapp.model;

public class Profile {
	
	String mId;
	String mName;
	String mAge;
	String mWeight;
	String mHeight;
	String mGender;
	String mBloodGroup;
	
	
	public Profile() {
		super();
	}
	
	public Profile(String mName, String mAge, String mWeight, String mHeight,
			String mGender, String mBloodGroup) {
		super();
		this.mName = mName;
		this.mAge = mAge;
		this.mWeight = mWeight;
		this.mHeight = mHeight;
		this.mGender = mGender;
		this.mBloodGroup = mBloodGroup;
	}

	public Profile(String mId, String mName, String mAge, String mWeight,
			String mHeight, String mGender, String mBloodGroup) {
		super();
		this.mId = mId;
		this.mName = mName;
		this.mAge = mAge;
		this.mWeight = mWeight;
		this.mHeight = mHeight;
		this.mGender = mGender;
		this.mBloodGroup = mBloodGroup;
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
	public String getAge() {
		return mAge;
	}
	public void setAge(String mAge) {
		this.mAge = mAge;
	}
	public String getWeight() {
		return mWeight;
	}
	public void setWeight(String mWeight) {
		this.mWeight = mWeight;
	}
	public String getHeight() {
		return mHeight;
	}
	public void setHeight(String mHeight) {
		this.mHeight = mHeight;
	}
	public String getGender() {
		return mGender;
	}
	public void setGender(String mGender) {
		this.mGender = mGender;
	}
	public String getBloodGroup() {
		return mBloodGroup;
	}
	public void setBloodGroup(String mBloodGroup) {
		this.mBloodGroup = mBloodGroup;
	}

}
