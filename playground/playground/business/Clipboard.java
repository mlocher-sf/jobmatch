// $Id: Clipboard.java,v 1.2 2000/04/11 15:53:51 locher Exp $

/*
 * $Log: Clipboard.java,v $
 * Revision 1.2  2000/04/11 15:53:51  locher
 * upload, BO interaction
 *
 * Revision 1.1  2000/04/10 12:15:47  locher
 * added business objects
 *
 * Revision 1.1.1.1  2000/04/01 14:04:42  locher
 *
 *
 */

package playground.business;

import java.io.Serializable;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;


/**
 *  Class description comes here
 *
 *  @since erstellungsdatum
 *  @author $Author: locher $
 *  @version $Revision: 1.2 $
 **/
public class Clipboard implements Cloneable, Serializable {

    private Collection _studenten;
    
    public Clipboard() {
	super();
	_studenten = new ArrayList();
    }

    /**
     *  @pre studi != null
     **/
    public void add(StudentBO studi) {
	_studenten.add(studi);
    }

    public Iterator getStudents() {
	return _studenten.iterator();
    }
    
    public static Clipboard getDemoClipboard() {
	Clipboard clipb = new Clipboard();
	clipb.add(new StudentBO("Hans"));
	return clipb;
    }

    /** @see Object.equals **/
    public boolean equals(Object other) {
	if (other == null) {
	    return false;
	}
	
	try {
	    return semanticEquality(this, (Clipboard) other);
	}
	catch (ClassCastException e) {
	    return false;
	}
    }

    /** @return true if a is considered the same as b **/
    private static boolean semanticEquality(Clipboard a, Clipboard b) {
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
	return this.cloneClipboard();
    }
    
    /** creates a clone of this object **/
    private Clipboard cloneClipboard() {
	// Do the basic clone
	Clipboard theClone = null;
	try {
	    theClone = (Clipboard) super.clone();
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
