// $Id: Formation.java,v 1.1 2000/06/20 13:32:54 pse4 Exp $

package jobmatch.business.candidate.cv;

import jobmatch.business.entity.*;
import jobmatch.business.candidate.*;
import jobmatch.data.*;
import java.util.*;

/**
 *  Formation Business Object
 *
 *  @since May 26 2000
 *  @author $Author: pse4 $
 *  @version $Revision: 1.1 $
 **/
public class Formation extends SchoolCandidateBDO implements CVSection {
    
    public Formation() throws Exception {
	super();
    }

    public Formation(SchoolCandidateDO dataObject) {
	super(dataObject);
    }

    /**
     * Returns a list of formations for the specified candidate
     **/
    public static List getAllFormationsFor(Candidate candidate) {
	List result = new ArrayList();
	try{
	    SchoolCandidateQuery query = new SchoolCandidateQuery();
	    query.setQueryCandidate(candidate.getDO());
	    SchoolCandidateDO element  = query.getNextDO();
	     while ( element != null) {
		result.add(new Formation(element));
		element = query.getNextDO();
	    }
	} catch (Exception qe) {
	    System.err.println(qe);
	}
	return result;
    }

    /**
     * Returns the Formations business Object specified by the string
     * representation of a data object
     */
    public static Formation getFromHandle(String handle) {
	return new Formation(SchoolCandidateDO.createExisting(handle));
    }

    /**
     * Returns the Candidate business object
     **/
    public Candidate getCandidateBO(){
	try {
	    return new Candidate(this.getCandidate());
	}
	catch (Exception e) {
	    throw new RuntimeException(e.toString());
	}
    }

    /**
     * Returns the School business object
     **/
    public School getSchoolBO() {
	try {
	    return new School(this.getSchool());
	} catch (Exception e) {
	    throw new RuntimeException(e.toString());
	}
    }
    
    /**
     * Returns the Graduation business object
     **/
    public Graduation getGraduationBO() {
	try {
	    return new Graduation(this.getDiploma());
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
	    return semanticEquality(this, (Formation) other);
	}
	catch (ClassCastException e) {
	    return false;
	}
    }

    /** @return true if a is considered the same as b **/
    private static boolean semanticEquality(Formation a, Formation b) {
	//XXX define the equality and remove the exception !!!
	throw new RuntimeException("semanticEqualitiy not defined");
	//return (a == b);
    }

    /** @see Object.toString **/
    public String toString() {
	return super.toString();
    }
       
} //class


/*
 * $Log: Formation.java,v $
 * Revision 1.1  2000/06/20 13:32:54  pse4
 * Initial revision
 *
 * Revision 1.6  2000/06/13 08:03:47  dniederm
 * Methode getFromHandle hinzugefuegt
 *
 * Revision 1.5  2000/06/10 11:06:18  studer
 * business object for cvsections
 *
 * Revision 1.4  2000/05/31 12:15:47  studer
 * Javadoc added
 *
 * Revision 1.3  2000/05/31 06:09:25  studer
 * Funktionalitaet zum dynamischen Fuellen einer Tabelle
 *
 * Revision 1.2  2000/05/26 13:59:55  locher
 * introduced entity package
 *
 * Revision 1.1  2000/05/26 11:54:54  locher
 * Fromation object and query skeleton in Candidate
 *
 */
