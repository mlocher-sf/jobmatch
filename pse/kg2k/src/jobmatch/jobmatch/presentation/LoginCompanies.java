// $Id: LoginCompanies.java,v 1.1 2000/05/16 09:29:49 locher Exp $
package jobmatch.presentation;

import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

/**
 *  @since May 16 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
public class LoginCompanies implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

        LoginCompaniesHTML page = (LoginCompaniesHTML)comms.xmlcFactory.create(LoginCompaniesHTML.class);
        comms.response.writeHTML(page);
    }

} // class

// Document history
/**
 * $Log: LoginCompanies.java,v $
 * Revision 1.1  2000/05/16 09:29:49  locher
 * *** empty log message ***
 *
 **/
