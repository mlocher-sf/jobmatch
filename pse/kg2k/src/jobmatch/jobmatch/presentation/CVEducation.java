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
	assertLegitimation(comms,  Account.TYPE_CANDIDATE);

        CVEducationHTML page = (CVEducationHTML)comms.xmlcFactory.create(CVEducationHTML.class);
	Candidate candidate = this.getCandidateAccount(comms).getCandidateBO();
	this.fillTable(page.getElementFormationTable(),
		       new EducationFormatter(page), candidate.getAllFormations());
	page.getElementTemplateRow().getParentNode().removeChild(page.getElementTemplateRow());
        comms.response.writeHTML(page);
    }

    /**
     * Inner class returns a formated HTMLElement for a given education section
     **/
    class EducationFormatter implements CVSectionFormatter {
	
	private CVEducationHTML page;

	public EducationFormatter(CVEducationHTML page) {
	    this.page = page;
	}

	public HTMLElement format(jobmatch.business.candidate.cv.CVSection section) {
	    Formation formation = (Formation) section;
	    try{
		
		page.setTextRemarks(formation.getRemarks());
		//Graduation can be null
		try {
		    page.setTextGraduation(formation.getGraduationBO().getDescription());
		}catch (NullPointerException e){
		    page.setTextGraduation("");
		}
		page.setTextBegin(formation.getBeginDate().toString());
		page.setTextEnd(formation.getEndDate().toString());
		//   System.out.println(formation.getSchoolBO().getSchoolTypeBO().getDescription());
		page.setTextSchooltype(formation.getSchoolBO().getSchoolTypeBO().getDescription());
		page.setTextSchoolname(formation.getSchoolBO().getDescription());
	    }catch (Exception e){
		System.out.println("Exception int class EducationFormatter");
		throw new RuntimeException(e.toString());
	    }
	    return (HTMLElement) this.page.getElementTemplateRow().cloneNode(true);
	}
	    
    } //inner class

}//class
