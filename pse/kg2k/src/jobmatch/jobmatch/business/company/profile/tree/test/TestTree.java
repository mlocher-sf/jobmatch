// $Id: TestTree.java,v 1.5 2000/05/30 14:23:22 locher Exp $

package jobmatch.business.company.profile.tree.test;

import junit.framework.*;
import jobmatch.business.company.profile.tree.*;

/**
 *  Test the tree classes
 *
 *  @since May 14 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.5 $
 **/
public class TestTree extends TestCase {

    private MatchTree tree;
    private TreeNode TRUE;
    private TreeNode FALSE;
    
    public TestTree(String name) {
	super(name);
    }
    
    protected void setUp() {
	this.tree = new MatchTree();
	this.TRUE = new BooleanNode(tree, true);
	this.FALSE = new BooleanNode(tree, false);
    }

    public void testBooleanNode() {
	assert(this.TRUE.match(null));
	assert(!this.FALSE.match(null));
	assert(this.TRUE.getTree() == tree);
	assert(this.FALSE.getTree() == tree);
	tree.setRoot(this.TRUE);
	assert(tree.getRoot() == this.TRUE);
	assert(tree.match(null));
    }

    public void testANDNode() {
	TreeNode and11 = new ANDNode(tree, this.TRUE, this.TRUE);
	TreeNode and01 = new ANDNode(tree, this.FALSE, this.TRUE);
	TreeNode and10 = new ANDNode(tree, this.TRUE, this.FALSE);
	TreeNode and00 = new ANDNode(tree, this.FALSE, this.FALSE);
	assert(and11.match(null));
	assert(!and01.match(null));
	assert(!and10.match(null));
	assert(!and00.match(null));
    }

    public void testORNode() {
	TreeNode or11 = new ORNode(tree, this.TRUE, this.TRUE);
	TreeNode or01 = new ORNode(tree, this.FALSE, this.TRUE);
	TreeNode or10 = new ORNode(tree, this.TRUE, this.FALSE);
	TreeNode or00 = new ORNode(tree, this.FALSE, this.FALSE);
	assert(or11.match(null));
	assert(or01.match(null));
	assert(or10.match(null));
	assert(!or00.match(null));
    }

    public void testNOTNode() {
	TreeNode not1 = new NOTNode(tree, this.TRUE);
	TreeNode not0 = new NOTNode(tree, this.FALSE);
	assert(!not1.match(null));
	assert(not0.match(null));
    }

    public static Test suite() { 
	TestSuite suite= new TestSuite(TestTree.class);
	return suite;
    }

} //class

// Document history
/*
 * $Log: TestTree.java,v $
 * Revision 1.5  2000/05/30 14:23:22  locher
 * tree redesign
 *
 * Revision 1.4  2000/05/16 07:39:51  locher
 * test operation nodes
 *
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
