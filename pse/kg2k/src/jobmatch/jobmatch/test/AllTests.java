// $Id: AllTests.java,v 1.5 2000/06/12 21:52:31 locher Exp $

package jobmatch.test;

import junit.framework.*;
import java.util.*;

/**
 *  TestSuite of all tests
 *
 *  @since May 12 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.5 $
 **/
public class AllTests extends TestSuite {

    private Collection tests;
    
    public AllTests() {
	super("All JobMatch Tests");
	this.tests = new ArrayList();
	this.addAllTests();
    }

    private void addAllTests() {
	this.addTest(jobmatch.business.test.AllTests.suite());
	this.addTest(jobmatch.data.test.AllTests.suite());
    }

    public void addTest(Test test) {
	super.addTest(test);
	this.tests.add(test);
    }

    public static Collection getAllTests() {
	return new AllTests().tests;
    }

    public static Test suite() { 
	return new TestSuite(AllTests.class); 
    }
    
} //class


// Document history
/*
 * $Log: AllTests.java,v $
 * Revision 1.5  2000/06/12 21:52:31  locher
 * added DB Stress test
 *
 * Revision 1.4  2000/06/12 16:28:02  locher
 * added new data layer tests
 *
 * Revision 1.3  2000/05/19 10:59:43  locher
 * matcher and mailer service including test skeletons
 *
 * Revision 1.2  2000/05/14 16:18:33  locher
 * display test results
 *
 * Revision 1.1  2000/05/12 15:25:56  locher
 * junit tests
 *
 */
