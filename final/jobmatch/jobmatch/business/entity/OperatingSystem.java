// $Id: OperatingSystem.java,v 1.1 2000/06/20 13:32:57 pse4 Exp $

package jobmatch.business.entity;

import com.lutris.dods.builder.generator.query.DataObjectException;
import jobmatch.data.*;
import java.util.*;

/**
 *  OperatingSystem Business Object
 *
 *  @since June 9 2000
 *  @author $Author: pse4 $
 *  @version $Revision: 1.1 $
 **/
public class OperatingSystem extends OperatingsystemBDO implements Description {
    
    private OperatingSystem(String description) throws Exception {
	super();
	this.setDescription(description);
	this.commit();
    }

    public OperatingSystem(OperatingsystemDO dataObject) {
	super(dataObject);
    }
    
    /**
     * Returns the specified Operatingsystem from the DB
     **/
    public static OperatingSystem getOperatingSystem(String description) {
	try {
	    OperatingsystemQuery query = new OperatingsystemQuery();
	    query.setQueryDescription(description);
	    OperatingsystemDO element = query.getNextDO();
	    if (element != null) {
		return new OperatingSystem(element);
	    } else {
		return new OperatingSystem(description);
	    }
	} catch (Exception e) { throw new RuntimeException(e.toString()); }	
    }

    /**
     * Return a list of all Operatingsystem in the DB
     **/
    public static List getAllOperatingSystem() {
	List result = new ArrayList();
	try {
	    OperatingsystemQuery query = new OperatingsystemQuery();
	    query.addOrderByDescription();
	    OperatingsystemDO element = query.getNextDO();
	    while ( element != null) {
		result.add(new OperatingSystem(element));
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
	    return (this.getHandle().equals(((OperatingsystemBDO) other).getHandle()));
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
 * $Log: OperatingSystem.java,v $
 * Revision 1.1  2000/06/20 13:32:57  pse4
 * Initial revision
 *
 * Revision 1.1  2000/06/09 12:00:19  studer
 * More Entities
 *
 *
 *
 */
