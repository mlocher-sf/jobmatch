// $Id: EntityManager.java,v 1.3 2000/06/10 13:35:39 studer Exp $

package jobmatch.business.entity;

import java.util.*;
import jobmatch.data.*;
import com.lutris.dods.builder.generator.query.*;

/**
 *  Controls access to Entities
 *
 *  @since May 29 2000
 *  @author $Author: studer $
 *  @version $Revision: 1.3 $
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

    public static List getLanguages() {
	return Language.getAllLanguage();
    }

    public static List getLanguageCapabilities(){
	return LanguageCapability.getAllCapabilities();
    }
  
    public static List getCountries() {
	return Country.getAllCountries();
    }

    public static List getSchools() {
	return School.getAllSchools();
    }

    public static List getSchooltypes() {
	return Schooltype.getAllSchooltypes();
    }

    public static List getGraduations() {
	return Graduation.getAllGraduations();
    }
    
} //class

/*
 * $Log: EntityManager.java,v $
 * Revision 1.3  2000/06/10 13:35:39  studer
 * *** empty log message ***
 *
 * Revision 1.2  2000/05/29 11:24:11  locher
 * Entity Manager
 *
 * Revision 1.1  2000/05/29 11:06:13  locher
 * Entity Manager
 *
 */
