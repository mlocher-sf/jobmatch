package jobmatch.presentation;

import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

public class CVComputerExperience implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

        CVComputerExperienceHTML page =
        (CVComputerExperienceHTML)comms.xmlcFactory.create(CVComputerExperienceHTML.class);
	//deletes the template table entries
	page.getElementTemplateProgramming().getParentNode().removeChild(page.getElementTemplateProgramming());
	page.getElementTemplateOperating().getParentNode().removeChild(page.getElementTemplateOperating());
	page.getElementTemplateSoftware().getParentNode().removeChild(page.getElementTemplateSoftware());
        comms.response.writeHTML(page);
    }

}
