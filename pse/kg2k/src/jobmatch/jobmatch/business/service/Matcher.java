// $Id: Matcher.java,v 1.1 2000/05/19 10:59:34 locher Exp $

package jobmatch.business.service;

import jobmatch.data.*;
import com.lutris.dods.builder.generator.query.*;
import jobmatch.business.candidate.Candidate;
import java.sql.Timestamp;

/**
 *  Controls the matching process
 *
 *  @since May 19 2000
 *  @author $Author: locher $
 *  @version $Revision: 1.1 $
 **/
public class Matcher {

    private final static Matcher uniqueInstance;
    
    static {
	uniqueInstance = new Matcher();
    }

    private Matcher() {
	super();
    }

    public static Matcher getUniqueInstance() {
	return uniqueInstance;
    }

    /** Schedule the candidate for matching **/
    public synchronized void schedule(Candidate who) {
	try {
	    MatchQueueBDO job =  MatchQueueBDO.createVirgin();
	    job.setTime(Matcher.getTimeNow());
	    job.setCandidate(who);
	    job.commit();
	} catch (Exception e) {
	    throw new RuntimeException(e.toString());
	}
    }

    /** @return the number of scheduled candidates **/
    public int countScheduled() {
	try {
	    return MatchQueueBDO.getBDOarray().length;
	} catch (Exception e) {
	    throw new RuntimeException(e.toString());
	}
    }

    /** cleans the scheduler table **/
    public synchronized void deleteAllScheduled() {
	try {
	    MatchQueueBDO[] jobs = MatchQueueBDO.getBDOarray();
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
 * $Log: Matcher.java,v $
 * Revision 1.1  2000/05/19 10:59:34  locher
 * matcher and mailer service including test skeletons
 *
 */
