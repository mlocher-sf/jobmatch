// $Id: Account.java,v 1.4 2000/05/23 14:10:55 locher Exp $

/*
 * $Log: Account.java,v $
 * Revision 1.4  2000/05/23 14:10:55  locher
 * authentification mechanisms
 *
 * Revision 1.3  2000/05/10 17:50:22  locher
 * login procedure
 *
 * Revision 1.2  2000/05/04 14:50:29  locher
 * created little hierarchy
 *
 * Revision 1.1  2000/05/04 14:21:27  locher
 * makefiles
 *
 *
 */

package jobmatch.business.provider.account;

import com.lutris.dods.builder.generator.query.DataObjectException;

/**
 *  Account Interface
 *
 *  @since May 4 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.4 $
 **/
public interface Account {
    
    public final static int TYPE_CANDIDATE = 1;
    public final static int TYPE_COMPANY = 2;
    public final static int TYPE_PROVIDER = 4; 
    
    /** @post return == TYPE_CANDIDATE || return == TYPE_COMPANY || return == TYPE_PROVIDER **/
    public int getType();

    public String getUsername() throws DataObjectException;
    public String getPassphrase() throws DataObjectException;
    //public String getEMail() throws DataObjectException;

}
