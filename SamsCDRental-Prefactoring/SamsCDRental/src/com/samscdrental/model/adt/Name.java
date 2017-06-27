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

public class Name
	implements java.io.Serializable
{

	private static final long serialVersionUID = -1L; 

	public Name()
	{
	}

	/**
	 * Name
	 *
	 * @param aString String
	 */

	public Name( String aString ) throws NameFormatDeviation
	{
		fromString( aString );

	}

/*	public String getValue()
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
	private void fromString( String aString ) throws NameFormatDeviation
	{

		if ( !StringHelper.containsNoControls( aString ) )
		{
			throw new NameFormatDeviation(
				ERROR_NAME_BAD_CHARACTERS );
		}
		theValue = aString;
	}

	public static Name parseString( String aString ) throws
		NameFormatDeviation
	{
		return new Name( aString );
	}

	/**
	 * Indicates whether some other object is "equal to" this one.
	 *
	 * @param obj the reference object with which to compare.
	 * @return <code>true</code> if this object is the same as the obj argument;
	 *   <code>false</code> otherwise.
	 * @todo Implement this java.lang.Object method
	 */
	public boolean equals( Name aName )
	{
		return ( aName.theValue.equals( this.theValue ) );

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
		return equals( ( Name ) obj );
	}

	private String theValue = INVALID_ID;
	private static final String INVALID_ID = "INVALID_NAME";

	// Error messages
	private static final String ERROR_NAME_BAD_CHARACTERS =
		"Name can contain only letters and spaces ";

}
