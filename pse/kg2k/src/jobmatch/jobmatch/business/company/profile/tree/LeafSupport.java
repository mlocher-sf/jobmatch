// $Id: LeafSupport.java,v 1.1 2000/05/30 14:23:19 locher Exp $

package jobmatch.business.company.profile.tree;

import jobmatch.business.candidate.Candidate;

/**
 *  Leafes can delegate to this object
 *
 *  @since May 16 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
public class LeafSupport extends AbstractNode {

    public LeafSupport(MatchTree tree) {
	super(tree);
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

    public boolean match(Candidate candidate) {
	return false;
    }
       
} //class


/*
 * $Log: LeafSupport.java,v $
 * Revision 1.1  2000/05/30 14:23:19  locher
 * tree redesign
 *
 */

