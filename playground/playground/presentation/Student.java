package playground.presentation;

import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

public class Student implements HttpPresentation {


    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

	String now = new java.util.Date().toString();
	StudentHTML page = new StudentHTML();
	
	page.getElementName().setValue(now);
        comms.response.writeHTML(page.toDocument());
    }

}
