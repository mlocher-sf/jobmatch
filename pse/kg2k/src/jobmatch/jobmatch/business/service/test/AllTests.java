// $Id: AllTests.java,v 1.2 2000/05/19 12:06:21 studer Exp $

package jobmatch.business.service.test;

import junit.framework.*;

/**
 *  TestSuite of all service tests
 *
 *  @since May 19 2000
 *  @author $Author: studer $
 *  @version $Revision: 1.2 $
 **/
public class AllTests extends TestSuite {

    public AllTests(String name) {
	super(name);
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
 * Revision 1.2  2000/05/19 12:06:21  studer
 * Konstruktor angepasst
 *
 * Revision 1.1  2000/05/19 10:59:36  locher
 * matcher and mailer service including test skeletons
 *
 */
