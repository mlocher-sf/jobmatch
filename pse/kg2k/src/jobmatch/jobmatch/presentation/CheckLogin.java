package jobmatch.presentation;

import jobmatch.business.provider.account.*;
import jobmatch.business.util.TimeUtil;

import com.lutris.appserver.server.session.*;
import com.lutris.appserver.server.httpPresentation.*;


/**
 * Checks the login of a CanidateAccount
 **/
public class CheckLogin implements HttpPresentation {

    public static String getLoginURL(String username, String passphrase,
					String grantURL, String denyURL) {
	return  "./CheckLogin.po?username=" + username + "&passphrase=" +
	    passphrase + "&grantURL=" + grantURL + "&denyURL=" + denyURL;
    }

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

	final String username = comms.request.getParameter("username");
	final String passphrase = comms.request.getParameter("passphrase");
	final String grantURL = comms.request.getParameter("grantURL");
	final String denyURL = comms.request.getParameter("denyURL");

	final AccountManager manager = AccountManager.getUniqueInstance();

	if (manager.isValidCandidateLogin(username, passphrase)) {
	    final CandidateAccount account = manager.getCandidateAccount(username);
	    try {
		account.setLoginReminder(0);
		account.setLastLogin(TimeUtil.getTimeNow());
		account.commit();
	    }  catch (Exception e) {
		throw new RuntimeException(e.toString());
	    }
	    this.setAccount(comms.sessionData, account);
	    throw new ClientPageRedirectException(
			  comms.request.getAppFileURIPath(grantURL));
	} else {
	    throw new ClientPageRedirectException(
			  comms.request.getAppFileURIPath(denyURL));
	}

    }

    /**
     * Stores the account in the session data
     **/
    private void setAccount(SessionData sessionData, Account account) {
	try {
	    sessionData.set("account", account);
	} catch (Exception e) {
	    throw new RuntimeException(e.toString());
	}
    }

} //class
