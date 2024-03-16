package com.levelup.model;

public class User {

	private int UserId;
	private String UserName;
	private String EmailAddress;
	private String MobileNo;

	public User(int UserId, String UserName, String EmailAddress, String MobileNo) {
		this.UserId = UserId;
		this.UserName = UserName;
		this.EmailAddress = EmailAddress;
		this.MobileNo = MobileNo;
	}

	public User() {}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int UserId) {
		this.UserId = UserId;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String UserName) {
		this.UserName = UserName;
	}

	public String getEmailAddress() {
		return EmailAddress;
	}

	public void setEmailAddress(String EmailAddress) {
		this.EmailAddress = EmailAddress;
	}

	public String getMobileNo() {
		return MobileNo;
	}

	public void setMobileNo(String MobileNo) {
		this.MobileNo = MobileNo;
	}

}
