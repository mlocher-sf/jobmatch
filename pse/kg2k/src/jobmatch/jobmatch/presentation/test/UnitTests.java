// $Id: UnitTests.java,v 1.1 2000/05/12 15:25:55 locher Exp $

package jobmatch.presentation.test;

import jobmatch.test.*;
import java.io.PrintStream;

import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

public class UnitTests implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

	PrintStream results = System.out;
	new TestRunner(results).nonTerminatingRun(new AllTests());

        TestResultHTML page = (TestResultHTML)comms.xmlcFactory.create(TestResultHTML.class);
        comms.response.writeHTML(page);
    }

} //class

/*
 *$Log: UnitTests.java,v $
 *Revision 1.1  2000/05/12 15:25:55  locher
 *junit tests
 *
 */
