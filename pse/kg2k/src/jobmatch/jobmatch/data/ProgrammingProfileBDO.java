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
 * /scratch/studer_repositry/dataTest/jobmatch/data/ProgrammingProfileBDO.java
 *-----------------------------------------------------------------------------
 */

package jobmatch.data;

import java.sql.*;
import com.lutris.appserver.server.*;
import com.lutris.appserver.server.sql.*;

import com.lutris.dods.builder.generator.query.*;

/**
 * ProgrammingProfileBDO contains the same set and get methods as
 * the ProgrammingProfileDO class.
 * Business Object (BO) classes typically need these set and get methods.
 * So by deriving a BO from a BDO, or by implementing a BO that 
 * contains a BDO, the developer of the BO is spared some work.
 *
 * @author studer
 * @version $Revision: 1.3 $
 */
public class ProgrammingProfileBDO implements java.io.Serializable {

    /**
     * The ProgrammingProfileDO object upon which the set and get methods operate.
     * This member is protected so that classes derived from ProgrammingProfileBDO
     * can access the underlying Data Object.
     */
    protected ProgrammingProfileDO DO;

    /**
     * Note:  This method is intended for use only by other BDO classes.
     * Presentation Layer classes should (theoretically) always use the
     * Business Layer (BDO) to create/access Data Layer (DO) objects.
     * The overhead for using BDO objects is small
     * (the BDO classes are fairly lightweight.)
     *
     * @return The DO object held by this BDO object.
     */
    public ProgrammingProfileDO getDO() { 
	return DO;
    }

    /**
     * Like the class <CODE>ProgrammingProfileDO</CODE>, 
     * this class acts as a factory.
     * Business Object (BO) classes typically need these set and get methods.
     * So by deriving a BO from a BDO, or by implementing a BO that 
     * contains one or more BDOs, the developer of the BO is spared some work.
     *
     * @exception Exception
     *   If an error occurs.
     */
    public static ProgrammingProfileBDO createVirgin() throws Exception { 
	ProgrammingProfileBDO bdo = new ProgrammingProfileBDO ();
	return bdo;
    }

    /**
     * Constructor for use by classes derived from <CODE>ProgrammingProfileBDO</CODE>.
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
    public ProgrammingProfileBDO( ProgrammingProfileDO DO ) { 
	this.DO = DO;
    }

    /**
     * Constructor required by <CODE>ProgrammingProfileBDO.create</CODE> methods.
     */
    public ProgrammingProfileBDO() throws Exception { 
	this.DO = ProgrammingProfileDO.createVirgin();
    }

    /**
     * The createExisting method is used to create a <CODE>ProgrammingProfileBDO</CODE>
     * from a <CODE>ProgrammingProfileDO</CODE> that was returned by 
     * the <CODE>ProgrammingProfileQuery</CODE> class.
     */
    public static ProgrammingProfileBDO createExisting( ProgrammingProfileDO DO ) { 
	ProgrammingProfileBDO bdo = new ProgrammingProfileBDO ( DO );
	return bdo;
    }

    /** 
     * The getBDOarray method performs a database query to
     * return an array of <CODE>ProgrammingProfileBDO</CODE> objects
     * representing all the rows in the <CODE>ProgrammingProfile</CODE> table.
     *
     * This method is a minimal example of using a Query class.
     * To restrict the set of objects returned,  you could
     * invoke <CODE>query.setXxx()</CODE>, where <CODE>Xxx</CODE>
     * is an Attribute of <CODE>ProgrammingProfileDO</CODE> which was 
     * marked as "Can be queried" in the DODS Attribute Editor.
     *
     * @exception DataObjectException
     *   If an object is not found in the database.
     */
    public static ProgrammingProfileBDO[] getBDOarray()
    throws DataObjectException {
        ProgrammingProfileDO[] DOarray = null;
        try {
            ProgrammingProfileQuery query = new ProgrammingProfileQuery();
	    // To restrict the set of objects returned,
	    // you could invoke query.setXxx(), where Xxx
	    // is an Attribute of <CODE>ProgrammingProfileDO</CODE> which was 
	    // marked as "Can be queried" in the DODS Attribute Editor.
            DOarray = query.getDOArray();
        } catch ( NonUniqueQueryException e1 ) {
            // ProgrammingProfileQuery will throw NonUniqueQueryException
            // only if query.requireUniqueInstance() is called
	    // and more than one DO was found.
        } catch ( DataObjectException e2 ) {
            // This could happen if the database server dies, etc.
            throw e2;
        }
        ProgrammingProfileBDO[] BDOarray = new ProgrammingProfileBDO[ DOarray.length ];
        for ( int i = 0; i < DOarray.length; i++ )
            BDOarray[i] = ProgrammingProfileBDO.createExisting( DOarray[i] );
 
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
     * HTML select lists with <CODE>ProgrammingProfileBDO</CODE> objects as options.
     *
     * The <CODE>getHandle()</CODE> method is used
     * to set the value for each option,
     * and the <CODE>hasMatchingHandle()<CODE>
     * methods are used to lookup the Data Object when the selection has
     * been made.
     *
     * This ProgrammingProfileBDO object holds a reference to a ProgrammingProfileDO object.
     * The id of this ProgrammingProfileBDO is the id of its ProgrammingProfileDO.
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
    * Get LeafNumber of the ProgrammingProfileDO
    *
    * @return LeafNumber of the ProgrammingProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public int getLeafNumber () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getLeafNumber ();
   }

   
   /**
    * Set LeafNumber of the ProgrammingProfileDO
    *
    * @param LeafNumber of the ProgrammingProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setLeafNumber ( int LeafNumber ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setLeafNumber ( LeafNumber );
      afterAnySet();	// business actions/assertions after data assignment
   }


   

   /**
    * Get Profile of the ProgrammingProfileDO
    *
    * @return Profile of the ProgrammingProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.ProfileDO getProfile () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getProfile ();
   }

   
   /**
    * Set Profile of the ProgrammingProfileDO
    *
    * @param Profile of the ProgrammingProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setProfile ( jobmatch.data.ProfileDO Profile ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setProfile ( Profile );
      afterAnySet();	// business actions/assertions after data assignment
   }

   

   /**
    * Get BDO-wrapped Profile of the ProgrammingProfileDO
    *
    * @return BDO-wrapped Profile of the ProgrammingProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.ProfileBDO getProfileBDO () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      jobmatch.data.ProfileBDO b = jobmatch.data.ProfileBDO.createExisting(
					  DO.getProfile () );
      return b;
   }

   /**
    * Set Profile of the ProgrammingProfileDO
    *
    * @param BDO-wrapped Profile of the ProgrammingProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setProfile ( jobmatch.data.ProfileBDO Profile ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      if ( null == Profile ) {
	  if ( false )
	      DO.setProfile ( null );
	  else 
	      throw new DataObjectException( 
		  "ProgrammingProfileBDO.setProfile does not allow NULL." );
      } else {
          DO.setProfile ( Profile.getDO() );
      }
      afterAnySet();	// business actions/assertions after data assignment
   }
   

   

   /**
    * Get Mandatory of the ProgrammingProfileDO
    *
    * @return Mandatory of the ProgrammingProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public boolean getMandatory () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getMandatory ();
   }

   
   /**
    * Set Mandatory of the ProgrammingProfileDO
    *
    * @param Mandatory of the ProgrammingProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setMandatory ( boolean Mandatory ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setMandatory ( Mandatory );
      afterAnySet();	// business actions/assertions after data assignment
   }


   

   /**
    * Get MinCapability of the ProgrammingProfileDO
    *
    * @return MinCapability of the ProgrammingProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.CompcapabilityDO getMinCapability () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getMinCapability ();
   }

   
   /**
    * Set MinCapability of the ProgrammingProfileDO
    *
    * @param MinCapability of the ProgrammingProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setMinCapability ( jobmatch.data.CompcapabilityDO MinCapability ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setMinCapability ( MinCapability );
      afterAnySet();	// business actions/assertions after data assignment
   }

   

   /**
    * Get BDO-wrapped MinCapability of the ProgrammingProfileDO
    *
    * @return BDO-wrapped MinCapability of the ProgrammingProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.CompcapabilityBDO getMinCapabilityBDO () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      jobmatch.data.CompcapabilityBDO b = jobmatch.data.CompcapabilityBDO.createExisting(
					  DO.getMinCapability () );
      return b;
   }

   /**
    * Set MinCapability of the ProgrammingProfileDO
    *
    * @param BDO-wrapped MinCapability of the ProgrammingProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setMinCapability ( jobmatch.data.CompcapabilityBDO MinCapability ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      if ( null == MinCapability ) {
	  if ( false )
	      DO.setMinCapability ( null );
	  else 
	      throw new DataObjectException( 
		  "ProgrammingProfileBDO.setMinCapability does not allow NULL." );
      } else {
          DO.setMinCapability ( MinCapability.getDO() );
      }
      afterAnySet();	// business actions/assertions after data assignment
   }
   

   

   /**
    * Get Language of the ProgrammingProfileDO
    *
    * @return Language of the ProgrammingProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.ProgrammingDO getLanguage () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getLanguage ();
   }

   
   /**
    * Set Language of the ProgrammingProfileDO
    *
    * @param Language of the ProgrammingProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setLanguage ( jobmatch.data.ProgrammingDO Language ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setLanguage ( Language );
      afterAnySet();	// business actions/assertions after data assignment
   }

   

   /**
    * Get BDO-wrapped Language of the ProgrammingProfileDO
    *
    * @return BDO-wrapped Language of the ProgrammingProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.ProgrammingBDO getLanguageBDO () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      jobmatch.data.ProgrammingBDO b = jobmatch.data.ProgrammingBDO.createExisting(
					  DO.getLanguage () );
      return b;
   }

   /**
    * Set Language of the ProgrammingProfileDO
    *
    * @param BDO-wrapped Language of the ProgrammingProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setLanguage ( jobmatch.data.ProgrammingBDO Language ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      if ( null == Language ) {
	  if ( false )
	      DO.setLanguage ( null );
	  else 
	      throw new DataObjectException( 
		  "ProgrammingProfileBDO.setLanguage does not allow NULL." );
      } else {
          DO.setLanguage ( Language.getDO() );
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
     * for the Profile data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where Profile is not valid for writing to the database.
     */
    protected void okToCommitProfile( jobmatch.data.ProfileDO member ) 
    throws RefAssertionException { }

    /**
     * A stub method for implementing pre-delete assertions 
     * for the Profile data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where Profile is not valid for deletion from the database.
     */
    protected void okToDeleteProfile( jobmatch.data.ProfileDO member ) 
    throws RefAssertionException { }

    /**
     * A stub method for implementing pre-commit assertions 
     * for the MinCapability data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where MinCapability is not valid for writing to the database.
     */
    protected void okToCommitMinCapability( jobmatch.data.CompcapabilityDO member ) 
    throws RefAssertionException { }

    /**
     * A stub method for implementing pre-delete assertions 
     * for the MinCapability data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where MinCapability is not valid for deletion from the database.
     */
    protected void okToDeleteMinCapability( jobmatch.data.CompcapabilityDO member ) 
    throws RefAssertionException { }

    /**
     * A stub method for implementing pre-commit assertions 
     * for the Language data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where Language is not valid for writing to the database.
     */
    protected void okToCommitLanguage( jobmatch.data.ProgrammingDO member ) 
    throws RefAssertionException { }

    /**
     * A stub method for implementing pre-delete assertions 
     * for the Language data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where Language is not valid for deletion from the database.
     */
    protected void okToDeleteLanguage( jobmatch.data.ProgrammingDO member ) 
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
	  	jobmatch.data.ProfileDO Profile_DO = DO.getProfile();
	if ( null != Profile_DO ) {
	    if ( Profile_DO.isLoaded() ) {
		okToCommitProfile( Profile_DO );
		jobmatch.data.ProfileBDO b = 
		    jobmatch.data.ProfileBDO.createExisting(
						    Profile_DO );
		b.commit( dbt );
	    } else {
		// since the referenced DO is not loaded,
		// it cannot be dirty, so there is no need to commit it.
	    }
	} else {
	    if ( ! false )
		throw new RefAssertionException(
		    "Cannot commit ProgrammingProfileBDO ( " + toString() +
		    " ) because Profile is not allowed to be null." );
	}
	jobmatch.data.CompcapabilityDO MinCapability_DO = DO.getMinCapability();
	if ( null != MinCapability_DO ) {
	    if ( MinCapability_DO.isLoaded() ) {
		okToCommitMinCapability( MinCapability_DO );
		jobmatch.data.CompcapabilityBDO b = 
		    jobmatch.data.CompcapabilityBDO.createExisting(
						    MinCapability_DO );
		b.commit( dbt );
	    } else {
		// since the referenced DO is not loaded,
		// it cannot be dirty, so there is no need to commit it.
	    }
	} else {
	    if ( ! false )
		throw new RefAssertionException(
		    "Cannot commit ProgrammingProfileBDO ( " + toString() +
		    " ) because MinCapability is not allowed to be null." );
	}
	jobmatch.data.ProgrammingDO Language_DO = DO.getLanguage();
	if ( null != Language_DO ) {
	    if ( Language_DO.isLoaded() ) {
		okToCommitLanguage( Language_DO );
		jobmatch.data.ProgrammingBDO b = 
		    jobmatch.data.ProgrammingBDO.createExisting(
						    Language_DO );
		b.commit( dbt );
	    } else {
		// since the referenced DO is not loaded,
		// it cannot be dirty, so there is no need to commit it.
	    }
	} else {
	    if ( ! false )
		throw new RefAssertionException(
		    "Cannot commit ProgrammingProfileBDO ( " + toString() +
		    " ) because Language is not allowed to be null." );
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
