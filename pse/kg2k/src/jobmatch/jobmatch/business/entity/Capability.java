// $Id: Capability.java,v 1.1 2000/06/09 12:00:16 studer Exp $

package jobmatch.business.entity;

/**
 *  Interface for Capabilities 
 *
 *  @since June 9 2000
 *  @author $Author: studer $
 *  @version $Revision: 1.1 $
 **/
public interface Capability extends Description{
    
    public int getOrdinal()throws
	com.lutris.dods.builder.generator.query.DataObjectException;
} //interface


