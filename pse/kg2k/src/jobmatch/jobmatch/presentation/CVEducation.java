package jobmatch.presentation;

import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

public class CVEducation implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

        CVEducationHTML page = (CVEducationHTML)comms.xmlcFactory.create(CVEducationHTML.class);
        comms.response.writeHTML(page);
    }

}
