package jobmatch.presentation;

import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

public class Login implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

        LoginHTML page = (LoginHTML)comms.xmlcFactory.create(LoginHTML.class);
        comms.response.writeHTML(page);
    }

}
