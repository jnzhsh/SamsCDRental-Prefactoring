package com.samscdrental.display;

import javax.swing.*;

/**
 * <p>Title: Sams CD Rental Store</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author Ken Pugh
 * @version 1.0
 */
import com.samscdrental.controller.*;
import com.samscdrental.failures.*;

public class Main
{

	public static void main( String[] args )
	{
		try
		{
			RentalOperations.getInstance();
			MainFrame mainFrame = new MainFrame();
			mainFrame.setBounds( 10, 10, 400, 400 );
			mainFrame.show();
		}
		catch ( SeriousErrorException exception )
		{
			JOptionPane.showMessageDialog( null,
										   TERMINATION_MESSAGE_HEADER +
										   exception.getMessage(),
										   TERMINATION_MESSAGE_TITLE,
										   JOptionPane.ERROR_MESSAGE );
			displayStackTrace( exception );
			Throwable imbeddedException =
				exception.getCause();
			displayStackTrace( imbeddedException );
		}
		catch ( Exception exception )
		{

			JOptionPane.showMessageDialog( null,
										   TERMINATION_MESSAGE_HEADER +
										   exception.getMessage(),
										   UNEXCEPTED_TERMINATION_MESSAGE_TITLE,
										   JOptionPane.ERROR_MESSAGE );
			displayStackTrace( exception );
		}
	}

	private static String getStackAsString( Throwable exception )
	{
		StringBuffer stringBuffer = new StringBuffer();
		StackTraceElement[] ste = exception.getStackTrace();
		for ( int i = 0; i < ste.length; i++ )
		{
			stringBuffer.append( ste[i].toString() +
								 System.getProperty( "line.separator" ) );
		}
		return stringBuffer.toString();

	}

	private static void displayStackTrace( Throwable exception )
	{
		String errorString = getStackAsString( exception );
		JOptionPane.showMessageDialog( null,
									   errorString,
									   STACK_TRACE_MESSAGE_TITLE,
									   JOptionPane.ERROR_MESSAGE );

	}

	private static final String TERMINATION_MESSAGE_HEADER = "This program has terminated with the following error.\nContact your support personal at 555-1212\n";
	private static final String TERMINATION_MESSAGE_TITLE =
		"Termination Error Message";
	private static final String UNEXCEPTED_TERMINATION_MESSAGE_TITLE =
		"Unexpected Termination Error Message";
	private static final String STACK_TRACE_MESSAGE_TITLE =
		"Termination Stack Trace Message";

}
