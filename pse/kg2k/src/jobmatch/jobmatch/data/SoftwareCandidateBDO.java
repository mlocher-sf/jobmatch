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
 * /scratch/studer_repositry/dataTest/jobmatch/data/SoftwareCandidateBDO.java
 *-----------------------------------------------------------------------------
 */

package jobmatch.data;

import java.sql.*;
import com.lutris.appserver.server.*;
import com.lutris.appserver.server.sql.*;

import com.lutris.dods.builder.generator.query.*;

/**
 * SoftwareCandidateBDO contains the same set and get methods as
 * the SoftwareCandidateDO class.
 * Business Object (BO) classes typically need these set and get methods.
 * So by deriving a BO from a BDO, or by implementing a BO that 
 * contains a BDO, the developer of the BO is spared some work.
 *
 * @author studer
 * @version $Revision: 1.3 $
 */
public class SoftwareCandidateBDO implements java.io.Serializable {

    /**
     * The SoftwareCandidateDO object upon which the set and get methods operate.
     * This member is protected so that classes derived from SoftwareCandidateBDO
     * can access the underlying Data Object.
     */
    protected SoftwareCandidateDO DO;

    /**
     * Note:  This method is intended for use only by other BDO classes.
     * Presentation Layer classes should (theoretically) always use the
     * Business Layer (BDO) to create/access Data Layer (DO) objects.
     * The overhead for using BDO objects is small
     * (the BDO classes are fairly lightweight.)
     *
     * @return The DO object held by this BDO object.
     */
    public SoftwareCandidateDO getDO() { 
	return DO;
    }

    /**
     * Like the class <CODE>SoftwareCandidateDO</CODE>, 
     * this class acts as a factory.
     * Business Object (BO) classes typically need these set and get methods.
     * So by deriving a BO from a BDO, or by implementing a BO that 
     * contains one or more BDOs, the developer of the BO is spared some work.
     *
     * @exception Exception
     *   If an error occurs.
     */
    public static SoftwareCandidateBDO createVirgin() throws Exception { 
	SoftwareCandidateBDO bdo = new SoftwareCandidateBDO ();
	return bdo;
    }

    /**
     * Constructor for use by classes derived from <CODE>SoftwareCandidateBDO</CODE>.
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
    public SoftwareCandidateBDO( SoftwareCandidateDO DO ) { 
	this.DO = DO;
    }

    /**
     * Constructor required by <CODE>SoftwareCandidateBDO.create</CODE> methods.
     */
    public SoftwareCandidateBDO() throws Exception { 
	this.DO = SoftwareCandidateDO.createVirgin();
    }

    /**
     * The createExisting method is used to create a <CODE>SoftwareCandidateBDO</CODE>
     * from a <CODE>SoftwareCandidateDO</CODE> that was returned by 
     * the <CODE>SoftwareCandidateQuery</CODE> class.
     */
    public static SoftwareCandidateBDO createExisting( SoftwareCandidateDO DO ) { 
	SoftwareCandidateBDO bdo = new SoftwareCandidateBDO ( DO );
	return bdo;
    }

    /** 
     * The getBDOarray method performs a database query to
     * return an array of <CODE>SoftwareCandidateBDO</CODE> objects
     * representing all the rows in the <CODE>SoftwareCandidate</CODE> table.
     *
     * This method is a minimal example of using a Query class.
     * To restrict the set of objects returned,  you could
     * invoke <CODE>query.setXxx()</CODE>, where <CODE>Xxx</CODE>
     * is an Attribute of <CODE>SoftwareCandidateDO</CODE> which was 
     * marked as "Can be queried" in the DODS Attribute Editor.
     *
     * @exception DataObjectException
     *   If an object is not found in the database.
     */
    public static SoftwareCandidateBDO[] getBDOarray()
    throws DataObjectException {
        SoftwareCandidateDO[] DOarray = null;
        try {
            SoftwareCandidateQuery query = new SoftwareCandidateQuery();
	    // To restrict the set of objects returned,
	    // you could invoke query.setXxx(), where Xxx
	    // is an Attribute of <CODE>SoftwareCandidateDO</CODE> which was 
	    // marked as "Can be queried" in the DODS Attribute Editor.
            DOarray = query.getDOArray();
        } catch ( NonUniqueQueryException e1 ) {
            // SoftwareCandidateQuery will throw NonUniqueQueryException
            // only if query.requireUniqueInstance() is called
	    // and more than one DO was found.
        } catch ( DataObjectException e2 ) {
            // This could happen if the database server dies, etc.
            throw e2;
        }
        SoftwareCandidateBDO[] BDOarray = new SoftwareCandidateBDO[ DOarray.length ];
        for ( int i = 0; i < DOarray.length; i++ )
            BDOarray[i] = SoftwareCandidateBDO.createExisting( DOarray[i] );
 
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
     * HTML select lists with <CODE>SoftwareCandidateBDO</CODE> objects as options.
     *
     * The <CODE>getHandle()</CODE> method is used
     * to set the value for each option,
     * and the <CODE>hasMatchingHandle()<CODE>
     * methods are used to lookup the Data Object when the selection has
     * been made.
     *
     * This SoftwareCandidateBDO object holds a reference to a SoftwareCandidateDO object.
     * The id of this SoftwareCandidateBDO is the id of its SoftwareCandidateDO.
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
    * Get Candidate of the SoftwareCandidateDO
    *
    * @return Candidate of the SoftwareCandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.CandidateDO getCandidate () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getCandidate ();
   }

   
   /**
    * Set Candidate of the SoftwareCandidateDO
    *
    * @param Candidate of the SoftwareCandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setCandidate ( jobmatch.data.CandidateDO Candidate ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setCandidate ( Candidate );
      afterAnySet();	// business actions/assertions after data assignment
   }

   

   /**
    * Get BDO-wrapped Candidate of the SoftwareCandidateDO
    *
    * @return BDO-wrapped Candidate of the SoftwareCandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.CandidateBDO getCandidateBDO () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      jobmatch.data.CandidateBDO b = jobmatch.data.CandidateBDO.createExisting(
					  DO.getCandidate () );
      return b;
   }

   /**
    * Set Candidate of the SoftwareCandidateDO
    *
    * @param BDO-wrapped Candidate of the SoftwareCandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setCandidate ( jobmatch.data.CandidateBDO Candidate ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      if ( null == Candidate ) {
	  if ( false )
	      DO.setCandidate ( null );
	  else 
	      throw new DataObjectException( 
		  "SoftwareCandidateBDO.setCandidate does not allow NULL." );
      } else {
          DO.setCandidate ( Candidate.getDO() );
      }
      afterAnySet();	// business actions/assertions after data assignment
   }
   

   

   /**
    * Get Capability of the SoftwareCandidateDO
    *
    * @return Capability of the SoftwareCandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.CompcapabilityDO getCapability () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getCapability ();
   }

   
   /**
    * Set Capability of the SoftwareCandidateDO
    *
    * @param Capability of the SoftwareCandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setCapability ( jobmatch.data.CompcapabilityDO Capability ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setCapability ( Capability );
      afterAnySet();	// business actions/assertions after data assignment
   }

   

   /**
    * Get BDO-wrapped Capability of the SoftwareCandidateDO
    *
    * @return BDO-wrapped Capability of the SoftwareCandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.CompcapabilityBDO getCapabilityBDO () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      jobmatch.data.CompcapabilityBDO b = jobmatch.data.CompcapabilityBDO.createExisting(
					  DO.getCapability () );
      return b;
   }

   /**
    * Set Capability of the SoftwareCandidateDO
    *
    * @param BDO-wrapped Capability of the SoftwareCandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setCapability ( jobmatch.data.CompcapabilityBDO Capability ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      if ( null == Capability ) {
	  if ( false )
	      DO.setCapability ( null );
	  else 
	      throw new DataObjectException( 
		  "SoftwareCandidateBDO.setCapability does not allow NULL." );
      } else {
          DO.setCapability ( Capability.getDO() );
      }
      afterAnySet();	// business actions/assertions after data assignment
   }
   

   

   /**
    * Get Software of the SoftwareCandidateDO
    *
    * @return Software of the SoftwareCandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.SoftwareDO getSoftware () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getSoftware ();
   }

   
   /**
    * Set Software of the SoftwareCandidateDO
    *
    * @param Software of the SoftwareCandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setSoftware ( jobmatch.data.SoftwareDO Software ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setSoftware ( Software );
      afterAnySet();	// business actions/assertions after data assignment
   }

   

   /**
    * Get BDO-wrapped Software of the SoftwareCandidateDO
    *
    * @return BDO-wrapped Software of the SoftwareCandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.SoftwareBDO getSoftwareBDO () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      jobmatch.data.SoftwareBDO b = jobmatch.data.SoftwareBDO.createExisting(
					  DO.getSoftware () );
      return b;
   }

   /**
    * Set Software of the SoftwareCandidateDO
    *
    * @param BDO-wrapped Software of the SoftwareCandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setSoftware ( jobmatch.data.SoftwareBDO Software ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      if ( null == Software ) {
	  if ( false )
	      DO.setSoftware ( null );
	  else 
	      throw new DataObjectException( 
		  "SoftwareCandidateBDO.setSoftware does not allow NULL." );
      } else {
          DO.setSoftware ( Software.getDO() );
      }
      afterAnySet();	// business actions/assertions after data assignment
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
     * for the Candidate data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where Candidate is not valid for writing to the database.
     */
    protected void okToCommitCandidate( jobmatch.data.CandidateDO member ) 
    throws RefAssertionException { }

    /**
     * A stub method for implementing pre-delete assertions 
     * for the Candidate data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where Candidate is not valid for deletion from the database.
     */
    protected void okToDeleteCandidate( jobmatch.data.CandidateDO member ) 
    throws RefAssertionException { }

    /**
     * A stub method for implementing pre-commit assertions 
     * for the Capability data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where Capability is not valid for writing to the database.
     */
    protected void okToCommitCapability( jobmatch.data.CompcapabilityDO member ) 
    throws RefAssertionException { }

    /**
     * A stub method for implementing pre-delete assertions 
     * for the Capability data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where Capability is not valid for deletion from the database.
     */
    protected void okToDeleteCapability( jobmatch.data.CompcapabilityDO member ) 
    throws RefAssertionException { }

    /**
     * A stub method for implementing pre-commit assertions 
     * for the Software data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where Software is not valid for writing to the database.
     */
    protected void okToCommitSoftware( jobmatch.data.SoftwareDO member ) 
    throws RefAssertionException { }

    /**
     * A stub method for implementing pre-delete assertions 
     * for the Software data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where Software is not valid for deletion from the database.
     */
    protected void okToDeleteSoftware( jobmatch.data.SoftwareDO member ) 
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
	  
          // The following line keeps the compiler happy
          // when the CASCADING_DELETES tag is empty.
          if ( false )
              throw new QueryException("XXX");
      } else {
	  // commit referenced DOs.
	  	jobmatch.data.CandidateDO Candidate_DO = DO.getCandidate();
	if ( null != Candidate_DO ) {
	    if ( Candidate_DO.isLoaded() ) {
		okToCommitCandidate( Candidate_DO );
		jobmatch.data.CandidateBDO b = 
		    jobmatch.data.CandidateBDO.createExisting(
						    Candidate_DO );
		b.commit( dbt );
	    } else {
		// since the referenced DO is not loaded,
		// it cannot be dirty, so there is no need to commit it.
	    }
	} else {
	    if ( ! false )
		throw new RefAssertionException(
		    "Cannot commit SoftwareCandidateBDO ( " + toString() +
		    " ) because Candidate is not allowed to be null." );
	}
	jobmatch.data.CompcapabilityDO Capability_DO = DO.getCapability();
	if ( null != Capability_DO ) {
	    if ( Capability_DO.isLoaded() ) {
		okToCommitCapability( Capability_DO );
		jobmatch.data.CompcapabilityBDO b = 
		    jobmatch.data.CompcapabilityBDO.createExisting(
						    Capability_DO );
		b.commit( dbt );
	    } else {
		// since the referenced DO is not loaded,
		// it cannot be dirty, so there is no need to commit it.
	    }
	} else {
	    if ( ! false )
		throw new RefAssertionException(
		    "Cannot commit SoftwareCandidateBDO ( " + toString() +
		    " ) because Capability is not allowed to be null." );
	}
	jobmatch.data.SoftwareDO Software_DO = DO.getSoftware();
	if ( null != Software_DO ) {
	    if ( Software_DO.isLoaded() ) {
		okToCommitSoftware( Software_DO );
		jobmatch.data.SoftwareBDO b = 
		    jobmatch.data.SoftwareBDO.createExisting(
						    Software_DO );
		b.commit( dbt );
	    } else {
		// since the referenced DO is not loaded,
		// it cannot be dirty, so there is no need to commit it.
	    }
	} else {
	    if ( ! false )
		throw new RefAssertionException(
		    "Cannot commit SoftwareCandidateBDO ( " + toString() +
		    " ) because Software is not allowed to be null." );
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
