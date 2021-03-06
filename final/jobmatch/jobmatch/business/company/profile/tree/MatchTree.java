// $Id: MatchTree.java,v 1.1 2000/06/20 13:32:55 pse4 Exp $

package jobmatch.business.company.profile.tree;

import java.io.Serializable;
import jobmatch.business.candidate.Candidate;

/**
 *  Match Tree
 *
 *  @since May 14 2000
 *  @author $Author: pse4 $
 *  @version $Revision: 1.1 $
 **/
public class MatchTree implements Cloneable, Serializable {

    private int versionId;
    private TreeNode root;
    private int nextLeafNumber;
    
    public MatchTree() {
	super();
	this.setRoot(new RootNode(this));
	this.versionId = 0;
	this.nextLeafNumber = 0;
    }

    public void registerNode(TreeNode node) {
	node.setTree(this);
	node.setLeafNo(nextLeafNumber++);
    }
    
    public MatchResult match(Candidate candidate) {
	return this.root.match(candidate);
    }

    public TreeNode getRoot() {
	return this.root;
    }

    public void setRoot(TreeNode root) {
	this.root = root;
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
 * Revision 1.1  2000/06/20 13:32:55  pse4
 * Initial revision
 *
 * Revision 1.5  2000/06/13 11:04:47  locher
 * new match interface
 *
 * Revision 1.4  2000/06/02 15:18:51  locher
 * *** empty log message ***
 *
 * Revision 1.3  2000/05/30 14:23:19  locher
 * tree redesign
 *
 * Revision 1.2  2000/05/16 07:21:29  locher
 * match tree
 *
 * Revision 1.1  2000/05/14 17:09:24  locher
 * begin matchtree
 *
 */
