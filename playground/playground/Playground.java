/*
 * playground
 * A sample Enhydra Application
 * 
 */

package playground;

import com.lutris.appserver.server.*;
import com.lutris.appserver.server.httpPresentation.*;
import com.lutris.appserver.server.session.*;
import com.lutris.util.*;

/**
 * The application object.
 * 
 * Application-wide data would go here.
 */
public class Playground extends StandardApplication {

    /*
     *  A few methods you might want to add to.
     *  See StandardApplication for more details.
     */
    public void startup(Config appConfig) throws ApplicationException {
        super.startup(appConfig);
        //  Here is where you would read application-specific settings from
        //  your config file.
    }
    public boolean requestPreprocessor(HttpPresentationComms comms)
                   throws Exception {
        return super.requestPreprocessor(comms);
    }

    /**
     * This is an optional function, used only by the Multiserver's graphical
     * administration. This bit of HTML appears in the status page for this
     * application. You could add extra status info, for example
     * a list of currently logged in users.
     *
     * @return HTML that is displayed in the status page of the Multiserver.
     */
    public String toHtml() {
        return "This is <I>playground</I>";
    }
}

