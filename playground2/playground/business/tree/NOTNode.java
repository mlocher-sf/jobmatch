// $Id: NOTNode.java,v 1.1 2000/05/02 06:45:59 locher Exp $

/*
 * $Log: NOTNode.java,v $
 * Revision 1.1  2000/05/02 06:45:59  locher
 * sunday changes
 *
 * Revision 1.1.1.1  2000/04/01 14:04:42  locher
 */

package playground.business.tree;

import java.io.Serializable;

/**
 *  Common behaviour for Operation Nodes
 *
 *  @since 30.4.2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
public class NOTNode extends OperationNode {

    private TreeNode child;

    public NOTNode(Tree tree, TreeNode child) {
	super(tree);
	this.child = child;
    }
    
    public String getDescription() {
	return "{NOT: " + this.child.getDescription() + " }";
    }

    public boolean match(Object candidate) {
	return ! this.child.match(candidate);
    }
    
    /** @see Object.equals **/
    public boolean equals(Object other) {
	if (other == null) {
	    return false;
	}
	try {
	    return semanticEquality(this, (NOTNode) other);
	}
	catch (ClassCastException e) {
	    return false;
	}
    }

    /** @return true if a is considered the same as b **/
    protected static boolean semanticEquality(NOTNode a, NOTNode b) {
	return OperationNode.semanticEquality(a, b) && a.child.equals(b.child);
    }

    /** @see Object.toString **/
    public String toString() {
	return this.getDescription();
    }

    /** @see Object.clone **/
    public Object clone() {
	return this.cloneNOTNode();
    }
    
    /** creates a clone of this object **/
    private NOTNode cloneNOTNode() {
	// Do the basic clone
	NOTNode theClone = null;
	theClone = (NOTNode) super.clone();

	// Clone mutable members
	theClone.child = this.child;
	return theClone;
    }
       
} //class

