// $Id: CandidateAccount.java,v 1.1 2000/05/04 14:50:29 locher Exp $

/*
 * $Log: CandidateAccount.java,v $
 * Revision 1.1  2000/05/04 14:50:29  locher
 * created little hierarchy
 *
 * Revision 1.1.1.1  2000/04/01 14:04:42  locher
 *
 */

package jobmatch.business.provider.account;

import java.io.Serializable;

/**
 *  An Account for Candidates
 *
 *  @since May 4 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
public class CandidateAccount extends AbstractAccount {

    
    public CandidateAccount() {
	super();
    }

    public int getType() {
	return TYPE_CANDIDATE;
    }
    
    /** @see Object.equals **/
    public boolean equals(Object other) {
	if (other == null) {
	    return false;
	}
	
	try {
	    return semanticEquality(this, (CandidateAccount) other);
	}
	catch (ClassCastException e) {
	    return false;
	}
    }

    /** @return true if a is considered the same as b **/
    private static boolean semanticEquality(CandidateAccount a, CandidateAccount b) {
	//XXX define the equality and remove the exception !!!
	throw new RuntimeException("semanticEqualitiy not defined");
	//return (a == b);
    }

    /** @see Object.toString **/
    public String toString() {
	return super.toString();
    }

    /** @see Object.clone **/
    public Object clone() {
	return this.cloneCandidateAccount();
    }
    
    /** creates a clone of this object **/
    private CandidateAccount cloneCandidateAccount() {
	// Do the basic clone
	CandidateAccount theClone = null;
	theClone = (CandidateAccount) super.clone();

	// Clone mutable members
	// XXX
	return theClone;
    }
       
} //class
