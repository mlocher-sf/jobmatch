// $Id: EntityTest.java,v 1.1 2000/06/05 12:14:41 studer Exp $

package jobmatch.business.entity.test;

import junit.framework.*;
import jobmatch.business.entity.*;

/**
 *  TestCase for entites
 *
 *  @since June 5 2000
 *  @author $Author: studer $
 *  @version $Revision: 1.1 $
 **/
public class EntityTest extends TestCase {

    public EntityTest(String name) {
	super(name);

    }

    public void setUp(){
	
	
    }
    

    public void testCountryEquals(){
	Country country = Country.getCountry("Schweiz");
	Country nation = Country.getCountry("Schweiz");
	assert(country.equals(nation));
    }

    public static Test suite() { 
	TestSuite suite= new TestSuite(EntityTest.class);
	return suite;
    }

} //class

// Document history
/*
 *
 */
