package com.bbd.model;

public class User {

	private int userID;
	private String username;
	private String email;
	private String mobile;

	public User(int userID, String username, String email, String mobile) {
		this.userID = userID;
		this.username = username;
		this.email = email;
		this.mobile = mobile;
	}

	public User() {}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
