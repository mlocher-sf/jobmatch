// $Id: AccountManager.java,v 1.11 2000/06/02 14:56:14 locher Exp $

package jobmatch.business.provider.account;

import jobmatch.data.*;
import com.lutris.dods.builder.generator.query.*;

/**
 *  Controls access to accounts
 *
 *  @since May 8 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.11 $
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

    //Candidate------------------------------------------------------------
    
    /**
     * Checks if the specified Login is valid
     **/
    public boolean isValidCandidateLogin(String username, String passphrase) {
	try {
	    CandidateAccountQuery query = new CandidateAccountQuery();
	    query.setQueryUsername(username);
	    final CandidateAccountBDO candidate = query.getNextBDO();
	    if (candidate != null && 
		candidate.getPassphrase().equals(passphrase)) {	return true; }
	} catch (Exception qe) {
	    System.err.println(qe);
	}
	return false;
    }

    /**
     * Returns the account of the candidate with the specified username
     **/
    public CandidateAccount getCandidateAccount(String username) {
	CandidateAccount result = null;
	try {
	    CandidateAccountQuery query = new CandidateAccountQuery();
	    query.setQueryUsername(username);
	    result = new CandidateAccount(query.getNextDO());
	} catch (Exception qe) {
	    System.err.println(qe);
	}
	return result;	
    }

    /**
     * Creates a new Candidate account
     **/
    public void createCandidateAccount(String username, String passphrase, String eMail) {
	if (!candidateUsernameExists(username)) {
	    try {
		CandidateBDO candidate = CandidateBDO.createVirgin();
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

    /**
     * Checks if the specified username exists in the DB
     **/
    public boolean candidateUsernameExists(String username) {
	try {
	    CandidateAccountQuery query = new CandidateAccountQuery();
	    query.setQueryUsername(username);
	    final CandidateAccountBDO candidate = query.getNextBDO();
	    if (candidate != null) { return true; }
	} catch (Exception qe) {
	    System.err.println(qe);
	}
	return false;
    }

    //Company------------------------------------------------------------
    
    /**
     * Checks if the specified Login is valid
     **/
    public boolean isValidCompanyLogin(String username, String passphrase) {
	try {
	    CompanyAccountQuery query = new CompanyAccountQuery();
	    query.setQueryUsername(username);
	    final CompanyAccountBDO company = query.getNextBDO();
	    if (company != null && 
		company.getPassphrase().equals(passphrase)) {	return true; }
	} catch (Exception qe) {
	    System.err.println(qe);
	}
	return false;
    }

    /**
     * Returns the account of the company with the specified username
     **/
    public CompanyAccount getCompanyAccount(String username) {
	CompanyAccount result = null;
	try {
	    CompanyAccountQuery query = new CompanyAccountQuery();
	    query.setQueryUsername(username);
	    result = new CompanyAccount(query.getNextDO());
	} catch (Exception qe) {
	    System.err.println(qe);
	}
	return result;	
    }

    /**
     * Creates a new Company account
     **/
    public void createCompanyAccount(String username, String passphrase, String eMail) {
	if (!companyUsernameExists(username)) {
	    try {
		CompanyBDO company = CompanyBDO.createVirgin();
		company.commit();
		CompanyAccountBDO account = CompanyAccountBDO.createVirgin(); 
		account.setUsername(username);
		account.setPassphrase(passphrase);
		account.setEmail(eMail);
		account.setCompany(company);
		account.commit();
	    } catch (Exception e) {
		System.err.println(e);
		throw new RuntimeException("db error");
	    }
	} else {
	    throw new RuntimeException("username exists");
	}
    }

    /**
     * Checks if the specified username exists in the DB
     **/
    public boolean companyUsernameExists(String username) {
	try {
	    CompanyAccountQuery query = new CompanyAccountQuery();
	    query.setQueryUsername(username);
	    final CompanyAccountBDO company = query.getNextBDO();
	    if (company != null) { return true; }
	} catch (Exception qe) {
	    System.err.println(qe);
	}
	return false;
    }

    //Provider------------------------------------------------------------
    
    /**
     * Checks if the specified Login is valid
     **/
    public boolean isValidProviderLogin(String username, String passphrase) {
	try {
	    ProviderAccountQuery query = new ProviderAccountQuery();
	    query.setQueryUsername(username);
	    final ProviderAccountBDO provider = query.getNextBDO();
	    if (provider != null && 
		provider.getPassphrase().equals(passphrase)) {	return true; }
	} catch (Exception qe) {
	    System.err.println(qe);
	}
	return false;
    }

    /**
     * Returns the account of the provider with the specified username
     **/
    public ProviderAccount getProviderAccount(String username) {
	ProviderAccount result = null;
	try {
	    ProviderAccountQuery query = new ProviderAccountQuery();
	    query.setQueryUsername(username);
	    result = new ProviderAccount(query.getNextDO());
	} catch (Exception qe) {
	    System.err.println(qe);
	}
	return result;	
    }

    /**
     * Creates a new Provider account
     **/
    public void createProviderAccount(String username, String passphrase, String eMail) {
	if (!providerUsernameExists(username)) {
	    try {
		ProviderAccountBDO account = ProviderAccountBDO.createVirgin(); 
		account.setUsername(username);
		account.setPassphrase(passphrase);
		account.setEmail(eMail);
		account.commit();
	    } catch (Exception e) {
		System.err.println(e);
		throw new RuntimeException("db error");
	    }
	} else {
	    throw new RuntimeException("username exists");
	}
    }

    /**
     * Checks if the specified username exists in the DB
     **/
    public boolean providerUsernameExists(String username) {
	try {
	    ProviderAccountQuery query = new ProviderAccountQuery();
	    query.setQueryUsername(username);
	    final ProviderAccountBDO provider = query.getNextBDO();
	    if (provider != null) { return true; }
	} catch (Exception qe) {
	    System.err.println(qe);
	}
	return false;
    }

} //class

/*
 * $Log: AccountManager.java,v $
 * Revision 1.11  2000/06/02 14:56:14  locher
 * extended login behaviour
 *
 * Revision 1.10  2000/05/31 12:15:55  studer
 * Javadoc added
 *
 * Revision 1.9  2000/05/30 15:52:23  locher
 * added Company and Profile BOs
 *
 * Revision 1.8  2000/05/23 14:10:55  locher
 * authentification mechanisms
 *
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
