package com.samscdrental.failures;

public class ImportFileDeviation
	extends Deviation
{
	private static final long serialVersionUID = -1L; 

	public ImportFileDeviation( String explanation, Exception aException )
	{
		super( explanation );
		theOriginalException = aException;

	}

	public Exception getTheOriginalException()
	{
		return theOriginalException;
	}

	private Exception theOriginalException;

}
