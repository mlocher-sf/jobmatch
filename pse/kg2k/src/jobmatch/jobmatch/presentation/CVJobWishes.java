package jobmatch.presentation;

import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

public class CVJobWishes implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

        CVJobWishesHTML page = (CVJobWishesHTML)comms.xmlcFactory.create(CVJobWishesHTML.class);
        comms.response.writeHTML(page);
    }

}
