package info.yalamanchili.gwt.security.role;

import info.yalamanchili.gwt.beans.MultiSelectObj;
import info.yalamanchili.gwt.callback.ALAsyncCallback;
import info.yalamanchili.gwt.composite.TreePanelComposite;
import info.yalamanchili.gwt.security.AdminService.AdminServiceAsync;
import info.yalamanchili.gwt.security.SecurityWelcome;
import info.yalamanchili.security.jpa.YRole;

public class TreeRolePanel extends TreePanelComposite<YRole> {
	private static TreeRolePanel instance;

	public static TreeRolePanel instance() {
		return instance;
	}

	public TreeRolePanel() {
		instance = this;
		initTreePanelComposite(YRole.class.getName());
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
	public YRole loadEntity() {
		return ReadRolePanel.instance().getEntity();
	}

	@Override
	public void treeNodeSelected(final String link) {

		if (YRole.class.getName().contains(link)) {
			String[] columns = { "rolename", };
			AdminServiceAsync.instance().getRoleRoles(entity, columns,
					new ALAsyncCallback<MultiSelectObj<YRole>>() {

						@Override
						public void onResponse(MultiSelectObj<YRole> arg0) {
							SecurityWelcome.entityPanel.clear();
							SecurityWelcome.entityPanel
									.add(new ReadAllRolesPanel(arg0
											.getSelectedObjs()));
							SecurityWelcome.entityPanel.clear();
							SecurityWelcome.entityPanel
									.add(new SelectRolePanel("Select Roles",
											TreeRolePanel.instance, arg0.getAvailable(), arg0
													.getSelected()));

						}

					});
		}

	}

	@Override
	public void showEntity() {
		SecurityWelcome.entityPanel.clear();
		SecurityWelcome.entityPanel.add(new ReadRolePanel(entity.getRoleId()));

	}

}
