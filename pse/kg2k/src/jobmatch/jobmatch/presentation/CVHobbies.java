package jobmatch.presentation;

import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

public class CVHobbies implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

        CVHobbiesHTML page =
        (CVHobbiesHTML)comms.xmlcFactory.create(CVHobbiesHTML.class);
	page.getElementTemplate().getParentNode().removeChild(page.getElementTemplate());
        comms.response.writeHTML(page);
    }

}
