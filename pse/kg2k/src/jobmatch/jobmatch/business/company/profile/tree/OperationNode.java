// $Id: OperationNode.java,v 1.1 2000/05/16 07:21:30 locher Exp $

package jobmatch.business.company.profile.tree;

import java.io.Serializable;
import java.util.*;

/**
 *  Common behaviour for Operation Nodes
 *
 *  @since May 16 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
abstract class OperationNode extends AbstractNode {

    protected List children;

    protected OperationNode(MatchTree tree) {
	super(tree);
	this.children = new ArrayList();
    }
    
    public String getDescription() {
	String result = "";
	Iterator it = this.children.iterator();
	while (it.hasNext()) {
	    result = result + ((TreeNode) it.next()).getDescription();
	    if (it.hasNext()) {
		result = result + this.getDelimiter();
	    }
	}
	return result;
    }

    protected String getDelimiter() {
	return ", ";
    }

    /** @pre node != null **/
    public void addNode(TreeNode node) {
	this.children.add(node);
    }

    public int numChildren() {
	return this.children.size();
    }
    
    /** @see Object.equals **/
    public boolean equals(Object other) {
	if (other == null) {
	    return false;
	}
	try {
	    return semanticEquality(this, (OperationNode) other);
	}
	catch (ClassCastException e) {
	    return false;
	}
    }

    /** @return true if a is considered the same as b **/
    protected static boolean semanticEquality(OperationNode a, OperationNode b) {
	if (AbstractNode.semanticEquality(a, b)) {
	    Iterator itA = a.children.iterator();
	    Iterator itB = a.children.iterator();
	    while (itA.hasNext() && itB.hasNext()) {
		if ( ! itA.next().equals(itB.next())) {
		    return false;
		}
	    }
	    return (itA.hasNext() == itB.hasNext());
	}
	return false;
    }

    /** @see Object.toString **/
    public String toString() {
	return this.getDescription();
    }

    /** @see Object.clone **/
    public Object clone() {
	return this.cloneOperationNode();
    }
    
    /** creates a clone of this object **/
    private OperationNode cloneOperationNode() {
	// Do the basic clone
	OperationNode theClone = null;
	theClone = (OperationNode) super.clone();

	// Clone mutable members
	theClone.children = (List) ((ArrayList) this.children).clone(); // Attention: only a shallow copy
	return theClone;
    }

} //class


/*
 * $Log: OperationNode.java,v $
 * Revision 1.1  2000/05/16 07:21:30  locher
 * match tree
 *
 */
