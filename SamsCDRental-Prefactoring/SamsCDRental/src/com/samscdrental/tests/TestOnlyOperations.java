package com.samscdrental.tests;

import com.samscdrental.dataaccess.*;
import com.samscdrental.model.*;
import com.samscdrental.model.adt.*;

/**
 * <p>Title: Sams CD Rental Store</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author Ken Pugh
 * @version 1.0
 */

public class TestOnlyOperations
{
	static TestOnlyOperations getInstance()
	{
		if ( theInstance == null )
		{
			theInstance = new TestOnlyOperations();
		}
		return theInstance;

	}

	void collectionsClear()
	{
		theCollections.theCDDiscDataAccess.removeAll_TestingOnly();
		theCollections.theCustomerDataAccess.removeAll_TestingOnly();
		theCollections.theCDReleaseDataAccess.removeAll_TestingOnly();
	}

	void setStartTimeForRentalBackSomeDays( PhysicalID aPhysicalID,
											int days )

	{
		CDDisc aCDDisc = theCollections.theCDDiscDataAccess.findByPhysicalID(
			aPhysicalID );
		if ( aCDDisc != null )
		{
			Rental rental = aCDDisc.getRental();
			Timestamp start = rental.getStartTime();
			start = start.addDays( -days );
			rental.setStartTime( start );
		}

	}

	private StoreDataAccess theCollections = StoreDataAccess.
		getInstance();

	private TestOnlyOperations()
	{
	}

	private static TestOnlyOperations theInstance = null;

}
