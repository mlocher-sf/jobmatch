package jobmatch.presentation;

import jobmatch.business.provider.account.*;
import jobmatch.business.util.TimeUtil;

import com.lutris.appserver.server.session.*;
import com.lutris.appserver.server.httpPresentation.*;


/**
 * Invalidates the current login
 **/
public class Logout implements HttpPresentation {

    public final String DEFAULT_DESTINATION = "./Welcome.po";

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {
	
	this.invalidateAccount(comms.sessionData);
	throw new ClientPageRedirectException(
			   comms.request.getAppFileURIPath(DEFAULT_DESTINATION));
    }
    
    /**
     * invalidates the login
     **/
    private void invalidateAccount(SessionData sessionData) {
	try {
	    sessionData.set("account", null);
	} catch (Exception e) {
	    throw new RuntimeException(e.toString());
	}
    }

} //class
