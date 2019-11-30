package com.amdocs.crm.beans;

import javax.validation.constraints.NotNull;

import com.amdocs.crm.model.Address;

public class RegistrationBean {
	
	@NotNull(message = "Please enter full name")
	private String fullName;
	
	@NotNull(message = "Please enter email")
	private String email;

	@NotNull(message = "Please enter contact number")
	private String contactNumber;

	@NotNull(message = "Please enter username")
	private String userName;

	@NotNull(message = "Please enter password")
	private String password;
	
	@NotNull(message = "Please enter address related values")
	private Address address;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
