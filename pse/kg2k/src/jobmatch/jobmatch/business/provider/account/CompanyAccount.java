// $Id: CompanyAccount.java,v 1.4 2000/06/03 09:15:42 locher Exp $

package jobmatch.business.provider.account;

import jobmatch.data.*;
import java.sql.Timestamp;
import jobmatch.business.company.Company;

/**
 *  An Account for Companies
 *
 *  @since May 4 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.4 $
 **/
public class CompanyAccount extends CompanyAccountBDO implements Account {
        
    CompanyAccount() throws Exception {
	super();
    }

    CompanyAccount(CompanyAccountDO dataObject) throws Exception {
	super(dataObject);
    }

    public int getType() {
	return TYPE_COMPANY;
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
    public Company getCompanyBO() {
	try {
	    return new Company(this.getCompany());
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
	    return semanticEquality(this, (CompanyAccount) other);
	}
	catch (ClassCastException e) {
	    return false;
	}
    }

    /** @return true if a is considered the same as b **/
    private boolean semanticEquality(CompanyAccount a, CompanyAccount b) {
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
 * $Log: CompanyAccount.java,v $
 * Revision 1.4  2000/06/03 09:15:42  locher
 * IP logging enabled
 *
 * Revision 1.3  2000/06/02 14:56:14  locher
 * extended login behaviour
 *
 * Revision 1.2  2000/05/31 12:15:57  studer
 * Javadoc added
 *
 * Revision 1.1  2000/05/30 15:52:24  locher
 * added Company and Profile BOs
 *
 */
