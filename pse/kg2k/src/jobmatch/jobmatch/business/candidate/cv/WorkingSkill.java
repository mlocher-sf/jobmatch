package jobmatch.business.candidate.cv;

import jobmatch.business.entity.*;
import jobmatch.business.candidate.*;
import jobmatch.data.*;
import java.util.*;

/**
 *  WorkingSkill Business Object
 *
 *  @since May 26 2000
 *  @author $Author: dniederm $
 *  @version $Revision: 1.1 $
 **/

public class WorkingSkill extends EmployerCandidateBDO implements CVSection {
    
    public WorkingSkill() throws Exception {
	super();
    }

    public WorkingSkill(EmployerCandidateDO dataObject) {
	super(dataObject);
    }
    /**
     * Returns a list of Working skills for the specified candidate
     **/
    public static List getAllWorkingSkillsFor(Candidate candidate) {
	List result = new ArrayList();
	try{
	    EmployerCandidateQuery query = new EmployerCandidateQuery();
	    query.setQueryCandidate(candidate.getDO());
	    EmployerCandidateDO element  = query.getNextDO();
	     while ( element != null) {
		result.add(new WorkingSkill(element));
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
     * Return the Employer business object
     **/

    public Employer getEmployerBO() {
	try {
	    return new Employer(this.getEmployer());
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
	    return semanticEquality(this, (WorkingSkill) other);
	}
	catch (ClassCastException e) {
	    return false;
	}
    }

  /** @return true if a is considered the same as b **/
    private static boolean semanticEquality(WorkingSkill a, WorkingSkill b) {
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
 * $Log: WorkingSkill.java,v $
 * Revision 1.1  2000/06/10 11:02:44  dniederm
 * *** empty log message ***
 *
 */
