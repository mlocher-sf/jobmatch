// $Id: CurriculumVitae.java,v 1.1 2000/06/03 08:35:27 locher Exp $
package jobmatch.presentation;

import com.lutris.appserver.server.httpPresentation.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.awt.Color;
import java.io.*;

/**
 * Curriculum Vitae
 *
 * @since May 31 2000
 * @author $Author: locher $
 * @version $Revision: 1.1 $
 **/
public class CurriculumVitae extends PDFPresentation {
    
    protected void formatDocument(HttpPresentationComms comms, Document document) 
	throws DocumentException, HttpPresentationException
    {
	Table table = new Table(3);
	table.setBorderWidth(1);
	table.setBorderColor(new Color(0, 0, 255));
	table.setCellpadding(5);
	table.setCellspacing(5);
	Cell cell = new Cell("header");
	cell.setHeader(true);
	cell.setColspan(3);
	table.addCell(cell);
	cell = new Cell("example cell with colspan 1 and rowspan 2");
	cell.setRowspan(2);
	cell.setBorderColor(new Color(255, 0, 0));
	table.addCell(cell);
	table.addCell("1.1");
	table.addCell("2.1");
	table.addCell("1.2");
	table.addCell("2.2");
	table.addCell("cell test1");
	cell = new Cell("big cell");
	cell.setBackgroundColor(new Color(0, 255, 255));
	cell.setRowspan(2);
	cell.setColspan(2);
	table.addCell(cell);
	table.addCell("cell test2");
	document.add(table);
    }
     
} // class

// Document history
/**
 * $Log: CurriculumVitae.java,v $
 * Revision 1.1  2000/06/03 08:35:27  locher
 * sessionData fix and PDF page
 *
 **/
