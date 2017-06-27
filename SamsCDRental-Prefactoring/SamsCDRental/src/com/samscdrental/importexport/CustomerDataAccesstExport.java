package com.samscdrental.importexport;

import java.io.*;

import com.samscdrental.dataaccess.*;
import com.samscdrental.failures.*;
import com.samscdrental.model.*;

/**
 * <p>Title: Sams CD Rental Store</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author Ken Pugh
 * @version 1.0
 */

public class CustomerDataAccesstExport
{

	public CustomerDataAccesstExport( CustomerDataAccess
									  aCustomerStateCollection )
	{
		theCustomerCollection = aCustomerStateCollection;
	}

	public void addCustomersFromFile( String filename ) throws
		ImportFormatDeviation, ImportFileDeviation
	{
		BufferedReader in = null;
		FileReader aFileReader = null;
		StringBuffer errorString = new StringBuffer();
		boolean errorOccurred = false;

		try
		{
			aFileReader = new FileReader( filename );
			in = new BufferedReader( aFileReader );
		}
		catch ( FileNotFoundException exception )
		{
			throw new ImportFileDeviation(
				ERROR_OPENING_CUSTOMER_FILE + filename, exception );
		}
		try
		{
			String line = null;
			// Keep reading till the end of the file
			while ( ( line = in.readLine() ) != null )
			{
				if ( line.length() > 0 )
				{
					try
					{
						Customer aCustomer =
							CustomerImportExport.parseLine( line );
						theCustomerCollection.add( aCustomer );

					}
					catch ( ParseLineDeviation e )
					{
						errorString.append( e.getMessage() );
						errorOccurred = true;

					}
				}
			}
		}

		catch ( IOException exception )
		{
			throw new ImportFileDeviation( ERROR_READING_CUSTOMER_FILE +
										   filename, exception );
		}
		finally
		{
			try
			{
				in.close();
			}
			catch ( IOException exception )
			{
				throw new SeriousErrorException(
					ERROR_CLOSING_CUSTOMER_FILE +
					filename, exception );
			}
		}
		if ( errorOccurred )
		{
			throw new ImportFormatDeviation( errorString.toString() );
		}
		return;
	}

	private CustomerDataAccess theCustomerCollection;

// Error messages
	private static final String ERROR_OPENING_CUSTOMER_FILE =
		"Error opening customer file ";
	private static final String ERROR_CLOSING_CUSTOMER_FILE =
		"Error closing customer file";
	private static final String ERROR_READING_CUSTOMER_FILE =
		"Error reading customer file";

}
