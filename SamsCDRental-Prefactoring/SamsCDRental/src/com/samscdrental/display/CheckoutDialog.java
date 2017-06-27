package com.samscdrental.display;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.samscdrental.controller.*;
import com.samscdrental.display.adt.*;
import com.samscdrental.failures.*;
import com.samscdrental.model.adt.*;
import com.samscdrental.model.dto.*;
import com.samscdrental.reports.*;

/**
 * <p>Title: Sams CD Rental Store</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author Ken Pugh
 * @version 1.0
 */

public class CheckoutDialog
	extends JDialog
{
	private static final long serialVersionUID = -1L; 
	
	RentalOperations theRentalOperations = RentalOperations.getInstance();
	JLabel jLabel1 = new JLabel();
	JLabel jLabel2 = new JLabel();
	PhysicalIDTextField theCDDiscPhysicalIDField = new PhysicalIDTextField();
	JToggleButton doneButton = new JToggleButton();
	JToggleButton cancelButton = new JToggleButton();
	JLabel jLabel3 = new JLabel();
	CustomerIDTextField theCustomerIDField = new CustomerIDTextField();
	public CheckoutDialog() throws HeadlessException
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
		jLabel1.setText( "Checkout" );
		jLabel1.setBounds( new Rectangle( 167, 40, 70, 32 ) );
		this.getContentPane().setLayout( null );
		jLabel2.setText( "CD Disc ID" );
		jLabel2.setBounds( new Rectangle( 107, 113, 72, 16 ) );
		theCDDiscPhysicalIDField.setText( "" );
		theCDDiscPhysicalIDField.setBounds( new Rectangle( 205, 111, 96, 20 ) );
		doneButton.setText( "Done" );
		doneButton.setBounds( new Rectangle( 120, 186, 80, 23 ) );
		doneButton.addActionListener( new
									  CheckoutDialog_DoneButton_actionAdapter( this ) );
		cancelButton.setText( "Cancel" );
		cancelButton.setBounds( new Rectangle( 218, 186, 80, 23 ) );
		cancelButton.addActionListener( new
										CheckoutDialog_CancelButton_actionAdapter( this ) );
		this.getContentPane().setBackground( SystemColor.control );
		this.setJMenuBar( null );
		this.setModal( true );
		this.setTitle( "CheckOut" );
		jLabel3.setBounds( new Rectangle( 107, 84, 72, 16 ) );
		jLabel3.setRequestFocusEnabled( true );
		jLabel3.setText( "Customer ID" );
		theCustomerIDField.setBounds( new Rectangle( 205, 83, 96, 20 ) );
		theCustomerIDField.setText( "" );
		this.getContentPane().add( jLabel1, null );
		this.getContentPane().add( theCDDiscPhysicalIDField, null );
		this.getContentPane().add( jLabel2, null );
		this.getContentPane().add( jLabel3, null );
		this.getContentPane().add( theCustomerIDField, null );
		this.getContentPane().add( doneButton, null );
		this.getContentPane().add( cancelButton, null );
	}

	void DoneButton_actionPerformed( ActionEvent e )
	{
		try
		{
			PhysicalID aPhysicalID = theCDDiscPhysicalIDField.getPhysicalID();
			CustomerID aCustomerID = theCustomerIDField.getCustomerID();

			RentalContractDTO aRentalContractDTO = theRentalOperations.
				checkoutCDDisc( aPhysicalID, aCustomerID );

			ContractReportPlainTextFormat
				aContractReportPlainTextFormat =
				Reports.createContractReport( aRentalContractDTO );

			Reports.printReport(
				aContractReportPlainTextFormat );
		}
		catch ( PhysicalIDFormatDeviation exception )
		{
			DisplayHelper.displayError( this, exception.getMessage() );
			return;
		}
		catch ( CustomerIDFormatDeviation exception )
		{
			DisplayHelper.displayError( this, exception.getMessage() );
			return;
		}
		catch ( CheckOutDeviation exception )
			{
				DisplayHelper.displayError( this, exception.getMessage() );
				return;
			}
		catch ( PrinterFailureDeviation exception )
		{
			DisplayHelper.displayError( this, exception.getMessage() );
		}

		setVisible( false );

	}

	void CancelButton_actionPerformed( ActionEvent e )
	{

		setVisible( false );
	}

}

class CheckoutDialog_DoneButton_actionAdapter
	implements java.awt.event.ActionListener
{
	CheckoutDialog adaptee;

	CheckoutDialog_DoneButton_actionAdapter( CheckoutDialog adaptee )
	{
		this.adaptee = adaptee;
	}

	public void actionPerformed( ActionEvent e )
	{
		adaptee.DoneButton_actionPerformed( e );
	}
}

class CheckoutDialog_CancelButton_actionAdapter
	implements java.awt.event.ActionListener
{
	CheckoutDialog adaptee;

	CheckoutDialog_CancelButton_actionAdapter( CheckoutDialog adaptee )
	{
		this.adaptee = adaptee;
	}

	public void actionPerformed( ActionEvent e )
	{
		adaptee.CancelButton_actionPerformed( e );
	}
}
