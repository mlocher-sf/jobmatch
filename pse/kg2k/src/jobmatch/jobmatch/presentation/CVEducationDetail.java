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
	
	this.assertLegitimation(comms, Account.TYPE_CANDIDATE, "Welcome.po");
	Candidate candidate = this.getCandidateAccount(comms).getCandidateBO();

	final String action = comms.request.getParameter("action");
	if (action != null && action.equals("write")){ 
	    this.processData(page, candidate, comms);
	}

	this.fillSchooltype(page, candidate);
	this.fillGraduation(page, candidate);
	this.fillPage(page, candidate, comms);
	comms.response.writeHTML(page);
    }

    private void fillGraduation(CVEducationDetailHTML page, Candidate candidate){
	final EntityManager man = EntityManager.getUniqueInstance();
	Collection data =  man.getGraduations();
	HTMLOptionElement template = page.getElementTemplateGraduation();
	this.fillListBox(template, data, null);
    }

    private void fillSchooltype(CVEducationDetailHTML page, Candidate candidate){
	final EntityManager man = EntityManager.getUniqueInstance();
	Collection data =  man.getSchooltypes();
	HTMLOptionElement template = page.getElementTemplateSchooltype();
	this.fillListBox(template, data, null);
    }

    private void processData(CVEducationDetailHTML page, Candidate candidate, HttpPresentationComms comms) {
	try{

	    

	}
	catch(Exception e) {
	    System.out.println(e.toString());
	    throw new RuntimeException();
	}
    }

    private void fillPage(CVEducationDetailHTML page, Candidate candidate, HttpPresentationComms comms) {
	try {
    
	}
	catch(Exception e) {
	    System.out.println(e.toString());
	    throw new RuntimeException();
	}
    }


}
