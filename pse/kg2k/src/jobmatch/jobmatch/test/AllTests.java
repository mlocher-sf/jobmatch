// $Id: AllTests.java,v 1.1 2000/05/12 15:25:56 locher Exp $

package jobmatch.test;

import junit.framework.*;

/**
 *  TestSuite of all tests
 *
 *  @since May 12 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
public class AllTests extends TestSuite {
    
    public AllTests() {
	super("All JobMatch Tests");
	this.addAllTests();
    }

    private void addAllTests() {
	// add other testsuites
    }
    
} //class


// Document history
/*
 * $Log: AllTests.java,v $
 * Revision 1.1  2000/05/12 15:25:56  locher
 * junit tests
 *
 */
