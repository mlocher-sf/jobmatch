// $Id: CompanyHome.java,v 1.1 2000/05/16 09:29:48 locher Exp $
package jobmatch.presentation;

import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

/**
 *  @since May 16 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
public class CompanyHome implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

        CompanyHomeHTML page = (CompanyHomeHTML)comms.xmlcFactory.create(CompanyHomeHTML.class);
        comms.response.writeHTML(page);
    }

} // class

// Document history
/**
 * $Log: CompanyHome.java,v $
 * Revision 1.1  2000/05/16 09:29:48  locher
 * *** empty log message ***
 *
 **/
