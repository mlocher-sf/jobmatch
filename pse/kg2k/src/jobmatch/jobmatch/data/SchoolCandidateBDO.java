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
 * /scratch/studer_repositry/dataTest/jobmatch/data/SchoolCandidateBDO.java
 *-----------------------------------------------------------------------------
 */

package jobmatch.data;

import java.sql.*;
import com.lutris.appserver.server.*;
import com.lutris.appserver.server.sql.*;

import com.lutris.dods.builder.generator.query.*;

/**
 * SchoolCandidateBDO contains the same set and get methods as
 * the SchoolCandidateDO class.
 * Business Object (BO) classes typically need these set and get methods.
 * So by deriving a BO from a BDO, or by implementing a BO that 
 * contains a BDO, the developer of the BO is spared some work.
 *
 * @author studer
 * @version $Revision: 1.3 $
 */
public class SchoolCandidateBDO implements java.io.Serializable {

    /**
     * The SchoolCandidateDO object upon which the set and get methods operate.
     * This member is protected so that classes derived from SchoolCandidateBDO
     * can access the underlying Data Object.
     */
    protected SchoolCandidateDO DO;

    /**
     * Note:  This method is intended for use only by other BDO classes.
     * Presentation Layer classes should (theoretically) always use the
     * Business Layer (BDO) to create/access Data Layer (DO) objects.
     * The overhead for using BDO objects is small
     * (the BDO classes are fairly lightweight.)
     *
     * @return The DO object held by this BDO object.
     */
    public SchoolCandidateDO getDO() { 
	return DO;
    }

    /**
     * Like the class <CODE>SchoolCandidateDO</CODE>, 
     * this class acts as a factory.
     * Business Object (BO) classes typically need these set and get methods.
     * So by deriving a BO from a BDO, or by implementing a BO that 
     * contains one or more BDOs, the developer of the BO is spared some work.
     *
     * @exception Exception
     *   If an error occurs.
     */
    public static SchoolCandidateBDO createVirgin() throws Exception { 
	SchoolCandidateBDO bdo = new SchoolCandidateBDO ();
	return bdo;
    }

    /**
     * Constructor for use by classes derived from <CODE>SchoolCandidateBDO</CODE>.
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
    public SchoolCandidateBDO( SchoolCandidateDO DO ) { 
	this.DO = DO;
    }

    /**
     * Constructor required by <CODE>SchoolCandidateBDO.create</CODE> methods.
     */
    public SchoolCandidateBDO() throws Exception { 
	this.DO = SchoolCandidateDO.createVirgin();
    }

    /**
     * The createExisting method is used to create a <CODE>SchoolCandidateBDO</CODE>
     * from a <CODE>SchoolCandidateDO</CODE> that was returned by 
     * the <CODE>SchoolCandidateQuery</CODE> class.
     */
    public static SchoolCandidateBDO createExisting( SchoolCandidateDO DO ) { 
	SchoolCandidateBDO bdo = new SchoolCandidateBDO ( DO );
	return bdo;
    }

    /** 
     * The getBDOarray method performs a database query to
     * return an array of <CODE>SchoolCandidateBDO</CODE> objects
     * representing all the rows in the <CODE>SchoolCandidate</CODE> table.
     *
     * This method is a minimal example of using a Query class.
     * To restrict the set of objects returned,  you could
     * invoke <CODE>query.setXxx()</CODE>, where <CODE>Xxx</CODE>
     * is an Attribute of <CODE>SchoolCandidateDO</CODE> which was 
     * marked as "Can be queried" in the DODS Attribute Editor.
     *
     * @exception DataObjectException
     *   If an object is not found in the database.
     */
    public static SchoolCandidateBDO[] getBDOarray()
    throws DataObjectException {
        SchoolCandidateDO[] DOarray = null;
        try {
            SchoolCandidateQuery query = new SchoolCandidateQuery();
	    // To restrict the set of objects returned,
	    // you could invoke query.setXxx(), where Xxx
	    // is an Attribute of <CODE>SchoolCandidateDO</CODE> which was 
	    // marked as "Can be queried" in the DODS Attribute Editor.
            DOarray = query.getDOArray();
        } catch ( NonUniqueQueryException e1 ) {
            // SchoolCandidateQuery will throw NonUniqueQueryException
            // only if query.requireUniqueInstance() is called
	    // and more than one DO was found.
        } catch ( DataObjectException e2 ) {
            // This could happen if the database server dies, etc.
            throw e2;
        }
        SchoolCandidateBDO[] BDOarray = new SchoolCandidateBDO[ DOarray.length ];
        for ( int i = 0; i < DOarray.length; i++ )
            BDOarray[i] = SchoolCandidateBDO.createExisting( DOarray[i] );
 
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
     * HTML select lists with <CODE>SchoolCandidateBDO</CODE> objects as options.
     *
     * The <CODE>getHandle()</CODE> method is used
     * to set the value for each option,
     * and the <CODE>hasMatchingHandle()<CODE>
     * methods are used to lookup the Data Object when the selection has
     * been made.
     *
     * This SchoolCandidateBDO object holds a reference to a SchoolCandidateDO object.
     * The id of this SchoolCandidateBDO is the id of its SchoolCandidateDO.
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
    * Get Candidate of the SchoolCandidateDO
    *
    * @return Candidate of the SchoolCandidateDO
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
    * Set Candidate of the SchoolCandidateDO
    *
    * @param Candidate of the SchoolCandidateDO
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
    * Get BDO-wrapped Candidate of the SchoolCandidateDO
    *
    * @return BDO-wrapped Candidate of the SchoolCandidateDO
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
    * Set Candidate of the SchoolCandidateDO
    *
    * @param BDO-wrapped Candidate of the SchoolCandidateDO
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
		  "SchoolCandidateBDO.setCandidate does not allow NULL." );
      } else {
          DO.setCandidate ( Candidate.getDO() );
      }
      afterAnySet();	// business actions/assertions after data assignment
   }
   

   

   /**
    * Get BeginDate of the SchoolCandidateDO
    *
    * @return BeginDate of the SchoolCandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public java.sql.Date getBeginDate () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getBeginDate ();
   }

   
   /**
    * Set BeginDate of the SchoolCandidateDO
    *
    * @param BeginDate of the SchoolCandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setBeginDate ( java.sql.Date BeginDate ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setBeginDate ( BeginDate );
      afterAnySet();	// business actions/assertions after data assignment
   }


   

   /**
    * Get EndDate of the SchoolCandidateDO
    *
    * @return EndDate of the SchoolCandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public java.sql.Date getEndDate () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getEndDate ();
   }

   
   /**
    * Set EndDate of the SchoolCandidateDO
    *
    * @param EndDate of the SchoolCandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setEndDate ( java.sql.Date EndDate ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setEndDate ( EndDate );
      afterAnySet();	// business actions/assertions after data assignment
   }


   

   /**
    * Get School of the SchoolCandidateDO
    *
    * @return School of the SchoolCandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.SchoolDO getSchool () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getSchool ();
   }

   
   /**
    * Set School of the SchoolCandidateDO
    *
    * @param School of the SchoolCandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setSchool ( jobmatch.data.SchoolDO School ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setSchool ( School );
      afterAnySet();	// business actions/assertions after data assignment
   }

   

   /**
    * Get BDO-wrapped School of the SchoolCandidateDO
    *
    * @return BDO-wrapped School of the SchoolCandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.SchoolBDO getSchoolBDO () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      jobmatch.data.SchoolBDO b = jobmatch.data.SchoolBDO.createExisting(
					  DO.getSchool () );
      return b;
   }

   /**
    * Set School of the SchoolCandidateDO
    *
    * @param BDO-wrapped School of the SchoolCandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setSchool ( jobmatch.data.SchoolBDO School ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      if ( null == School ) {
	  if ( false )
	      DO.setSchool ( null );
	  else 
	      throw new DataObjectException( 
		  "SchoolCandidateBDO.setSchool does not allow NULL." );
      } else {
          DO.setSchool ( School.getDO() );
      }
      afterAnySet();	// business actions/assertions after data assignment
   }
   

   

   /**
    * Get Diploma of the SchoolCandidateDO
    *
    * @return Diploma of the SchoolCandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.GraduationDO getDiploma () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getDiploma ();
   }

   
   /**
    * Set Diploma of the SchoolCandidateDO
    *
    * @param Diploma of the SchoolCandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setDiploma ( jobmatch.data.GraduationDO Diploma ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setDiploma ( Diploma );
      afterAnySet();	// business actions/assertions after data assignment
   }

   

   /**
    * Get BDO-wrapped Diploma of the SchoolCandidateDO
    *
    * @return BDO-wrapped Diploma of the SchoolCandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.GraduationBDO getDiplomaBDO () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      jobmatch.data.GraduationBDO b = jobmatch.data.GraduationBDO.createExisting(
					  DO.getDiploma () );
      return b;
   }

   /**
    * Set Diploma of the SchoolCandidateDO
    *
    * @param BDO-wrapped Diploma of the SchoolCandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setDiploma ( jobmatch.data.GraduationBDO Diploma ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      if ( null == Diploma ) {
	  if ( true )
	      DO.setDiploma ( null );
	  else 
	      throw new DataObjectException( 
		  "SchoolCandidateBDO.setDiploma does not allow NULL." );
      } else {
          DO.setDiploma ( Diploma.getDO() );
      }
      afterAnySet();	// business actions/assertions after data assignment
   }
   

   

   /**
    * Get Remarks of the SchoolCandidateDO
    *
    * @return Remarks of the SchoolCandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public String getRemarks () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getRemarks ();
   }

   
   /**
    * Set Remarks of the SchoolCandidateDO
    *
    * @param Remarks of the SchoolCandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setRemarks ( String Remarks ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setRemarks ( Remarks );
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
     * for the School data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where School is not valid for writing to the database.
     */
    protected void okToCommitSchool( jobmatch.data.SchoolDO member ) 
    throws RefAssertionException { }

    /**
     * A stub method for implementing pre-delete assertions 
     * for the School data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where School is not valid for deletion from the database.
     */
    protected void okToDeleteSchool( jobmatch.data.SchoolDO member ) 
    throws RefAssertionException { }

    /**
     * A stub method for implementing pre-commit assertions 
     * for the Diploma data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where Diploma is not valid for writing to the database.
     */
    protected void okToCommitDiploma( jobmatch.data.GraduationDO member ) 
    throws RefAssertionException { }

    /**
     * A stub method for implementing pre-delete assertions 
     * for the Diploma data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where Diploma is not valid for deletion from the database.
     */
    protected void okToDeleteDiploma( jobmatch.data.GraduationDO member ) 
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
		    "Cannot commit SchoolCandidateBDO ( " + toString() +
		    " ) because Candidate is not allowed to be null." );
	}
	jobmatch.data.SchoolDO School_DO = DO.getSchool();
	if ( null != School_DO ) {
	    if ( School_DO.isLoaded() ) {
		okToCommitSchool( School_DO );
		jobmatch.data.SchoolBDO b = 
		    jobmatch.data.SchoolBDO.createExisting(
						    School_DO );
		b.commit( dbt );
	    } else {
		// since the referenced DO is not loaded,
		// it cannot be dirty, so there is no need to commit it.
	    }
	} else {
	    if ( ! false )
		throw new RefAssertionException(
		    "Cannot commit SchoolCandidateBDO ( " + toString() +
		    " ) because School is not allowed to be null." );
	}
	jobmatch.data.GraduationDO Diploma_DO = DO.getDiploma();
	if ( null != Diploma_DO ) {
	    if ( Diploma_DO.isLoaded() ) {
		okToCommitDiploma( Diploma_DO );
		jobmatch.data.GraduationBDO b = 
		    jobmatch.data.GraduationBDO.createExisting(
						    Diploma_DO );
		b.commit( dbt );
	    } else {
		// since the referenced DO is not loaded,
		// it cannot be dirty, so there is no need to commit it.
	    }
	} else {
	    if ( ! true )
		throw new RefAssertionException(
		    "Cannot commit SchoolCandidateBDO ( " + toString() +
		    " ) because Diploma is not allowed to be null." );
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
