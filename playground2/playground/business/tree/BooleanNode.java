// $Id: BooleanNode.java,v 1.1 2000/05/02 06:45:58 locher Exp $

/*
 * $Log: BooleanNode.java,v $
 * Revision 1.1  2000/05/02 06:45:58  locher
 * sunday changes
 *
 * Revision 1.1.1.1  2000/04/01 14:04:42  locher
 */

package playground.business.tree;

/**
 *  This node gives a constants answer for match(candidate)
 *
 *  @since 30.4.2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
public class BooleanNode extends ElementaryNode {

    private boolean value;
    
    public BooleanNode(Tree tree, boolean value) {
	super(tree);
	this.value = value;
    }

    public String getDescription() {
	return "BooleanNode: " + (this.value?"true":"false");
    }

    public boolean match(Object candidate) {
	return this.value;
    }

} //class

