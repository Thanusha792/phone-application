package com.cg.phonebook.exception;

public class UsersupportException extends RuntimeException{
	
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public UsersupportException() {
	 super();
 }
 public UsersupportException(String errMessage) {
	 super(errMessage);
 }
}
