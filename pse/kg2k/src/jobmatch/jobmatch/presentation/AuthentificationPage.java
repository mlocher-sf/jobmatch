package jobmatch.presentation;

import jobmatch.business.provider.account.*;

import com.lutris.appserver.server.httpPresentation.*;

public abstract class AuthentificationPage implements HttpPresentation {

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

    protected Account getAccount(HttpPresentationComms comms) {
	Account account = null;
	try {
	    account = (Account) comms.sessionData.get("account");
	} catch (Exception e) {
	    throw new RuntimeException(e.toString());
	}
	return account;
    }

    protected CandidateAccount getCandidateAccount(HttpPresentationComms comms) {
	return (CandidateAccount) this.getAccount(comms);
    }

} //class
