// $Id: AccountManager.java,v 1.3 2000/05/10 12:03:37 studer Exp $

/*
 * $Log: AccountManager.java,v $
 * Revision 1.3  2000/05/10 12:03:37  studer
 * Query geaendert
 *
 * Revision 1.2  2000/05/08 14:54:52  locher
 * does a simple query
 *
 * Revision 1.1  2000/05/08 14:16:20  locher
 * login procedure
 *
 *
 */

package jobmatch.business.provider.account;

import jobmatch.data.*;
import com.lutris.dods.builder.generator.query.*;

/**
 *  Controls access to accounts
 *
 *  @since May 8 2000
 *  @author $Author: studer $
 *  @version $Revision: 1.3 $
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
    public boolean isValidLogin(String username, String passphrase) {
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
    
} //class
