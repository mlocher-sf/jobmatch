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

 	String event = comms.request.getParameter("event");
// 	System.out.println(comms.request.getHttpServletRequest().getMethod().toUpperCase());

	if (comms.request.getHttpServletRequest().getMethod().toUpperCase().equals("POST")) {
	    HttpPresentationInputStream in = comms.request.getInputStream();
	    this.save(comms);
	}
    	
        comms.response.writeHTML(page.toDocument());
    }

    private void save(HttpPresentationComms comms) {
	try {

	    final int MAX_SIZE = 256 * 1024;
	    MultipartRequest multi = new MultipartRequest(comms.request.getHttpServletRequest(), "/tmp", MAX_SIZE); 

/*	    System.out.println("Params:"); 
	    Enumeration params = multi.getParameterNames(); 
	    while (params.hasMoreElements()) { 
		String name = (String)params.nextElement(); 
		String value = multi.getParameter(name);
		System.out.println(name + " = " + value);
	    }
	    System.out.println();
	    System.out.println("Files:");*/

	    Enumeration files = multi.getFileNames();
	    while (files.hasMoreElements()) {
		String name = (String)files.nextElement();
//		String filename = multi.getFilesystemName(name);
//		String type = multi.getContentType(name);
		File f = multi.getFile(name);

/*		System.out.println("name: " + name);
		System.out.println("filename: " + filename);
		System.out.println("type: " + type);
		
		if (f != null) {
		    System.out.println("f.toString(): " + f.toString());
		    System.out.println("f.getName(): " + f.getName());
		    System.out.println("f.exists(): " + f.exists());
		    System.out.println("f.length(): " + f.length());
		    System.out.println(); 
		} */
	    }
	} catch (Exception e) {System.err.println(e);}
    }
} // class
