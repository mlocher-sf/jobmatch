// $Id: Industry.java,v 1.1 2000/06/20 13:32:57 pse4 Exp $

package jobmatch.business.entity;

import com.lutris.dods.builder.generator.query.DataObjectException;
import jobmatch.data.*;
import java.util.*;

/**
 *  Industry Business Object
 *
 *  @since June 10 2000
 *  @author $Author: pse4 $
 *  @version $Revision: 1.1 $
 **/
public class Industry extends IndustryBDO implements Description {
    
    private Industry(String description) throws Exception {
	super();
	this.setDescription(description);
	this.commit();
    }

    public Industry(IndustryDO dataObject) {
	super(dataObject);
    }
    
    /**
     * Returns the specified industry from the DB
     **/
    public static Industry getIndustry(String industry) {
	try {
	    IndustryQuery query = new IndustryQuery();
	    query.setQueryDescription(industry);
	    IndustryDO element = query.getNextDO();
	    if (element != null) {
		return new Industry(element);
	    } else {
		return new Industry(industry);
	    }
	} catch (Exception e) { throw new RuntimeException(e.toString()); }	
    }

    /**
     * Return a list of all industries in the DB
     **/
    public static List getAllIndustries() {
	List result = new ArrayList();
	try {
	    IndustryQuery query = new IndustryQuery();
	    query.addOrderByDescription();
	    IndustryDO element = query.getNextDO();
	    while ( element != null) {
		result.add(new Industry(element));
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
	    return (this.getHandle().equals(((IndustryBDO) other).getHandle()));
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
 * $Log: Industry.java,v $
 * Revision 1.1  2000/06/20 13:32:57  pse4
 * Initial revision
 *
 * Revision 1.1  2000/06/10 11:06:23  studer
 * business object for cvsections
 *
 *
 */
