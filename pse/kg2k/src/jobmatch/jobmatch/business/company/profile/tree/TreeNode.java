// $Id: TreeNode.java,v 1.2 2000/05/16 07:21:31 locher Exp $

package jobmatch.business.company.profile.tree;

import java.io.Serializable;
import jobmatch.business.candidate.Candidate;

/**
 *  Interface for tree nodes
 *
 *  @since May 14 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.2 $
 **/
public interface TreeNode extends Serializable {
    
    public boolean match(Candidate candidate);
    public String getDescription();
    public MatchTree getTree();
    public int getLeafNo();
    public int numChildren();
}

// Document history
/*
 * $Log: TreeNode.java,v $
 * Revision 1.2  2000/05/16 07:21:31  locher
 * match tree
 *
 * Revision 1.1  2000/05/14 17:09:24  locher
 * begin matchtree
 *
 */
