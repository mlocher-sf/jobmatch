// $Id: Profile.java,v 1.1 2000/05/30 15:52:21 locher Exp $

package jobmatch.business.company.profile;

import jobmatch.data.*;
import jobmatch.business.company.*;
import jobmatch.business.company.profile.tree.*;

/**
 *  Represents a Profile
 *
 *  @since May 30 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
public class Profile extends ProfileBDO {
    
    private Profile() throws Exception {
	super();
    }
    
    public Profile(ProfileDO dataObject) {
	super(dataObject);
    }

    /** @see Object.equals **/
    public boolean equals(Object other) {
	if (other == null) {
	    return false;
	}
	
	try {
	    return semanticEquality(this, (Profile) other);
	}
	catch (ClassCastException e) {
	    return false;
	}
    }

    /** @return true if a is considered the same as b **/
    private boolean semanticEquality(Profile a, Profile b) {
	//XXX define the equality and remove the exception !!!
	throw new RuntimeException("semanticEqualitiy not defined");
	//return (a == b);
    }

    /** @see Object.toString **/
    public String toString() {
	return super.toString();
    }
       
} //class

// Document history
/*
 * $Log: Profile.java,v $
 * Revision 1.1  2000/05/30 15:52:21  locher
 * added Company and Profile BOs
 *
 * Revision 1.2  2000/05/12 08:46:10  locher
 * log history moved to the end of the file
 *
 */

