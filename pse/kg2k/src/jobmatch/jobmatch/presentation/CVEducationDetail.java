package jobmatch.presentation;

import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

public class CVEducationDetail extends CVSection implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

        CVEducationDetailHTML page = (CVEducationDetailHTML)comms.xmlcFactory.create(CVEducationDetailHTML.class);
        comms.response.writeHTML(page);
    }

}
