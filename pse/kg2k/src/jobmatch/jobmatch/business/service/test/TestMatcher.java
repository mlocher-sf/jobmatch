// $Id: TestMatcher.java,v 1.1 2000/05/19 10:59:37 locher Exp $

package jobmatch.business.service.test;

import junit.framework.*;
import jobmatch.business.service.*;

/**
 *  Test the matcher
 *
 *  @since May 19 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
public class TestMatcher extends TestCase {

    public TestMatcher(String name) {
	super(name);
    }
    
    protected void setUp() {
    }

    public void testSomething() {
    }

    public static Test suite() { 
	TestSuite suite= new TestSuite(TestMatcher.class);
	return suite;
    }

} //class

// Document history
/*
 * $Log: TestMatcher.java,v $
 * Revision 1.1  2000/05/19 10:59:37  locher
 * matcher and mailer service including test skeletons
 *
 */
