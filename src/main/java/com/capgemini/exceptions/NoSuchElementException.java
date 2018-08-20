package com.capgemini.exceptions;

public class NoSuchElementException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public NoSuchElementException(String message){
		
		super("There is no such " +message+" in database");
		
	}

}
