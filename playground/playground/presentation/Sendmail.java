package playground.presentation;

import playground.business.*;

import org.w3c.dom.html.*;
import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

public class Sendmail implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

	final String from = comms.request.getParameter("From");
  	final String to = comms.request.getParameter("To");
  	final String subject = comms.request.getParameter("Subject");
  	final String msg = comms.request.getParameter("Message");

	final String host = "mail.unibe.ch";

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
