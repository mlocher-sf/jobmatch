package jobmatch.presentation.admin;

import com.lutris.xml.xmlc.*;
import jobmatch.presentation.*;
import com.lutris.appserver.server.httpPresentation.*;

public class ControlCenter extends AuthentificationPage {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {
	
        ControlCenterHTML page = (ControlCenterHTML)comms.xmlcFactory.create(ControlCenterHTML.class);
        comms.response.writeHTML(page);
    }

}
