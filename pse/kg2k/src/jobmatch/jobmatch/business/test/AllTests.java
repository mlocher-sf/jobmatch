// $Id: AllTests.java,v 1.1 2000/05/19 10:59:40 locher Exp $

package jobmatch.business.test;

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
	super("All Business Tests");
	this.addTest(jobmatch.business.service.test.AllTests.suite());
	this.addTest(jobmatch.business.company.tree.test.TestTree.suite());
    }

    public static Test suite() { 
	return new TestSuite(AllTests.class); 
    }
    
} //class

// Document history
/*
 * $Log: AllTests.java,v $
 * Revision 1.1  2000/05/19 10:59:40  locher
 * matcher and mailer service including test skeletons
 *
 */
