package com.cg.phonebook.domain;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class RaiseUserTicket {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long ticketId;
	/*
	 * Ticket Title
	 */
	private String titleType;
	/*
	 * ticket description
	 */
	private String Description;
	/*
	 * User Id
	 */
	@Column(unique = true, updatable = false)
	private String userIdentifier;
	
	

	public long getTicketId() {
		return ticketId;
	}



	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}



	public String getTitleType() {
		return titleType;
	}



	public void setTitleType(String titleType) {
		this.titleType = titleType;
	}



	public String getDescription() {
		return Description;
	}



	public void setDescription(String description) {
		this.Description = description;
	}
	
	



	public String getUserIdentifier() {
		return userIdentifier;
	}



	public void setUserIdentifier(String userIdentifier) {
		this.userIdentifier = userIdentifier;
	}



	public RaiseUserTicket(String userIdentifier, String titleType, String description) {
		super();
		this.userIdentifier = userIdentifier;
		this.titleType = titleType;
		this.Description = description;
	}



	public RaiseUserTicket() {
		super();
	}



	@Override
	public String toString() {
		return "RaiseUserTicket [ticketId=" + ticketId + ", titleType=" + titleType + ", Description=" + Description
				+ ", userIdentifier=" + userIdentifier + "]";
	}

}
