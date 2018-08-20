package com.capgemini.exceptions;

/**
 * Thrown when one of mandatory values in builder is not filled.
 * 
 * @author TKRYSTKO
 *
 */
public class MandatoryValueNotFilledException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public MandatoryValueNotFilledException()
	  {
		super("All mandatory valued must be filled");
	  }

}
