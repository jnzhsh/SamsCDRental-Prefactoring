package com.samscdrental.model;

import com.samscdrental.failures.*;

/**
 * <p>Title: Sams CD Rental Store</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author Ken Pugh
 * @version 1.0
 */

public class CDCategory
	implements java.io.Serializable
{
	private static final long serialVersionUID = -1L; 

	static final int NEW_RELEASE_CD = 0;
	static final int GOLDIE_OLDIE_CD = 1;
	static final int REGULAR_CD = 2;
	static final int INVALID_CATEGORY = 3;
	public CDCategory()
	{

	}

	public CDCategory( int value )
	{
		if ( value >= NEW_RELEASE_CD && value < INVALID_CATEGORY )
		{
			theValue = value;
		}
		else
		{
			throw new IllegalArgumentException( ERROR_CD_CATEGORY_VALUE + value );
		}
	}

	public String toString()
	{
		return theStringValues[theValue];
	}

	public static CDCategory parseString( String aString ) throws
		CDCategoryFormatDeviation
	{
		CDCategory aCDCategory = new CDCategory();
		aCDCategory.fromString( aString );
		return aCDCategory;
	}

	private void fromString( String aString ) throws CDCategoryFormatDeviation
	{
		for ( int i = 0; i < theStringValues.length; i++ )
		{
			if ( aString.equals( theStringValues[i] ) )
			{
				theValue = theCorrespondingValues[i];
				return;
			}
		}
		throw new CDCategoryFormatDeviation( ERROR_CD_CATEGORY_VALUE );
	}

	/**
	 * Indicates whether some other object is "equal to" this one.
	 *
	 * @param obj the reference object with which to compare.
	 * @return <code>true</code> if this object is the same as the obj argument;
	 *   <code>false</code> otherwise.
	 * @todo Implement this java.lang.Object method
	 */
	public boolean equals( CDCategory aCDCategory )
	{
		return ( theValue == aCDCategory.theValue );
	}

	public boolean equals( Object obj )
	{
		return this.equals( ( CDCategory ) obj );
	}

	private static final String[] theStringValues =
		{
		"NewRelease", "GoldenOldie", "Regular", "InvalidCategory"};
	private static final int[] theCorrespondingValues =
		{
		NEW_RELEASE_CD, REGULAR_CD, GOLDIE_OLDIE_CD};
	private int theValue = INVALID_CATEGORY;

	// Error messages
	private static final String ERROR_CD_CATEGORY_VALUE =
		"Bad CDCategory value ";

}
