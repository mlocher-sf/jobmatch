// $Id: Account.java,v 1.6 2000/06/02 14:56:13 locher Exp $


package jobmatch.business.provider.account;

import com.lutris.dods.builder.generator.query.DataObjectException;
import java.sql.Timestamp;

/**
 *  Account Interface
 *
 *  @since May 4 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.6 $
 **/
public interface Account {
    
    public final static int TYPE_CANDIDATE = 1;
    public final static int TYPE_COMPANY = 2;
    public final static int TYPE_PROVIDER = 4; 
    
    /** @post return == TYPE_CANDIDATE || return == TYPE_COMPANY || return == TYPE_PROVIDER **/
    public int getType();

    public String getUsername() throws DataObjectException;
    public String getPassphrase() throws DataObjectException;
    public String getEmail() throws DataObjectException;
    public void updateLoginData(Timestamp time, String host, String ip);
}

/*
 * $Log: Account.java,v $
 * Revision 1.6  2000/06/02 14:56:13  locher
 * extended login behaviour
 *
 * Revision 1.5  2000/05/31 12:15:54  studer
 * Javadoc added
 *
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
