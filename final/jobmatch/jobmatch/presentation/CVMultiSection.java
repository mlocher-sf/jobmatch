// $Id: CVMultiSection.java,v 1.1 2000/06/20 13:33:28 pse4 Exp $

package jobmatch.presentation;
import java.util.*;
import org.w3c.dom.html.*;
import org.enhydra.xml.xmlc.html.*;
/**
 *  Provides a method to fill a table with data.
 *
 *  @since May 30 2000
 *  @author $Author: pse4 $
 *  @version $Revision: 1.1 $
 **/
public abstract class CVMultiSection extends CVSection{
    
    /**
     * formats all CVSections contained in data with the provided formater
     * and puts the results into the container.
     **/
    public void fillTable(HTMLElement container, CVSectionFormatter formatter, Collection data) {
	Iterator iterator = data.iterator();
	try {
	    while (iterator.hasNext()) {
		final jobmatch.business.candidate.cv.CVSection section =
		    (jobmatch.business.candidate.cv.CVSection) iterator.next();
		final HTMLElement formated = formatter.format(section);
		if (formated != null) {
		    container.appendChild(formated);
		}
	    }	
	} catch (Exception e) {
	    throw new RuntimeException(e.toString());
	}
    }

} //class

// Document history
/*
 * $Log: CVMultiSection.java,v $
 * Revision 1.1  2000/06/20 13:33:28  pse4
 * Initial revision
 *
 * Revision 1.3  2000/06/04 11:50:43  locher
 * many little fixes
 *
 * Revision 1.2  2000/05/31 11:36:15  studer
 * javadoc added
 *
 * Revision 1.1  2000/05/31 06:09:28  studer
 * Funktionalitaet zum dynamischen Fuellen einer Tabelle
 *
 *
 */
