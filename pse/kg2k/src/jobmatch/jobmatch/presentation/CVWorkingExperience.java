package jobmatch.presentation;

import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

public class CVWorkingExperience implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

        CVWorkingExperienceHTML page = (CVWorkingExperienceHTML)comms.xmlcFactory.create(CVWorkingExperienceHTML.class);
        comms.response.writeHTML(page);
    }

}
