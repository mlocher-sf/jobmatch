// $Id: CreateLogin.java,v 1.1 2000/05/16 09:29:48 locher Exp $
package jobmatch.presentation;

import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

/**
 *  @since May 16 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
public class CreateLogin implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

        CreateLoginHTML page = (CreateLoginHTML)comms.xmlcFactory.create(CreateLoginHTML.class);
        comms.response.writeHTML(page);
    }

} // class

// Document history
/**
 * $Log: CreateLogin.java,v $
 * Revision 1.1  2000/05/16 09:29:48  locher
 * *** empty log message ***
 *
 **/
