// $Id: AbstractNode.java,v 1.1 2000/05/02 06:45:57 locher Exp $

/*
 * $Log: AbstractNode.java,v $
 * Revision 1.1  2000/05/02 06:45:57  locher
 * sunday changes
 *
 * Revision 1.1.1.1  2000/04/01 14:04:42  locher
 */

package playground.business.tree;

import java.io.Serializable;

/**
 *  Common behaviour for Nodes
 *
 *  @since 30.4.2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
abstract class AbstractNode implements TreeNode, Cloneable, Serializable {

    private Tree tree;

    protected AbstractNode(Tree tree) {
	this.tree = tree;
    }
    
    public String getDescription() {
	return "Elementary Node";
   }
       
    public Tree getTree() {
	return this.tree;
    }

    public int getLeafNo() {
	return this.getTree().getLeafNo(this);
    }

    /** @see Object.equals **/
    public boolean equals(Object other) {
	if (other == null) {
	    return false;
	}
	try {
	    return semanticEquality(this, (AbstractNode) other);
	}
	catch (ClassCastException e) {
	    return false;
	}
    }

    /** @return true if a is considered the same as b **/
    protected static boolean semanticEquality(AbstractNode a, AbstractNode b) {
	return (a.tree == b.tree);
    }

    /** @see Object.toString **/
    public String toString() {
	return this.getDescription();
    }

    /** @see Object.clone **/
    public Object clone() {
	return this.cloneAbstractNode();
    }
    
    /** creates a clone of this object **/
    private AbstractNode cloneAbstractNode() {
	// Do the basic clone
	AbstractNode theClone = null;
	try {
	    theClone = (AbstractNode) super.clone();
	}
	catch (CloneNotSupportedException e) {
	    // Should never happen
	    throw new InternalError(e.toString());
	}

	// Clone mutable members
	theClone.tree = this.tree;
	return theClone;
    }
       
} //class

