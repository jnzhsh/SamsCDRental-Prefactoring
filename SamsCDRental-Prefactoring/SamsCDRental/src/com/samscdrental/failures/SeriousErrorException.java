package com.samscdrental.failures;

/**
 * <p>Title: Sams CD Rental Store</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author Ken Pugh
 * @version 1.0
 */

public class SeriousErrorException
	extends RuntimeException
{
	private static final long serialVersionUID = -1L; 

	public SeriousErrorException()
	{
	}

	public SeriousErrorException( String explantion, Exception aException )
	{
		super( explantion, aException );

	}

}
