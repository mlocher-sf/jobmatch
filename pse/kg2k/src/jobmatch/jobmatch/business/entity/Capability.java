// $Id: Capability.java,v 1.3 2000/06/10 13:35:38 studer Exp $

package jobmatch.business.entity;

import java.util.List;

/**
 *  Interface for Capabilities 
 *
 *  @since June 9 2000
 *  @author $Author: studer $
 *  @version $Revision: 1.3 $
 **/
public interface Capability extends Description{
    
    public int getOrdinal()throws
	com.lutris.dods.builder.generator.query.DataObjectException;


} //interface


