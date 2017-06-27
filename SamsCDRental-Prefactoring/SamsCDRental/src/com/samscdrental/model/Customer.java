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

public class Customer
	implements java.io.Serializable
{
	private static final long serialVersionUID = -1L; 

	public CustomerID getCustomerID()
	{
		return theCustomerID;
	}

	public Name getName()
	{
		return theName;
	}



	public Customer( Name aName, CustomerID aCustomerID )
	{
		theName = aName;
		theCustomerID = aCustomerID;
	}

	private CustomerID theCustomerID;
	private Name theName;

}
