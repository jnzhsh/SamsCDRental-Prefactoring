package com.samscdrental.importexport;

import com.samscdrental.dataaccess.*;
import com.samscdrental.failures.*;
import com.samscdrental.model.*;
import com.samscdrental.model.adt.*;

public class CDDiscImportExport
{

	/**
	 * parseLine
	 *
	 * @param aString String
	 */
	public static CDDisc parseLine( String line ) throws ParseLineDeviation
	{
		// The line is expected to be in <PhysicalID> <UPCCode of CDRelease> format
		String[] tokens = line.split( "\\|" );
		if ( tokens.length < 2 )
		{
			throw new ParseLineDeviation( ERROR_CDDISC_TOKEN_COUNT +
										  tokens.length );
		}

		String cdDiscPhysicalIDString = tokens[0];
		String cdDiscReleaseUPCCodeString = tokens[1];
		try
		{
			PhysicalID aPhysicalID = PhysicalID.parseString(
				cdDiscPhysicalIDString );
			UPCCode aUPCCode = UPCCode.parseString( cdDiscReleaseUPCCodeString );

			StoreDataAccess collections = StoreDataAccess.getInstance();
			CDRelease aCDRelease = collections.theCDReleaseDataAccess.
				findByUPCCode( aUPCCode );
			if ( aCDRelease == null )
			{
				throw new ParseLineDeviation( ERROR_CDRELEASE_NOT_FOUND +
											  aUPCCode );
			}
			return new CDDisc( aCDRelease, aPhysicalID );
		}
		catch ( PhysicalIDFormatDeviation e )
		{
			throw new ParseLineDeviation( e.getMessage() );
		}
		catch ( UPCCodeFormatDeviation e )
		{
			throw new ParseLineDeviation( e.getMessage() );
		}

	}

	private static final String ERROR_CDDISC_TOKEN_COUNT =
		"CDDisc line has wrong element count of ";
	private static final String ERROR_CDRELEASE_NOT_FOUND =
		"CDRelease Not Found for CDDisc ";

}
