// $Id: LanguageCapability.java,v 1.1 2000/06/10 11:06:24 studer Exp $

package jobmatch.business.entity;

import com.lutris.dods.builder.generator.query.DataObjectException;
import jobmatch.data.*;
import java.util.*;

/**
 *  LanguageCapability Business Object
 *
 *  @since June 10 2000
 *  @author $Author: studer $
 *  @version $Revision: 1.1 $
 **/
public class LanguageCapability extends LanguagecapabilityBDO implements Capability {
    
    private LanguageCapability(String description) throws Exception {
	super();
	this.setDescription(description);
	this.commit();
    }

    public LanguageCapability(LanguagecapabilityDO dataObject) {
	super(dataObject);
    }
    
    /**
     * Returns the specified Languagecapability from the DB
     * If it doesn`t exist, it`s been created.
     **/
    public static Capability getCapability(String description) {
	try {
	    LanguagecapabilityQuery query = new LanguagecapabilityQuery();
	    query.setQueryDescription(description);
	    LanguagecapabilityDO element = query.getNextDO();
	    if (element != null) {
		return new LanguageCapability(element);
	    } else {
		return new LanguageCapability(description);
	    }
	} catch (Exception e) { throw new RuntimeException(e.toString()); }	
    }

    /**
     * Returns a list of all Languagecapabilities in the DB
     **/
    public  List getAllCapabilities() {
	List result = new ArrayList();
	try {
	    LanguagecapabilityQuery query = new LanguagecapabilityQuery();
	    query.addOrderByDescription();
	    LanguagecapabilityDO element = query.getNextDO();
	    while ( element != null) {
		result.add(new LanguageCapability(element));
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
	    return (this.getHandle().equals(((LanguagecapabilityBDO) other).getHandle()));
	}
	catch (Exception e) {
	    return false;
	}
    }

    /** @see Object.toString **/
    public String toString() {
	try {
	    return this.getDescription() + this.getOrdinal();
	} catch (Exception e) { 
	    throw new RuntimeException(e.toString()); 
	}
    }
    

       
} //class


/*
 * $Log: LanguageCapability.java,v $
 * Revision 1.1  2000/06/10 11:06:24  studer
 * business object for cvsections
 *
 *
 *
 */
