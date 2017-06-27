package com.samscdrental.model;

import com.samscdrental.failures.*;
import com.samscdrental.model.adt.*;
import com.samscdrental.model.dto.*;

/**
 * <p>Title: Sams CD Rental Store</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author Ken Pugh
 * @version 1.0
 */

public class CDDisc
	implements java.io.Serializable
{
	private static final long serialVersionUID = -1L; 

	public CDDisc( CDRelease aCDRelease, PhysicalID aPhysicalID )
	{
		theCDRelease = aCDRelease;
		thePhysicalID = aPhysicalID;
	}

	public Rental getRental()
	{
		return theRental;
	}
/*
	public void setCdRelease( CDRelease aCDRelease )
	{
		theCDRelease = aCDRelease;
	}

	public void setPhysicalID( PhysicalID aPhysicalID )
	{
		thePhysicalID = aPhysicalID;
	}
*/
	public CDRelease getCdRelease()
	{
		return theCDRelease;
	}

	public PhysicalID getPhysicalID()
	{
		return thePhysicalID;
	}

	/**
	 * isRented
	 *
	 * @return boolean
	 */
	public boolean isRented()
	{
		return ( theRental != null );

	}

	/**
	 * start_rental
	 *
	 * @param theCustomer Customer
	 */
	public RentalContractDTO startRental( Customer aCustomer ) throws
		CheckOutDeviation
	{
		int aBaseRentalPeriodDays =
			theCDRelease.getBaseRentalPeriodDays();
		Dollar rentalFee = theCDRelease.getRentalFee();
		if ( theRental != null )
		{
			throw new CheckOutDeviation( ERROR_CDDISC_ALREADY_RENTED );
		}
		theRental = new Rental( aCustomer, aBaseRentalPeriodDays, rentalFee );

		RentalContractDTO aRentalContractDTO = makeRentalContractDTO( aCustomer );
		return aRentalContractDTO;
	}

	/**
	 * endRental
	 */
	public void endRental() throws CheckInDeviation, LateReturnDeviation
	{
		if ( theRental == null )
		{
			throw new CheckInDeviation( ERROR_ENDING_NON_STARTED_RENTAL );
		}
		else
		{
			theRental.checkIn();
			if ( theRental.isLateReturn() )
			{
				OverdueRentalDTO aOverdueRentalDTO = makeOverdueRentalDTO();
				theRental = null;
				throw new LateReturnDeviation( aOverdueRentalDTO );
			}
		}
		theRental = null;
		return;
	}

	/**
	 * makeOverdueRentalDTO
	 */
	private RentalContractDTO makeRentalContractDTO( Customer aCustomer )
	{
		RentalContractDTO aRentalContractDTO = new RentalContractDTO();
		aRentalContractDTO.theCDDiscPhysicalID = this.thePhysicalID;
		aRentalContractDTO.theCDReleaseTitle = this.theCDRelease.getTitle();
		aRentalContractDTO.theCustomerName = aCustomer.getName();
		aRentalContractDTO.theRentalDueTime = this.theRental.getDueTime();
		aRentalContractDTO.theRentalFee = this.theRental.getRentalFee();
		aRentalContractDTO.theRentalStartTime = this.theRental.getStartTime();
		return aRentalContractDTO;
	}

	/**
	 * makeOverdueRentalDTO
	 */

	private OverdueRentalDTO makeOverdueRentalDTO()
	{
		OverdueRentalDTO aOverdueRentalDTO = new OverdueRentalDTO();
		aOverdueRentalDTO.theCDDiscPhysicalID = this.thePhysicalID;
		aOverdueRentalDTO.theCDReleaseTitle = this.theCDRelease.
			getTitle();
		aOverdueRentalDTO.theCustomerID = this.theRental.getCustomer().
			getCustomerID();
		aOverdueRentalDTO.theCustomerName = this.theRental.getCustomer().
			getName();
		aOverdueRentalDTO.theOverdueFee = OVERDUE_FEE;
		aOverdueRentalDTO.theRentalDueTime = this.theRental.getDueTime();
		aOverdueRentalDTO.theRentalEndTime = this.theRental.getEndTime();
		aOverdueRentalDTO.theRentalStartTime = this.theRental.
			getStartTime();
		return aOverdueRentalDTO;
	}

	private CDRelease theCDRelease;
	private PhysicalID thePhysicalID;
	private Rental theRental = null;
	private static final Dollar OVERDUE_FEE = new Dollar( 5.00 );

// Error messages
	private static final String ERROR_ENDING_NON_STARTED_RENTAL =
		"Ending a rental that has not started";
	private static final String ERROR_CDDISC_ALREADY_RENTED =
		"CDDisc is already rented";

}
