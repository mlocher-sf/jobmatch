// $Id: TestTree.java,v 1.1 2000/05/14 16:18:26 locher Exp $

package jobmatch.business.company.tree.test;

import junit.framework.*;
import jobmatch.business.company.tree.*;

/**
 *  Test the tree classes
 *
 *  @since May 14 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
public class TestTree extends TestCase {
    
    public TestTree(String name) {
	super(name);
    }
    
    protected void setUp() {
	// create fixup here
    }

    public void testSomething() {
	assert(true);
    }

    public static Test suite() { 
	TestSuite suite= new TestSuite(TestTree.class);
	return suite;
    }
	
} //class

// Document history
/*
 * $Log: TestTree.java,v $
 * Revision 1.1  2000/05/14 16:18:26  locher
 * display test results
 *
 */
