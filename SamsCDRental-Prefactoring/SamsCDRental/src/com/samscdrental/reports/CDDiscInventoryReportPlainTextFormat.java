package com.samscdrental.reports;

import com.samscdrental.model.dto.*;

public class CDDiscInventoryReportPlainTextFormat
	implements ReportPlainTextFormat

{
	public CDDiscInventoryReportPlainTextFormat()
	{
	}

	private String theReportString;
	public String getReportString()
	{
		return theReportString;
	}

	public CDDiscInventoryReportPlainTextFormat( CDDiscInventoryDTO
												 aCDDiscInventoryDTO )
	{
		String nl = System.getProperty( "line.separator" );

		StringBuffer stringBuffer = new StringBuffer(); ;

		stringBuffer.append( "Sam's CD Rental Store Inventory" + nl );
		for ( int i = 0; i < aCDDiscInventoryDTO.theCDDiscInventoryItems.length;
			  i++ )
		{
			CDDiscInventoryItem dii = aCDDiscInventoryDTO.
				theCDDiscInventoryItems[i];
			if ( dii.isCDDiscRented )
			{
				stringBuffer.append( dii.theCDDiscPhysicalID + " " +
									 dii.theCustomerID + nl );
			}
			else
			{
				stringBuffer.append( dii.theCDDiscPhysicalID +
									 " " + "In house" + nl );
			}
		}
		theReportString = stringBuffer.toString();
	}

}
