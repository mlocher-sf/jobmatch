package jobmatch.presentation;

import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

public class CandidateHome implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

        CandidateHomeHTML page = (CandidateHomeHTML)comms.xmlcFactory.create(CandidateHomeHTML.class);
        comms.response.writeHTML(page);
    }

}
