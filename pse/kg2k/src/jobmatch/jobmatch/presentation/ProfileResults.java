// $Id: ProfileResults.java,v 1.1 2000/05/16 09:36:59 locher Exp $
package jobmatch.presentation;

import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

/**
 *  @since May 16 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
public class ProfileResults implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

        ProfileResultsHTML page = (ProfileResultsHTML)comms.xmlcFactory.create(ProfileResultsHTML.class);
        comms.response.writeHTML(page);
    }

} // class

// Document history
/**
 * $Log: ProfileResults.java,v $
 * Revision 1.1  2000/05/16 09:36:59  locher
 * *** empty log message ***
 *
 * Revision 1.1  2000/05/16 09:29:50  locher
 * *** empty log message ***
 *
 **/
