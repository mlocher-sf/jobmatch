package jobmatch.business.candidate.cv;

import jobmatch.business.entity.*;
import jobmatch.business.candidate.*;
import jobmatch.data.*;
import java.util.*;

/**
 *  LanguageSkill Business Object
 *
 *  @since May 26 2000
 *  @author $Author: pse4 $
 *  @version $Revision: 1.1 $
 **/

public class LanguageSkill extends LanguageCandidateBDO implements CVSection {
    
    public LanguageSkill() throws Exception {
	super();
    }

    public LanguageSkill(LanguageCandidateDO dataObject) {
	super(dataObject);
    }
    /**
     * Returns a list of language skills for the specified candidate
     **/
    public static List getAllLanguageSkillsFor(Candidate candidate) {
	List result = new ArrayList();
	try{
	    LanguageCandidateQuery query = new LanguageCandidateQuery();
	    query.setQueryCandidate(candidate.getDO());
	    LanguageCandidateDO element  = query.getNextDO();
	     while ( element != null) {
		result.add(new LanguageSkill(element));
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
     * Return the language business object
     **/

    public Language getLanguageBO() {
	try {
	    return new Language(this.getLanguage());
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
	    return semanticEquality(this, (LanguageSkill) other);
	}
	catch (ClassCastException e) {
	    return false;
	}
    }

  /** @return true if a is considered the same as b **/
    private static boolean semanticEquality(LanguageSkill a, LanguageSkill b) {
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
 * $Log: LanguageSkill.java,v $
 * Revision 1.1  2000/06/20 13:32:54  pse4
 * Initial revision
 *
 * Revision 1.1  2000/06/10 11:02:42  dniederm
 * *** empty log message ***
 *
 */
