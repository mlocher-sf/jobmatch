// $Id: NOTNode.java,v 1.2 2000/06/02 15:18:52 locher Exp $

package jobmatch.business.company.profile.tree;

import java.io.Serializable;
import jobmatch.business.candidate.Candidate;

/**
 *  NOT - Node
 *
 *  @since 30.4.2000
 *  @author $Author: locher $
 *  @version $Revision: 1.2 $
 **/
public class NOTNode extends OperationNode {

    private TreeNode child;

    public NOTNode(MatchTree tree, TreeNode parent, TreeNode child) {
	super(tree, parent);
	this.child = child;
	child.setParent(this);
    }
    
    public String getDescription() {
	return "{NOT: " + this.child.getDescription() + " }";
    }

    public boolean match(Candidate candidate) {
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

/*
 * $Log: NOTNode.java,v $
 * Revision 1.2  2000/06/02 15:18:52  locher
 * *** empty log message ***
 *
 * Revision 1.1  2000/05/16 07:21:29  locher
 * match tree
 *
 */
