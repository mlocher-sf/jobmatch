package playground.presentation;

import java.util.Date;
import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

public class Welcome implements HttpPresentation {


    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

        String now = new Date().toString();
        WelcomeHTML welcome = new WelcomeHTML();
        welcome.setTextTime(now);
        comms.response.writeHTML(welcome.toDocument());
    }

}
