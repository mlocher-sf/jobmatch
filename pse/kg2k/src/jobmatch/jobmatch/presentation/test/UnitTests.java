// $Id: UnitTests.java,v 1.2 2000/05/14 16:18:31 locher Exp $

package jobmatch.presentation.test;

import jobmatch.test.*;
import java.io.*;
import java.util.*;
import junit.framework.*;

import com.lutris.xml.xmlc.*;
import org.w3c.dom.html.*;
import com.lutris.appserver.server.httpPresentation.*;

public class UnitTests implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

        TestResultHTML page = (TestResultHTML)comms.xmlcFactory.create(TestResultHTML.class);
	
	HTMLDivElement container = page.getElementTests();
	
	HTMLDivElement testBlock = page.getElementTest();
	testBlock.removeAttribute("id");
	
	Iterator tests = AllTests.getAllTests().iterator();
	while (tests.hasNext()) {
	    Test test = (Test) tests.next();
	    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	    new TestRunner(new PrintStream(buffer)).nonTerminatingRun(test);
	    page.setTextTestname(test.toString());
	    page.setTextResult(buffer.toString());
	    container.appendChild(testBlock.cloneNode(true));
	}

	container.removeChild(testBlock);
        comms.response.writeHTML(page);
    }

} //class

/*
 *$Log: UnitTests.java,v $
 *Revision 1.2  2000/05/14 16:18:31  locher
 *display test results
 *
 *Revision 1.1  2000/05/12 15:25:55  locher
 *junit tests
 *
 */
