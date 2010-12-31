package info.yalamanchili.security.gwt.role;

import info.yalamanchili.gwt.composite.CreateComposite.CreateCompositeType;
import info.yalamanchili.gwt.composite.SideBarComposite;
import info.yalamanchili.gwt.widgets.ClickableLink;
import info.yalamanchili.gwt.widgets.FindWidget;
import info.yalamanchili.security.gwt.SecurityWelcome;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.LazyPanel;
import com.google.gwt.user.client.ui.Widget;

public class RoleSideBar extends SideBarComposite {
	private static RoleSideBar instance;

	public static RoleSideBar instance() {
		return instance;
	}

	ClickableLink createLink = new ClickableLink("Create Role");
	FindWidget findWidget = new FindWidget(this);

	private static class RoleLazySearchPanel extends LazyPanel {
		@Override
		protected Widget createWidget() {
			return new RoleSearchPanelGeneric();
		}
	}

	RoleLazySearchPanel roleLazySearchPanel = new RoleLazySearchPanel();

	public RoleSideBar() {
		instance = this;
		initSideBarComposite();
	}

	@Override
	protected void addListeners() {
		createLink.addClickHandler(this);
	}

	@Override
	protected void configure() {
		panel.addStyleName("RoleSidePanel");
	}

	@Override
	protected void addWidgets() {
		panel.add(createLink);
		panel.add(findWidget);
	}

	@Override
	public void onClick(ClickEvent event) {
		if (event.getSource() == createLink) {
			createLinkClicked();
		}

	}

	protected void createLinkClicked() {
		SecurityWelcome.entityPanel.clear();
		SecurityWelcome.entityPanel.add(new CreateRolePanel(
				CreateCompositeType.CREATE));
	}

	protected void reloadCategory() {

	}

	@Override
	public void onSearchClose(CloseEvent<DisclosurePanel> arg0) {
		searchPanel.remove(roleLazySearchPanel);
	}

	@Override
	public void onSearchOpen(OpenEvent<DisclosurePanel> arg0) {
		searchPanel.add(roleLazySearchPanel);
		roleLazySearchPanel.setVisible(true);
	}

}
