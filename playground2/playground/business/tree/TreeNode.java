// $Id: TreeNode.java,v 1.1 2000/05/02 06:46:03 locher Exp $

/*
 * $Log: TreeNode.java,v $
 * Revision 1.1  2000/05/02 06:46:03  locher
 * sunday changes
 *
 * Revision 1.1.1.1  2000/04/01 14:04:42  locher
 */

package playground.business.tree;

/**
 *  Interface for tree noddes
 *
 *  @since 30.4.2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
public interface TreeNode {
    
    public boolean match(Object candidate);
    public String getDescription();
    public Tree getTree();
    public int getLeafNo();
    public int numChildren();
       
}
