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
import jobmatch.business.candidate.cv.*;
import jobmatch.business.entity.*;

public class CVEducationDetail extends CVSection implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {
	this.assertLegitimation(comms, Account.TYPE_CANDIDATE);
      	Candidate candidate = this.getCandidateAccount(comms).getCandidateBO();
	final String action = comms.request.getParameter("action");
	if (action != null && action.equals("write")) {
	    this.processData(candidate, comms);  
	}   	
	CVEducationDetailHTML page = (CVEducationDetailHTML)comms.xmlcFactory.create(CVEducationDetailHTML.class);
	
	if (this.isEditing(comms)) {
	    try {
		Formation formation = this.getFormation(comms, candidate);
		this.preparePageForNextCall(page, comms);
		this.fillSchooltype(page, formation);
		this.fillGraduation(page, formation);
		this.fillPage(page, formation);
	    } catch (Exception e) {
		throw new RuntimeException(e.toString());
	    }
	} else {
	    this.fillSchooltype(page, null);
	    this.fillGraduation(page, null);
	}
	comms.response.writeHTML(page);
    }

    private String getHandle(HttpPresentationComms comms) {
	try {
	    return comms.request.getParameter("edit");	    
	} catch (Exception e) {
	    throw new RuntimeException(e.toString());
	}
    }

    private void preparePageForNextCall(CVEducationDetailHTML page, HttpPresentationComms comms) {
	page.getElementEdit().setValue(this.getHandle(comms));
    }

    private boolean isEditing(HttpPresentationComms comms) {
	final String handle = this.getHandle(comms);
	return (handle != null && !handle.equals("new"));
    }

    private Formation getFormation(HttpPresentationComms comms,
				   Candidate candidate)  throws Exception {
	Formation formation;
	final String handle = this.getHandle(comms);
	if (handle == null || handle.equals("new")){
	    formation = new Formation();
	    formation.setCandidate(candidate);
	    formation.commit();
	} else {
	    formation = Formation.getFromHandle(handle);
	}
	return formation;
    }

    private void fillGraduation(CVEducationDetailHTML page, Formation formation){
	final EntityManager man = EntityManager.getUniqueInstance();
	Collection data =  man.getGraduations();
	Graduation selection = null;
	if (formation != null) {
	    selection = formation.getGraduationBO();
	}
	HTMLOptionElement template = page.getElementTemplateGraduation();
	this.fillListBox(template, data, selection);
    }

    private void fillSchooltype(CVEducationDetailHTML page, Formation formation){
	final EntityManager man = EntityManager.getUniqueInstance();
	Collection data =  man.getSchooltypes();
	Schooltype selection = null;
	if (formation != null && formation.getSchoolBO() != null) {
	    selection = formation.getSchoolBO().getSchoolTypeBO();
	}
	HTMLOptionElement template = page.getElementTemplateSchooltype();
	this.fillListBox(template, data, selection);
    }

    private void processData(Candidate candidate, HttpPresentationComms comms) {
	try{
	    Formation formation = this.getFormation(comms, candidate);
	    School school = School.getSchool(comms.request.getParameter("Description"));
	    school.setType(Schooltype.getSchooltype(comms.request.getParameter("Schooltype")));
            formation.setDiploma(Graduation.getGraduation(comms.request.getParameter("Graduation")));
	    school.commit();
	   
	    formation.setBeginDate(TimeUtil.getDate(Integer.parseInt(comms.request.getParameter("BeginYear")),
						    Integer.parseInt(comms.request.getParameter("BeginMonth")),
						    Integer.parseInt(comms.request.getParameter("BeginDay"))));
	    formation.setEndDate(TimeUtil.getDate(Integer.parseInt(comms.request.getParameter("EndYear")),
						    Integer.parseInt(comms.request.getParameter("EndMonth")),
						    Integer.parseInt(comms.request.getParameter("EndDay"))));
	    formation.setRemarks(comms.request.getParameter("Remarks"));
	    formation.setSchool(school);
	    formation.commit();
	}
	catch(Exception e) {
	    throw new RuntimeException(e.toString());
	}
    }

    private void fillPage(CVEducationDetailHTML page, Formation formation) {
	try {
	    if (formation.getSchoolBO() != null) {
		page.getElementDescription().setValue(formation.getSchoolBO().getDescription());
	    }
	  
	    page.getElementBeginYear().setValue(splitDate(formation.getBeginDate(), 3));
            page.getElementBeginMonth().setValue(splitDate(formation.getBeginDate(), 2));
            page.getElementBeginDay().setValue(splitDate(formation.getBeginDate(), 1));

	    page.getElementEndYear().setValue(splitDate(formation.getEndDate(), 3));
	    page.getElementEndMonth().setValue(splitDate(formation.getEndDate(), 2));
	    page.getElementEndDay().setValue(splitDate(formation.getEndDate(), 1));
            
	    page.getElementRemarks().setValue(formation.getRemarks());
	   
	}
	catch(Exception e) {
	    System.out.println(e.toString());
	    throw new RuntimeException(e.toString());
	} 
    }
    
    private String splitDate(Date d, int selector){
	String result = "";
	if (d != null) {
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
	}
	return result;
    }

} //class
