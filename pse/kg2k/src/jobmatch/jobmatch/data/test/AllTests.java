// $Id: AllTests.java,v 1.1 2000/06/12 21:52:28 locher Exp $

package jobmatch.data.test;

import junit.framework.*;

/**
 *  TestSuite of all data layer tests
 *
 *  @since June 12 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
public class AllTests extends TestSuite {

    public AllTests() {
	super("All data layer tests");
	this.addTest(jobmatch.data.test.BinaryTest.suite());
	this.addTest(jobmatch.data.test.StressTest.suite());
    }

    public static Test suite() { 
	return new AllTests();
    }
    
} //class

// Document history
/*
 * $Log: AllTests.java,v $
 * Revision 1.1  2000/06/12 21:52:28  locher
 * added DB Stress test
 *
 */
