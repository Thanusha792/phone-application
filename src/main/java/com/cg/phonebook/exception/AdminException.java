package com.cg.phonebook.exception;

public class AdminException extends RuntimeException{
	
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public AdminException() {
	 super();
 }
 public AdminException(String errMessage) {
	 super(errMessage);
 }
}
