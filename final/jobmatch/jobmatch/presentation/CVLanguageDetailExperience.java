package jobmatch.presentation;


import jobmatch.business.candidate.*;
import jobmatch.business.provider.account.*;
import org.w3c.dom.html.*;
import java.util.Collection;
import jobmatch.business.entity.*;
import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;
import jobmatch.business.candidate.cv.*;
import jobmatch.business.entity.*;

public class CVLanguageDetailExperience extends CVSection implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {
	this.assertLegitimation(comms, Account.TYPE_CANDIDATE);
       	Candidate candidate = this.getCandidateAccount(comms).getCandidateBO();
        CVLanguageDetailExperienceHTML page =
        (CVLanguageDetailExperienceHTML)comms.xmlcFactory.create(CVLanguageDetailExperienceHTML.class);

	final String action = comms.request.getParameter("action");
	if (action != null && action.equals("write")) 
	    this.processData(page, candidate, comms);

	fillWritingSkill(page, candidate);
	fillOralSkill(page, candidate);
	fillLanguage(page, candidate);
        comms.response.writeHTML(page);
    }

    /**
     * Fills the written capability Listbox
     **/
     private void fillWritingSkill(CVLanguageDetailExperienceHTML page, Candidate candidate){
	final EntityManager man = EntityManager.getUniqueInstance();
	Collection data =  man.getLanguageCapabilities();
	HTMLOptionElement template = page.getElementTemplateWritingSkill();
	this.fillListBox(template, data, null);
    }

    /**
     * Fills the oral capability Listbox
     **/
    private void fillOralSkill(CVLanguageDetailExperienceHTML page, Candidate candidate){
	final EntityManager man = EntityManager.getUniqueInstance();
	Collection data =  man.getLanguageCapabilities();
	HTMLOptionElement template = page.getElementTemplateOralSkill();
	this.fillListBox(template, data, null);
    }

    /**
     * Fills the Language Listbox
     **/
    private void fillLanguage(CVLanguageDetailExperienceHTML page, Candidate candidate){
	final EntityManager man = EntityManager.getUniqueInstance();
	Collection data =  man.getLanguages();
	HTMLOptionElement template = page.getElementTemplateLanguage();
	this.fillListBox(template, data, null);
    }

    /**
     * Stores the data in the db
     **/
    private void processData(CVLanguageDetailExperienceHTML page, Candidate candidate, HttpPresentationComms comms) {
	try{
	    LanguageSkill languageSkill = new LanguageSkill();
	    Language language = Language.getLanguage(comms.request.getParameter("language"));
	    LanguageCapability spoken =
	    LanguageCapability.getCapability(comms.request.getParameter("oralSkill"));
	    LanguageCapability written =
	    LanguageCapability.getCapability(comms.request.getParameter("writingSkill"));
	    String diploma = comms.request.getParameter("diploma");
	    languageSkill.setCandidate(candidate);
	    languageSkill.setLanguage(language);
	    languageSkill.setSpoken(spoken);
	    languageSkill.setWritten(written);  
	    languageSkill.setDiploma(diploma);
	    languageSkill.commit();
	    	 	}
	catch(Exception e) {
	    System.out.println(e.toString());
	    throw new RuntimeException();
	}
    }
}
