// $Id: BooleanNode.java,v 1.1 2000/05/30 14:23:21 locher Exp $

package jobmatch.business.company.profile.tree.test;

import jobmatch.business.company.profile.tree.*;
import jobmatch.business.candidate.Candidate;

/**
 *  This node gives a constants answer for match(candidate)
 *
 *  @since May 16 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
public class BooleanNode extends MemoryNode {

    private boolean value;
    
    public BooleanNode(MatchTree tree, boolean value) {
	super(tree);
	this.value = value;
    }

    public TreeNode getParent() { 
	return super.getParent(); 
    }


    public void setParent(TreeNode parent) {
	super.setParent(parent);
    }


    public MatchTree getTree() {
	return super.getTree();
    }

    public void setTree(MatchTree tree) {
	super.setTree(tree);
    }

    public int getLeafNo() {
	return super.getLeafNo();
    }

    public void setLeafNo(int number) {
	super.setLeafNo(number);
    }


    public void and(TreeNode other) {
	super.and(other);
    }

    public void or(TreeNode other) {
	super.or(other);
    }

    public void not() {
	super.not();
    }

    public int numChildren() {
	return 0;
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
 * Revision 1.1  2000/05/30 14:23:21  locher
 * tree redesign
 *
 * Revision 1.1  2000/05/16 07:21:28  locher
 * match tree
 *
 */