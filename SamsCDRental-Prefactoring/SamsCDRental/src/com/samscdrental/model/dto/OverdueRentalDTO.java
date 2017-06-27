package com.samscdrental.model.dto;

import com.samscdrental.model.adt.*;

/**
 * <p>Title: Sams CD Rental Store</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author Ken Pugh
 * @version 1.0
 */

public class OverdueRentalDTO
{

	public Timestamp theRentalStartTime = null;
	public Timestamp theRentalDueTime = null;
	public Timestamp theRentalEndTime = null;
	public Dollar theOverdueFee = null;
	public CustomerID theCustomerID = null;
	public Name theCustomerName = null;

	//PhoneNumber customerDayPhoneNumber;
	public Name theCDReleaseTitle = null;
	public PhysicalID theCDDiscPhysicalID = null;

}
