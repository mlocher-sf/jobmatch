// $Id: Jobwish.java,v 1.1 2000/06/10 11:06:19 studer Exp $

package jobmatch.business.candidate.cv;

import jobmatch.business.entity.*;
import jobmatch.business.candidate.*;
import jobmatch.data.*;
import java.util.*;

/**
 *  Jobwish Business Object
 *
 *  @since June 10 2000
 *  @author $Author: studer $
 *  @version $Revision: 1.1 $
 **/
public class Jobwish extends JobwishBDO implements CVSection {
    
    public Jobwish() throws Exception {
	super();
    }

    public Jobwish(JobwishDO dataObject) {
	super(dataObject);
    }

    /**
     * Returns the Area business object
     **/
    public Area getAreaBO(){
 
  	try {
	    return new Area(this.getArea());
	} catch (Exception e) {
	    throw new RuntimeException(e.toString());
	}
    }	

    /**
     * Returns the Industry business object
     **/
    public Industry getIndustryBO(){
 
  	try {
	    return new Industry(this.getIndustry());
	} catch (Exception e) {
	    throw new RuntimeException(e.toString());
	}
    }	


    /**
     * Returns a list of Jobwishes for the specified candidate
     **/
    public static List getAllJobwishFor(Candidate candidate) {
	List result = new ArrayList();
	try{
	    JobwishQuery query = new JobwishQuery();
	    query.setQueryCandidate(candidate.getDO());
	    JobwishDO element  = query.getNextDO();
	     while ( element != null) {
		result.add(new Jobwish(element));
		element = query.getNextDO();
	    }
	} catch (Exception qe) {
	    System.err.println(qe);
	}
	return result;
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
    
    /** @see Object.equals **/
    public boolean equals(Object other) {
	if (other == null) {
	    return false;
	}
	
	try {
	    return semanticEquality(this, (Jobwish) other);
	}
	catch (ClassCastException e) {
	    return false;
	}
    }

    /** @return true if a is considered the same as b **/
    private static boolean semanticEquality(Jobwish a, Jobwish b) {
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
 * $Log: Jobwish.java,v $
 * Revision 1.1  2000/06/10 11:06:19  studer
 * business object for cvsections
 *
 *
 */
