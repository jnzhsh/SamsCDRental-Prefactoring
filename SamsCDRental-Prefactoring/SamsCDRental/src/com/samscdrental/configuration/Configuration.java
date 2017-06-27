package com.samscdrental.configuration;

public class Configuration
{
	static public Configuration getInstance()
	{
		if ( theInstance == null )
		{
			theInstance = new Configuration();
		}
		return theInstance;

	}

	public DataAccessConfiguration getDataAccessConfiguration()
	{
		DataAccessConfiguration dac = new DataAccessConfiguration();
		// Could read from file
		dac.dataFilename = DATA_ACCESS_DATA_FILENAME;
		return dac;
	}

	public ReportConfiguration getReportConfiguration()
	{
		ReportConfiguration rc = new ReportConfiguration();
		// Could read this value from a file
		rc.printFilename = REPORT_PRINTER_FILENAME;
		return rc;
	}

	private static Configuration theInstance;

	private Configuration()
	{

	}

	private static String DATA_ACCESS_DATA_FILENAME =
		"c:/temp/StoreCollections.dat";
	private static String REPORT_PRINTER_FILENAME =
		"c:/temp/printer.txt";

}
