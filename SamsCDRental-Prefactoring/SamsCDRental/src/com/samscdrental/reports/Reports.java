package com.samscdrental.reports;

import java.io.*;

import com.samscdrental.configuration.*;
import com.samscdrental.failures.*;
import com.samscdrental.model.dto.*;

/**
 * <p>Title: Sams CD Rental Store</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author Ken Pugh
 * @version 1.0
 */

public class Reports
{

	public static void printReport( ReportPlainTextFormat
									aReportPlainTextFormat ) throws
		PrinterFailureDeviation

	{
		FileWriter output = null;
		try
		{
			output = new FileWriter( PRINTER_FILENAME );
		}
		catch ( IOException exception )
		{
			throw new PrinterFailureDeviation( ERROR_CANNOT_OPEN_PRINTER + " " +
											   PRINTER_FILENAME +
											   exception.getMessage() );
		}
		try
		{
			output.write( aReportPlainTextFormat.getReportString() );

		}
		catch ( IOException exception )
		{
			throw new PrinterFailureDeviation( ERROR_CANNOT_WRITE_TO_PRINTER +
											   exception.getMessage() );
		}

		finally
		{
			try
			{
				output.close();
			}
			catch ( IOException exception )
			{
				throw new PrinterFailureDeviation( ERROR_CANNOT_CLOSE_PRINTER +
					exception.getMessage() );
			}
		}

	}

	/**
	 * printContractReport
	 *
	 * @param aRentalContractDTO RentalContractDTO
	 */
	public static ContractReportPlainTextFormat createContractReport(
		RentalContractDTO
		aRentalContractDTO )
	{
		ContractReportPlainTextFormat aContractReportPlainTextFormat
			= new ContractReportPlainTextFormat( aRentalContractDTO );
		return aContractReportPlainTextFormat;
	}

	public static OverdueRentalReportPlainTextFormat createOverdueRentalReport(
		OverdueRentalDTO
		aOverdueRentalDTO )
	{
		OverdueRentalReportPlainTextFormat aOverdueRentalReportPlainTextFormat
			= new OverdueRentalReportPlainTextFormat( aOverdueRentalDTO );
		return aOverdueRentalReportPlainTextFormat;
	}

	private Reports()
	{

	}

	private static String getFilename()
	{
		Configuration configuration = Configuration.getInstance();
		ReportConfiguration rc = configuration.getReportConfiguration();
		return rc.printFilename;
	}

	private static String PRINTER_FILENAME = getFilename();

	private static final String ERROR_CANNOT_OPEN_PRINTER =
		"Cannot open  printer file";
	private static final String ERROR_CANNOT_WRITE_TO_PRINTER =
		"Cannot write to  printer file";
	private static final String ERROR_CANNOT_CLOSE_PRINTER =
		"Cannot close  printer file";

}
