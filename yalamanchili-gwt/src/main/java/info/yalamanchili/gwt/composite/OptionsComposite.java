package info.yalamanchili.gwt.composite;

import info.yalamanchili.gwt.utils.ClickableLink;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class OptionsComposite extends ALComposite implements
		ClickListener {
	protected HorizontalPanel panel = new HorizontalPanel();

	protected ClickableLink updateLink = new ClickableLink("Update");
	protected ClickableLink deleteLink = new ClickableLink("Delete");
	protected ClickableLink createLink = new ClickableLink("Create");

	public OptionsComposite() {
		updateLink.addClickListener(this);
		deleteLink.addClickListener(this);
		createLink.addClickListener(this);
	}

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

	public abstract void createLinkClicked();

	public abstract void updateLinkClicked();

	public abstract void deleteLinkClicked();

}
