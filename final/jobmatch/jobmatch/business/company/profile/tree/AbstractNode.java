// $Id: AbstractNode.java,v 1.1 2000/06/20 13:32:55 pse4 Exp $

package jobmatch.business.company.profile.tree;

import java.io.Serializable;

/**
 *  Common behaviour for Nodes
 *
 *  @since May 16 2000
 *  @author $Author: pse4 $
 *  @version $Revision: 1.1 $
 **/
abstract class AbstractNode implements TreeNode, Cloneable, Serializable {

    private MatchTree tree;
    private TreeNode parent;

    protected AbstractNode(MatchTree tree, TreeNode parent) {
	tree.registerNode(this);
	this.setParent(parent);
    }
    
    public String getDescription() {
	return "Abstract Node";
   }
       
    public MatchTree getTree() {
	return this.tree;
    }

    public void setTree(MatchTree tree) {
	this.tree = tree;
    }

    public TreeNode getParent() {
	return this.parent;
    }

    public void setParent(TreeNode parent) {
	this.parent = parent;
    }

    public void and(TreeNode other) {
	TreeNode parent = this.getParent();
	if (parent instanceof ANDNode) {
	    parent.and(other);
	} else {
	    ANDNode node = new ANDNode(this.getTree(), 
				       this.getParent(), 
				       this, other);
//	    parent.replace(this, node);
	}
    }

    public void or(TreeNode other) {
    }

    public void not() {
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


/*
 * $Log: AbstractNode.java,v $
 * Revision 1.1  2000/06/20 13:32:55  pse4
 * Initial revision
 *
 * Revision 1.3  2000/06/02 15:18:50  locher
 * *** empty log message ***
 *
 * Revision 1.2  2000/05/30 14:23:17  locher
 * tree redesign
 *
 * Revision 1.1  2000/05/16 07:21:27  locher
 * match tree
 *
 */
