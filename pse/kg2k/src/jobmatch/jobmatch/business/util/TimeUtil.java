// $Id: TimeUtil.java,v 1.1 2000/05/23 09:53:05 locher Exp $

package jobmatch.business.util;

import java.sql.*;

/**
 *  Time Utilities
 *
 *  @since May 23 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
public class TimeUtil {

    public static Date getDate(int year, int month, int day)  {
	return new Date(year, month, day); 
    }
    
    public static Timestamp getTimeNow() {
	return new Timestamp(System.currentTimeMillis());
    }
       
} //class

// Document history
/*
 * $Log: TimeUtil.java,v $
 * Revision 1.1  2000/05/23 09:53:05  locher
 * time utilities
 *
 */
