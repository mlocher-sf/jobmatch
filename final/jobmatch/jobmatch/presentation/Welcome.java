package jobmatch.presentation;

import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

public class Welcome implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

        WelcomeHTML page = (WelcomeHTML)comms.xmlcFactory.create(WelcomeHTML.class);
        comms.response.writeHTML(page);
    }

}
