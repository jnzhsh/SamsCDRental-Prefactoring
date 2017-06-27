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

public class CDReleaseDataAccessImportExport
{

	public CDReleaseDataAccessImportExport( CDReleaseDataAccess
											aCDReleaseStateCollection )
	{
		theCDReleaseCollection = aCDReleaseStateCollection;
	}

	public void addCDReleasesFromFile( String filename ) throws
		ImportFormatDeviation, ImportFileDeviation
	{
		BufferedReader in = null;
		FileReader fileReader = null;
		StringBuffer errorString = new StringBuffer();
		boolean errorOccurred = false;

		try
		{
			fileReader = new FileReader( filename );
			in = new BufferedReader( fileReader );
		}
		catch ( FileNotFoundException exception )
		{
			throw new ImportFileDeviation(
				ERROR_OPENING_CDRELEASE_FILE +
				filename, exception );
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
						CDRelease aCDRelease = CDReleaseImportExport.parseLine(
							line );
						theCDReleaseCollection.add( aCDRelease );
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
			throw new ImportFileDeviation( ERROR_READING_CDRELEASE_FILE +
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
					ERROR_CLOSING_CDRELEASE_FILE +
					filename, exception );
			}
		}
		if ( errorOccurred )
		{
			throw new ImportFormatDeviation( errorString.toString() );
		}
		return;
	}

	private CDReleaseDataAccess theCDReleaseCollection;

// Error messages
	private static final String ERROR_OPENING_CDRELEASE_FILE =
		"Error opening CDRelease file ";
	private static final String ERROR_CLOSING_CDRELEASE_FILE =
		"Error closing CDRelease file";
	private static final String ERROR_READING_CDRELEASE_FILE =
		"Error reading CDRelease file";

}
