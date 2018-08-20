package com.capgemini.exceptions;

public class MandatoryValueNotFilledException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public MandatoryValueNotFilledException()
	  {
		super("All mandatory valued must be filled");
	  }

}
