// $Id: School.java,v 1.1 2000/05/26 13:59:59 locher Exp $

package jobmatch.business.entity;

import jobmatch.data.*;
import java.util.*;

/**
 *  School Business Object
 *
 *  @since May 26 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
public class School extends SchoolBDO implements Description {
    
    public School() throws Exception {
	super();
    }

    public School(SchoolDO dataObject) {
	super(dataObject);
    }

    public static List getAllSchools() {
	List result = new ArrayList();
	// make a query
	// for every DO create a new School(DO) and add to result
	return result;
    }
    
    /** @see Object.equals **/
    public boolean equals(Object other) {
	if (other == null) {
	    return false;
	}
	
	try {
	    return semanticEquality(this, (School) other);
	}
	catch (ClassCastException e) {
	    return false;
	}
    }

    /** @return true if a is considered the same as b **/
    private static boolean semanticEquality(School a, School b) {
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
 * $Log: School.java,v $
 * Revision 1.1  2000/05/26 13:59:59  locher
 * introduced entity package
 *
 */
