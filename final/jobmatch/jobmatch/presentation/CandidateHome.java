// $Id: CandidateHome.java,v 1.1 2000/06/20 13:33:28 pse4 Exp $
package jobmatch.presentation;

import jobmatch.business.candidate.*;
import jobmatch.business.provider.account.*;
import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

/**
 *  @since May 16 2000
 *  @author $Author: pse4 $
 *  @version $Revision: 1.1 $
 **/
public class CandidateHome extends AuthentificationPage implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

	this.assertLegitimation(comms, Account.TYPE_CANDIDATE);
	CandidateAccount account = this.getCandidateAccount(comms);	
	final String action = comms.request.getParameter("action");
	
	if (action != null && action.equals("logout")) {
	    try {
		Candidate candidate = account.getCandidateBO();
		final String status = comms.request.getParameter("status");
		candidate.setStatus("active".equals(status));
		candidate.commit();
		throw new ClientPageRedirectException(
			   comms.request.getAppFileURIPath("Logout.po"));
	    } catch (Exception e) {
		throw new RuntimeException(e.toString());
	    }
	}

        CandidateHomeHTML page = (CandidateHomeHTML)comms.xmlcFactory.create(CandidateHomeHTML.class);

	try {
	    Candidate candidate = account.getCandidateBO();
	    if (candidate.getStatus()) {
		page.getElementActive().setChecked(true);
		page.getElementInactive().setChecked(false);
	    } else {
		page.getElementActive().setChecked(false);
		page.getElementInactive().setChecked(true);
	    }
	} catch (Exception e) {
	    throw new RuntimeException(e.toString());
	}
	
        comms.response.writeHTML(page);
    }

} // class

// Document history
/**
 * $Log: CandidateHome.java,v $
 * Revision 1.1  2000/06/20 13:33:28  pse4
 * Initial revision
 *
 * Revision 1.5  2000/06/13 14:34:54  locher
 * safe state of active flag
 *
 * Revision 1.4  2000/06/06 16:10:14  locher
 * enhanced login
 *
 * Revision 1.3  2000/05/23 14:10:59  locher
 * authentification mechanisms
 *
 * Revision 1.2  2000/05/16 09:29:47  locher
 * *** empty log message ***
 *
 **/
