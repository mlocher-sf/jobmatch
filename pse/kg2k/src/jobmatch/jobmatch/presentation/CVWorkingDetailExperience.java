package jobmatch.presentation;

import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

public class CVWorkingDetailExperience implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

        CVWorkingDetailExperienceHTML page = (CVWorkingDetailExperienceHTML)comms.xmlcFactory.create(CVWorkingDetailExperienceHTML.class);
        comms.response.writeHTML(page);
    }

}
