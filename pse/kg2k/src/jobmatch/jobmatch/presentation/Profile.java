// $Id: Profile.java,v 1.1 2000/05/16 09:29:50 locher Exp $
package jobmatch.presentation;

import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

/**
 *  @since May 16 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
public class Profile implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

        ProfileHTML page = (ProfileHTML)comms.xmlcFactory.create(ProfileHTML.class);
        comms.response.writeHTML(page);
    }

} // class

// Document history
/**
 * $Log: Profile.java,v $
 * Revision 1.1  2000/05/16 09:29:50  locher
 * *** empty log message ***
 *
 **/
