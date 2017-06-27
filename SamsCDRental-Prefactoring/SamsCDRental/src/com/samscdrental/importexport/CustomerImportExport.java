package com.samscdrental.importexport;

import com.samscdrental.failures.*;
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

public class CustomerImportExport
{

	/**
	 * parseLine
	 *
	 * @param aString String
	 */
	public static Customer parseLine( String line ) throws ParseLineDeviation
	{
		// The line is expected to be in <customerID> <name> format

		String[] tokens = line.split( "\\|" );
		if ( tokens.length < 2 )
		{
			throw new ParseLineDeviation( ERROR_CUSTOMER_TOKEN_COUNT +
										  tokens.length );
		}
		String customerIDString = tokens[0];
		String customerNameString = tokens[1];
		try
		{
			CustomerID aCustomerID = CustomerID.parseString( customerIDString );
			if ( customerNameString.length() < 1 )
			{
				throw new ParseLineDeviation( ERROR_NO_NAME + line );
			}
			Name aName = Name.parseString( customerNameString );

			return new Customer( aName, aCustomerID );
		}
		catch ( CustomerIDFormatDeviation e )
		{
			throw new ParseLineDeviation( e.getMessage() +
										  customerIDString );
		}
		catch ( NameFormatDeviation e )
		{
			throw new ParseLineDeviation( e.getMessage() +
										  customerNameString );
		}
	}

	// Error messages
	private static final String ERROR_NO_NAME =
		"No name for customer ";
	private static final String ERROR_CUSTOMER_TOKEN_COUNT =
		"Customer line has wrong element count of ";

}
