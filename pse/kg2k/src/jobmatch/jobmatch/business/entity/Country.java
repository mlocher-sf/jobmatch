// $Id: Country.java,v 1.4 2000/05/30 12:47:54 studer Exp $

package jobmatch.business.entity;

import com.lutris.dods.builder.generator.query.DataObjectException;
import jobmatch.data.*;
import java.util.*;

/**
 *  Country Business Object
 *
 *  @since May 26 2000
 *  @author $Author: studer $
 *  @version $Revision: 1.4 $
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
    private boolean semanticEquality(Country a, Country b) {
      	try{
	    return (a.getDescription().equals(b.getDescription()));
	}catch (Exception e) {
	    System.out.println(e.toString());
	    throw new RuntimeException(e.toString());
	}
    }

    /** @see Object.toString **/
    public String toString() {
	return super.toString();
    }
       
} //class


/*
 * $Log: Country.java,v $
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
