// $Id: Capability.java,v 1.1 2000/06/20 13:32:56 pse4 Exp $

package jobmatch.business.entity;

import java.util.List;

/**
 *  Interface for Capabilities 
 *
 *  @since June 9 2000
 *  @author $Author: pse4 $
 *  @version $Revision: 1.1 $
 **/
public interface Capability extends Description{
    
    public int getOrdinal()throws
	com.lutris.dods.builder.generator.query.DataObjectException;


} //interface


