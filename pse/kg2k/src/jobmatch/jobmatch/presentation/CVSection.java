// $Id: CVSection.java,v 1.6 2000/06/04 18:17:32 locher Exp $

package jobmatch.presentation;

import jobmatch.business.entity.*;
import java.util.*;
import org.w3c.dom.*;
import org.w3c.dom.html.*;
import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

/**
 *  Provides a method to fill a ListBox with descriptions.
 *
 *  @author $Author: locher $
 *  @version $Revision: 1.6 $
 **/
public abstract class CVSection extends AuthentificationPage implements  HttpPresentation {

    /**
     * Fills the listbox of the provided OptionElement with the descriptions
     * in the collection. Selects the entry which equals the selection argument.
     * Removes the template from the listbox.
     **/
    public void fillListBox(HTMLOptionElement template, Collection descriptions, Description selection) {	 
	Node container = template.getParentNode();
	template.removeChild(template.getFirstChild()); // remove the TextNode from the template
	Iterator iterator = descriptions.iterator();
	try {
	    while (iterator.hasNext()) {
		final Description description = (Description) iterator.next();
		String representation = description.getDescription();
		if (representation == null) {
		    representation = (description.toString() == null)?"???":description.toString();
		}
		final HTMLOptionElement clonedOption = (HTMLOptionElement) template.cloneNode(true); 
		clonedOption.setValue(representation);
		clonedOption.appendChild(template.getOwnerDocument().createTextNode(representation));
		if (selection != null && selection.equals(description)) {
		    clonedOption.setSelected(true);
		}
		container.appendChild(clonedOption);
	    }	
	} catch (com.lutris.dods.builder.generator.query.DataObjectException e){
	    throw new RuntimeException(e.toString());
	}
	container.removeChild(template);
    }
       
} //class

// Document history
/*
 * $Log: CVSection.java,v $
 * Revision 1.6  2000/06/04 18:17:32  locher
 * evil equals s
 *
 * Revision 1.5  2000/06/04 11:50:43  locher
 * many little fixes
 *
 * Revision 1.4  2000/05/31 11:36:15  studer
 * javadoc added
 *
 * Revision 1.3  2000/05/31 06:09:29  studer
 * Funktionalitaet zum dynamischen Fuellen einer Tabelle
 *
 */
