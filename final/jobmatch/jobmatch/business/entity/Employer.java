package jobmatch.business.entity;

import com.lutris.dods.builder.generator.query.DataObjectException;
import jobmatch.data.*;
import java.util.*;

/**
 *  Employer Business Object
 *
 *  @since May 26 2000
 *  @author $Author: pse4 $
 *  @version $Revision: 1.1 $
 **/
public class Employer extends EmployerBDO  {
    
    private Employer(String name) throws Exception {
	super();
	this.setName(name);
	this.commit();
    }

    public Employer(EmployerDO dataObject) {
	super(dataObject);
    }

    public Address getAddressBO(){
	try {
	    return new Address(this.getAdress());
	} catch (Exception e) {
	    throw new RuntimeException(e.toString());
	}
    }
        
    
    /**
     * Returns the specified Employer from the DB
     **/
    public static Employer getEmployer(String name) {
	try {
	    EmployerQuery query = new EmployerQuery();
	    query.setQueryName(name);
	    EmployerDO element = query.getNextDO();
	    if (element != null) {
		return new Employer(element);
	    } else {
		return new Employer(name);
	    }
	} catch (Exception e) { throw new RuntimeException(e.toString()); }	
    }

    
    /** @see Object.equals **/
    public boolean equals(Object other) {
	if (other == null) {
	    return false;
	}
	
	try {
	    return (this.getHandle().equals(((EmployerBDO) other).getHandle()));
	}
	catch (Exception e) {
	    return false;
	}
    }

    /** @see Object.toString **/
    public String toString() {
	try {
	    return this.getName() + this.getAdress().toString();
	} catch (Exception e) { 
	    throw new RuntimeException(e.toString()); 
	}
    }
    

       
} //class


/*
 * $Log: Employer.java,v $
 * Revision 1.1  2000/06/20 13:32:57  pse4
 * Initial revision
 *
 * Revision 1.1  2000/06/09 12:00:17  studer
 * More Entities
 *
 * 
 *
 */
