package com.samscdrental.model;

import com.samscdrental.model.adt.*;

/**
 * <p>Title: Sams CD Rental Store</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author Ken Pugh
 * @version 1.0
 */

public class Rental
	implements java.io.Serializable
{
	private static final long serialVersionUID = -1L; 

	/**
	 * Rental
	 *
	 * @param theCustomer Customer
	 * @param baseRentalPeriodDays int
	 */
	public Rental( Customer aCustomer, int baseRentalPeriodDays,
				   Dollar rentalFee )
	{
		theCustomer = aCustomer;
		theBaseRentalPeriodDays = baseRentalPeriodDays;
		theRentalFee = rentalFee;
	}

	/**
	 * isOverdue
	 *
	 * @return boolean
	 */
	public boolean isOverdue()
	{
		if ( theEndTime == null )
		{
			// Not yet returned
			Timestamp now = new Timestamp();
			return now.differenceInDays( theStartTime ) >
				 theBaseRentalPeriodDays ;
		}
		else
		{
			// Not overdue, since it has been returned
			return false;
		}

	}

	/**
	 * checkIn
	 */
	public void checkIn()
	{
		theEndTime = new Timestamp();
	}

	/**
	 * isLateReturn
	 *
	 * @return boolean
	 */
	public boolean isLateReturn()
	{
		if ( theEndTime == null )
		{
			// Not yet returned, but they are asking about it anyway
			Timestamp now = new Timestamp();
			return now.differenceInDays( theStartTime ) >
				 theBaseRentalPeriodDays ;
		}
		else
		{
			// See if was late
			return theEndTime.differenceInDays( theStartTime ) >
				 theBaseRentalPeriodDays;
		}
	}

	public void setStartTime( Timestamp startTime )
	{
		theStartTime = startTime;
	}
	public Customer getCustomer()
	{
		return theCustomer;
	}

	public Timestamp getStartTime()
	{
		return theStartTime;
	}

	public Timestamp getEndTime()
	{
		return theEndTime;
	}

	public Dollar getRentalFee()
	{
		return theRentalFee;
	}

	public int getBaseRentalPeriodDays()
	{
		return theBaseRentalPeriodDays;
	}

	public Timestamp getDueTime()
	{
		return theStartTime.addDays( theBaseRentalPeriodDays );
	}

	private Customer theCustomer = null;
	private Timestamp theStartTime = new Timestamp();
	private Timestamp theEndTime = null;
	private Dollar theRentalFee = null;
	private int theBaseRentalPeriodDays;

}
