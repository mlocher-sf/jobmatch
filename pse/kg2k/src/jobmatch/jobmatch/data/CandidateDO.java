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
 * /scratch/studer_repositry/dataTest/jobmatch/data/CandidateDO.java
 *-----------------------------------------------------------------------------
 */

package jobmatch.data;

import java.sql.*;
import java.math.*;
import java.util.Hashtable;
import java.util.Enumeration;

import com.lutris.util.Config;
import com.lutris.util.KeywordValueTable;
import com.lutris.appserver.server.*;
import com.lutris.appserver.server.sql.*;
import com.lutris.dods.builder.generator.dataobject.GenericDO;
import com.lutris.dods.builder.generator.query.*;


/**
 * Data core class, used to set, retrieve the CandidateDO information.
 *
 * @version $Revision: 1.7 $
 * @author  studer
 * @since   jobmatch
 */
 public class CandidateDO extends com.lutris.dods.builder.generator.dataobject.GenericDO implements java.io.Serializable {

    /**
     * static final data members name the table and columns for this DO.
     * By using these members with an instance of the QueryBuilder class,
     * an application can perform straight SQL queries while retaining
     * compile-time checking of table and column usage.
     *
     * Example:  List the Cities containing Persons named Bob:
     *
     *    Using straight SQL with QueryBuilder:
     *      Pro: code runs faster because you create fewer objects
     *      Con: code is less clear
     *
     *         Vector fields = new Vector();
     *         fields.addElement( AddressDO.City );
     *         QueryBuilder qb = new QueryBuilder( fields );
     *         qb.addWhere( PersonDO.FirstName, "Bob" );
     *         qb.addWhere( PersonDO.PrimaryKey, AddressDO.Person );
     *         RDBRow row;
     *         while ( null != ( row = qb.getNextRow() ) ) {
     *             String city = row.get( AddressDO.City ).getString();
     *         }
     *
     *    Using Query/DO classes:
     *      Pro: code is (often) clearer
     *      Con: code runs slower because you create more objects
     *
     *         PersonQuery pq = new PersonQuery();
     *         pq.setQueryFirstName( "Bob" );
     *         PersonDO[] bobs = pq.getDOArray();
     *         for ( int i = 0; i < bobs.length; i++ ) {
     *             AddressQuery aq = new AddressQuery();
     *             aq.setQueryPerson( bobs[i] );
     *             AddressDO addr = aq.getNextDO();
     *             String city = addr.getCity();
     *         }
     */
    static public final RDBTable  table = new RDBTable( "Candidate" );

    /**
     * Return Candidate as the name of the table in the database
     * which contains CandidateDO objects.
     * This method overrides CoreDO.getTableName()
     * and is used by CoreDO.executeUpdate() during error handling.
     *
     * @return the database table name
     * @see CoreDO
     * @author Jay Gunter
     */
    protected String getTableName() {
	return "Candidate";
    }

    static public final RDBColumn PrimaryKey = new RDBColumn( table,
					      GenericDO.getPrimaryKeyName() );
    /* RDBColumns for CandidateDO attributes are defined below. */

    /* Using a DO (and its Query class) to access a VIEW instead of a TABLE:
     *
     * A DO (and its Query class) can be used to access a VIEW
     * instead of a TABLE.  The Data Object is created as usual in DODS,
     * but the "create table" SQL command for that DO is not used.
     * Instead, you substitute a "create view" command to create a
     * virtual table in the database; this is often done to provide
     * convenient access to a collection of tables joined together.
     *
     * A VIEW usually does not return "oid" and "version" columns;
     * often (but now always) a VIEW is defined to return the "oid" column
     * for one of the tables joined together in the definition of the VIEW.
     * If the isView flag is true, CandidateDO.createExisting(ResultSet)
     * will NOT invoke the GenericDO(ResultSet) constructor
     * so to avoid attempting to extract the "oid" and "version" columns
     * from the ResultSet.
     *
     * A future release of DODS will allow this flag to be set from the GUI.
     * In the meantime, if this DO class represents a VIEW,
     * change the isView flag to true.
     */
    static protected final boolean isView = false;

    /**
     * A DO class contains a reference to a DataStruct class.
     * This reference can be null (when the data for the DO
     * has not yet been retrieved from the database),
     * allowing a DO object to be a lightweight placeholder
     * until its data is needed.
     */
    private CandidateDataStruct data = null;

    /**
     * isReadOnly()
     * Returns true if the data for this object has been marked read-only.
     */
    public boolean isReadOnly() {
	if ( null == data ) return false;
	return data.readOnly;
    }

    /**
     * Protected constructor.  Only derived classes should call it.
     *
     * @exception DatabaseManagerException
     *   If a connection to the database cannot be established, etc.
     * @exception com.lutris.appserver.server.sql.ObjectIdException
     *   If an object id can't be allocated for this object.
     */
    protected CandidateDO ( boolean is_view )
    throws ObjectIdException, DatabaseManagerException {
        super( is_view );
    }

    /**
     * Protected constructor.  Only derived classes should call it.
     *
     * @exception DatabaseManagerException
     *   If a connection to the database cannot be established, etc.
     * @exception com.lutris.appserver.server.sql.ObjectIdException
     *   If an object id can't be allocated for this object.
     */
    protected CandidateDO ()
    throws ObjectIdException, DatabaseManagerException {
        super( isView );
    }

    /**
     * isLoaded()
     * Returns true if the data for this objects has been retrieved
     * from the database.
     */
    public boolean isLoaded() {
	return ( null != data );
    }

    /**
     * loadData()
     * Load the fields for the DO from the database.
     *
     * @exception com.lutris.appserver.server.sql.ObjectIdException
     *   If an object id can't be allocated for this object.
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception SQLException
     *   If the database rejects the SQL generated to retrieve data
     *   for this object, or if the table contains a bad foreign key, etc.
     */
    public void loadData()
    throws SQLException, ObjectIdException, DataObjectException
    {
	if ( null == data ) {
	    
	    data = new CandidateDataStruct ();
	}

	ObjectId id = getOId();
	if ( null == id )
	    return;
	if ( ! isPersistent() )   // DO from createVirgin
	    return;

	// DO from createExisting.  Complain if no record in database.
	CandidateQuery query = new CandidateQuery ();
	query.setQueryOId( id );
	query.requireUniqueInstance();
	CandidateDO obj;
	try {
	    obj = query.getNextDO();
	    if ( null == obj )
		throw new DataObjectException(
		    "CandidateDO DO not found for id=" + id );
	    makeIdentical(obj);
	    setVersion(    obj.getVersion() );
	    setNewVersion( obj.getVersion() );

	} catch ( NonUniqueQueryException e ) {
	    throw new ObjectIdException( "Duplicate ObjectId" );
	}

    }
    /**
     * Load the actual DO data if necessary.
     * Called by get/set methods.
     *
     * @exception DataObjectException If a data access error occurs.
     */
    private void checkLoad()
    throws DataObjectException {
	if ( null == data )
	    try {
		loadData();
	    } catch ( Exception e ) {
		throw new DataObjectException(
		    "Unable to load data for CandidateDO id=" + getOId() +
		    ", error = " + e.getMessage() );
	    }
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
	if ( isReadOnly() )
	    throw new DataObjectException( this + " is read-only." );
    }
    protected void afterAnySet() {
    }

    /**
     * Protected constructor used by createExisting(ObjectId) above.
     *
     * Historical note (delete at will):
     * Formerly, createExisting(ObjectId) invoked the no-args GenericDO ctor,
     * which allocated a new ObjectId.  Then, createExisting(ObjectId)
     * would call setOId(id), discarding the newly allocated ObjectId;
     * this resulted in an ObjectId "leak" (needless consumption of oid's.)
     *
     * @param id The ObjectId for the object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception com.lutris.appserver.server.sql.ObjectIdException
     *   If an object id can't be allocated for this object.
     * @exception DatabaseManagerException
     *   If a connection to the database cannot be established, etc.
     * @exception SQLException
     *   Should never see this exception since GenericDO.ctor(ObjectId)
     *   never accesses the database.
     */
    protected CandidateDO( ObjectId id )
    throws SQLException, ObjectIdException, DataObjectException, DatabaseManagerException
    {
	super( id );
    }





    /**
     * createVirgin()
     * Creates a DO that has no ObjectId or data.
     * Such a DO is used to insert a new database entry
     * after its data has been set.
     *
     * @exception com.lutris.appserver.server.sql.ObjectIdException
     *   If an object id can't be allocated for this object.
     * @exception DatabaseManagerException
     *   If a connection to the database cannot be established, etc.
     */
    public static CandidateDO createVirgin()
    throws DatabaseManagerException, ObjectIdException {
	return new CandidateDO ();
    }

    /**
     * createExisting( BigDecimal )
     *
     * Factory method creates a CandidateDO object by searching for it
     * in the database using the passed BigDecimal value as the primary key.
     *
     * Creates a DO that represents an existing entry in the database.
     * Such a DO is used to examine and possibly update such an entry.
     * createExisting() is called only from the code that retrieves
     * an ObjectId from a ResultSet (database query result).
     * createExisting() is protected because no other DO or BO should ever
     * need to call it.
     * FIX unfortunately the createExisting(BigDecimal) form *does* need
     * to be public because it is called by the public ctors of other DOs.
     * For example,
     *    AaaDO contains a ref to a BbbDO,
     *    so there is a method AaaDO.setBbb(BbbDO).
     *    In the ctor AaaDO(ResultSet), we have the call
     *       setBbb( BbbDO.createExisting( rs.getBigDecimal( "bbb", 0 )));
     * Since AaaDO is not in the same package as BbbDO,
     * BbbDO.createExisting(BigDecimal) must be public, not protected.
     * Java needs the C++ 'friend' idea.
     *
     * @param bd The BigDecimal representation of the ObjectId for the object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception com.lutris.appserver.server.sql.ObjectIdException
     *   If an object id can't be allocated for this object.
     * @exception DatabaseManagerException
     *   If a connection to the database cannot be established, etc.
     * @exception SQLException
     *   If the database rejects the SQL generated to retrieve data
     *   for this object, or if the table contains a bad foreign key, etc.
     */
    public static CandidateDO createExisting( BigDecimal bd )
    throws SQLException, ObjectIdException, DataObjectException, DatabaseManagerException
    {
	if ( null == bd )
	    return null;
	return createExisting( new ObjectId( bd ) );
    }

    /**
     * The createExisting method is used to create a <CODE>CandidateDO</CODE>
     * from a string handle.
     */
    public static CandidateDO createExisting( String handle ) {
	CandidateDO ret = null;
        try {
            BigDecimal bd = new BigDecimal( handle );
	    ret = createExisting( bd );
        } catch ( Exception e ) {
        }
	return ret;
    }

    /**
     * createExisting( ObjectId )
     *
     * Factory method creates a CandidateDO object by searching for it
     * in the database using the passed ObjectID value as the primary key.
     *
     * @param id The ObjectId for the object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception com.lutris.appserver.server.sql.ObjectIdException
     *   If an object id can't be allocated for this object.
     * @exception DatabaseManagerException
     *   If a connection to the database cannot be established, etc.
     * @exception SQLException
     *   If the database rejects the SQL generated to retrieve data
     *   for this object, or if the table contains a bad foreign key, etc.
     */
    protected static CandidateDO createExisting( ObjectId id )
    throws SQLException, ObjectIdException, DataObjectException, DatabaseManagerException
    {
	if ( null == id )
	    return null;
	CandidateDO ret = null;
	ret = new CandidateDO( id );
	ret.setPersistent( true );  // mark DO as persistent (preexisting)
	if ( ! false ) // If not lazy-loading, fetch DO data now.
	    ret.loadData();
	// unset the GenericDO.dirty flag.
	ret.markClean();
	return ret;
    }

    /**
     * createExisting( ResultSet )
     *
     * Factory method used to create an instance of this class to
     * represent a Data Object already existing in the database.
     *
     * @param rs The ResultSet returned by the Query class for
     * an existing Data Object stored in the database.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception com.lutris.appserver.server.sql.ObjectIdException
     *   If an object id can't be allocated for this object.
     * @exception DatabaseManagerException
     *   If a connection to the database cannot be established, etc.
     * @exception SQLException
     *   If the database rejects the SQL generated to retrieve data
     *   for this object, or if the table contains a bad foreign key, etc.
     */
    protected static CandidateDO createExisting( ResultSet rs )
    throws SQLException, ObjectIdException, DataObjectException
		, DatabaseManagerException
    {
	if ( null == rs )
	    return null;
	CandidateDO ret = null;
	if ( isView ) {
	    ret = new CandidateDO ();
	    ret.initFromResultSet( rs );
	} else {
	    ret = new CandidateDO ( rs );
	}
	return ret;
    }

    /**
     * createExisting( RDBRow )
     *
     * Factory method creates a CandidateDO object by searching for it
     * in the database using the CandidateDO.PrimaryKey value
     * in the passed RDBRow.
     *
     * @param RDBRow A row returned by QueryBuilder.getNextRow().
     *
     * @exception DataObjectException
     *   If the RDBRow does not contain a CandidateDO.PrimaryKey.
     *   If the object is not found in the database.
     * @exception com.lutris.appserver.server.sql.ObjectIdException
     *   If an object id can't be allocated for this object.
     * @exception DatabaseManagerException
     *   If a connection to the database cannot be established, etc.
     * @exception SQLException
     *   If the database rejects the SQL generated to retrieve data
     *   for this object, or if the table contains a bad foreign key, etc.
     */
    protected static CandidateDO createExisting( RDBRow row )
    throws SQLException, ObjectIdException, DataObjectException
		, DatabaseManagerException
    {
	if ( null == row )
	    return null;
        RDBColumnValue pk = null;
        try {
	    pk = row.get( CandidateDO.PrimaryKey );
	    return createExisting( pk );
        } catch ( Exception e ) {
	    throw new DataObjectException(
		"Cannot create CandidateDO, row does not " +
		"contain CandidateDO primary key." );
        }
    }

    /**
     * createExisting( RDBColumnValue )
     *
     * Factory method creates a CandidateDO object by searching for it
     * in the database using the passed CandidateDO.PrimaryKey.
     *
     * @param RDBColumnValue a PrimaryKey column value from a row
     * that was returned by QueryBuilder.getNextRow().
     *
     * @exception DataObjectException
     *   If the RDBColumnValue does not contain a CandidateDO.PrimaryKey.
     *   If the object is not found in the database.
     * @exception com.lutris.appserver.server.sql.ObjectIdException
     *   If an object id can't be allocated for this object.
     * @exception DatabaseManagerException
     *   If a connection to the database cannot be established, etc.
     * @exception SQLException
     *   If the database rejects the SQL generated to retrieve data
     *   for this object, or if the table contains a bad foreign key, etc.
     */
    protected static CandidateDO createExisting( RDBColumnValue pk )
    throws SQLException, ObjectIdException, DataObjectException
		, DatabaseManagerException
    {
	if ( null == pk )
	    return null;
	if ( ! pk.equals( CandidateDO.PrimaryKey ) )
	    throw new DataObjectException(
		"Cannot create CandidateDO, " +
		"RDBColumnValue is not CandidateDO.PrimaryKey." );
	BigDecimal bd = null;
        try {
	    bd = pk.getBigDecimal();
        } catch ( Exception e ) {
	    throw new DataObjectException(
		"Cannot create CandidateDO, bad primary key." );
        }
	if ( null == bd )
            return null;
	return createExisting( bd );
    }

    /**
     * createCopy()
     * Creates a DO that has no ObjectId
     * but has a copy of an existing DO's data.
     * Such a DO is used to insert a new database entry
     * that is largely similar to an existing entry.
     *
     * @param data The data struct to copy values from.
     *
     * @exception com.lutris.appserver.server.sql.ObjectIdException
     *   If an object id can't be allocated for this object.
     * @exception DatabaseManagerException
     *   If a connection to the database cannot be established, etc.
     */
    public static CandidateDO createCopy( CandidateDataStruct data )
    throws DatabaseManagerException, ObjectIdException {
	CandidateDO ret = new CandidateDO ();
	ret.data = ( CandidateDataStruct ) data.duplicate();
	return ret;
    }

    /**
     * createCopy()
     * Creates a DO that has no ObjectId
     * but has a copy of an existing DO's data.
     * Such a DO is used to insert a new database entry
     * that is largely similar to an existing entry.
     *
     * @param orig The original DO to copy.
     *
     * @exception com.lutris.appserver.server.sql.ObjectIdException
     *   If an object id can't be allocated for this object.
     * @exception DatabaseManagerException
     *   If a connection to the database cannot be established, etc.
     */
    public static CandidateDO createCopy( CandidateDO orig )
    throws DatabaseManagerException, ObjectIdException {
	CandidateDO ret = new CandidateDO ();
	if ( null != orig.data ) {
	    ret.data = ( CandidateDataStruct ) orig.data.duplicate();
	}
	return ret;
    }

    /**
     * reload()
     * Causes the DO to refresh itself from the database
     * the next time a set or get method is called.
     */
    public void reload() {
	data = null;
    }


    /**
     * The methods <CODE>
     *     getHandle
     *     hasMatchingHandle
     * </CODE> are used by Presentation Objects that need to populate
     * HTML select lists with Data Objects as options.
     * The <CODE>getHandle()</CODE> method is used
     * to set the value for each option,
     * and the <CODE>hasMatchingHandle()<CODE>
     * methods are used to lookup the Data Object when the selection has
     * been made.
     *
     * @exception DatabaseManagerException
     *   If a connection to the database cannot be established, etc.
     *
     * @return id of this DO as a string
     *   If an object id can't be allocated for this object.
     */
    public        String  getHandle()
    throws DatabaseManagerException {
	String ret = null;
	    if ( null == getOId() )
		   throw new DatabaseManagerException( "ID not set" );
	    ret = getOId().toString();
        return ret;
    }

   /**
     * hasMatchingHandle
     *
     * @param handle
     *   <CODE>String</CODE> version of DO id
     * @return boolean
     *   True if the string version of the id of this DO matches passed handle
     * @see getHandle
     */
    public        boolean hasMatchingHandle( String handle ) {
	boolean ret = false;
	    if ( null == getOId() )
		   ret = false;
	    else
		   ret = getOId().toString().equals( handle );
        return ret;
    }



    /**
     * makeIdentical()
     *
     * Assigns the DataStruct of an existing DO to this DO.
     * Does not duplicate data. Just assigns the reference.
     *
     * @param orig The original DO.
     *
     */
    protected void makeIdentical( CandidateDO orig ) {
	super.makeIdentical(orig);
	data = orig.data;
    }

////////////////////////// data member Picture

   /* static final RDBColumn Picture for use with QueryBuilder.
    * See RDBColumn PrimaryKey at the top of this file for usage example.
    */
   static public final RDBColumn Picture = 
			    new RDBColumn( table, "Picture" );

   /**
    * Get Picture of the Candidate
    *
    * @return Picture of the Candidate
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.PictureDO getPicture () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      checkLoad();
      return data.Picture;
   }

   /**
    * Set Picture of the Candidate
    *
    * @param Picture of the Candidate
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
      checkLoad();
      data.Picture = (jobmatch.data.PictureDO) markNewValue(
	data.Picture, Picture  );
      afterAnySet();	// business actions/assertions after data assignment
   }
   


////////////////////////// data member Email

   /* static final RDBColumn Email for use with QueryBuilder.
    * See RDBColumn PrimaryKey at the top of this file for usage example.
    */
   static public final RDBColumn Email = 
			    new RDBColumn( table, "Email" );

   /**
    * Get Email of the Candidate
    *
    * @return Email of the Candidate
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public String getEmail () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      checkLoad();
      return data.Email;
   }

   /**
    * Set Email of the Candidate
    *
    * @param Email of the Candidate
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
      checkLoad();
      data.Email =  markNewValue(
	data.Email, Email , 0, 50, true );
      afterAnySet();	// business actions/assertions after data assignment
   }
   


////////////////////////// data member Fax

   /* static final RDBColumn Fax for use with QueryBuilder.
    * See RDBColumn PrimaryKey at the top of this file for usage example.
    */
   static public final RDBColumn Fax = 
			    new RDBColumn( table, "Fax" );

   /**
    * Get Fax of the Candidate
    *
    * @return Fax of the Candidate
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public String getFax () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      checkLoad();
      return data.Fax;
   }

   /**
    * Set Fax of the Candidate
    *
    * @param Fax of the Candidate
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
      checkLoad();
      data.Fax =  markNewValue(
	data.Fax, Fax , 0, 20, true );
      afterAnySet();	// business actions/assertions after data assignment
   }
   


////////////////////////// data member Adress

   /* static final RDBColumn Adress for use with QueryBuilder.
    * See RDBColumn PrimaryKey at the top of this file for usage example.
    */
   static public final RDBColumn Adress = 
			    new RDBColumn( table, "Adress" );

   /**
    * Get Adress of the Candidate
    *
    * @return Adress of the Candidate
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.AdressDO getAdress () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      checkLoad();
      return data.Adress;
   }

   /**
    * Set Adress of the Candidate
    *
    * @param Adress of the Candidate
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
      checkLoad();
      data.Adress = (jobmatch.data.AdressDO) markNewValue(
	data.Adress, Adress  );
      afterAnySet();	// business actions/assertions after data assignment
   }
   


////////////////////////// data member Fname

   /* static final RDBColumn Fname for use with QueryBuilder.
    * See RDBColumn PrimaryKey at the top of this file for usage example.
    */
   static public final RDBColumn Fname = 
			    new RDBColumn( table, "Fname" );

   /**
    * Get Fname of the Candidate
    *
    * @return Fname of the Candidate
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public String getFname () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      checkLoad();
      return data.Fname;
   }

   /**
    * Set Fname of the Candidate
    *
    * @param Fname of the Candidate
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
      checkLoad();
      data.Fname =  markNewValue(
	data.Fname, Fname , 0, 20, true );
      afterAnySet();	// business actions/assertions after data assignment
   }
   


////////////////////////// data member Lname

   /* static final RDBColumn Lname for use with QueryBuilder.
    * See RDBColumn PrimaryKey at the top of this file for usage example.
    */
   static public final RDBColumn Lname = 
			    new RDBColumn( table, "Lname" );

   /**
    * Get Lname of the Candidate
    *
    * @return Lname of the Candidate
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public String getLname () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      checkLoad();
      return data.Lname;
   }

   /**
    * Set Lname of the Candidate
    *
    * @param Lname of the Candidate
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
      checkLoad();
      data.Lname =  markNewValue(
	data.Lname, Lname , 0, 25, true );
      afterAnySet();	// business actions/assertions after data assignment
   }
   


////////////////////////// data member Natel

   /* static final RDBColumn Natel for use with QueryBuilder.
    * See RDBColumn PrimaryKey at the top of this file for usage example.
    */
   static public final RDBColumn Natel = 
			    new RDBColumn( table, "Natel" );

   /**
    * Get Natel of the Candidate
    *
    * @return Natel of the Candidate
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public String getNatel () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      checkLoad();
      return data.Natel;
   }

   /**
    * Set Natel of the Candidate
    *
    * @param Natel of the Candidate
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
      checkLoad();
      data.Natel =  markNewValue(
	data.Natel, Natel , 0, 25, true );
      afterAnySet();	// business actions/assertions after data assignment
   }
   


////////////////////////// data member Phone

   /* static final RDBColumn Phone for use with QueryBuilder.
    * See RDBColumn PrimaryKey at the top of this file for usage example.
    */
   static public final RDBColumn Phone = 
			    new RDBColumn( table, "Phone" );

   /**
    * Get Phone of the Candidate
    *
    * @return Phone of the Candidate
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public String getPhone () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      checkLoad();
      return data.Phone;
   }

   /**
    * Set Phone of the Candidate
    *
    * @param Phone of the Candidate
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
      checkLoad();
      data.Phone =  markNewValue(
	data.Phone, Phone , 0, 25, true );
      afterAnySet();	// business actions/assertions after data assignment
   }
   


////////////////////////// data member Residence

   /* static final RDBColumn Residence for use with QueryBuilder.
    * See RDBColumn PrimaryKey at the top of this file for usage example.
    */
   static public final RDBColumn Residence = 
			    new RDBColumn( table, "Residence" );

   /**
    * Get Residence of the Candidate
    *
    * @return Residence of the Candidate
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public String getResidence () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      checkLoad();
      return data.Residence;
   }

   /**
    * Set Residence of the Candidate
    *
    * @param Residence of the Candidate
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
      checkLoad();
      data.Residence =  markNewValue(
	data.Residence, Residence , 0, 50, true );
      afterAnySet();	// business actions/assertions after data assignment
   }
   


////////////////////////// data member Sex

   /* static final RDBColumn Sex for use with QueryBuilder.
    * See RDBColumn PrimaryKey at the top of this file for usage example.
    */
   static public final RDBColumn Sex = 
			    new RDBColumn( table, "Sex" );

   /**
    * Get Sex of the Candidate
    *
    * @return Sex of the Candidate
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public String getSex () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      checkLoad();
      return data.Sex;
   }

   /**
    * Set Sex of the Candidate
    *
    * @param Sex of the Candidate
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
      checkLoad();
      data.Sex =  markNewValue(
	data.Sex, Sex , 0, 32, true );
      afterAnySet();	// business actions/assertions after data assignment
   }
   


////////////////////////// data member Birthdate

   /* static final RDBColumn Birthdate for use with QueryBuilder.
    * See RDBColumn PrimaryKey at the top of this file for usage example.
    */
   static public final RDBColumn Birthdate = 
			    new RDBColumn( table, "Birthdate" );

   /**
    * Get Birthdate of the Candidate
    *
    * @return Birthdate of the Candidate
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public java.sql.Date getBirthdate () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      checkLoad();
      return data.Birthdate;
   }

   /**
    * Set Birthdate of the Candidate
    *
    * @param Birthdate of the Candidate
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
      checkLoad();
      data.Birthdate =  markNewValue(
	data.Birthdate, Birthdate  );
      afterAnySet();	// business actions/assertions after data assignment
   }
   


////////////////////////// data member Nationality

   /* static final RDBColumn Nationality for use with QueryBuilder.
    * See RDBColumn PrimaryKey at the top of this file for usage example.
    */
   static public final RDBColumn Nationality = 
			    new RDBColumn( table, "Nationality" );

   /**
    * Get Nationality of the Candidate
    *
    * @return Nationality of the Candidate
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.CountryDO getNationality () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      checkLoad();
      return data.Nationality;
   }

   /**
    * Set Nationality of the Candidate
    *
    * @param Nationality of the Candidate
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
      checkLoad();
      data.Nationality = (jobmatch.data.CountryDO) markNewValue(
	data.Nationality, Nationality  );
      afterAnySet();	// business actions/assertions after data assignment
   }
   


////////////////////////// data member URL

   /* static final RDBColumn URL for use with QueryBuilder.
    * See RDBColumn PrimaryKey at the top of this file for usage example.
    */
   static public final RDBColumn URL = 
			    new RDBColumn( table, "URL" );

   /**
    * Get URL of the Candidate
    *
    * @return URL of the Candidate
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public String getURL () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      checkLoad();
      return data.URL;
   }

   /**
    * Set URL of the Candidate
    *
    * @param URL of the Candidate
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
      checkLoad();
      data.URL =  markNewValue(
	data.URL, URL , 0, 50, true );
      afterAnySet();	// business actions/assertions after data assignment
   }
   


////////////////////////// data member AIESECMember

   /* static final RDBColumn AIESECMember for use with QueryBuilder.
    * See RDBColumn PrimaryKey at the top of this file for usage example.
    */
   static public final RDBColumn AIESECMember = 
			    new RDBColumn( table, "AIESECMember" );

   /**
    * Get AIESECMember of the Candidate
    *
    * @return AIESECMember of the Candidate
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public boolean getAIESECMember () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      checkLoad();
      return data.AIESECMember;
   }

   /**
    * Set AIESECMember of the Candidate
    *
    * @param AIESECMember of the Candidate
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
      checkLoad();
      data.AIESECMember =  markNewValue(
	data.AIESECMember, AIESECMember  );
      afterAnySet();	// business actions/assertions after data assignment
   }
   


////////////////////////// data member Competence

   /* static final RDBColumn Competence for use with QueryBuilder.
    * See RDBColumn PrimaryKey at the top of this file for usage example.
    */
   static public final RDBColumn Competence = 
			    new RDBColumn( table, "Competence" );

   /**
    * Get Competence of the Candidate
    *
    * @return Competence of the Candidate
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public String getCompetence () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      checkLoad();
      return data.Competence;
   }

   /**
    * Set Competence of the Candidate
    *
    * @param Competence of the Candidate
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
      checkLoad();
      data.Competence =  markNewValue(
	data.Competence, Competence , 0, 32, true );
      afterAnySet();	// business actions/assertions after data assignment
   }
   


////////////////////////// data member Status

   /* static final RDBColumn Status for use with QueryBuilder.
    * See RDBColumn PrimaryKey at the top of this file for usage example.
    */
   static public final RDBColumn Status = 
			    new RDBColumn( table, "Status" );

   /**
    * Get Status of the Candidate
    *
    * @return Status of the Candidate
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public boolean getStatus () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      checkLoad();
      return data.Status;
   }

   /**
    * Set Status of the Candidate
    *
    * @param Status of the Candidate
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
      checkLoad();
      data.Status =  markNewValue(
	data.Status, Status  );
      afterAnySet();	// business actions/assertions after data assignment
   }
   

    /**
     * Protected constructor.
     *
     * @param rs
     *   Result set from which to obtain product data.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception com.lutris.appserver.server.sql.ObjectIdException
     *   If an object id can't be allocated for this object.
     * @exception DatabaseManagerException
     *   If a connection to the database cannot be established, etc.
     * @exception SQLException
     *   If the database rejects the SQL generated to retrieve data
     *   for this object, or if the table contains a bad foreign key, etc.
     */
    protected CandidateDO(ResultSet rs)
            throws SQLException, ObjectIdException, DataObjectException
 	    , DatabaseManagerException
    {
        super(rs);
	initFromResultSet( rs );
    }

    /**
     * initFromResultSet initializes the data members from Candidate.
     * This code was separated from the ResultSet constructor
     * so that createExisting(ResultSet) could handle VIEWs.
     */
    private void initFromResultSet( ResultSet rs )
            throws SQLException, ObjectIdException, DataObjectException
 	    , DatabaseManagerException
    {
	// Constructing a DO from a ResultSet means we definitely need the 
	// DataStruct ready for the setXxx methods invoked below.
	data = new CandidateDataStruct ();
 
	// writeMemberStuff uses the ResultSetExtraction.template
	// to build up the value for this tag:
	// the value is a series of calls to the DO set methods.
		
	setPicture( 
	    jobmatch.data.PictureDO.createExisting( 
		rs.getBigDecimal( 
			"Picture" , 0 )
	     )
	);
	
	
	setEmail( 
	    
		rs.getString( 
			"Email"  )
	    
	);
	
	
	setFax( 
	    
		rs.getString( 
			"Fax"  )
	    
	);
	
	
	setAdress( 
	    jobmatch.data.AdressDO.createExisting( 
		rs.getBigDecimal( 
			"Adress" , 0 )
	     )
	);
	
	
	setFname( 
	    
		rs.getString( 
			"Fname"  )
	    
	);
	
	
	setLname( 
	    
		rs.getString( 
			"Lname"  )
	    
	);
	
	
	setNatel( 
	    
		rs.getString( 
			"Natel"  )
	    
	);
	
	
	setPhone( 
	    
		rs.getString( 
			"Phone"  )
	    
	);
	
	
	setResidence( 
	    
		rs.getString( 
			"Residence"  )
	    
	);
	
	
	setSex( 
	    
		rs.getString( 
			"Sex"  )
	    
	);
	
	
	setBirthdate( 
	    
		rs.getDate( 
			"Birthdate"  )
	    
	);
	
	
	setNationality( 
	    jobmatch.data.CountryDO.createExisting( 
		rs.getBigDecimal( 
			"Nationality" , 0 )
	     )
	);
	
	
	setURL( 
	    
		rs.getString( 
			"URL"  )
	    
	);
	
	
	setAIESECMember( 
	    
		rs.getBoolean( 
			"AIESECMember"  )
	    
	);
	
	
	setCompetence( 
	    
		rs.getString( 
			"Competence"  )
	    
	);
	
	
	setStatus( 
	    
		rs.getBoolean( 
			"Status"  )
	    
	);
	

 
        markClean();
    }        


    

    

    private int[] param = null;

    /**
     * Prepares the statement used to insert this object
     * into the database.
     * @param conn the database connection.
     * @return the insert statement.
     * @exception java.sql.SQLException if an error occurs.
     */
    public PreparedStatement getInsertStatement(DBConnection conn)
            throws SQLException {
	/* 
	 * It would probably be better to have CoreDO implement
	 * 	void addToCache(CoreDO DO) {}
	 * and have each DO that has caching enabled override it as
	 *      void addToCache(CoreDO DO) { cache.put( DO.getOId(), DO ); }
	 * and change CoreDO to invoke addToCache()
	 * when it invokes getInsertStatement().
	 */

        ObjectId oid;

        PreparedStatement stmt = conn.prepareStatement( 
	    "insert into Candidate ( Picture, Email, Fax, Adress, Fname, Lname, Natel, Phone, Residence, Sex, Birthdate, Nationality, URL, AIESECMember, Competence, Status, " + getOIdColumnName() + ", " + getVersionColumnName() + " ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )" );

	param = new int[1]; param[0] = 1;
	// writeMemberStuff uses the JDBCsetCalls.template
	// to build up the value for this tag:
	// the value is a series of calls to setPrepStmtParam_TYPE methods.
	// Those methods are defined in GenericDO.
	try {
	    	setPrepStmtParam_DO( stmt, param,
		getPicture() );
	setPrepStmtParam_String( stmt, param,
		getEmail() );
	setPrepStmtParam_String( stmt, param,
		getFax() );
	setPrepStmtParam_DO( stmt, param,
		getAdress() );
	setPrepStmtParam_String( stmt, param,
		getFname() );
	setPrepStmtParam_String( stmt, param,
		getLname() );
	setPrepStmtParam_String( stmt, param,
		getNatel() );
	setPrepStmtParam_String( stmt, param,
		getPhone() );
	setPrepStmtParam_String( stmt, param,
		getResidence() );
	setPrepStmtParam_String( stmt, param,
		getSex() );
	setPrepStmtParam_java_sql_Date( stmt, param,
		getBirthdate() );
	setPrepStmtParam_DO( stmt, param,
		getNationality() );
	setPrepStmtParam_String( stmt, param,
		getURL() );
	setPrepStmtParam_boolean( stmt, param,
		getAIESECMember() );
	setPrepStmtParam_String( stmt, param,
		getCompetence() );
	setPrepStmtParam_boolean( stmt, param,
		getStatus() );


	    /* The order of the values being inserted must match
	     * the order of the columns listed in the CREATE TABLE command
	     * that appears in the .sql file for this DO.
	     * Since the id and version number are always the last columns
	     * listed in the CREATE TABLE command, their values are added
	     * to the PreparedStatement after all other values.
	     */
	    setPrepStmtParam_BigDecimal( stmt, param, getOId().toBigDecimal() );
	    setPrepStmtParam_int(        stmt, param, getNewVersion() );

	} catch ( Exception e ) {
	    throw new SQLException( "Data Object error: " + e.getMessage() );
	}

        return stmt;
    }

    /**
     * Prepares the statement used to update this object
     * in the database.
     * @param conn the database connection
     * @return the update statement.
     * @exception java.sql.SQLException if an error occurs.
     */
    public PreparedStatement getUpdateStatement(DBConnection conn)
            throws SQLException {

        ObjectId oid;

        PreparedStatement stmt = conn.prepareStatement(
	    "update Candidate set " + getVersionColumnName() + " = ?, Picture = ?, Email = ?, Fax = ?, Adress = ?, Fname = ?, Lname = ?, Natel = ?, Phone = ?, Residence = ?, Sex = ?, Birthdate = ?, Nationality = ?, URL = ?, AIESECMember = ?, Competence = ?, Status = ? " +
	    "where " + getOIdColumnName() + " = ? and " + getVersionColumnName() + " = ?" );

	param = new int[1]; param[0] = 1;
	//int[] param = {1};
	// writeMemberStuff builds up the value for this tag:
	// the value is a series of calls to setPrepStmtParam_TYPE methods.
	// Those methods are defined below.
	try {
	    setPrepStmtParam_int( stmt, param, getNewVersion() );
	    	setPrepStmtParam_DO( stmt, param,
		getPicture() );
	setPrepStmtParam_String( stmt, param,
		getEmail() );
	setPrepStmtParam_String( stmt, param,
		getFax() );
	setPrepStmtParam_DO( stmt, param,
		getAdress() );
	setPrepStmtParam_String( stmt, param,
		getFname() );
	setPrepStmtParam_String( stmt, param,
		getLname() );
	setPrepStmtParam_String( stmt, param,
		getNatel() );
	setPrepStmtParam_String( stmt, param,
		getPhone() );
	setPrepStmtParam_String( stmt, param,
		getResidence() );
	setPrepStmtParam_String( stmt, param,
		getSex() );
	setPrepStmtParam_java_sql_Date( stmt, param,
		getBirthdate() );
	setPrepStmtParam_DO( stmt, param,
		getNationality() );
	setPrepStmtParam_String( stmt, param,
		getURL() );
	setPrepStmtParam_boolean( stmt, param,
		getAIESECMember() );
	setPrepStmtParam_String( stmt, param,
		getCompetence() );
	setPrepStmtParam_boolean( stmt, param,
		getStatus() );


	    /* When updating a persistent object, the UPDATE_WHERE_CLAUSE tag
	     * used to build the SQL WHERE clause (above) uses the 
	     * DO's id and version number to locate the correct row in
	     * the database table.
	     */
	    setPrepStmtParam_BigDecimal( stmt, param, getOId().toBigDecimal() );
	    setPrepStmtParam_int(        stmt, param, getVersion() );

	} catch ( Exception e ) {
	    throw new SQLException( "Data Object error: " + e.getMessage() );
	}

        return stmt;
    }

    /**
     * Prepares the statement used to delete this object
     * from the database.
     * @param conn the database connection
     * @return the delete statement.
     * @exception java.sql.SQLException if an error occurs.
     */
    public PreparedStatement getDeleteStatement(DBConnection conn)
            throws SQLException {

        String sql =
            "delete from Candidate \n" +
            "where " + getOIdColumnName() + " = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setBigDecimal(1, getOId().toBigDecimal());
        return stmt;
    }

    

    /*
     * toString - for debugging
     */
/*
    public String toString(){
	String str = "CandidateDO:";
	ObjectId oid = getOId();
	String id = "virgin";
	if ( null != oid ) 
	    id = oid.toString();
	str += " OID=" + id;
	if ( null != data ) 
	    str = str + "\n" + indent + "Picture=" + ( null == data.Picture ? null  : data.Picture.toString( indentCount + 1 ) )
+ "\n" + indent + "Email=" + data.Email
+ "\n" + indent + "Fax=" + data.Fax
+ "\n" + indent + "Adress=" + ( null == data.Adress ? null  : data.Adress.toString( indentCount + 1 ) )
+ "\n" + indent + "Fname=" + data.Fname
+ "\n" + indent + "Lname=" + data.Lname
+ "\n" + indent + "Natel=" + data.Natel
+ "\n" + indent + "Phone=" + data.Phone
+ "\n" + indent + "Residence=" + data.Residence
+ "\n" + indent + "Sex=" + data.Sex
+ "\n" + indent + "Birthdate=" + data.Birthdate
+ "\n" + indent + "Nationality=" + ( null == data.Nationality ? null  : data.Nationality.toString( indentCount + 1 ) )
+ "\n" + indent + "URL=" + data.URL
+ "\n" + indent + "AIESECMember=" + data.AIESECMember
+ "\n" + indent + "Competence=" + data.Competence
+ "\n" + indent + "Status=" + data.Status
;
        return str + "; " + super.toString();
    }
*/

    /*
     * toString - for debugging
     */
    public String toString(){
        return toString( 1 );
    }
    public String toString( int indentCount ){
        String indent = "";
        for ( int i = 0; i < indentCount; i++ ) {
            indent += ". ";
        }
        String str = indent + "CandidateDO:";
        ObjectId oid = getOId();
        String id = "virgin";
        if ( null != oid )
            id = oid.toString();
        str += " OID=" + id;
        if ( null != data )
            str = str + "\n" + indent + "Picture=" + ( null == data.Picture ? null  : data.Picture.toString( indentCount + 1 ) )
+ "\n" + indent + "Email=" + data.Email
+ "\n" + indent + "Fax=" + data.Fax
+ "\n" + indent + "Adress=" + ( null == data.Adress ? null  : data.Adress.toString( indentCount + 1 ) )
+ "\n" + indent + "Fname=" + data.Fname
+ "\n" + indent + "Lname=" + data.Lname
+ "\n" + indent + "Natel=" + data.Natel
+ "\n" + indent + "Phone=" + data.Phone
+ "\n" + indent + "Residence=" + data.Residence
+ "\n" + indent + "Sex=" + data.Sex
+ "\n" + indent + "Birthdate=" + data.Birthdate
+ "\n" + indent + "Nationality=" + ( null == data.Nationality ? null  : data.Nationality.toString( indentCount + 1 ) )
+ "\n" + indent + "URL=" + data.URL
+ "\n" + indent + "AIESECMember=" + data.AIESECMember
+ "\n" + indent + "Competence=" + data.Competence
+ "\n" + indent + "Status=" + data.Status
;
        return str + "\n" + indent + "SUPER=" + super.toString( indentCount );
        //return str;
    }

    
    /**
     * Get array of CandidateAccountDO objects that refer to this DO.
     *
     * @return array of CandidateAccountDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception QueryException
     *   If an error occured while building the query before execution.
     */
    public jobmatch.data.CandidateAccountDO[] getCandidateAccountDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.CandidateAccountDO[] ret = null;
	try {
	    jobmatch.data.CandidateAccountQuery q = new jobmatch.data.CandidateAccountQuery();
	    q.setQueryCandidate( this );
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
     * that refers to this DO.
     *
     * @return CandidateAccountDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception QueryException
     *   If an error occured while building the query before execution.
     * @exception NonUniqueQueryException
     *   If more than one CandidateAccountDO object was found.
     */
    public jobmatch.data.CandidateAccountDO getCandidateAccountDO () 
    throws DataObjectException, QueryException, NonUniqueQueryException {
	jobmatch.data.CandidateAccountQuery q = new jobmatch.data.CandidateAccountQuery();
	q.setQueryCandidate( this );
	q.requireUniqueInstance();
	return q.getNextDO();
    }

    /**
     * Add (set & commit) a CandidateAccountDO object that refers to this DO.
     *
     * @param referrer CandidateAccountDO to be set to point to this DO and committed.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addCandidateAccountDO( jobmatch.data.CandidateAccountDO referrer )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        addCandidateAccountDO( referrer, null );
    }
 
 
    /**
     * Add (set & commit) a CandidateAccountDO object that refers to this DO.
     *
     * @param referrer CandidateAccountDO to be set to point to this DO and committed.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addCandidateAccountDO( jobmatch.data.CandidateAccountDO referrer, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        referrer.setCandidate( this );
        referrer.commit( tran );
    }

 
    /**
     * Remove (delete) a CandidateAccountDO object that refers to this DO.
     *
     * @param referrer CandidateAccountDO to be deleted.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeCandidateAccountDO( jobmatch.data.CandidateAccountDO referrer )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        removeCandidateAccountDO( referrer, null );
    }
 
 
    /**
     * Remove (delete) a CandidateAccountDO object that refers to this DO.
     *
     * @param referrer CandidateAccountDO to be deleted.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeCandidateAccountDO( jobmatch.data.CandidateAccountDO referrer, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
	CandidateDO referred = referrer.getCandidate();
	String referredHandle = referred.getHandle();
	String mydoHandle = this.getHandle();
	if ( null == referredHandle || null == mydoHandle || 
	     ( ! referredHandle.equals( mydoHandle ) ) ) {
	    throw new DataObjectException( "Object " + referrer +
		" does not refer to object " + this +
		", cannot be removed this way." );
	}
        referrer.delete( tran );
    }
 

    /**
     * Get array of CandidateProfileDO objects that refer to this DO.
     *
     * @return array of CandidateProfileDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception QueryException
     *   If an error occured while building the query before execution.
     */
    public jobmatch.data.CandidateProfileDO[] getCandidateProfileDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.CandidateProfileDO[] ret = null;
	try {
	    jobmatch.data.CandidateProfileQuery q = new jobmatch.data.CandidateProfileQuery();
	    q.setQueryCandidate( this );
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
     * that refers to this DO.
     *
     * @return CandidateProfileDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception QueryException
     *   If an error occured while building the query before execution.
     * @exception NonUniqueQueryException
     *   If more than one CandidateProfileDO object was found.
     */
    public jobmatch.data.CandidateProfileDO getCandidateProfileDO () 
    throws DataObjectException, QueryException, NonUniqueQueryException {
	jobmatch.data.CandidateProfileQuery q = new jobmatch.data.CandidateProfileQuery();
	q.setQueryCandidate( this );
	q.requireUniqueInstance();
	return q.getNextDO();
    }

    /**
     * Add (set & commit) a CandidateProfileDO object that refers to this DO.
     *
     * @param referrer CandidateProfileDO to be set to point to this DO and committed.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addCandidateProfileDO( jobmatch.data.CandidateProfileDO referrer )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        addCandidateProfileDO( referrer, null );
    }
 
 
    /**
     * Add (set & commit) a CandidateProfileDO object that refers to this DO.
     *
     * @param referrer CandidateProfileDO to be set to point to this DO and committed.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addCandidateProfileDO( jobmatch.data.CandidateProfileDO referrer, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        referrer.setCandidate( this );
        referrer.commit( tran );
    }

 
    /**
     * Remove (delete) a CandidateProfileDO object that refers to this DO.
     *
     * @param referrer CandidateProfileDO to be deleted.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeCandidateProfileDO( jobmatch.data.CandidateProfileDO referrer )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        removeCandidateProfileDO( referrer, null );
    }
 
 
    /**
     * Remove (delete) a CandidateProfileDO object that refers to this DO.
     *
     * @param referrer CandidateProfileDO to be deleted.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeCandidateProfileDO( jobmatch.data.CandidateProfileDO referrer, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
	CandidateDO referred = referrer.getCandidate();
	String referredHandle = referred.getHandle();
	String mydoHandle = this.getHandle();
	if ( null == referredHandle || null == mydoHandle || 
	     ( ! referredHandle.equals( mydoHandle ) ) ) {
	    throw new DataObjectException( "Object " + referrer +
		" does not refer to object " + this +
		", cannot be removed this way." );
	}
        referrer.delete( tran );
    }
 

    /**
     * Get array of MatchQueueDO objects that refer to this DO.
     *
     * @return array of MatchQueueDO objects.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception QueryException
     *   If an error occured while building the query before execution.
     */
    public jobmatch.data.MatchQueueDO[] getMatchQueueDOArray () 
    throws DataObjectException, QueryException {
	jobmatch.data.MatchQueueDO[] ret = null;
	try {
	    jobmatch.data.MatchQueueQuery q = new jobmatch.data.MatchQueueQuery();
	    q.setQueryCandidate( this );
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
     * that refers to this DO.
     *
     * @return MatchQueueDO object.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception QueryException
     *   If an error occured while building the query before execution.
     * @exception NonUniqueQueryException
     *   If more than one MatchQueueDO object was found.
     */
    public jobmatch.data.MatchQueueDO getMatchQueueDO () 
    throws DataObjectException, QueryException, NonUniqueQueryException {
	jobmatch.data.MatchQueueQuery q = new jobmatch.data.MatchQueueQuery();
	q.setQueryCandidate( this );
	q.requireUniqueInstance();
	return q.getNextDO();
    }

    /**
     * Add (set & commit) a MatchQueueDO object that refers to this DO.
     *
     * @param referrer MatchQueueDO to be set to point to this DO and committed.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addMatchQueueDO( jobmatch.data.MatchQueueDO referrer )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        addMatchQueueDO( referrer, null );
    }
 
 
    /**
     * Add (set & commit) a MatchQueueDO object that refers to this DO.
     *
     * @param referrer MatchQueueDO to be set to point to this DO and committed.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void addMatchQueueDO( jobmatch.data.MatchQueueDO referrer, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        referrer.setCandidate( this );
        referrer.commit( tran );
    }

 
    /**
     * Remove (delete) a MatchQueueDO object that refers to this DO.
     *
     * @param referrer MatchQueueDO to be deleted.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeMatchQueueDO( jobmatch.data.MatchQueueDO referrer )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
        removeMatchQueueDO( referrer, null );
    }
 
 
    /**
     * Remove (delete) a MatchQueueDO object that refers to this DO.
     *
     * @param referrer MatchQueueDO to be deleted.
     *
     * @param tran The transaction to be used for the commit.
     * If null, a new transaction is created.
     *
     * @exception DatabaseManagerException if could not create a transaction
     * @exception java.sql.SQLException if any SQL errors occur.
     * @exception DataObjectException If object is not found in the database.
     */
    public void removeMatchQueueDO( jobmatch.data.MatchQueueDO referrer, DBTransaction tran )
    throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
	CandidateDO referred = referrer.getCandidate();
	String referredHandle = referred.getHandle();
	String mydoHandle = this.getHandle();
	if ( null == referredHandle || null == mydoHandle || 
	     ( ! referredHandle.equals( mydoHandle ) ) ) {
	    throw new DataObjectException( "Object " + referrer +
		" does not refer to object " + this +
		", cannot be removed this way." );
	}
        referrer.delete( tran );
    }
 



    /**
     * From the many-to-many relationship expressed by CandidateProfileDO,
     * get array of ProfileDO objects that indirectly refer
     * to this DO.
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
     * to this DO.
     *
     * @param d The ProfileDO to add to the CandidateProfileDO mapping
     * for this DO.
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
     * add a ProfileDO object that indirectly refers to this DO.
     *
     * @param b The ProfileDO to add to the CandidateProfileDO mapping for this DO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void mapProfile_via_CandidateProfileDO( jobmatch.data.ProfileDO d, DBTransaction tran )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	jobmatch.data.CandidateProfileDO m = null;
	try {
	    m = jobmatch.data.CandidateProfileDO.createVirgin();
	} catch ( Exception e ) { 
	    throw new DataObjectException( 
		"jobmatch.data.CandidateProfileDO.createVirgin failed", e );
	}
	m.setProfile( d );
	m.setCandidate( this );
	m.commit( tran );
    }

    /**
     * From the many-to-many relationship expressed by CandidateProfileDO,
     * remove (delete) the ProfileDO object that indirectly refers
     * to this DO.
     *
     * @param d The ProfileDO to remove from the CandidateProfileDO mapping
     * for this DO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception QueryException
     *   If an error occured while building the query before execution.
     */
    public void unmapProfile_via_CandidateProfileDO( jobmatch.data.ProfileDO d )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	unmapProfile_via_CandidateProfileDO( d, null );
    }

    /**
     * From the many-to-many relationship expressed by CandidateProfileDO,
     * remove (delete) the ProfileDO object that indirectly refers
     * to this DO.
     *
     * @param b The ProfileDO to remove from the CandidateProfileDO mapping
     * for this DO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception QueryException
     *   If an error occured while building the query before execution.
     */
    public void unmapProfile_via_CandidateProfileDO( jobmatch.data.ProfileDO d, DBTransaction tran )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	jobmatch.data.CandidateProfileQuery q = new jobmatch.data.CandidateProfileQuery();
	q.setQueryCandidate( this );
	q.setQueryProfile( d );
	q.requireUniqueInstance();
	jobmatch.data.CandidateProfileDO m = null;
	try {
	    m = q.getNextDO();
	} catch ( NonUniqueQueryException e ) { 
	    throw new DataObjectException( "Multiple mappings for " +
		this + " and " + d + " in jobmatch.data.CandidateProfile table." );
	}
	m.delete( tran );
    }


    /**
     * A stub method for implementing pre-commit assertions 
     * for this CandidateDO.
     * Implement this stub to throw an RefAssertionException for cases
     * where this object is not valid for writing to the database.
     */
    protected void okToCommit() 
    throws RefAssertionException { }

    /**
     * A stub method for implementing pre-delete assertions 
     * for this CandidateDO.
     * Implement this stub to throw an RefAssertionException for cases
     * where this object is not valid for deletion from the database.
     */
    protected void okToDelete() 
    throws RefAssertionException { }

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
   * The transaction is likely provided by the commit() method of another DO
   * which references this DO.
   * 
   * @param dbt The transaction object to use for this operation.
   * @exception com.lutris.appserver.server.sql.DatabaseManagerException if a Transaction can not be created.
   * @exception com.lutris.appserver.server.sql.DBRowUpdateException if a version error occurs.
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
   * The transaction is likely provided by the delete() method of another DO
   * which references this DO.
   *
   * @param dbt The transaction object to use for this operation.
   * @exception com.lutris.appserver.server.sql.DatabaseManagerException if a Transaction can not be created.
   * @exception com.lutris.appserver.server.sql.DBRowUpdateException if a version error occurs.
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
   * @exception com.lutris.appserver.server.sql.DBRowUpdateException if a version error occurs.
   * @exception RefAssertionException thrown by okTo method.
   * @exception java.sql.SQLException if any SQL errors occur.
   */
  protected void modifyDO( DBTransaction dbt, boolean delete )
  throws SQLException, DatabaseManagerException, DataObjectException, RefAssertionException, DBRowUpdateException, QueryException {
    if ( delete )
	okToDelete();
    else
	okToCommit();
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
	    jobmatch.data.CandidateAccountDO[] a = getCandidateAccountDOArray();
	    for ( int i = 0; i < a.length; i++ ) {
		a[ i ].delete( dbt );
	    }
	}
	
	
	{
	    // perform cascading delete on referring table
	    jobmatch.data.CandidateProfileDO[] a = getCandidateProfileDOArray();
	    for ( int i = 0; i < a.length; i++ ) {
		a[ i ].delete( dbt );
	    }
	}
	
	
	{
	    // perform cascading delete on referring table
	    jobmatch.data.MatchQueueDO[] a = getMatchQueueDOArray();
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
	  	jobmatch.data.PictureDO Picture_DO = getPicture();
	if ( null != Picture_DO ) {
	    if ( Picture_DO.isLoaded() ) {
		okToCommitPicture( Picture_DO );
		Picture_DO.commit( dbt );
	    } else {
		// since the referenced DO is not loaded,
		// it cannot be dirty, so there is no need to commit it.
	    }
	} else {
	    if ( ! true )
		throw new RefAssertionException(
		    "Cannot commit CandidateDO ( " + toString() +
		    " ) because Picture is not allowed to be null." );
	}
	jobmatch.data.AdressDO Adress_DO = getAdress();
	if ( null != Adress_DO ) {
	    if ( Adress_DO.isLoaded() ) {
		okToCommitAdress( Adress_DO );
		Adress_DO.commit( dbt );
	    } else {
		// since the referenced DO is not loaded,
		// it cannot be dirty, so there is no need to commit it.
	    }
	} else {
	    if ( ! true )
		throw new RefAssertionException(
		    "Cannot commit CandidateDO ( " + toString() +
		    " ) because Adress is not allowed to be null." );
	}
	jobmatch.data.CountryDO Nationality_DO = getNationality();
	if ( null != Nationality_DO ) {
	    if ( Nationality_DO.isLoaded() ) {
		okToCommitNationality( Nationality_DO );
		Nationality_DO.commit( dbt );
	    } else {
		// since the referenced DO is not loaded,
		// it cannot be dirty, so there is no need to commit it.
	    }
	} else {
	    if ( ! true )
		throw new RefAssertionException(
		    "Cannot commit CandidateDO ( " + toString() +
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
          dbt.delete( this );
      } else {
	  if ( isLoaded() )
	      dbt.insert( this );   // dbt.insert() handles insertions and updates
      }
      if (ownTransaction) {
	  dbt.commit(); // commit the transaction
      }
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
