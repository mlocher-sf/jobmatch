// $Id: Mailer.java,v 1.1 2000/05/19 10:59:33 locher Exp $

package jobmatch.business.service;

import jobmatch.data.*;
import com.lutris.dods.builder.generator.query.*;
import java.sql.Timestamp;

/**
 *  Controls all eMail issues
 *
 *  @since May 19 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
public class Mailer {

    private final static Mailer uniqueInstance;

    public final static int LOW_PRIORITY = 0;
    public final static int NORMAL_PRIORITY = 1;
    public final static int URGENT_PRIORITY = 2;
    
    static {
	uniqueInstance = new Mailer();
    }

    private Mailer() {
	super();
    }

    public static Mailer getUniqueInstance() {
	return uniqueInstance;
    }

    /** Schedules the mail with normal priority **/
    public void schedule(MailMessage mail) {
	this.schedule(mail, Mailer.NORMAL_PRIORITY);
    }

    /** Schedule the mail for sending **/
    public synchronized void schedule(MailMessage mail, int priority) {
	try {
	    MailQueueBDO job =  MailQueueBDO.createVirgin();
	    job.setTime(Mailer.getTimeNow());
	    job.setPriority(priority);
	    job.commit();
	} catch (Exception e) {
	    throw new RuntimeException(e.toString());
	}
    }

    /** @return the number of scheduled mails **/
    public int countScheduled() {
	try {
	    return MailQueueBDO.getBDOarray().length;
	} catch (Exception e) {
	    throw new RuntimeException(e.toString());
	}
    }

    /** cleans the scheduler table **/
    public synchronized void deleteAllScheduled() {
	try {
 	    MailQueueBDO[] jobs = MailQueueBDO.getBDOarray();
 	    for(int i=0; i < jobs.length; i++) {
 		jobs[i].delete();
 	    }
	} catch (Exception e) {
	    throw new RuntimeException(e.toString());
	}
    }

    private static Timestamp getTimeNow() {
	return new Timestamp(System.currentTimeMillis());
    }

} //class

/*
 * $Log: Mailer.java,v $
 * Revision 1.1  2000/05/19 10:59:33  locher
 * matcher and mailer service including test skeletons
 *
 */
