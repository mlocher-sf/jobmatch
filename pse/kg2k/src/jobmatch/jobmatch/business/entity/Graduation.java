// $Id: Graduation.java,v 1.1 2000/05/26 13:59:57 locher Exp $

package jobmatch.business.entity;

import jobmatch.data.*;
import java.util.*;

/**
 *  Graduation Business Object
 *
 *  @since May 26 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
public class Graduation extends GraduationBDO implements Description {
    
    public Graduation() throws Exception {
	super();
    }

    public Graduation(GraduationDO dataObject) {
	super(dataObject);
    }

    public static List getAllGraduations() {
	List result = new ArrayList();
	// make a query
	// for every DO create a new Graduation(DO) and add to result
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
 * Revision 1.1  2000/05/26 13:59:57  locher
 * introduced entity package
 *
 */
