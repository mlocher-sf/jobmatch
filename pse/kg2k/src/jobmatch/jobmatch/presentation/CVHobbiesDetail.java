package jobmatch.presentation;

import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

public class CVHobbiesDetail implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

        CVHobbiesDetailHTML page = (CVHobbiesDetailHTML)comms.xmlcFactory.create(CVHobbiesDetailHTML.class);
        comms.response.writeHTML(page);
    }

}
