package info.yalamanchili.gwt.composite;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

// TODO: Auto-generated Javadoc
/**
 * The Class SideBarComposite.
 */
public abstract class SideBarComposite extends ALComposite implements
		ClickListener {

	/** The panel. */
	protected VerticalPanel panel = new VerticalPanel();

	/**
	 * Instantiates a new side bar composite.
	 */
	public SideBarComposite() {
		
	}

	public void setup() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user
	 * .client.ui.Widget)
	 */
	public abstract void onClick(Widget widget);

}
