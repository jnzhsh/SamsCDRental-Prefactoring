package com.samscdrental.display;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.samscdrental.controller.*;
import com.samscdrental.display.adt.*;
import com.samscdrental.failures.*;
import com.samscdrental.model.adt.*;
import com.samscdrental.reports.*;

/**
 * <p>Title: Sams CD Rental Store</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author Ken Pugh
 * @version 1.0
 */

public class CheckinDialog
	extends JDialog
{
	private static final long serialVersionUID = -1L; 

	JLabel jLabel1 = new JLabel();
	JLabel jLabel2 = new JLabel();
	PhysicalIDTextField theCDDiscPhysicalIDField = new PhysicalIDTextField();
	JToggleButton doneButton = new JToggleButton();
	JToggleButton cancelButton = new JToggleButton();
	RentalOperations theRentalOperations = RentalOperations.getInstance();
	public CheckinDialog() throws HeadlessException
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
		jLabel1.setFont( new java.awt.Font( "Dialog", 1, 15 ) );
		jLabel1.setText( "Checkin" );
		jLabel1.setBounds( new Rectangle( 167, 40, 70, 32 ) );
		this.getContentPane().setLayout( null );
		jLabel2.setText( "CD Disc ID" );
		jLabel2.setBounds( new Rectangle( 107, 113, 72, 16 ) );
		theCDDiscPhysicalIDField.setText( "" );
		theCDDiscPhysicalIDField.setBounds( new Rectangle( 205, 111, 96, 20 ) );
		doneButton.setText( "Done" );
		doneButton.setBounds( new Rectangle( 125, 186, 80, 23 ) );
		doneButton.addActionListener( new
									  CheckinDialog_DoneButton_actionAdapter( this ) );
		cancelButton.setText( "Cancel" );
		cancelButton.setBounds( new Rectangle( 215, 186, 80, 23 ) );
		cancelButton.addActionListener( new
										CheckinDialog_CancelButton_actionAdapter( this ) );
		this.getContentPane().setBackground( SystemColor.control );
		this.setJMenuBar( null );
		this.setModal( true );
		this.setTitle( "CheckIn " );
		this.getContentPane().add( jLabel1, null );
		this.getContentPane().add( doneButton, null );
		this.getContentPane().add( theCDDiscPhysicalIDField, null );
		this.getContentPane().add( jLabel2, null );
		this.getContentPane().add( cancelButton, null );
	}

	void DoneButton_actionPerformed( ActionEvent e )
	{
		try
		{
			PhysicalID aPhysicalID = theCDDiscPhysicalIDField.getPhysicalID();
			theRentalOperations.checkinCDDisc( aPhysicalID );
		}
		catch ( PhysicalIDFormatDeviation exception )
		{
			DisplayHelper.displayError( this, exception.getMessage() );
			return;

		}
		catch ( CheckInDeviation exception )
		{

			DisplayHelper.displayError( this, exception.getMessage() );
			return;
		}
		catch ( LateReturnDeviation exception )
		{

			OverdueRentalReportPlainTextFormat
				aOverdueRentalReportPlainTextFormat =
				Reports.createOverdueRentalReport( exception.
				getOverdueRentalDTO() );
			DisplayHelper.displayError( this,
										aOverdueRentalReportPlainTextFormat.
										getReportString() );

		}
		setVisible( false );

	}

	void CancelButton_actionPerformed( ActionEvent e )
	{
		setVisible( false );
	}

}

class CheckinDialog_DoneButton_actionAdapter
	implements java.awt.event.ActionListener
{
	CheckinDialog adaptee;

	CheckinDialog_DoneButton_actionAdapter( CheckinDialog adaptee )
	{
		this.adaptee = adaptee;
	}

	public void actionPerformed( ActionEvent e )
	{
		adaptee.DoneButton_actionPerformed( e );
	}
}

class CheckinDialog_CancelButton_actionAdapter
	implements java.awt.event.ActionListener
{
	CheckinDialog adaptee;

	CheckinDialog_CancelButton_actionAdapter( CheckinDialog adaptee )
	{
		this.adaptee = adaptee;
	}

	public void actionPerformed( ActionEvent e )
	{
		adaptee.CancelButton_actionPerformed( e );
	}
}
