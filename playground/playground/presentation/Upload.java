package playground.presentation;

import playground.business.*;

import java.io.*;
import gnu.regexp.*;
import org.w3c.dom.html.*;
import com.lutris.mime.*;
import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

public class Upload implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

	PictureHTML page = new PictureHTML();

 	String event = comms.request.getParameter("event");
 	System.out.println(comms.request.getHttpServletRequest().getMethod().toUpperCase());

	if (comms.request.getHttpServletRequest().getMethod().toUpperCase().equals("POST")) {
	    HttpPresentationInputStream in = comms.request.getInputStream();
	    this.save(in);
	}
    	
        comms.response.writeHTML(page.toDocument());
    }

    private void save(HttpPresentationInputStream in) {
	try {
	    String RE = "(?:Content-Disposition: form-data; name=\"image\"; filename=\")";

	    System.out.println(RE);
	    System.out.println(new RE(RE));


	    OutputStream out = new FileOutputStream("output.txt");
	    
	    byte[] buffer = new byte[512];
	    int offset = 0;
	    while( 0 != in.available()) {
		int numRead = in.readLine(buffer, 0, 512);
		System.out.print(new String(buffer, 0, numRead));
		out.write(buffer, 0, numRead);
	    }
	    out.flush();
	} catch (Exception e) {}
    }
}
