/*
 *  This is a sample presentation object written by hand (not using the
 *  Jolt compiler). Since this presentation object only redirects, and does
 *  not emit any HTML, it does not need Jolt.
 *  This type of presentation object could, for example, be the target of
 *  a form's submit button, process the data, and then decide where to send
 *  the user (back or on to the next page).
 */

package jobmatch.presentation;

import com.lutris.appserver.server.httpPresentation.*;


public class Redirect implements HttpPresentation {

   /*
    * There is the only function needed in order to be a presentation object.
    */
    public void run(HttpPresentationComms comms) throws Exception {
        throw new ClientPageRedirectException(
                      comms.request.getAppFileURIPath("Welcome.po"));
    }

}
