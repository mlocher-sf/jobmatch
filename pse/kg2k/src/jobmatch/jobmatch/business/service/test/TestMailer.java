// $Id: TestMailer.java,v 1.1 2000/05/19 10:59:37 locher Exp $

package jobmatch.business.service.test;

import junit.framework.*;
import jobmatch.business.service.*;

/**
 *  Test the mailer
 *
 *  @since May 19 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
public class TestMailer extends TestCase {

    public TestMailer(String name) {
	super(name);
    }
    
    protected void setUp() {
    }

    public void testSomething() {
    }

    public static Test suite() { 
	TestSuite suite= new TestSuite(TestMailer.class);
	return suite;
    }

} //class

// Document history
/*
 * $Log: TestMailer.java,v $
 * Revision 1.1  2000/05/19 10:59:37  locher
 * matcher and mailer service including test skeletons
 *
 */
