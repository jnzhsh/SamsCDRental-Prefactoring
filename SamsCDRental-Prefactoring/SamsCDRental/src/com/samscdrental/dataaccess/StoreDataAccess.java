package com.samscdrental.dataaccess;

import java.io.*;

import com.samscdrental.configuration.*;
import com.samscdrental.failures.*;

/**
 * <p>Title: Sams CD Rental Store</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author Ken Pugh
 * @version 1.0
 */

public class StoreDataAccess
{

	public CDDiscDataAccess theCDDiscDataAccess
		= new CDDiscDataAccess();
	public CustomerDataAccess theCustomerDataAccess
		= new CustomerDataAccess();
	public CDReleaseDataAccess theCDReleaseDataAccess
		= new CDReleaseDataAccess();
	public static StoreDataAccess getInstance()
	{
		if ( theCollections == null )
		{
			theCollections = new StoreDataAccess();
		}
		return theCollections;
	}

	public static StoreDataAccess makeNew()
	{
		theCollections = new StoreDataAccess( true );

		return theCollections;

	}

	private StoreDataAccess( boolean other )
	{
		Configuration configuration = Configuration.getInstance();
		DataAccessConfiguration dac = configuration.getDataAccessConfiguration();
		DATA_FILENAME = dac.dataFilename;
		theCDDiscDataAccess = new CDDiscDataAccess();
		theCustomerDataAccess = new CustomerDataAccess();
		theCDReleaseDataAccess = new CDReleaseDataAccess();
	}

	private StoreDataAccess()
	{

		Configuration configuration = Configuration.getInstance();
		DataAccessConfiguration dac = configuration.getDataAccessConfiguration();
		DATA_FILENAME = dac.dataFilename;
		// Read the serialized file
		FileInputStream fileinput = null;
		ObjectInputStream input = null;
		try
		{
			fileinput = new FileInputStream( DATA_FILENAME );
			input = new ObjectInputStream( fileinput );
		}
		catch ( FileNotFoundException exception )
		{
			throw new SeriousErrorException(
				ERROR_OPENING_FILE + DATA_FILENAME, exception );
		}
		catch ( IOException exception )
		{
			throw new SeriousErrorException(
				ERROR_CREATING_OBJECT_STREAM + DATA_FILENAME, exception );

		}

		theCDDiscDataAccess =
			( CDDiscDataAccess ) DataAccessHelper.readObjectFromOpenStream(
			theCDDiscDataAccess.getClass(), input );
		theCustomerDataAccess =
			( CustomerDataAccess ) DataAccessHelper.
			readObjectFromOpenStream(
			theCustomerDataAccess.getClass(), input );
		theCDReleaseDataAccess =
			( CDReleaseDataAccess ) DataAccessHelper.
			readObjectFromOpenStream(
			theCDReleaseDataAccess.getClass(), input );

		try
		{
			input.close();
		}
		catch ( IOException exception )
		{
			throw new SeriousErrorException(
				ERROR_STREAM_NOT_CLOSED + DATA_FILENAME, exception );
		}
	}

	/**
	 * dispose
	 */
	public void dispose()
	{
		try
		{
			FileOutputStream fileoutput = new FileOutputStream(
				DATA_FILENAME );
			ObjectOutputStream output = new ObjectOutputStream( fileoutput );
			DataAccessHelper.writeObjectToOpenStream(
				theCDDiscDataAccess, output );
			DataAccessHelper.writeObjectToOpenStream(
				theCustomerDataAccess, output );
			DataAccessHelper.writeObjectToOpenStream(
				theCDReleaseDataAccess, output );
			output.close();
		}
		catch ( FileNotFoundException exception )
		{
			throw new SeriousErrorException(
				ERROR_OPENING_FILE + DATA_FILENAME, exception );
		}
		catch ( IOException exception )
		{
			throw new SeriousErrorException(
				ERROR_CREATING_OBJECT_STREAM + DATA_FILENAME, exception );
		}

	}

	// This is a singleton class
	private static StoreDataAccess theCollections = null;

	private static final String ERROR_OPENING_FILE =
		"Error opening  file ";
	private static final String ERROR_CREATING_OBJECT_STREAM
		= "Unable to create or access Object stream ";
	private static final String ERROR_STREAM_NOT_CLOSED
		= "Stream not closed ";

	private static String DATA_FILENAME;

}
