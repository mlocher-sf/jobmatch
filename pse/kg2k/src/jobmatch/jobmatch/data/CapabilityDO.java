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
 * /scratch/studer_repositry/dataTest/jobmatch/data/CapabilityDO.java
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
 * Data core class, used to set, retrieve the CapabilityDO information.
 *
 * @version $Revision: 1.1 $
 * @author  studer
 * @since   jobmatch
 */
abstract public class CapabilityDO extends jobmatch.data.ConstantTableDO implements java.io.Serializable {

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
    static public final RDBTable  table = new RDBTable( "NewDBTable" );

    /**
     * Return NewDBTable as the name of the table in the database
     * which contains CapabilityDO objects.
     * This method overrides CoreDO.getTableName()
     * and is used by CoreDO.executeUpdate() during error handling.
     *
     * @return the database table name
     * @see CoreDO
     * @author Jay Gunter
     */
    protected String getTableName() {
	return "NewDBTable";
    }

    static public final RDBColumn PrimaryKey = new RDBColumn( table,
					      GenericDO.getPrimaryKeyName() );
    /* RDBColumns for CapabilityDO attributes are defined below. */

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
     * If the isView flag is true, CapabilityDO.createExisting(ResultSet)
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
    private CapabilityDataStruct data = null;

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
    protected CapabilityDO ( boolean is_view )
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
    protected CapabilityDO ()
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
	    data = new CapabilityDataStruct ();
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
		    "Unable to load data for CapabilityDO id=" + getOId() +
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
    protected CapabilityDO( ObjectId id )
    throws SQLException, ObjectIdException, DataObjectException, DatabaseManagerException
    {
	super( id );
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
    protected void makeIdentical( CapabilityDO orig ) {
	super.makeIdentical(orig);
	data = orig.data;
    }

////////////////////////// data member Ordinal

   /* static final RDBColumn Ordinal for use with QueryBuilder.
    * See RDBColumn PrimaryKey at the top of this file for usage example.
    */
   static public final RDBColumn Ordinal = 
			    new RDBColumn( table, "Ordinal" );

   /**
    * Get Ordinal of the NewDBTable
    *
    * @return Ordinal of the NewDBTable
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   public int getOrdinal () 
   throws DataObjectException {
      beforeAnyGet();	// business actions/assertions prior to data return
      checkLoad();
      return data.Ordinal;
   }

   /**
    * Set Ordinal of the NewDBTable
    *
    * @param Ordinal of the NewDBTable
    *
    * @exception DataObjectException
    *   If the object is not found in the database.
    */
   
   public void setOrdinal ( int Ordinal )
   throws DataObjectException {
      try {
	  // business actions/assertions prior to data assignment
	  beforeAnySet();
      } catch ( Exception e ) { 
	  throw new DataObjectException( "beforeAnySet: " + e.getMessage() );
      }
      checkLoad();
      data.Ordinal =  markNewValue(
	data.Ordinal, Ordinal  );
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
    protected CapabilityDO(ResultSet rs)
            throws SQLException, ObjectIdException, DataObjectException
 	    , DatabaseManagerException
    {
        super(rs);
	initFromResultSet( rs );
    }

    /**
     * initFromResultSet initializes the data members from NewDBTable.
     * This code was separated from the ResultSet constructor
     * so that createExisting(ResultSet) could handle VIEWs.
     */
    private void initFromResultSet( ResultSet rs )
            throws SQLException, ObjectIdException, DataObjectException
 	    , DatabaseManagerException
    {
	// Constructing a DO from a ResultSet means we definitely need the 
	// DataStruct ready for the setXxx methods invoked below.
	data = new CapabilityDataStruct ();
 
	// writeMemberStuff uses the ResultSetExtraction.template
	// to build up the value for this tag:
	// the value is a series of calls to the DO set methods.
		
	setOrdinal( 
	    
		rs.getInt( 
			"Ordinal"  )
	    
	);
	

 
        markClean();
    }        


    


    /*
     * toString - for debugging
     */
/*
    public String toString(){
	String str = "CapabilityDO:";
	ObjectId oid = getOId();
	String id = "virgin";
	if ( null != oid ) 
	    id = oid.toString();
	str += " OID=" + id;
	if ( null != data ) 
	    str = str + "\n" + indent + "Ordinal=" + data.Ordinal
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
        String str = indent + "CapabilityDO:";
        ObjectId oid = getOId();
        String id = "virgin";
        if ( null != oid )
            id = oid.toString();
        str += " OID=" + id;
        if ( null != data )
            str = str + "\n" + indent + "Ordinal=" + data.Ordinal
;
        return str + "\n" + indent + "SUPER=" + super.toString( indentCount );
        //return str;
    }

    



    /**
     * A stub method for implementing pre-commit assertions 
     * for this CapabilityDO.
     * Implement this stub to throw an RefAssertionException for cases
     * where this object is not valid for writing to the database.
     */
    protected void okToCommit() 
    throws RefAssertionException { }

    /**
     * A stub method for implementing pre-delete assertions 
     * for this CapabilityDO.
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
