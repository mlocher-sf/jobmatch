// $Id: Address.java,v 1.1 2000/06/20 13:32:56 pse4 Exp $

package jobmatch.business.entity;

import com.lutris.dods.builder.generator.query.DataObjectException;
import jobmatch.data.*;

/**
 *  Address Business Object
 *
 *  @since May 26 2000
 *  @author $Author: pse4 $
 *  @version $Revision: 1.1 $
 **/
public class Address extends AdressBDO {
    
    private Address() throws Exception {
	super();
    }

    public Address(AdressDO dataObject) {
	super(dataObject);
    }

    public Country getCountryBO() {
	try {
	    return new Country(this.getCountry());
	} catch (Exception e) {
	    throw new RuntimeException(e.toString());
	}
    }
    
    /** @see Object.equals **/
    public boolean equals(Object other) {
	if (other == null) {
	    return false;
	}
	
	try {
	    return semanticEquality(this, (Address) other);
	}
	catch (ClassCastException e) {
	    return false;
	}
    }

    /** @return true if a is considered the same as b **/
    private boolean semanticEquality(Address a, Address b) {
      	try{
	    return a == b;
	}catch (Exception e) {
	    System.out.println(e.toString());
	    throw new RuntimeException(e.toString());
	}
    }

    /** @see Object.toString **/
    public String toString() {
	try {
	    return super.toString();
	} catch (Exception e) { 
	    throw new RuntimeException(e.toString()); 
	}
    }

} //class


/*
 * $Log: Address.java,v $
 * Revision 1.1  2000/06/20 13:32:56  pse4
 * Initial revision
 *
 * Revision 1.2  2000/06/04 11:50:42  locher
 * many little fixes
 *
 * Revision 1.1  2000/06/02 16:02:31  locher
 * introduced address entities
 *
 */
