// $Id: TreeNode.java,v 1.1 2000/06/20 13:32:56 pse4 Exp $

package jobmatch.business.company.profile.tree;

import java.io.Serializable;
import jobmatch.business.candidate.Candidate;

/**
 *  Interface for tree nodes
 *
 *  @since May 14 2000
 *  @author $Author: pse4 $
 *  @version $Revision: 1.1 $
 **/
public interface TreeNode extends Serializable {
    public int numChildren();
    public String getDescription();
    public TreeNode getParent();
    public void setParent(TreeNode parent);

    // tree register related
    public MatchTree getTree();
    public void setTree(MatchTree tree);
    public int getLeafNo();    
    public void setLeafNo(int number);

    // operation related
    public void and(TreeNode other);
    public void or(TreeNode other);
    public void not();
    public MatchResult match(Candidate candidate);
}

// Document history
/*
 * $Log: TreeNode.java,v $
 * Revision 1.1  2000/06/20 13:32:56  pse4
 * Initial revision
 *
 * Revision 1.4  2000/06/13 11:04:49  locher
 * new match interface
 *
 * Revision 1.3  2000/05/30 14:23:20  locher
 * tree redesign
 *
 * Revision 1.2  2000/05/16 07:21:31  locher
 * match tree
 *
 * Revision 1.1  2000/05/14 17:09:24  locher
 * begin matchtree
 *
 */
