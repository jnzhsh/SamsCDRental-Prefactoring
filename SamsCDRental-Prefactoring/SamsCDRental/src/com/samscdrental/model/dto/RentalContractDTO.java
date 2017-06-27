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

public class RentalContractDTO
{

	public Timestamp theRentalStartTime = null;
	public Timestamp theRentalDueTime = null;
	public Name theCustomerName = null;
	public Name theCDReleaseTitle = null;
	public PhysicalID theCDDiscPhysicalID = null;
	public Dollar theRentalFee = null;
}
