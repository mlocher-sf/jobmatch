package jobmatch.presentation;

import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

public class CVSoftwareExperience implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

        CVSoftwareExperienceHTML page = (CVSoftwareExperienceHTML)comms.xmlcFactory.create(CVSoftwareExperienceHTML.class);
        comms.response.writeHTML(page);
    }

}
