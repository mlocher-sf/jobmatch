// $Id: AccountManager.java,v 1.7 2000/05/19 15:28:32 locher Exp $

package jobmatch.business.provider.account;

import jobmatch.data.*;
import com.lutris.dods.builder.generator.query.*;

/**
 *  Controls access to accounts
 *
 *  @since May 8 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.7 $
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
		System.err.println(candidate.getUsername());
		if (candidate.getPassphrase().equals(passphrase)) return true;
	    }
	} catch (Exception qe) {
	    System.err.println(qe);
	}
	return false;
    }

    public void createCandidateAccount(String username, String passphrase, String eMail) {
	if (!candidateUsernameExists(username)) {
	    try {
		CandidateBDO candidate = CandidateBDO.createVirgin();
		candidate.setAIESECMember(false);
		candidate.commit();
		CandidateAccountBDO account = CandidateAccountBDO.createVirgin(); 
		account.setUsername(username);
		account.setPassphrase(passphrase);
		account.setEmail(eMail);
		account.setCandidate(candidate);
		account.commit();
	    } catch (Exception e) {
		System.err.println(e);
		throw new RuntimeException("db error");
	    }
	} else {
	    throw new RuntimeException("username exists");
	}
    }

    public boolean candidateUsernameExists(String username) {
	return false;
    }
    
} //class

/*
 * $Log: AccountManager.java,v $
 * Revision 1.7  2000/05/19 15:28:32  locher
 * create login
 *
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
