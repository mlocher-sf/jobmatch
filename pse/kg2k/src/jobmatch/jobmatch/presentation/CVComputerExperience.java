package jobmatch.presentation;

import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

public class CVComputerExperience implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

        CVComputerExperienceHTML page = (CVComputerExperienceHTML)comms.xmlcFactory.create(CVComputerExperienceHTML.class);
        comms.response.writeHTML(page);
    }

}
