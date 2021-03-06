// $Id: Default.java,v 1.2 2000/05/12 08:46:10 locher Exp $

package pack;

import java.io.Serializable;

/**
 *  Class description comes here
 *
 *  @since erstellungsdatum
 *  @author $Author: locher $
 *  @version $Revision: 1.2 $
 **/
public class Default implements Cloneable, Serializable {
    
    public Default() {
	super();
    }
    
    /** @see Object.equals **/
    public boolean equals(Object other) {
	if (other == null) {
	    return false;
	}
	
	try {
	    return semanticEquality(this, (Default) other);
	}
	catch (ClassCastException e) {
	    return false;
	}
    }

    /** @return true if a is considered the same as b **/
    private static boolean semanticEquality(Default a, Default b) {
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
	return this.cloneDefault();
    }
    
    /** creates a clone of this object **/
    private Default cloneDefault() {
	// Do the basic clone
	Default theClone = null;
	try {
	    theClone = (Default) super.clone();
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

// Document history
/*
 * $Log: Default.java,v $
 * Revision 1.2  2000/05/12 08:46:10  locher
 * log history moved to the end of the file
 *
 */
