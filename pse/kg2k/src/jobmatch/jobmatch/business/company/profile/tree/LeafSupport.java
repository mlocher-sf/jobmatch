// $Id: LeafSupport.java,v 1.3 2000/06/13 11:04:46 locher Exp $

package jobmatch.business.company.profile.tree;

import jobmatch.business.candidate.Candidate;

/**
 *  Leafes can delegate to this object
 *
 *  @since May 16 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.3 $
 **/
public class LeafSupport extends AbstractNode {

    public LeafSupport(MatchTree tree, TreeNode parent) {
	super(tree, parent);
    }

    public int numChildren() {
	return 0;
    }

    public String getDescription() {
	return "Leaf";
    }

    public int getLeafNo() {
	throw new RuntimeException("operation not supported");
    }
    
    public void setLeafNo(int number) {
    }

    public MatchResult match(Candidate candidate) {
	return null;
    }
       
} //class


/*
 * $Log: LeafSupport.java,v $
 * Revision 1.3  2000/06/13 11:04:46  locher
 * new match interface
 *
 * Revision 1.2  2000/06/02 15:18:50  locher
 * *** empty log message ***
 *
 * Revision 1.1  2000/05/30 14:23:19  locher
 * tree redesign
 *
 */

