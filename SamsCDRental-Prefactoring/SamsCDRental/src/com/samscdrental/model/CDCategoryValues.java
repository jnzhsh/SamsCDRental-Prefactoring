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

public class CDCategoryValues
{

	private class CDCategoryInternalValues
	{
		CDCategoryInternalValues( int aCDCategory,
								  int aBaseRentalPeriodDays,
								  Dollar aRentalFee )
		{
			theCDCategory = new CDCategory( aCDCategory );
			theBaseRentalPeriodDays = aBaseRentalPeriodDays;
			theRentalFee = aRentalFee;

		}

		CDCategory theCDCategory;
		int theBaseRentalPeriodDays;
		Dollar theRentalFee;
	}

	private CDCategoryInternalValues[] theCDCategoryValues =
		{
		new CDCategoryInternalValues( CDCategory.NEW_RELEASE_CD,
									  2, new Dollar( 3.00 ) ),
		new CDCategoryInternalValues( CDCategory.GOLDIE_OLDIE_CD,
									  4, new Dollar( 2.00 ) ),
		new CDCategoryInternalValues( CDCategory.REGULAR_CD,
									  3, new Dollar( 2.50 ) )
	};

	int getBaseRentalPeriodDays( CDCategory cdCategory )
	{
		for ( int i = 0; i < theCDCategoryValues.length; i++ )
		{
			if ( theCDCategoryValues[i].theCDCategory.equals( cdCategory ) )
			{
				return theCDCategoryValues[i].theBaseRentalPeriodDays;
			}
		}
		return 0;
	}

	Dollar getRentalFee( CDCategory cdCategory )
	{
		for ( int i = 0; i < theCDCategoryValues.length; i++ )
		{
			if ( theCDCategoryValues[i].theCDCategory.equals( cdCategory ) )
			{
				return theCDCategoryValues[i].theRentalFee;
			}
		}
		return new Dollar( 0 );
	}
}
