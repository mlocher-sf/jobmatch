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
 * /scratch/studer_repositry/dataTest/jobmatch/data/EmployerBDO.java
 *-----------------------------------------------------------------------------
 */

package jobmatch.data;

import java.sql.*;
import com.lutris.appserver.server.*;
import com.lutris.appserver.server.sql.*;

import com.lutris.dods.builder.generator.query.*;

/**
 * EmployerBDO contains the same set and get methods as
 * the EmployerDO class.
 * Business Object (BO) classes typically need these set and get methods.
 * So by deriving a BO from a BDO, or by implementing a BO that 
 * contains a BDO, the developer of the BO is spared some work.
 *
 * @author studer
 * @version $Revision: 1.5 $
 */
public class EmployerBDO implements java.io.Serializable {

    /**
     * The EmployerDO object upon which the set and get methods operate.
     * This member is protected so that classes derived from EmployerBDO
     * can access the underlying Data Object.
     */
    protected EmployerDO DO;

    /**
     * Note:  This method is intended for use only by other BDO classes.
     * Presentation Layer classes should (theoretically) always use the
     * Business Layer (BDO) to create/access Data Layer (DO) objects.
     * The overhead for using BDO objects is small
     * (the BDO classes are fairly lightweight.)
     *
     * @return The DO object held by this BDO object.
     */
    public EmployerDO getDO() { 
	return DO;
    }

    /**
     * Like the class <CODE>EmployerDO</CODE>, 
     * this class acts as a factory.
     * Business Object (BO) classes typically need these set and get methods.
     * So by deriving a BO from a BDO, or by implementing a BO that 
     * contains one or more BDOs, the developer of the BO is spared some work.
     *
     * @exception Exception
     *   If an error occurs.
     */
    public static EmployerBDO createVirgin() throws Exception { 
	EmployerBDO bdo = new EmployerBDO ();
	return bdo;
    }

    /**
     * Constructor for use by classes derived from <CODE>EmployerBDO</CODE>.
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
    public EmployerBDO( EmployerDO DO ) { 
	this.DO = DO;
    }

    /**
     * Constructor required by <CODE>EmployerBDO.create</CODE> methods.
     */
    public EmployerBDO() throws Exception { 
	this.DO = EmployerDO.createVirgin();
    }

    /**
     * The createExisting method is used to create a <CODE>EmployerBDO</CODE>
     * from a <CODE>EmployerDO</CODE> that was returned by 
     * the <CODE>EmployerQuery</CODE> class.
     */
    public static EmployerBDO createExisting( EmployerDO DO ) { 
	EmployerBDO bdo = new EmployerBDO ( DO );
	return bdo;
    }

    /** 
     * The getBDOarray method performs a database query to
     * return an array of <CODE>EmployerBDO</CODE> objects
     * representing all the rows in the <CODE>Employer</CODE> table.
     *
     * This method is a minimal example of using a Query class.
     * To restrict the set of objects returned,  you could
     * invoke <CODE>query.setXxx()</CODE>, where <CODE>Xxx</CODE>
     * is an Attribute of <CODE>EmployerDO</CODE> which was 
     * marked as "Can be queried" in the DODS Attribute Editor.
     *
     * @exception DataObjectException
     *   If an object is not found in the database.
     */
    public static EmployerBDO[] getBDOarray()
    throws DataObjectException {
        EmployerDO[] DOarray = null;
        try {
            EmployerQuery query = new EmployerQuery();
	    // To restrict the set of objects returned,
	    // you could invoke query.setXxx(), where Xxx
	    // is an Attribute of <CODE>EmployerDO</CODE> which was 
	    // marked as "Can be queried" in the DODS Attribute Editor.
            DOarray = query.getDOArray();
        } catch ( NonUniqueQueryException e1 ) {
            // EmployerQuery will throw NonUniqueQueryException
            // only if query.requireUniqueInstance() is called
	    // and more than one DO was found.
        } catch ( DataObjectException e2 ) {
            // This could happen if the database server dies, etc.
            throw e2;
        }
        EmployerBDO[] BDOarray = new EmployerBDO[ DOarray.length ];
        for ( int i = 0; i < DOarray.length; i++ )
            BDOarray[i] = EmployerBDO.createExisting( DOarray[i] );
 
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
     * HTML select lists with <CODE>EmployerBDO</CODE> objects as options.
     *
     * The <CODE>getHandle()</CODE> method is used
     * to set the value for each option,
     * and the <CODE>hasMatchingHandle()<CODE>
     * methods are used to lookup the Data Object when the selection has
     * been made.
     *
     * This EmployerBDO object holds a reference to a EmployerDO object.
     * The id of this EmployerBDO is the id of its EmployerDO.
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
    * Get Name of the EmployerDO
    *
    * @return Name of the EmployerDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public String getName () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getName ();
   }

   
   /**
    * Set Name of the EmployerDO
    *
    * @param Name of the EmployerDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setName ( String Name ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setName ( Name );
      afterAnySet();	// business actions/assertions after data assignment
   }


   

   /**
    * Get Adress of the EmployerDO
    *
    * @return Adress of the EmployerDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.AdressDO getAdress () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getAdress ();
   }

   
   /**
    * Set Adress of the EmployerDO
    *
    * @param Adress of the EmployerDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setAdress ( jobmatch.data.AdressDO Adress ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setAdress ( Adress );
      afterAnySet();	// business actions/assertions after data assignment
   }

   

   /**
    * Get BDO-wrapped Adress of the EmployerDO
    *
    * @return BDO-wrapped Adress of the EmployerDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.AdressBDO getAdressBDO () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      jobmatch.data.AdressBDO b = jobmatch.data.AdressBDO.createExisting(
					  DO.getAdress () );
      return b;
   }

   /**
    * Set Adress of the EmployerDO
    *
    * @param BDO-wrapped Adress of the EmployerDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setAdress ( jobmatch.data.AdressBDO Adress ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      if ( null == Adress ) {
	  if ( true )
	      DO.setAdress ( null );
	  else 
	      throw new DataObjectException( 
		  "EmployerBDO.setAdress does not allow NULL." );
      } else {
          DO.setAdress ( Adress.getDO() );
      }
      afterAnySet();	// business actions/assertions after data assignment
   }
   

   
    /**
     * Get array of EmployerCandidateDO objects that refer to the DO held by this BDO.
     *
     * @return array of EmployerCandidateDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.EmployerCandidateDO[] getEmployerCandidateDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.EmployerCandidateDO[] ret = null;
	try {
	    jobmatch.data.EmployerCandidateQuery q = new jobmatch.data.EmployerCandidateQuery();
	    q.setQueryEmployer( DO );
	    ret = q.getDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.EmployerCandidateDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single EmployerCandidateDO object
     * that refers to the DO held by this BDO.
     *
     * @return EmployerCandidateDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one EmployerCandidateDO object was found.
     */
    public jobmatch.data.EmployerCandidateDO getEmployerCandidateDO () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.EmployerCandidateQuery q = new jobmatch.data.EmployerCandidateQuery();
	q.setQueryEmployer( DO );
	q.requireUniqueInstance();
	return q.getNextDO();
    }

    /**
     * Get array of EmployerCandidateBDO objects holding EmployerCandidateDO objects
     * that refer to the DO held by this BDO.
     *
     * @return array of EmployerCandidateBDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.EmployerCandidateBDO[] getEmployerCandidateBDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.EmployerCandidateBDO[] ret = null;
	try {
	    jobmatch.data.EmployerCandidateQuery q = new jobmatch.data.EmployerCandidateQuery();
	    q.setQueryEmployer( DO );
	    ret = q.getBDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.EmployerCandidateBDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single EmployerCandidateBDO object holding a EmployerCandidateDO object
     * that refers to the DO held by this BDO.
     *
     * @return EmployerCandidateBDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one EmployerCandidateBDO object was found.
     */
    public jobmatch.data.EmployerCandidateBDO getEmployerCandidateBDO () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.EmployerCandidateQuery q = new jobmatch.data.EmployerCandidateQuery();
	q.setQueryEmployer( DO );
	q.requireUniqueInstance();
	return q.getNextBDO();
    }

 
    /**
     * Add (set & commit) a EmployerCandidateBDO object whose EmployerCandidateDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo EmployerCandidateBDO to be set to point to this BDO and committed.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addEmployerCandidateBDO( jobmatch.data.EmployerCandidateBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        addEmployerCandidateBDO( rbdo, null );
    }
 
 
    /**
     * Add (set & commit) a EmployerCandidateBDO object whose EmployerCandidateDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo EmployerCandidateBDO to be set to point to this BDO and committed.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addEmployerCandidateBDO( jobmatch.data.EmployerCandidateBDO rbdo, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        rbdo.setEmployer( this.DO );
        rbdo.commit( tran );
    }

 
    /**
     * Remove (delete) a EmployerCandidateBDO object whose EmployerCandidateDO
     * refers to the DO held by this BDO.
     *
     * @param r EmployerCandidateBDO to be deleted.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeEmployerCandidateBDO( jobmatch.data.EmployerCandidateBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        removeEmployerCandidateBDO( rbdo, null );
    }
 
 
    /**
     * Remove (delete) a EmployerCandidateBDO object whose EmployerCandidateDO
     * refers to the DO held by this BDO.
     *
     * @param r EmployerCandidateBDO to be deleted.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeEmployerCandidateBDO( jobmatch.data.EmployerCandidateBDO rbdo, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
	EmployerDO rdo = rbdo.getEmployer();
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
     * A stub method for implementing pre-commit assertions 
     * for the Adress data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where Adress is not valid for writing to the database.
     */
    protected void okToCommitAdress( jobmatch.data.AdressDO member ) 
    throws RefAssertionException { }

    /**
     * A stub method for implementing pre-delete assertions 
     * for the Adress data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where Adress is not valid for deletion from the database.
     */
    protected void okToDeleteAdress( jobmatch.data.AdressDO member ) 
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
	    jobmatch.data.EmployerCandidateBDO[] a = getEmployerCandidateBDOArray();
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
	  	jobmatch.data.AdressDO Adress_DO = DO.getAdress();
	if ( null != Adress_DO ) {
	    if ( Adress_DO.isLoaded() ) {
		okToCommitAdress( Adress_DO );
		jobmatch.data.AdressBDO b = 
		    jobmatch.data.AdressBDO.createExisting(
						    Adress_DO );
		b.commit( dbt );
	    } else {
		// since the referenced DO is not loaded,
		// it cannot be dirty, so there is no need to commit it.
	    }
	} else {
	    if ( ! true )
		throw new RefAssertionException(
		    "Cannot commit EmployerBDO ( " + toString() +
		    " ) because Adress is not allowed to be null." );
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
