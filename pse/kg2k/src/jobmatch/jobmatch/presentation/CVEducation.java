package jobmatch.presentation;

import jobmatch.business.provider.account.*;
import jobmatch.business.candidate.*;
import jobmatch.business.candidate.cv.*;
import org.w3c.dom.html.*;
import org.enhydra.xml.xmlc.html.*;
import com.lutris.appserver.server.httpPresentation.*;

public class CVEducation extends CVMultiSection implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {
	assertLegitimation(comms,  Account.TYPE_CANDIDATE, "Welcome.po");
        CVEducationHTML page = (CVEducationHTML)comms.xmlcFactory.create(CVEducationHTML.class);
	CandidateAccount account = this.getCandidateAccount(comms);
	fillTable(page,  new EducationFormatter(), account.getCandidateBO().getAllFormations(), 
		  page.getElementFormationTable());
        comms.response.writeHTML(page);
    }

    /**
     * Inner class formats a HTMLElement for the education section
     **/
    class EducationFormatter implements CVSectionFormatter {

	public HTMLElement format(jobmatch.business.candidate.cv.CVSection section, HTMLObject page){
	    Formation formation = (Formation) section;
	    CVEducationHTML education_page = (CVEducationHTML) page;
	    return (HTMLElement)education_page.getElementTemplateRow().cloneNode(true);
	}
	    
    }//inner class

}//class
