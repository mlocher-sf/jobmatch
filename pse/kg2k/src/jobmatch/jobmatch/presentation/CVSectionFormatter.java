// $Id: CVSectionFormatter.java,v 1.1 2000/05/31 06:09:30 studer Exp $

package jobmatch.presentation;

import jobmatch.business.*;
import org.w3c.dom.html.*;
import org.enhydra.xml.xmlc.html.*;


/**
 *  Formats a CVSection for a MultiSection-Page
 *
 *  @since May 30 2000
 *  @author $Author: studer $
 *  @version $Revision: 1.1 $
 **/
public interface CVSectionFormatter {
    public HTMLElement format(jobmatch.business.candidate.cv.CVSection section, HTMLObject page);
}//interface


/*
 * $Log: CVSectionFormatter.java,v $
 * Revision 1.1  2000/05/31 06:09:30  studer
 * Funktionalitaet zum dynamischen Fuellen einer Tabelle
 *
 *
 */
