package jobmatch.presentation;

import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

public class CVLanguageDetailExperience implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

        CVLanguageDetailExperienceHTML page = (CVLanguageDetailExperienceHTML)comms.xmlcFactory.create(CVLanguageDetailExperienceHTML.class);
        comms.response.writeHTML(page);
    }

}
