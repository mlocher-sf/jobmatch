// $Id: AccountManager.java,v 1.1 2000/05/08 14:16:20 locher Exp $

/*
 * $Log: AccountManager.java,v $
 * Revision 1.1  2000/05/08 14:16:20  locher
 * login procedure
 *
 *
 */

package jobmatch.business.provider.account;

import jobmatch.data.*;
import com.lutris.dods.builder.generator.query.*;

/**
 *  An Account for Candidates
 *
 *  @since May 8 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
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
    
    public boolean isValidLogin(String username, String passphrase) {
	try {
	    CandidateAccountQuery query = new CandidateAccountQuery();
	    
	    query.setQueryUsername(username);
	    query.setQueryPassword(passphrase);
	} catch (QueryException qe) {
	    System.err.println(qe);
	}
	
// 	PersonDO[] bobs = pq.getDOArray();
//      for ( int i = 0; i < bobs.length; i++ ) {
//           AddressQuery aq = new AddressQuery();
//           aq.setQueryPerson( bobs[i] );
//           AddressDO addr = aq.getNextDO();
//           String city = addr.getCity();
	return true;
    }
    
} //class
