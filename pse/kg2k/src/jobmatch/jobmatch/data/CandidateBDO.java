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
 * /scratch/locher/pse/kg2k/src/jobmatch/jobmatch/ble/jobmatch/data/CandidateBDO.java
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
 * @author locher
 * @version $Revision: 1.1 $
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
    * Get Nationality of the CandidateDO
    *
    * @return Nationality of the CandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public String getNationality () 
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
   public void setNationality ( String Nationality ) 
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
    * Get Sex of the CandidateDO
    *
    * @return Sex of the CandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public boolean getSex () 
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
   public void setSex ( boolean Sex ) 
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
    * Get Jobwish of the CandidateDO
    *
    * @return Jobwish of the CandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.JobwishDO getJobwish () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getJobwish ();
   }

   
   /**
    * Set Jobwish of the CandidateDO
    *
    * @param Jobwish of the CandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setJobwish ( jobmatch.data.JobwishDO Jobwish ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setJobwish ( Jobwish );
      afterAnySet();	// business actions/assertions after data assignment
   }

   

   /**
    * Get BDO-wrapped Jobwish of the CandidateDO
    *
    * @return BDO-wrapped Jobwish of the CandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.JobwishBDO getJobwishBDO () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      jobmatch.data.JobwishBDO b = jobmatch.data.JobwishBDO.createExisting(
					  DO.getJobwish () );
      return b;
   }

   /**
    * Set Jobwish of the CandidateDO
    *
    * @param BDO-wrapped Jobwish of the CandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setJobwish ( jobmatch.data.JobwishBDO Jobwish ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      if ( null == Jobwish ) {
	  if ( true )
	      DO.setJobwish ( null );
	  else 
	      throw new DataObjectException( 
		  "CandidateBDO.setJobwish does not allow NULL." );
      } else {
          DO.setJobwish ( Jobwish.getDO() );
      }
      afterAnySet();	// business actions/assertions after data assignment
   }
   

   

   /**
    * Get Picture of the CandidateDO
    *
    * @return Picture of the CandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public byte[] getPicture () 
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
   public void setPicture ( byte[] Picture ) 
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
    * Get Street of the CandidateDO
    *
    * @return Street of the CandidateDO
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
    * Set Street of the CandidateDO
    *
    * @param Street of the CandidateDO
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
    * Get HouseNumber of the CandidateDO
    *
    * @return HouseNumber of the CandidateDO
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
    * Set HouseNumber of the CandidateDO
    *
    * @param HouseNumber of the CandidateDO
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
    * Get PLZ of the CandidateDO
    *
    * @return PLZ of the CandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public int getPLZ () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getPLZ ();
   }

   
   /**
    * Set PLZ of the CandidateDO
    *
    * @param PLZ of the CandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setPLZ ( int PLZ ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setPLZ ( PLZ );
      afterAnySet();	// business actions/assertions after data assignment
   }


   

   /**
    * Get City of the CandidateDO
    *
    * @return City of the CandidateDO
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
    * Set City of the CandidateDO
    *
    * @param City of the CandidateDO
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
    * Get LastLogin of the CandidateDO
    *
    * @return LastLogin of the CandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public java.sql.Timestamp getLastLogin () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getLastLogin ();
   }

   
   /**
    * Set LastLogin of the CandidateDO
    *
    * @param LastLogin of the CandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setLastLogin ( java.sql.Timestamp LastLogin ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setLastLogin ( LastLogin );
      afterAnySet();	// business actions/assertions after data assignment
   }


   

   /**
    * Get Account of the CandidateDO
    *
    * @return Account of the CandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.CandidateAccountDO getAccount () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      return DO.getAccount ();
   }

   
   /**
    * Set Account of the CandidateDO
    *
    * @param Account of the CandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setAccount ( jobmatch.data.CandidateAccountDO Account ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      DO.setAccount ( Account );
      afterAnySet();	// business actions/assertions after data assignment
   }

   

   /**
    * Get BDO-wrapped Account of the CandidateDO
    *
    * @return BDO-wrapped Account of the CandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.CandidateAccountBDO getAccountBDO () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      jobmatch.data.CandidateAccountBDO b = jobmatch.data.CandidateAccountBDO.createExisting(
					  DO.getAccount () );
      return b;
   }

   /**
    * Set Account of the CandidateDO
    *
    * @param BDO-wrapped Account of the CandidateDO
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public void setAccount ( jobmatch.data.CandidateAccountBDO Account ) 
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      if ( null == Account ) {
	  if ( false )
	      DO.setAccount ( null );
	  else 
	      throw new DataObjectException( 
		  "CandidateBDO.setAccount does not allow NULL." );
      } else {
          DO.setAccount ( Account.getDO() );
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
     * for the Jobwish data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where Jobwish is not valid for writing to the database.
     */
    protected void okToCommitJobwish( jobmatch.data.JobwishDO member ) 
    throws RefAssertionException { }

    /**
     * A stub method for implementing pre-delete assertions 
     * for the Jobwish data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where Jobwish is not valid for deletion from the database.
     */
    protected void okToDeleteJobwish( jobmatch.data.JobwishDO member ) 
    throws RefAssertionException { }

    /**
     * A stub method for implementing pre-commit assertions 
     * for the Account data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where Account is not valid for writing to the database.
     */
    protected void okToCommitAccount( jobmatch.data.CandidateAccountDO member ) 
    throws RefAssertionException { }

    /**
     * A stub method for implementing pre-delete assertions 
     * for the Account data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where Account is not valid for deletion from the database.
     */
    protected void okToDeleteAccount( jobmatch.data.CandidateAccountDO member ) 
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
	  	jobmatch.data.JobwishDO Jobwish_DO = DO.getJobwish();
	if ( null != Jobwish_DO ) {
	    if ( Jobwish_DO.isLoaded() ) {
		okToCommitJobwish( Jobwish_DO );
		jobmatch.data.JobwishBDO b = 
		    jobmatch.data.JobwishBDO.createExisting(
						    Jobwish_DO );
		b.commit( dbt );
	    } else {
		// since the referenced DO is not loaded,
		// it cannot be dirty, so there is no need to commit it.
	    }
	} else {
	    if ( ! true )
		throw new RefAssertionException(
		    "Cannot commit CandidateBDO ( " + toString() +
		    " ) because Jobwish is not allowed to be null." );
	}
	jobmatch.data.CandidateAccountDO Account_DO = DO.getAccount();
	if ( null != Account_DO ) {
	    if ( Account_DO.isLoaded() ) {
		okToCommitAccount( Account_DO );
		jobmatch.data.CandidateAccountBDO b = 
		    jobmatch.data.CandidateAccountBDO.createExisting(
						    Account_DO );
		b.commit( dbt );
	    } else {
		// since the referenced DO is not loaded,
		// it cannot be dirty, so there is no need to commit it.
	    }
	} else {
	    if ( ! false )
		throw new RefAssertionException(
		    "Cannot commit CandidateBDO ( " + toString() +
		    " ) because Account is not allowed to be null." );
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
