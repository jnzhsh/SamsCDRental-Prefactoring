package com.samscdrental.display;

import java.awt.*;
import javax.swing.*;

public class DisplayHelper
{
	public static void displayError( Component component, String text )
	{
		JOptionPane.showMessageDialog( component,
									   text,
									   ERROR_TITLE,
									   JOptionPane.ERROR_MESSAGE );

	}

	public static void displayMessage( String text )
	{
		JOptionPane.showMessageDialog( null,
									   text,
									   MESSAGE_TITLE,
									   JOptionPane.ERROR_MESSAGE );

	}

	private static String ERROR_TITLE = "Error";
	private static String MESSAGE_TITLE = "Message";

}
