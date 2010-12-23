package info.yalamanchili.gwt.security.user;

import info.yalamanchili.gwt.composite.CreateComposite.CreateCompositeType;
import info.yalamanchili.gwt.composite.SideBarComposite;
import info.yalamanchili.gwt.widgets.ClickableLink;
import info.yalamanchili.gwt.widgets.FindWidget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.LazyPanel;
import com.google.gwt.user.client.ui.Widget;

public class UserSideBar extends SideBarComposite {
	private static UserSideBar categorySideBar;

	public static UserSideBar instance() {
		return categorySideBar;
	}

	ClickableLink createLink = new ClickableLink("Create User");
	FindWidget findWidget = new FindWidget(this);

	private static class CategoryLazySearchPanel extends LazyPanel {
		@Override
		protected Widget createWidget() {
			return new UserSearchPanelGeneric();
		}
	}

	CategoryLazySearchPanel categoryLazySearchPanel = new CategoryLazySearchPanel();

	public UserSideBar() {
		categorySideBar = this;
		initSideBarComposite();
	}

	@Override
	protected void addListeners() {
		createLink.addClickHandler(this);
	}

	@Override
	protected void configure() {
		panel.addStyleName("UserSidePanel");
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
		ReadAllUsersPanel.entityPanel.clear();
		ReadAllUsersPanel.entityPanel.add(new CreateUserPanel(
				CreateCompositeType.CREATE));
	}

	protected void reloadCategory() {

	}

	@Override
	public void onSearchClose(CloseEvent<DisclosurePanel> arg0) {
		searchPanel.remove(categoryLazySearchPanel);
	}

	@Override
	public void onSearchOpen(OpenEvent<DisclosurePanel> arg0) {
		searchPanel.add(categoryLazySearchPanel);
		categoryLazySearchPanel.setVisible(true);
	}
}
