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
 *  @version $Revision: 1.2 $
 **/

public class OperatingSystemSkill extends OperatingsystemCandidateBDO implements CVSection {
    
    public OperatingSystemSkill() throws Exception {
	super();
    }

    public OperatingSystemSkill (OperatingsystemCandidateDO dataObject) {
	super(dataObject);
    }
    /**
     * Returns a list of operating system skills for the specified candidate
     **/
    public static List getAllOperatingSystemSkillsFor(Candidate candidate) {
	List result = new ArrayList();
	try{
	    OperatingsystemCandidateQuery query = new OperatingsystemCandidateQuery();
	    query.setQueryCandidate(candidate.getDO());
	    OperatingsystemCandidateDO element  = query.getNextDO();
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

    public OperatingSystem  getOperatingSystemBO() {
	try {
	    return new OperatingSystem(this.getOperatingsystem());
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

/*
 * $Log: OperatingSystemSkill.java,v $
 * Revision 1.2  2000/06/10 11:02:43  dniederm
 * *** empty log message ***
 *
 */
