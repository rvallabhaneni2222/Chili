package info.yalamanchili.gwt.widgets;

import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.Label;

// TODO: Auto-generated Javadoc
/**
 * The Class ClickableLink.
 */
public class ClickableLink extends Label implements MouseOverHandler,
		MouseOutHandler {
	/**
	 * Instantiates a new clickable link.
	 * 
	 * @param name
	 *            the name
	 */
	public ClickableLink(String name) {
		this.setText(name);
		this.addStyleName("ClickableLink");
		addMouseOverHandler(this);
	}

	/**
	 * Instantiates a new clickable link.
	 * 
	 * @param name
	 *            the name
	 * @param isLabel
	 *            the is label
	 */
	public ClickableLink(String name, Boolean isLabel) {
		this.setText(name);
		this.addStyleName("ClickableLink");
		if (isLabel)
			this.addStyleName("LabelLink");
		addMouseOverHandler(this);
	}

	@Override
	public void onMouseOver(MouseOverEvent arg0) {
		this.addStyleName("ClickableLink-enter");

	}

	@Override
	public void onMouseOut(MouseOutEvent arg0) {
		this.removeStyleName("ClickableLink-enter");
	}
}
