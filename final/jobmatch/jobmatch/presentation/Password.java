package jobmatch.presentation;

import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

public class Password implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

        PasswordHTML page = (PasswordHTML)comms.xmlcFactory.create(PasswordHTML.class);
        comms.response.writeHTML(page);
    }

}
