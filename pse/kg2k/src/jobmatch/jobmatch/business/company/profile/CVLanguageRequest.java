// $Id: CVLanguageRequest.java,v 1.1 2000/06/13 15:21:25 studer Exp $

package jobmatch.business.company.profile;

import jobmatch.business.candidate.Candidate;
import jobmatch.business.profile.tree.*;
import jobmatch.data.*;

/**
 *  Profile for a language request 
 *
 *  @since June 13 2000
 *  @author $Author: studer $
 *  @version $Revision: 1.1 $
 **/
public class CVLanguageRequest extends LanguageCandidateBDO implements
CVRequest  {

    private LeafSupport leafSupport;

    public CVLanugageRequest(MatchTree tree, TreeNode parent){
	this.leafSupport = new LeafSupport(MatchTree tree, TreeNode parent);
    }

    public int numChildren(){
	return this.leafSupport.numChildren();
    }
    public String getDescription(){
	return this.leafSupport.getDescription();
    }

    public TreeNode getParent(){
	return this.leafSupport.getParent();
    }

    public void setParent(TreeNode parent){
    	this.leafSupport.setParent(parent);
    }

    // tree register related
    public MatchTree getTree(){
	return 	this.leafSupport.getTree();
    }

    public void setTree(MatchTree tree){
	this.leafSupport.setTree(tree);
    }

    public int getLeafNo(){
	return 	this.leafSupport.getLeafNo();
    }

    public void setLeafNo(int number){
	this.leafSupport.setLeafNo(number);
    }

    // operation related
    public void and(TreeNode other){
	this.leafSupport.and(other);
    }

    public void or(TreeNode other){
	this.leafSupport.or(other);
    }
    public void not(){
	this.leafSupport.not();
    }
    
    public MatchResult match(Candidate candidate){
	return	this.leafSupport(candidate);
    }
}

// Document history
/*
 * $Log: CVLanguageRequest.java,v $
 * Revision 1.1  2000/06/13 15:21:25  studer
 * *** empty log message ***
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
