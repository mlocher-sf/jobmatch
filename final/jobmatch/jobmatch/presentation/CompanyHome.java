// $Id: CompanyHome.java,v 1.1 2000/06/20 13:33:29 pse4 Exp $
package jobmatch.presentation;

import com.lutris.xml.xmlc.*;
import jobmatch.business.company.*;
import jobmatch.business.provider.account.*;
import com.lutris.appserver.server.httpPresentation.*;

/**
 *  @since May 16 2000
 *  @author $Author: pse4 $
 *  @version $Revision: 1.1 $
 **/
public class CompanyHome extends AuthentificationPage implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

	this.assertLegitimation(comms, Account.TYPE_COMPANY);
	CompanyAccount account = this.getCompanyAccount(comms);	

        CompanyHomeHTML page = (CompanyHomeHTML)comms.xmlcFactory.create(CompanyHomeHTML.class);
        comms.response.writeHTML(page);
    }

} // class

// Document history
/**
 * $Log: CompanyHome.java,v $
 * Revision 1.1  2000/06/20 13:33:29  pse4
 * Initial revision
 *
 * Revision 1.3  2000/06/06 16:10:14  locher
 * enhanced login
 *
 * Revision 1.2  2000/06/02 14:55:06  locher
 * extended login behaviour
 *
 * Revision 1.1  2000/05/16 09:29:48  locher
 * *** empty log message ***
 *
 **/
