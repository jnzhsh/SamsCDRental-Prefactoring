package com.samscdrental.display;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.samscdrental.controller.*;

/**
 * <p>Title: Sams CD Rental Store</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author Ken Pugh
 * @version 1.0
 */

public class MainFrame
	extends JFrame
{
	private static final long serialVersionUID = -1L; 
	
	JToggleButton checkoutButton = new JToggleButton();
	JToggleButton checkinButton = new JToggleButton();
	JLabel generalFrame = new JLabel();
	public MainFrame() throws HeadlessException
	{
		try
		{
			jbInit();
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception
	{
		checkoutButton.setMaximumSize( new Dimension( 71, 23 ) );
		checkoutButton.setMinimumSize( new Dimension( 71, 23 ) );
		checkoutButton.setText( "Checkout" );
		checkoutButton.setBounds( new Rectangle( 93, 126, 106, 31 ) );
		checkoutButton.addActionListener( new
										  MainFrame_checkoutButton_actionAdapter( this ) );
		checkinButton.setText( "Checkin" );
		checkinButton.setBounds( new Rectangle( 221, 126, 106, 31 ) );
		checkinButton.addActionListener( new
										 MainFrame_checkinButton_actionAdapter( this ) );
		this.getContentPane().setLayout( null );
		generalFrame.setFont( new java.awt.Font( "Dialog", 1, 20 ) );
		generalFrame.setText( "Sam\'s CD Rental" );
		generalFrame.setBounds( new Rectangle( 126, 44, 176, 43 ) );
		this.setLocale( java.util.Locale.getDefault() );
		this.setResizable( false );
		this.setTitle( "Sam\'s CD Rental" );
		this.addWindowListener( new MainFrame_this_windowAdapter( this ) );
		this.getContentPane().add( checkinButton, null );
		this.getContentPane().add( checkoutButton, null );
		this.getContentPane().add( generalFrame, null );

	}

	void checkoutButton_actionPerformed( ActionEvent e )
	{
		CheckoutDialog checkoutDialog = new CheckoutDialog();
		checkoutDialog.setBounds( 50, 50, 400, 300 );

		checkoutDialog.setVisible( true );
	}

	void checkinButton_actionPerformed( ActionEvent e )
	{
		CheckinDialog checkinDialog = new CheckinDialog();
		checkinDialog.setBounds( 50, 50, 400, 300 );
		checkinDialog.setVisible( true );
	}

	void this_windowClosing( WindowEvent e )
	{
		RentalOperations aRentalOperations =
			RentalOperations.getInstance();
		aRentalOperations.dispose();

		System.exit( 0 );
	}

}

class MainFrame_checkoutButton_actionAdapter
	implements java.awt.event.ActionListener
{
	MainFrame adaptee;

	MainFrame_checkoutButton_actionAdapter( MainFrame adaptee )
	{
		this.adaptee = adaptee;
	}

	public void actionPerformed( ActionEvent e )
	{
		adaptee.checkoutButton_actionPerformed( e );
	}
}

class MainFrame_checkinButton_actionAdapter
	implements java.awt.event.ActionListener
{
	MainFrame adaptee;

	MainFrame_checkinButton_actionAdapter( MainFrame adaptee )
	{
		this.adaptee = adaptee;
	}

	public void actionPerformed( ActionEvent e )
	{
		adaptee.checkinButton_actionPerformed( e );
	}
}

class MainFrame_this_windowAdapter
	extends java.awt.event.WindowAdapter
{
	MainFrame adaptee;

	MainFrame_this_windowAdapter( MainFrame adaptee )
	{
		this.adaptee = adaptee;
	}

	public void windowClosing( WindowEvent e )
	{
		adaptee.this_windowClosing( e );
	}
}
