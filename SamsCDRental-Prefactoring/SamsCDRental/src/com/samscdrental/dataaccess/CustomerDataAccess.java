package com.samscdrental.dataaccess;

import java.util.*;

import com.samscdrental.model.*;
import com.samscdrental.model.adt.*;

/**
 * <p>Title: Sams CD Rental Store</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author Ken Pugh
 * @version 1.0
 */

public class CustomerDataAccess
	implements java.io.Serializable

{
	private static final long serialVersionUID = -1L; 


	/**
	 * add
	 *
	 * @param aCustomer Customer
	 */
	public void add( Customer aCustomer )
	{
		theCollection.add( aCustomer );
	}

	/**
	 * remove
	 *
	 * @param aCustomer Customer
	 */
	public void remove( Customer aCustomer )
	{
		theCollection.remove( aCustomer );
	}

	/**
	 * findByCustomerID
	 *
	 * @return Customer
	 */
	public Customer findByCustomerID( CustomerID aCustomerID )
	{
		Iterator iterator = theCollection.iterator();
		while ( iterator.hasNext() )
		{
			Customer Customer = ( Customer ) iterator.next();
			if ( Customer.getCustomerID().equals( aCustomerID ) )
			{
				return Customer;
			}
		}
		return null;
	}

	/**
	 * removeAll
	 */
	public void removeAll_TestingOnly()
	{
		theCollection.clear();
	}

	private Collection theCollection = new Vector();

}
