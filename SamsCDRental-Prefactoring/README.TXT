ENVIRONMENTS:

You can use Eclipse to run this system.  Once you have unpacked the files, you can create
a project from the existing source of /SamsCDRental.  Use /SamsCDRental/classes as the output 
directory.  


This system has also been set up for use with Borland JBuilder Foundation Edition.  
You can get your free copy at www.borland.com.  
The project file is:  SamsCDRental-Exception-V1.jpx.  

UNPACK AND SETUP: 

Unzip the files into the root directory of a hard drive.  

You can unzip the files into another directory.  If you do so, you will either need to place the 
three text files: customer.txt, cddisc.txt, and cdrelease.txt 
into a directory called /SamsCDRental, or alter the locations 
in CheckinCheckoutTests.java and MigrateMain.java.   

     CDRELEASE_FILENAME = "/SamsCDRental/cdrelease.txt";
     CDDISC_FILENAME = "/SamsCDRental/cddisc.txt";
     CUSTOMER_FILENAME = "/SamsCDRental/customer.txt"; 

Two filenames are in com.samscdrental.configuration.Configuration.  
They are initialized to use the c:\temp directory, 
but you can locate the two files anywhere.
     DATA_ACCESS_DATA_FILENAME = "c:/temp/StoreCollections.dat";
     REPORT_PRINTER_FILENAME = "c:/temp/printer.txt";

TESTS:

The test program is com.samscdrental.tests.CheckinCheckoutTests.java.  
junit.jar is included in the archive.  You need to add this jar to your libraries,
if it is not added by default.  


REGULAR PROGRAM: 

The main program is com.samscdrental.display.Main.java.  
You need to run com.sams.cdrental.migration.MigrateMain.java 
or run the tests before running this program.  



