package jobmatch.business.candidate.cv;

import jobmatch.business.entity.*;
import jobmatch.business.candidate.*;
import jobmatch.data.*;
import java.util.*;

/**
 *  ProgrammingSkill Business Object
 *
 *  @since May 26 2000
 *  @author $Author: dniederm $
 *  @version $Revision: 1.1 $
 **/

public class ProgrammingSkill extends ProgrammingCandidateBDO implements CVSection {
    
    public ProgramingSkill() throws Exception {
	super();
    }

    public ProgrammingSkill(ProgrammingCandidateDO dataObject) {
	super(dataObject);
    }
    /**
     * Returns a list of programming skills for the specified candidate
     **/
    public static List getAllProgrammingSkillsFor(Candidate candidate) {
	List result = new ArrayList();
	try{
	    ProgrammingCandidateQuery query = new ProgrammingCandidateQuery();
	    query.setQueryCandidate(candidate.getDO());
	    ProgrammingCandidateDO element  = query.getNextDO();
	     while ( element != null) {
		result.add(new ProgrammingSkill(element));
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
     * Return the Programming business object
     **/

    public Programming getProgrammingBO() {
	try {
	    return new Programming(this.getProgramming());
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
	    return semanticEquality(this, (PrrogrammingSkill) other);
	}
	catch (ClassCastException e) {
	    return false;
	}
    }

  /** @return true if a is considered the same as b **/
    private static boolean semanticEquality(ProgrammingSkill a, ProgrammingSkill b) {
	//XXX define the equality and remove the exception !!!
	throw new RuntimeException("semanticEqualitiy not defined");
	//return (a == b);
    }

  /** @see Object.toString **/
    public String toString() {
	return super.toString();
    }
       
} //class
