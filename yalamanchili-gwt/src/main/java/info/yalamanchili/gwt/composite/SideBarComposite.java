package info.yalamanchili.gwt.composite;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.OpenHandler;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlowPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class SideBarComposite.
 */
public abstract class SideBarComposite extends ALComposite implements
		ClickHandler {

	/** The panel. */
	protected FlowPanel panel = new FlowPanel();

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
		panel.addStyleName("SideBarComposite");
		searchPanel.addStyleName("SearchDisclosurePanel");
		searchPanel.addOpenHandler(new OpenHandler<DisclosurePanel>() {

			@Override
			public void onOpen(OpenEvent<DisclosurePanel> arg0) {
				onSearchOpen(arg0);

			}

		});
		searchPanel.addCloseHandler(new CloseHandler<DisclosurePanel>() {

			@Override
			public void onClose(CloseEvent<DisclosurePanel> arg0) {
				onSearchClose(arg0);

			}

		});
	}

	public abstract void onSearchOpen(OpenEvent<DisclosurePanel> arg0);

	public abstract void onSearchClose(CloseEvent<DisclosurePanel> arg0);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user
	 * .client.ui.Widget)
	 */
	public abstract void onClick(ClickEvent event);

}
