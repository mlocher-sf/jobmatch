package playground.presentation;

import playground.business.*;

import org.w3c.dom.html.*;
import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

public class Picture implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

	String now = new java.util.Date().toString();
	PictureHTML page = new PictureHTML();
	
        comms.response.writeHTML(page.toDocument());
    }
}
