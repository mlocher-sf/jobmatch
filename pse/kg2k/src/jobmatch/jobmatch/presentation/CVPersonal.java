package jobmatch.presentation;

import jobmatch.business.candidate.*;
import jobmatch.business.provider.account.*;
import org.w3c.dom.html.*;
import java.util.List;
import jobmatch.business.entity.*;
import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

public class CVPersonal extends CVSection implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {
	CandidateAccount account = getCandidateAccount(comms);
	Candidate candidate = account.getCandidateBO();
        CVPersonalHTML page = (CVPersonalHTML)comms.xmlcFactory.create(CVPersonalHTML.class);
	fillNationality(page, candidate);
	final String action = comms.request.getParameter("action");
	if (action != null && action.equals("write")){ 
	    fillTextFields(page, candidate, comms);
	}
	comms.response.writeHTML(page);
    }

    /**
     * Fills the text fields with CV-data 
     **/
    private void fillTextFields(CVPersonalHTML page, Candidate candidate, HttpPresentationComms comms){
	try{
	    candidate.setLname(comms.request.getParameter("lastName"));
	    candidate.setNatel( comms.request.getParameter("mobile"));
	    candidate.commit();
	}
	catch(Exception e){
	    System.out.println(e.toString());
	    throw new RuntimeException();}
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
