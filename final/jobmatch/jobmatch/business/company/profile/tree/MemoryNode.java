// $Id: MemoryNode.java,v 1.1 2000/06/20 13:32:55 pse4 Exp $

package jobmatch.business.company.profile.tree;

import java.io.Serializable;

/**
 *  Common behaviour for Nodes which life in memory
 *
 *  @since May 16 2000
 *  @author $Author: pse4 $
 *  @version $Revision: 1.1 $
 **/
public abstract class MemoryNode extends AbstractNode {
    private int leafNumber;

    protected MemoryNode(MatchTree tree, TreeNode parent) {
	super(tree, parent);
    }

    public int getLeafNo() {
	return this.leafNumber;
    }

    public void setLeafNo(int number) {
	this.leafNumber = number;
    }
       
} //class


/*
 * $Log: MemoryNode.java,v $
 * Revision 1.1  2000/06/20 13:32:55  pse4
 * Initial revision
 *
 * Revision 1.2  2000/06/02 15:18:51  locher
 * *** empty log message ***
 *
 * Revision 1.1  2000/05/30 14:23:20  locher
 * tree redesign
 *
 */

