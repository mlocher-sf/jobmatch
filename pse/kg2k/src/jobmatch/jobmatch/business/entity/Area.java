// $Id: Area.java,v 1.1 2000/06/10 11:06:21 studer Exp $

package jobmatch.business.entity;

import com.lutris.dods.builder.generator.query.DataObjectException;
import jobmatch.data.*;
import java.util.*;

/**
 *  Area Business Object
 *
 *  @since June 10 2000
 *  @author $Author: studer $
 *  @version $Revision: 1.1 $
 **/
public class Area extends AreaBDO implements Description {
    
    private Area(String description) throws Exception {
	super();
	this.setDescription(description);
	this.commit();
    }

    public Area(AreaDO dataObject) {
	super(dataObject);
    }
    
    /**
     * Returns the specified area from the DB
     **/
    public static Area getArea(String industry) {
	try {
	    AreaQuery query = new AreaQuery();
	    query.setQueryDescription(industry);
	    AreaDO element = query.getNextDO();
	    if (element != null) {
		return new Area(element);
	    } else {
		return new Area(industry);
	    }
	} catch (Exception e) { throw new RuntimeException(e.toString()); }	
    }

    /**
     * Return a list of all areas in the DB
     **/
    public static List getAllIndustries() {
	List result = new ArrayList();
	try {
	    AreaQuery query = new AreaQuery();
	    query.addOrderByDescription();
	    AreaDO element = query.getNextDO();
	    while ( element != null) {
		result.add(new Area(element));
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
	    return (this.getHandle().equals(((AreaBDO) other).getHandle()));
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
 * $Log: Area.java,v $
 * Revision 1.1  2000/06/10 11:06:21  studer
 * business object for cvsections
 *
 *
 */
