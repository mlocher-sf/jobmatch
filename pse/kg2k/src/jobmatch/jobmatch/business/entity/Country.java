// $Id: Country.java,v 1.2 2000/05/29 11:58:31 locher Exp $

package jobmatch.business.entity;

import jobmatch.data.*;
import java.util.*;

/**
 *  Country Business Object
 *
 *  @since May 26 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.2 $
 **/
public class Country extends CountryBDO implements Description {
    
    public Country(String description) throws Exception {
	super();
	this.setDescription(description);
	this.commit();
    }

    public Country(CountryDO dataObject) {
	super(dataObject);
    }

    public static List getAllCountries() {
	List result = new ArrayList();
	try {
	    CountryQuery query = new CountryQuery();
	    query.addOrderByDescription();
	    CountryDO element = query.getNextDO();
	    while ( element != null) {
		result.add(new Country(element));
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
	    return semanticEquality(this, (Country) other);
	}
	catch (ClassCastException e) {
	    return false;
	}
    }

    /** @return true if a is considered the same as b **/
    private static boolean semanticEquality(Country a, Country b) {
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
 * $Log: Country.java,v $
 * Revision 1.2  2000/05/29 11:58:31  locher
 * added queries
 *
 * Revision 1.1  2000/05/29 11:24:10  locher
 * Entity Manager
 *
 * Revision 1.1  2000/05/26 13:59:59  locher
 * introduced entity package
 *
 */