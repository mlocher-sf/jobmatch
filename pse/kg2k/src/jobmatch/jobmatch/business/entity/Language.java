// $Id: Language.java,v 1.1 2000/06/09 12:00:18 studer Exp $

package jobmatch.business.entity;

import com.lutris.dods.builder.generator.query.DataObjectException;
import jobmatch.data.*;
import java.util.*;

/**
 *  Language Business Object
 *
 *  @since June 9 2000
 *  @author $Author: studer $
 *  @version $Revision: 1.1 $
 **/
public class Language extends LanguageBDO implements Description {
    
    private Language(String description) throws Exception {
	super();
	this.setDescription(description);
	this.commit();
    }

    public Language(LanguageDO dataObject) {
	super(dataObject);
    }
    
    /**
     * Returns the specified Language from the DB
     **/
    public static Language getLanguage(String description) {
	try {
	    LanguageQuery query = new LanguageQuery();
	    query.setQueryDescription(description);
	    LanguageDO element = query.getNextDO();
	    if (element != null) {
		return new Language(element);
	    } else {
		return new Language(description);
	    }
	} catch (Exception e) { throw new RuntimeException(e.toString()); }	
    }

    /**
     * Return a list of all Language in the DB
     **/
    public static List getAllLanguage() {
	List result = new ArrayList();
	try {
	    LanguageQuery query = new LanguageQuery();
	    query.addOrderByDescription();
	    LanguageDO element = query.getNextDO();
	    while ( element != null) {
		result.add(new Language(element));
		element = query.getNextDO();
	    }
	} catch (Exception e) { throw new RuntimeException(e.toString()); }
 	return result;
    }
    
    /** @see Object.equals **/
    public boolean equals(Object other) {
	if (other == null) {
	    return false;
	}
	
	try {
	    return (this.getHandle().equals(((LanguageBDO) other).getHandle()));
	}
	catch (Exception e) {
	    return false;
	}
    }

    /** @see Object.toString **/
    public String toString() {
	try {
	    return this.getDescription();
	} catch (Exception e) { 
	    throw new RuntimeException(e.toString()); 
	}
    }
    

       
} //class


/*
 * $Log: Language.java,v $
 * Revision 1.1  2000/06/09 12:00:18  studer
 * More Entities
 *
 *
 *
 */
