// $Id: DocumentPresentation.java,v 1.1 2000/06/20 13:33:29 pse4 Exp $
package jobmatch.presentation;

import com.lutris.appserver.server.httpPresentation.*;
import com.lowagie.text.*;
import java.io.*;

/**
 * Superclass of all generic Documents
 *
 *  @since May 31 2000
 *  @author $Author: pse4 $
 *  @version $Revision: 1.1 $
 **/
abstract class DocumentPresentation extends AuthentificationPage implements HttpPresentation {
    
    final private static int INITIAL_BUFFERSIZE = 2048;
    final private static String AUTHOR = "JobMatch by PSE 2000/4 http://www.iam.unibe.ch";
    
    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {
	
	comms.response.setContentType(this.getMimeType());	
	ByteArrayOutputStream result = new ByteArrayOutputStream(this.getInitalBufferSize());
	Document document = this.createDocument();
	try {
	    this.setUpDocumentListeners(result, document);
	    this.setMetaInformation(comms, document);
	    document.open();
	    this.formatDocument(comms, document);
	    document.close();
	} catch (DocumentException e) {
	    throw new RuntimeException(e.toString());
	}
	try {
	    result.writeTo(comms.response.getOutputStream());
	} catch (IOException e) {
	    throw new RuntimeException(e.toString());
	}
	comms.response.flush();
    }

    // Obligatory Subclass Hooks ----------------------------------------------

    abstract protected String getMimeType();

    abstract protected void setUpDocumentListeners(OutputStream os, Document document)
	throws DocumentException, HttpPresentationException;
    
    abstract protected void formatDocument(HttpPresentationComms comms, Document document)
	throws DocumentException, HttpPresentationException;


    // Faccultative Subclass Hooks --------------------------------------------

    protected int getInitalBufferSize() {
	return INITIAL_BUFFERSIZE;
    }
    
    protected String getSubject() {
	return "DocumentPresentation: no subject";
    }
    
    protected Document createDocument() {
	return new Document(PageSize.A4, 50, 50, 50, 50);
    }

    protected void setMetaInformation(HttpPresentationComms comms, Document document)
	throws DocumentException, HttpPresentationException
    {
	document.addSubject(this.getSubject());
	document.addAuthor(AUTHOR);	
    }
     
} // class

// Document history
/**
 * $Log: DocumentPresentation.java,v $
 * Revision 1.1  2000/06/20 13:33:29  pse4
 * Initial revision
 *
 * Revision 1.2  2000/06/13 15:29:01  locher
 * added CV Preview to CandidateHome
 *
 * Revision 1.1  2000/06/02 10:22:00  locher
 * generic presentation
 *
 **/
