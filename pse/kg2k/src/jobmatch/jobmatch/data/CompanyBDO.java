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
 * /scratch/studer_repositry/dataTest/jobmatch/data/CompanyBDO.java
 *-----------------------------------------------------------------------------
 */

package jobmatch.data;

import java.sql.*;
import com.lutris.appserver.server.*;
import com.lutris.appserver.server.sql.*;

import com.lutris.dods.builder.generator.query.*;

/**
 * CompanyBDO contains the same set and get methods as
 * the CompanyDO class.
 * Business Object (BO) classes typically need these set and get methods.
 * So by deriving a BO from a BDO, or by implementing a BO that 
 * contains a BDO, the developer of the BO is spared some work.
 *
 * @author studer
 * @version $Revision: 1.5 $
 */
public class CompanyBDO implements java.io.Serializable {

    /**
     * The CompanyDO object upon which the set and get methods operate.
     * This member is protected so that classes derived from CompanyBDO
     * can access the underlying Data Object.
     */
    protected CompanyDO DO;

    /**
     * Note:  This method is intended for use only by other BDO classes.
     * Presentation Layer classes should (theoretically) always use the
     * Business Layer (BDO) to create/access Data Layer (DO) objects.
     * The overhead for using BDO objects is small
     * (the BDO classes are fairly lightweight.)
     *
     * @return The DO object held by this BDO object.
     */
    public CompanyDO getDO() { 
	return DO;
    }

    /**
     * Like the class <CODE>CompanyDO</CODE>, 
     * this class acts as a factory.
     * Business Object (BO) classes typically need these set and get methods.
     * So by deriving a BO from a BDO, or by implementing a BO that 
     * contains one or more BDOs, the developer of the BO is spared some work.
     *
     * @exception Exception
     *   If an error occurs.
     */
    public static CompanyBDO createVirgin() throws Exception { 
	CompanyBDO bdo = new CompanyBDO ();
	return bdo;
    }

    /**
     * Constructor for use by classes derived from <CODE>CompanyBDO</CODE>.
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
    public CompanyBDO( CompanyDO DO ) { 
	this.DO = DO;
    }

    /**
     * Constructor required by <CODE>CompanyBDO.create</CODE> methods.
     */
    public CompanyBDO() throws Exception { 
	this.DO = CompanyDO.createVirgin();
    }

    /**
     * The createExisting method is used to create a <CODE>CompanyBDO</CODE>
     * from a <CODE>CompanyDO</CODE> that was returned by 
     * the <CODE>CompanyQuery</CODE> class.
     */
    public static CompanyBDO createExisting( CompanyDO DO ) { 
	CompanyBDO bdo = new CompanyBDO ( DO );
	return bdo;
    }

    /** 
     * The getBDOarray method performs a database query to
     * return an array of <CODE>CompanyBDO</CODE> objects
     * representing all the rows in the <CODE>Company</CODE> table.
     *
     * This method is a minimal example of using a Query class.
     * To restrict the set of objects returned,  you could
     * invoke <CODE>query.setXxx()</CODE>, where <CODE>Xxx</CODE>
     * is an Attribute of <CODE>CompanyDO</CODE> which was 
     * marked as "Can be queried" in the DODS Attribute Editor.
     *
     * @exception DataObjectException
     *   If an object is not found in the database.
     */
    public static CompanyBDO[] getBDOarray()
    throws DataObjectException {
        CompanyDO[] DOarray = null;
        try {
            CompanyQuery query = new CompanyQuery();
	    // To restrict the set of objects returned,
	    // you could invoke query.setXxx(), where Xxx
	    // is an Attribute of <CODE>CompanyDO</CODE> which was 
	    // marked as "Can be queried" in the DODS Attribute Editor.
            DOarray = query.getDOArray();
        } catch ( NonUniqueQueryException e1 ) {
            // CompanyQuery will throw NonUniqueQueryException
            // only if query.requireUniqueInstance() is called
	    // and more than one DO was found.
        } catch ( DataObjectException e2 ) {
            // This could happen if the database server dies, etc.
            throw e2;
        }
        CompanyBDO[] BDOarray = new CompanyBDO[ DOarray.length ];
        for ( int i = 0; i < DOarray.length; i++ )
            BDOarray[i] = CompanyBDO.createExisting( DOarray[i] );
 
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
     * HTML select lists with <CODE>CompanyBDO</CODE> objects as options.
     *
     * The <CODE>getHandle()</CODE> method is used
     * to set the value for each option,
     * and the <CODE>hasMatchingHandle()<CODE>
     * methods are used to lookup the Data Object when the selection has
     * been made.
     *
     * This CompanyBDO object holds a reference to a CompanyDO object.
     * The id of this CompanyBDO is the id of its CompanyDO.
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
    * Get Active of the CompanyDO
    *
    * @return Active of the CompanyDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public boolean getActive () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getActive ();
   }

   
   /**
    * Set Active of the CompanyDO
    *
    * @param Active of the CompanyDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setActive ( boolean Active ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setActive ( Active );
      afterAnySet();	// business actions/assertions after data assignment
   }


   

   /**
    * Get Earnings of the CompanyDO
    *
    * @return Earnings of the CompanyDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public int getEarnings () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getEarnings ();
   }

   
   /**
    * Set Earnings of the CompanyDO
    *
    * @param Earnings of the CompanyDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setEarnings ( int Earnings ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setEarnings ( Earnings );
      afterAnySet();	// business actions/assertions after data assignment
   }


   

   /**
    * Get GraduatesPerYear of the CompanyDO
    *
    * @return GraduatesPerYear of the CompanyDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public int getGraduatesPerYear () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getGraduatesPerYear ();
   }

   
   /**
    * Set GraduatesPerYear of the CompanyDO
    *
    * @param GraduatesPerYear of the CompanyDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setGraduatesPerYear ( int GraduatesPerYear ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setGraduatesPerYear ( GraduatesPerYear );
      afterAnySet();	// business actions/assertions after data assignment
   }


   

   /**
    * Get Location of the CompanyDO
    *
    * @return Location of the CompanyDO
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
    * Set Location of the CompanyDO
    *
    * @param Location of the CompanyDO
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
    * Get Name of the CompanyDO
    *
    * @return Name of the CompanyDO
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
    * Set Name of the CompanyDO
    *
    * @param Name of the CompanyDO
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
    * Get NumberEmployees of the CompanyDO
    *
    * @return NumberEmployees of the CompanyDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public int getNumberEmployees () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getNumberEmployees ();
   }

   
   /**
    * Set NumberEmployees of the CompanyDO
    *
    * @param NumberEmployees of the CompanyDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setNumberEmployees ( int NumberEmployees ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setNumberEmployees ( NumberEmployees );
      afterAnySet();	// business actions/assertions after data assignment
   }


   

   /**
    * Get ShortDescription of the CompanyDO
    *
    * @return ShortDescription of the CompanyDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public String getShortDescription () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getShortDescription ();
   }

   
   /**
    * Set ShortDescription of the CompanyDO
    *
    * @param ShortDescription of the CompanyDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setShortDescription ( String ShortDescription ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setShortDescription ( ShortDescription );
      afterAnySet();	// business actions/assertions after data assignment
   }


   

   /**
    * Get Contact of the CompanyDO
    *
    * @return Contact of the CompanyDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.PersonDO getContact () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getContact ();
   }

   
   /**
    * Set Contact of the CompanyDO
    *
    * @param Contact of the CompanyDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setContact ( jobmatch.data.PersonDO Contact ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setContact ( Contact );
      afterAnySet();	// business actions/assertions after data assignment
   }

   

   /**
    * Get BDO-wrapped Contact of the CompanyDO
    *
    * @return BDO-wrapped Contact of the CompanyDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.PersonBDO getContactBDO () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      jobmatch.data.PersonBDO b = jobmatch.data.PersonBDO.createExisting(
					  DO.getContact () );
      return b;
   }

   /**
    * Set Contact of the CompanyDO
    *
    * @param BDO-wrapped Contact of the CompanyDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setContact ( jobmatch.data.PersonBDO Contact ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      if ( null == Contact ) {
	  if ( true )
	      DO.setContact ( null );
	  else 
	      throw new DataObjectException( 
		  "CompanyBDO.setContact does not allow NULL." );
      } else {
          DO.setContact ( Contact.getDO() );
      }
      afterAnySet();	// business actions/assertions after data assignment
   }
   

   

   /**
    * Get Industry of the CompanyDO
    *
    * @return Industry of the CompanyDO
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
    * Set Industry of the CompanyDO
    *
    * @param Industry of the CompanyDO
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
    * Get BDO-wrapped Industry of the CompanyDO
    *
    * @return BDO-wrapped Industry of the CompanyDO
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
    * Set Industry of the CompanyDO
    *
    * @param BDO-wrapped Industry of the CompanyDO
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
	  if ( true )
	      DO.setIndustry ( null );
	  else 
	      throw new DataObjectException( 
		  "CompanyBDO.setIndustry does not allow NULL." );
      } else {
          DO.setIndustry ( Industry.getDO() );
      }
      afterAnySet();	// business actions/assertions after data assignment
   }
   

   

   /**
    * Get Presence of the CompanyDO
    *
    * @return Presence of the CompanyDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public String getPresence () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getPresence ();
   }

   
   /**
    * Set Presence of the CompanyDO
    *
    * @param Presence of the CompanyDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setPresence ( String Presence ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setPresence ( Presence );
      afterAnySet();	// business actions/assertions after data assignment
   }


   

   /**
    * Get URL of the CompanyDO
    *
    * @return URL of the CompanyDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public String getURL () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getURL ();
   }

   
   /**
    * Set URL of the CompanyDO
    *
    * @param URL of the CompanyDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setURL ( String URL ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setURL ( URL );
      afterAnySet();	// business actions/assertions after data assignment
   }


   
    /**
     * Get array of CompanyAccountDO objects that refer to the DO held by this BDO.
     *
     * @return array of CompanyAccountDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.CompanyAccountDO[] getCompanyAccountDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.CompanyAccountDO[] ret = null;
	try {
	    jobmatch.data.CompanyAccountQuery q = new jobmatch.data.CompanyAccountQuery();
	    q.setQueryCompany( DO );
	    ret = q.getDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.CompanyAccountDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single CompanyAccountDO object
     * that refers to the DO held by this BDO.
     *
     * @return CompanyAccountDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one CompanyAccountDO object was found.
     */
    public jobmatch.data.CompanyAccountDO getCompanyAccountDO () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.CompanyAccountQuery q = new jobmatch.data.CompanyAccountQuery();
	q.setQueryCompany( DO );
	q.requireUniqueInstance();
	return q.getNextDO();
    }

    /**
     * Get array of CompanyAccountBDO objects holding CompanyAccountDO objects
     * that refer to the DO held by this BDO.
     *
     * @return array of CompanyAccountBDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.CompanyAccountBDO[] getCompanyAccountBDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.CompanyAccountBDO[] ret = null;
	try {
	    jobmatch.data.CompanyAccountQuery q = new jobmatch.data.CompanyAccountQuery();
	    q.setQueryCompany( DO );
	    ret = q.getBDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.CompanyAccountBDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single CompanyAccountBDO object holding a CompanyAccountDO object
     * that refers to the DO held by this BDO.
     *
     * @return CompanyAccountBDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one CompanyAccountBDO object was found.
     */
    public jobmatch.data.CompanyAccountBDO getCompanyAccountBDO () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.CompanyAccountQuery q = new jobmatch.data.CompanyAccountQuery();
	q.setQueryCompany( DO );
	q.requireUniqueInstance();
	return q.getNextBDO();
    }

 
    /**
     * Add (set & commit) a CompanyAccountBDO object whose CompanyAccountDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo CompanyAccountBDO to be set to point to this BDO and committed.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addCompanyAccountBDO( jobmatch.data.CompanyAccountBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        addCompanyAccountBDO( rbdo, null );
    }
 
 
    /**
     * Add (set & commit) a CompanyAccountBDO object whose CompanyAccountDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo CompanyAccountBDO to be set to point to this BDO and committed.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addCompanyAccountBDO( jobmatch.data.CompanyAccountBDO rbdo, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        rbdo.setCompany( this.DO );
        rbdo.commit( tran );
    }

 
    /**
     * Remove (delete) a CompanyAccountBDO object whose CompanyAccountDO
     * refers to the DO held by this BDO.
     *
     * @param r CompanyAccountBDO to be deleted.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeCompanyAccountBDO( jobmatch.data.CompanyAccountBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        removeCompanyAccountBDO( rbdo, null );
    }
 
 
    /**
     * Remove (delete) a CompanyAccountBDO object whose CompanyAccountDO
     * refers to the DO held by this BDO.
     *
     * @param r CompanyAccountBDO to be deleted.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeCompanyAccountBDO( jobmatch.data.CompanyAccountBDO rbdo, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
	CompanyDO rdo = rbdo.getCompany();
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
     * Get array of ProfileDO objects that refer to the DO held by this BDO.
     *
     * @return array of ProfileDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.ProfileDO[] getProfileDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.ProfileDO[] ret = null;
	try {
	    jobmatch.data.ProfileQuery q = new jobmatch.data.ProfileQuery();
	    q.setQueryCompany( DO );
	    ret = q.getDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.ProfileDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single ProfileDO object
     * that refers to the DO held by this BDO.
     *
     * @return ProfileDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one ProfileDO object was found.
     */
    public jobmatch.data.ProfileDO getProfileDO () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.ProfileQuery q = new jobmatch.data.ProfileQuery();
	q.setQueryCompany( DO );
	q.requireUniqueInstance();
	return q.getNextDO();
    }

    /**
     * Get array of ProfileBDO objects holding ProfileDO objects
     * that refer to the DO held by this BDO.
     *
     * @return array of ProfileBDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.ProfileBDO[] getProfileBDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.ProfileBDO[] ret = null;
	try {
	    jobmatch.data.ProfileQuery q = new jobmatch.data.ProfileQuery();
	    q.setQueryCompany( DO );
	    ret = q.getBDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.ProfileBDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single ProfileBDO object holding a ProfileDO object
     * that refers to the DO held by this BDO.
     *
     * @return ProfileBDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one ProfileBDO object was found.
     */
    public jobmatch.data.ProfileBDO getProfileBDO () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.ProfileQuery q = new jobmatch.data.ProfileQuery();
	q.setQueryCompany( DO );
	q.requireUniqueInstance();
	return q.getNextBDO();
    }

 
    /**
     * Add (set & commit) a ProfileBDO object whose ProfileDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo ProfileBDO to be set to point to this BDO and committed.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addProfileBDO( jobmatch.data.ProfileBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        addProfileBDO( rbdo, null );
    }
 
 
    /**
     * Add (set & commit) a ProfileBDO object whose ProfileDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo ProfileBDO to be set to point to this BDO and committed.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addProfileBDO( jobmatch.data.ProfileBDO rbdo, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        rbdo.setCompany( this.DO );
        rbdo.commit( tran );
    }

 
    /**
     * Remove (delete) a ProfileBDO object whose ProfileDO
     * refers to the DO held by this BDO.
     *
     * @param r ProfileBDO to be deleted.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeProfileBDO( jobmatch.data.ProfileBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        removeProfileBDO( rbdo, null );
    }
 
 
    /**
     * Remove (delete) a ProfileBDO object whose ProfileDO
     * refers to the DO held by this BDO.
     *
     * @param r ProfileBDO to be deleted.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeProfileBDO( jobmatch.data.ProfileBDO rbdo, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
	CompanyDO rdo = rbdo.getCompany();
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
     * for the Contact data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where Contact is not valid for writing to the database.
     */
    protected void okToCommitContact( jobmatch.data.PersonDO member ) 
    throws RefAssertionException { }

    /**
     * A stub method for implementing pre-delete assertions 
     * for the Contact data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where Contact is not valid for deletion from the database.
     */
    protected void okToDeleteContact( jobmatch.data.PersonDO member ) 
    throws RefAssertionException { }

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
	    jobmatch.data.CompanyAccountBDO[] a = getCompanyAccountBDOArray();
	    for ( int i = 0; i < a.length; i++ ) {
		a[ i ].delete( dbt );
	    }
	}
	
	
	{
	    // perform cascading delete on referring table
	    jobmatch.data.ProfileBDO[] a = getProfileBDOArray();
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
	  	jobmatch.data.PersonDO Contact_DO = DO.getContact();
	if ( null != Contact_DO ) {
	    if ( Contact_DO.isLoaded() ) {
		okToCommitContact( Contact_DO );
		jobmatch.data.PersonBDO b = 
		    jobmatch.data.PersonBDO.createExisting(
						    Contact_DO );
		b.commit( dbt );
	    } else {
		// since the referenced DO is not loaded,
		// it cannot be dirty, so there is no need to commit it.
	    }
	} else {
	    if ( ! true )
		throw new RefAssertionException(
		    "Cannot commit CompanyBDO ( " + toString() +
		    " ) because Contact is not allowed to be null." );
	}
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
	    if ( ! true )
		throw new RefAssertionException(
		    "Cannot commit CompanyBDO ( " + toString() +
		    " ) because Industry is not allowed to be null." );
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
