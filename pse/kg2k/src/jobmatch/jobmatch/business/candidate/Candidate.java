// $Id: Candidate.java,v 1.3 2000/05/23 14:10:53 locher Exp $

package jobmatch.business.candidate;

import jobmatch.data.*;
import java.io.Serializable;

/**
 *  Candidate Business Object
 *
 *  @since May 4 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.3 $
 **/
public class Candidate extends CandidateBDO {
    
    public Candidate() throws Exception {
	super();
    }

    public Candidate(CandidateDO dataObject) {
	super(dataObject);
    }
    
    /** @see Object.equals **/
    public boolean equals(Object other) {
	if (other == null) {
	    return false;
	}
	
	try {
	    return semanticEquality(this, (Candidate) other);
	}
	catch (ClassCastException e) {
	    return false;
	}
    }

    /** @return true if a is considered the same as b **/
    private static boolean semanticEquality(Candidate a, Candidate b) {
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
 * $Log: Candidate.java,v $
 * Revision 1.3  2000/05/23 14:10:53  locher
 * authentification mechanisms
 *
 * Revision 1.2  2000/05/19 10:59:29  locher
 * matcher and mailer service including test skeletons
 *
 * Revision 1.1  2000/05/04 14:21:24  locher
 * makefiles
 *
 * Revision 1.1.1.1  2000/04/01 14:04:42  locher
 *
 */
