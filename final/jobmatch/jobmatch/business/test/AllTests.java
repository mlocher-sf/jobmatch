// $Id: AllTests.java,v 1.1 2000/06/20 13:32:59 pse4 Exp $

package jobmatch.business.test;

import junit.framework.*;

/**
 *  TestSuite of all business layer tests
 *
 *  @since May 19 2000
 *  @author $Author: pse4 $
 *  @version $Revision: 1.1 $
 **/
public class AllTests extends TestSuite {

    public AllTests() {
	super("All business layer tests");
	this.addTest(jobmatch.business.service.test.AllTests.suite());
	this.addTest(jobmatch.business.company.profile.tree.test.TestTree.suite());
	this.addTest(jobmatch.business.entity.test.EntityTest.suite());
    }

    public static Test suite() { 
	return new AllTests();
    }
    
} //class

// Document history
/*
 * $Log: AllTests.java,v $
 * Revision 1.1  2000/06/20 13:32:59  pse4
 * Initial revision
 *
 * Revision 1.6  2000/06/12 21:52:26  locher
 * added DB Stress test
 *
 * Revision 1.5  2000/06/05 13:11:18  studer
 * entity test added
 *
 * Revision 1.4  2000/05/30 14:23:23  locher
 * tree redesign
 *
 * Revision 1.3  2000/05/21 20:02:02  locher
 * working constructors for test suites
 *
 * Revision 1.2  2000/05/19 12:06:00  studer
 * Konstruktor angepasst
 *
 * Revision 1.1  2000/05/19 10:59:40  locher
 * matcher and mailer service including test skeletons
 *
 */
