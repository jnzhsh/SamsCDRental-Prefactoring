package com.samscdrental.helper;

public class StringHelper
{
	public static boolean containsOnlyAlphanumerics( String aString )
	{
		for ( int i = 0; i < aString.length(); i++ )
		{
			char c = aString.charAt( i );
			if ( !Character.isLetterOrDigit( c ) )
			{
				return false;
			}
		}
		return true;
	}

	public static boolean containsOnlyNumerics( String aString )
	{
		for ( int i = 0; i < aString.length(); i++ )
		{
			char c = aString.charAt( i );
			if ( !Character.isDigit( c ) )
			{
				return false;
			}
		}
		return true;
	}

	public static boolean containsNoControls( String aString )
	{
		for ( int i = 0; i < aString.length(); i++ )
		{
			char c = aString.charAt( i );
			if ( Character.isISOControl( c ) )
			{
				return false;
			}
		}
		return true;
	}

}
