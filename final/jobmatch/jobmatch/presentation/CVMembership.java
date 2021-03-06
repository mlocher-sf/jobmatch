package jobmatch.presentation;

import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

public class CVMembership implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

        CVMembershipHTML page =
        (CVMembershipHTML)comms.xmlcFactory.create(CVMembershipHTML.class);
	page.getElementTemplate().getParentNode().removeChild(page.getElementTemplate());
        comms.response.writeHTML(page);
    }

}
