package jobmatch.presentation;

import jobmatch.business.provider.account.*;

import com.lutris.appserver.server.httpPresentation.*;

public class CheckLogin implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

	String grantURL = comms.request.getParameter("grantURL");

	System.out.println(grantURL);
	
	throw new ClientPageRedirectException(
			  comms.request.getAppFileURIPath(grantURL));
    }

} //class
