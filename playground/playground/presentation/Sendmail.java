package playground.presentation;

import playground.business.*;

import org.w3c.dom.html.*;
import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

public class Sendmail implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

  	String from = comms.request.getParameter("From");
  	String to = comms.request.getParameter("To");
  	String subject = comms.request.getParameter("Subject");
  	String msg = comms.request.getParameter("Message");

	String host = "mail.unibe.ch";

	System.out.println(comms.request.getParameter("From"));
  	System.out.println(comms.request.getParameter("To"));
  	System.out.println(comms.request.getParameter("Subject"));
  	System.out.println(comms.request.getParameter("Message"));
	
	try {
	    Mailer.sendMail(host, from, to, subject, msg);
	} catch (Exception e) {
	    System.err.println("Mailing Error:\n"+e.toString());
	}

	// redirect to static page
	throw new ClientPageRedirectException(
	    comms.request.getAppFileURIPath("MailSent.html"));

    }

}
