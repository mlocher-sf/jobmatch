// $Id: MatchTree.java,v 1.1 2000/05/14 17:09:24 locher Exp $

package jobmatch.business.company.profile.tree;

import java.io.Serializable;

/**
 *  Match Tree
 *
 *  @since May 14 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
public class MatchTree implements Cloneable, Serializable {

    private int versionId;
    private TreeNode root;
    
    public MatchTree() {
	super();
	this.versionId = 0;
    }
    
    /** @see Object.equals **/
    public boolean equals(Object other) {
	if (other == null) {
	    return false;
	}
	
	try {
	    return semanticEquality(this, (MatchTree) other);
	}
	catch (ClassCastException e) {
	    return false;
	}
    }

    /** @return true if a is considered the same as b **/
    private static boolean semanticEquality(MatchTree a, MatchTree b) {
	//XXX define the equality and remove the exception !!!
	throw new RuntimeException("semanticEqualitiy not defined");
	//return (a == b);
    }

    /** @see Object.toString **/
    public String toString() {
	return super.toString();
    }

    /** @see Object.clone **/
    public Object clone() {
	return this.cloneMatchTree();
    }
    
    /** creates a clone of this object **/
    private MatchTree cloneMatchTree() {
	// Do the basic clone
	MatchTree theClone = null;
	try {
	    theClone = (MatchTree) super.clone();
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

// Document history
/*
 * $Log: MatchTree.java,v $
 * Revision 1.1  2000/05/14 17:09:24  locher
 * begin matchtree
 *
 */
