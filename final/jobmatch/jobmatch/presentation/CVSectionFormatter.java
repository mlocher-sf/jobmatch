// $Id: CVSectionFormatter.java,v 1.1 2000/06/20 13:33:28 pse4 Exp $

package jobmatch.presentation;

import jobmatch.business.*;
import org.w3c.dom.html.*;
import org.enhydra.xml.xmlc.html.*;


/**
 *  Formats a CVSection
 *
 *  @since May 30 2000
 *  @author $Author: pse4 $
 *  @version $Revision: 1.1 $
 **/
public interface CVSectionFormatter {
    public HTMLElement format(jobmatch.business.candidate.cv.CVSection section);
}//interface


/*
 * $Log: CVSectionFormatter.java,v $
 * Revision 1.1  2000/06/20 13:33:28  pse4
 * Initial revision
 *
 * Revision 1.2  2000/06/04 11:50:43  locher
 * many little fixes
 *
 * Revision 1.1  2000/05/31 06:09:30  studer
 * Funktionalitaet zum dynamischen Fuellen einer Tabelle
 *
 */
