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

        CVEducationDetailHTML page = (CVEducationDetailHTML)comms.xmlcFactory.create(CVEducationDetailHTML.class);

	Candidate candidate = this.getCandidateAccount(comms).getCandidateBO();
	
	final String action = comms.request.getParameter("action");
	if (action != null && action.equals("write")) 
	    this.processData(page, candidate, comms);

	this.fillSchooltype(page, candidate);
	this.fillGraduation(page, candidate);

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
	    School school = School.getSchool(comms.request.getParameter("Description"));
	    school.setType(Schooltype.getSchooltype(comms.request.getParameter("Schooltype")));
	    System.out.println(school.toString());
	    school.commit();
	    
	    Formation formation = new Formation();
	    formation.setCandidate(candidate);
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
	    System.out.println(e.toString());
	    throw new RuntimeException();
	}
    }

   /* private void fillPage(CVEducationDetailHTML page, Formation formation, HttpPresentationComms comms) {
	try {
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
	    throw new RuntimeException();
	}
    }
    
    private String splitDate(Date d, int selector){
	String result;
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
	return result;
    }*/

}
