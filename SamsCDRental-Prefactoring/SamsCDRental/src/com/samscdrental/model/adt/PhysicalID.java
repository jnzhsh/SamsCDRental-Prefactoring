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
import com.samscdrental.helper.*;

public class PhysicalID
	implements java.io.Serializable
{

	private static final long serialVersionUID = -1L; 

	public PhysicalID()
	{
	}

	/**
	 * PhysicalID
	 *
	 * @param aString String
	 */

	public PhysicalID( String aString ) throws PhysicalIDFormatDeviation
	{
		fromString( aString );

	}
/*
	public String getValue()
	{
		return theValue;
	}
*/
	boolean isInvalid()
	{
		return theValue.equals( INVALID_ID );
	}

	/**
	 * fromString
	 *
	 * @param aString String
	 * @return boolean
	 */
	private void fromString( String aString ) throws PhysicalIDFormatDeviation
	{
		if ( aString.length() == LENGTH_OF_VALUE )
		{
			if ( !StringHelper.containsOnlyNumerics( aString ) )
			{
				throw new PhysicalIDFormatDeviation(
					ERROR_PHYSICAL_ID_BAD_CHARACTERS );
			}
			theValue = aString;
		}
		else
		{
			throw new PhysicalIDFormatDeviation( ERROR_PHYSICAL_ID_BAD_LENGTH );
		}
	}

	public static PhysicalID parseString( String aString ) throws
		PhysicalIDFormatDeviation
	{
		return new PhysicalID( aString );
	}

	/**
	 * Indicates whether some other object is "equal to" this one.
	 *
	 * @param obj the reference object with which to compare.
	 * @return <code>true</code> if this object is the same as the obj argument;
	 *   <code>false</code> otherwise.
	 * @todo Implement this java.lang.Object method
	 */
	public boolean equals( PhysicalID aPhysicalID )
	{
		return ( aPhysicalID.theValue.equals( this.theValue ) );

	}

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
		return equals( ( PhysicalID ) obj );
	}

	private String theValue = INVALID_ID;
	private static final String INVALID_ID = "INVALID_ID";
	private static final int LENGTH_OF_VALUE = 10;

	// Error messages
	private static final String ERROR_PHYSICAL_ID_BAD_LENGTH =
		"Physical ID not correct length ";
	private static final String ERROR_PHYSICAL_ID_BAD_CHARACTERS =
		"Physical ID can contain only digits ";

}
