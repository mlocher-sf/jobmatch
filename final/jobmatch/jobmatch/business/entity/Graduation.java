// $Id: Graduation.java,v 1.1 2000/06/20 13:32:57 pse4 Exp $

package jobmatch.business.entity;

import jobmatch.data.*;
import java.util.*;

/**
 *  Graduation Business Object
 *
 *  @since May 26 2000
 *  @author $Author: pse4 $
 *  @version $Revision: 1.1 $
 **/
public class Graduation extends GraduationBDO implements Description {
    
    /**
     * Private Constructor
     **/
    private Graduation(String description) throws Exception {
	super();
	this.setDescription(description);
	this.commit();
    }

    public Graduation(GraduationDO dataObject) {
	super(dataObject);
    }

    /**
     * Gets the specified graduation from the DB
     **/
    public static Graduation getGraduation(String graduation) {
	try {
	    GraduationQuery query = new GraduationQuery();
	    query.setQueryDescription(graduation);
	    GraduationDO element = query.getNextDO();
	    if (element != null) {
		return new Graduation(element);
	    } else {
		return new Graduation(graduation);
	    }
	} catch (Exception e) { throw new RuntimeException(e.toString()); }	
    }

    /**
     * Return a list with all graduationdata from the DB
     **/
    public static List getAllGraduations() {
	List result = new ArrayList();
	try {
	    GraduationQuery query = new GraduationQuery();
	    query.addOrderByDescription();
	    GraduationDO element = query.getNextDO();
	    while ( element != null) {
		result.add(new Graduation(element));
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
	    return (this.getHandle().equals(((Graduation) other).getHandle()));
	} catch (Exception e) {
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
 * $Log: Graduation.java,v $
 * Revision 1.1  2000/06/20 13:32:57  pse4
 * Initial revision
 *
 * Revision 1.8  2000/06/13 13:13:51  locher
 * fixed equals
 *
 * Revision 1.7  2000/05/31 13:34:15  loeffel
 * Javadoc - Bug fixed
 *
 * Revision 1.6  2000/05/31 12:15:51  studer
 * Javadoc added
 *
 * Revision 1.5  2000/05/30 14:24:29  locher
 * toString defined
 *
 * Revision 1.4  2000/05/30 08:26:10  locher
 * get methods for entities
 *
 * Revision 1.3  2000/05/29 11:58:31  locher
 * added queries
 *
 * Revision 1.2  2000/05/29 11:24:11  locher
 * Entity Manager
 *
 * Revision 1.1  2000/05/26 13:59:57  locher
 * introduced entity package
 *
 */
