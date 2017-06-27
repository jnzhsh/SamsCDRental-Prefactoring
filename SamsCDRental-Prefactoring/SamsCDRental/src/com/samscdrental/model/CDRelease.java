package com.samscdrental.model;

import com.samscdrental.model.adt.*;

/**
 * <p>Title: Sams CD Rental Store</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author Ken Pugh
 * @version 1.0
 */

public class CDRelease
	implements java.io.Serializable
{
	private static final long serialVersionUID = -1L; 

	/*
	public void setCdCategory( CDCategory aCDCategory )
	{
		theCDCategory = aCDCategory;
	}

	public void setTitle( Name title )
	{
		theTitle = title;
	}

	public void setUPCCode( UPCCode aUPCCode )
	{
		theUPCCode = aUPCCode;
	}
*/
	public CDCategory getCdCategory()
	{
		return theCDCategory;
	}

	public Name getTitle()
	{
		return theTitle;
	}

	public UPCCode getUPCCode()
	{
		return theUPCCode;
	}



	public CDRelease( CDCategory aCDCategory, Name aTitle, UPCCode aUPCCode )
	{
		theCDCategory = aCDCategory;
		theTitle = aTitle;
		theUPCCode = aUPCCode;
	}

	int getBaseRentalPeriodDays()
	{
		return theCDCategoryValues.getBaseRentalPeriodDays( theCDCategory );
	}

	Dollar getRentalFee()
	{
		return theCDCategoryValues.getRentalFee( theCDCategory );
	}

	private CDCategory theCDCategory;
	private Name theTitle;
	private UPCCode theUPCCode;

	//
	private static final CDCategoryValues theCDCategoryValues =
		new CDCategoryValues();

}
