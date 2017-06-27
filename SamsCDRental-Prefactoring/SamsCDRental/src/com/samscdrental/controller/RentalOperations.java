package com.samscdrental.controller;

import com.samscdrental.dataaccess.*;
import com.samscdrental.failures.*;
import com.samscdrental.model.*;
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

public class RentalOperations
{
	// Error messages

	static public RentalOperations getInstance()
	{
		if ( theInstance == null )
		{
			theInstance = new RentalOperations();
		}
		return theInstance;

	}

	/**
	 * checkinCDDisc
	 *
	 * @param aPhysicalID PhysicalID
	 */
	public void checkinCDDisc( PhysicalID aPhysicalID ) throws CheckInDeviation,
		LateReturnDeviation
	{
		CDDisc aCDDisc = collections.theCDDiscDataAccess.
			findByPhysicalID( aPhysicalID );
		if ( aCDDisc != null )
		{
			aCDDisc.endRental();
		}
		else
		{
			throw new CheckInDeviation( ERROR_CD_DISC_NOT_IN_COLLECTION );
		}
	}

	/**
	 * isCDDiscRented
	 *
	 * @param aPhysicalID PhysicalID
	 * @return boolean
	 */
	public boolean isCDDiscRented( PhysicalID aPhysicalID ) throws
		StatusDeviation
	{
		CDDisc aCDDisc = collections.theCDDiscDataAccess.
			findByPhysicalID( aPhysicalID );
		if ( aCDDisc != null )
		{
			return ( aCDDisc.isRented() );
		}
		else
		{
			throw new StatusDeviation( ERROR_CD_DISC_NOT_IN_COLLECTION );

		}
	}

	/**
	 * checkoutCDDisc
	 *
	 * @param anObject PhysicalID
	 */
	public RentalContractDTO checkoutCDDisc( PhysicalID aPhysicalID,
											 CustomerID aCustomerID ) throws
		CheckOutDeviation
	{
		CDDisc aCDDisc = collections.theCDDiscDataAccess.
			findByPhysicalID( aPhysicalID );
		Customer aCustomer = collections.theCustomerDataAccess.
			findByCustomerID( aCustomerID );
		if ( aCDDisc == null )
		{
			throw new CheckOutDeviation(
				ERROR_CD_DISC_NOT_IN_COLLECTION );
		}
		if ( aCustomer == null )
		{
			throw new CheckOutDeviation(
				ERROR_CUSTOMER_NOT_IN_COLLECTION );
		}
		return aCDDisc.startRental( aCustomer );
	}

	/**
	 * dispose
	 */
	public void dispose()
	{
		collections.dispose();
	}

	private static final String ERROR_CD_DISC_NOT_IN_COLLECTION =
		"CDDisc PhysicalID not in collection";
	private static final String ERROR_CUSTOMER_NOT_IN_COLLECTION =
		"Customer ID not in collection";

	private StoreDataAccess collections = StoreDataAccess.getInstance();

	private RentalOperations()
	{

	}

	private static RentalOperations theInstance = null;

}
