// $Id: Schooltype.java,v 1.1 2000/06/20 13:32:57 pse4 Exp $

package jobmatch.business.entity;

import jobmatch.data.*;
import java.util.*;

/**
 *  Schooltype Business Object
 *
 *  @since May 26 2000
 *  @author $Author: pse4 $
 *  @version $Revision: 1.1 $
 **/
public class Schooltype extends SchooltypeBDO implements Description {
    
    private Schooltype(String description) throws Exception {
	super();
	this.setDescription(description);
	this.commit();
    }

    public Schooltype(SchooltypeDO dataObject) {
	super(dataObject);
    }

   public static Schooltype getSchooltype(String schooltype) {
	try {
	    SchooltypeQuery query = new SchooltypeQuery();
	    query.setQueryDescription(schooltype);
	    SchooltypeDO element = query.getNextDO();
	    if (element != null) {
		return new Schooltype(element);
	    } else {
		return new Schooltype(schooltype);
	    }
	} catch (Exception e) { throw new RuntimeException(e.toString()); }	
    }

    public static Schooltype getDefault(){
	return getSchooltype("Primarschule");
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
	    return (this.getHandle().equals(((Schooltype) other).getHandle()));
	} catch (Exception e) {
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
	try {
	    return this.getDescription();
	} catch (Exception e) { 
	    throw new RuntimeException(e.toString()); 
	}
    }
       
} //class


/*
 * $Log: Schooltype.java,v $
 * Revision 1.1  2000/06/20 13:32:57  pse4
 * Initial revision
 *
 * Revision 1.7  2000/06/13 13:13:51  locher
 * fixed equals
 *
 * Revision 1.6  2000/06/06 11:39:55  studer
 * getSchooltype methode hinzugefuegt
 *
 * Revision 1.5  2000/05/30 14:24:29  locher
 * toString defined
 *
 * Revision 1.4  2000/05/30 08:26:11  locher
 * get methods for entities
 *
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
