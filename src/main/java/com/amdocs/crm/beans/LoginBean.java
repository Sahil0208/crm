package com.amdocs.crm.beans;

import javax.validation.constraints.NotNull;

public class LoginBean {
	
	@NotNull(message = "Please enter user name")
	private String userName;
	
	@NotNull(message = "Please enter password")
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
