package jobmatch.presentation;

import jobmatch.business.candidate.*;
import jobmatch.business.provider.account.*;
import org.w3c.dom.html.*;
import java.sql.Date;
import java.util.Collection;
import jobmatch.business.entity.*;
import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;
import jobmatch.business.util.TimeUtil;

public class CVPersonal extends CVSection implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {
	
	this.assertLegitimation(comms, Account.TYPE_CANDIDATE, "Welcome.po");

	Candidate candidate = this.getCandidateAccount(comms).getCandidateBO();
        CVPersonalHTML page = (CVPersonalHTML) comms.xmlcFactory.create(CVPersonalHTML.class);

	final String action = comms.request.getParameter("action");
	if (action != null && action.equals("write")){ 
	    this.processData(page, candidate, comms);
	}

	this.fillCountry(page, candidate);
	this.fillNationality(page, candidate);
	this.fillPage(page, candidate, comms);

	comms.response.writeHTML(page);
    }

    /**
     * Fills the text fields with CV-data 
     **/
    private void processData(CVPersonalHTML page, Candidate candidate, HttpPresentationComms comms) {
	try{
	    candidate.setLname(comms.request.getParameter("lName"));
	    candidate.setFname(comms.request.getParameter("fName"));
	    candidate.setSex(comms.request.getParameter("sex"));
	    candidate.setBirthdate(TimeUtil.getDate(Integer.parseInt(comms.request.getParameter("year")),
						    Integer.parseInt(comms.request.getParameter("month")),
						    Integer.parseInt(comms.request.getParameter("day"))));
	    candidate.setResidence(comms.request.getParameter("residence"));
	    candidate.setNationality(Country.getCountry(comms.request.getParameter("nationality")));

	    Address addr = candidate.getAddressBO();
	    addr.setStreet(comms.request.getParameter("street"));
	    addr.setHouseNumber(comms.request.getParameter("housenumber"));
	    addr.setZIP(comms.request.getParameter("zip"));
	    addr.setCity(comms.request.getParameter("city"));
	    addr.setCountry(Country.getCountry(comms.request.getParameter("country")));
	    addr.commit();
	    candidate.setPhone(comms.request.getParameter("phonenumber"));
	    candidate.setFax(comms.request.getParameter("faxnumber"));
	    candidate.setNatel(comms.request.getParameter("natel"));
	    candidate.commit();
	}
	catch(Exception e) {
	    System.out.println(e.toString());
	    throw new RuntimeException();
	}
    }
    
    private void fillPage(CVPersonalHTML page, Candidate candidate, HttpPresentationComms comms) {
	try {

	    page.getElementLName().setValue(candidate.getLname());
	    page.getElementFName().setValue(candidate.getFname());
	    
	    if (candidate.isMale()) {
		page.getElementWsex().setChecked(false);
		page.getElementMsex().setChecked(true);
	    } else {
		page.getElementWsex().setChecked(true);
		page.getElementMsex().setChecked(false);
	    }
		
	    page.getElementYear().setValue(splitDate(candidate.getBirthdate(), 3));
	    page.getElementMonth().setValue(splitDate(candidate.getBirthdate(), 2));
	    page.getElementDay().setValue(splitDate(candidate.getBirthdate(), 1));
	    
	    page.getElementResidence().setValue(candidate.getResidence());

	    Address addr = candidate.getAddressBO();
	    page.getElementStreet().setValue(addr.getStreet());
	    page.getElementHousenumber().setValue(addr.getHouseNumber());
	    page.getElementZip().setValue(String.valueOf(addr.getZIP()));
	    page.getElementCity().setValue(addr.getCity());

	    page.getElementPhonenumber().setValue(candidate.getPhone());
	    page.getElementFaxnumber().setValue(candidate.getFax());
	    page.getElementNatel().setValue(candidate.getNatel());
	    comms.response.writeHTML(page);
    
	}
	catch(Exception e) {
	    System.out.println(e.toString());
	    throw new RuntimeException();
	}
    }

    private String splitDate(Date d, int selector){
	String result;
	switch (selector) {
	case 3:
	    result = String.valueOf(TimeUtil.getYear(d));
	    break;
	case 2:
	    result = String.valueOf(TimeUtil.getMonth(d));
	    break;
	case 1:
	    result = String.valueOf(TimeUtil.getDay(d));
	    break;
	default:
	    result = d.toString();
	}
	return result;
    }

    /**
     * fills the nationality-list with CV-data
     **/
    private void fillNationality(CVPersonalHTML page, Candidate candidate){
	final EntityManager man = EntityManager.getUniqueInstance();
	Collection data =  man.getCountries();
	Country selection = candidate.getNationalityBO();
	HTMLOptionElement template = page.getElementTemplateNationality();
	this.fillListBox(template, data, selection);
    }

    /**
     * fills the country-list with the CV-data
     **/
    private void fillCountry(CVPersonalHTML page, Candidate candidate){
	final EntityManager man = EntityManager.getUniqueInstance();
	Collection data =  man.getCountries();
	Country selection = candidate.getAddressBO().getCountryBO();
	HTMLOptionElement template = page.getElementTemplateCountry();
	this.fillListBox(template, data, selection);
    }
    
} //class

// Document history
/*
 * $Log: CVPersonal.java,v $
 * Revision 1.12  2000/06/06 08:20:21  loeffel
 * Populated drop down menus
 *
 * Revision 1.10  2000/06/05 11:39:37  loeffel
 * CVPersonal should be finished: data can be inserted into the database
 * and is read back
 *
 * Revision 1.9  2000/06/04 11:50:43  locher
 * many little fixes
 *
 */
