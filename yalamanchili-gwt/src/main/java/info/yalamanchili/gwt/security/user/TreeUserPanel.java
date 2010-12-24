package info.yalamanchili.gwt.security.user;

import info.yalamanchili.gwt.callback.ALAsyncCallback;
import info.yalamanchili.gwt.composite.OptionsComposite.OptionsCompositeType;
import info.yalamanchili.gwt.composite.TreePanelComposite;
import info.yalamanchili.gwt.security.AdminService.AdminServiceAsync;
import info.yalamanchili.gwt.security.SecurityWelcome;
import info.yalamanchili.gwt.security.role.ReadAllRolesPanel;
import info.yalamanchili.gwt.security.role.RoleOptionsPanel;
import info.yalamanchili.security.jpa.YRole;
import info.yalamanchili.security.jpa.YUser;

import java.util.List;

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
	public void linkClicked(final String link) {

		if (link.contains(YRole.class.getName())) {
			AdminServiceAsync.instance().getRolesForUser(entity.getUserId(),
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
}
