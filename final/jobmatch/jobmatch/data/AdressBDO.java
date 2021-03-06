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
 * /scratch/studer_repositry/dataTest/jobmatch/data/AdressBDO.java
 *-----------------------------------------------------------------------------
 */

package jobmatch.data;

import java.sql.*;
import com.lutris.appserver.server.*;
import com.lutris.appserver.server.sql.*;

import com.lutris.dods.builder.generator.query.*;

/**
 * AdressBDO contains the same set and get methods as
 * the AdressDO class.
 * Business Object (BO) classes typically need these set and get methods.
 * So by deriving a BO from a BDO, or by implementing a BO that 
 * contains a BDO, the developer of the BO is spared some work.
 *
 * @author studer
 * @version $Revision: 1.1 $
 */
public class AdressBDO implements java.io.Serializable {

    /**
     * The AdressDO object upon which the set and get methods operate.
     * This member is protected so that classes derived from AdressBDO
     * can access the underlying Data Object.
     */
    protected AdressDO DO;

    /**
     * Note:  This method is intended for use only by other BDO classes.
     * Presentation Layer classes should (theoretically) always use the
     * Business Layer (BDO) to create/access Data Layer (DO) objects.
     * The overhead for using BDO objects is small
     * (the BDO classes are fairly lightweight.)
     *
     * @return The DO object held by this BDO object.
     */
    public AdressDO getDO() { 
	return DO;
    }

    /**
     * Like the class <CODE>AdressDO</CODE>, 
     * this class acts as a factory.
     * Business Object (BO) classes typically need these set and get methods.
     * So by deriving a BO from a BDO, or by implementing a BO that 
     * contains one or more BDOs, the developer of the BO is spared some work.
     *
     * @exception Exception
     *   If an error occurs.
     */
    public static AdressBDO createVirgin() throws Exception { 
	AdressBDO bdo = new AdressBDO ();
	return bdo;
    }

    /**
     * Constructor for use by classes derived from <CODE>AdressBDO</CODE>.
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
    public AdressBDO( AdressDO DO ) { 
	this.DO = DO;
    }

    /**
     * Constructor required by <CODE>AdressBDO.create</CODE> methods.
     */
    public AdressBDO() throws Exception { 
	this.DO = AdressDO.createVirgin();
    }

    /**
     * The createExisting method is used to create a <CODE>AdressBDO</CODE>
     * from a <CODE>AdressDO</CODE> that was returned by 
     * the <CODE>AdressQuery</CODE> class.
     */
    public static AdressBDO createExisting( AdressDO DO ) { 
	AdressBDO bdo = new AdressBDO ( DO );
	return bdo;
    }

    /** 
     * The getBDOarray method performs a database query to
     * return an array of <CODE>AdressBDO</CODE> objects
     * representing all the rows in the <CODE>Adress</CODE> table.
     *
     * This method is a minimal example of using a Query class.
     * To restrict the set of objects returned,  you could
     * invoke <CODE>query.setXxx()</CODE>, where <CODE>Xxx</CODE>
     * is an Attribute of <CODE>AdressDO</CODE> which was 
     * marked as "Can be queried" in the DODS Attribute Editor.
     *
     * @exception DataObjectException
     *   If an object is not found in the database.
     */
    public static AdressBDO[] getBDOarray()
    throws DataObjectException {
        AdressDO[] DOarray = null;
        try {
            AdressQuery query = new AdressQuery();
	    // To restrict the set of objects returned,
	    // you could invoke query.setXxx(), where Xxx
	    // is an Attribute of <CODE>AdressDO</CODE> which was 
	    // marked as "Can be queried" in the DODS Attribute Editor.
            DOarray = query.getDOArray();
        } catch ( NonUniqueQueryException e1 ) {
            // AdressQuery will throw NonUniqueQueryException
            // only if query.requireUniqueInstance() is called
	    // and more than one DO was found.
        } catch ( DataObjectException e2 ) {
            // This could happen if the database server dies, etc.
            throw e2;
        }
        AdressBDO[] BDOarray = new AdressBDO[ DOarray.length ];
        for ( int i = 0; i < DOarray.length; i++ )
            BDOarray[i] = AdressBDO.createExisting( DOarray[i] );
 
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
     * HTML select lists with <CODE>AdressBDO</CODE> objects as options.
     *
     * The <CODE>getHandle()</CODE> method is used
     * to set the value for each option,
     * and the <CODE>hasMatchingHandle()<CODE>
     * methods are used to lookup the Data Object when the selection has
     * been made.
     *
     * This AdressBDO object holds a reference to a AdressDO object.
     * The id of this AdressBDO is the id of its AdressDO.
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
    * Get Street of the AdressDO
    *
    * @return Street of the AdressDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public String getStreet () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getStreet ();
   }

   
   /**
    * Set Street of the AdressDO
    *
    * @param Street of the AdressDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setStreet ( String Street ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setStreet ( Street );
      afterAnySet();	// business actions/assertions after data assignment
   }


   

   /**
    * Get HouseNumber of the AdressDO
    *
    * @return HouseNumber of the AdressDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public String getHouseNumber () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getHouseNumber ();
   }

   
   /**
    * Set HouseNumber of the AdressDO
    *
    * @param HouseNumber of the AdressDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setHouseNumber ( String HouseNumber ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setHouseNumber ( HouseNumber );
      afterAnySet();	// business actions/assertions after data assignment
   }


   

   /**
    * Get ZIP of the AdressDO
    *
    * @return ZIP of the AdressDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public String getZIP () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getZIP ();
   }

   
   /**
    * Set ZIP of the AdressDO
    *
    * @param ZIP of the AdressDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setZIP ( String ZIP ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setZIP ( ZIP );
      afterAnySet();	// business actions/assertions after data assignment
   }


   

   /**
    * Get City of the AdressDO
    *
    * @return City of the AdressDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public String getCity () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getCity ();
   }

   
   /**
    * Set City of the AdressDO
    *
    * @param City of the AdressDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setCity ( String City ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setCity ( City );
      afterAnySet();	// business actions/assertions after data assignment
   }


   

   /**
    * Get Country of the AdressDO
    *
    * @return Country of the AdressDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.CountryDO getCountry () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getCountry ();
   }

   
   /**
    * Set Country of the AdressDO
    *
    * @param Country of the AdressDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setCountry ( jobmatch.data.CountryDO Country ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setCountry ( Country );
      afterAnySet();	// business actions/assertions after data assignment
   }

   

   /**
    * Get BDO-wrapped Country of the AdressDO
    *
    * @return BDO-wrapped Country of the AdressDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.CountryBDO getCountryBDO () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      jobmatch.data.CountryBDO b = jobmatch.data.CountryBDO.createExisting(
					  DO.getCountry () );
      return b;
   }

   /**
    * Set Country of the AdressDO
    *
    * @param BDO-wrapped Country of the AdressDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setCountry ( jobmatch.data.CountryBDO Country ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      if ( null == Country ) {
	  if ( true )
	      DO.setCountry ( null );
	  else 
	      throw new DataObjectException( 
		  "AdressBDO.setCountry does not allow NULL." );
      } else {
          DO.setCountry ( Country.getDO() );
      }
      afterAnySet();	// business actions/assertions after data assignment
   }
   

   
    /**
     * Get array of CandidateDO objects that refer to the DO held by this BDO.
     *
     * @return array of CandidateDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.CandidateDO[] getCandidateDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.CandidateDO[] ret = null;
	try {
	    jobmatch.data.CandidateQuery q = new jobmatch.data.CandidateQuery();
	    q.setQueryAdress( DO );
	    ret = q.getDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.CandidateDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single CandidateDO object
     * that refers to the DO held by this BDO.
     *
     * @return CandidateDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one CandidateDO object was found.
     */
    public jobmatch.data.CandidateDO getCandidateDO () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.CandidateQuery q = new jobmatch.data.CandidateQuery();
	q.setQueryAdress( DO );
	q.requireUniqueInstance();
	return q.getNextDO();
    }

    /**
     * Get array of CandidateBDO objects holding CandidateDO objects
     * that refer to the DO held by this BDO.
     *
     * @return array of CandidateBDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.CandidateBDO[] getCandidateBDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.CandidateBDO[] ret = null;
	try {
	    jobmatch.data.CandidateQuery q = new jobmatch.data.CandidateQuery();
	    q.setQueryAdress( DO );
	    ret = q.getBDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.CandidateBDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single CandidateBDO object holding a CandidateDO object
     * that refers to the DO held by this BDO.
     *
     * @return CandidateBDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one CandidateBDO object was found.
     */
    public jobmatch.data.CandidateBDO getCandidateBDO () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.CandidateQuery q = new jobmatch.data.CandidateQuery();
	q.setQueryAdress( DO );
	q.requireUniqueInstance();
	return q.getNextBDO();
    }

 
    /**
     * Add (set & commit) a CandidateBDO object whose CandidateDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo CandidateBDO to be set to point to this BDO and committed.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addCandidateBDO( jobmatch.data.CandidateBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        addCandidateBDO( rbdo, null );
    }
 
 
    /**
     * Add (set & commit) a CandidateBDO object whose CandidateDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo CandidateBDO to be set to point to this BDO and committed.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addCandidateBDO( jobmatch.data.CandidateBDO rbdo, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        rbdo.setAdress( this.DO );
        rbdo.commit( tran );
    }

 
    /**
     * Remove (delete) a CandidateBDO object whose CandidateDO
     * refers to the DO held by this BDO.
     *
     * @param r CandidateBDO to be deleted.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeCandidateBDO( jobmatch.data.CandidateBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        removeCandidateBDO( rbdo, null );
    }
 
 
    /**
     * Remove (delete) a CandidateBDO object whose CandidateDO
     * refers to the DO held by this BDO.
     *
     * @param r CandidateBDO to be deleted.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeCandidateBDO( jobmatch.data.CandidateBDO rbdo, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
	AdressDO rdo = rbdo.getAdress();
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
     * Get array of CompanyDO objects that refer to the DO held by this BDO.
     *
     * @return array of CompanyDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.CompanyDO[] getCompanyDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.CompanyDO[] ret = null;
	try {
	    jobmatch.data.CompanyQuery q = new jobmatch.data.CompanyQuery();
	    q.setQueryAdress( DO );
	    ret = q.getDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.CompanyDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single CompanyDO object
     * that refers to the DO held by this BDO.
     *
     * @return CompanyDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one CompanyDO object was found.
     */
    public jobmatch.data.CompanyDO getCompanyDO () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.CompanyQuery q = new jobmatch.data.CompanyQuery();
	q.setQueryAdress( DO );
	q.requireUniqueInstance();
	return q.getNextDO();
    }

    /**
     * Get array of CompanyBDO objects holding CompanyDO objects
     * that refer to the DO held by this BDO.
     *
     * @return array of CompanyBDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.CompanyBDO[] getCompanyBDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.CompanyBDO[] ret = null;
	try {
	    jobmatch.data.CompanyQuery q = new jobmatch.data.CompanyQuery();
	    q.setQueryAdress( DO );
	    ret = q.getBDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.CompanyBDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single CompanyBDO object holding a CompanyDO object
     * that refers to the DO held by this BDO.
     *
     * @return CompanyBDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one CompanyBDO object was found.
     */
    public jobmatch.data.CompanyBDO getCompanyBDO () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.CompanyQuery q = new jobmatch.data.CompanyQuery();
	q.setQueryAdress( DO );
	q.requireUniqueInstance();
	return q.getNextBDO();
    }

 
    /**
     * Add (set & commit) a CompanyBDO object whose CompanyDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo CompanyBDO to be set to point to this BDO and committed.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addCompanyBDO( jobmatch.data.CompanyBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        addCompanyBDO( rbdo, null );
    }
 
 
    /**
     * Add (set & commit) a CompanyBDO object whose CompanyDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo CompanyBDO to be set to point to this BDO and committed.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addCompanyBDO( jobmatch.data.CompanyBDO rbdo, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        rbdo.setAdress( this.DO );
        rbdo.commit( tran );
    }

 
    /**
     * Remove (delete) a CompanyBDO object whose CompanyDO
     * refers to the DO held by this BDO.
     *
     * @param r CompanyBDO to be deleted.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeCompanyBDO( jobmatch.data.CompanyBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        removeCompanyBDO( rbdo, null );
    }
 
 
    /**
     * Remove (delete) a CompanyBDO object whose CompanyDO
     * refers to the DO held by this BDO.
     *
     * @param r CompanyBDO to be deleted.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeCompanyBDO( jobmatch.data.CompanyBDO rbdo, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
	AdressDO rdo = rbdo.getAdress();
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
     * Get array of EmployerDO objects that refer to the DO held by this BDO.
     *
     * @return array of EmployerDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.EmployerDO[] getEmployerDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.EmployerDO[] ret = null;
	try {
	    jobmatch.data.EmployerQuery q = new jobmatch.data.EmployerQuery();
	    q.setQueryAdress( DO );
	    ret = q.getDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.EmployerDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single EmployerDO object
     * that refers to the DO held by this BDO.
     *
     * @return EmployerDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one EmployerDO object was found.
     */
    public jobmatch.data.EmployerDO getEmployerDO () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.EmployerQuery q = new jobmatch.data.EmployerQuery();
	q.setQueryAdress( DO );
	q.requireUniqueInstance();
	return q.getNextDO();
    }

    /**
     * Get array of EmployerBDO objects holding EmployerDO objects
     * that refer to the DO held by this BDO.
     *
     * @return array of EmployerBDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.EmployerBDO[] getEmployerBDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.EmployerBDO[] ret = null;
	try {
	    jobmatch.data.EmployerQuery q = new jobmatch.data.EmployerQuery();
	    q.setQueryAdress( DO );
	    ret = q.getBDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.EmployerBDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single EmployerBDO object holding a EmployerDO object
     * that refers to the DO held by this BDO.
     *
     * @return EmployerBDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one EmployerBDO object was found.
     */
    public jobmatch.data.EmployerBDO getEmployerBDO () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.EmployerQuery q = new jobmatch.data.EmployerQuery();
	q.setQueryAdress( DO );
	q.requireUniqueInstance();
	return q.getNextBDO();
    }

 
    /**
     * Add (set & commit) a EmployerBDO object whose EmployerDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo EmployerBDO to be set to point to this BDO and committed.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addEmployerBDO( jobmatch.data.EmployerBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        addEmployerBDO( rbdo, null );
    }
 
 
    /**
     * Add (set & commit) a EmployerBDO object whose EmployerDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo EmployerBDO to be set to point to this BDO and committed.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addEmployerBDO( jobmatch.data.EmployerBDO rbdo, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        rbdo.setAdress( this.DO );
        rbdo.commit( tran );
    }

 
    /**
     * Remove (delete) a EmployerBDO object whose EmployerDO
     * refers to the DO held by this BDO.
     *
     * @param r EmployerBDO to be deleted.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeEmployerBDO( jobmatch.data.EmployerBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        removeEmployerBDO( rbdo, null );
    }
 
 
    /**
     * Remove (delete) a EmployerBDO object whose EmployerDO
     * refers to the DO held by this BDO.
     *
     * @param r EmployerBDO to be deleted.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeEmployerBDO( jobmatch.data.EmployerBDO rbdo, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
	AdressDO rdo = rbdo.getAdress();
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
     * Get array of PersonDO objects that refer to the DO held by this BDO.
     *
     * @return array of PersonDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.PersonDO[] getPersonDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.PersonDO[] ret = null;
	try {
	    jobmatch.data.PersonQuery q = new jobmatch.data.PersonQuery();
	    q.setQueryAdress( DO );
	    ret = q.getDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.PersonDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single PersonDO object
     * that refers to the DO held by this BDO.
     *
     * @return PersonDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one PersonDO object was found.
     */
    public jobmatch.data.PersonDO getPersonDO () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.PersonQuery q = new jobmatch.data.PersonQuery();
	q.setQueryAdress( DO );
	q.requireUniqueInstance();
	return q.getNextDO();
    }

    /**
     * Get array of PersonBDO objects holding PersonDO objects
     * that refer to the DO held by this BDO.
     *
     * @return array of PersonBDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.PersonBDO[] getPersonBDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.PersonBDO[] ret = null;
	try {
	    jobmatch.data.PersonQuery q = new jobmatch.data.PersonQuery();
	    q.setQueryAdress( DO );
	    ret = q.getBDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.PersonBDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single PersonBDO object holding a PersonDO object
     * that refers to the DO held by this BDO.
     *
     * @return PersonBDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one PersonBDO object was found.
     */
    public jobmatch.data.PersonBDO getPersonBDO () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.PersonQuery q = new jobmatch.data.PersonQuery();
	q.setQueryAdress( DO );
	q.requireUniqueInstance();
	return q.getNextBDO();
    }

 
    /**
     * Add (set & commit) a PersonBDO object whose PersonDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo PersonBDO to be set to point to this BDO and committed.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addPersonBDO( jobmatch.data.PersonBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        addPersonBDO( rbdo, null );
    }
 
 
    /**
     * Add (set & commit) a PersonBDO object whose PersonDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo PersonBDO to be set to point to this BDO and committed.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addPersonBDO( jobmatch.data.PersonBDO rbdo, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        rbdo.setAdress( this.DO );
        rbdo.commit( tran );
    }

 
    /**
     * Remove (delete) a PersonBDO object whose PersonDO
     * refers to the DO held by this BDO.
     *
     * @param r PersonBDO to be deleted.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removePersonBDO( jobmatch.data.PersonBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        removePersonBDO( rbdo, null );
    }
 
 
    /**
     * Remove (delete) a PersonBDO object whose PersonDO
     * refers to the DO held by this BDO.
     *
     * @param r PersonBDO to be deleted.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removePersonBDO( jobmatch.data.PersonBDO rbdo, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
	AdressDO rdo = rbdo.getAdress();
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
     * From the many-to-many relationship expressed by CompanyDO,
     * get array of IndustryDO objects that indirectly refer
     * to the DO held by this BDO.
     *
     * @return array of IndustryDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.IndustryDO[] getIndustryDOArray_via_Company () 
    throws DataObjectException {
	jobmatch.data.IndustryDO[] ret = null;
	try {
	    jobmatch.data.CompanyDO[] arr = getCompanyDOArray();
	    ret = new jobmatch.data.IndustryDO[ arr.length ];
	    for ( int i = 0; i < arr.length; i++ ) {
		ret[ i ] = arr[ i ].getIndustry();
	    }
	} catch ( Exception e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: ", e );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.IndustryDO[ 0 ];
	}
	return ret;
    }

    /**
     * To the many-to-many relationship expressed by CompanyDO,
     * add a IndustryDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param d The IndustryDO to add to the CompanyDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void mapIndustry_via_CompanyDO( jobmatch.data.IndustryDO d )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	mapIndustry_via_CompanyDO( d, null );
    }

    /**
     * To the many-to-many relationship expressed by CompanyDO,
     * add a IndustryDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param d The IndustryDO to add to the CompanyDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void mapIndustry_via_CompanyDO( jobmatch.data.IndustryDO d, DBTransaction tran )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	jobmatch.data.IndustryBDO b = jobmatch.data.IndustryBDO.createExisting( d );
	mapIndustry_via_CompanyBDO( b, tran );
    }

    /**
     * To the many-to-many relationship expressed by CompanyDO,
     * add a IndustryDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param b The IndustryBDO to add to the CompanyDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void mapIndustry_via_CompanyBDO( jobmatch.data.IndustryBDO b )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	mapIndustry_via_CompanyBDO( b, null );
    }

    /**
     * To the many-to-many relationship expressed by CompanyDO,
     * add a IndustryDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param b The IndustryBDO to add to the CompanyDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void mapIndustry_via_CompanyBDO( jobmatch.data.IndustryBDO b, DBTransaction tran )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	jobmatch.data.CompanyBDO m = null;
	try {
	    m = jobmatch.data.CompanyBDO.createVirgin();
	} catch ( Exception e ) { 
	    throw new DataObjectException( 
		"jobmatch.data.CompanyBDO.createVirgin failed", e );
	}
	m.setIndustry( b );
	m.setAdress( this );
	m.commit( tran );
    }

    /**
     * From the many-to-many relationship expressed by CompanyDO,
     * remove (delete) the IndustryDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param d The IndustryDO to remove from the CompanyDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void unmapIndustry_via_CompanyDO( jobmatch.data.IndustryDO d )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	unmapIndustry_via_CompanyDO( d, null );
    }

    /**
     * From the many-to-many relationship expressed by CompanyDO,
     * remove (delete) the IndustryDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param d The IndustryDO to remove from the CompanyDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void unmapIndustry_via_CompanyDO( jobmatch.data.IndustryDO d, DBTransaction tran )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	jobmatch.data.IndustryBDO b = jobmatch.data.IndustryBDO.createExisting( d );
	unmapIndustry_via_CompanyBDO( b, tran );
    }

    /**
     * From the many-to-many relationship expressed by CompanyDO,
     * remove (delete) the IndustryDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param b The IndustryBDO to remove from the CompanyDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void unmapIndustry_via_CompanyBDO( jobmatch.data.IndustryBDO b )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	unmapIndustry_via_CompanyBDO( b, null );
    }

    /**
     * From the many-to-many relationship expressed by CompanyDO,
     * remove (delete) the IndustryDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param b The IndustryBDO to remove from the CompanyDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void unmapIndustry_via_CompanyBDO( jobmatch.data.IndustryBDO b, DBTransaction tran )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	jobmatch.data.CompanyQuery q = new jobmatch.data.CompanyQuery();
	q.setQueryAdress( DO );
	q.setQueryIndustry( b.getDO() );
	q.requireUniqueInstance();
	jobmatch.data.CompanyBDO m = null;
	try {
	    m = q.getNextBDO();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( "Multiple mappings for " +
		DO + " and " + b.getDO() + " in jobmatch.data.Company table." );
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
     * for the Country data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where Country is not valid for writing to the database.
     */
    protected void okToCommitCountry( jobmatch.data.CountryDO member ) 
    throws RefAssertionException { }

    /**
     * A stub method for implementing pre-delete assertions 
     * for the Country data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where Country is not valid for deletion from the database.
     */
    protected void okToDeleteCountry( jobmatch.data.CountryDO member ) 
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
	    jobmatch.data.CandidateBDO[] a = getCandidateBDOArray();
	    for ( int i = 0; i < a.length; i++ ) {
		a[ i ].delete( dbt );
	    }
	}
	
	
	{
	    // perform cascading delete on referring table
	    jobmatch.data.CompanyBDO[] a = getCompanyBDOArray();
	    for ( int i = 0; i < a.length; i++ ) {
		a[ i ].delete( dbt );
	    }
	}
	
	
	{
	    // perform cascading delete on referring table
	    jobmatch.data.EmployerBDO[] a = getEmployerBDOArray();
	    for ( int i = 0; i < a.length; i++ ) {
		a[ i ].delete( dbt );
	    }
	}
	
	
	{
	    // perform cascading delete on referring table
	    jobmatch.data.PersonBDO[] a = getPersonBDOArray();
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
	  	jobmatch.data.CountryDO Country_DO = DO.getCountry();
	if ( null != Country_DO ) {
	    if ( Country_DO.isLoaded() ) {
		okToCommitCountry( Country_DO );
		jobmatch.data.CountryBDO b = 
		    jobmatch.data.CountryBDO.createExisting(
						    Country_DO );
		b.commit( dbt );
	    } else {
		// since the referenced DO is not loaded,
		// it cannot be dirty, so there is no need to commit it.
	    }
	} else {
	    if ( ! true )
		throw new RefAssertionException(
		    "Cannot commit AdressBDO ( " + toString() +
		    " ) because Country is not allowed to be null." );
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
