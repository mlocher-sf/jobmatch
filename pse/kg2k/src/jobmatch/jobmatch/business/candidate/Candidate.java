// $Id: Candidate.java,v 1.1 2000/05/04 14:21:24 locher Exp $

/*
 * $Log: Candidate.java,v $
 * Revision 1.1  2000/05/04 14:21:24  locher
 * makefiles
 *
 * Revision 1.1.1.1  2000/04/01 14:04:42  locher
 *
 */

package jobmatch.business.candidate;

import java.io.Serializable;

/**
 *  Candidate Business Object
 *
 *  @since May 4 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
public class Candidate implements Cloneable, Serializable {

    
    public Candidate() {
	super();
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

    /** @see Object.clone **/
    public Object clone() {
	return this.cloneCandidate();
    }
    
    /** creates a clone of this object **/
    private Candidate cloneCandidate() {
	// Do the basic clone
	Candidate theClone = null;
	try {
	    theClone = (Candidate) super.clone();
	}
	catch (CloneNotSupportedException e) {
	    // Should never happen
	    throw new InternalError(e.toString());
	}

	// Clone mutable members
	// XXX
	return theClone;
    }
       
} //class
