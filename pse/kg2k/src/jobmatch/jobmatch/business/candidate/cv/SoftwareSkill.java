package jobmatch.business.candidate.cv;

import jobmatch.business.entity.*;
import jobmatch.business.candidate.*;
import jobmatch.data.*;
import java.util.*;

/**
 *  SoftwareSkill Business Object
 *
 *  @since May 26 2000
 *  @author $Author: dniederm $
 *  @version $Revision: 1.2 $
 **/

public class SoftwareSkill extends SoftwareCandidateBDO implements CVSection {
    
    public SoftwareSkill() throws Exception {
	super();
    }

    public SoftwareSkill(SoftwareCandidateDO dataObject) {
	super(dataObject);
    }
    /**
     * Returns a list of software skills for the specified candidate
     **/
    public static List getAllSoftwareSkillsFor(Candidate candidate) {
	List result = new ArrayList();
	try{
	    SoftwareCandidateQuery query = new SoftwareCandidateQuery();
	    query.setQueryCandidate(candidate.getDO());
	    SoftwareCandidateDO element  = query.getNextDO();
	     while ( element != null) {
		result.add(new SoftwareSkill(element));
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

    /**
     * Return the Software  business object
     **/

    public Software getSoftwareBO() {
	try {
	    return new Software(this.getSoftware());
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
	    return semanticEquality(this, (SoftwareSkill) other);
	}
	catch (ClassCastException e) {
	    return false;
	}
    }

  /** @return true if a is considered the same as b **/
    private static boolean semanticEquality(SoftwareSkill a, SoftwareSkill b) {
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
 * $Log: SoftwareSkill.java,v $
 * Revision 1.2  2000/06/10 11:02:44  dniederm
 * *** empty log message ***
 *
 */
