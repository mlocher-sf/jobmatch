package jobmatch.test;

import java.lang.reflect.*;
import java.text.NumberFormat;
import java.util.*;
import java.io.PrintStream;

import junit.framework.*;
import junit.util.*;

/**
 *  TestRunner that doesn't terminate the VM
 *
 *  @since May 12 2000
 *  @author $Author: pse4 $
 *  @version $Revision: 1.1 $
 **/
public class TestRunner extends junit.textui.TestRunner {

    protected PrintStream output;

    public TestRunner(PrintStream output) {
	super(output);
	this.output = output;
    }
    
    /** need to rewrite because in super class it was private **/
    private PrintStream writer() {
	if (this.output != null) {
	    return this.output;
	} else {
	    return System.out;
	}
    }
    
    /** runs the tests, but does no kill the VM **/
    public void nonTerminatingRun(Test suite) {
	this.doRun(suite, false);
    }

    /** do the same as the superclass, but don't send System.exit at the end **/
    protected void doRun(Test suite, boolean wait) {
	TestResult result= createTestResult();
	result.addListener(this);
	long startTime= System.currentTimeMillis();
	suite.run(result);
	long endTime= System.currentTimeMillis();
	long runTime= endTime-startTime;
	writer().println();
	writer().println("Time: "+StringUtil.elapsedTimeAsString(runTime));
	print(result);
	writer().println();
    }
	
} // class

// Document history
/*
 * $Log: TestRunner.java,v $
 * Revision 1.1  2000/06/20 13:33:37  pse4
 * Initial revision
 *
 * Revision 1.1  2000/05/12 15:25:57  locher
 * junit tests
 *
 */
