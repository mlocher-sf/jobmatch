package jobmatch.presentation;

import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

public class CVProgrammingExperience implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

        CVProgrammingExperienceHTML page = (CVProgrammingExperienceHTML)comms.xmlcFactory.create(CVProgrammingExperienceHTML.class);
        comms.response.writeHTML(page);
    }

}
