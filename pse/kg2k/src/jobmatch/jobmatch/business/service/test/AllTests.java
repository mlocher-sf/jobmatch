// $Id: AllTests.java,v 1.1 2000/05/19 10:59:36 locher Exp $

package jobmatch.business.service.test;

import junit.framework.*;

/**
 *  TestSuite of all service tests
 *
 *  @since May 19 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
public class AllTests extends TestSuite {

    public AllTests() {
	super("All Service Tests");
	this.addTest(TestMatcher.suite());
	this.addTest(TestMailer.suite());
    }

    public static Test suite() { 
	return new TestSuite(AllTests.class); 
    }
    
} //class

// Document history
/*
 * $Log: AllTests.java,v $
 * Revision 1.1  2000/05/19 10:59:36  locher
 * matcher and mailer service including test skeletons
 *
 */
