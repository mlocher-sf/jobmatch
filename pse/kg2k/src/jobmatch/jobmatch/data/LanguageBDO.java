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
 * /scratch/studer_repositry/dataTest/jobmatch/data/LanguageBDO.java
 *-----------------------------------------------------------------------------
 */

package jobmatch.data;

import java.sql.*;
import com.lutris.appserver.server.*;
import com.lutris.appserver.server.sql.*;

import com.lutris.dods.builder.generator.query.*;

/**
 * LanguageBDO contains the same set and get methods as
 * the LanguageDO class.
 * Business Object (BO) classes typically need these set and get methods.
 * So by deriving a BO from a BDO, or by implementing a BO that 
 * contains a BDO, the developer of the BO is spared some work.
 *
 * @author studer
 * @version $Revision: 1.2 $
 */
public class LanguageBDO implements java.io.Serializable {

    /**
     * The LanguageDO object upon which the set and get methods operate.
     * This member is protected so that classes derived from LanguageBDO
     * can access the underlying Data Object.
     */
    protected LanguageDO DO;

    /**
     * Note:  This method is intended for use only by other BDO classes.
     * Presentation Layer classes should (theoretically) always use the
     * Business Layer (BDO) to create/access Data Layer (DO) objects.
     * The overhead for using BDO objects is small
     * (the BDO classes are fairly lightweight.)
     *
     * @return The DO object held by this BDO object.
     */
    public LanguageDO getDO() { 
	return DO;
    }

    /**
     * Like the class <CODE>LanguageDO</CODE>, 
     * this class acts as a factory.
     * Business Object (BO) classes typically need these set and get methods.
     * So by deriving a BO from a BDO, or by implementing a BO that 
     * contains one or more BDOs, the developer of the BO is spared some work.
     *
     * @exception Exception
     *   If an error occurs.
     */
    public static LanguageBDO createVirgin() throws Exception { 
	LanguageBDO bdo = new LanguageBDO ();
	return bdo;
    }

    /**
     * Constructor for use by classes derived from <CODE>LanguageBDO</CODE>.
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
    public LanguageBDO( LanguageDO DO ) { 
	this.DO = DO;
    }

    /**
     * Constructor required by <CODE>LanguageBDO.create</CODE> methods.
     */
    public LanguageBDO() throws Exception { 
	this.DO = LanguageDO.createVirgin();
    }

    /**
     * The createExisting method is used to create a <CODE>LanguageBDO</CODE>
     * from a <CODE>LanguageDO</CODE> that was returned by 
     * the <CODE>LanguageQuery</CODE> class.
     */
    public static LanguageBDO createExisting( LanguageDO DO ) { 
	LanguageBDO bdo = new LanguageBDO ( DO );
	return bdo;
    }

    /** 
     * The getBDOarray method performs a database query to
     * return an array of <CODE>LanguageBDO</CODE> objects
     * representing all the rows in the <CODE>Language</CODE> table.
     *
     * This method is a minimal example of using a Query class.
     * To restrict the set of objects returned,  you could
     * invoke <CODE>query.setXxx()</CODE>, where <CODE>Xxx</CODE>
     * is an Attribute of <CODE>LanguageDO</CODE> which was 
     * marked as "Can be queried" in the DODS Attribute Editor.
     *
     * @exception DataObjectException
     *   If an object is not found in the database.
     */
    public static LanguageBDO[] getBDOarray()
    throws DataObjectException {
        LanguageDO[] DOarray = null;
        try {
            LanguageQuery query = new LanguageQuery();
	    // To restrict the set of objects returned,
	    // you could invoke query.setXxx(), where Xxx
	    // is an Attribute of <CODE>LanguageDO</CODE> which was 
	    // marked as "Can be queried" in the DODS Attribute Editor.
            DOarray = query.getDOArray();
        } catch ( NonUniqueQueryException e1 ) {
            // LanguageQuery will throw NonUniqueQueryException
            // only if query.requireUniqueInstance() is called
	    // and more than one DO was found.
        } catch ( DataObjectException e2 ) {
            // This could happen if the database server dies, etc.
            throw e2;
        }
        LanguageBDO[] BDOarray = new LanguageBDO[ DOarray.length ];
        for ( int i = 0; i < DOarray.length; i++ )
            BDOarray[i] = LanguageBDO.createExisting( DOarray[i] );
 
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
     * HTML select lists with <CODE>LanguageBDO</CODE> objects as options.
     *
     * The <CODE>getHandle()</CODE> method is used
     * to set the value for each option,
     * and the <CODE>hasMatchingHandle()<CODE>
     * methods are used to lookup the Data Object when the selection has
     * been made.
     *
     * This LanguageBDO object holds a reference to a LanguageDO object.
     * The id of this LanguageBDO is the id of its LanguageDO.
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
    * Get Description of the LanguageDO
    *
    * @return Description of the LanguageDO
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
    * Set Description of the LanguageDO
    *
    * @param Description of the LanguageDO
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
     * Get array of LanguageCandidateDO objects that refer to the DO held by this BDO.
     *
     * @return array of LanguageCandidateDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.LanguageCandidateDO[] getLanguageCandidateDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.LanguageCandidateDO[] ret = null;
	try {
	    jobmatch.data.LanguageCandidateQuery q = new jobmatch.data.LanguageCandidateQuery();
	    q.setQueryLanguage( DO );
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
    public jobmatch.data.LanguageCandidateDO getLanguageCandidateDO () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.LanguageCandidateQuery q = new jobmatch.data.LanguageCandidateQuery();
	q.setQueryLanguage( DO );
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
    public jobmatch.data.LanguageCandidateBDO[] getLanguageCandidateBDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.LanguageCandidateBDO[] ret = null;
	try {
	    jobmatch.data.LanguageCandidateQuery q = new jobmatch.data.LanguageCandidateQuery();
	    q.setQueryLanguage( DO );
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
    public jobmatch.data.LanguageCandidateBDO getLanguageCandidateBDO () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.LanguageCandidateQuery q = new jobmatch.data.LanguageCandidateQuery();
	q.setQueryLanguage( DO );
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
    public void addLanguageCandidateBDO( jobmatch.data.LanguageCandidateBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        addLanguageCandidateBDO( rbdo, null );
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
    public void addLanguageCandidateBDO( jobmatch.data.LanguageCandidateBDO rbdo, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        rbdo.setLanguage( this.DO );
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
    public void removeLanguageCandidateBDO( jobmatch.data.LanguageCandidateBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        removeLanguageCandidateBDO( rbdo, null );
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
    public void removeLanguageCandidateBDO( jobmatch.data.LanguageCandidateBDO rbdo, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
	LanguageDO rdo = rbdo.getLanguage();
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
    public jobmatch.data.LanguageProfileDO[] getLanguageProfileDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.LanguageProfileDO[] ret = null;
	try {
	    jobmatch.data.LanguageProfileQuery q = new jobmatch.data.LanguageProfileQuery();
	    q.setQueryLanguage( DO );
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
    public jobmatch.data.LanguageProfileDO getLanguageProfileDO () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.LanguageProfileQuery q = new jobmatch.data.LanguageProfileQuery();
	q.setQueryLanguage( DO );
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
    public jobmatch.data.LanguageProfileBDO[] getLanguageProfileBDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.LanguageProfileBDO[] ret = null;
	try {
	    jobmatch.data.LanguageProfileQuery q = new jobmatch.data.LanguageProfileQuery();
	    q.setQueryLanguage( DO );
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
    public jobmatch.data.LanguageProfileBDO getLanguageProfileBDO () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.LanguageProfileQuery q = new jobmatch.data.LanguageProfileQuery();
	q.setQueryLanguage( DO );
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
    public void addLanguageProfileBDO( jobmatch.data.LanguageProfileBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        addLanguageProfileBDO( rbdo, null );
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
    public void addLanguageProfileBDO( jobmatch.data.LanguageProfileBDO rbdo, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        rbdo.setLanguage( this.DO );
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
    public void removeLanguageProfileBDO( jobmatch.data.LanguageProfileBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        removeLanguageProfileBDO( rbdo, null );
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
    public void removeLanguageProfileBDO( jobmatch.data.LanguageProfileBDO rbdo, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
	LanguageDO rdo = rbdo.getLanguage();
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
	    jobmatch.data.LanguageCandidateBDO[] a = getLanguageCandidateBDOArray();
	    for ( int i = 0; i < a.length; i++ ) {
		a[ i ].delete( dbt );
	    }
	}
	
	
	{
	    // perform cascading delete on referring table
	    jobmatch.data.LanguageProfileBDO[] a = getLanguageProfileBDOArray();
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
