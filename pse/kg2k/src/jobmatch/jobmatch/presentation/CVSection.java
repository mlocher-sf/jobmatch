// $Id: CVSection.java,v 1.4 2000/05/31 11:36:15 studer Exp $

package jobmatch.presentation;

import jobmatch.business.entity.*;
import java.util.*;
import org.w3c.dom.*;
import org.w3c.dom.html.*;
import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

/**
 *  Provides a method to fill a ListBox with data
 *
 *  @since erstellungsdatum
 *  @author $Author: studer $
 *  @version $Revision: 1.4 $
 **/
public abstract class CVSection extends AuthentificationPage implements  HttpPresentation {

    /**
     * Fills a listbox with data CV-data.
     * Selects the right option.
     **/
    public void fillListBox(HTMLOptionElement template, Collection descriptions, Description first_option){	 
	Node select_node = template.getParentNode();
	template.removeChild(template.getFirstChild());
	Iterator iterator = descriptions.iterator();
	try{
	    while (iterator.hasNext()){
		final Description description = (Description) iterator.next();
		HTMLOptionElement clonedOption = (HTMLOptionElement) template.cloneNode(true);
		clonedOption.setValue(description.getDescription());
		Node optionNode = template.getOwnerDocument().createTextNode(description.getDescription());
		if (first_option != null && description.equals(first_option)){
		    clonedOption.setSelected(true);
		}
		clonedOption.appendChild(optionNode);
		select_node.appendChild(clonedOption);
	    }	
	}catch (Exception e){
	    throw new RuntimeException(e.toString());
	}
	select_node.removeChild(template);
    }
 
       
} //class

// Document history
/*
 * $Log: CVSection.java,v $
 * Revision 1.4  2000/05/31 11:36:15  studer
 * javadoc added
 *
 * Revision 1.3  2000/05/31 06:09:29  studer
 * Funktionalitaet zum dynamischen Fuellen einer Tabelle
 *
 */
