package jobmatch.presentation;

import jobmatch.business.provider.account.Account;
import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

public class Login implements HttpPresentation {

    public static String getURL(int type, String grantURL) {
	final String applicationPath = "/"; // XXX fix this
	String url = applicationPath + "Login.po?grantURL=" + grantURL;
	if (type == Account.TYPE_CANDIDATE) {
	    url = url + "&type=candidate";
	}
	if (type == Account.TYPE_COMPANY) {
	    url = url + "&type=company";
	}
	if (type == Account.TYPE_PROVIDER) {
	    url = url + "&type=provider";
	}
	return url;
    }

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

        LoginHTML page =
	    (LoginHTML)comms.xmlcFactory.create(LoginHTML.class);
	
	final String accType = comms.request.getParameter("type");
	String grantURL = comms.request.getParameter("grantURL");
	
	if (accType != null) {
	    if (accType.equals("candidate")) {
		page.getElementCandidate().setSelected(true);
	    } 
	    if (accType.equals("company")) {
		page.getElementCompany().setSelected(true);
	    } 
	    if (accType.equals("provider")) {
		page.getElementProvider().setSelected(true);
	    } 
	}
	if (grantURL != null) {
	    page.getElementGrant().setValue(grantURL);
	}

        comms.response.writeHTML(page);
    }

}
