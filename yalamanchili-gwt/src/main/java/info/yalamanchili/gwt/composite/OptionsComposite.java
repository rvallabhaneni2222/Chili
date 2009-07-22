package info.yalamanchili.gwt.composite;

import info.yalamanchili.gwt.utils.Alignment;
import info.yalamanchili.gwt.widgets.ClickableLink;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.CellPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class OptionsComposite.
 */
public abstract class OptionsComposite extends ALComposite implements
		ClickHandler {

	/** The panel. */
	protected CellPanel panel;

	/** The update link. */
	protected ClickableLink updateLink = new ClickableLink("Update");

	/** The delete link. */
	protected ClickableLink deleteLink = new ClickableLink("Delete");

	/** The create link. */
	protected ClickableLink createLink = new ClickableLink("Create");

	/**
	 * Instantiates a new options composite.
	 */
	public OptionsComposite(Alignment alignment) {
		switch (alignment) {
		case HORIZONTAL:
			panel = new HorizontalPanel();
			break;
		case VERTICAL:
			panel = new VerticalPanel();
			break;
		}
		init(panel);
		setup();
	}

	public OptionsComposite() {
		panel = new HorizontalPanel();
		init(panel);
		setup();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user
	 * .client.ui.Widget)
	 */
	public void onClick(ClickEvent event) {
		if (event.getSource() == updateLink) {
			updateLinkClicked();
		}
		if (event.getSource() == deleteLink) {
			deleteLinkClicked();
		}
		if (event.getSource() == createLink) {
			createLinkClicked();
		}
	}

	public void setup() {
		this.addStyleName("OptionsComposite");
		updateLink.addClickHandler(this);
		deleteLink.addClickHandler(this);
		createLink.addClickHandler(this);
	}

	/**
	 * Creates the link clicked.
	 */
	public abstract void createLinkClicked();

	/**
	 * Update link clicked.
	 */
	public abstract void updateLinkClicked();

	/**
	 * Delete link clicked.
	 */
	public abstract void deleteLinkClicked();

}
