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

public class CVEducationDetail extends CVSection implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

        CVEducationDetailHTML page = (CVEducationDetailHTML)comms.xmlcFactory.create(CVEducationDetailHTML.class);
	
	/*Candidate candidate = this.getCandidateAccount(comms).getCandidateBO();


	final String action = comms.request.getParameter("action");
	if (action != null && action.equals("write")){ 
	    this.processData(page, candidate, comms);
	}

	this.fillSchooltype(page, candidate);
	this.fillGraduation(page, candidate);
	this.fillPage(page, candidate, comms);*/
	comms.response.writeHTML(page);
    }

   /* private void fillGraduation(CVEducationDetail page, Candidate candidate){
	final EntityManager man = EntityManager.getUniqueInstance();
	Collection data =  man.getSchooltypes();
	Country selection = candidate.getSchoolBO();
	HTMLOptionElement template = page.getElementTemplateNationality();
	this.fillListBox(template, data, selection);
    }

    private void fillSchooltype(CVEducationDetail page, Candidate candidate){
	final EntityManager man = EntityManager.getUniqueInstance();
	Collection data =  man.getSchooltypes();
	Country selection = candidate.getSchoolBO();
	HTMLOptionElement template = page.getElementTemplateNationality();
	this.fillListBox(template, data, selection);
    }

    
    private void processData(CVPersonalHTML page, Candidate candidate, HttpPresentationComms comms) {
	try{

	}
	catch(Exception e) {
	    System.out.println(e.toString());
	    throw new RuntimeException();
	}
    }



    private void fillPage(CVPersonalHTML page, Candidate candidate, HttpPresentationComms comms) {
	try {
    
	}
	catch(Exception e) {
	    System.out.println(e.toString());
	    throw new RuntimeException();
	}
    }*/


}
