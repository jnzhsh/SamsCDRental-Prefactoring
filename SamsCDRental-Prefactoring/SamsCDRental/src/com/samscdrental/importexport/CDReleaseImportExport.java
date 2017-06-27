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

public class CDReleaseImportExport
{

	/**
	 * parseLine
	 *
	 * @param aString String
	 */
	public static CDRelease parseLine( String line ) throws ParseLineDeviation
	{
		// The line is expected to be in <UPCCode><categoryid> <title>

		String[] tokens = line.split( "\\|" );
		if ( tokens.length < 3 )
		{
			throw new ParseLineDeviation( ERROR_CDRELEASE_TOKEN_COUNT +
										  tokens.length );
		}

		String upcCodeString = tokens[0];
		String categoryIDString = tokens[1];
		String titleString = tokens[2];

		try
		{
			UPCCode aUPCCode = UPCCode.parseString( upcCodeString );

			CDCategory aCDCategory = CDCategory.parseString( categoryIDString );

			if ( titleString.length() < 1 )

			{
				throw new ParseLineDeviation( ERROR_TITLE + line );
			}
			Name aTitle = Name.parseString( titleString );
			return new CDRelease( aCDCategory, aTitle, aUPCCode );
		}

		catch ( CDCategoryFormatDeviation e )
		{
			throw new ParseLineDeviation( e.getMessage()
										  + categoryIDString );
		}
		catch ( UPCCodeFormatDeviation e )
		{
			throw new ParseLineDeviation( e.getMessage()
										  + upcCodeString );
		}
		catch ( NameFormatDeviation e )
		{
			throw new ParseLineDeviation( e.getMessage()
										  + titleString );
		}

	}

	// Error messages from the parse
	private static final String ERROR_TITLE =
		"No title";
	private static final String ERROR_CDRELEASE_TOKEN_COUNT =
		"CDRelease line has wrong element count of ";

}
