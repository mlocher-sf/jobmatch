package jobmatch.presentation.script;

import com.lutris.appserver.server.httpPresentation.*;
import org.python.util.PythonInterpreter;
import java.util.*;
import org.w3c.dom.*;
import org.w3c.dom.html.*;
import java.io.*;
import com.lutris.appserver.server.Application;

public class JobScriptRunner implements HttpPresentation {

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

	JobScriptsHTML page = (JobScriptsHTML)comms.xmlcFactory.create(JobScriptsHTML.class);
	this.preparePage(this.getScripts(comms.application), page);
	final String action = comms.request.getParameter("action");
	if (action != null && action.equals("execute")) {
	    final String file = comms.request.getParameter("file");
	    this.createNewInterpreter(comms);
	    this.runScript(comms, file);
	} else {
	    if (action != null && action.equals("interpretLast")) {
		this.invokeInterpreter(comms);
	    }
	}
	this.setLastRunInfo(comms, page);
        comms.response.writeHTML(page);
    }

    private void createNewInterpreter(HttpPresentationComms comms) {
	try {
	    PythonInterpreter interpreter = new PythonInterpreter();
	    comms.sessionData.set("interpreterRun", interpreter);
	}  catch (Exception e) { throw new RuntimeException(e.toString()); }
    }

    private void invokeInterpreter(HttpPresentationComms comms) {
	try {
	    comms.sessionData.set("interpreter", this.getInterpreter(comms));
	    comms.sessionData.set("history", new ArrayList());
	    throw new ClientPageRedirectException(
			   comms.request.getAppFileURIPath("./script/JobScript.po"));	    
	}  catch (Exception e) { throw new RuntimeException(e.toString()); }
    }

    protected void initInterpreter(PythonInterpreter interpreter) {
	try {
	    interpreter.exec("import java, javax, org, com, jobmatch");
	} catch (Exception e) { System.err.println(e.toString()); }
    }

    private PythonInterpreter getInterpreter(HttpPresentationComms comms) {
	try {
	    PythonInterpreter result = (PythonInterpreter) comms.sessionData.get("interpreterRun");
	    if (result == null) {
		this.createNewInterpreter(comms);
		result = (PythonInterpreter) comms.sessionData.get("interpreterRun");
	    }
	    return result;
	} catch (Exception e) { throw new RuntimeException(e.toString()); }
    }

    private void runScript(HttpPresentationComms comms, String file) {
	final String fullPath = this.getScriptDir(comms.application) + file;
	PythonInterpreter interpreter = this.getInterpreter(comms);
	String status = "?";
	try {
	    interpreter.execfile(fullPath);
	    status = "no errors";
	} catch (Exception e) {
	    status = e.toString();
	}
	try {
	    comms.sessionData.set("JobmatchStatus", status);
	} catch (Exception e) { throw new RuntimeException(e.toString()); }
    }

    private String getLastStatus(HttpPresentationComms comms) {
	try {
	    return (String) comms.sessionData.get("JobmatchStatus");
	} catch (Exception e) {
	    throw new RuntimeException(e.toString());
	}
    }

    private void preparePage(String[] files, JobScriptsHTML page) {
	page.setTextNumber(String.valueOf((files != null)?files.length:0));

	if (files != null) {
	    HTMLOptionElement entry = page.getElementFileEntry();
	    HTMLSelectElement list = (HTMLSelectElement) entry.getParentNode();

	    //remove the standatd option text
	    entry.removeChild(entry.getFirstChild());

	    for (int i = 0; i < files.length; i++) {
		HTMLOptionElement clone = (HTMLOptionElement) entry.cloneNode(true);
		clone.setValue(files[i]);
		
		Node optionTextNode = clone.getOwnerDocument().createTextNode(files[i]);
		clone.appendChild(optionTextNode);
		list.appendChild(clone);
	    }
	    list.removeChild(entry);
	}
    }

    private void setLastRunInfo(HttpPresentationComms comms, JobScriptsHTML page) {
	if (this.ranBefore(comms)) {
	    page.setTextLastResult(this.getLastStatus(comms));
	    this.writeDictionary(this.getInterpreter(comms), page);
	} else {
	    Node last = page.getElementLastDiv();
	    last.getParentNode().removeChild(last);
	}
    }

    private boolean ranBefore(HttpPresentationComms comms) {
	try {
	    return (this.getLastStatus(comms) != null);
	} catch (Exception e) {
	    return false;
	}
    }

    private void writeDictionary(PythonInterpreter interpreter, JobScriptsHTML page) {
	page.setTextDictionary(interpreter.eval("dir()").toString());
    }


    private String getScriptDir(Application application) {
	try {
	    return application.getConfig().getString("JobScript.Directory", ".");
	} catch (Exception e) {
	    throw new RuntimeException(e.toString());
	}
    }

    private String[] getScripts(Application application) {
	return new File(this.getScriptDir(application)).list(new PythonFiles());
    }

    class PythonFiles implements FilenameFilter {
	public boolean accept(File dir, String name) {
	    return ( dir != null && name != null &&
		     name.length() > 3 && name.substring(name.length()-3).toLowerCase().equals(".py"));
	}
    }

} //class
