package com.samscdrental.importexport;

import java.io.*;

import com.samscdrental.failures.*;

/**
 * <p>Title: Sams CD Rental Store</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author Ken Pugh
 * @version 1.0
 */

public class DataAccessImportExportHelper
{

	public void addCDDiscsFromFile( String filename,
									String nameForErrors,
									AddFromFileInterface addFromFile ) throws
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
			throw new ImportFileDeviation( ERROR_OPENING + nameForErrors +
										   ": " + filename, exception );
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
						addFromFile.addToCollection( line );
					}
					catch ( ParseLineDeviation e )
					{
						errorString.append( e.getMessage() +
											System.getProperty( "line.separator" ) );
						errorOccurred = true;

					}
				}
			}
		}
		catch ( IOException exception )
		{
			throw new ImportFormatDeviation( ERROR_READING + nameForErrors +
											 ": " +
											 filename );

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
					ERROR_CLOSING + nameForErrors +
					": " + filename, exception );
			}
		}
		if ( errorOccurred )
		{
			throw new ImportFormatDeviation( errorString.toString() );
		}
		return;
	}

	private static final String ERROR_OPENING =
		"Error opening ";
	private static final String ERROR_CLOSING =
		"Error closing ";
	private static final String ERROR_READING =
		"Error reading ";

}
