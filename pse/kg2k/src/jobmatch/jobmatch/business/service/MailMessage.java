// $Id: MailMessage.java,v 1.1 2000/05/19 10:59:32 locher Exp $

package jobmatch.business.service;

import java.io.Serializable;

/**
 *  Represents a Mail Message
 *
 *  @since May 19 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
public class MailMessage implements Cloneable, Serializable {
    
    public MailMessage() {
	super();
    }
    
    /** @see Object.equals **/
    public boolean equals(Object other) {
	if (other == null) {
	    return false;
	}
	
	try {
	    return semanticEquality(this, (MailMessage) other);
	}
	catch (ClassCastException e) {
	    return false;
	}
    }

    /** @return true if a is considered the same as b **/
    private static boolean semanticEquality(MailMessage a, MailMessage b) {
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
	return this.cloneMailMessage();
    }
    
    /** creates a clone of this object **/
    private MailMessage cloneMailMessage() {
	// Do the basic clone
	MailMessage theClone = null;
	try {
	    theClone = (MailMessage) super.clone();
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
 * $Log: MailMessage.java,v $
 * Revision 1.1  2000/05/19 10:59:32  locher
 * matcher and mailer service including test skeletons
 *
 * Revision 1.2  2000/05/12 08:46:10  locher
 * log history moved to the end of the file
 *
 */
