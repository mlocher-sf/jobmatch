// $Id: AccountManager.java,v 1.4 2000/05/10 17:50:23 locher Exp $

/*
 * $Log: AccountManager.java,v $
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

package jobmatch.business.provider.account;

import jobmatch.data.*;
import com.lutris.dods.builder.generator.query.*;

/**
 *  Controls access to accounts
 *
 *  @since May 8 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.4 $
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
		if (candidate.getPassword() == passphrase) return true;
	    }
	   // CandidateAccountBDO[] results = query.getBDOArray();
	   // System.out.println("result length: " + 
	//		       ((results!=null)?Integer.toString(results.length):"-"));
		
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
		account.setPassword(passphrase);
		//account.setEMail(eMail);
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
