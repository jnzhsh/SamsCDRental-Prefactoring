package com.samscdrental.importexport;

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

public class CDDiscDataAccessImportExport
{
	public CDDiscDataAccessImportExport( CDDiscDataAccess aCDDiscCollection )
	{
		theCDDiscCollection = aCDDiscCollection;
	}

	public void addCDDiscsFromFile( String filename ) throws
		ImportFormatDeviation, ImportFileDeviation
	{
		DataAccessImportExportHelper importExportHelper =
			new DataAccessImportExportHelper();
		importExportHelper.addCDDiscsFromFile( filename, CDDISC_FILE,
											   new CDDiscAddToCollection() );
	}

	private class CDDiscAddToCollection
		implements AddFromFileInterface
	{
		public void addToCollection( String line ) throws ParseLineDeviation
		{
			CDDisc aCDDisc = CDDiscImportExport.parseLine( line );
			theCDDiscCollection.add( aCDDisc );
			return;

		}
	}

	private CDDiscDataAccess theCDDiscCollection;

	private static final String CDDISC_FILE = "CDDisc file ";

}
