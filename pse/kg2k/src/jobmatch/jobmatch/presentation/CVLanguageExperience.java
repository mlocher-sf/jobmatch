package jobmatch.presentation;

import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

public class CVLanguageExperience implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

        CVLanguageExperienceHTML page = (CVLanguageExperienceHTML)comms.xmlcFactory.create(CVLanguageExperienceHTML.class);
        comms.response.writeHTML(page);
    }

}
