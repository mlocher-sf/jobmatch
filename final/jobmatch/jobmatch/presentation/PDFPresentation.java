// $Id: PDFPresentation.java,v 1.1 2000/06/20 13:33:30 pse4 Exp $
package jobmatch.presentation;

import com.lutris.appserver.server.httpPresentation.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.io.*;

/**
 * Superclass for PDF Presentations
 *
 * @since May 31 2000
 * @author $Author: pse4 $
 * @version $Revision: 1.1 $
 **/
abstract public class PDFPresentation extends DocumentPresentation {
    
    final private static String MIME_TYPE = "application/pdf";

    protected String getMimeType() {
	return MIME_TYPE;
    }
    
    protected String getSubject() {
	return "PDF Presentation: no subject";
    }
    
    protected void setUpDocumentListeners(OutputStream os, Document document) 
	throws DocumentException, HttpPresentationException
    {
	PdfWriter.getInstance(document, os);
    }
    
    abstract protected void formatDocument(HttpPresentationComms comms, Document document) 
	throws DocumentException, HttpPresentationException;
     
} // class

// Document history
/**
 * $Log: PDFPresentation.java,v $
 * Revision 1.1  2000/06/20 13:33:30  pse4
 * Initial revision
 *
 * Revision 1.1  2000/06/02 10:22:02  locher
 * generic presentation
 *
 **/
