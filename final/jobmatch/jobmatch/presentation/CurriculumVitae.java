// $Id: CurriculumVitae.java,v 1.1 2000/06/20 13:33:29 pse4 Exp $
package jobmatch.presentation;

import jobmatch.business.provider.account.*;
import com.lutris.appserver.server.httpPresentation.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.awt.Color;
import java.io.*;

/**
 * Curriculum Vitae
 *
 * @since May 31 2000
 * @author $Author: pse4 $
 * @version $Revision: 1.1 $
 **/
public class CurriculumVitae extends PDFPresentation {
    
    protected void formatDocument(HttpPresentationComms comms, Document document) 
	throws DocumentException, HttpPresentationException
    {

	this.assertLegitimation(comms, Account.TYPE_CANDIDATE);

	final Font titleFont = new Font(Font.HELVETICA, 24, Font.BOLD);
	final Font heading1Font = new Font(Font.HELVETICA, 16, Font.BOLD);
	final Font heading2Font = new Font(Font.HELVETICA, 14, Font.BOLD);
	final Font stdandardFont = new Font(Font.HELVETICA, 12, Font.NORMAL);

	document.add(new Paragraph("Curriculum Vitae", titleFont));

	
	document.add(new Paragraph("Persoenliche Daten", heading1Font));

	document.add(new Paragraph("Ausbildungen", heading1Font));

	Table table = new Table(5);
	table.setBorderWidth(1);
	table.setBorderColor(Color.black);
	table.setCellpadding(2);
	table.setCellspacing(2);
	table.addCell("Schule");
	table.addCell("Schultyp");
	table.addCell("Ort");
	table.addCell("von");
	table.addCell("bis");
	document.add(table);

	document.add(new Paragraph("Sprachen", heading1Font));

    }
     
} // class

// Document history
/**
 * $Log: CurriculumVitae.java,v $
 * Revision 1.1  2000/06/20 13:33:29  pse4
 * Initial revision
 *
 * Revision 1.3  2000/06/13 15:29:00  locher
 * added CV Preview to CandidateHome
 *
 * Revision 1.2  2000/06/06 11:09:59  locher
 * *** empty log message ***
 *
 * Revision 1.1  2000/06/03 08:35:27  locher
 * sessionData fix and PDF page
 *
 **/
