package com.cg.phonebook.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;


@Entity
public class Contact {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long contactId;

	/*
	 * Contact Number
	 */
	@Column(unique=true)
	private String contactNumber;
	/*
	 * Contact Name
	 */
	@Column(unique=true)
    private String contactName;
	/*
	 * contact email
	 */
	@Column(name="email")
	private String  email;
	@Column(name = "user_id")
	private long userId;
	
	
	public Contact(String contactNumber, String contactName, String email) {
		super();
		this.contactNumber = contactNumber;
		this.contactName = contactName;
		this.email = email;
		
	}
	
	public Contact() {
    	
    }
	
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getContactId() {
		return contactId;
	}

	public void setContactId(long contactId) {
		this.contactId = contactId;
	}

	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Contact [contactId=" + contactId + ", contactNumber=" + contactNumber + ", contactName=" + contactName
				+ ", email=" + email + ", userId=" + userId + "]";
	}
	

	 
 
 }
