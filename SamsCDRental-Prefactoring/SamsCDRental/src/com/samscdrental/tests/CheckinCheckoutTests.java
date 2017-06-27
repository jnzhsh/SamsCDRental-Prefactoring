package com.samscdrental.tests;

import com.samscdrental.controller.*;
import com.samscdrental.dataaccess.*;
import com.samscdrental.failures.*;
import com.samscdrental.model.adt.*;
import com.samscdrental.model.dto.*;
import junit.framework.*;

/**
 * <p>Title: Sams CD Rental Store</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author Ken Pugh
 * @version 1.0
 */

public class CheckinCheckoutTests
	extends TestCase
{

	public CheckinCheckoutTests()
	{

	}

	public CheckinCheckoutTests( String name )
	{
		super( name );
	}

	/**
	 * runNormalOperationTest
	 */
	public void testNormalOperation() throws Exception
	{
		try
		{
			PhysicalID aPhysicalID = new PhysicalID( "1234567890" );
			CustomerID aCustomerID = new CustomerID( "ABCDEFGHIJ" );
			RentalContractDTO rcdto = theRentalOperations.
				checkoutCDDisc( aPhysicalID, aCustomerID );
			// Check DTO values
			assertEquals( "Rental fee incorrect", new Dollar( 3.0 ),
						  rcdto.theRentalFee );
			assertEquals( "Physical ID DTO incorrect", aPhysicalID,
						  rcdto.theCDDiscPhysicalID );
			Timestamp today = new Timestamp();
			assertEquals( " Start date incorrect", today,
						  rcdto.theRentalStartTime );
			Timestamp due_date = today.addDays( 2 );
			assertEquals( " End date incorrect ", due_date,
						  rcdto.theRentalDueTime );
			assertEquals( " Customer name incorrect", new Name( "Sammy" ),
						  rcdto.theCustomerName );
			assertEquals( "Title incorrect", new Name( "Really Good Title" ),
						  rcdto.theCDReleaseTitle );
			boolean flag = theRentalOperations.isCDDiscRented( aPhysicalID );
			assertTrue( "CDDisc not rented", flag );
			theRentalOperations.checkinCDDisc( aPhysicalID );
		}
		catch ( PhysicalIDFormatDeviation e )
		{
			fail(
				"Physical id failed " + e.getMessage() );
		}
		catch ( CustomerIDFormatDeviation e )
		{
			fail( "Customer id failed " + e.getMessage() );
		}
		catch ( CheckOutDeviation e )
		{
			fail( "Checkout failed " + e.getMessage() );

		}
		catch ( CheckInDeviation e )
		{
			fail( "Checkin failed " + e.getMessage() );

		}
		catch ( LateReturnDeviation e )
		{
			fail( "Should not be late return " );
		}
	}

	public void testLateReturn() throws Exception
	{
		PhysicalID aPhysicalID = null;
		CustomerID aCustomerID = null;
		try
		{
			aPhysicalID = new PhysicalID( "1234567890" );
			aCustomerID = new CustomerID( "ABCDEFGHIJ" );
			theRentalOperations.checkoutCDDisc( aPhysicalID, aCustomerID );
			boolean flag = theRentalOperations.isCDDiscRented( aPhysicalID );
			assertTrue( "CDDisc not rented", flag );
			// What do I do - wait for two days and then test?
			// I really want to perform this test at the command level
			// Because Sam wants a dialog box put up.
			theTestOnlyOperations.setStartTimeForRentalBackSomeDays(
				aPhysicalID, 3 );
			theRentalOperations.checkinCDDisc(
				aPhysicalID );
			fail( "Rental supposed to be overdue" );
		}
		catch ( PhysicalIDFormatDeviation e )
		{
			fail(
				"Physical id failed " + e.getMessage() );
		}
		catch ( CustomerIDFormatDeviation e )
		{
			fail( "Customer id failed " + e.getMessage() );
		}
		catch ( CheckOutDeviation e )
		{
			fail( "Checkout failed " + e.getMessage() );

		}
		catch ( CheckInDeviation e )
		{
			fail( "Checkin failed " + e.getMessage() );

		}
		catch ( LateReturnDeviation e )
		{
			// Check the contents of the DTO
			OverdueRentalDTO ordto = e.getOverdueRentalDTO();
			Timestamp today = new Timestamp();
			Timestamp start = today.addDays( -3 );
			assertEquals( "Start date incorrect", start,
						  ordto.theRentalStartTime );
			Timestamp due_date = start.addDays( 2 );
			assertEquals( "Due date incorrect", due_date,
						  ordto.theRentalDueTime );
			Timestamp end_date = today;
			assertEquals( "End date incoorect", end_date,
						  ordto.theRentalEndTime );
			assertEquals( " Customer name incorrect", new Name( "Sammy" ),
						  ordto.theCustomerName );
			assertEquals( "Title incorrect", new Name( "Really Good Title" ),
						  ordto.theCDReleaseTitle );
			assertEquals( "Customer id incorrect", aCustomerID,
						  ordto.theCustomerID );
			assertEquals( "Physical id incorrect", aPhysicalID,
						  ordto.theCDDiscPhysicalID );
			;
		}

	}

	public void testBadPhysicalID() throws Exception
	{
		try
		{
			new PhysicalID( "123456" );
			fail( "Physical id supposed to fail" );
		}
		catch ( PhysicalIDFormatDeviation e )
		{
			;
		}
	}

	public void testBadCustomerID() throws Exception
	{
		try
		{
			new CustomerID( "ABCDEFG" );
			fail( "Customer id supposed to fail" );
		}
		catch ( CustomerIDFormatDeviation e )
		{
			;
		}

	}

	public void testNonExistentPhysicalID() throws Exception
	{
		try
		{
			PhysicalID aPhysicalID = new PhysicalID( "1234567000" );
			CustomerID aCustomerID = new CustomerID( "ABCDEFGHIJ" );
			theRentalOperations.checkoutCDDisc( aPhysicalID,
												aCustomerID );
			fail( "Checkout should fail" );
		}
		catch ( PhysicalIDFormatDeviation e )
		{
			fail(
				"Physical id failed " + e.getMessage() );
		}
		catch ( CustomerIDFormatDeviation e )
		{
			fail( "Customer id failed " + e.getMessage() );
		}
		catch ( CheckOutDeviation e )
		{
			;
		}

	}

	public void testNonExistentCustomerID() throws Exception
	{
		try
		{
			PhysicalID aPhysicalID = new PhysicalID( "1234567890" );
			CustomerID aCustomerID = new CustomerID( "ABCDEQQQQQ" );
			theRentalOperations.checkoutCDDisc( aPhysicalID,
												aCustomerID );
			fail( "Checkout should fail" );
		}
		catch ( PhysicalIDFormatDeviation e )
		{
			fail(
				"Physical id failed " + e.getMessage() );
		}
		catch ( CustomerIDFormatDeviation e )
		{
			fail( "Customer id failed " + e.getMessage() );
		}
		catch ( CheckOutDeviation e )
		{
			;
		}
	}

	public void testAlreadyRented() throws Exception
	{
		try
		{
			PhysicalID aPhysicalID = new PhysicalID( "1234567890" );
			CustomerID aCustomerID = new CustomerID( "ABCDEFGHIJ" );
			try
			{
				theRentalOperations.checkoutCDDisc( aPhysicalID, aCustomerID );
			}
			catch ( CheckOutDeviation e )
			{
				fail( "Checkout failed " + e.getMessage() );

			}

			boolean flag = theRentalOperations.isCDDiscRented( aPhysicalID );
			assertTrue( "CDDisc not rented", flag );

			CustomerID anotherCustomerID = new CustomerID( "DEFGHIJKLM" );
			theRentalOperations.checkoutCDDisc( aPhysicalID, anotherCustomerID );
			fail( "Checkout was supposed to fail" );
		}
		catch ( PhysicalIDFormatDeviation e )
		{
			fail(
				"Physical id failed " + e.getMessage() );
		}
		catch ( CustomerIDFormatDeviation e )
		{
			fail( "Customer id failed " + e.getMessage() );
		}
		catch ( CheckOutDeviation e )
		{
			;
		}

	}

	public void testReturnNotRented() throws Exception
	{
		try
		{
			PhysicalID aPhysicalID = new PhysicalID( "1234567890" );
			theRentalOperations.checkinCDDisc(
				aPhysicalID );
			fail( "Checkin suppose to fail" );
		}
		catch ( PhysicalIDFormatDeviation e )
		{
			fail(
				"Physical id failed " + e.getMessage() );
		}
		catch ( CheckInDeviation e )
		{
			;
		}

	}

	public static Test suite()
	{
		TestSuite suite = new TestSuite();
		suite.addTest( new CheckinCheckoutTests( "testNormalOperation" ) );
		suite.addTest( new CheckinCheckoutTests( "testLateReturn" ) );
		suite.addTest( new CheckinCheckoutTests( "testBadPhysicalID" ) );
		suite.addTest( new CheckinCheckoutTests( "testBadCustomerID" ) );
		suite.addTest( new CheckinCheckoutTests( "testAlreadyRented" ) );
		suite.addTest( new CheckinCheckoutTests( "testNonExistentPhysicalID" ) );
		suite.addTest( new CheckinCheckoutTests( "testReturnNotRented" ) );

		TestResult testResult = new TestResult();
		suite.run( testResult );
		return suite;
	}

	public static void main( String[] args )
	{
		setupFilenames( args );
		StoreDataAccess.makeNew();
		// If errors occur, then let them occur here
		new CheckinCheckoutTests();
		junit.textui.TestRunner.run( suite() );
	}

	/**
	 * Sets up the fixture, for example, open a network connection.
	 *
	 * @throws Exception
	 * @todo Implement this junit.framework.TestCase method
	 */
	protected void setUp() throws Exception
	{
		theRentalOperations = RentalOperations.getInstance();
		theMaintenanceOperations =
			MaintenanceOperations.getInstance();
		theTestOnlyOperations = TestOnlyOperations.getInstance();

		theTestOnlyOperations.collectionsClear();
		theMaintenanceOperations.collectionsInitialize(
			CUSTOMER_FILENAME,
			CDDISC_FILENAME,
			CDRELEASE_FILENAME );

	}

	/**
	 * Tears down the fixture, for example, close a network connection.
	 *
	 * @throws Exception
	 * @todo Implement this junit.framework.TestCase method
	 */
	protected void tearDown() throws Exception
	{

		theRentalOperations.dispose();

	}

	private RentalOperations theRentalOperations;
	private MaintenanceOperations theMaintenanceOperations;
	private TestOnlyOperations theTestOnlyOperations;

	private static void setupFilenames( String[] args )
	{
		if ( args.length == 0 )
		{
			return;
		}
		if ( args.length < 3 )
		{
			System.out.println( "You must specify 3 filenames" +
								"- CDRelease, CDDisc, Customer" );
			return;
		}
		CDRELEASE_FILENAME = args[0];
		CDDISC_FILENAME = args[1];
		CUSTOMER_FILENAME = args[2];

	}

	private static String CDRELEASE_FILENAME =
		"/SamsCDRental/cdrelease.txt";
	private static String CDDISC_FILENAME = "/SamsCDRental/cddisc.txt";
	private static String CUSTOMER_FILENAME = "/SamsCDRental/customer.txt";

}
