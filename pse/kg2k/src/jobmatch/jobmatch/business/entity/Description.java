// $Id: Description.java,v 1.1 2000/05/26 13:59:57 locher Exp $

package jobmatch.business.entity;

/**
 *  Interface for Constant Entities
 *
 *  @since May 26 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
public interface Description {
    
    public String getDescription() throws 
	com.lutris.dods.builder.generator.query.DataObjectException;
    
} //interface


/*
 * $Log: Description.java,v $
 * Revision 1.1  2000/05/26 13:59:57  locher
 * introduced entity package
 *
 */
