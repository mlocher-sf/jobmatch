package jobmatch.presentation;

import jobmatch.business.candidate.*;
import jobmatch.business.provider.account.*;
import org.w3c.dom.html.*;
import java.sql.Date;
import java.util.List;
import jobmatch.business.entity.*;
import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;
import jobmatch.business.util.TimeUtil;

public class CVPersonal extends CVSection implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {
	CandidateAccount account = getCandidateAccount(comms);
	Candidate candidate = account.getCandidateBO();
        CVPersonalHTML page = (CVPersonalHTML)comms.xmlcFactory.create(CVPersonalHTML.class);
	fillNationality(page, candidate);
	final String action = comms.request.getParameter("action");
	//System.out.println("Die Action lautet: " + action);
	writeTextFields(page, candidate, comms);
	if (action != null && action.equals("write")){ 
	    readTextFields(page, candidate, comms);
	}
	comms.response.writeHTML(page);
    }

    /**
     * Fills the text fields with CV-data 
     **/

    private void readTextFields(CVPersonalHTML page, Candidate candidate, HttpPresentationComms comms){
	try{
	    candidate.setLname(comms.request.getParameter("lName"));
	    candidate.setFname(comms.request.getParameter("fName"));
	    candidate.setSex(comms.request.getParameter("sex"));
	    candidate.setBirthdate(TimeUtil.getDate(Integer.parseInt(comms.request.getParameter("year")),
						    Integer.parseInt(comms.request.getParameter("month"))-1,
						    Integer.parseInt(comms.request.getParameter("day"))));
	    candidate.setResidence(comms.request.getParameter("residence"));
	    //candidate.setNationality(comms.request.getParameter("nationality"));
	    candidate.setStreet(comms.request.getParameter("street"));
	    candidate.setHouseNumber(comms.request.getParameter("housenumber"));
	    candidate.setPLZ(Integer.parseInt(comms.request.getParameter("zip")));
	    candidate.setCity(comms.request.getParameter("city"));
	    //candidate.setCountry(comms.request.getParameter("country"));
	    candidate.setPhone(comms.request.getParameter("phonenumber"));
	    candidate.setFax(comms.request.getParameter("faxnumber"));
	    candidate.setNatel(comms.request.getParameter("natel"));
	    //candidate.setPicture(comms.request.getParameter("picture")); 

	    candidate.commit();
	}
	catch(Exception e){
	    System.out.println(e.toString());
	    throw new RuntimeException();}
    }
    
    private void writeTextFields(CVPersonalHTML page, Candidate candidate, HttpPresentationComms comms){
	try{

	    page.getElementLName().setValue(candidate.getLname());
	    page.getElementFName().setValue(candidate.getFname());
	    
	    /*if (candidate.getSex().equals("w")){
		page.getElementWsex().setSelected(true);
		page.getElementMsex().setSelected(false);
	    }
	    else{
		page.getElementMsex().setSelected(true);
		page.getElementWsex().setSelected(false);
	    }*/
		
	   // page.getElementYear().setValue(splitDate(candidate.getBirthdate(),3));
	    //page.getElementMonth().setValue(splitDate(candidate.getBirthdate(),2));
	    //page.getElementDay().setValue(splitDate(candidate.getBirthdate(),1));
	    page.getElementResidence().setValue(candidate.getResidence());
	    page.getElementStreet().setValue(candidate.getStreet());
	    page.getElementHousenumber().setValue(candidate.getHouseNumber());
	    page.getElementZip().setValue(String.valueOf(candidate.getPLZ()));
	    page.getElementCity().setValue(candidate.getCity());
	    page.getElementPhonenumber().setValue(candidate.getPhone());
	    page.getElementFaxnumber().setValue(candidate.getFax());
	    page.getElementNatel().setValue(candidate.getNatel());
	    comms.response.writeHTML(page);
    
	}
	catch(Exception e){
	    System.out.println(e.toString());
	    throw new RuntimeException();}
	    
    }
	

    private String splitDate(Date d, int selector){
	String result = " ";
	if (selector == 3)
	    result = String.valueOf(TimeUtil.getYear(d));
	if (selector == 2)
	    result = String.valueOf(TimeUtil.getMonth(d));
	if (selector == 1)
	    result = String.valueOf(TimeUtil.getDay(d));
	return result;
    }

    /**
     * fills the nationality-list with CV-data
     **/
    private void fillNationality(CVPersonalHTML page, Candidate candidate){
	Country selected_option = null;
	selected_option = candidate.getNationalityBO();
	EntityManager man = EntityManager.getUniqueInstance();
	List country_list  =  man.getCountries();
	HTMLOptionElement template = page.getElementTemplateNationality();
	fillListBox(template ,country_list, selected_option);
    }

    /**
     * fills the country-list with the CV-data
     **/
    private void fillCountryList(CVPersonalHTML page){
	EntityManager man = EntityManager.getUniqueInstance();
	List country_list  =  man.getCountries();
	HTMLOptionElement template = page.getElementTemplateCountry();
	fillListBox(template ,country_list, null);
    }

}
