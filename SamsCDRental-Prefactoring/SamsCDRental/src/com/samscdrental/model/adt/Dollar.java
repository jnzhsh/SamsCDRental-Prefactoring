package com.samscdrental.model.adt;

import java.math.*;
import java.text.*;

import com.samscdrental.failures.*;

/**
 * <p>Title: Sams CD Rental Store</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author Ken Pugh
 * @version 1.0
 */

public class Dollar
	implements java.io.Serializable
{
	private static final long serialVersionUID = -1L; 

	public Dollar()
	{
	}

	public Dollar( double value )
	{
		theValue = new BigDecimal( value );
	}

	/**
	 * fromString
	 *
	 * @param aString String
	 * @return boolean
	 */
	public void fromString( String aString ) throws DollarFormatDeviation
	{
		String s = aString.trim();
		s.replaceAll( ",", "" );
		if ( s.startsWith( "$" ) )
		{
			s = s.substring( 1 );
		}
		try
		{
			theValue = new BigDecimal( s );
		}
		catch ( NumberFormatException exception )
		{
			throw new DollarFormatDeviation( ERROR_DOLLAR_BAD_FORMAT + aString );
		}

		return;
	}

	/**
	 * PhysicalID
	 *
	 * @param aString String
	 */
	private Dollar( String aString ) throws DollarFormatDeviation
	{
		fromString( aString );

	}

	public static Dollar parseString( String aString ) throws
		DollarFormatDeviation
	{
		return new Dollar( aString );
	}

	/**
	 * Indicates whether some other object is "equal to" this one.
	 *
	 * @param obj the reference object with which to compare.
	 * @return <code>true</code> if this object is the same as the obj argument;
	 *   <code>false</code> otherwise.
	 * @todo Implement this java.lang.Object method
	 */
	public boolean equals( Dollar aDollar )
	{
		return aDollar.theValue.equals( theValue );
	}

	void fromDouble( double value )
	{
		theValue = new BigDecimal( value );

	}

	/**
	 * Returns a string representation of the object.
	 *
	 * @return a string representation of the object.
	 * @todo Implement this java.lang.Object method
	 */
	public String toString()
	{
		return NumberFormat.getCurrencyInstance().format( theValue );
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
		return equals( ( Dollar ) obj );
	}

	private BigDecimal theValue = new BigDecimal( 0.0 );

	// Error messages
	static final String ERROR_DOLLAR_BAD_FORMAT =
		"Dollar - bad format ";

}
