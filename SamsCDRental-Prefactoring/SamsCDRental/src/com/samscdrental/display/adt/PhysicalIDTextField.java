package com.samscdrental.display.adt;

import java.awt.event.*;
import javax.swing.*;

import com.samscdrental.failures.*;
import com.samscdrental.model.adt.*;

public class PhysicalIDTextField
	extends JTextField
{
	private static final long serialVersionUID = -1L; 

	private class MyKeyListener
		extends java.awt.event.KeyAdapter

	{
		/**
		 * Invoked when a key has been typed.
		 *
		 * @param e KeyEvent
		 * @todo Implement this java.awt.event.KeyListener method
		 */
		public void keyTyped( KeyEvent e )
		{
			char c = e.getKeyChar();
			if ( !Character.isDigit( c ) &&
				 !Character.isISOControl( c ) )
			{
				e.consume();
			}
		}

	}

	public PhysicalIDTextField()
	{
		super();
		setText( "" );
		this.addKeyListener( new MyKeyListener() );
	}

	public PhysicalID getPhysicalID() throws
		PhysicalIDFormatDeviation
	{
		String physicalIDString = this.getText();
		PhysicalID aPhysicalID = new PhysicalID( physicalIDString );
		return aPhysicalID;
	}
}