// $Id: ORNode.java,v 1.1 2000/05/02 06:46:00 locher Exp $

/*
 * $Log: ORNode.java,v $
 * Revision 1.1  2000/05/02 06:46:00  locher
 * sunday changes
 *
 * Revision 1.1.1.1  2000/04/01 14:04:42  locher
 */

package playground.business.tree;

import java.io.Serializable;
import java.util.*;

/**
 *  Common behaviour for Operation Nodes
 *
 *  @since 30.4.2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
public class ORNode extends OperationNode {

    public ORNode(Tree tree, TreeNode a, TreeNode b) {
	super(tree);
	this.addNode(a);
	this.addNode(b);
    }

    public String getDescription() {
	return "{OR: " + super.getDescription() + " }";
    }

    protected String getDelimiter() {
	return " | ";
    }

    public boolean match(Object candidate) {
	Iterator it = this.children.iterator();
	while (it.hasNext()) {
	    if ( ((TreeNode) it.next()).match(candidate)) {
		return true;
	    }
	}
	return false;
    }
    
    /** @see Object.equals **/
    public boolean equals(Object other) {
	if (other == null) {
	    return false;
	}
	try {
	    return semanticEquality(this, (ORNode) other);
	}
	catch (ClassCastException e) {
	    return false;
	}
    }

    /** @return true if a is considered the same as b **/
    protected static boolean semanticEquality(ORNode a, ORNode b) {
	return OperationNode.semanticEquality(a, b);
    }

    /** @see Object.toString **/
    public String toString() {
	return this.getDescription();
    }

    /** @see Object.clone **/
    public Object clone() {
	return this.cloneORNode();
    }
    
    /** creates a clone of this object **/
    private ORNode cloneORNode() {
	// Do the basic clone
	ORNode theClone = null;
	theClone = (ORNode) super.clone();

	return theClone;
    }
       
} //class

