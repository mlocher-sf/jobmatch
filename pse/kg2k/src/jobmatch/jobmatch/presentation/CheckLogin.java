package jobmatch.presentation;

import jobmatch.business.provider.account.*;

import com.lutris.appserver.server.httpPresentation.*;

public class CheckLogin implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

	final String username = comms.request.getParameter("username");
	final String passphrase = comms.request.getParameter("passphrase");
	final String grantURL = comms.request.getParameter("grantURL");
	final String denyURL = comms.request.getParameter("denyURL");

	if (AccountManager.getUniqueInstance().isValidCandidateLogin(username, passphrase)) {
	    throw new ClientPageRedirectException(
			  comms.request.getAppFileURIPath(grantURL));
	} else {
	    throw new ClientPageRedirectException(
			  comms.request.getAppFileURIPath(denyURL));
	}

    }

} //class
