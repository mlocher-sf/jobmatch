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
 * /scratch/studer_repositry/dataTest/jobmatch/data/LanguageProfileBDO.java
 *-----------------------------------------------------------------------------
 */

package jobmatch.data;

import java.sql.*;
import com.lutris.appserver.server.*;
import com.lutris.appserver.server.sql.*;

import com.lutris.dods.builder.generator.query.*;

/**
 * LanguageProfileBDO contains the same set and get methods as
 * the LanguageProfileDO class.
 * Business Object (BO) classes typically need these set and get methods.
 * So by deriving a BO from a BDO, or by implementing a BO that 
 * contains a BDO, the developer of the BO is spared some work.
 *
 * @author studer
 * @version $Revision: 1.1 $
 */
public class LanguageProfileBDO implements java.io.Serializable {

    /**
     * The LanguageProfileDO object upon which the set and get methods operate.
     * This member is protected so that classes derived from LanguageProfileBDO
     * can access the underlying Data Object.
     */
    protected LanguageProfileDO DO;

    /**
     * Note:  This method is intended for use only by other BDO classes.
     * Presentation Layer classes should (theoretically) always use the
     * Business Layer (BDO) to create/access Data Layer (DO) objects.
     * The overhead for using BDO objects is small
     * (the BDO classes are fairly lightweight.)
     *
     * @return The DO object held by this BDO object.
     */
    public LanguageProfileDO getDO() { 
	return DO;
    }

    /**
     * Like the class <CODE>LanguageProfileDO</CODE>, 
     * this class acts as a factory.
     * Business Object (BO) classes typically need these set and get methods.
     * So by deriving a BO from a BDO, or by implementing a BO that 
     * contains one or more BDOs, the developer of the BO is spared some work.
     *
     * @exception Exception
     *   If an error occurs.
     */
    public static LanguageProfileBDO createVirgin() throws Exception { 
	LanguageProfileBDO bdo = new LanguageProfileBDO ();
	return bdo;
    }

    /**
     * Constructor for use by classes derived from <CODE>LanguageProfileBDO</CODE>.
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
    public LanguageProfileBDO( LanguageProfileDO DO ) { 
	this.DO = DO;
    }

    /**
     * Constructor required by <CODE>LanguageProfileBDO.create</CODE> methods.
     */
    public LanguageProfileBDO() throws Exception { 
	this.DO = LanguageProfileDO.createVirgin();
    }

    /**
     * The createExisting method is used to create a <CODE>LanguageProfileBDO</CODE>
     * from a <CODE>LanguageProfileDO</CODE> that was returned by 
     * the <CODE>LanguageProfileQuery</CODE> class.
     */
    public static LanguageProfileBDO createExisting( LanguageProfileDO DO ) { 
	LanguageProfileBDO bdo = new LanguageProfileBDO ( DO );
	return bdo;
    }

    /** 
     * The getBDOarray method performs a database query to
     * return an array of <CODE>LanguageProfileBDO</CODE> objects
     * representing all the rows in the <CODE>LanguageProfile</CODE> table.
     *
     * This method is a minimal example of using a Query class.
     * To restrict the set of objects returned,  you could
     * invoke <CODE>query.setXxx()</CODE>, where <CODE>Xxx</CODE>
     * is an Attribute of <CODE>LanguageProfileDO</CODE> which was 
     * marked as "Can be queried" in the DODS Attribute Editor.
     *
     * @exception DataObjectException
     *   If an object is not found in the database.
     */
    public static LanguageProfileBDO[] getBDOarray()
    throws DataObjectException {
        LanguageProfileDO[] DOarray = null;
        try {
            LanguageProfileQuery query = new LanguageProfileQuery();
	    // To restrict the set of objects returned,
	    // you could invoke query.setXxx(), where Xxx
	    // is an Attribute of <CODE>LanguageProfileDO</CODE> which was 
	    // marked as "Can be queried" in the DODS Attribute Editor.
            DOarray = query.getDOArray();
        } catch ( NonUniqueQueryException e1 ) {
            // LanguageProfileQuery will throw NonUniqueQueryException
            // only if query.requireUniqueInstance() is called
	    // and more than one DO was found.
        } catch ( DataObjectException e2 ) {
            // This could happen if the database server dies, etc.
            throw e2;
        }
        LanguageProfileBDO[] BDOarray = new LanguageProfileBDO[ DOarray.length ];
        for ( int i = 0; i < DOarray.length; i++ )
            BDOarray[i] = LanguageProfileBDO.createExisting( DOarray[i] );
 
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
     * HTML select lists with <CODE>LanguageProfileBDO</CODE> objects as options.
     *
     * The <CODE>getHandle()</CODE> method is used
     * to set the value for each option,
     * and the <CODE>hasMatchingHandle()<CODE>
     * methods are used to lookup the Data Object when the selection has
     * been made.
     *
     * This LanguageProfileBDO object holds a reference to a LanguageProfileDO object.
     * The id of this LanguageProfileBDO is the id of its LanguageProfileDO.
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
    * Get Language of the LanguageProfileDO
    *
    * @return Language of the LanguageProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.LanguageDO getLanguage () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getLanguage ();
   }

   
   /**
    * Set Language of the LanguageProfileDO
    *
    * @param Language of the LanguageProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setLanguage ( jobmatch.data.LanguageDO Language ) 
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
    * Get BDO-wrapped Language of the LanguageProfileDO
    *
    * @return BDO-wrapped Language of the LanguageProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.LanguageBDO getLanguageBDO () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      jobmatch.data.LanguageBDO b = jobmatch.data.LanguageBDO.createExisting(
					  DO.getLanguage () );
      return b;
   }

   /**
    * Set Language of the LanguageProfileDO
    *
    * @param BDO-wrapped Language of the LanguageProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setLanguage ( jobmatch.data.LanguageBDO Language ) 
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
		  "LanguageProfileBDO.setLanguage does not allow NULL." );
      } else {
          DO.setLanguage ( Language.getDO() );
      }
      afterAnySet();	// business actions/assertions after data assignment
   }
   

   

   /**
    * Get Profile of the LanguageProfileDO
    *
    * @return Profile of the LanguageProfileDO
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
    * Set Profile of the LanguageProfileDO
    *
    * @param Profile of the LanguageProfileDO
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
    * Get BDO-wrapped Profile of the LanguageProfileDO
    *
    * @return BDO-wrapped Profile of the LanguageProfileDO
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
    * Set Profile of the LanguageProfileDO
    *
    * @param BDO-wrapped Profile of the LanguageProfileDO
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
		  "LanguageProfileBDO.setProfile does not allow NULL." );
      } else {
          DO.setProfile ( Profile.getDO() );
      }
      afterAnySet();	// business actions/assertions after data assignment
   }
   

   

   /**
    * Get Mandatory of the LanguageProfileDO
    *
    * @return Mandatory of the LanguageProfileDO
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
    * Set Mandatory of the LanguageProfileDO
    *
    * @param Mandatory of the LanguageProfileDO
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
    * Get Diploma of the LanguageProfileDO
    *
    * @return Diploma of the LanguageProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public String getDiploma () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getDiploma ();
   }

   
   /**
    * Set Diploma of the LanguageProfileDO
    *
    * @param Diploma of the LanguageProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setDiploma ( String Diploma ) 
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
    * Get MinWritten of the LanguageProfileDO
    *
    * @return MinWritten of the LanguageProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.LanguagecapabilityDO getMinWritten () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getMinWritten ();
   }

   
   /**
    * Set MinWritten of the LanguageProfileDO
    *
    * @param MinWritten of the LanguageProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setMinWritten ( jobmatch.data.LanguagecapabilityDO MinWritten ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setMinWritten ( MinWritten );
      afterAnySet();	// business actions/assertions after data assignment
   }

   

   /**
    * Get BDO-wrapped MinWritten of the LanguageProfileDO
    *
    * @return BDO-wrapped MinWritten of the LanguageProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.LanguagecapabilityBDO getMinWrittenBDO () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      jobmatch.data.LanguagecapabilityBDO b = jobmatch.data.LanguagecapabilityBDO.createExisting(
					  DO.getMinWritten () );
      return b;
   }

   /**
    * Set MinWritten of the LanguageProfileDO
    *
    * @param BDO-wrapped MinWritten of the LanguageProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setMinWritten ( jobmatch.data.LanguagecapabilityBDO MinWritten ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      if ( null == MinWritten ) {
	  if ( false )
	      DO.setMinWritten ( null );
	  else 
	      throw new DataObjectException( 
		  "LanguageProfileBDO.setMinWritten does not allow NULL." );
      } else {
          DO.setMinWritten ( MinWritten.getDO() );
      }
      afterAnySet();	// business actions/assertions after data assignment
   }
   

   

   /**
    * Get MinSpoken of the LanguageProfileDO
    *
    * @return MinSpoken of the LanguageProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.LanguagecapabilityDO getMinSpoken () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getMinSpoken ();
   }

   
   /**
    * Set MinSpoken of the LanguageProfileDO
    *
    * @param MinSpoken of the LanguageProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setMinSpoken ( jobmatch.data.LanguagecapabilityDO MinSpoken ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setMinSpoken ( MinSpoken );
      afterAnySet();	// business actions/assertions after data assignment
   }

   

   /**
    * Get BDO-wrapped MinSpoken of the LanguageProfileDO
    *
    * @return BDO-wrapped MinSpoken of the LanguageProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.LanguagecapabilityBDO getMinSpokenBDO () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      jobmatch.data.LanguagecapabilityBDO b = jobmatch.data.LanguagecapabilityBDO.createExisting(
					  DO.getMinSpoken () );
      return b;
   }

   /**
    * Set MinSpoken of the LanguageProfileDO
    *
    * @param BDO-wrapped MinSpoken of the LanguageProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setMinSpoken ( jobmatch.data.LanguagecapabilityBDO MinSpoken ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      if ( null == MinSpoken ) {
	  if ( false )
	      DO.setMinSpoken ( null );
	  else 
	      throw new DataObjectException( 
		  "LanguageProfileBDO.setMinSpoken does not allow NULL." );
      } else {
          DO.setMinSpoken ( MinSpoken.getDO() );
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
     * for the Language data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where Language is not valid for writing to the database.
     */
    protected void okToCommitLanguage( jobmatch.data.LanguageDO member ) 
    throws RefAssertionException { }

    /**
     * A stub method for implementing pre-delete assertions 
     * for the Language data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where Language is not valid for deletion from the database.
     */
    protected void okToDeleteLanguage( jobmatch.data.LanguageDO member ) 
    throws RefAssertionException { }

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
     * for the MinWritten data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where MinWritten is not valid for writing to the database.
     */
    protected void okToCommitMinWritten( jobmatch.data.LanguagecapabilityDO member ) 
    throws RefAssertionException { }

    /**
     * A stub method for implementing pre-delete assertions 
     * for the MinWritten data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where MinWritten is not valid for deletion from the database.
     */
    protected void okToDeleteMinWritten( jobmatch.data.LanguagecapabilityDO member ) 
    throws RefAssertionException { }

    /**
     * A stub method for implementing pre-commit assertions 
     * for the MinSpoken data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where MinSpoken is not valid for writing to the database.
     */
    protected void okToCommitMinSpoken( jobmatch.data.LanguagecapabilityDO member ) 
    throws RefAssertionException { }

    /**
     * A stub method for implementing pre-delete assertions 
     * for the MinSpoken data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where MinSpoken is not valid for deletion from the database.
     */
    protected void okToDeleteMinSpoken( jobmatch.data.LanguagecapabilityDO member ) 
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
	  	jobmatch.data.LanguageDO Language_DO = DO.getLanguage();
	if ( null != Language_DO ) {
	    if ( Language_DO.isLoaded() ) {
		okToCommitLanguage( Language_DO );
		jobmatch.data.LanguageBDO b = 
		    jobmatch.data.LanguageBDO.createExisting(
						    Language_DO );
		b.commit( dbt );
	    } else {
		// since the referenced DO is not loaded,
		// it cannot be dirty, so there is no need to commit it.
	    }
	} else {
	    if ( ! false )
		throw new RefAssertionException(
		    "Cannot commit LanguageProfileBDO ( " + toString() +
		    " ) because Language is not allowed to be null." );
	}
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
		    "Cannot commit LanguageProfileBDO ( " + toString() +
		    " ) because Profile is not allowed to be null." );
	}
	jobmatch.data.LanguagecapabilityDO MinWritten_DO = DO.getMinWritten();
	if ( null != MinWritten_DO ) {
	    if ( MinWritten_DO.isLoaded() ) {
		okToCommitMinWritten( MinWritten_DO );
		jobmatch.data.LanguagecapabilityBDO b = 
		    jobmatch.data.LanguagecapabilityBDO.createExisting(
						    MinWritten_DO );
		b.commit( dbt );
	    } else {
		// since the referenced DO is not loaded,
		// it cannot be dirty, so there is no need to commit it.
	    }
	} else {
	    if ( ! false )
		throw new RefAssertionException(
		    "Cannot commit LanguageProfileBDO ( " + toString() +
		    " ) because MinWritten is not allowed to be null." );
	}
	jobmatch.data.LanguagecapabilityDO MinSpoken_DO = DO.getMinSpoken();
	if ( null != MinSpoken_DO ) {
	    if ( MinSpoken_DO.isLoaded() ) {
		okToCommitMinSpoken( MinSpoken_DO );
		jobmatch.data.LanguagecapabilityBDO b = 
		    jobmatch.data.LanguagecapabilityBDO.createExisting(
						    MinSpoken_DO );
		b.commit( dbt );
	    } else {
		// since the referenced DO is not loaded,
		// it cannot be dirty, so there is no need to commit it.
	    }
	} else {
	    if ( ! false )
		throw new RefAssertionException(
		    "Cannot commit LanguageProfileBDO ( " + toString() +
		    " ) because MinSpoken is not allowed to be null." );
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
