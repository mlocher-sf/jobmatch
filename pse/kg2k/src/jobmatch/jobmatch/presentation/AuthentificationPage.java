package jobmatch.presentation;

import jobmatch.business.provider.account.*;

import com.lutris.appserver.server.httpPresentation.*;

/**
 * Provides Methods to legitimate an account
 **/
public abstract class AuthentificationPage implements HttpPresentation {

    /**
     * Checks the type of the current account
     **/
    protected boolean isLegitimated(HttpPresentationComms comms, int accountType) {
	try {
	    Account account = this.getAccount(comms);
	    if (account != null && account.getType() == accountType) {return true;}
	} catch (Exception e) {
	    throw new RuntimeException(e.toString());
	}
	return false;
    }

    protected void assertLegitimation(HttpPresentationComms comms,  
				      int accountType, String failURL) {
	if (!this.isLegitimated(comms, accountType)) {
	    try {
		throw new ClientPageRedirectException(comms.request.getAppFileURIPath(failURL));
	    } catch (Exception e) {
		throw new RuntimeException(e.toString());
	    }
	}
    }

    /**
     * Returns the current account
     **/
    protected Account getAccount(HttpPresentationComms comms) {
	Account account = null;
	try {
	    account = (Account) comms.sessionData.get("account");
	} catch (Exception e) {
	    throw new RuntimeException(e.toString());
	}
	return account;
    }

    /**
     * Return the current candidate account
     **/
    protected CandidateAccount getCandidateAccount(HttpPresentationComms comms) {
	return (CandidateAccount) this.getAccount(comms);
    }

    /**
     * Return the current company account
     **/
    protected CompanyAccount getCompanyAccount(HttpPresentationComms comms) {
	return (CompanyAccount) this.getAccount(comms);
    }

    /**
     * Return the current provider account
     **/
    protected ProviderAccount getProviderAccount(HttpPresentationComms comms) {
	return (ProviderAccount) this.getAccount(comms);
    }

} //class
