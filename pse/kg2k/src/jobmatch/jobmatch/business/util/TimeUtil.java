// $Id: TimeUtil.java,v 1.3 2000/06/05 11:38:45 loeffel Exp $

package jobmatch.business.util;

import java.sql.*;

/**
 *  Time Utilities
 *
 *  @since May 23 2000
 *  @author $Author: loeffel $
 *  @version $Revision: 1.3 $
 **/
public class TimeUtil {

    public static Date getDate(int year, int month, int day)  {
	return new Date(year, month, day); 
    }
    
    public static Timestamp getTimeNow() {
	return new Timestamp(System.currentTimeMillis());
    }
    
    public static int getYear(Date date) {
	return date.getYear();
    }

    public static int getMonth(Date date) {
	return date.getMonth();
    }
    
    public static int getDay(Date date) {
	// Man glaube oder auch nicht: getDate gibt den TAG des Monats zurueck
	return date.getDate();
    }
    
	
       
} //class

// Document history
/*
 * $Log: TimeUtil.java,v $
 * Revision 1.3  2000/06/05 11:38:45  loeffel
 * Set some temporary methods to return date
 *
 * Revision 1.2  2000/06/02 15:24:13  loeffel
 * irgend
 *
 * Revision 1.1  2000/05/23 09:53:05  locher
 * time utilities
 *
 */
