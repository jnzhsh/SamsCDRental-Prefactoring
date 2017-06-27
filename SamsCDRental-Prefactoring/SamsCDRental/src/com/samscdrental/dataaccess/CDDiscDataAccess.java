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

public class CDDiscDataAccess
	implements java.io.Serializable
{
	private static final long serialVersionUID = 1L; 

	/**
	 * add
	 *
	 * @param aCDDisc CDDisc
	 */
	public void add( CDDisc aCDDisc )
	{
		theCollection.add( aCDDisc );
	}

	/**
	 * remove
	 *
	 * @param aCDDisc CDDisc
	 */
	public void remove( CDDisc aCDDisc )
	{
		theCollection.remove( aCDDisc );
	}

	/**
	 * findByCDRelease
	 *
	 * @return CDDisc
	 */
	public CDDisc[] findByCDRelease( CDRelease aCDRelease )
	{
		Iterator iterator = theCollection.iterator();
		Vector cdDiscs = new Vector();
		while ( iterator.hasNext() )
		{
			CDDisc cdDisc = ( CDDisc ) iterator.next();
			if ( cdDisc.getCdRelease().equals( aCDRelease ) )
			{
				cdDiscs.add( cdDisc );
			}
		}
		return ( CDDisc[] ) cdDiscs.toArray();
	}

	/**
	 * findByCDRelease
	 *
	 * @return CDDisc
	 */
	public CDDisc findByPhysicalID( PhysicalID aPhysicalID )
	{
		Iterator iterator = theCollection.iterator();
		while ( iterator.hasNext() )
		{
			CDDisc cdDisc = ( CDDisc ) iterator.next();
			if ( cdDisc.getPhysicalID().equals( aPhysicalID ) )
			{
				return cdDisc;
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

	/**
	 * findAll
	 */

	public CDDisc[] findAll()
	{
		int size = theCollection.size();
		CDDisc[] cddiscs = new CDDisc[size];
		Iterator iterator = theCollection.iterator();

		for ( int i = 0; i < cddiscs.length; i++ )
		{
			cddiscs[i] = ( CDDisc ) iterator.next();

		}
		return cddiscs;
	}

	private Collection theCollection = new Vector();

}
