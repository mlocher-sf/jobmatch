// $Id: ORNode.java,v 1.1 2000/06/20 13:32:56 pse4 Exp $

package jobmatch.business.company.profile.tree;

import java.io.Serializable;
import java.util.*;
import jobmatch.business.candidate.Candidate;

/**
 *  OR - Node
 *
 *  @since May 16 2000
 *  @author $Author: pse4 $
 *  @version $Revision: 1.1 $
 **/
public class ORNode extends OperationNode {

    public ORNode(MatchTree tree, TreeNode parent, TreeNode a, TreeNode b) {
	super(tree, parent);
	this.addNode(a);
	this.addNode(b);
    }

    public String getDescription() {
	return "{OR: " + super.getDescription() + " }";
    }

    protected String getDelimiter() {
	return " | ";
    }

    public MatchResult match(Candidate candidate) {
	Iterator it = this.children.iterator();
/*	while (it.hasNext()) {
	    if ( ((TreeNode) it.next()).match(candidate)) {
		return null;
	    }
	}*/
	return null;
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


/*
 * $Log: ORNode.java,v $
 * Revision 1.1  2000/06/20 13:32:56  pse4
 * Initial revision
 *
 * Revision 1.3  2000/06/13 11:04:48  locher
 * new match interface
 *
 * Revision 1.2  2000/06/02 15:18:52  locher
 * *** empty log message ***
 *
 * Revision 1.1  2000/05/16 07:21:30  locher
 * match tree
 *
 */
