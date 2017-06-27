package com.samscdrental.migration;

import com.samscdrental.controller.*;
import com.samscdrental.dataaccess.*;
import com.samscdrental.failures.*;

public class MigrateMain
{
	public static void main( String[] args )
	{
		setupFilenames( args );
		try
		{
			StoreDataAccess aStoreDataAccess = StoreDataAccess.makeNew();
			MaintenanceOperations aMaintenanceOperations =
				MaintenanceOperations.getInstance();
			aMaintenanceOperations.collectionsInitialize(
				CUSTOMER_FILENAME,
				CDDISC_FILENAME,
				CDRELEASE_FILENAME );

			aStoreDataAccess.dispose();
		}
		catch ( ImportFileDeviation e )
		{
			System.out.println( e.getMessage() );
		}
		catch ( ImportFormatDeviation e )
		{
			System.out.println( e.getMessage() );
		}
	}

	private static void setupFilenames( String[] args )
	{
		if ( args.length == 0 )
		{
			return;
		}
		if ( args.length < 3 )
		{
			System.out.println( "You must specify 3 filenames" +
								"- CDRelease, CDDisc, Customer" );
			return;
		}
		CDRELEASE_FILENAME = args[0];
		CDDISC_FILENAME = args[1];
		CUSTOMER_FILENAME = args[2];

	}

	private static String CDRELEASE_FILENAME =
		"/SamsCDRental/cdrelease.txt";
	private static String CDDISC_FILENAME = "/SamsCDRental/cddisc.txt";
	private static String CUSTOMER_FILENAME = "/SamsCDRental/customer.txt";

}
