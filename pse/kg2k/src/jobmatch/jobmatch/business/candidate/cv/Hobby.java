// $Id: Hobby.java,v 1.1 2000/06/10 11:06:19 studer Exp $

package jobmatch.business.candidate.cv;

import jobmatch.business.candidate.*;
import jobmatch.data.*;
import java.util.*;

/**
 *  Hobby Business Object
 *
 *  @since June 10 2000
 *  @author $Author: studer $
 *  @version $Revision: 1.1 $
 **/
public class Hobby extends HobbyBDO implements CVSection {
    
    public Hobby() throws Exception {
	super();
    }

    public Hobby(HobbyDO dataObject) {
	super(dataObject);
    }

    /**
     * Returns a list of Hobbies for the specified candidate
     **/
    public static List getAllHobbiesFor(Candidate candidate) {
	List result = new ArrayList();
	try{
	    HobbyQuery query = new HobbyQuery();
	    query.setQueryCandidate(candidate.getDO());
	    HobbyDO element  = query.getNextDO();
	     while ( element != null) {
		result.add(new Hobby(element));
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
	    return semanticEquality(this, (Hobby) other);
	}
	catch (ClassCastException e) {
	    return false;
	}
    }

    /** @return true if a is considered the same as b **/
    private static boolean semanticEquality(Hobby a, Hobby b) {
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
 * $Log: Hobby.java,v $
 * Revision 1.1  2000/06/10 11:06:19  studer
 * business object for cvsections
 *
 *
 */
