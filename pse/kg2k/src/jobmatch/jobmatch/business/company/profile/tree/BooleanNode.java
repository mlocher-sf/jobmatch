// $Id: BooleanNode.java,v 1.1 2000/05/16 07:21:28 locher Exp $

package jobmatch.business.company.profile.tree;

import jobmatch.business.candidate.Candidate;

/**
 *  This node gives a constants answer for match(candidate)
 *
 *  @since May 16 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
public class BooleanNode extends ElementaryNode {

    private boolean value;
    
    public BooleanNode(MatchTree tree, boolean value) {
	super(tree);
	this.value = value;
    }

    public String getDescription() {
	return "BooleanNode: " + (this.value?"true":"false");
    }

    public boolean match(Candidate candidate) {
	return this.value;
    }

} //class

/*
 * $Log: BooleanNode.java,v $
 * Revision 1.1  2000/05/16 07:21:28  locher
 * match tree
 *
 */
