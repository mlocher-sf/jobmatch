// $Id: CreateLogin.java,v 1.2 2000/05/19 15:28:34 locher Exp $
package jobmatch.presentation;

import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

import jobmatch.business.provider.account.*;

/**
 *  @since May 16 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.2 $
 **/
public class CreateLogin implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {
	
	final String action = comms.request.getParameter("action");

	if (action != null && action.equals("create")) {
	    // create a new account and then go to the candidate home
	    final String username = comms.request.getParameter("username");
	    final String passphrase = comms.request.getParameter("passphrase");
	    final String confirm = comms.request.getParameter("passphraseConfirm");
	    final String email = comms.request.getParameter("eMail");
	    if (passphrase != null && passphrase.equals(confirm)) {
		try {
		    this.createAccount(username, passphrase, email);
		    throw new ClientPageRedirectException(
					   comms.request.getAppFileURIPath("CandidateHome.po"));
		} catch  (Exception e) {
		    CreateLoginHTML page = (CreateLoginHTML)comms.xmlcFactory.create(CreateLoginHTML.class);
		    page.setTextMessage(e.toString());
		    comms.response.writeHTML(page);
		}
	    } else {
		CreateLoginHTML page = (CreateLoginHTML)comms.xmlcFactory.create(CreateLoginHTML.class);
		page.setTextMessage("confirmation failed");
		comms.response.writeHTML(page);
	    }
	} else {
	    // Simply show the page
	    CreateLoginHTML page = (CreateLoginHTML)comms.xmlcFactory.create(CreateLoginHTML.class);
	    page.getElementMessageDiv().removeChild(page.getElementMessageContainer());
	    comms.response.writeHTML(page);
	}
    }

    private void createAccount(String username, String passphrase, String email) {
	try {
	    AccountManager.getUniqueInstance().createCandidateAccount(username, passphrase, email);
	} catch (Exception e) {
	    throw new RuntimeException("creation failed");
	}
    }

} // class

// Document history
/**
 * $Log: CreateLogin.java,v $
 * Revision 1.2  2000/05/19 15:28:34  locher
 * create login
 *
 * Revision 1.1  2000/05/16 09:29:48  locher
 * *** empty log message ***
 *
 **/
