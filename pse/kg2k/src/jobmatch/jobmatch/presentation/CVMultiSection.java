// $Id: CVMultiSection.java,v 1.1 2000/05/31 06:09:28 studer Exp $

package jobmatch.presentation;
import java.util.*;
import org.w3c.dom.html.*;
import org.enhydra.xml.xmlc.html.*;
/**
 *  Provides a method to fill a table with data from the db
 *
 *  @since 30.5.00
 *  @author $Author: studer $
 *  @version $Revision: 1.1 $
 **/
public abstract class CVMultiSection extends CVSection{
    
    public void fillTable(HTMLObject page, CVSectionFormatter formatter, Collection data, HTMLElement container){
	Iterator iterator = data.iterator();
	try{
	    while (iterator.hasNext()){
		final jobmatch.business.candidate.cv.CVSection section =
		    (jobmatch.business.candidate.cv.CVSection) iterator.next();
		HTMLElement clonedRow = formatter.format(section, page);
      		container.appendChild(clonedRow);
	    }	
	}catch (Exception e){
	    throw new RuntimeException(e.toString());
	}
    }
} //class

// Document history
/*
 * $Log: CVMultiSection.java,v $
 * Revision 1.1  2000/05/31 06:09:28  studer
 * Funktionalitaet zum dynamischen Fuellen einer Tabelle
 *
 *
 */
