// $Id: EntityTest.java,v 1.2 2000/06/05 14:05:54 studer Exp $

package jobmatch.business.entity.test;

import junit.framework.*;
import jobmatch.business.entity.*;

/**
 *  TestCase for entites
 *
 *  @since June 5 2000
 *  @author $Author: studer $
 *  @version $Revision: 1.2 $
 **/
public class EntityTest extends TestCase {

    public EntityTest(String name) {
	super(name);

    }

    public void setUp(){
	
	
    }
    

    public void testCountryEquals(){
	Country suisse = Country.getCountry("Schweiz");
	Country ch = Country.getCountry("Schweiz");
	Country de = Country.getCountry("Deutschland");
	assert(ch.equals(suisse));
	assert(!ch.equals(null));
	assert(!ch.equals(de));
	
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
