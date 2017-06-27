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

public class CDReleaseDataAccess
	implements java.io.Serializable

{
	private static final long serialVersionUID = -1L; 

	private Collection theCollection = new Vector();

	/**
	 * add
	 *
	 * @param aCDRelease CDRelease
	 */
	public void add( CDRelease aCDRelease )
	{
		theCollection.add( aCDRelease );
	}

	/**
	 * remove
	 *
	 * @param aCDRelease CDRelease
	 */
	public void remove( CDRelease aCDRelease )
	{
		theCollection.remove( aCDRelease );
	}

	/**
	 * findByUPCCode
	 *
	 * @return CDRelease
	 */
	public CDRelease findByUPCCode( UPCCode aUPCCode )
	{
		Iterator iterator = theCollection.iterator();
		while ( iterator.hasNext() )
		{
			CDRelease aCDRelease = ( CDRelease ) iterator.next();
			if ( aCDRelease.getUPCCode().equals( aUPCCode ) )
			{
				return aCDRelease;
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

}
