// $Id: ElementaryNode.java,v 1.1 2000/05/02 06:45:58 locher Exp $

/*
 * $Log: ElementaryNode.java,v $
 * Revision 1.1  2000/05/02 06:45:58  locher
 * sunday changes
 *
 * Revision 1.1.1.1  2000/04/01 14:04:42  locher
 */

package playground.business.tree;

import java.io.Serializable;

/**
 *  Common behaviour for Leaf Nodes
 *
 *  @since 30.4.2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
abstract class ElementaryNode extends AbstractNode {

    protected ElementaryNode(Tree tree) {
	super(tree);
    }
    
    public final int numChildren() {
	return 0;
    }

    public String getDescription() {
	return "Elementary Node";
    }

    
    /** @see Object.equals **/
    public boolean equals(Object other) {
	if (other == null) {
	    return false;
	}
	try {
	    return semanticEquality(this, (ElementaryNode) other);
	}
	catch (ClassCastException e) {
	    return false;
	}
    }

    /** @return true if a is considered the same as b **/
    protected static boolean semanticEquality(ElementaryNode a, ElementaryNode b) {
	return AbstractNode.semanticEquality(a, b);
    }

    /** @see Object.toString **/
    public String toString() {
	return this.getDescription();
    }

    /** @see Object.clone **/
    public Object clone() {
	return this.cloneElementaryNode();
    }
    
    /** creates a clone of this object **/
    private ElementaryNode cloneElementaryNode() {
	// Do the basic clone
	ElementaryNode theClone = null;
	theClone = (ElementaryNode) super.clone();

	// Clone mutable members
	// XXX
	return theClone;
    }
       
} //class

