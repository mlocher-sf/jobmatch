// $Id: Student.java,v 1.1 2000/04/10 12:15:48 locher Exp $

/*
 * $Log: Student.java,v $
 * Revision 1.1  2000/04/10 12:15:48  locher
 * added business objects
 *
 * Revision 1.1.1.1  2000/04/01 14:04:42  locher
 *
 *
 */

package playground.business;

import java.io.Serializable;

/**
 *  Class description comes here
 *
 *  @since erstellungsdatum
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
public class Student implements Cloneable, Serializable {

        
    private String _name;
    

    
    public Student() {
	super();
    }

    /**
       * Get the value of name.
       * @return Value of name.
       */
    public String getName() {return _name;}
    
    /**
       * Set the value of name.
       * @param v  Value to assign to name.
       */
    public void setName(String  v) {this._name = v;}
    
    
    /** @see Object.equals **/
    public boolean equals(Object other) {
	if (other == null) {
	    return false;
	}
	
	try {
	    return semanticEquality(this, (Student) other);
	}
	catch (ClassCastException e) {
	    return false;
	}
    }

    /** @return true if a is considered the same as b **/
    private static boolean semanticEquality(Student a, Student b) {
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
	return this.cloneStudent();
    }
    
    /** creates a clone of this object **/
    private Student cloneStudent() {
	// Do the basic clone
	Student theClone = null;
	try {
	    theClone = (Student) super.clone();
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
