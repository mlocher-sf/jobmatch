// $Id: OperationNode.java,v 1.1 2000/05/02 06:46:01 locher Exp $

/*
 * $Log: OperationNode.java,v $
 * Revision 1.1  2000/05/02 06:46:01  locher
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
abstract class OperationNode extends AbstractNode {

    protected List children;

    protected OperationNode(Tree tree) {
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

