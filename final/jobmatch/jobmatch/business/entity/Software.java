// $Id: Software.java,v 1.1 2000/06/20 13:32:57 pse4 Exp $

package jobmatch.business.entity;

import com.lutris.dods.builder.generator.query.DataObjectException;
import jobmatch.data.*;
import java.util.*;

/**
 *  Software Business Object
 *
 *  @since June 9 2000
 *  @author $Author: pse4 $
 *  @version $Revision: 1.1 $
 **/
public class Software extends SoftwareBDO implements Description {
    
    private Software(String description) throws Exception {
	super();
	this.setDescription(description);
	this.commit();
    }

    public Software(SoftwareDO dataObject) {
	super(dataObject);
    }
    
    /**
     * Returns the specified software from the DB
     **/
    public static Software getSoftware(String description) {
	try {
	    SoftwareQuery query = new SoftwareQuery();
	    query.setQueryDescription(description);
	    SoftwareDO element = query.getNextDO();
	    if (element != null) {
		return new Software(element);
	    } else {
		return new Software(description);
	    }
	} catch (Exception e) { throw new RuntimeException(e.toString()); }	
    }

    /**
     * Return a list of all software in the DB
     **/
    public static List getAllSoftware() {
	List result = new ArrayList();
	try {
	    SoftwareQuery query = new SoftwareQuery();
	    query.addOrderByDescription();
	    SoftwareDO element = query.getNextDO();
	    while ( element != null) {
		result.add(new Software(element));
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
	    return (this.getHandle().equals(((SoftwareBDO) other).getHandle()));
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
 * $Log: Software.java,v $
 * Revision 1.1  2000/06/20 13:32:57  pse4
 * Initial revision
 *
 * Revision 1.2  2000/06/10 11:06:24  studer
 * business object for cvsections
 *
 * Revision 1.1  2000/06/09 12:00:20  studer
 * More Entities
 *
 *
 *
 */
