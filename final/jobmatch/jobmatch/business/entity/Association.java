// $Id: Association.java,v 1.1 2000/06/20 13:32:56 pse4 Exp $

package jobmatch.business.entity;

import com.lutris.dods.builder.generator.query.DataObjectException;
import jobmatch.data.*;
import java.util.*;

/**
 *  Association Business Object
 *
 *  @since June 9 2000
 *  @author $Author: pse4 $
 *  @version $Revision: 1.1 $
 **/
public class Association extends AssociationBDO implements Description {
    
    private Association(String description) throws Exception {
	super();
	this.setDescription(description);
	this.commit();
    }

    public Association(AssociationDO dataObject) {
	super(dataObject);
    }
    
    /**
     * Returns the specified Association from the DB
     **/
    public static Association getAssociation(String description) {
	try {
	    AssociationQuery query = new AssociationQuery();
	    query.setQueryDescription(description);
	    AssociationDO element = query.getNextDO();
	    if (element != null) {
		return new Association(element);
	    } else {
		return new Association(description);
	    }
	} catch (Exception e) { throw new RuntimeException(e.toString()); }	
    }

    /**
     * Return a list of all Associations in the DB
     **/
    public static List getAllAssociations() {
	List result = new ArrayList();
	try {
	    AssociationQuery query = new AssociationQuery();
	    query.addOrderByDescription();
	    AssociationDO element = query.getNextDO();
	    while ( element != null) {
		result.add(new Association(element));
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
	    return (this.getHandle().equals(((AssociationBDO) other).getHandle()));
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
 * $Log: Association.java,v $
 * Revision 1.1  2000/06/20 13:32:56  pse4
 * Initial revision
 *
 * Revision 1.3  2000/06/13 13:13:50  locher
 * fixed equals
 *
 * Revision 1.2  2000/06/10 11:06:22  studer
 * business object for cvsections
 *
 * Revision 1.1  2000/06/09 12:00:15  studer
 * More Entities
 *
 *
 *
 */
