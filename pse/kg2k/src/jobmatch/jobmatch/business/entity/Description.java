// $Id: Description.java,v 1.2 2000/06/06 12:06:38 locher Exp $

package jobmatch.business.entity;

/**
 *  Interface for Constant Entities
 *
 *  @since May 26 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.2 $
 **/
public interface Description {
    
    public String getDescription() throws 
	com.lutris.dods.builder.generator.query.DataObjectException;
    
    public String getHandle() throws
	com.lutris.appserver.server.sql.DatabaseManagerException;

} //interface


/*
 * $Log: Description.java,v $
 * Revision 1.2  2000/06/06 12:06:38  locher
 * added getHandle
 *
 * Revision 1.1  2000/05/26 13:59:57  locher
 * introduced entity package
 *
 */
