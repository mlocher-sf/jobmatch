// $Id: CandidateAccount.java,v 1.2 2000/05/10 17:50:23 locher Exp $

/*
 * $Log: CandidateAccount.java,v $
 * Revision 1.2  2000/05/10 17:50:23  locher
 * login procedure
 *
 * Revision 1.1  2000/05/04 14:50:29  locher
 * created little hierarchy
 *
 * Revision 1.1.1.1  2000/04/01 14:04:42  locher
 *
 */

package jobmatch.business.provider.account;

import jobmatch.data.*;

// RENAME the DB Field Password to Passphrase and kill this import !!
import com.lutris.dods.builder.generator.query.DataObjectException;

/**
 *  An Account for Candidates
 *
 *  @since May 4 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.2 $
 **/
public class CandidateAccount extends CandidateAccountBDO implements Account {
        
    public CandidateAccount() throws Exception {
	super();
	System.out.println("new Candidate Account created");
    }

    public int getType() {
	return TYPE_CANDIDATE;
    }

    // RENAME the DB Field Password to Passphrase and kill this method !!
    public String getPassphrase() throws DataObjectException {
	return this.getPassword();
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
