// $Id: TestTree.java,v 1.3 2000/05/16 07:20:30 locher Exp $

package jobmatch.business.company.tree.test;

import junit.framework.*;
import jobmatch.business.company.profile.tree.*;

/**
 *  Test the tree classes
 *
 *  @since May 14 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.3 $
 **/
public class TestTree extends TestCase {

    private MatchTree tree;
    
    public TestTree(String name) {
	super(name);
    }
    
    protected void setUp() {
	this.tree = new MatchTree();
    }

    public void testBooleanNode() {
	TreeNode node = new BooleanNode(tree, true);
	tree.setRoot(node);
	assert(node.getTree() == tree);
	assert(tree.getRoot() == node);
	assert(tree.match(null));
    }

    public void testANDNode() {

    }

    public static Test suite() { 
	TestSuite suite= new TestSuite(TestTree.class);
	return suite;
    }

} //class

// Document history
/*
 * $Log: TestTree.java,v $
 * Revision 1.3  2000/05/16 07:20:30  locher
 * match tree
 *
 * Revision 1.2  2000/05/14 17:09:26  locher
 * begin matchtree
 *
 * Revision 1.1  2000/05/14 16:18:26  locher
 * display test results
 *
 */
