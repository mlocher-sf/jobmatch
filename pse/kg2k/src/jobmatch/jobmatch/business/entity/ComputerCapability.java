// $Id: ComputerCapability.java,v 1.2 2000/06/10 13:35:38 studer Exp $

package jobmatch.business.entity;

import com.lutris.dods.builder.generator.query.DataObjectException;
import jobmatch.data.*;
import java.util.*;

/**
 *  ComputerCapability Business Object
 *
 *  @since June 10 2000
 *  @author $Author: studer $
 *  @version $Revision: 1.2 $
 **/
public class ComputerCapability extends CompcapabilityBDO implements Capability {
    
    private ComputerCapability(String description) throws Exception {
	super();
	this.setDescription(description);
	this.commit();
    }

    public ComputerCapability(CompcapabilityDO dataObject) {
	super(dataObject);
    }
    
    /**
     * Returns the specified Computercapability from the DB
     **/
    public static ComputerCapability getCapability(String description) {
	try {
	    CompcapabilityQuery query = new CompcapabilityQuery();
	    query.setQueryDescription(description);
	    CompcapabilityDO element = query.getNextDO();
	    if (element != null) {
		return new ComputerCapability(element);
	    } else {
		return new ComputerCapability(description);
	    }
	} catch (Exception e) { throw new RuntimeException(e.toString()); }	
    }

    /**
     * Returns a list of all Computercapabilities in the DB
     **/
    public  static List getAllCapabilities() {
	List result = new ArrayList();
	try {
	    CompcapabilityQuery query = new CompcapabilityQuery();
	    query.addOrderByDescription();
	    CompcapabilityDO element = query.getNextDO();
	    while ( element != null) {
		result.add(new ComputerCapability(element));
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
	    return (this.getHandle().equals(((CompcapabilityBDO) other).getHandle()));
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
 * $Log: ComputerCapability.java,v $
 * Revision 1.2  2000/06/10 13:35:38  studer
 * *** empty log message ***
 *
 * Revision 1.1  2000/06/10 11:06:23  studer
 * business object for cvsections
 *
 *
 *
 */
