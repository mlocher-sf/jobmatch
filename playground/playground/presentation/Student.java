package playground.presentation;

import playground.business.*;

import org.w3c.dom.html.*;
import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

public class Student implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

	String now = new java.util.Date().toString();
	StudentHTML page = new StudentHTML();
	
	page.getElementName().setValue(now);
	foo(page);
        comms.response.writeHTML(page.toDocument());
    }

    private void foo(StudentHTML page) {
	Clipboard board = Clipboard.getDemoClipboard();
	Iterator studis = board.getStudents();

	while (studis.hasNext()) {
	    Student s = (Student) studis.next();
	    page.getStudis().appendChild(createStudiParagraph(s));
	}
    }

	private HTMLParagraphElement createStudiParagraph(Student studi) {
	    HTMLParagraphElement temp = new HTMLParagraphElement();
	    temp.setNodeValue(studi.getName());
	    return temp;
	}

}
