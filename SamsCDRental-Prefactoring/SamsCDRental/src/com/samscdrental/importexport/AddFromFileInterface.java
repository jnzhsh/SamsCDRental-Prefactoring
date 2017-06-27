package com.samscdrental.importexport;

import com.samscdrental.failures.*;

/**
 * <p>Title: Sams CD Rental Store</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author Ken Pugh
 * @version 1.0
 */

public interface AddFromFileInterface
{
	void addToCollection( String line ) throws ParseLineDeviation;
}
