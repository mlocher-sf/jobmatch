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
 * /scratch/studer_repositry/dataTest/jobmatch/data/JobwishBDO.java
 *-----------------------------------------------------------------------------
 */

package jobmatch.data;

import java.sql.*;
import com.lutris.appserver.server.*;
import com.lutris.appserver.server.sql.*;

import com.lutris.dods.builder.generator.query.*;

/**
 * JobwishBDO contains the same set and get methods as
 * the JobwishDO class.
 * Business Object (BO) classes typically need these set and get methods.
 * So by deriving a BO from a BDO, or by implementing a BO that 
 * contains a BDO, the developer of the BO is spared some work.
 *
 * @author studer
 * @version $Revision: 1.2 $
 */
public class JobwishBDO implements java.io.Serializable {

    /**
     * The JobwishDO object upon which the set and get methods operate.
     * This member is protected so that classes derived from JobwishBDO
     * can access the underlying Data Object.
     */
    protected JobwishDO DO;

    /**
     * Note:  This method is intended for use only by other BDO classes.
     * Presentation Layer classes should (theoretically) always use the
     * Business Layer (BDO) to create/access Data Layer (DO) objects.
     * The overhead for using BDO objects is small
     * (the BDO classes are fairly lightweight.)
     *
     * @return The DO object held by this BDO object.
     */
    public JobwishDO getDO() { 
	return DO;
    }

    /**
     * Like the class <CODE>JobwishDO</CODE>, 
     * this class acts as a factory.
     * Business Object (BO) classes typically need these set and get methods.
     * So by deriving a BO from a BDO, or by implementing a BO that 
     * contains one or more BDOs, the developer of the BO is spared some work.
     *
     * @exception Exception
     *   If an error occurs.
     */
    public static JobwishBDO createVirgin() throws Exception { 
	JobwishBDO bdo = new JobwishBDO ();
	return bdo;
    }

    /**
     * Constructor for use by classes derived from <CODE>JobwishBDO</CODE>.
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
    public JobwishBDO( JobwishDO DO ) { 
	this.DO = DO;
    }

    /**
     * Constructor required by <CODE>JobwishBDO.create</CODE> methods.
     */
    public JobwishBDO() throws Exception { 
	this.DO = JobwishDO.createVirgin();
    }

    /**
     * The createExisting method is used to create a <CODE>JobwishBDO</CODE>
     * from a <CODE>JobwishDO</CODE> that was returned by 
     * the <CODE>JobwishQuery</CODE> class.
     */
    public static JobwishBDO createExisting( JobwishDO DO ) { 
	JobwishBDO bdo = new JobwishBDO ( DO );
	return bdo;
    }

    /** 
     * The getBDOarray method performs a database query to
     * return an array of <CODE>JobwishBDO</CODE> objects
     * representing all the rows in the <CODE>Jobwish</CODE> table.
     *
     * This method is a minimal example of using a Query class.
     * To restrict the set of objects returned,  you could
     * invoke <CODE>query.setXxx()</CODE>, where <CODE>Xxx</CODE>
     * is an Attribute of <CODE>JobwishDO</CODE> which was 
     * marked as "Can be queried" in the DODS Attribute Editor.
     *
     * @exception DataObjectException
     *   If an object is not found in the database.
     */
    public static JobwishBDO[] getBDOarray()
    throws DataObjectException {
        JobwishDO[] DOarray = null;
        try {
            JobwishQuery query = new JobwishQuery();
	    // To restrict the set of objects returned,
	    // you could invoke query.setXxx(), where Xxx
	    // is an Attribute of <CODE>JobwishDO</CODE> which was 
	    // marked as "Can be queried" in the DODS Attribute Editor.
            DOarray = query.getDOArray();
        } catch ( NonUniqueQueryException e1 ) {
            // JobwishQuery will throw NonUniqueQueryException
            // only if query.requireUniqueInstance() is called
	    // and more than one DO was found.
        } catch ( DataObjectException e2 ) {
            // This could happen if the database server dies, etc.
            throw e2;
        }
        JobwishBDO[] BDOarray = new JobwishBDO[ DOarray.length ];
        for ( int i = 0; i < DOarray.length; i++ )
            BDOarray[i] = JobwishBDO.createExisting( DOarray[i] );
 
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
     * HTML select lists with <CODE>JobwishBDO</CODE> objects as options.
     *
     * The <CODE>getHandle()</CODE> method is used
     * to set the value for each option,
     * and the <CODE>hasMatchingHandle()<CODE>
     * methods are used to lookup the Data Object when the selection has
     * been made.
     *
     * This JobwishBDO object holds a reference to a JobwishDO object.
     * The id of this JobwishBDO is the id of its JobwishDO.
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
    * Get Pensum of the JobwishDO
    *
    * @return Pensum of the JobwishDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public int getPensum () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getPensum ();
   }

   
   /**
    * Set Pensum of the JobwishDO
    *
    * @param Pensum of the JobwishDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setPensum ( int Pensum ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setPensum ( Pensum );
      afterAnySet();	// business actions/assertions after data assignment
   }


   

   /**
    * Get Remarks of the JobwishDO
    *
    * @return Remarks of the JobwishDO
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
    * Set Remarks of the JobwishDO
    *
    * @param Remarks of the JobwishDO
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
    * Get Industry of the JobwishDO
    *
    * @return Industry of the JobwishDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.IndustryDO getIndustry () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getIndustry ();
   }

   
   /**
    * Set Industry of the JobwishDO
    *
    * @param Industry of the JobwishDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setIndustry ( jobmatch.data.IndustryDO Industry ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setIndustry ( Industry );
      afterAnySet();	// business actions/assertions after data assignment
   }

   

   /**
    * Get BDO-wrapped Industry of the JobwishDO
    *
    * @return BDO-wrapped Industry of the JobwishDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.IndustryBDO getIndustryBDO () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      jobmatch.data.IndustryBDO b = jobmatch.data.IndustryBDO.createExisting(
					  DO.getIndustry () );
      return b;
   }

   /**
    * Set Industry of the JobwishDO
    *
    * @param BDO-wrapped Industry of the JobwishDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setIndustry ( jobmatch.data.IndustryBDO Industry ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      if ( null == Industry ) {
	  if ( false )
	      DO.setIndustry ( null );
	  else 
	      throw new DataObjectException( 
		  "JobwishBDO.setIndustry does not allow NULL." );
      } else {
          DO.setIndustry ( Industry.getDO() );
      }
      afterAnySet();	// business actions/assertions after data assignment
   }
   

   

   /**
    * Get Area of the JobwishDO
    *
    * @return Area of the JobwishDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.AreaDO getArea () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getArea ();
   }

   
   /**
    * Set Area of the JobwishDO
    *
    * @param Area of the JobwishDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setArea ( jobmatch.data.AreaDO Area ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setArea ( Area );
      afterAnySet();	// business actions/assertions after data assignment
   }

   

   /**
    * Get BDO-wrapped Area of the JobwishDO
    *
    * @return BDO-wrapped Area of the JobwishDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.AreaBDO getAreaBDO () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      jobmatch.data.AreaBDO b = jobmatch.data.AreaBDO.createExisting(
					  DO.getArea () );
      return b;
   }

   /**
    * Set Area of the JobwishDO
    *
    * @param BDO-wrapped Area of the JobwishDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setArea ( jobmatch.data.AreaBDO Area ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      if ( null == Area ) {
	  if ( false )
	      DO.setArea ( null );
	  else 
	      throw new DataObjectException( 
		  "JobwishBDO.setArea does not allow NULL." );
      } else {
          DO.setArea ( Area.getDO() );
      }
      afterAnySet();	// business actions/assertions after data assignment
   }
   

   

   /**
    * Get Function of the JobwishDO
    *
    * @return Function of the JobwishDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public String getFunction () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getFunction ();
   }

   
   /**
    * Set Function of the JobwishDO
    *
    * @param Function of the JobwishDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setFunction ( String Function ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setFunction ( Function );
      afterAnySet();	// business actions/assertions after data assignment
   }


   

   /**
    * Get Candidate of the JobwishDO
    *
    * @return Candidate of the JobwishDO
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
    * Set Candidate of the JobwishDO
    *
    * @param Candidate of the JobwishDO
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
    * Get BDO-wrapped Candidate of the JobwishDO
    *
    * @return BDO-wrapped Candidate of the JobwishDO
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
    * Set Candidate of the JobwishDO
    *
    * @param BDO-wrapped Candidate of the JobwishDO
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
		  "JobwishBDO.setCandidate does not allow NULL." );
      } else {
          DO.setCandidate ( Candidate.getDO() );
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
     * for the Industry data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where Industry is not valid for writing to the database.
     */
    protected void okToCommitIndustry( jobmatch.data.IndustryDO member ) 
    throws RefAssertionException { }

    /**
     * A stub method for implementing pre-delete assertions 
     * for the Industry data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where Industry is not valid for deletion from the database.
     */
    protected void okToDeleteIndustry( jobmatch.data.IndustryDO member ) 
    throws RefAssertionException { }

    /**
     * A stub method for implementing pre-commit assertions 
     * for the Area data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where Area is not valid for writing to the database.
     */
    protected void okToCommitArea( jobmatch.data.AreaDO member ) 
    throws RefAssertionException { }

    /**
     * A stub method for implementing pre-delete assertions 
     * for the Area data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where Area is not valid for deletion from the database.
     */
    protected void okToDeleteArea( jobmatch.data.AreaDO member ) 
    throws RefAssertionException { }

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
	  	jobmatch.data.IndustryDO Industry_DO = DO.getIndustry();
	if ( null != Industry_DO ) {
	    if ( Industry_DO.isLoaded() ) {
		okToCommitIndustry( Industry_DO );
		jobmatch.data.IndustryBDO b = 
		    jobmatch.data.IndustryBDO.createExisting(
						    Industry_DO );
		b.commit( dbt );
	    } else {
		// since the referenced DO is not loaded,
		// it cannot be dirty, so there is no need to commit it.
	    }
	} else {
	    if ( ! false )
		throw new RefAssertionException(
		    "Cannot commit JobwishBDO ( " + toString() +
		    " ) because Industry is not allowed to be null." );
	}
	jobmatch.data.AreaDO Area_DO = DO.getArea();
	if ( null != Area_DO ) {
	    if ( Area_DO.isLoaded() ) {
		okToCommitArea( Area_DO );
		jobmatch.data.AreaBDO b = 
		    jobmatch.data.AreaBDO.createExisting(
						    Area_DO );
		b.commit( dbt );
	    } else {
		// since the referenced DO is not loaded,
		// it cannot be dirty, so there is no need to commit it.
	    }
	} else {
	    if ( ! false )
		throw new RefAssertionException(
		    "Cannot commit JobwishBDO ( " + toString() +
		    " ) because Area is not allowed to be null." );
	}
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
		    "Cannot commit JobwishBDO ( " + toString() +
		    " ) because Candidate is not allowed to be null." );
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
