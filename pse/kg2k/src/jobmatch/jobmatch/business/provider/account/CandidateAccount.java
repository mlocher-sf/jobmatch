// $Id: CandidateAccount.java,v 1.5 2000/05/23 14:10:55 locher Exp $

package jobmatch.business.provider.account;

import jobmatch.data.*;
import jobmatch.business.candidate.Candidate;

/**
 *  An Account for Candidates
 *
 *  @since May 4 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.5 $
 **/
public class CandidateAccount extends CandidateAccountBDO implements Account {
        
    public CandidateAccount() throws Exception {
	super();
    }

    public CandidateAccount(CandidateAccountDO dataObject) throws Exception {
	super(dataObject);
    }

    public int getType() {
	return TYPE_CANDIDATE;
    }

    public Candidate getCandidateBO() {
	try {
	    return new Candidate(this.getCandidate());
	} catch (Exception e) {
	    throw new RuntimeException(e.toString());
	}
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
           
} //class

/*
 * $Log: CandidateAccount.java,v $
 * Revision 1.5  2000/05/23 14:10:55  locher
 * authentification mechanisms
 *
 * Revision 1.4  2000/05/19 10:59:30  locher
 * matcher and mailer service including test skeletons
 *
 * Revision 1.3  2000/05/17 09:59:48  locher
 * changed password to passphrase
 *
 * Revision 1.2  2000/05/10 17:50:23  locher
 * login procedure
 *
 * Revision 1.1  2000/05/04 14:50:29  locher
 * created little hierarchy
 *
 * Revision 1.1.1.1  2000/04/01 14:04:42  locher
 *
 */
