// $Id: ProfileKeyFigures.java,v 1.1 2000/06/20 13:33:30 pse4 Exp $
package jobmatch.presentation;

import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;
import jobmatch.business.company.*;
import jobmatch.business.entity.*;
import jobmatch.business.provider.account.*;

/**
 * 
 *  @since June 13 2000
 *  @author $Author: pse4 $
 *  @version $Revision: 1.1 $
 **/
public class ProfileKeyFigures extends AuthentificationPage implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {
	
	this.assertLegitimation(comms, Account.TYPE_COMPANY);

	Company company = this.getCompanyAccount(comms).getCompanyBO();
	ProfileKeyFiguresHTML page = (ProfileKeyFiguresHTML)comms.xmlcFactory.create(ProfileKeyFiguresHTML.class);
	

	final String action = comms.request.getParameter("action");
	if (action != null && action.equals("write")) {
	    this.processData(page, company, comms);
		}
	this.fillPage(page, company, comms);
	comms.response.writeHTML(page);
    }
	private void processData (ProfileKeyFiguresHTML page, Company company,
				  HttpPresentationComms comms) {
	    try{
    
	    company.setName(comms.request.getParameter("name"));
	    company.setPresence(comms.request.getParameter("presence"));
	    company.setIndustry(Industry.getIndustry(comms.request.getParameter("industry")));
	    //company.setNumberEmployee(comms.request.getParameter("numberEmployee");
	    //company.setEarnings(comms.request.getParameter("earnings");
	    //company.setGraduatesPerYear(comms.request.getParameter("graduatesPerYear");

	    company.commit();
	    Address addr = company.getAddressBO();
	    addr.setStreet(comms.request.getParameter("street"));
	    addr.setZIP(comms.request.getParameter("zip"));
	    addr.setCity(comms.request.getParameter("city"));
	  
	    addr.commit();
	  	}
	catch(Exception e) {
	    System.out.println(e.toString());
	    throw new RuntimeException();
	}

    }


   private void fillPage(ProfileKeyFiguresHTML page, Company company, HttpPresentationComms comms) {
	try {

	    page.getElementName().setValue(company.getName());
	    page.getElementPresence().setValue(company.getPresence());
	  //  page.getElementIndustry().setValue(Industry.getIndustry());

	    Address addr = company.getAddressBO();
	    page.getElementStreet().setValue(addr.getStreet());
	    page.getElementZip().setValue(String.valueOf(addr.getZIP()));
	    page.getElementCity().setValue(addr.getCity());
	}
	catch(Exception e) {
	    System.out.println(e.toString());
	    throw new RuntimeException();
	}
    }

} // class

// Document history
/**
 * $Log: ProfileKeyFigures.java,v $
 * Revision 1.1  2000/06/20 13:33:30  pse4
 * Initial revision
 *
 * Revision 1.2  2000/06/14 12:43:18  studer
 * keyfigures
 *
 * Revision 1.1  2000/06/13 13:44:59  dniederm
 * *** empty log message ***
 *
 *
 *
 *
 **/
