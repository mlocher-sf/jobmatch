package jobmatch.presentation;

import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

public class CVPersonal implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

        CVPersonalHTML page = (CVPersonalHTML)comms.xmlcFactory.create(CVPersonalHTML.class);
        comms.response.writeHTML(page);
    }

}
