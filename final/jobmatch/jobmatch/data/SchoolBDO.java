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
 * /scratch/studer_repositry/dataTest/jobmatch/data/SchoolBDO.java
 *-----------------------------------------------------------------------------
 */

package jobmatch.data;

import java.sql.*;
import com.lutris.appserver.server.*;
import com.lutris.appserver.server.sql.*;

import com.lutris.dods.builder.generator.query.*;

/**
 * SchoolBDO contains the same set and get methods as
 * the SchoolDO class.
 * Business Object (BO) classes typically need these set and get methods.
 * So by deriving a BO from a BDO, or by implementing a BO that 
 * contains a BDO, the developer of the BO is spared some work.
 *
 * @author studer
 * @version $Revision: 1.1 $
 */
public class SchoolBDO implements java.io.Serializable {

    /**
     * The SchoolDO object upon which the set and get methods operate.
     * This member is protected so that classes derived from SchoolBDO
     * can access the underlying Data Object.
     */
    protected SchoolDO DO;

    /**
     * Note:  This method is intended for use only by other BDO classes.
     * Presentation Layer classes should (theoretically) always use the
     * Business Layer (BDO) to create/access Data Layer (DO) objects.
     * The overhead for using BDO objects is small
     * (the BDO classes are fairly lightweight.)
     *
     * @return The DO object held by this BDO object.
     */
    public SchoolDO getDO() { 
	return DO;
    }

    /**
     * Like the class <CODE>SchoolDO</CODE>, 
     * this class acts as a factory.
     * Business Object (BO) classes typically need these set and get methods.
     * So by deriving a BO from a BDO, or by implementing a BO that 
     * contains one or more BDOs, the developer of the BO is spared some work.
     *
     * @exception Exception
     *   If an error occurs.
     */
    public static SchoolBDO createVirgin() throws Exception { 
	SchoolBDO bdo = new SchoolBDO ();
	return bdo;
    }

    /**
     * Constructor for use by classes derived from <CODE>SchoolBDO</CODE>.
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
    public SchoolBDO( SchoolDO DO ) { 
	this.DO = DO;
    }

    /**
     * Constructor required by <CODE>SchoolBDO.create</CODE> methods.
     */
    public SchoolBDO() throws Exception { 
	this.DO = SchoolDO.createVirgin();
    }

    /**
     * The createExisting method is used to create a <CODE>SchoolBDO</CODE>
     * from a <CODE>SchoolDO</CODE> that was returned by 
     * the <CODE>SchoolQuery</CODE> class.
     */
    public static SchoolBDO createExisting( SchoolDO DO ) { 
	SchoolBDO bdo = new SchoolBDO ( DO );
	return bdo;
    }

    /** 
     * The getBDOarray method performs a database query to
     * return an array of <CODE>SchoolBDO</CODE> objects
     * representing all the rows in the <CODE>School</CODE> table.
     *
     * This method is a minimal example of using a Query class.
     * To restrict the set of objects returned,  you could
     * invoke <CODE>query.setXxx()</CODE>, where <CODE>Xxx</CODE>
     * is an Attribute of <CODE>SchoolDO</CODE> which was 
     * marked as "Can be queried" in the DODS Attribute Editor.
     *
     * @exception DataObjectException
     *   If an object is not found in the database.
     */
    public static SchoolBDO[] getBDOarray()
    throws DataObjectException {
        SchoolDO[] DOarray = null;
        try {
            SchoolQuery query = new SchoolQuery();
	    // To restrict the set of objects returned,
	    // you could invoke query.setXxx(), where Xxx
	    // is an Attribute of <CODE>SchoolDO</CODE> which was 
	    // marked as "Can be queried" in the DODS Attribute Editor.
            DOarray = query.getDOArray();
        } catch ( NonUniqueQueryException e1 ) {
            // SchoolQuery will throw NonUniqueQueryException
            // only if query.requireUniqueInstance() is called
	    // and more than one DO was found.
        } catch ( DataObjectException e2 ) {
            // This could happen if the database server dies, etc.
            throw e2;
        }
        SchoolBDO[] BDOarray = new SchoolBDO[ DOarray.length ];
        for ( int i = 0; i < DOarray.length; i++ )
            BDOarray[i] = SchoolBDO.createExisting( DOarray[i] );
 
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
     * HTML select lists with <CODE>SchoolBDO</CODE> objects as options.
     *
     * The <CODE>getHandle()</CODE> method is used
     * to set the value for each option,
     * and the <CODE>hasMatchingHandle()<CODE>
     * methods are used to lookup the Data Object when the selection has
     * been made.
     *
     * This SchoolBDO object holds a reference to a SchoolDO object.
     * The id of this SchoolBDO is the id of its SchoolDO.
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
    * Get Description of the SchoolDO
    *
    * @return Description of the SchoolDO
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
    * Set Description of the SchoolDO
    *
    * @param Description of the SchoolDO
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
    * Get Location of the SchoolDO
    *
    * @return Location of the SchoolDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public String getLocation () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getLocation ();
   }

   
   /**
    * Set Location of the SchoolDO
    *
    * @param Location of the SchoolDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setLocation ( String Location ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setLocation ( Location );
      afterAnySet();	// business actions/assertions after data assignment
   }


   

   /**
    * Get Type of the SchoolDO
    *
    * @return Type of the SchoolDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.SchooltypeDO getType () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getType ();
   }

   
   /**
    * Set Type of the SchoolDO
    *
    * @param Type of the SchoolDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setType ( jobmatch.data.SchooltypeDO Type ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setType ( Type );
      afterAnySet();	// business actions/assertions after data assignment
   }

   

   /**
    * Get BDO-wrapped Type of the SchoolDO
    *
    * @return BDO-wrapped Type of the SchoolDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.SchooltypeBDO getTypeBDO () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      jobmatch.data.SchooltypeBDO b = jobmatch.data.SchooltypeBDO.createExisting(
					  DO.getType () );
      return b;
   }

   /**
    * Set Type of the SchoolDO
    *
    * @param BDO-wrapped Type of the SchoolDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setType ( jobmatch.data.SchooltypeBDO Type ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      if ( null == Type ) {
	  if ( false )
	      DO.setType ( null );
	  else 
	      throw new DataObjectException( 
		  "SchoolBDO.setType does not allow NULL." );
      } else {
          DO.setType ( Type.getDO() );
      }
      afterAnySet();	// business actions/assertions after data assignment
   }
   

   
    /**
     * Get array of SchoolCandidateDO objects that refer to the DO held by this BDO.
     *
     * @return array of SchoolCandidateDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.SchoolCandidateDO[] getSchoolCandidateDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.SchoolCandidateDO[] ret = null;
	try {
	    jobmatch.data.SchoolCandidateQuery q = new jobmatch.data.SchoolCandidateQuery();
	    q.setQuerySchool( DO );
	    ret = q.getDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.SchoolCandidateDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single SchoolCandidateDO object
     * that refers to the DO held by this BDO.
     *
     * @return SchoolCandidateDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one SchoolCandidateDO object was found.
     */
    public jobmatch.data.SchoolCandidateDO getSchoolCandidateDO () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.SchoolCandidateQuery q = new jobmatch.data.SchoolCandidateQuery();
	q.setQuerySchool( DO );
	q.requireUniqueInstance();
	return q.getNextDO();
    }

    /**
     * Get array of SchoolCandidateBDO objects holding SchoolCandidateDO objects
     * that refer to the DO held by this BDO.
     *
     * @return array of SchoolCandidateBDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.SchoolCandidateBDO[] getSchoolCandidateBDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.SchoolCandidateBDO[] ret = null;
	try {
	    jobmatch.data.SchoolCandidateQuery q = new jobmatch.data.SchoolCandidateQuery();
	    q.setQuerySchool( DO );
	    ret = q.getBDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.SchoolCandidateBDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single SchoolCandidateBDO object holding a SchoolCandidateDO object
     * that refers to the DO held by this BDO.
     *
     * @return SchoolCandidateBDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one SchoolCandidateBDO object was found.
     */
    public jobmatch.data.SchoolCandidateBDO getSchoolCandidateBDO () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.SchoolCandidateQuery q = new jobmatch.data.SchoolCandidateQuery();
	q.setQuerySchool( DO );
	q.requireUniqueInstance();
	return q.getNextBDO();
    }

 
    /**
     * Add (set & commit) a SchoolCandidateBDO object whose SchoolCandidateDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo SchoolCandidateBDO to be set to point to this BDO and committed.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addSchoolCandidateBDO( jobmatch.data.SchoolCandidateBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        addSchoolCandidateBDO( rbdo, null );
    }
 
 
    /**
     * Add (set & commit) a SchoolCandidateBDO object whose SchoolCandidateDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo SchoolCandidateBDO to be set to point to this BDO and committed.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addSchoolCandidateBDO( jobmatch.data.SchoolCandidateBDO rbdo, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        rbdo.setSchool( this.DO );
        rbdo.commit( tran );
    }

 
    /**
     * Remove (delete) a SchoolCandidateBDO object whose SchoolCandidateDO
     * refers to the DO held by this BDO.
     *
     * @param r SchoolCandidateBDO to be deleted.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeSchoolCandidateBDO( jobmatch.data.SchoolCandidateBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        removeSchoolCandidateBDO( rbdo, null );
    }
 
 
    /**
     * Remove (delete) a SchoolCandidateBDO object whose SchoolCandidateDO
     * refers to the DO held by this BDO.
     *
     * @param r SchoolCandidateBDO to be deleted.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeSchoolCandidateBDO( jobmatch.data.SchoolCandidateBDO rbdo, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
	SchoolDO rdo = rbdo.getSchool();
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
     * From the many-to-many relationship expressed by SchoolCandidateDO,
     * get array of GraduationDO objects that indirectly refer
     * to the DO held by this BDO.
     *
     * @return array of GraduationDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.GraduationDO[] getGraduationDOArray_via_SchoolCandidate () 
    throws DataObjectException {
	jobmatch.data.GraduationDO[] ret = null;
	try {
	    jobmatch.data.SchoolCandidateDO[] arr = getSchoolCandidateDOArray();
	    ret = new jobmatch.data.GraduationDO[ arr.length ];
	    for ( int i = 0; i < arr.length; i++ ) {
		ret[ i ] = arr[ i ].getDiploma();
	    }
	} catch ( Exception e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: ", e );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.GraduationDO[ 0 ];
	}
	return ret;
    }

    /**
     * To the many-to-many relationship expressed by SchoolCandidateDO,
     * add a GraduationDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param d The GraduationDO to add to the SchoolCandidateDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void mapGraduation_via_SchoolCandidateDO( jobmatch.data.GraduationDO d )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	mapGraduation_via_SchoolCandidateDO( d, null );
    }

    /**
     * To the many-to-many relationship expressed by SchoolCandidateDO,
     * add a GraduationDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param d The GraduationDO to add to the SchoolCandidateDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void mapGraduation_via_SchoolCandidateDO( jobmatch.data.GraduationDO d, DBTransaction tran )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	jobmatch.data.GraduationBDO b = jobmatch.data.GraduationBDO.createExisting( d );
	mapGraduation_via_SchoolCandidateBDO( b, tran );
    }

    /**
     * To the many-to-many relationship expressed by SchoolCandidateDO,
     * add a GraduationDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param b The GraduationBDO to add to the SchoolCandidateDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void mapGraduation_via_SchoolCandidateBDO( jobmatch.data.GraduationBDO b )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	mapGraduation_via_SchoolCandidateBDO( b, null );
    }

    /**
     * To the many-to-many relationship expressed by SchoolCandidateDO,
     * add a GraduationDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param b The GraduationBDO to add to the SchoolCandidateDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void mapGraduation_via_SchoolCandidateBDO( jobmatch.data.GraduationBDO b, DBTransaction tran )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	jobmatch.data.SchoolCandidateBDO m = null;
	try {
	    m = jobmatch.data.SchoolCandidateBDO.createVirgin();
	} catch ( Exception e ) { 
	    throw new DataObjectException( 
		"jobmatch.data.SchoolCandidateBDO.createVirgin failed", e );
	}
	m.setDiploma( b );
	m.setSchool( this );
	m.commit( tran );
    }

    /**
     * From the many-to-many relationship expressed by SchoolCandidateDO,
     * remove (delete) the GraduationDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param d The GraduationDO to remove from the SchoolCandidateDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void unmapGraduation_via_SchoolCandidateDO( jobmatch.data.GraduationDO d )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	unmapGraduation_via_SchoolCandidateDO( d, null );
    }

    /**
     * From the many-to-many relationship expressed by SchoolCandidateDO,
     * remove (delete) the GraduationDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param d The GraduationDO to remove from the SchoolCandidateDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void unmapGraduation_via_SchoolCandidateDO( jobmatch.data.GraduationDO d, DBTransaction tran )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	jobmatch.data.GraduationBDO b = jobmatch.data.GraduationBDO.createExisting( d );
	unmapGraduation_via_SchoolCandidateBDO( b, tran );
    }

    /**
     * From the many-to-many relationship expressed by SchoolCandidateDO,
     * remove (delete) the GraduationDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param b The GraduationBDO to remove from the SchoolCandidateDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void unmapGraduation_via_SchoolCandidateBDO( jobmatch.data.GraduationBDO b )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	unmapGraduation_via_SchoolCandidateBDO( b, null );
    }

    /**
     * From the many-to-many relationship expressed by SchoolCandidateDO,
     * remove (delete) the GraduationDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param b The GraduationBDO to remove from the SchoolCandidateDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void unmapGraduation_via_SchoolCandidateBDO( jobmatch.data.GraduationBDO b, DBTransaction tran )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	jobmatch.data.SchoolCandidateQuery q = new jobmatch.data.SchoolCandidateQuery();
	q.setQuerySchool( DO );
	q.setQueryDiploma( b.getDO() );
	q.requireUniqueInstance();
	jobmatch.data.SchoolCandidateBDO m = null;
	try {
	    m = q.getNextBDO();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( "Multiple mappings for " +
		DO + " and " + b.getDO() + " in jobmatch.data.SchoolCandidate table." );
	}
	m.delete( tran );
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
     * A stub method for implementing pre-commit assertions 
     * for the Type data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where Type is not valid for writing to the database.
     */
    protected void okToCommitType( jobmatch.data.SchooltypeDO member ) 
    throws RefAssertionException { }

    /**
     * A stub method for implementing pre-delete assertions 
     * for the Type data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where Type is not valid for deletion from the database.
     */
    protected void okToDeleteType( jobmatch.data.SchooltypeDO member ) 
    throws RefAssertionException { }



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
	    jobmatch.data.SchoolCandidateBDO[] a = getSchoolCandidateBDOArray();
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
	  	jobmatch.data.SchooltypeDO Type_DO = DO.getType();
	if ( null != Type_DO ) {
	    if ( Type_DO.isLoaded() ) {
		okToCommitType( Type_DO );
		jobmatch.data.SchooltypeBDO b = 
		    jobmatch.data.SchooltypeBDO.createExisting(
						    Type_DO );
		b.commit( dbt );
	    } else {
		// since the referenced DO is not loaded,
		// it cannot be dirty, so there is no need to commit it.
	    }
	} else {
	    if ( ! false )
		throw new RefAssertionException(
		    "Cannot commit SchoolBDO ( " + toString() +
		    " ) because Type is not allowed to be null." );
	}

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
