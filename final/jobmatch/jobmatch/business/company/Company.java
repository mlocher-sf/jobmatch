// $Id: Company.java,v 1.1 2000/06/20 13:32:55 pse4 Exp $

package jobmatch.business.company;

import jobmatch.data.*;
import jobmatch.business.company.profile.*;
import jobmatch.business.entity.*;

/**
 *  Represents a Company
 *
 *  @since May 30 2000
 *  @author $Author: pse4 $
 *  @version $Revision: 1.1 $
 **/
public class Company extends CompanyBDO {
    
    private Company() throws Exception {
	super();
    }
    
    public Company(CompanyDO dataObject) {
	super(dataObject);
    }

    /**
     * Returns the company`s address
     **/
    public Address getAddressBO(){
	try {
	    AdressDO data = this.getAdress();
	    if (data == null) {
		data = AdressDO.createVirgin();
		data.commit();
		this.setAdress(data);
		this.commit();
	    }
	    return new Address(data);
	} catch (Exception err){
	    throw new RuntimeException(err.toString());
	}
    }  
 
    /** @see Object.equals **/
    public boolean equals(Object other) {
	if (other == null) {
	    return false;
	}
	
	try {
	    return semanticEquality(this, (Company) other);
	}
	catch (ClassCastException e) {
	    return false;
	}
    }

    /** @return true if a is considered the same as b **/
    private boolean semanticEquality(Company a, Company b) {
	//XXX define the equality and remove the exception !!!
	throw new RuntimeException("semanticEqualitiy not defined");
	//return (a == b);
    }

    /** @see Object.toString **/
    public String toString() {
	return super.toString();
    }
       
} //class

// Document history
/*
 * $Log: Company.java,v $
 * Revision 1.1  2000/06/20 13:32:55  pse4
 * Initial revision
 *
 * Revision 1.2  2000/06/14 12:43:13  studer
 * keyfigures
 *
 * Revision 1.1  2000/05/30 15:52:19  locher
 * added Company and Profile BOs
 *
 * Revision 1.2  2000/05/12 08:46:10  locher
 *
 */

