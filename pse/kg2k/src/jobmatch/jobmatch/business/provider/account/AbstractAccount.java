// $Id: AbstractAccount.java,v 1.1 2000/05/04 14:50:28 locher Exp $

/*
 * $Log: AbstractAccount.java,v $
 * Revision 1.1  2000/05/04 14:50:28  locher
 * created little hierarchy
 *
 * Revision 1.1.1.1  2000/04/01 14:04:42  locher
 *
 */

package jobmatch.business.provider.account;

import java.io.Serializable;

/**
 *  Common base for accounts
 *
 *  @since May 4 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
abstract class AbstractAccount implements Account, Cloneable, Serializable {

    private String username;
    private String passphrase;
    private String eMail;
    
    public AbstractAccount() {
    }
    

    public String getUsername() {
	return this.username;
    }

    public String getPassphrase() {
	return this.passphrase;
    }

    public String getEMail() {
	return this.eMail;
    }

    /** @see Object.equals **/
    public boolean equals(Object other) {
	if (other == null) {
	    return false;
	}
	
	try {
	    return semanticEquality(this, (AbstractAccount) other);
	}
	catch (ClassCastException e) {
	    return false;
	}
    }

    /** @return true if a is considered the same as b **/
    private static boolean semanticEquality(AbstractAccount a, AbstractAccount b) {
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
	return this.cloneAbstractAccount();
    }
    
    /** creates a clone of this object **/
    private AbstractAccount cloneAbstractAccount() {
	// Do the basic clone
	AbstractAccount theClone = null;
	try {
	    theClone = (AbstractAccount) super.clone();
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
