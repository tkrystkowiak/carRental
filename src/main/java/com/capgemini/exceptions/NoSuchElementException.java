package com.capgemini.exceptions;

/**
 * Thrown when there is no such element in database
 * 
 * @author TKRYSTKO
 *
 */
public class NoSuchElementException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public NoSuchElementException(String element){
		
		super("There is no such " +element+" in database");
		
	}

}
