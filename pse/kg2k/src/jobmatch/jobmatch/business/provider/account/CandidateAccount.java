// $Id: CandidateAccount.java,v 1.9 2000/06/03 09:15:41 locher Exp $

package jobmatch.business.provider.account;

import jobmatch.data.*;
import java.sql.Timestamp;
import jobmatch.business.candidate.Candidate;

/**
 *  An Account for Candidates
 *
 *  @since May 4 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.9 $
 **/
public class CandidateAccount extends CandidateAccountBDO implements Account {
        
    CandidateAccount() throws Exception {
	super();
    }

    CandidateAccount(CandidateAccountDO dataObject) throws Exception {
	super(dataObject);
    }

    public int getType() {
	return TYPE_CANDIDATE;
    }

   /**
    * Updates the login tracking fields of the account
    **/
    public void updateLoginData(Timestamp time, String host, String ip) {
	try {
	    this.setLoginReminder(0);
	    this.setLastLogin(time);
 	    this.setLastIP(ip);
 	    this.setLastHost(host);
	    this.commit();
	}  catch (Exception e) {
	    throw new RuntimeException(e.toString());
	}
    }

    /**
     * Returns the candidate business object for this account
     **/
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
 * Revision 1.9  2000/06/03 09:15:41  locher
 * IP logging enabled
 *
 * Revision 1.8  2000/06/02 14:56:14  locher
 * extended login behaviour
 *
 * Revision 1.7  2000/05/31 12:15:56  studer
 * Javadoc added
 *
 * Revision 1.6  2000/05/30 15:52:24  locher
 * added Company and Profile BOs
 *
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
