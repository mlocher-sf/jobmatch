// $Id: Schooltype.java,v 1.1 2000/05/26 13:59:59 locher Exp $

package jobmatch.business.entity;

import jobmatch.data.*;
import java.util.*;

/**
 *  Schooltype Business Object
 *
 *  @since May 26 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
public class Schooltype extends SchooltypeBDO implements Description {
    
    public Schooltype() throws Exception {
	super();
    }

    public Schooltype(SchooltypeDO dataObject) {
	super(dataObject);
    }

    public static List getAllSchooltypes() {
	List result = new ArrayList();
	// make a query
	// for every DO create a new Schooltype(DO) and add to result
	return result;
    }
    
    /** @see Object.equals **/
    public boolean equals(Object other) {
	if (other == null) {
	    return false;
	}
	
	try {
	    return semanticEquality(this, (Schooltype) other);
	}
	catch (ClassCastException e) {
	    return false;
	}
    }

    /** @return true if a is considered the same as b **/
    private static boolean semanticEquality(Schooltype a, Schooltype b) {
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
 * $Log: Schooltype.java,v $
 * Revision 1.1  2000/05/26 13:59:59  locher
 * introduced entity package
 *
 */
