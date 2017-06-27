package com.samscdrental.failures;

public class Deviation
	extends Exception
{
	private static final long serialVersionUID = -1L; 

	public Deviation( String explanation )
	{
		super( explanation );
	}

}
