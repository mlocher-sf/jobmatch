// $Id: CandidateHome.java,v 1.3 2000/05/23 14:10:59 locher Exp $
package jobmatch.presentation;

import jobmatch.business.candidate.*;
import jobmatch.business.provider.account.*;
import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

/**
 *  @since May 16 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.3 $
 **/
public class CandidateHome extends AuthentificationPage implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

	this.assertLegitimation(comms, Account.TYPE_CANDIDATE, "Welcome.po");
	CandidateAccount account = this.getCandidateAccount(comms);	

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
 * Revision 1.3  2000/05/23 14:10:59  locher
 * authentification mechanisms
 *
 * Revision 1.2  2000/05/16 09:29:47  locher
 * *** empty log message ***
 *
 **/
