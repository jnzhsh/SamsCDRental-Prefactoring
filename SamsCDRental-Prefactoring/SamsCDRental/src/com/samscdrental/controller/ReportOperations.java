package com.samscdrental.controller;

import com.samscdrental.dataaccess.*;
import com.samscdrental.model.*;
import com.samscdrental.model.adt.*;
import com.samscdrental.model.dto.*;
import com.samscdrental.reports.*;

public class ReportOperations
{
	static public ReportOperations getInstance()
	{
		if ( theInstance == null )
		{
			theInstance = new ReportOperations();
		}
		return theInstance;

	}

	public CDDiscInventoryReportPlainTextFormat
		makeCDDiscInventoryReportPlainTextFormat()
	{
		CDDiscInventoryDTO aCDDiscInventoryDTO =
			makeCDDiscInventoryDTO();
		CDDiscInventoryReportPlainTextFormat irptf =
			new CDDiscInventoryReportPlainTextFormat( aCDDiscInventoryDTO );
		return irptf;
	}

	public CDDiscInventoryDTO makeCDDiscInventoryDTO()
	{
		CDDiscInventoryDTO cdidto = new CDDiscInventoryDTO();
		CDDisc[] cdDiscs = collections.theCDDiscDataAccess.findAll();
		cdidto.theCDDiscInventoryItems = new CDDiscInventoryItem[cdDiscs.length];
		for ( int i = 0; i < cdDiscs.length; i++ )
		{
			CDDisc aCDDisc = cdDiscs[i];
			Rental aRental = aCDDisc.getRental();
			CDDiscInventoryItem dii = new CDDiscInventoryItem();
			dii.isCDDiscRented = aCDDisc.isRented();
			dii.theCDDiscPhysicalID = aCDDisc.getPhysicalID();
			if ( aCDDisc.isRented() )
			{
				dii.theCustomerID = aRental.getCustomer().getCustomerID();
			}
			else
			{
				dii.theCustomerID = new CustomerID();
			}
			cdidto.theCDDiscInventoryItems[i] = dii;
		}
		return cdidto;

	}

	private ReportOperations()
	{
	}

	private StoreDataAccess collections = StoreDataAccess.getInstance();
	private static ReportOperations theInstance = null;

}
