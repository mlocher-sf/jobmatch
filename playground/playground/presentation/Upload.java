package playground.presentation;

import playground.business.*;

import org.w3c.dom.html.*;
import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

public class Upload implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

	PictureHTML page = new PictureHTML();

	System.out.println(comms.request.toString());
    	
        comms.response.writeHTML(page.toDocument());
    }
}
