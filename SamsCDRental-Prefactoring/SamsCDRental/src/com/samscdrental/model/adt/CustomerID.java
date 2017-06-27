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

public class CustomerID
	implements java.io.Serializable
{
	private static final long serialVersionUID = -1L; 

	public CustomerID()
	{

	}

	public CustomerID( String aString ) throws CustomerIDFormatDeviation
	{
		fromString( aString );

	}

	/**
	 * fromString
	 *
	 * @param aString String
	 * @return boolean
	 */
	public void fromString( String aString ) throws CustomerIDFormatDeviation
	{
		if ( aString.length() == LENGTH_OF_VALUE )
		{
			if ( !StringHelper.containsOnlyAlphanumerics( aString ) )
			{
				throw new CustomerIDFormatDeviation(
					ERROR_CUSTOMER_ID_BAD_CHARACTERS );
			}

			theValue = aString;
		}
		else
		{

			throw new CustomerIDFormatDeviation( ERROR_CUSTOMER_ID_BAD_LENGTH );
		}
	}

	public static CustomerID parseString( String aString ) throws
		CustomerIDFormatDeviation
	{
		return new CustomerID( aString );
	}

	boolean isInvalid()
	{
		return theValue.equals( INVALID_ID );
	}

/*	public String getValue()
	{
		return theValue;
	}
*/
	/**
	 * Returns a string representation of the object.
	 *
	 * @return a string representation of the object.
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
	public boolean equals( CustomerID aCustomerID )
	{
		return theValue.equals( aCustomerID.theValue );
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
		return equals( ( CustomerID ) obj );
	}

	private String theValue = INVALID_ID;
	private static final String INVALID_ID = "INVALID ID";
	private static final int LENGTH_OF_VALUE = 10;

	// Error messages
	private static final String ERROR_CUSTOMER_ID_BAD_LENGTH =
		"Customer ID not correct length ";
	private static final String ERROR_CUSTOMER_ID_BAD_CHARACTERS =
		"Customer ID can contain only digits and letters ";

}
