// $Id: Country.java,v 1.7 2000/06/04 18:17:31 locher Exp $

package jobmatch.business.entity;

import com.lutris.dods.builder.generator.query.DataObjectException;
import jobmatch.data.*;
import java.util.*;

/**
 *  Country Business Object
 *
 *  @since May 26 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.7 $
 **/
public class Country extends CountryBDO implements Description {
    
    private Country(String description) throws Exception {
	super();
	this.setDescription(description);
	this.commit();
    }

    public Country(CountryDO dataObject) {
	super(dataObject);
    }
    
    /**
     * Returns the specified country from the DB
     **/
    public static Country getCountry(String country) {
	try {
	    CountryQuery query = new CountryQuery();
	    query.setQueryDescription(country);
	    CountryDO element = query.getNextDO();
	    if (element != null) {
		return new Country(element);
	    } else {
		return new Country(country);
	    }
	} catch (Exception e) { throw new RuntimeException(e.toString()); }	
    }

    /**
     * Return a list of all countries in the DB
     **/
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
	    return (this.getHandle().equals(((CountryBDO) other).getHandle()));
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
 * $Log: Country.java,v $
 * Revision 1.7  2000/06/04 18:17:31  locher
 * evil equals s
 *
 * Revision 1.6  2000/05/31 12:15:50  studer
 * Javadoc added
 *
 * Revision 1.5  2000/05/30 14:24:28  locher
 * toString defined
 *
 * Revision 1.4  2000/05/30 12:47:54  studer
 * DropDown wird jetzt via DB abefuellt
 *
 * Revision 1.3  2000/05/30 08:26:09  locher
 * get methods for entities
 *
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
