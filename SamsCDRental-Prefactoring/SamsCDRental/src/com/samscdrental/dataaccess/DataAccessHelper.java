package com.samscdrental.dataaccess;

import java.io.*;

import com.samscdrental.failures.*;

/**
 * <p>Title: Sams CD Rental Store</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author Ken Pugh
 * @version 1.0
 */

public class DataAccessHelper
{

	public static Object readObjectFromOpenStream( Class expectedClass,
		ObjectInputStream input )
	{
		Object anObject = null;
		try
		{
			anObject = input.readObject();
			if ( !anObject.getClass().equals( expectedClass ) )
			{
				throw new ClassNotFoundException();
			}
		}

		catch ( IOException exception )
		{
			throw new SeriousErrorException(
				ERROR_CREATING_OBJECT_STREAM, exception );
		}
		catch ( ClassNotFoundException exception )
		{
			throw new SeriousErrorException(
				ERROR_STREAM_NOT_COLLECTION, exception );
		}

		return anObject;

	}

	public static void writeObjectToOpenStream( Object anObject,
												ObjectOutputStream output )
	{

		try
		{
			output.writeObject( anObject );
		}
		catch ( IOException exception )
		{
			throw new SeriousErrorException(
				ERROR_CREATING_OBJECT_STREAM, exception );
		}

		return;
	}

	// Error messages
	private static final String ERROR_CREATING_OBJECT_STREAM
		= "Unable to create or access Object stream ";
	private static final String ERROR_STREAM_NOT_COLLECTION
		= "Stream is not a collection ";

}
