package com.cg.phonebook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TicketNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * no parameter constructor
	 */

	public TicketNotFoundException() {
		super();
	}
	public TicketNotFoundException(String message) {
		super(message);
		
		}

}
