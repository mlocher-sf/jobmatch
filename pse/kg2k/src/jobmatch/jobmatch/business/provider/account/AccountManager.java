// $Id: AccountManager.java,v 1.6 2000/05/19 10:59:30 locher Exp $

package jobmatch.business.provider.account;

import jobmatch.data.*;
import com.lutris.dods.builder.generator.query.*;

/**
 *  Controls access to accounts
 *
 *  @since May 8 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.6 $
 **/
final public class AccountManager {

    private final static AccountManager uniqueInstance;
    
    static {
	uniqueInstance = new AccountManager();
    }

    private AccountManager() {
	super();
    }

    public static AccountManager getUniqueInstance() {
	return uniqueInstance;
    }

    /**
     * Checks if the specified Login is valid
     **/
    public boolean isValidCandidateLogin(String username, String passphrase) {
	try {
	    CandidateAccountQuery query = new CandidateAccountQuery();
	    query.setQueryUsername(username);
	    CandidateAccountBDO candidate;
	    while ((candidate = query.getNextBDO()) != null){
		if (candidate.getPassphrase().equals(passphrase)) return true;
	    }
	} catch (Exception qe) {
	    System.err.println(qe);
	}
	return false;
    }

    public CandidateAccount createCandidateAccount(String username, String passphrase, String eMail) {
	CandidateAccount account = null;
	if (!candidateUsernameExists(username)) {
	    try {
		account = (CandidateAccount) CandidateAccount.createVirgin(); 
		//verify that cast ! (is it legal??)
		account.setUsername(username);
		account.setPassphrase(passphrase);
		account.setEmail(eMail);
		account.commit();
	    } catch (Exception e) {
		System.err.println(e);
		// should thrown a jobmatchException
	    }
	} else {
	    // throw
	}
	return account;
    }

    public boolean candidateUsernameExists(String username) {
	return false;
    }
    
} //class

/*
 * $Log: AccountManager.java,v $
 * Revision 1.6  2000/05/19 10:59:30  locher
 * matcher and mailer service including test skeletons
 *
 * Revision 1.5  2000/05/17 09:59:48  locher
 * changed password to passphrase
 *
 * Revision 1.4  2000/05/10 17:50:23  locher
 * login procedure
 *
 * Revision 1.3  2000/05/10 12:03:37  studer
 * Query geaendert
 *
 * Revision 1.2  2000/05/08 14:54:52  locher
 * does a simple query
 *
 * Revision 1.1  2000/05/08 14:16:20  locher
 * login procedure
 */
