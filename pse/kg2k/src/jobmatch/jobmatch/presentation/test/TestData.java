// $Id: TestData.java,v 1.1 2000/05/23 08:27:32 studer Exp $

package jobmatch.presentation.test;

import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;
import jobmatch.data.*;
import java.sql.*;

/**
 *  Test the CandidateBDO Class
 *
 *  @since May 14 2000
 *  @author $Author: studer $
 *  @version $Revision: 1.1 $
 **/
public class TestData  implements HttpPresentation {
    public final int DATA_COUNT = 10;
    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {
        TestDataHTML page = (TestDataHTML)comms.xmlcFactory.create(TestDataHTML.class);
	insertCandidates();
	page.setTextCandidate("Inserting candidates");
        comms.response.writeHTML(page);
    }
  
 


    private  void insertCandidates(){
	String username = "muster";
	String email = "hans.muster@unibe.ch";
	String passphrase = "hans";
	int login_reminder = 0;
	String lname = "Muster";
	String	fname = "Hans";
	String	sex = "m";
	String	street = "Breitenweg";
	String	house_no = "1";
	String	city = "Bern";
	String	fax = "1234345";
	String	residence = "Bern";
	String	nationality = "Schweizer";
	String	competence = "zuverlaessig";
	int plz = 1000;		
	for ( int i=1;i<=DATA_COUNT;i++){
	    try {
		CandidateBDO candidate =  CandidateBDO.createVirgin(); 
		candidate.setLname(lname + i);
		candidate.setFname(fname +i);
		candidate.setSex(sex);
		candidate.setStreet(street + i);
		candidate.setHouseNumber(house_no+ i );
		candidate.setCity(city+ i );
		candidate.setPLZ(plz+ i);
		candidate.setBirthdate(new Date(200,4,i));
		candidate.setFax(fax+ i);
		candidate.setResidence(residence + i);
		candidate.setNationality(nationality + i);
		candidate.setCompetence(competence + i);
		candidate.setStatus(true);
		CandidateAccountBDO candidate_account = CandidateAccountBDO.createVirgin();
		candidate_account.setUsername(username + i);
		candidate_account.setCandidate(candidate);
		candidate_account.setLoginReminder(login_reminder + i);
		candidate_account.setLastLogin(new Timestamp(2000,4,6,13,i,0,0));
		candidate_account.setEmail(email + i);
		candidate_account.setPassphrase(passphrase + i);
		candidate_account.commit();
		candidate.commit();
	    } catch (Exception e) {
		System.err.println(e);
		// should thrown a jobmatchException
	    }
	}
    }



  

} //class

// Document history
/*
 * $Log: TestData.java,v $
 * Revision 1.1  2000/05/23 08:27:32  studer
 * TestData added
 *
 * Revision 1.4  2000/05/16 07:39:51  locher
 * test operation nodes
 *
 * Revision 1.3  2000/05/16 07:20:30  locher
 * match tree
 *
 * Revision 1.2  2000/05/14 17:09:26  locher
 * begin matchtree
 *
 * Revision 1.1  2000/05/14 16:18:26  locher
 * display test results
 *
 */
