package com.ftfl.hospital.model;

public class Camera {
	int mIid;
	String mName;
	byte[] mImage;
	
	
	public Camera() {

	}
	
	public Camera(String mName, byte[] mImage) {
		
		this.mName = mName;
		this.mImage = mImage;
	}

	public Camera(int mIid, String mName, byte[] mImage) {

		this.mIid = mIid;
		this.mName = mName;
		this.mImage = mImage;
	}
	
	public int getIid() {
		return mIid;
	}
	public void setIid(int mIid) {
		this.mIid = mIid;
	}
	public String getName() {
		return mName;
	}
	public void setName(String mName) {
		this.mName = mName;
	}
	public byte[] getImage() {
		return mImage;
	}
	public void setImage(byte[] mImage) {
		this.mImage = mImage;
	}
}
