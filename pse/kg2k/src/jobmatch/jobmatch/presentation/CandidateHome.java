// $Id: CandidateHome.java,v 1.2 2000/05/16 09:29:47 locher Exp $
package jobmatch.presentation;

import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

/**
 *  @since May 16 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.2 $
 **/
public class CandidateHome implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

        CandidateHomeHTML page = (CandidateHomeHTML)comms.xmlcFactory.create(CandidateHomeHTML.class);
        comms.response.writeHTML(page);
    }

} // class

// Document history
/**
 * $Log: CandidateHome.java,v $
 * Revision 1.2  2000/05/16 09:29:47  locher
 * *** empty log message ***
 *
 **/
