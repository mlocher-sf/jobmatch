// $Id: TimeUtil.java,v 1.2 2000/06/02 15:24:13 loeffel Exp $

package jobmatch.business.util;

import java.sql.*;

/**
 *  Time Utilities
 *
 *  @since May 23 2000
 *  @author $Author: loeffel $
 *  @version $Revision: 1.2 $
 **/
public class TimeUtil {

    public static Date getDate(int year, int month, int day)  {
	return new Date(year, month, day); 
    }
    
    public static Timestamp getTimeNow() {
	return new Timestamp(System.currentTimeMillis());
    }
    
    public static int getYear(Date date) {
	return 0;
    }

    public static int getMonth(Date date) {
	return 0;
    }
    
    public static int getDay(Date date) {
	return 0;
    }
    
	
       
} //class

// Document history
/*
 * $Log: TimeUtil.java,v $
 * Revision 1.2  2000/06/02 15:24:13  loeffel
 * irgend
 *
 * Revision 1.1  2000/05/23 09:53:05  locher
 * time utilities
 *
 */
