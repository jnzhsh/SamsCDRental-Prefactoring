package com.samscdrental.reports;

import com.samscdrental.controller.*;
import com.samscdrental.failures.*;

public class CDDiscInventoryMain
{
	public static void main( String[] args )
	{
		try
		{
			ReportOperations aReportOperations = ReportOperations.getInstance();
			CDDiscInventoryReportPlainTextFormat dirptf = aReportOperations.
				makeCDDiscInventoryReportPlainTextFormat();
			Reports.printReport( dirptf );
		}
		catch ( PrinterFailureDeviation e )
		{

			System.out.println( e.getMessage() );
		}

	}

}
