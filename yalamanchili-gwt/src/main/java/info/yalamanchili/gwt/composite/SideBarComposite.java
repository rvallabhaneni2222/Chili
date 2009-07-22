package info.yalamanchili.gwt.composite;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DisclosureEvent;
import com.google.gwt.user.client.ui.DisclosureHandler;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class SideBarComposite.
 */
public abstract class SideBarComposite extends ALComposite implements
		ClickHandler {

	/** The panel. */
	protected VerticalPanel panel = new VerticalPanel();

	protected DisclosurePanel searchPanel = new DisclosurePanel("search");

	/**
	 * Instantiates a new side bar composite.
	 */
	public SideBarComposite() {
	}

	protected void initSideBarComposite() {
		init(panel);
		setup();
	}

	public void setup() {
		panel.add(searchPanel);
		panel.setSpacing(5);
		panel.addStyleName("SideBarComposite");
		searchPanel.addStyleName("SearchDisclosurePanel");
		searchPanel.addEventHandler(new DisclosureHandler() {
			public void onClose(DisclosureEvent arg0) {
				onSearchClose(arg0);
			}

			public void onOpen(DisclosureEvent arg0) {
				onSearchOpen(arg0);
			}
		});
	}

	public abstract void onSearchOpen(DisclosureEvent arg0);

	public abstract void onSearchClose(DisclosureEvent arg0);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user
	 * .client.ui.Widget)
	 */
	public abstract void onClick(ClickEvent event);

}
