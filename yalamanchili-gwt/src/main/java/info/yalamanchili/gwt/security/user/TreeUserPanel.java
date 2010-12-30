package info.yalamanchili.gwt.security.user;

import info.yalamanchili.gwt.beans.MultiSelectObj;
import info.yalamanchili.gwt.callback.ALAsyncCallback;
import info.yalamanchili.gwt.composite.TreePanelComposite;
import info.yalamanchili.gwt.security.AdminService.AdminServiceAsync;
import info.yalamanchili.gwt.security.SecurityWelcome;
import info.yalamanchili.gwt.security.role.ReadAllRolesPanel;
import info.yalamanchili.gwt.security.role.SelectRolePanel;
import info.yalamanchili.security.jpa.YRole;
import info.yalamanchili.security.jpa.YUser;

public class TreeUserPanel extends TreePanelComposite<YUser> {
	private static TreeUserPanel instance;

	public static TreeUserPanel instance() {
		return instance;
	}

	public TreeUserPanel() {
		instance = this;
		initTreePanelComposite(YUser.class.getName());
	}

	@Override
	protected void addListeners() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void addWidgets() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void configure() {
		// TODO Auto-generated method stub

	}

	@Override
	public YUser loadEntity() {
		return ReadUserPanel.instance().getEntity();
	}

	@Override
	public void treeNodeSelected(final String link) {

		if (YRole.class.getName().contains(link)) {

			String[] columns = { "rolename", };
			AdminServiceAsync.instance().getUserRoles(entity, columns,
					new ALAsyncCallback<MultiSelectObj<YRole>>() {

						@Override
						public void onResponse(MultiSelectObj<YRole> arg0) {
							SecurityWelcome.entityPanel.clear();
							SecurityWelcome.entityPanel
									.add(new ReadAllRolesPanel(arg0
											.getSelectedObjs()));
							SecurityWelcome.entityPanel
									.add(new SelectRolePanel("Select Roles",
											TreeUserPanel.instance(), arg0
													.getAvailable(), arg0
													.getSelected()));

						}

					});

		}

	}

	@Override
	public void showEntity() {
		SecurityWelcome.entityPanel.clear();
		SecurityWelcome.entityPanel.add(new ReadUserPanel(entity.getUserId()));

	}
}
