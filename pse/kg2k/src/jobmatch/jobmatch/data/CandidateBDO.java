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
 * /scratch/studer_repositry/dataTest/jobmatch/data/CandidateBDO.java
 *-----------------------------------------------------------------------------
 */

package jobmatch.data;

import java.sql.*;
import com.lutris.appserver.server.*;
import com.lutris.appserver.server.sql.*;

import com.lutris.dods.builder.generator.query.*;

/**
 * CandidateBDO contains the same set and get methods as
 * the CandidateDO class.
 * Business Object (BO) classes typically need these set and get methods.
 * So by deriving a BO from a BDO, or by implementing a BO that 
 * contains a BDO, the developer of the BO is spared some work.
 *
 * @author studer
 * @version $Revision: 1.7 $
 */
public class CandidateBDO implements java.io.Serializable {

    /**
     * The CandidateDO object upon which the set and get methods operate.
     * This member is protected so that classes derived from CandidateBDO
     * can access the underlying Data Object.
     */
    protected CandidateDO DO;

    /**
     * Note:  This method is intended for use only by other BDO classes.
     * Presentation Layer classes should (theoretically) always use the
     * Business Layer (BDO) to create/access Data Layer (DO) objects.
     * The overhead for using BDO objects is small
     * (the BDO classes are fairly lightweight.)
     *
     * @return The DO object held by this BDO object.
     */
    public CandidateDO getDO() { 
	return DO;
    }

    /**
     * Like the class <CODE>CandidateDO</CODE>, 
     * this class acts as a factory.
     * Business Object (BO) classes typically need these set and get methods.
     * So by deriving a BO from a BDO, or by implementing a BO that 
     * contains one or more BDOs, the developer of the BO is spared some work.
     *
     * @exception Exception
     *   If an error occurs.
     */
    public static CandidateBDO createVirgin() throws Exception { 
	CandidateBDO bdo = new CandidateBDO ();
	return bdo;
    }

    /**
     * Constructor for use by classes derived from <CODE>CandidateBDO</CODE>.
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
    public CandidateBDO( CandidateDO DO ) { 
	this.DO = DO;
    }

    /**
     * Constructor required by <CODE>CandidateBDO.create</CODE> methods.
     */
    public CandidateBDO() throws Exception { 
	this.DO = CandidateDO.createVirgin();
    }

    /**
     * The createExisting method is used to create a <CODE>CandidateBDO</CODE>
     * from a <CODE>CandidateDO</CODE> that was returned by 
     * the <CODE>CandidateQuery</CODE> class.
     */
    public static CandidateBDO createExisting( CandidateDO DO ) { 
	CandidateBDO bdo = new CandidateBDO ( DO );
	return bdo;
    }

    /** 
     * The getBDOarray method performs a database query to
     * return an array of <CODE>CandidateBDO</CODE> objects
     * representing all the rows in the <CODE>Candidate</CODE> table.
     *
     * This method is a minimal example of using a Query class.
     * To restrict the set of objects returned,  you could
     * invoke <CODE>query.setXxx()</CODE>, where <CODE>Xxx</CODE>
     * is an Attribute of <CODE>CandidateDO</CODE> which was 
     * marked as "Can be queried" in the DODS Attribute Editor.
     *
     * @exception DataObjectException
     *   If an object is not found in the database.
     */
    public static CandidateBDO[] getBDOarray()
    throws DataObjectException {
        CandidateDO[] DOarray = null;
        try {
            CandidateQuery query = new CandidateQuery();
	    // To restrict the set of objects returned,
	    // you could invoke query.setXxx(), where Xxx
	    // is an Attribute of <CODE>CandidateDO</CODE> which was 
	    // marked as "Can be queried" in the DODS Attribute Editor.
            DOarray = query.getDOArray();
        } catch ( NonUniqueQueryException e1 ) {
            // CandidateQuery will throw NonUniqueQueryException
            // only if query.requireUniqueInstance() is called
	    // and more than one DO was found.
        } catch ( DataObjectException e2 ) {
            // This could happen if the database server dies, etc.
            throw e2;
        }
        CandidateBDO[] BDOarray = new CandidateBDO[ DOarray.length ];
        for ( int i = 0; i < DOarray.length; i++ )
            BDOarray[i] = CandidateBDO.createExisting( DOarray[i] );
 
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
     * HTML select lists with <CODE>CandidateBDO</CODE> objects as options.
     *
     * The <CODE>getHandle()</CODE> method is used
     * to set the value for each option,
     * and the <CODE>hasMatchingHandle()<CODE>
     * methods are used to lookup the Data Object when the selection has
     * been made.
     *
     * This CandidateBDO object holds a reference to a CandidateDO object.
     * The id of this CandidateBDO is the id of its CandidateDO.
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
    * Get Picture of the CandidateDO
    *
    * @return Picture of the CandidateDO
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
    * Set Picture of the CandidateDO
    *
    * @param Picture of the CandidateDO
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
    * Get BDO-wrapped Picture of the CandidateDO
    *
    * @return BDO-wrapped Picture of the CandidateDO
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
    * Set Picture of the CandidateDO
    *
    * @param BDO-wrapped Picture of the CandidateDO
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
		  "CandidateBDO.setPicture does not allow NULL." );
      } else {
          DO.setPicture ( Picture.getDO() );
      }
      afterAnySet();	// business actions/assertions after data assignment
   }
   

   

   /**
    * Get Email of the CandidateDO
    *
    * @return Email of the CandidateDO
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
    * Set Email of the CandidateDO
    *
    * @param Email of the CandidateDO
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
    * Get Fax of the CandidateDO
    *
    * @return Fax of the CandidateDO
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
    * Set Fax of the CandidateDO
    *
    * @param Fax of the CandidateDO
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
    * Get Adress of the CandidateDO
    *
    * @return Adress of the CandidateDO
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
    * Set Adress of the CandidateDO
    *
    * @param Adress of the CandidateDO
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
    * Get BDO-wrapped Adress of the CandidateDO
    *
    * @return BDO-wrapped Adress of the CandidateDO
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
    * Set Adress of the CandidateDO
    *
    * @param BDO-wrapped Adress of the CandidateDO
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
		  "CandidateBDO.setAdress does not allow NULL." );
      } else {
          DO.setAdress ( Adress.getDO() );
      }
      afterAnySet();	// business actions/assertions after data assignment
   }
   

   

   /**
    * Get Fname of the CandidateDO
    *
    * @return Fname of the CandidateDO
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
    * Set Fname of the CandidateDO
    *
    * @param Fname of the CandidateDO
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
    * Get Lname of the CandidateDO
    *
    * @return Lname of the CandidateDO
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
    * Set Lname of the CandidateDO
    *
    * @param Lname of the CandidateDO
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
    * Get Natel of the CandidateDO
    *
    * @return Natel of the CandidateDO
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
    * Set Natel of the CandidateDO
    *
    * @param Natel of the CandidateDO
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
    * Get Phone of the CandidateDO
    *
    * @return Phone of the CandidateDO
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
    * Set Phone of the CandidateDO
    *
    * @param Phone of the CandidateDO
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
    * Get Residence of the CandidateDO
    *
    * @return Residence of the CandidateDO
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
    * Set Residence of the CandidateDO
    *
    * @param Residence of the CandidateDO
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
    * Get Sex of the CandidateDO
    *
    * @return Sex of the CandidateDO
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
    * Set Sex of the CandidateDO
    *
    * @param Sex of the CandidateDO
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
    * Get Birthdate of the CandidateDO
    *
    * @return Birthdate of the CandidateDO
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
    * Set Birthdate of the CandidateDO
    *
    * @param Birthdate of the CandidateDO
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
    * Get Nationality of the CandidateDO
    *
    * @return Nationality of the CandidateDO
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
    * Set Nationality of the CandidateDO
    *
    * @param Nationality of the CandidateDO
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
    * Get BDO-wrapped Nationality of the CandidateDO
    *
    * @return BDO-wrapped Nationality of the CandidateDO
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
    * Set Nationality of the CandidateDO
    *
    * @param BDO-wrapped Nationality of the CandidateDO
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
		  "CandidateBDO.setNationality does not allow NULL." );
      } else {
          DO.setNationality ( Nationality.getDO() );
      }
      afterAnySet();	// business actions/assertions after data assignment
   }
   

   

   /**
    * Get URL of the CandidateDO
    *
    * @return URL of the CandidateDO
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
    * Set URL of the CandidateDO
    *
    * @param URL of the CandidateDO
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
    * Get AIESECMember of the CandidateDO
    *
    * @return AIESECMember of the CandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public boolean getAIESECMember () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getAIESECMember ();
   }

   
   /**
    * Set AIESECMember of the CandidateDO
    *
    * @param AIESECMember of the CandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setAIESECMember ( boolean AIESECMember ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setAIESECMember ( AIESECMember );
      afterAnySet();	// business actions/assertions after data assignment
   }


   

   /**
    * Get Competence of the CandidateDO
    *
    * @return Competence of the CandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public String getCompetence () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getCompetence ();
   }

   
   /**
    * Set Competence of the CandidateDO
    *
    * @param Competence of the CandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setCompetence ( String Competence ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setCompetence ( Competence );
      afterAnySet();	// business actions/assertions after data assignment
   }


   

   /**
    * Get Status of the CandidateDO
    *
    * @return Status of the CandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public boolean getStatus () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getStatus ();
   }

   
   /**
    * Set Status of the CandidateDO
    *
    * @param Status of the CandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setStatus ( boolean Status ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setStatus ( Status );
      afterAnySet();	// business actions/assertions after data assignment
   }


   
    /**
     * Get array of CandidateAccountDO objects that refer to the DO held by this BDO.
     *
     * @return array of CandidateAccountDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.CandidateAccountDO[] getCandidateAccountDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.CandidateAccountDO[] ret = null;
	try {
	    jobmatch.data.CandidateAccountQuery q = new jobmatch.data.CandidateAccountQuery();
	    q.setQueryCandidate( DO );
	    ret = q.getDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.CandidateAccountDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single CandidateAccountDO object
     * that refers to the DO held by this BDO.
     *
     * @return CandidateAccountDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one CandidateAccountDO object was found.
     */
    public jobmatch.data.CandidateAccountDO getCandidateAccountDO () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.CandidateAccountQuery q = new jobmatch.data.CandidateAccountQuery();
	q.setQueryCandidate( DO );
	q.requireUniqueInstance();
	return q.getNextDO();
    }

    /**
     * Get array of CandidateAccountBDO objects holding CandidateAccountDO objects
     * that refer to the DO held by this BDO.
     *
     * @return array of CandidateAccountBDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.CandidateAccountBDO[] getCandidateAccountBDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.CandidateAccountBDO[] ret = null;
	try {
	    jobmatch.data.CandidateAccountQuery q = new jobmatch.data.CandidateAccountQuery();
	    q.setQueryCandidate( DO );
	    ret = q.getBDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.CandidateAccountBDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single CandidateAccountBDO object holding a CandidateAccountDO object
     * that refers to the DO held by this BDO.
     *
     * @return CandidateAccountBDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one CandidateAccountBDO object was found.
     */
    public jobmatch.data.CandidateAccountBDO getCandidateAccountBDO () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.CandidateAccountQuery q = new jobmatch.data.CandidateAccountQuery();
	q.setQueryCandidate( DO );
	q.requireUniqueInstance();
	return q.getNextBDO();
    }

 
    /**
     * Add (set & commit) a CandidateAccountBDO object whose CandidateAccountDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo CandidateAccountBDO to be set to point to this BDO and committed.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addCandidateAccountBDO( jobmatch.data.CandidateAccountBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        addCandidateAccountBDO( rbdo, null );
    }
 
 
    /**
     * Add (set & commit) a CandidateAccountBDO object whose CandidateAccountDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo CandidateAccountBDO to be set to point to this BDO and committed.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addCandidateAccountBDO( jobmatch.data.CandidateAccountBDO rbdo, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        rbdo.setCandidate( this.DO );
        rbdo.commit( tran );
    }

 
    /**
     * Remove (delete) a CandidateAccountBDO object whose CandidateAccountDO
     * refers to the DO held by this BDO.
     *
     * @param r CandidateAccountBDO to be deleted.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeCandidateAccountBDO( jobmatch.data.CandidateAccountBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        removeCandidateAccountBDO( rbdo, null );
    }
 
 
    /**
     * Remove (delete) a CandidateAccountBDO object whose CandidateAccountDO
     * refers to the DO held by this BDO.
     *
     * @param r CandidateAccountBDO to be deleted.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeCandidateAccountBDO( jobmatch.data.CandidateAccountBDO rbdo, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
	CandidateDO rdo = rbdo.getCandidate();
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
	    q.setQueryCandidate( DO );
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
	q.setQueryCandidate( DO );
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
	    q.setQueryCandidate( DO );
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
	q.setQueryCandidate( DO );
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
        rbdo.setCandidate( this.DO );
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
	CandidateDO rdo = rbdo.getCandidate();
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
     * Get array of MatchQueueDO objects that refer to the DO held by this BDO.
     *
     * @return array of MatchQueueDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.MatchQueueDO[] getMatchQueueDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.MatchQueueDO[] ret = null;
	try {
	    jobmatch.data.MatchQueueQuery q = new jobmatch.data.MatchQueueQuery();
	    q.setQueryCandidate( DO );
	    ret = q.getDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.MatchQueueDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single MatchQueueDO object
     * that refers to the DO held by this BDO.
     *
     * @return MatchQueueDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one MatchQueueDO object was found.
     */
    public jobmatch.data.MatchQueueDO getMatchQueueDO () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.MatchQueueQuery q = new jobmatch.data.MatchQueueQuery();
	q.setQueryCandidate( DO );
	q.requireUniqueInstance();
	return q.getNextDO();
    }

    /**
     * Get array of MatchQueueBDO objects holding MatchQueueDO objects
     * that refer to the DO held by this BDO.
     *
     * @return array of MatchQueueBDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.MatchQueueBDO[] getMatchQueueBDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.MatchQueueBDO[] ret = null;
	try {
	    jobmatch.data.MatchQueueQuery q = new jobmatch.data.MatchQueueQuery();
	    q.setQueryCandidate( DO );
	    ret = q.getBDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.MatchQueueBDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single MatchQueueBDO object holding a MatchQueueDO object
     * that refers to the DO held by this BDO.
     *
     * @return MatchQueueBDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one MatchQueueBDO object was found.
     */
    public jobmatch.data.MatchQueueBDO getMatchQueueBDO () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.MatchQueueQuery q = new jobmatch.data.MatchQueueQuery();
	q.setQueryCandidate( DO );
	q.requireUniqueInstance();
	return q.getNextBDO();
    }

 
    /**
     * Add (set & commit) a MatchQueueBDO object whose MatchQueueDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo MatchQueueBDO to be set to point to this BDO and committed.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addMatchQueueBDO( jobmatch.data.MatchQueueBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        addMatchQueueBDO( rbdo, null );
    }
 
 
    /**
     * Add (set & commit) a MatchQueueBDO object whose MatchQueueDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo MatchQueueBDO to be set to point to this BDO and committed.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addMatchQueueBDO( jobmatch.data.MatchQueueBDO rbdo, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        rbdo.setCandidate( this.DO );
        rbdo.commit( tran );
    }

 
    /**
     * Remove (delete) a MatchQueueBDO object whose MatchQueueDO
     * refers to the DO held by this BDO.
     *
     * @param r MatchQueueBDO to be deleted.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeMatchQueueBDO( jobmatch.data.MatchQueueBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        removeMatchQueueBDO( rbdo, null );
    }
 
 
    /**
     * Remove (delete) a MatchQueueBDO object whose MatchQueueDO
     * refers to the DO held by this BDO.
     *
     * @param r MatchQueueBDO to be deleted.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeMatchQueueBDO( jobmatch.data.MatchQueueBDO rbdo, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
	CandidateDO rdo = rbdo.getCandidate();
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
     * get array of ProfileDO objects that indirectly refer
     * to the DO held by this BDO.
     *
     * @return array of ProfileDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.ProfileDO[] getProfileDOArray_via_CandidateProfile () 
    throws DataObjectException {
	jobmatch.data.ProfileDO[] ret = null;
	try {
	    jobmatch.data.CandidateProfileDO[] arr = getCandidateProfileDOArray();
	    ret = new jobmatch.data.ProfileDO[ arr.length ];
	    for ( int i = 0; i < arr.length; i++ ) {
		ret[ i ] = arr[ i ].getProfile();
	    }
	} catch ( Exception e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: ", e );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.ProfileDO[ 0 ];
	}
	return ret;
    }

    /**
     * To the many-to-many relationship expressed by CandidateProfileDO,
     * add a ProfileDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param d The ProfileDO to add to the CandidateProfileDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void mapProfile_via_CandidateProfileDO( jobmatch.data.ProfileDO d )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	mapProfile_via_CandidateProfileDO( d, null );
    }

    /**
     * To the many-to-many relationship expressed by CandidateProfileDO,
     * add a ProfileDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param d The ProfileDO to add to the CandidateProfileDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void mapProfile_via_CandidateProfileDO( jobmatch.data.ProfileDO d, DBTransaction tran )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	jobmatch.data.ProfileBDO b = jobmatch.data.ProfileBDO.createExisting( d );
	mapProfile_via_CandidateProfileBDO( b, tran );
    }

    /**
     * To the many-to-many relationship expressed by CandidateProfileDO,
     * add a ProfileDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param b The ProfileBDO to add to the CandidateProfileDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void mapProfile_via_CandidateProfileBDO( jobmatch.data.ProfileBDO b )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	mapProfile_via_CandidateProfileBDO( b, null );
    }

    /**
     * To the many-to-many relationship expressed by CandidateProfileDO,
     * add a ProfileDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param b The ProfileBDO to add to the CandidateProfileDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void mapProfile_via_CandidateProfileBDO( jobmatch.data.ProfileBDO b, DBTransaction tran )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	jobmatch.data.CandidateProfileBDO m = null;
	try {
	    m = jobmatch.data.CandidateProfileBDO.createVirgin();
	} catch ( Exception e ) { 
	    throw new DataObjectException( 
		"jobmatch.data.CandidateProfileBDO.createVirgin failed", e );
	}
	m.setProfile( b );
	m.setCandidate( this );
	m.commit( tran );
    }

    /**
     * From the many-to-many relationship expressed by CandidateProfileDO,
     * remove (delete) the ProfileDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param d The ProfileDO to remove from the CandidateProfileDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void unmapProfile_via_CandidateProfileDO( jobmatch.data.ProfileDO d )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	unmapProfile_via_CandidateProfileDO( d, null );
    }

    /**
     * From the many-to-many relationship expressed by CandidateProfileDO,
     * remove (delete) the ProfileDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param d The ProfileDO to remove from the CandidateProfileDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void unmapProfile_via_CandidateProfileDO( jobmatch.data.ProfileDO d, DBTransaction tran )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	jobmatch.data.ProfileBDO b = jobmatch.data.ProfileBDO.createExisting( d );
	unmapProfile_via_CandidateProfileBDO( b, tran );
    }

    /**
     * From the many-to-many relationship expressed by CandidateProfileDO,
     * remove (delete) the ProfileDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param b The ProfileBDO to remove from the CandidateProfileDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void unmapProfile_via_CandidateProfileBDO( jobmatch.data.ProfileBDO b )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	unmapProfile_via_CandidateProfileBDO( b, null );
    }

    /**
     * From the many-to-many relationship expressed by CandidateProfileDO,
     * remove (delete) the ProfileDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param b The ProfileBDO to remove from the CandidateProfileDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void unmapProfile_via_CandidateProfileBDO( jobmatch.data.ProfileBDO b, DBTransaction tran )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	jobmatch.data.CandidateProfileQuery q = new jobmatch.data.CandidateProfileQuery();
	q.setQueryCandidate( DO );
	q.setQueryProfile( b.getDO() );
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
	  	
	{
	    // perform cascading delete on referring table
	    jobmatch.data.CandidateAccountBDO[] a = getCandidateAccountBDOArray();
	    for ( int i = 0; i < a.length; i++ ) {
		a[ i ].delete( dbt );
	    }
	}
	
	
	{
	    // perform cascading delete on referring table
	    jobmatch.data.CandidateProfileBDO[] a = getCandidateProfileBDOArray();
	    for ( int i = 0; i < a.length; i++ ) {
		a[ i ].delete( dbt );
	    }
	}
	
	
	{
	    // perform cascading delete on referring table
	    jobmatch.data.MatchQueueBDO[] a = getMatchQueueBDOArray();
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
		    "Cannot commit CandidateBDO ( " + toString() +
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
		    "Cannot commit CandidateBDO ( " + toString() +
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
		    "Cannot commit CandidateBDO ( " + toString() +
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
