// $Id: Schooltype.java,v 1.3 2000/05/29 11:58:32 locher Exp $

package jobmatch.business.entity;

import jobmatch.data.*;
import java.util.*;

/**
 *  Schooltype Business Object
 *
 *  @since May 26 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.3 $
 **/
public class Schooltype extends SchooltypeBDO implements Description {
    
    public Schooltype(String description) throws Exception {
	super();
	this.setDescription(description);
	this.commit();
    }

    public Schooltype(SchooltypeDO dataObject) {
	super(dataObject);
    }

    public static List getAllSchooltypes() {
	List result = new ArrayList();
	try {
	    SchooltypeQuery query = new SchooltypeQuery();
	    query.addOrderByDescription();
	    SchooltypeDO element = query.getNextDO();
	    while ( element != null) {
		result.add(new Schooltype(element));
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
 * Revision 1.3  2000/05/29 11:58:32  locher
 * added queries
 *
 * Revision 1.2  2000/05/29 11:24:12  locher
 * Entity Manager
 *
 * Revision 1.1  2000/05/26 13:59:59  locher
 * introduced entity package
 *
 */
