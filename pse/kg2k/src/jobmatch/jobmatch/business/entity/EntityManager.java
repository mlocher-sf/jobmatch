// $Id: EntityManager.java,v 1.1 2000/05/29 11:06:13 locher Exp $

package jobmatch.business.entity;

import java.util.*;
import jobmatch.data.*;
import com.lutris.dods.builder.generator.query.*;

/**
 *  Controls access to Entities
 *
 *  @since May 29 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
final public class EntityManager {

    private final static EntityManager uniqueInstance;
    
    static {
	uniqueInstance = new EntityManager();
    }

    private EntityManager() {
	super();
    }

    public static EntityManager getUniqueInstance() {
	return uniqueInstance;
    }

    public static List getCountries() {
	// do a query and return a list of Countries
	return new ArrayList();
    }
    
} //class

/*
 * $Log: EntityManager.java,v $
 * Revision 1.1  2000/05/29 11:06:13  locher
 * Entity Manager
 *
 */
