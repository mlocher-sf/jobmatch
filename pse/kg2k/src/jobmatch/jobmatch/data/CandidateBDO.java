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
 * @version $Revision: 1.3 $
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
     * Get array of AssociationCandidateDO objects that refer to the DO held by this BDO.
     *
     * @return array of AssociationCandidateDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.AssociationCandidateDO[] getAssociationCandidateDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.AssociationCandidateDO[] ret = null;
	try {
	    jobmatch.data.AssociationCandidateQuery q = new jobmatch.data.AssociationCandidateQuery();
	    q.setQueryCandidate( DO );
	    ret = q.getDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.AssociationCandidateDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single AssociationCandidateDO object
     * that refers to the DO held by this BDO.
     *
     * @return AssociationCandidateDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one AssociationCandidateDO object was found.
     */
    public jobmatch.data.AssociationCandidateDO getAssociationCandidateDO () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.AssociationCandidateQuery q = new jobmatch.data.AssociationCandidateQuery();
	q.setQueryCandidate( DO );
	q.requireUniqueInstance();
	return q.getNextDO();
    }

    /**
     * Get array of AssociationCandidateBDO objects holding AssociationCandidateDO objects
     * that refer to the DO held by this BDO.
     *
     * @return array of AssociationCandidateBDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.AssociationCandidateBDO[] getAssociationCandidateBDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.AssociationCandidateBDO[] ret = null;
	try {
	    jobmatch.data.AssociationCandidateQuery q = new jobmatch.data.AssociationCandidateQuery();
	    q.setQueryCandidate( DO );
	    ret = q.getBDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.AssociationCandidateBDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single AssociationCandidateBDO object holding a AssociationCandidateDO object
     * that refers to the DO held by this BDO.
     *
     * @return AssociationCandidateBDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one AssociationCandidateBDO object was found.
     */
    public jobmatch.data.AssociationCandidateBDO getAssociationCandidateBDO () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.AssociationCandidateQuery q = new jobmatch.data.AssociationCandidateQuery();
	q.setQueryCandidate( DO );
	q.requireUniqueInstance();
	return q.getNextBDO();
    }

 
    /**
     * Add (set & commit) a AssociationCandidateBDO object whose AssociationCandidateDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo AssociationCandidateBDO to be set to point to this BDO and committed.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addAssociationCandidateBDO( jobmatch.data.AssociationCandidateBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        addAssociationCandidateBDO( rbdo, null );
    }
 
 
    /**
     * Add (set & commit) a AssociationCandidateBDO object whose AssociationCandidateDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo AssociationCandidateBDO to be set to point to this BDO and committed.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addAssociationCandidateBDO( jobmatch.data.AssociationCandidateBDO rbdo, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        rbdo.setCandidate( this.DO );
        rbdo.commit( tran );
    }

 
    /**
     * Remove (delete) a AssociationCandidateBDO object whose AssociationCandidateDO
     * refers to the DO held by this BDO.
     *
     * @param r AssociationCandidateBDO to be deleted.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeAssociationCandidateBDO( jobmatch.data.AssociationCandidateBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        removeAssociationCandidateBDO( rbdo, null );
    }
 
 
    /**
     * Remove (delete) a AssociationCandidateBDO object whose AssociationCandidateDO
     * refers to the DO held by this BDO.
     *
     * @param r AssociationCandidateBDO to be deleted.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeAssociationCandidateBDO( jobmatch.data.AssociationCandidateBDO rbdo, DBTransaction tran )
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
     * Get array of EmployerCandidateDO objects that refer to the DO held by this BDO.
     *
     * @return array of EmployerCandidateDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.EmployerCandidateDO[] getEmployerCandidateDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.EmployerCandidateDO[] ret = null;
	try {
	    jobmatch.data.EmployerCandidateQuery q = new jobmatch.data.EmployerCandidateQuery();
	    q.setQueryCandidate( DO );
	    ret = q.getDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.EmployerCandidateDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single EmployerCandidateDO object
     * that refers to the DO held by this BDO.
     *
     * @return EmployerCandidateDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one EmployerCandidateDO object was found.
     */
    public jobmatch.data.EmployerCandidateDO getEmployerCandidateDO () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.EmployerCandidateQuery q = new jobmatch.data.EmployerCandidateQuery();
	q.setQueryCandidate( DO );
	q.requireUniqueInstance();
	return q.getNextDO();
    }

    /**
     * Get array of EmployerCandidateBDO objects holding EmployerCandidateDO objects
     * that refer to the DO held by this BDO.
     *
     * @return array of EmployerCandidateBDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.EmployerCandidateBDO[] getEmployerCandidateBDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.EmployerCandidateBDO[] ret = null;
	try {
	    jobmatch.data.EmployerCandidateQuery q = new jobmatch.data.EmployerCandidateQuery();
	    q.setQueryCandidate( DO );
	    ret = q.getBDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.EmployerCandidateBDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single EmployerCandidateBDO object holding a EmployerCandidateDO object
     * that refers to the DO held by this BDO.
     *
     * @return EmployerCandidateBDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one EmployerCandidateBDO object was found.
     */
    public jobmatch.data.EmployerCandidateBDO getEmployerCandidateBDO () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.EmployerCandidateQuery q = new jobmatch.data.EmployerCandidateQuery();
	q.setQueryCandidate( DO );
	q.requireUniqueInstance();
	return q.getNextBDO();
    }

 
    /**
     * Add (set & commit) a EmployerCandidateBDO object whose EmployerCandidateDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo EmployerCandidateBDO to be set to point to this BDO and committed.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addEmployerCandidateBDO( jobmatch.data.EmployerCandidateBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        addEmployerCandidateBDO( rbdo, null );
    }
 
 
    /**
     * Add (set & commit) a EmployerCandidateBDO object whose EmployerCandidateDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo EmployerCandidateBDO to be set to point to this BDO and committed.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addEmployerCandidateBDO( jobmatch.data.EmployerCandidateBDO rbdo, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        rbdo.setCandidate( this.DO );
        rbdo.commit( tran );
    }

 
    /**
     * Remove (delete) a EmployerCandidateBDO object whose EmployerCandidateDO
     * refers to the DO held by this BDO.
     *
     * @param r EmployerCandidateBDO to be deleted.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeEmployerCandidateBDO( jobmatch.data.EmployerCandidateBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        removeEmployerCandidateBDO( rbdo, null );
    }
 
 
    /**
     * Remove (delete) a EmployerCandidateBDO object whose EmployerCandidateDO
     * refers to the DO held by this BDO.
     *
     * @param r EmployerCandidateBDO to be deleted.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeEmployerCandidateBDO( jobmatch.data.EmployerCandidateBDO rbdo, DBTransaction tran )
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
     * Get array of HobbyDO objects that refer to the DO held by this BDO.
     *
     * @return array of HobbyDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.HobbyDO[] getHobbyDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.HobbyDO[] ret = null;
	try {
	    jobmatch.data.HobbyQuery q = new jobmatch.data.HobbyQuery();
	    q.setQueryCandidate( DO );
	    ret = q.getDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.HobbyDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single HobbyDO object
     * that refers to the DO held by this BDO.
     *
     * @return HobbyDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one HobbyDO object was found.
     */
    public jobmatch.data.HobbyDO getHobbyDO () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.HobbyQuery q = new jobmatch.data.HobbyQuery();
	q.setQueryCandidate( DO );
	q.requireUniqueInstance();
	return q.getNextDO();
    }

    /**
     * Get array of HobbyBDO objects holding HobbyDO objects
     * that refer to the DO held by this BDO.
     *
     * @return array of HobbyBDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.HobbyBDO[] getHobbyBDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.HobbyBDO[] ret = null;
	try {
	    jobmatch.data.HobbyQuery q = new jobmatch.data.HobbyQuery();
	    q.setQueryCandidate( DO );
	    ret = q.getBDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.HobbyBDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single HobbyBDO object holding a HobbyDO object
     * that refers to the DO held by this BDO.
     *
     * @return HobbyBDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one HobbyBDO object was found.
     */
    public jobmatch.data.HobbyBDO getHobbyBDO () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.HobbyQuery q = new jobmatch.data.HobbyQuery();
	q.setQueryCandidate( DO );
	q.requireUniqueInstance();
	return q.getNextBDO();
    }

 
    /**
     * Add (set & commit) a HobbyBDO object whose HobbyDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo HobbyBDO to be set to point to this BDO and committed.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addHobbyBDO( jobmatch.data.HobbyBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        addHobbyBDO( rbdo, null );
    }
 
 
    /**
     * Add (set & commit) a HobbyBDO object whose HobbyDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo HobbyBDO to be set to point to this BDO and committed.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addHobbyBDO( jobmatch.data.HobbyBDO rbdo, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        rbdo.setCandidate( this.DO );
        rbdo.commit( tran );
    }

 
    /**
     * Remove (delete) a HobbyBDO object whose HobbyDO
     * refers to the DO held by this BDO.
     *
     * @param r HobbyBDO to be deleted.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeHobbyBDO( jobmatch.data.HobbyBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        removeHobbyBDO( rbdo, null );
    }
 
 
    /**
     * Remove (delete) a HobbyBDO object whose HobbyDO
     * refers to the DO held by this BDO.
     *
     * @param r HobbyBDO to be deleted.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeHobbyBDO( jobmatch.data.HobbyBDO rbdo, DBTransaction tran )
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
     * Get array of JobwishDO objects that refer to the DO held by this BDO.
     *
     * @return array of JobwishDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.JobwishDO[] getJobwishDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.JobwishDO[] ret = null;
	try {
	    jobmatch.data.JobwishQuery q = new jobmatch.data.JobwishQuery();
	    q.setQueryCandidate( DO );
	    ret = q.getDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.JobwishDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single JobwishDO object
     * that refers to the DO held by this BDO.
     *
     * @return JobwishDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one JobwishDO object was found.
     */
    public jobmatch.data.JobwishDO getJobwishDO () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.JobwishQuery q = new jobmatch.data.JobwishQuery();
	q.setQueryCandidate( DO );
	q.requireUniqueInstance();
	return q.getNextDO();
    }

    /**
     * Get array of JobwishBDO objects holding JobwishDO objects
     * that refer to the DO held by this BDO.
     *
     * @return array of JobwishBDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.JobwishBDO[] getJobwishBDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.JobwishBDO[] ret = null;
	try {
	    jobmatch.data.JobwishQuery q = new jobmatch.data.JobwishQuery();
	    q.setQueryCandidate( DO );
	    ret = q.getBDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.JobwishBDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single JobwishBDO object holding a JobwishDO object
     * that refers to the DO held by this BDO.
     *
     * @return JobwishBDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one JobwishBDO object was found.
     */
    public jobmatch.data.JobwishBDO getJobwishBDO () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.JobwishQuery q = new jobmatch.data.JobwishQuery();
	q.setQueryCandidate( DO );
	q.requireUniqueInstance();
	return q.getNextBDO();
    }

 
    /**
     * Add (set & commit) a JobwishBDO object whose JobwishDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo JobwishBDO to be set to point to this BDO and committed.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addJobwishBDO( jobmatch.data.JobwishBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        addJobwishBDO( rbdo, null );
    }
 
 
    /**
     * Add (set & commit) a JobwishBDO object whose JobwishDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo JobwishBDO to be set to point to this BDO and committed.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addJobwishBDO( jobmatch.data.JobwishBDO rbdo, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        rbdo.setCandidate( this.DO );
        rbdo.commit( tran );
    }

 
    /**
     * Remove (delete) a JobwishBDO object whose JobwishDO
     * refers to the DO held by this BDO.
     *
     * @param r JobwishBDO to be deleted.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeJobwishBDO( jobmatch.data.JobwishBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        removeJobwishBDO( rbdo, null );
    }
 
 
    /**
     * Remove (delete) a JobwishBDO object whose JobwishDO
     * refers to the DO held by this BDO.
     *
     * @param r JobwishBDO to be deleted.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeJobwishBDO( jobmatch.data.JobwishBDO rbdo, DBTransaction tran )
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
     * Get array of LanguageCandidateDO objects that refer to the DO held by this BDO.
     *
     * @return array of LanguageCandidateDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.LanguageCandidateDO[] getLanguageCandidateDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.LanguageCandidateDO[] ret = null;
	try {
	    jobmatch.data.LanguageCandidateQuery q = new jobmatch.data.LanguageCandidateQuery();
	    q.setQueryCandidate( DO );
	    ret = q.getDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.LanguageCandidateDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single LanguageCandidateDO object
     * that refers to the DO held by this BDO.
     *
     * @return LanguageCandidateDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one LanguageCandidateDO object was found.
     */
    public jobmatch.data.LanguageCandidateDO getLanguageCandidateDO () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.LanguageCandidateQuery q = new jobmatch.data.LanguageCandidateQuery();
	q.setQueryCandidate( DO );
	q.requireUniqueInstance();
	return q.getNextDO();
    }

    /**
     * Get array of LanguageCandidateBDO objects holding LanguageCandidateDO objects
     * that refer to the DO held by this BDO.
     *
     * @return array of LanguageCandidateBDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.LanguageCandidateBDO[] getLanguageCandidateBDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.LanguageCandidateBDO[] ret = null;
	try {
	    jobmatch.data.LanguageCandidateQuery q = new jobmatch.data.LanguageCandidateQuery();
	    q.setQueryCandidate( DO );
	    ret = q.getBDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.LanguageCandidateBDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single LanguageCandidateBDO object holding a LanguageCandidateDO object
     * that refers to the DO held by this BDO.
     *
     * @return LanguageCandidateBDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one LanguageCandidateBDO object was found.
     */
    public jobmatch.data.LanguageCandidateBDO getLanguageCandidateBDO () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.LanguageCandidateQuery q = new jobmatch.data.LanguageCandidateQuery();
	q.setQueryCandidate( DO );
	q.requireUniqueInstance();
	return q.getNextBDO();
    }

 
    /**
     * Add (set & commit) a LanguageCandidateBDO object whose LanguageCandidateDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo LanguageCandidateBDO to be set to point to this BDO and committed.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addLanguageCandidateBDO( jobmatch.data.LanguageCandidateBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        addLanguageCandidateBDO( rbdo, null );
    }
 
 
    /**
     * Add (set & commit) a LanguageCandidateBDO object whose LanguageCandidateDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo LanguageCandidateBDO to be set to point to this BDO and committed.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addLanguageCandidateBDO( jobmatch.data.LanguageCandidateBDO rbdo, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        rbdo.setCandidate( this.DO );
        rbdo.commit( tran );
    }

 
    /**
     * Remove (delete) a LanguageCandidateBDO object whose LanguageCandidateDO
     * refers to the DO held by this BDO.
     *
     * @param r LanguageCandidateBDO to be deleted.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeLanguageCandidateBDO( jobmatch.data.LanguageCandidateBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        removeLanguageCandidateBDO( rbdo, null );
    }
 
 
    /**
     * Remove (delete) a LanguageCandidateBDO object whose LanguageCandidateDO
     * refers to the DO held by this BDO.
     *
     * @param r LanguageCandidateBDO to be deleted.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeLanguageCandidateBDO( jobmatch.data.LanguageCandidateBDO rbdo, DBTransaction tran )
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
     * Get array of SchoolCandidateDO objects that refer to the DO held by this BDO.
     *
     * @return array of SchoolCandidateDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.SchoolCandidateDO[] getSchoolCandidateDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.SchoolCandidateDO[] ret = null;
	try {
	    jobmatch.data.SchoolCandidateQuery q = new jobmatch.data.SchoolCandidateQuery();
	    q.setQueryCandidate( DO );
	    ret = q.getDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.SchoolCandidateDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single SchoolCandidateDO object
     * that refers to the DO held by this BDO.
     *
     * @return SchoolCandidateDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one SchoolCandidateDO object was found.
     */
    public jobmatch.data.SchoolCandidateDO getSchoolCandidateDO () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.SchoolCandidateQuery q = new jobmatch.data.SchoolCandidateQuery();
	q.setQueryCandidate( DO );
	q.requireUniqueInstance();
	return q.getNextDO();
    }

    /**
     * Get array of SchoolCandidateBDO objects holding SchoolCandidateDO objects
     * that refer to the DO held by this BDO.
     *
     * @return array of SchoolCandidateBDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.SchoolCandidateBDO[] getSchoolCandidateBDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.SchoolCandidateBDO[] ret = null;
	try {
	    jobmatch.data.SchoolCandidateQuery q = new jobmatch.data.SchoolCandidateQuery();
	    q.setQueryCandidate( DO );
	    ret = q.getBDOArray();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: unexpected NonUniqueQueryException" );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.SchoolCandidateBDO[ 0 ];
	}
	return ret;
    }

    /**
     * Get the single SchoolCandidateBDO object holding a SchoolCandidateDO object
     * that refers to the DO held by this BDO.
     *
     * @return SchoolCandidateBDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception NonUniqueQueryException
     *   If more than one SchoolCandidateBDO object was found.
     */
    public jobmatch.data.SchoolCandidateBDO getSchoolCandidateBDO () 
    throws DataObjectException, NonUniqueQueryException, QueryException {
	jobmatch.data.SchoolCandidateQuery q = new jobmatch.data.SchoolCandidateQuery();
	q.setQueryCandidate( DO );
	q.requireUniqueInstance();
	return q.getNextBDO();
    }

 
    /**
     * Add (set & commit) a SchoolCandidateBDO object whose SchoolCandidateDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo SchoolCandidateBDO to be set to point to this BDO and committed.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addSchoolCandidateBDO( jobmatch.data.SchoolCandidateBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        addSchoolCandidateBDO( rbdo, null );
    }
 
 
    /**
     * Add (set & commit) a SchoolCandidateBDO object whose SchoolCandidateDO
     * refers to the DO held by this BDO.
     *
     * @param rbdo SchoolCandidateBDO to be set to point to this BDO and committed.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addSchoolCandidateBDO( jobmatch.data.SchoolCandidateBDO rbdo, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        rbdo.setCandidate( this.DO );
        rbdo.commit( tran );
    }

 
    /**
     * Remove (delete) a SchoolCandidateBDO object whose SchoolCandidateDO
     * refers to the DO held by this BDO.
     *
     * @param r SchoolCandidateBDO to be deleted.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeSchoolCandidateBDO( jobmatch.data.SchoolCandidateBDO rbdo )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        removeSchoolCandidateBDO( rbdo, null );
    }
 
 
    /**
     * Remove (delete) a SchoolCandidateBDO object whose SchoolCandidateDO
     * refers to the DO held by this BDO.
     *
     * @param r SchoolCandidateBDO to be deleted.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeSchoolCandidateBDO( jobmatch.data.SchoolCandidateBDO rbdo, DBTransaction tran )
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
     * From the many-to-many relationship expressed by AssociationCandidateDO,
     * get array of AssociationDO objects that indirectly refer
     * to the DO held by this BDO.
     *
     * @return array of AssociationDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.AssociationDO[] getAssociationDOArray_via_AssociationCandidate () 
    throws DataObjectException {
	jobmatch.data.AssociationDO[] ret = null;
	try {
	    jobmatch.data.AssociationCandidateDO[] arr = getAssociationCandidateDOArray();
	    ret = new jobmatch.data.AssociationDO[ arr.length ];
	    for ( int i = 0; i < arr.length; i++ ) {
		ret[ i ] = arr[ i ].getAssociation();
	    }
	} catch ( Exception e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: ", e );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.AssociationDO[ 0 ];
	}
	return ret;
    }

    /**
     * To the many-to-many relationship expressed by AssociationCandidateDO,
     * add a AssociationDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param d The AssociationDO to add to the AssociationCandidateDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void mapAssociation_via_AssociationCandidateDO( jobmatch.data.AssociationDO d )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	mapAssociation_via_AssociationCandidateDO( d, null );
    }

    /**
     * To the many-to-many relationship expressed by AssociationCandidateDO,
     * add a AssociationDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param d The AssociationDO to add to the AssociationCandidateDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void mapAssociation_via_AssociationCandidateDO( jobmatch.data.AssociationDO d, DBTransaction tran )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	jobmatch.data.AssociationBDO b = jobmatch.data.AssociationBDO.createExisting( d );
	mapAssociation_via_AssociationCandidateBDO( b, tran );
    }

    /**
     * To the many-to-many relationship expressed by AssociationCandidateDO,
     * add a AssociationDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param b The AssociationBDO to add to the AssociationCandidateDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void mapAssociation_via_AssociationCandidateBDO( jobmatch.data.AssociationBDO b )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	mapAssociation_via_AssociationCandidateBDO( b, null );
    }

    /**
     * To the many-to-many relationship expressed by AssociationCandidateDO,
     * add a AssociationDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param b The AssociationBDO to add to the AssociationCandidateDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void mapAssociation_via_AssociationCandidateBDO( jobmatch.data.AssociationBDO b, DBTransaction tran )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	jobmatch.data.AssociationCandidateBDO m = null;
	try {
	    m = jobmatch.data.AssociationCandidateBDO.createVirgin();
	} catch ( Exception e ) { 
	    throw new DataObjectException( 
		"jobmatch.data.AssociationCandidateBDO.createVirgin failed", e );
	}
	m.setAssociation( b );
	m.setCandidate( this );
	m.commit( tran );
    }

    /**
     * From the many-to-many relationship expressed by AssociationCandidateDO,
     * remove (delete) the AssociationDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param d The AssociationDO to remove from the AssociationCandidateDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void unmapAssociation_via_AssociationCandidateDO( jobmatch.data.AssociationDO d )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	unmapAssociation_via_AssociationCandidateDO( d, null );
    }

    /**
     * From the many-to-many relationship expressed by AssociationCandidateDO,
     * remove (delete) the AssociationDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param d The AssociationDO to remove from the AssociationCandidateDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void unmapAssociation_via_AssociationCandidateDO( jobmatch.data.AssociationDO d, DBTransaction tran )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	jobmatch.data.AssociationBDO b = jobmatch.data.AssociationBDO.createExisting( d );
	unmapAssociation_via_AssociationCandidateBDO( b, tran );
    }

    /**
     * From the many-to-many relationship expressed by AssociationCandidateDO,
     * remove (delete) the AssociationDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param b The AssociationBDO to remove from the AssociationCandidateDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void unmapAssociation_via_AssociationCandidateBDO( jobmatch.data.AssociationBDO b )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	unmapAssociation_via_AssociationCandidateBDO( b, null );
    }

    /**
     * From the many-to-many relationship expressed by AssociationCandidateDO,
     * remove (delete) the AssociationDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param b The AssociationBDO to remove from the AssociationCandidateDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void unmapAssociation_via_AssociationCandidateBDO( jobmatch.data.AssociationBDO b, DBTransaction tran )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	jobmatch.data.AssociationCandidateQuery q = new jobmatch.data.AssociationCandidateQuery();
	q.setQueryCandidate( DO );
	q.setQueryAssociation( b.getDO() );
	q.requireUniqueInstance();
	jobmatch.data.AssociationCandidateBDO m = null;
	try {
	    m = q.getNextBDO();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( "Multiple mappings for " +
		DO + " and " + b.getDO() + " in jobmatch.data.AssociationCandidate table." );
	}
	m.delete( tran );
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
     * From the many-to-many relationship expressed by EmployerCandidateDO,
     * get array of EmployerDO objects that indirectly refer
     * to the DO held by this BDO.
     *
     * @return array of EmployerDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public jobmatch.data.EmployerDO[] getEmployerDOArray_via_EmployerCandidate () 
    throws DataObjectException {
	jobmatch.data.EmployerDO[] ret = null;
	try {
	    jobmatch.data.EmployerCandidateDO[] arr = getEmployerCandidateDOArray();
	    ret = new jobmatch.data.EmployerDO[ arr.length ];
	    for ( int i = 0; i < arr.length; i++ ) {
		ret[ i ] = arr[ i ].getEmployer();
	    }
	} catch ( Exception e ) { 
	    throw new DataObjectException( 
		"INTERNAL ERROR: ", e );
	} finally {
	    if ( null == ret )
		ret = new jobmatch.data.EmployerDO[ 0 ];
	}
	return ret;
    }

    /**
     * To the many-to-many relationship expressed by EmployerCandidateDO,
     * add a EmployerDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param d The EmployerDO to add to the EmployerCandidateDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void mapEmployer_via_EmployerCandidateDO( jobmatch.data.EmployerDO d )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	mapEmployer_via_EmployerCandidateDO( d, null );
    }

    /**
     * To the many-to-many relationship expressed by EmployerCandidateDO,
     * add a EmployerDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param d The EmployerDO to add to the EmployerCandidateDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void mapEmployer_via_EmployerCandidateDO( jobmatch.data.EmployerDO d, DBTransaction tran )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	jobmatch.data.EmployerBDO b = jobmatch.data.EmployerBDO.createExisting( d );
	mapEmployer_via_EmployerCandidateBDO( b, tran );
    }

    /**
     * To the many-to-many relationship expressed by EmployerCandidateDO,
     * add a EmployerDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param b The EmployerBDO to add to the EmployerCandidateDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void mapEmployer_via_EmployerCandidateBDO( jobmatch.data.EmployerBDO b )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	mapEmployer_via_EmployerCandidateBDO( b, null );
    }

    /**
     * To the many-to-many relationship expressed by EmployerCandidateDO,
     * add a EmployerDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param b The EmployerBDO to add to the EmployerCandidateDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void mapEmployer_via_EmployerCandidateBDO( jobmatch.data.EmployerBDO b, DBTransaction tran )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	jobmatch.data.EmployerCandidateBDO m = null;
	try {
	    m = jobmatch.data.EmployerCandidateBDO.createVirgin();
	} catch ( Exception e ) { 
	    throw new DataObjectException( 
		"jobmatch.data.EmployerCandidateBDO.createVirgin failed", e );
	}
	m.setEmployer( b );
	m.setCandidate( this );
	m.commit( tran );
    }

    /**
     * From the many-to-many relationship expressed by EmployerCandidateDO,
     * remove (delete) the EmployerDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param d The EmployerDO to remove from the EmployerCandidateDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void unmapEmployer_via_EmployerCandidateDO( jobmatch.data.EmployerDO d )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	unmapEmployer_via_EmployerCandidateDO( d, null );
    }

    /**
     * From the many-to-many relationship expressed by EmployerCandidateDO,
     * remove (delete) the EmployerDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param d The EmployerDO to remove from the EmployerCandidateDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void unmapEmployer_via_EmployerCandidateDO( jobmatch.data.EmployerDO d, DBTransaction tran )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	jobmatch.data.EmployerBDO b = jobmatch.data.EmployerBDO.createExisting( d );
	unmapEmployer_via_EmployerCandidateBDO( b, tran );
    }

    /**
     * From the many-to-many relationship expressed by EmployerCandidateDO,
     * remove (delete) the EmployerDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param b The EmployerBDO to remove from the EmployerCandidateDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void unmapEmployer_via_EmployerCandidateBDO( jobmatch.data.EmployerBDO b )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	unmapEmployer_via_EmployerCandidateBDO( b, null );
    }

    /**
     * From the many-to-many relationship expressed by EmployerCandidateDO,
     * remove (delete) the EmployerDO object that indirectly refers
     * to the DO held by this BDO.
     *
     * @param b The EmployerBDO to remove from the EmployerCandidateDO mapping
     * for this BDO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void unmapEmployer_via_EmployerCandidateBDO( jobmatch.data.EmployerBDO b, DBTransaction tran )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	jobmatch.data.EmployerCandidateQuery q = new jobmatch.data.EmployerCandidateQuery();
	q.setQueryCandidate( DO );
	q.setQueryEmployer( b.getDO() );
	q.requireUniqueInstance();
	jobmatch.data.EmployerCandidateBDO m = null;
	try {
	    m = q.getNextBDO();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( "Multiple mappings for " +
		DO + " and " + b.getDO() + " in jobmatch.data.EmployerCandidate table." );
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
	    jobmatch.data.AssociationCandidateBDO[] a = getAssociationCandidateBDOArray();
	    for ( int i = 0; i < a.length; i++ ) {
		a[ i ].delete( dbt );
	    }
	}
	
	
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
	    jobmatch.data.EmployerCandidateBDO[] a = getEmployerCandidateBDOArray();
	    for ( int i = 0; i < a.length; i++ ) {
		a[ i ].delete( dbt );
	    }
	}
	
	
	{
	    // perform cascading delete on referring table
	    jobmatch.data.HobbyBDO[] a = getHobbyBDOArray();
	    for ( int i = 0; i < a.length; i++ ) {
		a[ i ].delete( dbt );
	    }
	}
	
	
	{
	    // perform cascading delete on referring table
	    jobmatch.data.JobwishBDO[] a = getJobwishBDOArray();
	    for ( int i = 0; i < a.length; i++ ) {
		a[ i ].delete( dbt );
	    }
	}
	
	
	{
	    // perform cascading delete on referring table
	    jobmatch.data.LanguageCandidateBDO[] a = getLanguageCandidateBDOArray();
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
	
	
	{
	    // perform cascading delete on referring table
	    jobmatch.data.SchoolCandidateBDO[] a = getSchoolCandidateBDOArray();
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
