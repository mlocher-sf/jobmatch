// $Id: AllTests.java,v 1.2 2000/05/14 16:18:33 locher Exp $

package jobmatch.test;

import junit.framework.*;
import java.util.*;

/**
 *  TestSuite of all tests
 *
 *  @since May 12 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.2 $
 **/
public class AllTests extends TestSuite {

    private Collection tests;
    
    public AllTests() {
	super("All JobMatch Tests");
	this.tests = new ArrayList();
	this.addAllTests();
    }

    private void addAllTests() {
	this.addTest(jobmatch.business.company.tree.test.TestTree.suite());
//	this.addTest(new jobmatch.business.company.tree.test.TestTree().suite());
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
 * Revision 1.2  2000/05/14 16:18:33  locher
 * display test results
 *
 * Revision 1.1  2000/05/12 15:25:56  locher
 * junit tests
 *
 */
