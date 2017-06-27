package com.samscdrental.model.adt;

import java.util.*;

/**
 * <p>Title: Sams CD Rental Store</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author Ken Pugh
 * @version 1.0
 */

public class Timestamp
	implements java.io.Serializable
{

	private static final long serialVersionUID = -1L; 

	public Timestamp()
	{
		// Will be initialized to the current time
		theDate = new Date();
	}

	/**
	 * subtract
	 *
	 * @param aTimestamp Timestamp
	 * @return int - Number of Days
	 */
	public int differenceInDays( Timestamp aTimestamp )
	{
		long thisMilliseconds = this.theDate.getTime();
		long thatMilliseconds = aTimestamp.theDate.getTime();
		long millisecondsDifference = thisMilliseconds - thatMilliseconds;
		int days = ( int ) ( millisecondsDifference /
							 NUMBER_MILLISECONDS_IN_DAY );

		return days;

	}

	/**
	 * add
	 *
	 * @param days int
	 * @return Timestamp
	 */
	public Timestamp addDays( int days )
	{
		long thisMilliseconds = this.theDate.getTime();
		long thatMilliseconds = thisMilliseconds +
			days * NUMBER_MILLISECONDS_IN_DAY;

		Timestamp aTimestamp = new Timestamp();
		aTimestamp.theDate = new Date( thatMilliseconds );
		return aTimestamp;
	}

	/**
	 * Returns a string representation of the object.
	 *
	 * @return a string representation of the object.
	 * @todo Implement this java.lang.Object method
	 */
	public String toString()
	{
		return theDate.toString();
	}

	/**
	 * Indicates whether some other object is "equal to" this one.
	 *
	 * @param obj the reference object with which to compare.
	 * @return <code>true</code> if this object is the same as the obj argument;
	 *   <code>false</code> otherwise.
	 * @todo Implement this java.lang.Object method
	 */
	public boolean equals( Timestamp aTimestamp )
	{
		return theDate.equals( aTimestamp.theDate );
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
		return equals( ( Timestamp ) obj );
	}

	private Date theDate;
	private static final long NUMBER_MILLISECONDS_IN_DAY = 1000 * 24 * 60 * 60;

}
