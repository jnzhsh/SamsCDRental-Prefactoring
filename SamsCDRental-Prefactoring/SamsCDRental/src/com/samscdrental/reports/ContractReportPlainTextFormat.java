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

public class ContractReportPlainTextFormat
	implements ReportPlainTextFormat
{
	private String theReportString;
	public String getReportString()
	{
		return theReportString;
	}

	public ContractReportPlainTextFormat( RentalContractDTO rentalContractDTO )
	{
		String nl = System.getProperty( "line.separator" );
		theReportString =
			"Sam's CD Rental Store Contract" + nl +
			"Name: " + rentalContractDTO.theCustomerName + nl +
			"Date: " + rentalContractDTO.theRentalStartTime + nl +
			"DUE:  " + rentalContractDTO.theRentalDueTime + nl +
			"Title:" + rentalContractDTO.theCDReleaseTitle + nl +
			"ID:   " + rentalContractDTO.theCDDiscPhysicalID + nl +
			"Fee:  " + rentalContractDTO.theRentalFee + nl;

	}

}
