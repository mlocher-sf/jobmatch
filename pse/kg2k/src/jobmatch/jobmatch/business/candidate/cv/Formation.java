// $Id: Formation.java,v 1.1 2000/05/26 11:54:54 locher Exp $

package jobmatch.business.candidate.cv;

import jobmatch.business.candidate.*;
import jobmatch.data.*;
import java.util.*;

/**
 *  Formation Business Object
 *
 *  @since May 26 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
public class Formation extends SchoolCandidateBDO {
    
    public Formation() throws Exception {
	super();
    }

    public Formation(SchoolCandidateDO dataObject) {
	super(dataObject);
    }

    public static List getAllFormationsFor(Candidate candidate) {
	List result = new ArrayList();
	// make a query
	// for every DO create a new Formation(DO) and add to result
	return result;
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
 * Revision 1.1  2000/05/26 11:54:54  locher
 * Fromation object and query skeleton in Candidate
 *
 */
