package jobmatch.business.candidate.cv;

import jobmatch.business.entity.*;
import jobmatch.business.candidate.*;
import jobmatch.data.*;
import java.util.*;

/**
 *  OperatingSystemSkill Business Object
 *
 *  @since May 26 2000
 *  @author $Author: dniederm $
 *  @version $Revision: 1.1 $
 **/

public class OperatingSystemSkill extends OperatingsystemCandidateBDO implements CVSection {
    
    public OperatingSystemSkill() throws Exception {
	super();
    }

    public OperatingSystemSkill (OperatingSystemCandidateDO dataObject) {
	super(dataObject);
    }
    /**
     * Returns a list of operating system skills for the specified candidate
     **/
    public static List getAllOperatingSystemSkillsFor(Candidate candidate) {
	List result = new ArrayList();
	try{
	    OperatingSystemCandidateQuery query = new OperatingSystemCandidateQuery();
	    query.setQueryCandidate(candidate.getDO());
	    OperatingSystemCandidateDO element  = query.getNextDO();
	     while ( element != null) {
		result.add(new OperatingSystemSkill(element));
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
     * Return the OperatingSystem business object
     **/

    public Software getOperatingSystemBO() {
	try {
	    return new OperatingSystem(this.getOperatingSystem());
	} catch (Exception e) {
	    throws new RuntimeException(e.toString());
	}
    }

  /** @see Object.equals **/
    public boolean equals(Object other) {
	if (other == null) {
	    return false;
	}
	
	try {
	    return semanticEquality(this, (OperatingSystemSkill) other);
	}
	catch (ClassCastException e) {
	    return false;
	}
    }

  /** @return true if a is considered the same as b **/
    private static boolean semanticEquality(OperatingSystemSkill a, OperatingSystemSkill b) {
	//XXX define the equality and remove the exception !!!
	throw new RuntimeException("semanticEqualitiy not defined");
	//return (a == b);
    }

  /** @see Object.toString **/
    public String toString() {
	return super.toString();
    }
       
} //class
