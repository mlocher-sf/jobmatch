// $Id: ANDNode.java,v 1.2 2000/06/02 15:18:50 locher Exp $

package jobmatch.business.company.profile.tree;

import java.io.Serializable;
import java.util.*;
import jobmatch.business.candidate.Candidate;

/**
 *  AND - Node
 *
 *  @since May 16 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.2 $
 **/
public class ANDNode extends OperationNode {

    public ANDNode(MatchTree tree, TreeNode parent, TreeNode a, TreeNode b) {
	super(tree, parent);
	this.addNode(a);
	this.addNode(b);
    }
    
    public String getDescription() {
	return "{AND: " + super.getDescription() + " }";
    }

    protected String getDelimiter() {
	return " & ";
    }


    public boolean match(Candidate candidate) {
	Iterator it = this.children.iterator();
	while (it.hasNext()) {
	    if (! ((TreeNode) it.next()).match(candidate)) {
		return false;
	    }
	}
	return true;
    }
    
    /** @see Object.equals **/
    public boolean equals(Object other) {
	if (other == null) {
	    return false;
	}
	try {
	    return semanticEquality(this, (ANDNode) other);
	}
	catch (ClassCastException e) {
	    return false;
	}
    }

    /** @return true if a is considered the same as b **/
    protected static boolean semanticEquality(ANDNode a, ANDNode b) {
	return OperationNode.semanticEquality(a, b);
    }

    /** @see Object.toString **/
    public String toString() {
	return this.getDescription();
    }

    /** @see Object.clone **/
    public Object clone() {
	return this.cloneANDNode();
    }
    
    /** creates a clone of this object **/
    private ANDNode cloneANDNode() {
	// Do the basic clone
	ANDNode theClone = null;
	theClone = (ANDNode) super.clone();

	return theClone;
    }
       
} //class

/*
 * $Log: ANDNode.java,v $
 * Revision 1.2  2000/06/02 15:18:50  locher
 * *** empty log message ***
 *
 * Revision 1.1  2000/05/16 07:21:27  locher
 * match tree
 *
 */
