// $Id: RootNode.java,v 1.1 2000/06/02 15:18:53 locher Exp $

package jobmatch.business.company.profile.tree;

import jobmatch.business.candidate.Candidate;

/**
 *  Root of the tree
 *
 *  @since May 31 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
class RootNode extends MemoryNode {

    RootNode(MatchTree tree) {
	super(tree, null);
    }
    
    public String getDescription() {
	return "Root Node";
    }

    public int numChildren() {
	return 0;
    }
    
    public boolean match(Candidate candidate) {
	throw new RuntimeException("can not match root node");
    }
    
    /** @see Object.equals **/
    public boolean equals(Object other) {
	if (other == null) {
	    return false;
	}
	try {
	    return semanticEquality(this, (RootNode) other);
	}
	catch (ClassCastException e) {
	    return false;
	}
    }

    /** @return true if a is considered the same as b **/
    protected boolean semanticEquality(RootNode a, RootNode b) {
	return a == b;
    }

    /** @see Object.toString **/
    public String toString() {
	return this.getDescription();
    }

    /** @see Object.clone **/
    public Object clone() {
	return this.cloneRootNode();
    }
    
    /** creates a clone of this object **/
    private RootNode cloneRootNode() {
	// Do the basic clone
	RootNode theClone = null;
	theClone = (RootNode) super.clone();
	return theClone;
    }

} //class


/*
 * $Log: RootNode.java,v $
 * Revision 1.1  2000/06/02 15:18:53  locher
 * *** empty log message ***
 *
 */
