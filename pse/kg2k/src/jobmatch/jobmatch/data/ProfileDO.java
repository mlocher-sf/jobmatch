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
 * /scratch/studer_repositry/dataTest/jobmatch/data/ProfileDO.java
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
 * Data core class, used to set, retrieve the ProfileDO information.
 *
 * @version $Revision: 1.3 $
 * @author  studer
 * @since   jobmatch
 */
 public class ProfileDO extends com.lutris.dods.builder.generator.dataobject.GenericDO implements java.io.Serializable {

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
    static public final RDBTable  table = new RDBTable( "Profile" );

    /**
     * Return Profile as the name of the table in the database
     * which contains ProfileDO objects.
     * This method overrides CoreDO.getTableName()
     * and is used by CoreDO.executeUpdate() during error handling.
     *
     * @return the database table name
     * @see CoreDO
     * @author Jay Gunter
     */
    protected String getTableName() {
	return "Profile";
    }

    static public final RDBColumn PrimaryKey = new RDBColumn( table,
					      GenericDO.getPrimaryKeyName() );
    /* RDBColumns for ProfileDO attributes are defined below. */

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
     * If the isView flag is true, ProfileDO.createExisting(ResultSet)
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
    private ProfileDataStruct data = null;

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
    protected ProfileDO ( boolean is_view )
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
    protected ProfileDO ()
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
	    
	    data = new ProfileDataStruct ();
	}

	ObjectId id = getOId();
	if ( null == id )
	    return;
	if ( ! isPersistent() )   // DO from createVirgin
	    return;

	// DO from createExisting.  Complain if no record in database.
	ProfileQuery query = new ProfileQuery ();
	query.setQueryOId( id );
	query.requireUniqueInstance();
	ProfileDO obj;
	try {
	    obj = query.getNextDO();
	    if ( null == obj )
		throw new DataObjectException(
		    "ProfileDO DO not found for id=" + id );
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
		    "Unable to load data for ProfileDO id=" + getOId() +
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
    protected ProfileDO( ObjectId id )
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
    public static ProfileDO createVirgin()
    throws DatabaseManagerException, ObjectIdException {
	return new ProfileDO ();
    }

    /**
     * createExisting( BigDecimal )
     *
     * Factory method creates a ProfileDO object by searching for it
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
    public static ProfileDO createExisting( BigDecimal bd )
    throws SQLException, ObjectIdException, DataObjectException, DatabaseManagerException
    {
	if ( null == bd )
	    return null;
	return createExisting( new ObjectId( bd ) );
    }

    /**
     * The createExisting method is used to create a <CODE>ProfileDO</CODE>
     * from a string handle.
     */
    public static ProfileDO createExisting( String handle ) {
	ProfileDO ret = null;
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
     * Factory method creates a ProfileDO object by searching for it
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
    protected static ProfileDO createExisting( ObjectId id )
    throws SQLException, ObjectIdException, DataObjectException, DatabaseManagerException
    {
	if ( null == id )
	    return null;
	ProfileDO ret = null;
	ret = new ProfileDO( id );
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
    protected static ProfileDO createExisting( ResultSet rs )
    throws SQLException, ObjectIdException, DataObjectException
		, DatabaseManagerException
    {
	if ( null == rs )
	    return null;
	ProfileDO ret = null;
	if ( isView ) {
	    ret = new ProfileDO ();
	    ret.initFromResultSet( rs );
	} else {
	    ret = new ProfileDO ( rs );
	}
	return ret;
    }

    /**
     * createExisting( RDBRow )
     *
     * Factory method creates a ProfileDO object by searching for it
     * in the database using the ProfileDO.PrimaryKey value
     * in the passed RDBRow.
     *
     * @param RDBRow A row returned by QueryBuilder.getNextRow().
     *
     * @exception DataObjectException
     *   If the RDBRow does not contain a ProfileDO.PrimaryKey.
     *   If the object is not found in the database.
     * @exception com.lutris.appserver.server.sql.ObjectIdException
     *   If an object id can't be allocated for this object.
     * @exception DatabaseManagerException
     *   If a connection to the database cannot be established, etc.
     * @exception SQLException
     *   If the database rejects the SQL generated to retrieve data
     *   for this object, or if the table contains a bad foreign key, etc.
     */
    protected static ProfileDO createExisting( RDBRow row )
    throws SQLException, ObjectIdException, DataObjectException
		, DatabaseManagerException
    {
	if ( null == row )
	    return null;
        RDBColumnValue pk = null;
        try {
	    pk = row.get( ProfileDO.PrimaryKey );
	    return createExisting( pk );
        } catch ( Exception e ) {
	    throw new DataObjectException(
		"Cannot create ProfileDO, row does not " +
		"contain ProfileDO primary key." );
        }
    }

    /**
     * createExisting( RDBColumnValue )
     *
     * Factory method creates a ProfileDO object by searching for it
     * in the database using the passed ProfileDO.PrimaryKey.
     *
     * @param RDBColumnValue a PrimaryKey column value from a row
     * that was returned by QueryBuilder.getNextRow().
     *
     * @exception DataObjectException
     *   If the RDBColumnValue does not contain a ProfileDO.PrimaryKey.
     *   If the object is not found in the database.
     * @exception com.lutris.appserver.server.sql.ObjectIdException
     *   If an object id can't be allocated for this object.
     * @exception DatabaseManagerException
     *   If a connection to the database cannot be established, etc.
     * @exception SQLException
     *   If the database rejects the SQL generated to retrieve data
     *   for this object, or if the table contains a bad foreign key, etc.
     */
    protected static ProfileDO createExisting( RDBColumnValue pk )
    throws SQLException, ObjectIdException, DataObjectException
		, DatabaseManagerException
    {
	if ( null == pk )
	    return null;
	if ( ! pk.equals( ProfileDO.PrimaryKey ) )
	    throw new DataObjectException(
		"Cannot create ProfileDO, " +
		"RDBColumnValue is not ProfileDO.PrimaryKey." );
	BigDecimal bd = null;
        try {
	    bd = pk.getBigDecimal();
        } catch ( Exception e ) {
	    throw new DataObjectException(
		"Cannot create ProfileDO, bad primary key." );
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
    public static ProfileDO createCopy( ProfileDataStruct data )
    throws DatabaseManagerException, ObjectIdException {
	ProfileDO ret = new ProfileDO ();
	ret.data = ( ProfileDataStruct ) data.duplicate();
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
    public static ProfileDO createCopy( ProfileDO orig )
    throws DatabaseManagerException, ObjectIdException {
	ProfileDO ret = new ProfileDO ();
	if ( null != orig.data ) {
	    ret.data = ( ProfileDataStruct ) orig.data.duplicate();
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
    protected void makeIdentical( ProfileDO orig ) {
	super.makeIdentical(orig);
	data = orig.data;
    }

////////////////////////// data member Name

   /* static final RDBColumn Name for use with QueryBuilder.
    * See RDBColumn PrimaryKey at the top of this file for usage example.
    */
   static public final RDBColumn Name = 
			    new RDBColumn( table, "Name" );

   /**
    * Get Name of the Profile
    *
    * @return Name of the Profile
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public String getName () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      checkLoad();
      return data.Name;
   }

   /**
    * Set Name of the Profile
    *
    * @param Name of the Profile
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
      checkLoad();
      data.Name =  markNewValue(
	data.Name, Name , 0, 40, false );
      afterAnySet();	// business actions/assertions after data assignment
   }
   


////////////////////////// data member Company

   /* static final RDBColumn Company for use with QueryBuilder.
    * See RDBColumn PrimaryKey at the top of this file for usage example.
    */
   static public final RDBColumn Company = 
			    new RDBColumn( table, "Company" );

   /**
    * Get Company of the Profile
    *
    * @return Company of the Profile
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public jobmatch.data.CompanyDO getCompany () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      checkLoad();
      return data.Company;
   }

   /**
    * Set Company of the Profile
    *
    * @param Company of the Profile
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
      checkLoad();
      data.Company = (jobmatch.data.CompanyDO) markNewValue(
	data.Company, Company  );
      afterAnySet();	// business actions/assertions after data assignment
   }
   


////////////////////////// data member NeedsRematching

   /* static final RDBColumn NeedsRematching for use with QueryBuilder.
    * See RDBColumn PrimaryKey at the top of this file for usage example.
    */
   static public final RDBColumn NeedsRematching = 
			    new RDBColumn( table, "NeedsRematching" );

   /**
    * Get NeedsRematching of the Profile
    *
    * @return NeedsRematching of the Profile
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public boolean getNeedsRematching () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      checkLoad();
      return data.NeedsRematching;
   }

   /**
    * Set NeedsRematching of the Profile
    *
    * @param NeedsRematching of the Profile
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
      checkLoad();
      data.NeedsRematching =  markNewValue(
	data.NeedsRematching, NeedsRematching  );
      afterAnySet();	// business actions/assertions after data assignment
   }
   


////////////////////////// data member Notify

   /* static final RDBColumn Notify for use with QueryBuilder.
    * See RDBColumn PrimaryKey at the top of this file for usage example.
    */
   static public final RDBColumn Notify = 
			    new RDBColumn( table, "Notify" );

   /**
    * Get Notify of the Profile
    *
    * @return Notify of the Profile
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public boolean getNotify () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      checkLoad();
      return data.Notify;
   }

   /**
    * Set Notify of the Profile
    *
    * @param Notify of the Profile
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
      checkLoad();
      data.Notify =  markNewValue(
	data.Notify, Notify  );
      afterAnySet();	// business actions/assertions after data assignment
   }
   


////////////////////////// data member Period

   /* static final RDBColumn Period for use with QueryBuilder.
    * See RDBColumn PrimaryKey at the top of this file for usage example.
    */
   static public final RDBColumn Period = 
			    new RDBColumn( table, "Period" );

   /**
    * Get Period of the Profile
    *
    * @return Period of the Profile
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public int getPeriod () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      checkLoad();
      return data.Period;
   }

   /**
    * Set Period of the Profile
    *
    * @param Period of the Profile
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   
   public void setPeriod ( int Period )
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      checkLoad();
      data.Period =  markNewValue(
	data.Period, Period  );
      afterAnySet();	// business actions/assertions after data assignment
   }
   


////////////////////////// data member MatchTree

   /* static final RDBColumn MatchTree for use with QueryBuilder.
    * See RDBColumn PrimaryKey at the top of this file for usage example.
    */
   static public final RDBColumn MatchTree = 
			    new RDBColumn( table, "MatchTree" );

   /**
    * Get MatchTree of the Profile
    *
    * @return MatchTree of the Profile
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public byte[] getMatchTree () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      checkLoad();
      return data.MatchTree;
   }

   /**
    * Set MatchTree of the Profile
    *
    * @param MatchTree of the Profile
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
      checkLoad();
      data.MatchTree =  markNewValue(
	data.MatchTree, MatchTree  );
      afterAnySet();	// business actions/assertions after data assignment
   }
   


////////////////////////// data member LastNotification

   /* static final RDBColumn LastNotification for use with QueryBuilder.
    * See RDBColumn PrimaryKey at the top of this file for usage example.
    */
   static public final RDBColumn LastNotification = 
			    new RDBColumn( table, "LastNotification" );

   /**
    * Get LastNotification of the Profile
    *
    * @return LastNotification of the Profile
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public java.sql.Timestamp getLastNotification () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      checkLoad();
      return data.LastNotification;
   }

   /**
    * Set LastNotification of the Profile
    *
    * @param LastNotification of the Profile
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
      checkLoad();
      data.LastNotification =  markNewValue(
	data.LastNotification, LastNotification  );
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
    protected ProfileDO(ResultSet rs)
            throws SQLException, ObjectIdException, DataObjectException
 	    , DatabaseManagerException
    {
        super(rs);
	initFromResultSet( rs );
    }

    /**
     * initFromResultSet initializes the data members from Profile.
     * This code was separated from the ResultSet constructor
     * so that createExisting(ResultSet) could handle VIEWs.
     */
    private void initFromResultSet( ResultSet rs )
            throws SQLException, ObjectIdException, DataObjectException
 	    , DatabaseManagerException
    {
	// Constructing a DO from a ResultSet means we definitely need the 
	// DataStruct ready for the setXxx methods invoked below.
	data = new ProfileDataStruct ();
 
	// writeMemberStuff uses the ResultSetExtraction.template
	// to build up the value for this tag:
	// the value is a series of calls to the DO set methods.
		
	setName( 
	    
		rs.getString( 
			"Name"  )
	    
	);
	
	
	setCompany( 
	    jobmatch.data.CompanyDO.createExisting( 
		rs.getBigDecimal( 
			"Company" , 0 )
	     )
	);
	
	
	setNeedsRematching( 
	    
		rs.getBoolean( 
			"NeedsRematching"  )
	    
	);
	
	
	setNotify( 
	    
		rs.getBoolean( 
			"Notify"  )
	    
	);
	
	
	setPeriod( 
	    
		rs.getInt( 
			"Period"  )
	    
	);
	
	
	setMatchTree( 
	    
		rs.getBytes( 
			"MatchTree"  )
	    
	);
	
	
	setLastNotification( 
	    
		rs.getTimestamp( 
			"LastNotification"  )
	    
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
	    "insert into Profile ( Name, Company, NeedsRematching, Notify, Period, MatchTree, LastNotification, " + getOIdColumnName() + ", " + getVersionColumnName() + " ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ? )" );

	param = new int[1]; param[0] = 1;
	// writeMemberStuff uses the JDBCsetCalls.template
	// to build up the value for this tag:
	// the value is a series of calls to setPrepStmtParam_TYPE methods.
	// Those methods are defined in GenericDO.
	try {
	    	setPrepStmtParam_String( stmt, param,
		getName() );
	setPrepStmtParam_DO( stmt, param,
		getCompany() );
	setPrepStmtParam_boolean( stmt, param,
		getNeedsRematching() );
	setPrepStmtParam_boolean( stmt, param,
		getNotify() );
	setPrepStmtParam_int( stmt, param,
		getPeriod() );
	setPrepStmtParam_bytes( stmt, param,
		getMatchTree() );
	setPrepStmtParam_java_sql_Timestamp( stmt, param,
		getLastNotification() );


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
	    "update Profile set " + getVersionColumnName() + " = ?, Name = ?, Company = ?, NeedsRematching = ?, Notify = ?, Period = ?, MatchTree = ?, LastNotification = ? " +
	    "where " + getOIdColumnName() + " = ? and " + getVersionColumnName() + " = ?" );

	param = new int[1]; param[0] = 1;
	//int[] param = {1};
	// writeMemberStuff builds up the value for this tag:
	// the value is a series of calls to setPrepStmtParam_TYPE methods.
	// Those methods are defined below.
	try {
	    setPrepStmtParam_int( stmt, param, getNewVersion() );
	    	setPrepStmtParam_String( stmt, param,
		getName() );
	setPrepStmtParam_DO( stmt, param,
		getCompany() );
	setPrepStmtParam_boolean( stmt, param,
		getNeedsRematching() );
	setPrepStmtParam_boolean( stmt, param,
		getNotify() );
	setPrepStmtParam_int( stmt, param,
		getPeriod() );
	setPrepStmtParam_bytes( stmt, param,
		getMatchTree() );
	setPrepStmtParam_java_sql_Timestamp( stmt, param,
		getLastNotification() );


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
            "delete from Profile \n" +
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
	String str = "ProfileDO:";
	ObjectId oid = getOId();
	String id = "virgin";
	if ( null != oid ) 
	    id = oid.toString();
	str += " OID=" + id;
	if ( null != data ) 
	    str = str + "\n" + indent + "Name=" + data.Name
+ "\n" + indent + "Company=" + ( null == data.Company ? null  : data.Company.toString( indentCount + 1 ) )
+ "\n" + indent + "NeedsRematching=" + data.NeedsRematching
+ "\n" + indent + "Notify=" + data.Notify
+ "\n" + indent + "Period=" + data.Period
+ "\n" + indent + "MatchTree=" + data.MatchTree
+ "\n" + indent + "LastNotification=" + data.LastNotification
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
        String str = indent + "ProfileDO:";
        ObjectId oid = getOId();
        String id = "virgin";
        if ( null != oid )
            id = oid.toString();
        str += " OID=" + id;
        if ( null != data )
            str = str + "\n" + indent + "Name=" + data.Name
+ "\n" + indent + "Company=" + ( null == data.Company ? null  : data.Company.toString( indentCount + 1 ) )
+ "\n" + indent + "NeedsRematching=" + data.NeedsRematching
+ "\n" + indent + "Notify=" + data.Notify
+ "\n" + indent + "Period=" + data.Period
+ "\n" + indent + "MatchTree=" + data.MatchTree
+ "\n" + indent + "LastNotification=" + data.LastNotification
;
        return str + "\n" + indent + "SUPER=" + super.toString( indentCount );
        //return str;
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
	    q.setQueryProfile( this );
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
	q.setQueryProfile( this );
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
        referrer.setProfile( this );
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
	ProfileDO referred = referrer.getProfile();
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
     * get array of CandidateDO objects that indirectly refer
     * to this DO.
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
     * to this DO.
     *
     * @param d The CandidateDO to add to the CandidateProfileDO mapping
     * for this DO.
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
     * add a CandidateDO object that indirectly refers to this DO.
     *
     * @param b The CandidateDO to add to the CandidateProfileDO mapping for this DO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     */
    public void mapCandidate_via_CandidateProfileDO( jobmatch.data.CandidateDO d, DBTransaction tran )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	jobmatch.data.CandidateProfileDO m = null;
	try {
	    m = jobmatch.data.CandidateProfileDO.createVirgin();
	} catch ( Exception e ) { 
	    throw new DataObjectException( 
		"jobmatch.data.CandidateProfileDO.createVirgin failed", e );
	}
	m.setCandidate( d );
	m.setProfile( this );
	m.commit( tran );
    }

    /**
     * From the many-to-many relationship expressed by CandidateProfileDO,
     * remove (delete) the CandidateDO object that indirectly refers
     * to this DO.
     *
     * @param d The CandidateDO to remove from the CandidateProfileDO mapping
     * for this DO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception QueryException
     *   If an error occured while building the query before execution.
     */
    public void unmapCandidate_via_CandidateProfileDO( jobmatch.data.CandidateDO d )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	unmapCandidate_via_CandidateProfileDO( d, null );
    }

    /**
     * From the many-to-many relationship expressed by CandidateProfileDO,
     * remove (delete) the CandidateDO object that indirectly refers
     * to this DO.
     *
     * @param b The CandidateDO to remove from the CandidateProfileDO mapping
     * for this DO.
     *
     * @exception DataObjectException
     *   If the object is not found in the database.
     * @exception QueryException
     *   If an error occured while building the query before execution.
     */
    public void unmapCandidate_via_CandidateProfileDO( jobmatch.data.CandidateDO d, DBTransaction tran )
    throws DataObjectException, DatabaseManagerException, RefAssertionException, SQLException, DBRowUpdateException, QueryException {
	jobmatch.data.CandidateProfileQuery q = new jobmatch.data.CandidateProfileQuery();
	q.setQueryProfile( this );
	q.setQueryCandidate( d );
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
     * for this ProfileDO.
     * Implement this stub to throw an RefAssertionException for cases
     * where this object is not valid for writing to the database.
     */
    protected void okToCommit() 
    throws RefAssertionException { }

    /**
     * A stub method for implementing pre-delete assertions 
     * for this ProfileDO.
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
	    jobmatch.data.CandidateProfileDO[] a = getCandidateProfileDOArray();
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
	  	jobmatch.data.CompanyDO Company_DO = getCompany();
	if ( null != Company_DO ) {
	    if ( Company_DO.isLoaded() ) {
		okToCommitCompany( Company_DO );
		Company_DO.commit( dbt );
	    } else {
		// since the referenced DO is not loaded,
		// it cannot be dirty, so there is no need to commit it.
	    }
	} else {
	    if ( ! false )
		throw new RefAssertionException(
		    "Cannot commit ProfileDO ( " + toString() +
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
