// $Id: Account.java,v 1.2 2000/05/04 14:50:29 locher Exp $

/*
 * $Log: Account.java,v $
 * Revision 1.2  2000/05/04 14:50:29  locher
 * created little hierarchy
 *
 * Revision 1.1  2000/05/04 14:21:27  locher
 * makefiles
 *
 *
 */

package jobmatch.business.provider.account;

/**
 *  Account Interface
 *
 *  @since May 4 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.2 $
 **/
public interface Account {
    
    public final static int TYPE_CANDIDATE = 1;
    public final static int TYPE_COMPANY = 2;
    public final static int TYPE_PROVIDER = 3; 
    
    /** @post return == TYPE_CANDIDATE || return == TYPE_COMPANY || return == TYPE_PROVIDER **/
    public int getType();

    public String getUsername();
    public String getPassphrase();
    public String getEMail();

}
