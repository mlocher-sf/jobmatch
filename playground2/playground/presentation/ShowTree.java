package playground.presentation;

import playground.business.*;
import playground.business.tree.*;

import org.w3c.dom.*;
import org.w3c.dom.html.*;
import com.lutris.xml.xmlc.*;
import com.lutris.appserver.server.httpPresentation.*;

public class ShowTree implements HttpPresentation {

    private final static String MARKER = "XXX";
    private static TreeNodeHTML NODE_TEMPLATE = null;

    public void run(HttpPresentationComms comms) 
        throws HttpPresentationException {

	Tree testTree = Tree.createDemoTree();

	ShowTreeHTML page = (ShowTreeHTML)comms.xmlcFactory.create(ShowTreeHTML.class);
	
	page.getElementRoot().appendChild(this.convertNode(comms, page, testTree.getRoot()));
	page.getElementRoot().appendChild(this.convertNode(comms, page, testTree.getRoot()));
	
        comms.response.writeHTML(page.toDocument());
    }


    private synchronized HTMLElement convertNode(HttpPresentationComms comms, HTMLDocument page, TreeNode node) {
	NODE_TEMPLATE = (TreeNodeHTML)comms.xmlcFactory.create(TreeNodeHTML.class);    

	final int leafNo = node.getLeafNo();
	this.setLeafNo(leafNo);
	
	NODE_TEMPLATE.setTextDescription(node.getDescription());

	HTMLElement response = (HTMLElement)page.importNode(NODE_TEMPLATE.getElementNODE(),true);
	return response;
    }

    private void setLeafNo(int number) {
	final String stringValue = Integer.toString(number);

	fixButtonAnchor(NODE_TEMPLATE.getElementOR(), stringValue);
	appendAttribute(NODE_TEMPLATE.getElementORimage(), "name", stringValue);

	fixButtonAnchor(NODE_TEMPLATE.getElementAND(), stringValue);
	appendAttribute(NODE_TEMPLATE.getElementANDimage(), "name", stringValue);

	fixButtonAnchor(NODE_TEMPLATE.getElementNOT(), stringValue);
	appendAttribute(NODE_TEMPLATE.getElementNOTimage(), "name", stringValue);
	
	appendAttribute(NODE_TEMPLATE.getElementDEL(), "href", stringValue);
	appendAttribute(NODE_TEMPLATE.getElementEDIT(), "href", stringValue);
    }

    private static void fixButtonAnchor(HTMLElement button, String leafNo) {
	appendAttribute(button, "href", leafNo);
	updateAttribute(button, "onmouseout", MARKER, leafNo);
	updateAttribute(button, "onmousedown", MARKER, leafNo);
	updateAttribute(button, "onmouseup", MARKER, leafNo);
	updateAttribute(button, "onmouseover", MARKER, leafNo);
    }

    private static void appendAttribute(HTMLElement element, String attribute, String value) {
	element.setAttribute(attribute, element.getAttribute(attribute) + value);
    }

    private static void updateAttribute(HTMLElement element, String attribute, String replace, String with) {
	String value =element.getAttribute(attribute);
	final int markerPos = value.lastIndexOf(MARKER);
	if (markerPos >= 0) {
	    element.setAttribute(attribute, value.substring(0,markerPos) + with + value.substring(markerPos+3));
	}
    }


} //class
