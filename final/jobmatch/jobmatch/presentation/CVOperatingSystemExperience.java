package jobmatch.presentation;

import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

public class CVOperatingSystemExperience implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

        CVOperatingSystemExperienceHTML page = (CVOperatingSystemExperienceHTML)comms.xmlcFactory.create(CVOperatingSystemExperienceHTML.class);
        comms.response.writeHTML(page);
    }

}
