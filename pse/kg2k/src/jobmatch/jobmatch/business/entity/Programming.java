// $Id: Programming.java,v 1.1 2000/06/09 12:00:20 studer Exp $

package jobmatch.business.entity;

import com.lutris.dods.builder.generator.query.DataObjectException;
import jobmatch.data.*;
import java.util.*;

/**
 *  Programming Business Object
 *
 *  @since June 9 2000
 *  @author $Author: studer $
 *  @version $Revision: 1.1 $
 **/
public class Programming extends ProgrammingBDO implements Description {
    
    private Programming(String description) throws Exception {
	super();
	this.setDescription(description);
	this.commit();
    }

    public Programming(ProgrammingDO dataObject) {
	super(dataObject);
    }
    
    /**
     * Returns the specified Programming from the DB
     **/
    public static Programming getProgramming(String description) {
	try {
	    ProgrammingQuery query = new ProgrammingQuery();
	    query.setQueryDescription(description);
	    ProgrammingDO element = query.getNextDO();
	    if (element != null) {
		return new Programming(element);
	    } else {
		return new Programming(description);
	    }
	} catch (Exception e) { throw new RuntimeException(e.toString()); }	
    }

    /**
     * Return a list of all Programming in the DB
     **/
    public static List getAllProgramming() {
	List result = new ArrayList();
	try {
	    ProgrammingQuery query = new ProgrammingQuery();
	    query.addOrderByDescription();
	    ProgrammingDO element = query.getNextDO();
	    while ( element != null) {
		result.add(new Programming(element));
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
	    return (this.getHandle().equals(((ProgrammingBDO) other).getHandle()));
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
 * $Log: Programming.java,v $
 * Revision 1.1  2000/06/09 12:00:20  studer
 * More Entities
 *
 *
 *
 */
