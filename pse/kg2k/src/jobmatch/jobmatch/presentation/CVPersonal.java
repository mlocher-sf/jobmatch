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
        comms.response.writeHTML(page);
    }

    private void fillTextFields(CVPersonalHTML page, Candidate candidate){
	//fill all text fields with data 
    }

    private void fillNationality(CVPersonalHTML page, Candidate candidate){
	Country selected_option = null;
	selected_option = candidate.getNationalityBO();
	EntityManager man = EntityManager.getUniqueInstance();
	List country_list  =  man.getCountries();
	HTMLOptionElement template = page.getElementTemplateNationality();
	fillListBox(template ,country_list, selected_option);
    }

    private void fillCountryList(CVPersonalHTML page){
	EntityManager man = EntityManager.getUniqueInstance();
	List country_list  =  man.getCountries();
	HTMLOptionElement template = page.getElementTemplateCountry();
	fillListBox(template ,country_list, null);
    }

}
