// $Id: School.java,v 1.7 2000/06/06 11:39:54 studer Exp $

package jobmatch.business.entity;

import jobmatch.data.*;
import java.util.*;

/**
 *  School Business Object
 *
 *  @since May 26 2000
 *  @author $Author: studer $
 *  @version $Revision: 1.7 $
 **/
public class School extends SchoolBDO implements Description {
    
    private School(String description) throws Exception {
	super();
	this.setDescription(description);
   	this.setType(Schooltype.getDefault());
	this.commit();
    }

    public School(SchoolDO dataObject) {
	super(dataObject);
    }

    /**
     * Return the specified school from the DB
     **/
   public static School getSchool(String school) {
	try {
	    SchoolQuery query = new SchoolQuery();
	    query.setQueryDescription(school);
	    SchoolDO element = query.getNextDO();
	    if (element != null) {
		return new School(element);
	    } else {
		return new School(school);
	    }
	} catch (Exception e) { throw new RuntimeException(e.toString()); }	
    }

    /**
     * Return the Schooltype business object
     **/
    public Schooltype getSchoolTypeBO() {
	try {
	    return new Schooltype(this.getType());
	} catch (Exception e) {
	    throw new RuntimeException(e.toString());
	}
    }
    
    /**
     * Return a list of all schools in the DB
     **/
    public static List getAllSchools() {
	List result = new ArrayList();
	try {
	    SchoolQuery query = new SchoolQuery();
	    query.addOrderByDescription();
	    SchoolDO element = query.getNextDO();
	    while ( element != null) {
		result.add(new School(element));
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
	    return semanticEquality(this, (School) other);
	}
	catch (ClassCastException e) {
	    return false;
	}
    }

    /** @return true if a is considered the same as b **/
    private boolean semanticEquality(School a, School b) {
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
 * $Log: School.java,v $
 * Revision 1.7  2000/06/06 11:39:54  studer
 * getSchooltype methode hinzugefuegt
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
 * Revision 1.2  2000/05/29 11:24:12  locher
 * Entity Manager
 *
 * Revision 1.1  2000/05/26 13:59:59  locher
 * introduced entity package
 *
 */
