package jobmatch.presentation;

import jobmatch.business.provider.account.*;
import jobmatch.business.candidate.*;
import jobmatch.business.candidate.cv.*;
import org.w3c.dom.html.*;
import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

public class CVLanguageExperience extends CVMultiSection implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {
	assertLegitimation(comms,  Account.TYPE_CANDIDATE);

        CVLanguageExperienceHTML page =
        (CVLanguageExperienceHTML)comms.xmlcFactory.create(CVLanguageExperienceHTML.class);

	Candidate candidate = this.getCandidateAccount(comms).getCandidateBO();
	this.fillTable(page.getElementLanguageTable(),
		       new LanguageFormatter(page), candidate.getAllLanguageSkills());
	page.getElementTemplateRow().getParentNode().removeChild(page.getElementTemplateRow());
        comms.response.writeHTML(page);
    }

  /**
     * Inner class returns a formated HTMLElement for a given language section
     **/
    class LanguageFormatter implements CVSectionFormatter {
	
	private CVLanguageExperienceHTML page;

	public LanguageFormatter(CVLanguageExperienceHTML page) {
	    this.page = page;
	}

	public HTMLElement format(jobmatch.business.candidate.cv.CVSection section) {
	    LanguageSkill languageSkill = (LanguageSkill) section;
	    try{
		
		page.setTextLanguage(languageSkill.getLanguage().getDescription());
		page.setTextWritten(languageSkill.getWritten().getDescription());
		page.setTextSpoken(languageSkill.getSpoken().getDescription());
		page.setTextDiploma(languageSkill.getDiploma());
	    }catch (Exception e){
		System.out.println("Exception int class LanguageFormatter");
		throw new RuntimeException(e.toString());
	    }
	    return (HTMLElement) this.page.getElementTemplateRow().cloneNode(true);
	}
	    
    } //inner class

}
