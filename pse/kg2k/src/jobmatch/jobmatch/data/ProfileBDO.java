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
 * /scratch/studer_repositry/dataTest/jobmatch/data/ProfileBDO.java
 *-----------------------------------------------------------------------------
 */

package jobmatch.data;

import java.sql.*;
import com.lutris.appserver.server.*;
import com.lutris.appserver.server.sql.*;

import com.lutris.dods.builder.generator.query.*;

/**
 * ProfileBDO contains the same set and get methods as
 * the ProfileDO class.
 * Business Object (BO) classes typically need these set and get methods.
 * So by deriving a BO from a BDO, or by implementing a BO that 
 * contains a BDO, the developer of the BO is spared some work.
 *
 * @author studer
 * @version $Revision: 1.5 $
 */
public class ProfileBDO implements java.io.Serializable {

    /**
     * The ProfileDO object upon which the set and get methods operate.
     * This member is protected so that classes derived from ProfileBDO
     * can access the underlying Data Object.
     */
    protected ProfileDO DO;

    /**
     * Note:  This method is intended for use only by other BDO classes.
     * Presentation Layer classes should (theoretically) always use the
     * Business Layer (BDO) to create/access Data Layer (DO) objects.
     * The overhead for using BDO objects is small
     * (the BDO classes are fairly lightweight.)
     *
     * @return The DO object held by this BDO object.
     */
    public ProfileDO getDO() { 
	return DO;
    }

    /**
     * Like the class <CODE>ProfileDO</CODE>, 
     * this class acts as a factory.
     * Business Object (BO) classes typically need these set and get methods.
     * So by deriving a BO from a BDO, or by implementing a BO that 
     * contains one or more BDOs, the developer of the BO is spared some work.
     *
     * @exception Exception
     *   If an error occurs.
     */
    public static ProfileBDO createVirgin() throws Exception { 
	ProfileBDO bdo = new ProfileBDO ();
	return bdo;
    }

    /**
     * Constructor for use by classes derived from <CODE>ProfileBDO</CODE>.
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
    public ProfileBDO( ProfileDO DO ) { 
	this.DO = DO;
    }

    /**
     * Constructor required by <CODE>ProfileBDO.create</CODE> methods.
     */
    public ProfileBDO() throws Exception { 
	this.DO = ProfileDO.createVirgin();
    }

    /**
     * The createExisting method is used to create a <CODE>ProfileBDO</CODE>
     * from a <CODE>ProfileDO</CODE> that was returned by 
     * the <CODE>ProfileQuery</CODE> class.
     */
    public static ProfileBDO createExisting( ProfileDO DO ) { 
	ProfileBDO bdo = new ProfileBDO ( DO );
	return bdo;
    }

    /** 
     * The getBDOarray method performs a database query to
     * return an array of <CODE>ProfileBDO</CODE> objects
     * representing all the rows in the <CODE>Profile</CODE> table.
     *
     * This method is a minimal example of using a Query class.
     * To restrict the set of objects returned,  you could
     * invoke <CODE>query.setXxx()</CODE>, where <CODE>Xxx</CODE>
     * is an Attribute of <CODE>ProfileDO</CODE> which was 
     * marked as "Can be queried" in the DODS Attribute Editor.
     *
     * @exception DataObjectException
     *   If an object is not found in the database.
     */
    public static ProfileBDO[] getBDOarray()
    throws DataObjectException {
        ProfileDO[] DOarray = null;
        try {
            ProfileQuery query = new ProfileQuery();
	    // To restrict the set of objects returned,
	    // you could invoke query.setXxx(), where Xxx
	    // is an Attribute of <CODE>ProfileDO</CODE> which was 
	    // marked as "Can be queried" in the DODS Attribute Editor.
            DOarray = query.getDOArray();
        } catch ( NonUniqueQueryException e1 ) {
            // ProfileQuery will throw NonUniqueQueryException
            // only if query.requireUniqueInstance() is called
	    // and more than one DO was found.
        } catch ( DataObjectException e2 ) {
            // This could happen if the database server dies, etc.
            throw e2;
        }
        ProfileBDO[] BDOarray = new ProfileBDO[ DOarray.length ];
        for ( int i = 0; i < DOarray.length; i++ )
            BDOarray[i] = ProfileBDO.createExisting( DOarray[i] );
 
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
     * HTML select lists with <CODE>ProfileBDO</CODE> objects as options.
     *
     * The <CODE>getHandle()</CODE> method is used
     * to set the value for each option,
     * and the <CODE>hasMatchingHandle()<CODE>
     * methods are used to lookup the Data Object when the selection has
     * been made.
     *
     * This ProfileBDO object holds a reference to a ProfileDO object.
     * The id of this ProfileBDO is the id of its ProfileDO.
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
    * Get Company of the ProfileDO
    *
    * @return Company of the ProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.CompanyDO getCompany () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getCompany ();
   }

   
   /**
    * Set Company of the ProfileDO
    *
    * @param Company of the ProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setCompany ( jobmatch.data.CompanyDO Company ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setCompany ( Company );
      afterAnySet();	// business actions/assertions after data assignment
   }

   

   /**
    * Get BDO-wrapped Company of the ProfileDO
    *
    * @return BDO-wrapped Company of the ProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.CompanyBDO getCompanyBDO () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      jobmatch.data.CompanyBDO b = jobmatch.data.CompanyBDO.createExisting(
					  DO.getCompany () );
      return b;
   }

   /**
    * Set Company of the ProfileDO
    *
    * @param BDO-wrapped Company of the ProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setCompany ( jobmatch.data.CompanyBDO Company ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      if ( null == Company ) {
	  if ( false )
	      DO.setCompany ( null );
	  else 
	      throw new DataObjectException( 
		  "ProfileBDO.setCompany does not allow NULL." );
      } else {
          DO.setCompany ( Company.getDO() );
      }
      afterAnySet();	// business actions/assertions after data assignment
   }
   

   

   /**
    * Get LastNotification of the ProfileDO
    *
    * @return LastNotification of the ProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public java.sql.Timestamp getLastNotification () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getLastNotification ();
   }

   
   /**
    * Set LastNotification of the ProfileDO
    *
    * @param LastNotification of the ProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setLastNotification ( java.sql.Timestamp LastNotification ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setLastNotification ( LastNotification );
      afterAnySet();	// business actions/assertions after data assignment
   }


   

   /**
    * Get MatchTree of the ProfileDO
    *
    * @return MatchTree of the ProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public byte[] getMatchTree () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getMatchTree ();
   }

   
   /**
    * Set MatchTree of the ProfileDO
    *
    * @param MatchTree of the ProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setMatchTree ( byte[] MatchTree ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setMatchTree ( MatchTree );
      afterAnySet();	// business actions/assertions after data assignment
   }


   

   /**
    * Get Name of the ProfileDO
    *
    * @return Name of the ProfileDO
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
    * Set Name of the ProfileDO
    *
    * @param Name of the ProfileDO
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
    * Get NeedsRematching of the ProfileDO
    *
    * @return NeedsRematching of the ProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public boolean getNeedsRematching () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getNeedsRematching ();
   }

   
   /**
    * Set NeedsRematching of the ProfileDO
    *
    * @param NeedsRematching of the ProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setNeedsRematching ( boolean NeedsRematching ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setNeedsRematching ( NeedsRematching );
      afterAnySet();	// business actions/assertions after data assignment
   }


   

   /**
    * Get Notify of the ProfileDO
    *
    * @return Notify of the ProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public boolean getNotify () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getNotify ();
   }

   
   /**
    * Set Notify of the ProfileDO
    *
    * @param Notify of the ProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setNotify ( boolean Notify ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setNotify ( Notify );
      afterAnySet();	// business actions/assertions after data assignment
   }


   

   /**
    * Get NotificationPeriod of the ProfileDO
    *
    * @return NotificationPeriod of the ProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public int getNotificationPeriod () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getNotificationPeriod ();
   }

   
   /**
    * Set NotificationPeriod of the ProfileDO
    *
    * @param NotificationPeriod of the ProfileDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setNotificationPeriod ( int NotificationPeriod ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setNotificationPeriod ( NotificationPeriod );
      afterAnySet();	// business actions/assertions after data assignment
   }


   
    /**
     * Get array of CandidateProfileDO objects that refer to the DO held by this BDO.
     *
     * @return array of CandidateProfileDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.CandidateProfileDO[] getCandidateProfileDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.CandidateProfileDO[] ret = null;
	try {
	    jobmatch.data.CandidateProfileQuery q = new jobmatch.data.CandidateProfileQuery();
	    q.setQueryProfile( DO );
	    ret = q.getDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.CandidateProfileDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single CandidateProfileDO object
     * that refers to the DO held by this BDO.
     *
     * @return CandidateProfileDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one CandidateProfileDO object was found.
     */
    public jobmatch.data.CandidateProfileDO getCandidateProfileDO () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.CandidateProfileQuery q = new jobmatch.data.CandidateProfileQuery();
	q.setQueryProfile( DO );
	q.requireUniqueInstance();
	return q.getNextDO();
    }

    /**
     * Get array of CandidateProfileBDO objects holding CandidateProfileDO objects
     * that refer to the DO held by this BDO.
     *
     * @return array of CandidateProfileBDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.CandidateProfileBDO[] getCandidateProfileBDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.CandidateProfileBDO[] ret = null;
	try {
	    jobmatch.data.CandidateProfileQuery q = new jobmatch.data.CandidateProfileQuery();
	    q.setQueryProfile( DO );
	    ret = q.getBDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.CandidateProfileBDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single CandidateProfileBDO object holding a CandidateProfileDO object
     * that refers to the DO held by this BDO.
     *
     * @return CandidateProfileBDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one CandidateProfileBDO object was found.
     */
    public jobmatch.data.CandidateProfileBDO getCandidateProfileBDO () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.CandidateProfileQuery q = new jobmatch.data.CandidateProfileQuery();
	q.setQueryProfile( DO );
	q.requireUniqueInstance();
	return q.getNextBDO();
    }

 
    /**
     * Add (set & commit) a CandidateProfileBDO object whose CandidateProfileDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo CandidateProfileBDO to be set to point to this BDO and committed.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addCandidateProfileBDO( jobmatch.data.CandidateProfileBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        addCandidateProfileBDO( rbdo, null );
    }
 
 
    /**
     * Add (set & commit) a CandidateProfileBDO object whose CandidateProfileDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo CandidateProfileBDO to be set to point to this BDO and committed.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addCandidateProfileBDO( jobmatch.data.CandidateProfileBDO rbdo, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        rbdo.setProfile( this.DO );
        rbdo.commit( tran );
    }

 
    /**
     * Remove (delete) a CandidateProfileBDO object whose CandidateProfileDO
     * refers to the DO held by this BDO.
     *
     * @param r CandidateProfileBDO to be deleted.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeCandidateProfileBDO( jobmatch.data.CandidateProfileBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        removeCandidateProfileBDO( rbdo, null );
    }
 
 
    /**
     * Remove (delete) a CandidateProfileBDO object whose CandidateProfileDO
     * refers to the DO held by this BDO.
     *
     * @param r CandidateProfileBDO to be deleted.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeCandidateProfileBDO( jobmatch.data.CandidateProfileBDO rbdo, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
	ProfileDO rdo = rbdo.getProfile();
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
     * From the many-to-many relationship expressed by CandidateProfileDO,
     * get array of CandidateDO objects that indirectly refer
     * to the DO held by this BDO.
     *
     * @return array of CandidateDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.CandidateDO[] getCandidateDOArray_via_CandidateProfile () 
    throws DataObjectException {
	jobmatch.data.CandidateDO[] ret = null;
	try {
	    jobmatch.data.CandidateProfileDO[] arr = getCandidateProfileDOArray();
	    ret = new jobmatch.data.CandidateDO[ arr.length ];
	    for ( int i = 0; i < arr.length; i++ ) {
		ret[ i ] = arr[ i ].getCandidate();
	    }
	} catch ( Exception e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: ", e );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.CandidateDO[ 0 ];
	}
	return ret;
    }

    /**
     * To the many-to-many relationship expressed by CandidateProfileDO,
     * add a CandidateDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param d The CandidateDO to add to the CandidateProfileDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void mapCandidate_via_CandidateProfileDO( jobmatch.data.CandidateDO d )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	mapCandidate_via_CandidateProfileDO( d, null );
    }

    /**
     * To the many-to-many relationship expressed by CandidateProfileDO,
     * add a CandidateDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param d The CandidateDO to add to the CandidateProfileDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void mapCandidate_via_CandidateProfileDO( jobmatch.data.CandidateDO d, DBTransaction tran )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	jobmatch.data.CandidateBDO b = jobmatch.data.CandidateBDO.createExisting( d );
	mapCandidate_via_CandidateProfileBDO( b, tran );
    }

    /**
     * To the many-to-many relationship expressed by CandidateProfileDO,
     * add a CandidateDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param b The CandidateBDO to add to the CandidateProfileDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void mapCandidate_via_CandidateProfileBDO( jobmatch.data.CandidateBDO b )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	mapCandidate_via_CandidateProfileBDO( b, null );
    }

    /**
     * To the many-to-many relationship expressed by CandidateProfileDO,
     * add a CandidateDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param b The CandidateBDO to add to the CandidateProfileDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void mapCandidate_via_CandidateProfileBDO( jobmatch.data.CandidateBDO b, DBTransaction tran )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	jobmatch.data.CandidateProfileBDO m = null;
	try {
	    m = jobmatch.data.CandidateProfileBDO.createVirgin();
	} catch ( Exception e ) { 
	    throw new DataObjectException( 
		"jobmatch.data.CandidateProfileBDO.createVirgin failed", e );
	}
	m.setCandidate( b );
	m.setProfile( this );
	m.commit( tran );
    }

    /**
     * From the many-to-many relationship expressed by CandidateProfileDO,
     * remove (delete) the CandidateDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param d The CandidateDO to remove from the CandidateProfileDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void unmapCandidate_via_CandidateProfileDO( jobmatch.data.CandidateDO d )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	unmapCandidate_via_CandidateProfileDO( d, null );
    }

    /**
     * From the many-to-many relationship expressed by CandidateProfileDO,
     * remove (delete) the CandidateDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param d The CandidateDO to remove from the CandidateProfileDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void unmapCandidate_via_CandidateProfileDO( jobmatch.data.CandidateDO d, DBTransaction tran )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	jobmatch.data.CandidateBDO b = jobmatch.data.CandidateBDO.createExisting( d );
	unmapCandidate_via_CandidateProfileBDO( b, tran );
    }

    /**
     * From the many-to-many relationship expressed by CandidateProfileDO,
     * remove (delete) the CandidateDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param b The CandidateBDO to remove from the CandidateProfileDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void unmapCandidate_via_CandidateProfileBDO( jobmatch.data.CandidateBDO b )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	unmapCandidate_via_CandidateProfileBDO( b, null );
    }

    /**
     * From the many-to-many relationship expressed by CandidateProfileDO,
     * remove (delete) the CandidateDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param b The CandidateBDO to remove from the CandidateProfileDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void unmapCandidate_via_CandidateProfileBDO( jobmatch.data.CandidateBDO b, DBTransaction tran )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	jobmatch.data.CandidateProfileQuery q = new jobmatch.data.CandidateProfileQuery();
	q.setQueryProfile( DO );
	q.setQueryCandidate( b.getDO() );
	q.requireUniqueInstance();
	jobmatch.data.CandidateProfileBDO m = null;
	try {
	    m = q.getNextBDO();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( "Multiple mappings for " +
		DO + " and " + b.getDO() + " in jobmatch.data.CandidateProfile table." );
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
     * for the Company data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where Company is not valid for writing to the database.
     */
    protected void okToCommitCompany( jobmatch.data.CompanyDO member ) 
    throws RefAssertionException { }

    /**
     * A stub method for implementing pre-delete assertions 
     * for the Company data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where Company is not valid for deletion from the database.
     */
    protected void okToDeleteCompany( jobmatch.data.CompanyDO member ) 
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
	    jobmatch.data.CandidateProfileBDO[] a = getCandidateProfileBDOArray();
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
	  	jobmatch.data.CompanyDO Company_DO = DO.getCompany();
	if ( null != Company_DO ) {
	    if ( Company_DO.isLoaded() ) {
		okToCommitCompany( Company_DO );
		jobmatch.data.CompanyBDO b = 
		    jobmatch.data.CompanyBDO.createExisting(
						    Company_DO );
		b.commit( dbt );
	    } else {
		// since the referenced DO is not loaded,
		// it cannot be dirty, so there is no need to commit it.
	    }
	} else {
	    if ( ! false )
		throw new RefAssertionException(
		    "Cannot commit ProfileBDO ( " + toString() +
		    " ) because Company is not allowed to be null." );
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
