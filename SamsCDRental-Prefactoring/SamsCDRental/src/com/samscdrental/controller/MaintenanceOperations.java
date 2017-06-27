package com.samscdrental.controller;

import com.samscdrental.dataaccess.*;
import com.samscdrental.failures.*;
import com.samscdrental.importexport.*;

/**
 * <p>Title: Sams CD Rental Store</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author Ken Pugh
 * @version 1.0
 */
public class MaintenanceOperations
{
	static public MaintenanceOperations getInstance()
	{
		if ( theInstance == null )
		{
			theInstance = new MaintenanceOperations();
		}
		return theInstance;

	}

	public void collectionsInitialize(
		String customerFilename, String cdDiscFilename,
		String cdReleaseFilename ) throws ImportFormatDeviation,
		ImportFileDeviation
	{
		CustomerDataAccesstExport customerImportExport =
			new CustomerDataAccesstExport(
			collections.theCustomerDataAccess );
		customerImportExport.
			addCustomersFromFile( customerFilename );
		CDReleaseDataAccessImportExport cdReleaseImportExport =
			new CDReleaseDataAccessImportExport(
			collections.theCDReleaseDataAccess );
		cdReleaseImportExport.
			addCDReleasesFromFile( cdReleaseFilename );
		CDDiscDataAccessImportExport discImportExport =
			new CDDiscDataAccessImportExport(
			collections.theCDDiscDataAccess );
		discImportExport.addCDDiscsFromFile( cdDiscFilename );
		return;
	}

	private MaintenanceOperations()
	{
	}

	private static MaintenanceOperations theInstance = null;
	private StoreDataAccess collections = StoreDataAccess.getInstance();

}
