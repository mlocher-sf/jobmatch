// $Id: ProviderAccount.java,v 1.3 2000/06/13 07:31:47 locher Exp $

package jobmatch.business.provider.account;

import jobmatch.data.*;
import java.sql.Timestamp;


/**
 *  An Account for Administrators
 *
 *  @since May 4 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.3 $
 **/
public class ProviderAccount extends ProviderAccountBDO implements Account {
        
    public static final String ROOT_USERNAME = "root";
    public static final String DEFAULT_ROOT_PASSPHRASE = "root";

    ProviderAccount() throws Exception {
	super();
    }

    ProviderAccount(ProviderAccountDO dataObject) throws Exception {
	super(dataObject);
    }

    public int getType() {
	return TYPE_PROVIDER;
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
    
    /** @see Object.equals **/
    public boolean equals(Object other) {
	if (other == null) {
	    return false;
	}
	
	try {
	    return semanticEquality(this, (ProviderAccount) other);
	}
	catch (ClassCastException e) {
	    return false;
	}
    }

    /** @return true if a is considered the same as b **/
    private boolean semanticEquality(ProviderAccount a, ProviderAccount b) {
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
 * $Log: ProviderAccount.java,v $
 * Revision 1.3  2000/06/13 07:31:47  locher
 * assure the root account exists
 *
 * Revision 1.2  2000/06/03 09:15:42  locher
 * IP logging enabled
 *
 * Revision 1.1  2000/06/02 14:56:15  locher
 * extended login behaviour
 *
 */
