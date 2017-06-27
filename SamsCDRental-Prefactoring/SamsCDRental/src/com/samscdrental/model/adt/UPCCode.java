package com.samscdrental.model.adt;

/**
 * <p>Title: Sams CD Rental Store</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author Ken Pugh
 * @version 1.0
 */
import com.samscdrental.failures.*;

public class UPCCode
	implements java.io.Serializable
{

	 private static final long serialVersionUID = -1L; 

/*	public String getValue()
	{
		return theValue;
	}
*/
	public UPCCode()
	{
	}

	/**
	 * fromString
	 *
	 * @param aString String
	 * @return boolean
	 */
	private void fromString( String aString ) throws UPCCodeFormatDeviation
	{
		if ( aString.length() == LENGTH_OF_VALUE )
		{
			theValue = aString;
		}
		else
		{
			throw new UPCCodeFormatDeviation( ERROR_UPCCODE_BAD_FORMAT );
		}
	}

	public static UPCCode parseString( String aString ) throws
		UPCCodeFormatDeviation
	{
		return new UPCCode( aString );
	}

	/**
	 * UPCCode
	 *
	 * @param aString String
	 */
	public UPCCode( String aString ) throws UPCCodeFormatDeviation
	{

		fromString( aString );

	}

	/**
	 * Indicates whether some other object is "equal to" this one.
	 *
	 * @param obj the reference object with which to compare.
	 * @return <code>true</code> if this object is the same as the obj argument;
	 *   <code>false</code> otherwise.
	 * @todo Implement this java.lang.Object method
	 */
	public boolean equals( UPCCode aUPCCode )
	{
		return aUPCCode.theValue.equals( this.theValue );

	}

	/**
	 * Returns a string representation of the object.
	 *
	 * @return a string representation of the object.
	 * @todo Implement this java.lang.Object method
	 */
	public String toString()
	{
		return theValue;
	}

	/**
	 * Indicates whether some other object is "equal to" this one.
	 *
	 * @param obj the reference object with which to compare.
	 * @return <code>true</code> if this object is the same as the obj argument;
	 *   <code>false</code> otherwise.
	 * @todo Implement this java.lang.Object method
	 */
	public boolean equals( Object obj )
	{
		return equals( ( UPCCode ) obj );
	}

	private String theValue = "INVALID UPC";
	private static final int LENGTH_OF_VALUE = 12;

	// Error messages
	private static final String ERROR_UPCCODE_BAD_FORMAT =
		"UPCCode - bad format ";

}
