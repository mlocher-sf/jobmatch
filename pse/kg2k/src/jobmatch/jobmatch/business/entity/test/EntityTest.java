// $Id: EntityTest.java,v 1.3 2000/06/12 16:27:58 locher Exp $

package jobmatch.business.entity.test;

import junit.framework.*;
import jobmatch.business.entity.*;

/**
 *  TestCase for entites
 *
 *  @since June 5 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.3 $
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
	assert(suisse.equals(ch));
	assert(!ch.equals(null));
	assert(!ch.equals(de));
	assert(!suisse.equals(de));
    }

    public static Test suite() { 
	TestSuite suite= new TestSuite(EntityTest.class);
	return suite;
    }

} //class

// Document history
/*
 * $Log: EntityTest.java,v $
 * Revision 1.3  2000/06/12 16:27:58  locher
 * added new data layer tests
 *
 */
