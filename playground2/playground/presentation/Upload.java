package playground.presentation;

import playground.business.*;

import java.io.*;
import java.util.*;
import com.oreilly.servlet.*;
import org.w3c.dom.html.*;
import com.lutris.mime.*;
import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

public class Upload implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

	PictureHTML page = (PictureHTML)comms.xmlcFactory.create(PictureHTML.class);

	if (comms.request.getHttpServletRequest().getMethod().toUpperCase().equals("POST")) {
	    this.save(comms);
	}
    	
        comms.response.writeHTML(page.toDocument());
    }

    private void save(HttpPresentationComms comms) {
	try {

	    final int MAX_SIZE = 256 * 1024;
	    MultipartRequest multi = new MultipartRequest(comms.request.getHttpServletRequest(), "/tmp", MAX_SIZE); 

	    Enumeration files = multi.getFileNames();
	    while (files.hasMoreElements()) {
		String name = (String)files.nextElement();
		File f = multi.getFile(name);
	    }
	} catch (Exception e) {System.err.println(e);}
    }
} // class
