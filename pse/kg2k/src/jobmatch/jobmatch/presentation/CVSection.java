// $Id: CVSection.java,v 1.1 2000/05/29 12:07:15 studer Exp $

package jobmatch.presentation;

import jobmatch.business.entity.*;
import java.util.*;
import org.w3c.dom.*;
import org.w3c.dom.html.*;
import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

/**
 *  Class description comes here
 *
 *  @since erstellungsdatum
 *  @author $Author: studer $
 *  @version $Revision: 1.1 $
 **/
public abstract class CVSection extends AuthentificationPage implements  HttpPresentation {

    public void fillListBox(HTMLOptionElement template, Collection descriptions){	 
	Node select_node = template.getParentNode();
	Iterator iterator = descriptions.iterator();
	try{
	    while (iterator.hasNext()){
		final Description description = (Description) iterator.next();
		HTMLOptionElement clonedOption = (HTMLOptionElement) template.cloneNode(true);
		template.setValue(description.getDescription());
		Node optionNode = clonedOption.getOwnerDocument().createTextNode(description.getDescription());
		template.appendChild(optionNode);
		select_node.appendChild(clonedOption);
	    }	
	}catch (Exception e){
	   System.err.println(e);
	}
	select_node.removeChild(template);
    }
 
       
} //class

// Document history
/*
 * $Log: CVSection.java,v $
 * Revision 1.1  2000/05/29 12:07:15  studer
 * Initial import
 *
 * Revision 1.2  2000/05/12 08:46:10  locher
 * log history moved to the end of the file
 *
 */
