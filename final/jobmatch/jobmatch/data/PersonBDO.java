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
 * /scratch/studer_repositry/dataTest/jobmatch/data/PersonBDO.java
 *-----------------------------------------------------------------------------
 */

package jobmatch.data;

import java.sql.*;
import com.lutris.appserver.server.*;
import com.lutris.appserver.server.sql.*;

import com.lutris.dods.builder.generator.query.*;

/**
 * PersonBDO contains the same set and get methods as
 * the PersonDO class.
 * Business Object (BO) classes typically need these set and get methods.
 * So by deriving a BO from a BDO, or by implementing a BO that 
 * contains a BDO, the developer of the BO is spared some work.
 *
 * @author studer
 * @version $Revision: 1.1 $
 */
public class PersonBDO implements java.io.Serializable {

    /**
     * The PersonDO object upon which the set and get methods operate.
     * This member is protected so that classes derived from PersonBDO
     * can access the underlying Data Object.
     */
    protected PersonDO DO;

    /**
     * Note:  This method is intended for use only by other BDO classes.
     * Presentation Layer classes should (theoretically) always use the
     * Business Layer (BDO) to create/access Data Layer (DO) objects.
     * The overhead for using BDO objects is small
     * (the BDO classes are fairly lightweight.)
     *
     * @return The DO object held by this BDO object.
     */
    public PersonDO getDO() { 
	return DO;
    }

    /**
     * Like the class <CODE>PersonDO</CODE>, 
     * this class acts as a factory.
     * Business Object (BO) classes typically need these set and get methods.
     * So by deriving a BO from a BDO, or by implementing a BO that 
     * contains one or more BDOs, the developer of the BO is spared some work.
     *
     * @exception Exception
     *   If an error occurs.
     */
    public static PersonBDO createVirgin() throws Exception { 
	PersonBDO bdo = new PersonBDO ();
	return bdo;
    }

    /**
     * Constructor for use by classes derived from <CODE>PersonBDO</CODE>.
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
    public PersonBDO( PersonDO DO ) { 
	this.DO = DO;
    }

    /**
     * Constructor required by <CODE>PersonBDO.create</CODE> methods.
     */
    public PersonBDO() throws Exception { 
	this.DO = PersonDO.createVirgin();
    }

    /**
     * The createExisting method is used to create a <CODE>PersonBDO</CODE>
     * from a <CODE>PersonDO</CODE> that was returned by 
     * the <CODE>PersonQuery</CODE> class.
     */
    public static PersonBDO createExisting( PersonDO DO ) { 
	PersonBDO bdo = new PersonBDO ( DO );
	return bdo;
    }

    /** 
     * The getBDOarray method performs a database query to
     * return an array of <CODE>PersonBDO</CODE> objects
     * representing all the rows in the <CODE>Person</CODE> table.
     *
     * This method is a minimal example of using a Query class.
     * To restrict the set of objects returned,  you could
     * invoke <CODE>query.setXxx()</CODE>, where <CODE>Xxx</CODE>
     * is an Attribute of <CODE>PersonDO</CODE> which was 
     * marked as "Can be queried" in the DODS Attribute Editor.
     *
     * @exception DataObjectException
     *   If an object is not found in the database.
     */
    public static PersonBDO[] getBDOarray()
    throws DataObjectException {
        PersonDO[] DOarray = null;
        try {
            PersonQuery query = new PersonQuery();
	    // To restrict the set of objects returned,
	    // you could invoke query.setXxx(), where Xxx
	    // is an Attribute of <CODE>PersonDO</CODE> which was 
	    // marked as "Can be queried" in the DODS Attribute Editor.
            DOarray = query.getDOArray();
        } catch ( NonUniqueQueryException e1 ) {
            // PersonQuery will throw NonUniqueQueryException
            // only if query.requireUniqueInstance() is called
	    // and more than one DO was found.
        } catch ( DataObjectException e2 ) {
            // This could happen if the database server dies, etc.
            throw e2;
        }
        PersonBDO[] BDOarray = new PersonBDO[ DOarray.length ];
        for ( int i = 0; i < DOarray.length; i++ )
            BDOarray[i] = PersonBDO.createExisting( DOarray[i] );
 
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
     * HTML select lists with <CODE>PersonBDO</CODE> objects as options.
     *
     * The <CODE>getHandle()</CODE> method is used
     * to set the value for each option,
     * and the <CODE>hasMatchingHandle()<CODE>
     * methods are used to lookup the Data Object when the selection has
     * been made.
     *
     * This PersonBDO object holds a reference to a PersonDO object.
     * The id of this PersonBDO is the id of its PersonDO.
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
    * Get Picture of the PersonDO
    *
    * @return Picture of the PersonDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.PictureDO getPicture () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getPicture ();
   }

   
   /**
    * Set Picture of the PersonDO
    *
    * @param Picture of the PersonDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setPicture ( jobmatch.data.PictureDO Picture ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setPicture ( Picture );
      afterAnySet();	// business actions/assertions after data assignment
   }

   

   /**
    * Get BDO-wrapped Picture of the PersonDO
    *
    * @return BDO-wrapped Picture of the PersonDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.PictureBDO getPictureBDO () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      jobmatch.data.PictureBDO b = jobmatch.data.PictureBDO.createExisting(
					  DO.getPicture () );
      return b;
   }

   /**
    * Set Picture of the PersonDO
    *
    * @param BDO-wrapped Picture of the PersonDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setPicture ( jobmatch.data.PictureBDO Picture ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      if ( null == Picture ) {
	  if ( true )
	      DO.setPicture ( null );
	  else 
	      throw new DataObjectException( 
		  "PersonBDO.setPicture does not allow NULL." );
      } else {
          DO.setPicture ( Picture.getDO() );
      }
      afterAnySet();	// business actions/assertions after data assignment
   }
   

   

   /**
    * Get Email of the PersonDO
    *
    * @return Email of the PersonDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public String getEmail () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getEmail ();
   }

   
   /**
    * Set Email of the PersonDO
    *
    * @param Email of the PersonDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setEmail ( String Email ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setEmail ( Email );
      afterAnySet();	// business actions/assertions after data assignment
   }


   

   /**
    * Get Fax of the PersonDO
    *
    * @return Fax of the PersonDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public String getFax () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getFax ();
   }

   
   /**
    * Set Fax of the PersonDO
    *
    * @param Fax of the PersonDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setFax ( String Fax ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setFax ( Fax );
      afterAnySet();	// business actions/assertions after data assignment
   }


   

   /**
    * Get Adress of the PersonDO
    *
    * @return Adress of the PersonDO
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
    * Set Adress of the PersonDO
    *
    * @param Adress of the PersonDO
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
    * Get BDO-wrapped Adress of the PersonDO
    *
    * @return BDO-wrapped Adress of the PersonDO
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
    * Set Adress of the PersonDO
    *
    * @param BDO-wrapped Adress of the PersonDO
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
		  "PersonBDO.setAdress does not allow NULL." );
      } else {
          DO.setAdress ( Adress.getDO() );
      }
      afterAnySet();	// business actions/assertions after data assignment
   }
   

   

   /**
    * Get Fname of the PersonDO
    *
    * @return Fname of the PersonDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public String getFname () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getFname ();
   }

   
   /**
    * Set Fname of the PersonDO
    *
    * @param Fname of the PersonDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setFname ( String Fname ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setFname ( Fname );
      afterAnySet();	// business actions/assertions after data assignment
   }


   

   /**
    * Get Lname of the PersonDO
    *
    * @return Lname of the PersonDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public String getLname () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getLname ();
   }

   
   /**
    * Set Lname of the PersonDO
    *
    * @param Lname of the PersonDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setLname ( String Lname ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setLname ( Lname );
      afterAnySet();	// business actions/assertions after data assignment
   }


   

   /**
    * Get Natel of the PersonDO
    *
    * @return Natel of the PersonDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public String getNatel () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getNatel ();
   }

   
   /**
    * Set Natel of the PersonDO
    *
    * @param Natel of the PersonDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setNatel ( String Natel ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setNatel ( Natel );
      afterAnySet();	// business actions/assertions after data assignment
   }


   

   /**
    * Get Phone of the PersonDO
    *
    * @return Phone of the PersonDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public String getPhone () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getPhone ();
   }

   
   /**
    * Set Phone of the PersonDO
    *
    * @param Phone of the PersonDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setPhone ( String Phone ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setPhone ( Phone );
      afterAnySet();	// business actions/assertions after data assignment
   }


   

   /**
    * Get Residence of the PersonDO
    *
    * @return Residence of the PersonDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public String getResidence () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getResidence ();
   }

   
   /**
    * Set Residence of the PersonDO
    *
    * @param Residence of the PersonDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setResidence ( String Residence ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setResidence ( Residence );
      afterAnySet();	// business actions/assertions after data assignment
   }


   

   /**
    * Get Sex of the PersonDO
    *
    * @return Sex of the PersonDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public String getSex () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getSex ();
   }

   
   /**
    * Set Sex of the PersonDO
    *
    * @param Sex of the PersonDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setSex ( String Sex ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setSex ( Sex );
      afterAnySet();	// business actions/assertions after data assignment
   }


   

   /**
    * Get Birthdate of the PersonDO
    *
    * @return Birthdate of the PersonDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public java.sql.Date getBirthdate () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getBirthdate ();
   }

   
   /**
    * Set Birthdate of the PersonDO
    *
    * @param Birthdate of the PersonDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setBirthdate ( java.sql.Date Birthdate ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setBirthdate ( Birthdate );
      afterAnySet();	// business actions/assertions after data assignment
   }


   

   /**
    * Get Nationality of the PersonDO
    *
    * @return Nationality of the PersonDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.CountryDO getNationality () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getNationality ();
   }

   
   /**
    * Set Nationality of the PersonDO
    *
    * @param Nationality of the PersonDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setNationality ( jobmatch.data.CountryDO Nationality ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setNationality ( Nationality );
      afterAnySet();	// business actions/assertions after data assignment
   }

   

   /**
    * Get BDO-wrapped Nationality of the PersonDO
    *
    * @return BDO-wrapped Nationality of the PersonDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.CountryBDO getNationalityBDO () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      jobmatch.data.CountryBDO b = jobmatch.data.CountryBDO.createExisting(
					  DO.getNationality () );
      return b;
   }

   /**
    * Set Nationality of the PersonDO
    *
    * @param BDO-wrapped Nationality of the PersonDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setNationality ( jobmatch.data.CountryBDO Nationality ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      if ( null == Nationality ) {
	  if ( true )
	      DO.setNationality ( null );
	  else 
	      throw new DataObjectException( 
		  "PersonBDO.setNationality does not allow NULL." );
      } else {
          DO.setNationality ( Nationality.getDO() );
      }
      afterAnySet();	// business actions/assertions after data assignment
   }
   

   

   /**
    * Get URL of the PersonDO
    *
    * @return URL of the PersonDO
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
    * Set URL of the PersonDO
    *
    * @param URL of the PersonDO
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
     * for the Picture data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where Picture is not valid for writing to the database.
     */
    protected void okToCommitPicture( jobmatch.data.PictureDO member ) 
    throws RefAssertionException { }

    /**
     * A stub method for implementing pre-delete assertions 
     * for the Picture data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where Picture is not valid for deletion from the database.
     */
    protected void okToDeletePicture( jobmatch.data.PictureDO member ) 
    throws RefAssertionException { }

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
     * A stub method for implementing pre-commit assertions 
     * for the Nationality data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where Nationality is not valid for writing to the database.
     */
    protected void okToCommitNationality( jobmatch.data.CountryDO member ) 
    throws RefAssertionException { }

    /**
     * A stub method for implementing pre-delete assertions 
     * for the Nationality data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where Nationality is not valid for deletion from the database.
     */
    protected void okToDeleteNationality( jobmatch.data.CountryDO member ) 
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
	  	jobmatch.data.PictureDO Picture_DO = DO.getPicture();
	if ( null != Picture_DO ) {
	    if ( Picture_DO.isLoaded() ) {
		okToCommitPicture( Picture_DO );
		jobmatch.data.PictureBDO b = 
		    jobmatch.data.PictureBDO.createExisting(
						    Picture_DO );
		b.commit( dbt );
	    } else {
		// since the referenced DO is not loaded,
		// it cannot be dirty, so there is no need to commit it.
	    }
	} else {
	    if ( ! true )
		throw new RefAssertionException(
		    "Cannot commit PersonBDO ( " + toString() +
		    " ) because Picture is not allowed to be null." );
	}
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
		    "Cannot commit PersonBDO ( " + toString() +
		    " ) because Adress is not allowed to be null." );
	}
	jobmatch.data.CountryDO Nationality_DO = DO.getNationality();
	if ( null != Nationality_DO ) {
	    if ( Nationality_DO.isLoaded() ) {
		okToCommitNationality( Nationality_DO );
		jobmatch.data.CountryBDO b = 
		    jobmatch.data.CountryBDO.createExisting(
						    Nationality_DO );
		b.commit( dbt );
	    } else {
		// since the referenced DO is not loaded,
		// it cannot be dirty, so there is no need to commit it.
	    }
	} else {
	    if ( ! true )
		throw new RefAssertionException(
		    "Cannot commit PersonBDO ( " + toString() +
		    " ) because Nationality is not allowed to be null." );
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
