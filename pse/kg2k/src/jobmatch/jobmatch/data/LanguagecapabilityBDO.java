/*-----------------------------------------------------------------------------
 * Enhydra Java Application Server
 * Copyright 1997-2000 Lutris Technologies, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 * 3. All advertising materials mentioning features or use of this software
 *    must display the following acknowledgement:
 *      This product includes Enhydra software developed by Lutris
 *      Technologies, Inc. and its contributors.
 * 4. Neither the name of Lutris Technologies nor the names of its contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY LUTRIS TECHNOLOGIES AND CONTRIBUTORS ``AS IS''
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL LUTRIS TECHNOLOGIES OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *-----------------------------------------------------------------------------
 * /scratch/studer_repositry/dataTest/jobmatch/data/LanguagecapabilityBDO.java
 *-----------------------------------------------------------------------------
 */

package jobmatch.data;

import java.sql.*;
import com.lutris.appserver.server.*;
import com.lutris.appserver.server.sql.*;

import com.lutris.dods.builder.generator.query.*;

/**
 * LanguagecapabilityBDO contains the same set and get methods as
 * the LanguagecapabilityDO class.
 * Business Object (BO) classes typically need these set and get methods.
 * So by deriving a BO from a BDO, or by implementing a BO that 
 * contains a BDO, the developer of the BO is spared some work.
 *
 * @author studer
 * @version $Revision: 1.1 $
 */
public class LanguagecapabilityBDO implements java.io.Serializable {

    /**
     * The LanguagecapabilityDO object upon which the set and get methods operate.
     * This member is protected so that classes derived from LanguagecapabilityBDO
     * can access the underlying Data Object.
     */
    protected LanguagecapabilityDO DO;

    /**
     * Note:  This method is intended for use only by other BDO classes.
     * Presentation Layer classes should (theoretically) always use the
     * Business Layer (BDO) to create/access Data Layer (DO) objects.
     * The overhead for using BDO objects is small
     * (the BDO classes are fairly lightweight.)
     *
     * @return The DO object held by this BDO object.
     */
    public LanguagecapabilityDO getDO() { 
	return DO;
    }

    /**
     * Like the class <CODE>LanguagecapabilityDO</CODE>, 
     * this class acts as a factory.
     * Business Object (BO) classes typically need these set and get methods.
     * So by deriving a BO from a BDO, or by implementing a BO that 
     * contains one or more BDOs, the developer of the BO is spared some work.
     *
     * @exception Exception
     *   If an error occurs.
     */
    public static LanguagecapabilityBDO createVirgin() throws Exception { 
	LanguagecapabilityBDO bdo = new LanguagecapabilityBDO ();
	return bdo;
    }

    /**
     * Constructor for use by classes derived from <CODE>LanguagecapabilityBDO</CODE>.
     * Example usage:<CODE>
     *      class CustomerBO extends CustomerBDO {
     *          // a BDO class is commonly extended in order to implement:
     *          public void beforeAnySet() throws Exception {
     *              if ( CustomerDO should not be altered )
     *                  throw new ApplicationException( "ERROR" );
     *          }
     *          public CustomerBO( CustomerDO DO ) {
     *              super( DO );
     *          }
     *      }
     *      class SomePresentationLayerClass {
     *          public CustomerBO findCustomer( String name ) {
     *              CustomerQuery q = new CustomerQuery();
     *              q.setQueryName( name );
     *              CustomerDO DO = q.getNextDO();
     *              // Here the CustomerBO ctor fires the CustomerBDO ctor.
     *              return new CustomerBO( DO );
     *          }
     *      }
     * </CODE>
     */
    public LanguagecapabilityBDO( LanguagecapabilityDO DO ) { 
	this.DO = DO;
    }

    /**
     * Constructor required by <CODE>LanguagecapabilityBDO.create</CODE> methods.
     */
    public LanguagecapabilityBDO() throws Exception { 
	this.DO = LanguagecapabilityDO.createVirgin();
    }

    /**
     * The createExisting method is used to create a <CODE>LanguagecapabilityBDO</CODE>
     * from a <CODE>LanguagecapabilityDO</CODE> that was returned by 
     * the <CODE>LanguagecapabilityQuery</CODE> class.
     */
    public static LanguagecapabilityBDO createExisting( LanguagecapabilityDO DO ) { 
	LanguagecapabilityBDO bdo = new LanguagecapabilityBDO ( DO );
	return bdo;
    }

    /** 
     * The getBDOarray method performs a database query to
     * return an array of <CODE>LanguagecapabilityBDO</CODE> objects
     * representing all the rows in the <CODE>Languagecapability</CODE> table.
     *
     * This method is a minimal example of using a Query class.
     * To restrict the set of objects returned,  you could
     * invoke <CODE>query.setXxx()</CODE>, where <CODE>Xxx</CODE>
     * is an Attribute of <CODE>LanguagecapabilityDO</CODE> which was 
     * marked as "Can be queried" in the DODS Attribute Editor.
     *
     * @exception DataObjectException
     *   If an object is not found in the database.
     */
    public static LanguagecapabilityBDO[] getBDOarray()
    throws DataObjectException {
        LanguagecapabilityDO[] DOarray = null;
        try {
            LanguagecapabilityQuery query = new LanguagecapabilityQuery();
	    // To restrict the set of objects returned,
	    // you could invoke query.setXxx(), where Xxx
	    // is an Attribute of <CODE>LanguagecapabilityDO</CODE> which was 
	    // marked as "Can be queried" in the DODS Attribute Editor.
            DOarray = query.getDOArray();
        } catch ( NonUniqueQueryException e1 ) {
            // LanguagecapabilityQuery will throw NonUniqueQueryException
            // only if query.requireUniqueInstance() is called
	    // and more than one DO was found.
        } catch ( DataObjectException e2 ) {
            // This could happen if the database server dies, etc.
            throw e2;
        }
        LanguagecapabilityBDO[] BDOarray = new LanguagecapabilityBDO[ DOarray.length ];
        for ( int i = 0; i < DOarray.length; i++ )
            BDOarray[i] = LanguagecapabilityBDO.createExisting( DOarray[i] );
 
        return BDOarray;
    }


    /**
     * The developer of a Business Object that derives from this class
     * can override the methods:<CODE>
     *     beforeAnyGet
     *     beforeAnySet
     *     afterAnySet
     * </CODE> to handle any general assertions or cleanup needed
     * for <CODE>get</CODE> and <CODE>set</CODE> methods.
     */
    protected void beforeAnyGet() {
    }
    // beforeAnySet throws plain Exception to allow overriding implementations
    // to throw any flavor needed.
    protected void beforeAnySet() throws Exception {
	if ( DO.isReadOnly() )
	    throw new DataObjectException( DO + " is read-only." ); 
    }
    protected void afterAnySet() {
    }

    /**
     * The methods <CODE>
     *     getHandle
     *     hasMatchingHandle
     * </CODE> are used by Presentation Objects that need to populate
     * HTML select lists with <CODE>LanguagecapabilityBDO</CODE> objects as options.
     *
     * The <CODE>getHandle()</CODE> method is used
     * to set the value for each option,
     * and the <CODE>hasMatchingHandle()<CODE>
     * methods are used to lookup the Data Object when the selection has
     * been made.
     *
     * This LanguagecapabilityBDO object holds a reference to a LanguagecapabilityDO object.
     * The id of this LanguagecapabilityBDO is the id of its LanguagecapabilityDO.
     * @exception DatabaseManagerException
     *   If a connection to the database cannot be established, etc.
     * @return id of this BDO as a string
     *   If an object id can't be allocated for this object.
     */
    public String getHandle()
    throws DatabaseManagerException {
	return DO.getHandle();
    }

    /**
     * @param handle
     *   <CODE>String</CODE> representation of the id for this BDO
     * @return boolean
     *   True if the string version of the id of this DO matches passed handle
     * @see getHandle
     */
    public boolean hasMatchingHandle( String handle ) {
	return DO.hasMatchingHandle( handle );
    }


    /**
     * for debugging
     */
    public String toString() {
	if ( null == DO )
		return "NULL";
	return DO.toString();
    }

   /**
    * Get Description of the LanguagecapabilityDO
    *
    * @return Description of the LanguagecapabilityDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public String getDescription () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getDescription ();
   }

   
   /**
    * Set Description of the LanguagecapabilityDO
    *
    * @param Description of the LanguagecapabilityDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setDescription ( String Description ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setDescription ( Description );
      afterAnySet();	// business actions/assertions after data assignment
   }


   

   /**
    * Get Ordinal of the LanguagecapabilityDO
    *
    * @return Ordinal of the LanguagecapabilityDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public int getOrdinal () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getOrdinal ();
   }

   
   /**
    * Set Ordinal of the LanguagecapabilityDO
    *
    * @param Ordinal of the LanguagecapabilityDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setOrdinal ( int Ordinal ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setOrdinal ( Ordinal );
      afterAnySet();	// business actions/assertions after data assignment
   }


   
    /**
     * Get array of LanguageCandidateDO objects that refer to the DO held by this BDO.
     *
     * @return array of LanguageCandidateDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.LanguageCandidateDO[] getLanguageCandidateDOArray_Written () 
    throws DataObjectException, QueryException {
	jobmatch.data.LanguageCandidateDO[] ret = null;
	try {
	    jobmatch.data.LanguageCandidateQuery q = new jobmatch.data.LanguageCandidateQuery();
	    q.setQueryWritten( DO );
	    ret = q.getDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.LanguageCandidateDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single LanguageCandidateDO object
     * that refers to the DO held by this BDO.
     *
     * @return LanguageCandidateDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one LanguageCandidateDO object was found.
     */
    public jobmatch.data.LanguageCandidateDO getLanguageCandidateDO_Written () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.LanguageCandidateQuery q = new jobmatch.data.LanguageCandidateQuery();
	q.setQueryWritten( DO );
	q.requireUniqueInstance();
	return q.getNextDO();
    }

    /**
     * Get array of LanguageCandidateBDO objects holding LanguageCandidateDO objects
     * that refer to the DO held by this BDO.
     *
     * @return array of LanguageCandidateBDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.LanguageCandidateBDO[] getLanguageCandidateBDOArray_Written () 
    throws DataObjectException, QueryException {
	jobmatch.data.LanguageCandidateBDO[] ret = null;
	try {
	    jobmatch.data.LanguageCandidateQuery q = new jobmatch.data.LanguageCandidateQuery();
	    q.setQueryWritten( DO );
	    ret = q.getBDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.LanguageCandidateBDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single LanguageCandidateBDO object holding a LanguageCandidateDO object
     * that refers to the DO held by this BDO.
     *
     * @return LanguageCandidateBDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one LanguageCandidateBDO object was found.
     */
    public jobmatch.data.LanguageCandidateBDO getLanguageCandidateBDO_Written () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.LanguageCandidateQuery q = new jobmatch.data.LanguageCandidateQuery();
	q.setQueryWritten( DO );
	q.requireUniqueInstance();
	return q.getNextBDO();
    }

 
    /**
     * Add (set & commit) a LanguageCandidateBDO object whose LanguageCandidateDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo LanguageCandidateBDO to be set to point to this BDO and committed.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addLanguageCandidateBDO_Written( jobmatch.data.LanguageCandidateBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        addLanguageCandidateBDO_Written( rbdo, null );
    }
 
 
    /**
     * Add (set & commit) a LanguageCandidateBDO object whose LanguageCandidateDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo LanguageCandidateBDO to be set to point to this BDO and committed.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addLanguageCandidateBDO_Written( jobmatch.data.LanguageCandidateBDO rbdo, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        rbdo.setWritten( this.DO );
        rbdo.commit( tran );
    }

 
    /**
     * Remove (delete) a LanguageCandidateBDO object whose LanguageCandidateDO
     * refers to the DO held by this BDO.
     *
     * @param r LanguageCandidateBDO to be deleted.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeLanguageCandidateBDO_Written( jobmatch.data.LanguageCandidateBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        removeLanguageCandidateBDO_Written( rbdo, null );
    }
 
 
    /**
     * Remove (delete) a LanguageCandidateBDO object whose LanguageCandidateDO
     * refers to the DO held by this BDO.
     *
     * @param r LanguageCandidateBDO to be deleted.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeLanguageCandidateBDO_Written( jobmatch.data.LanguageCandidateBDO rbdo, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
	LanguagecapabilityDO rdo = rbdo.getWritten();
	String rdoHandle = rdo.getHandle();
	String mydoHandle = DO.getHandle();
	if ( null == rdoHandle || null == mydoHandle || 
	     ( ! rdoHandle.equals( mydoHandle ) ) ) {
	    throw new DataObjectException( "Object " + rdo +
		" does not refer to object " + DO +
		", cannot be removed this way." );
	}
        rbdo.delete( tran );
    }
 

    /**
     * Get array of LanguageCandidateDO objects that refer to the DO held by this BDO.
     *
     * @return array of LanguageCandidateDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.LanguageCandidateDO[] getLanguageCandidateDOArray_Spoken () 
    throws DataObjectException, QueryException {
	jobmatch.data.LanguageCandidateDO[] ret = null;
	try {
	    jobmatch.data.LanguageCandidateQuery q = new jobmatch.data.LanguageCandidateQuery();
	    q.setQuerySpoken( DO );
	    ret = q.getDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.LanguageCandidateDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single LanguageCandidateDO object
     * that refers to the DO held by this BDO.
     *
     * @return LanguageCandidateDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one LanguageCandidateDO object was found.
     */
    public jobmatch.data.LanguageCandidateDO getLanguageCandidateDO_Spoken () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.LanguageCandidateQuery q = new jobmatch.data.LanguageCandidateQuery();
	q.setQuerySpoken( DO );
	q.requireUniqueInstance();
	return q.getNextDO();
    }

    /**
     * Get array of LanguageCandidateBDO objects holding LanguageCandidateDO objects
     * that refer to the DO held by this BDO.
     *
     * @return array of LanguageCandidateBDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.LanguageCandidateBDO[] getLanguageCandidateBDOArray_Spoken () 
    throws DataObjectException, QueryException {
	jobmatch.data.LanguageCandidateBDO[] ret = null;
	try {
	    jobmatch.data.LanguageCandidateQuery q = new jobmatch.data.LanguageCandidateQuery();
	    q.setQuerySpoken( DO );
	    ret = q.getBDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.LanguageCandidateBDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single LanguageCandidateBDO object holding a LanguageCandidateDO object
     * that refers to the DO held by this BDO.
     *
     * @return LanguageCandidateBDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one LanguageCandidateBDO object was found.
     */
    public jobmatch.data.LanguageCandidateBDO getLanguageCandidateBDO_Spoken () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.LanguageCandidateQuery q = new jobmatch.data.LanguageCandidateQuery();
	q.setQuerySpoken( DO );
	q.requireUniqueInstance();
	return q.getNextBDO();
    }

 
    /**
     * Add (set & commit) a LanguageCandidateBDO object whose LanguageCandidateDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo LanguageCandidateBDO to be set to point to this BDO and committed.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addLanguageCandidateBDO_Spoken( jobmatch.data.LanguageCandidateBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        addLanguageCandidateBDO_Spoken( rbdo, null );
    }
 
 
    /**
     * Add (set & commit) a LanguageCandidateBDO object whose LanguageCandidateDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo LanguageCandidateBDO to be set to point to this BDO and committed.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addLanguageCandidateBDO_Spoken( jobmatch.data.LanguageCandidateBDO rbdo, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        rbdo.setSpoken( this.DO );
        rbdo.commit( tran );
    }

 
    /**
     * Remove (delete) a LanguageCandidateBDO object whose LanguageCandidateDO
     * refers to the DO held by this BDO.
     *
     * @param r LanguageCandidateBDO to be deleted.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeLanguageCandidateBDO_Spoken( jobmatch.data.LanguageCandidateBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        removeLanguageCandidateBDO_Spoken( rbdo, null );
    }
 
 
    /**
     * Remove (delete) a LanguageCandidateBDO object whose LanguageCandidateDO
     * refers to the DO held by this BDO.
     *
     * @param r LanguageCandidateBDO to be deleted.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeLanguageCandidateBDO_Spoken( jobmatch.data.LanguageCandidateBDO rbdo, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
	LanguagecapabilityDO rdo = rbdo.getSpoken();
	String rdoHandle = rdo.getHandle();
	String mydoHandle = DO.getHandle();
	if ( null == rdoHandle || null == mydoHandle || 
	     ( ! rdoHandle.equals( mydoHandle ) ) ) {
	    throw new DataObjectException( "Object " + rdo +
		" does not refer to object " + DO +
		", cannot be removed this way." );
	}
        rbdo.delete( tran );
    }
 

    /**
     * Get array of LanguageProfileDO objects that refer to the DO held by this BDO.
     *
     * @return array of LanguageProfileDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.LanguageProfileDO[] getLanguageProfileDOArray_MinWritten () 
    throws DataObjectException, QueryException {
	jobmatch.data.LanguageProfileDO[] ret = null;
	try {
	    jobmatch.data.LanguageProfileQuery q = new jobmatch.data.LanguageProfileQuery();
	    q.setQueryMinWritten( DO );
	    ret = q.getDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.LanguageProfileDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single LanguageProfileDO object
     * that refers to the DO held by this BDO.
     *
     * @return LanguageProfileDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one LanguageProfileDO object was found.
     */
    public jobmatch.data.LanguageProfileDO getLanguageProfileDO_MinWritten () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.LanguageProfileQuery q = new jobmatch.data.LanguageProfileQuery();
	q.setQueryMinWritten( DO );
	q.requireUniqueInstance();
	return q.getNextDO();
    }

    /**
     * Get array of LanguageProfileBDO objects holding LanguageProfileDO objects
     * that refer to the DO held by this BDO.
     *
     * @return array of LanguageProfileBDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.LanguageProfileBDO[] getLanguageProfileBDOArray_MinWritten () 
    throws DataObjectException, QueryException {
	jobmatch.data.LanguageProfileBDO[] ret = null;
	try {
	    jobmatch.data.LanguageProfileQuery q = new jobmatch.data.LanguageProfileQuery();
	    q.setQueryMinWritten( DO );
	    ret = q.getBDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.LanguageProfileBDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single LanguageProfileBDO object holding a LanguageProfileDO object
     * that refers to the DO held by this BDO.
     *
     * @return LanguageProfileBDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one LanguageProfileBDO object was found.
     */
    public jobmatch.data.LanguageProfileBDO getLanguageProfileBDO_MinWritten () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.LanguageProfileQuery q = new jobmatch.data.LanguageProfileQuery();
	q.setQueryMinWritten( DO );
	q.requireUniqueInstance();
	return q.getNextBDO();
    }

 
    /**
     * Add (set & commit) a LanguageProfileBDO object whose LanguageProfileDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo LanguageProfileBDO to be set to point to this BDO and committed.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addLanguageProfileBDO_MinWritten( jobmatch.data.LanguageProfileBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        addLanguageProfileBDO_MinWritten( rbdo, null );
    }
 
 
    /**
     * Add (set & commit) a LanguageProfileBDO object whose LanguageProfileDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo LanguageProfileBDO to be set to point to this BDO and committed.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addLanguageProfileBDO_MinWritten( jobmatch.data.LanguageProfileBDO rbdo, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        rbdo.setMinWritten( this.DO );
        rbdo.commit( tran );
    }

 
    /**
     * Remove (delete) a LanguageProfileBDO object whose LanguageProfileDO
     * refers to the DO held by this BDO.
     *
     * @param r LanguageProfileBDO to be deleted.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeLanguageProfileBDO_MinWritten( jobmatch.data.LanguageProfileBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        removeLanguageProfileBDO_MinWritten( rbdo, null );
    }
 
 
    /**
     * Remove (delete) a LanguageProfileBDO object whose LanguageProfileDO
     * refers to the DO held by this BDO.
     *
     * @param r LanguageProfileBDO to be deleted.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeLanguageProfileBDO_MinWritten( jobmatch.data.LanguageProfileBDO rbdo, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
	LanguagecapabilityDO rdo = rbdo.getMinWritten();
	String rdoHandle = rdo.getHandle();
	String mydoHandle = DO.getHandle();
	if ( null == rdoHandle || null == mydoHandle || 
	     ( ! rdoHandle.equals( mydoHandle ) ) ) {
	    throw new DataObjectException( "Object " + rdo +
		" does not refer to object " + DO +
		", cannot be removed this way." );
	}
        rbdo.delete( tran );
    }
 

    /**
     * Get array of LanguageProfileDO objects that refer to the DO held by this BDO.
     *
     * @return array of LanguageProfileDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.LanguageProfileDO[] getLanguageProfileDOArray_MinSpoken () 
    throws DataObjectException, QueryException {
	jobmatch.data.LanguageProfileDO[] ret = null;
	try {
	    jobmatch.data.LanguageProfileQuery q = new jobmatch.data.LanguageProfileQuery();
	    q.setQueryMinSpoken( DO );
	    ret = q.getDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.LanguageProfileDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single LanguageProfileDO object
     * that refers to the DO held by this BDO.
     *
     * @return LanguageProfileDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one LanguageProfileDO object was found.
     */
    public jobmatch.data.LanguageProfileDO getLanguageProfileDO_MinSpoken () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.LanguageProfileQuery q = new jobmatch.data.LanguageProfileQuery();
	q.setQueryMinSpoken( DO );
	q.requireUniqueInstance();
	return q.getNextDO();
    }

    /**
     * Get array of LanguageProfileBDO objects holding LanguageProfileDO objects
     * that refer to the DO held by this BDO.
     *
     * @return array of LanguageProfileBDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.LanguageProfileBDO[] getLanguageProfileBDOArray_MinSpoken () 
    throws DataObjectException, QueryException {
	jobmatch.data.LanguageProfileBDO[] ret = null;
	try {
	    jobmatch.data.LanguageProfileQuery q = new jobmatch.data.LanguageProfileQuery();
	    q.setQueryMinSpoken( DO );
	    ret = q.getBDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.LanguageProfileBDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single LanguageProfileBDO object holding a LanguageProfileDO object
     * that refers to the DO held by this BDO.
     *
     * @return LanguageProfileBDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one LanguageProfileBDO object was found.
     */
    public jobmatch.data.LanguageProfileBDO getLanguageProfileBDO_MinSpoken () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.LanguageProfileQuery q = new jobmatch.data.LanguageProfileQuery();
	q.setQueryMinSpoken( DO );
	q.requireUniqueInstance();
	return q.getNextBDO();
    }

 
    /**
     * Add (set & commit) a LanguageProfileBDO object whose LanguageProfileDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo LanguageProfileBDO to be set to point to this BDO and committed.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addLanguageProfileBDO_MinSpoken( jobmatch.data.LanguageProfileBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        addLanguageProfileBDO_MinSpoken( rbdo, null );
    }
 
 
    /**
     * Add (set & commit) a LanguageProfileBDO object whose LanguageProfileDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo LanguageProfileBDO to be set to point to this BDO and committed.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addLanguageProfileBDO_MinSpoken( jobmatch.data.LanguageProfileBDO rbdo, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        rbdo.setMinSpoken( this.DO );
        rbdo.commit( tran );
    }

 
    /**
     * Remove (delete) a LanguageProfileBDO object whose LanguageProfileDO
     * refers to the DO held by this BDO.
     *
     * @param r LanguageProfileBDO to be deleted.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeLanguageProfileBDO_MinSpoken( jobmatch.data.LanguageProfileBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        removeLanguageProfileBDO_MinSpoken( rbdo, null );
    }
 
 
    /**
     * Remove (delete) a LanguageProfileBDO object whose LanguageProfileDO
     * refers to the DO held by this BDO.
     *
     * @param r LanguageProfileBDO to be deleted.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeLanguageProfileBDO_MinSpoken( jobmatch.data.LanguageProfileBDO rbdo, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
	LanguagecapabilityDO rdo = rbdo.getMinSpoken();
	String rdoHandle = rdo.getHandle();
	String mydoHandle = DO.getHandle();
	if ( null == rdoHandle || null == mydoHandle || 
	     ( ! rdoHandle.equals( mydoHandle ) ) ) {
	    throw new DataObjectException( "Object " + rdo +
		" does not refer to object " + DO +
		", cannot be removed this way." );
	}
        rbdo.delete( tran );
    }
 




  /**
   * Inserts/Updates the DO into its table.
   *
   * @exception com.lutris.appserver.server.sql.DatabaseManagerException if a Transaction can not be created.
   * @exception RefAssertionException thrown by okTo method.
   * @exception java.sql.SQLException if any SQL errors occur.
   */
  public void commit() 
  throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
    modifyDO( null, false );
  }

  /**
   * Inserts/Updates the DO into its table.
   * The transaction is likely provided by the commit() method of another BDO
   * whose DO references this DO.
   * 
   * @param dbt The transaction object to use for this operation.
   * @exception com.lutris.appserver.server.sql.DatabaseManagerException if a Transaction can not be created.
   * @exception RefAssertionException thrown by okTo method.
   * @exception java.sql.SQLException if any SQL errors occur.
   */
  public void commit(DBTransaction dbt)
  throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
    modifyDO( dbt, false );
  }

  /**
   * Deletes the DO from its table.
   *
   * @exception com.lutris.appserver.server.sql.DatabaseManagerException if a Transaction can not be created.
   * @exception RefAssertionException thrown by okTo method.
   * @exception java.sql.SQLException if any SQL errors occur.
   */
  public void delete() 
  throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
    modifyDO( null, true );
  }

  /**
   * Deletes the DO from its table.
   * The transaction is likely provided by the delete() method of another BDO
   * whose DO references this DO.
   *
   * @param dbt The transaction object to use for this operation.
   * @exception com.lutris.appserver.server.sql.DatabaseManagerException if a Transaction can not be created.
   * @exception RefAssertionException thrown by okTo method.
   * @exception java.sql.SQLException if any SQL errors occur.
   */
  public void delete(DBTransaction dbt)
  throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
    modifyDO( dbt, true );
  }

  

  /**
   * Modifies the DO within its table.
   * Performs recursive commit/delete on referenced DOs;
   * all operations occur within a single transaction
   * to allow rollback in the event of error.
   * Only the creator of the transaction releases it.
   *
   * @param dbt The transaction object to use for this operation.
   * @param delete True if doing a delete, otherwise doing insert/update.
   * @exception com.lutris.appserver.server.sql.DatabaseManagerException if a Transaction can not be created.
   * @exception RefAssertionException thrown by okTo method.
   * @exception java.sql.SQLException if any SQL errors occur.
   */
  protected void modifyDO( DBTransaction dbt, boolean delete )
  throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
    boolean ownTransaction = false;
    try {
      if ( null == dbt ) {
	DatabaseManager dbm = Enhydra.getDatabaseManager();
        dbt = dbm.createTransaction();      // create a transaction
	ownTransaction = true;
      }
      if ( null == dbt )
	throw new DatabaseManagerException(
		"DatabaseManager.createTransaction returned null." );
      if ( delete ) {
	  // Code to perform cascading deletes is generated here
	  // if cascading deletes are not supported by the database.      
	  	
	{
	    // perform cascading delete on referring table
	    jobmatch.data.LanguageCandidateBDO[] a = getLanguageCandidateBDOArray_Written();
	    for ( int i = 0; i < a.length; i++ ) {
		a[ i ].delete( dbt );
	    }
	}
	
	
	{
	    // perform cascading delete on referring table
	    jobmatch.data.LanguageCandidateBDO[] a = getLanguageCandidateBDOArray_Spoken();
	    for ( int i = 0; i < a.length; i++ ) {
		a[ i ].delete( dbt );
	    }
	}
	
	
	{
	    // perform cascading delete on referring table
	    jobmatch.data.LanguageProfileBDO[] a = getLanguageProfileBDOArray_MinWritten();
	    for ( int i = 0; i < a.length; i++ ) {
		a[ i ].delete( dbt );
	    }
	}
	
	
	{
	    // perform cascading delete on referring table
	    jobmatch.data.LanguageProfileBDO[] a = getLanguageProfileBDOArray_MinSpoken();
	    for ( int i = 0; i < a.length; i++ ) {
		a[ i ].delete( dbt );
	    }
	}
	

          // The following line keeps the compiler happy
          // when the CASCADING_DELETES tag is empty.
          if ( false )
              throw new QueryException("XXX");
      } else {
	  // commit referenced DOs.
	  
      }
      if ( false ) {
	  // This throw is here to keep the compiler happy
	  // in the case of a DO that does not refer to other DOs.
	  // In that case, the above delete/commit code blocks will be empty
	  // and throw nothing.
	  throw new DataObjectException( "foo" );	  
      }
      if ( delete ) {
          dbt.delete( DO );
      } else {
	  if ( DO.isLoaded() )
	      dbt.insert( DO );   // dbt.insert() handles insertions and updates
      }
      if (ownTransaction) {
	  dbt.commit(); // commit the transaction
      }
    } catch (DataObjectException doe) {
      throw doe;
    } catch (SQLException sqle) {
      StringBuffer message = new StringBuffer("Failed to insert/update DO: ");
      message.append(sqle.getMessage());

      // rollback, if necessary
      if (ownTransaction) {
        try {
          dbt.rollback();
        } catch (SQLException sqle2) {
          message.insert(0,"\n");
          message.insert(0,sqle2.getMessage());
          message.insert(0,"Rollback failed: ");
        }
      }
      throw new SQLException(message.toString());
    } finally {
      // release the transaction, if any
      if (ownTransaction) {
        dbt.release();
      }
    }
  }

}
