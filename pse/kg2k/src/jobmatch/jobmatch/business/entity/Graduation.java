// $Id: Graduation.java,v 1.3 2000/05/29 11:58:31 locher Exp $

package jobmatch.business.entity;

import jobmatch.data.*;
import java.util.*;

/**
 *  Graduation Business Object
 *
 *  @since May 26 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.3 $
 **/
public class Graduation extends GraduationBDO implements Description {
    
    public Graduation(String description) throws Exception {
	super();
	this.setDescription(description);
	this.commit();
    }

    public Graduation(GraduationDO dataObject) {
	super(dataObject);
    }

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
	    return semanticEquality(this, (Graduation) other);
	}
	catch (ClassCastException e) {
	    return false;
	}
    }

    /** @return true if a is considered the same as b **/
    private static boolean semanticEquality(Graduation a, Graduation b) {
	//XXX define the equality and remove the exception !!!
	throw new RuntimeException("semanticEqualitiy not defined");
	//return (a == b);
    }

    /** @see Object.toString **/
    public String toString() {
	return super.toString();
    }
       
} //class


/*
 * $Log: Graduation.java,v $
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
