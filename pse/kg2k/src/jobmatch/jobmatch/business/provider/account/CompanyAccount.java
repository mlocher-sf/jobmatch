// $Id: CompanyAccount.java,v 1.2 2000/05/31 12:15:57 studer Exp $

package jobmatch.business.provider.account;

import jobmatch.data.*;
import jobmatch.business.company.Company;

/**
 *  An Account for Companies
 *
 *  @since May 4 2000
 *  @author $Author: studer $
 *  @version $Revision: 1.2 $
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
 * Revision 1.2  2000/05/31 12:15:57  studer
 * Javadoc added
 *
 * Revision 1.1  2000/05/30 15:52:24  locher
 * added Company and Profile BOs
 *
 */
