package com.cg.phonebook.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AdminEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long adminId;
	/*
	 * Admin First Name 
	 */
	private String firstName;
	/*
	 * Admin Last name
	 */
	private String lastName;
	/*
	 * Admin ID which is unique
	 */
	@Column(unique=true, updatable = false)
	private String adminIdentifier;
	/*
	 * Admin Email ID
	 */
	@Column(unique = true, updatable = false)
	private String email;
	/*
	 * Admin phone number
	 */
	@Column(unique = true, updatable = false)
	private String phoneNumber;
	/*
	 * Admin Password
	 */
	private String password;
	private String confirmPassword;
	
	
	public long getAdminId() {
		return adminId;
	}

	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAdminIdentifier() {
		return adminIdentifier;
	}

	public void setAdminIdentifier(String adminIdentifier) {
		this.adminIdentifier = adminIdentifier;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public AdminEntity(String firstName, String lastName, String adminIdentifier, String email, String phoneNumber,
			String password, String confirmPassword) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.adminIdentifier = adminIdentifier;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}
	
	public AdminEntity(String firstName, String adminIdentifier, String phoneNumber) {
		super();
		this.firstName = firstName;
		this.adminIdentifier = adminIdentifier;
		this.phoneNumber = phoneNumber;
	}
	
	public AdminEntity() {
		super();
	}
	@Override
	public String toString() {
		return "AdminEntity [adminId=" + adminId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", adminIdentifier=" + adminIdentifier + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", password=" + password + ", confirmPassword=" + confirmPassword + "]";
	}
	
	
	
	
	
	
	
}
