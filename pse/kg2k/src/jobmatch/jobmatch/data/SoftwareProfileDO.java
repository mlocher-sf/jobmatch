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
 * /scratch/studer_repositry/dataTest/jobmatch/data/SoftwareProfileDO.java
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
 * Data core class, used to set, retrieve the SoftwareProfileDO information.
 *
 * @version $Revision: 1.4 $
 * @author  studer
 * @since   jobmatch
 */
 public class SoftwareProfileDO extends jobmatch.data.ComputerRequestDO implements java.io.Serializable {

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
    static public final RDBTable  table = new RDBTable( "SoftwareProfile" );

    /**
     * Return SoftwareProfile as the name of the table in the database
     * which contains SoftwareProfileDO objects.
     * This method overrides CoreDO.getTableName()
     * and is used by CoreDO.executeUpdate() during error handling.
     *
     * @return the database table name
     * @see CoreDO
     * @author Jay Gunter
     */
    protected String getTableName() {
	return "SoftwareProfile";
    }

    static public final RDBColumn PrimaryKey = new RDBColumn( table,
					      GenericDO.getPrimaryKeyName() );
    /* RDBColumns for SoftwareProfileDO attributes are defined below. */

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
     * If the isView flag is true, SoftwareProfileDO.createExisting(ResultSet)
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
    private SoftwareProfileDataStruct data = null;

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
    protected SoftwareProfileDO ( boolean is_view )
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
    protected SoftwareProfileDO ()
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
	    super.loadData();
	    data = new SoftwareProfileDataStruct ();
	}

	ObjectId id = getOId();
	if ( null == id )
	    return;
	if ( ! isPersistent() )   // DO from createVirgin
	    return;

	// DO from createExisting.  Complain if no record in database.
	SoftwareProfileQuery query = new SoftwareProfileQuery ();
	query.setQueryOId( id );
	query.requireUniqueInstance();
	SoftwareProfileDO obj;
	try {
	    obj = query.getNextDO();
	    if ( null == obj )
		throw new DataObjectException(
		    "SoftwareProfileDO DO not found for id=" + id );
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
		    "Unable to load data for SoftwareProfileDO id=" + getOId() +
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
    protected SoftwareProfileDO( ObjectId id )
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
    public static SoftwareProfileDO createVirgin()
    throws DatabaseManagerException, ObjectIdException {
	return new SoftwareProfileDO ();
    }

    /**
     * createExisting( BigDecimal )
     *
     * Factory method creates a SoftwareProfileDO object by searching for it
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
    public static SoftwareProfileDO createExisting( BigDecimal bd )
    throws SQLException, ObjectIdException, DataObjectException, DatabaseManagerException
    {
	if ( null == bd )
	    return null;
	return createExisting( new ObjectId( bd ) );
    }

    /**
     * The createExisting method is used to create a <CODE>SoftwareProfileDO</CODE>
     * from a string handle.
     */
    public static SoftwareProfileDO createExisting( String handle ) {
	SoftwareProfileDO ret = null;
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
     * Factory method creates a SoftwareProfileDO object by searching for it
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
    protected static SoftwareProfileDO createExisting( ObjectId id )
    throws SQLException, ObjectIdException, DataObjectException, DatabaseManagerException
    {
	if ( null == id )
	    return null;
	SoftwareProfileDO ret = null;
	ret = new SoftwareProfileDO( id );
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
    protected static SoftwareProfileDO createExisting( ResultSet rs )
    throws SQLException, ObjectIdException, DataObjectException
		, DatabaseManagerException
    {
	if ( null == rs )
	    return null;
	SoftwareProfileDO ret = null;
	if ( isView ) {
	    ret = new SoftwareProfileDO ();
	    ret.initFromResultSet( rs );
	} else {
	    ret = new SoftwareProfileDO ( rs );
	}
	return ret;
    }

    /**
     * createExisting( RDBRow )
     *
     * Factory method creates a SoftwareProfileDO object by searching for it
     * in the database using the SoftwareProfileDO.PrimaryKey value
     * in the passed RDBRow.
     *
     * @param RDBRow A row returned by QueryBuilder.getNextRow().
     *
     * @exception DataObjectException
     *   If the RDBRow does not contain a SoftwareProfileDO.PrimaryKey.
     *   If the object is not found in the database.
     * @exception com.lutris.appserver.server.sql.ObjectIdException
     *   If an object id can't be allocated for this object.
     * @exception DatabaseManagerException
     *   If a connection to the database cannot be established, etc.
     * @exception SQLException
     *   If the database rejects the SQL generated to retrieve data
     *   for this object, or if the table contains a bad foreign key, etc.
     */
    protected static SoftwareProfileDO createExisting( RDBRow row )
    throws SQLException, ObjectIdException, DataObjectException
		, DatabaseManagerException
    {
	if ( null == row )
	    return null;
        RDBColumnValue pk = null;
        try {
	    pk = row.get( SoftwareProfileDO.PrimaryKey );
	    return createExisting( pk );
        } catch ( Exception e ) {
	    throw new DataObjectException(
		"Cannot create SoftwareProfileDO, row does not " +
		"contain SoftwareProfileDO primary key." );
        }
    }

    /**
     * createExisting( RDBColumnValue )
     *
     * Factory method creates a SoftwareProfileDO object by searching for it
     * in the database using the passed SoftwareProfileDO.PrimaryKey.
     *
     * @param RDBColumnValue a PrimaryKey column value from a row
     * that was returned by QueryBuilder.getNextRow().
     *
     * @exception DataObjectException
     *   If the RDBColumnValue does not contain a SoftwareProfileDO.PrimaryKey.
     *   If the object is not found in the database.
     * @exception com.lutris.appserver.server.sql.ObjectIdException
     *   If an object id can't be allocated for this object.
     * @exception DatabaseManagerException
     *   If a connection to the database cannot be established, etc.
     * @exception SQLException
     *   If the database rejects the SQL generated to retrieve data
     *   for this object, or if the table contains a bad foreign key, etc.
     */
    protected static SoftwareProfileDO createExisting( RDBColumnValue pk )
    throws SQLException, ObjectIdException, DataObjectException
		, DatabaseManagerException
    {
	if ( null == pk )
	    return null;
	if ( ! pk.equals( SoftwareProfileDO.PrimaryKey ) )
	    throw new DataObjectException(
		"Cannot create SoftwareProfileDO, " +
		"RDBColumnValue is not SoftwareProfileDO.PrimaryKey." );
	BigDecimal bd = null;
        try {
	    bd = pk.getBigDecimal();
        } catch ( Exception e ) {
	    throw new DataObjectException(
		"Cannot create SoftwareProfileDO, bad primary key." );
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
    public static SoftwareProfileDO createCopy( SoftwareProfileDataStruct data )
    throws DatabaseManagerException, ObjectIdException {
	SoftwareProfileDO ret = new SoftwareProfileDO ();
	ret.data = ( SoftwareProfileDataStruct ) data.duplicate();
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
    public static SoftwareProfileDO createCopy( SoftwareProfileDO orig )
    throws DatabaseManagerException, ObjectIdException {
	SoftwareProfileDO ret = new SoftwareProfileDO ();
	if ( null != orig.data ) {
	    ret.data = ( SoftwareProfileDataStruct ) orig.data.duplicate();
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
    protected void makeIdentical( SoftwareProfileDO orig ) {
	super.makeIdentical(orig);
	data = orig.data;
    }

////////////////////////// data member Software

   /* static final RDBColumn Software for use with QueryBuilder.
    * See RDBColumn PrimaryKey at the top of this file for usage example.
    */
   static public final RDBColumn Software = 
			    new RDBColumn( table, "Software" );

   /**
    * Get Software of the SoftwareProfile
    *
    * @return Software of the SoftwareProfile
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.SoftwareDO getSoftware () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      checkLoad();
      return data.Software;
   }

   /**
    * Set Software of the SoftwareProfile
    *
    * @param Software of the SoftwareProfile
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   
   public void setSoftware ( jobmatch.data.SoftwareDO Software )
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      checkLoad();
      data.Software = (jobmatch.data.SoftwareDO) markNewValue(
	data.Software, Software  );
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
    protected SoftwareProfileDO(ResultSet rs)
            throws SQLException, ObjectIdException, DataObjectException
 	    , DatabaseManagerException
    {
        super(rs);
	initFromResultSet( rs );
    }

    /**
     * initFromResultSet initializes the data members from SoftwareProfile.
     * This code was separated from the ResultSet constructor
     * so that createExisting(ResultSet) could handle VIEWs.
     */
    private void initFromResultSet( ResultSet rs )
            throws SQLException, ObjectIdException, DataObjectException
 	    , DatabaseManagerException
    {
	// Constructing a DO from a ResultSet means we definitely need the 
	// DataStruct ready for the setXxx methods invoked below.
	data = new SoftwareProfileDataStruct ();
 
	// writeMemberStuff uses the ResultSetExtraction.template
	// to build up the value for this tag:
	// the value is a series of calls to the DO set methods.
		
	setSoftware( 
	    jobmatch.data.SoftwareDO.createExisting( 
		rs.getBigDecimal( 
			"Software" , 0 )
	     )
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
	    "insert into SoftwareProfile ( LeafNumber, Profile, Mandatory, MinCapability, Software, " + getOIdColumnName() + ", " + getVersionColumnName() + " ) values ( ?, ?, ?, ?, ?, ?, ? )" );

	param = new int[1]; param[0] = 1;
	// writeMemberStuff uses the JDBCsetCalls.template
	// to build up the value for this tag:
	// the value is a series of calls to setPrepStmtParam_TYPE methods.
	// Those methods are defined in GenericDO.
	try {
	    	setPrepStmtParam_int( stmt, param,
		getLeafNumber() );
	setPrepStmtParam_DO( stmt, param,
		getProfile() );
	setPrepStmtParam_boolean( stmt, param,
		getMandatory() );
	setPrepStmtParam_DO( stmt, param,
		getMinCapability() );
	setPrepStmtParam_DO( stmt, param,
		getSoftware() );


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
	    "update SoftwareProfile set " + getVersionColumnName() + " = ?, LeafNumber = ?, Profile = ?, Mandatory = ?, MinCapability = ?, Software = ? " +
	    "where " + getOIdColumnName() + " = ? and " + getVersionColumnName() + " = ?" );

	param = new int[1]; param[0] = 1;
	//int[] param = {1};
	// writeMemberStuff builds up the value for this tag:
	// the value is a series of calls to setPrepStmtParam_TYPE methods.
	// Those methods are defined below.
	try {
	    setPrepStmtParam_int( stmt, param, getNewVersion() );
	    	setPrepStmtParam_int( stmt, param,
		getLeafNumber() );
	setPrepStmtParam_DO( stmt, param,
		getProfile() );
	setPrepStmtParam_boolean( stmt, param,
		getMandatory() );
	setPrepStmtParam_DO( stmt, param,
		getMinCapability() );
	setPrepStmtParam_DO( stmt, param,
		getSoftware() );


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
            "delete from SoftwareProfile \n" +
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
	String str = "SoftwareProfileDO:";
	ObjectId oid = getOId();
	String id = "virgin";
	if ( null != oid ) 
	    id = oid.toString();
	str += " OID=" + id;
	if ( null != data ) 
	    str = str + "\n" + indent + "Software=" + ( null == data.Software ? null  : data.Software.toString( indentCount + 1 ) )
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
        String str = indent + "SoftwareProfileDO:";
        ObjectId oid = getOId();
        String id = "virgin";
        if ( null != oid )
            id = oid.toString();
        str += " OID=" + id;
        if ( null != data )
            str = str + "\n" + indent + "Software=" + ( null == data.Software ? null  : data.Software.toString( indentCount + 1 ) )
;
        return str + "\n" + indent + "SUPER=" + super.toString( indentCount );
        //return str;
    }

    



    /**
     * A stub method for implementing pre-commit assertions 
     * for this SoftwareProfileDO.
     * Implement this stub to throw an RefAssertionException for cases
     * where this object is not valid for writing to the database.
     */
    protected void okToCommit() 
    throws RefAssertionException { }

    /**
     * A stub method for implementing pre-delete assertions 
     * for this SoftwareProfileDO.
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
     * for the Software data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where Software is not valid for writing to the database.
     */
    protected void okToCommitSoftware( jobmatch.data.SoftwareDO member ) 
    throws RefAssertionException { }

    /**
     * A stub method for implementing pre-delete assertions 
     * for the Software data member.
     * Implement this stub to throw an RefAssertionException for cases
     * where Software is not valid for deletion from the database.
     */
    protected void okToDeleteSoftware( jobmatch.data.SoftwareDO member ) 
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
	  
	  // The following line keeps the compiler happy 
	  // when the CASCADING_DELETES tag is empty.
          if ( false )
	      throw new QueryException("XXX");
      } else {
	  // commit referenced DOs.
	  	jobmatch.data.ProfileDO Profile_DO = getProfile();
	if ( null != Profile_DO ) {
	    if ( Profile_DO.isLoaded() ) {
		okToCommitProfile( Profile_DO );
		Profile_DO.commit( dbt );
	    } else {
		// since the referenced DO is not loaded,
		// it cannot be dirty, so there is no need to commit it.
	    }
	} else {
	    if ( ! false )
		throw new RefAssertionException(
		    "Cannot commit SoftwareProfileDO ( " + toString() +
		    " ) because Profile is not allowed to be null." );
	}
	jobmatch.data.CompcapabilityDO MinCapability_DO = getMinCapability();
	if ( null != MinCapability_DO ) {
	    if ( MinCapability_DO.isLoaded() ) {
		okToCommitMinCapability( MinCapability_DO );
		MinCapability_DO.commit( dbt );
	    } else {
		// since the referenced DO is not loaded,
		// it cannot be dirty, so there is no need to commit it.
	    }
	} else {
	    if ( ! false )
		throw new RefAssertionException(
		    "Cannot commit SoftwareProfileDO ( " + toString() +
		    " ) because MinCapability is not allowed to be null." );
	}
	jobmatch.data.SoftwareDO Software_DO = getSoftware();
	if ( null != Software_DO ) {
	    if ( Software_DO.isLoaded() ) {
		okToCommitSoftware( Software_DO );
		Software_DO.commit( dbt );
	    } else {
		// since the referenced DO is not loaded,
		// it cannot be dirty, so there is no need to commit it.
	    }
	} else {
	    if ( ! false )
		throw new RefAssertionException(
		    "Cannot commit SoftwareProfileDO ( " + toString() +
		    " ) because Software is not allowed to be null." );
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
