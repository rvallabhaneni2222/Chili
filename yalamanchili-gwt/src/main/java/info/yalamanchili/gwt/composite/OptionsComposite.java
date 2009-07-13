package info.yalamanchili.gwt.composite;

import info.yalamanchili.gwt.utils.Alignment;
import info.yalamanchili.gwt.widgets.ClickableLink;

import com.google.gwt.user.client.ui.CellPanel;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

// TODO: Auto-generated Javadoc
/**
 * The Class OptionsComposite.
 */
public abstract class OptionsComposite extends ALComposite implements
		ClickListener {

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
		updateLink.addClickListener(this);
		deleteLink.addClickListener(this);
		createLink.addClickListener(this);
	}

	public OptionsComposite() {
		panel = new HorizontalPanel();
		init(panel);
		updateLink.addClickListener(this);
		deleteLink.addClickListener(this);
		createLink.addClickListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user
	 * .client.ui.Widget)
	 */
	public void onClick(Widget widget) {
		if (widget == updateLink) {
			updateLinkClicked();
		}
		if (widget == deleteLink) {
			deleteLinkClicked();
		}
		if (widget == createLink) {
			createLinkClicked();
		}
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
