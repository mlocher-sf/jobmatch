// $Id: Tree.java,v 1.1 2000/05/02 06:46:02 locher Exp $

/*
 * $Log: Tree.java,v $
 * Revision 1.1  2000/05/02 06:46:02  locher
 * sunday changes
 *
 * Revision 1.1.1.1  2000/04/01 14:04:42  locher
 *
 *
 */

package playground.business.tree;

import java.io.Serializable;

/**
 *  Class description comes here
 *
 *  @since erstellungsdatum
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
public class Tree implements Cloneable, Serializable {

    private TreeNode root;

    public static Tree createDemoTree() {
	return new Tree();
    }
    
    public Tree() {
	super();
	this.root =new ORNode(this,
			      new ANDNode(this,  
					  new NOTNode(this, new BooleanNode(this, false)),
					  new BooleanNode(this, true)),
			      new ANDNode(this,  
					  new BooleanNode(this, false),
					  new BooleanNode(this, true)));
    }


    public TreeNode getRoot() {
	return this.root;
    }

    /** @pre node.getTree() == this **/
    public int getLeafNo(TreeNode node) {
	//	return 23;
	return (int) (500*Math.random());
    }
    
    /** @see Object.equals **/
    public boolean equals(Object other) {
	if (other == null) {
	    return false;
	}
	
	try {
	    return semanticEquality(this, (Tree) other);
	}
	catch (ClassCastException e) {
	    return false;
	}
    }

    /** @return true if a is considered the same as b **/
    private static boolean semanticEquality(Tree a, Tree b) {
	//XXX define the equality and remove the exception !!!
	throw new RuntimeException("semanticEqualitiy not defined");
	//return (a == b);
    }

    /** @see Object.toString **/
    public String toString() {
	return "Tree";
    }

    /** @see Object.clone **/
    public Object clone() {
	return this.cloneTree();
    }
    
    /** creates a clone of this object **/
    private Tree cloneTree() {
	// Do the basic clone
	Tree theClone = null;
	try {
	    theClone = (Tree) super.clone();
	}
	catch (CloneNotSupportedException e) {
	    // Should never happen
	    throw new InternalError(e.toString());
	}

	// Clone mutable members
	// XXX
	return theClone;
    }
       
} //class
