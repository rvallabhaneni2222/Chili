package info.yalamanchili.gwt.security.role;

import info.yalamanchili.gwt.callback.ALAsyncCallback;
import info.yalamanchili.gwt.composite.OptionsComposite.OptionsCompositeType;
import info.yalamanchili.gwt.composite.TreePanelComposite;
import info.yalamanchili.gwt.security.AdminService.AdminServiceAsync;
import info.yalamanchili.gwt.security.SecurityWelcome;
import info.yalamanchili.security.jpa.YRole;

import java.util.List;

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
			AdminServiceAsync.instance().getRolesForRole(entity.getRoleId(),
					new ALAsyncCallback<List<YRole>>() {

						@Override
						public void onResponse(List<YRole> roles) {
							ReadAllRolesPanel readAllRolesPanel = new ReadAllRolesPanel(
									roles);
							SecurityWelcome.entityPanel.clear();
							SecurityWelcome.entityPanel.add(readAllRolesPanel);
							SecurityWelcome.entityPanel
									.add(new RoleOptionsPanel(
											OptionsCompositeType.ADD_ALL));

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
