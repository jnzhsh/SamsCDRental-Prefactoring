package com.samscdrental.reports;

import com.samscdrental.model.dto.*;

/**
 * <p>Title: Sams CD Rental Store</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author Ken Pugh
 * @version 1.0
 */

public class OverdueRentalReportPlainTextFormat
	implements ReportPlainTextFormat
{
	private String theReportString;
	public String getReportString()
	{
		return theReportString;
	}

	public OverdueRentalReportPlainTextFormat( OverdueRentalDTO
											   aOverdueRentalContractDTO )
	{
		String nl = System.getProperty( "line.separator" );
		theReportString =
			"LATE RETURN REPORT" + nl +
			"Name:     " + aOverdueRentalContractDTO.theCustomerName + nl +
			"Date:     " + aOverdueRentalContractDTO.theRentalStartTime + nl +
			"Due:      " + aOverdueRentalContractDTO.theRentalDueTime + nl +
			"Returned: " + aOverdueRentalContractDTO.theRentalEndTime + nl +
			"Title:    " + aOverdueRentalContractDTO.theCDReleaseTitle + nl +
			"ID:       " + aOverdueRentalContractDTO.theCDDiscPhysicalID + nl +
			"Fee:      " + aOverdueRentalContractDTO.theOverdueFee + nl;

	}

}
