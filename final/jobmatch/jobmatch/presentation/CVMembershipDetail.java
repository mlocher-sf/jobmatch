package jobmatch.presentation;

import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

public class CVMembershipDetail implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

        CVMembershipDetailHTML page = (CVMembershipDetailHTML)comms.xmlcFactory.create(CVMembershipDetailHTML.class);
        comms.response.writeHTML(page);
    }

}
