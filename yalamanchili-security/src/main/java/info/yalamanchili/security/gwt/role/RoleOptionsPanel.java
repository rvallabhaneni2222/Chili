package info.yalamanchili.security.gwt.role;

import info.yalamanchili.gwt.callback.ALAsyncCallback;
import info.yalamanchili.gwt.composite.CreateComposite.CreateCompositeType;
import info.yalamanchili.gwt.composite.OptionsComposite;
import info.yalamanchili.gwt.utils.Alignment;
import info.yalamanchili.gwt.widgets.ResponseStatusWidget;
import info.yalamanchili.security.gwt.AdminService.AdminServiceAsync;
import info.yalamanchili.security.gwt.SecurityWelcome;

public class RoleOptionsPanel extends OptionsComposite {

	public RoleOptionsPanel(OptionsCompositeType type) {
		super(type);
	}

	public RoleOptionsPanel(Alignment alignment, OptionsCompositeType type) {
		super(alignment, type);
	}

	@Override
	public void deleteLinkClicked() {
		AdminServiceAsync.instance().deleteRole(
				ReadRolePanel.instance().getEntity(),
				new ALAsyncCallback<Void>() {

					@Override
					public void onResponse(Void arg0) {
						new ResponseStatusWidget().show("deleted");
					}

				});

	}

	@Override
	public void updateLinkClicked() {
		info.yalamanchili.security.gwt.SecurityWelcome.entityPanel.clear();
		info.yalamanchili.security.gwt.SecurityWelcome.entityPanel
				.add(new UpdateRolePanel());
	}

	@Override
	protected void addListeners() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void addWidgets() {

	}

	@Override
	protected void configure() {
		panel.setSpacing(5);

	}

	@Override
	public void createLinkClicked() {
		SecurityWelcome.entityPanel.clear();
		SecurityWelcome.entityPanel.add(new CreateRolePanel(
				CreateCompositeType.CREATE));
	}

	@Override
	public void addLinkClicked() {

	}

	@Override
	public void addAllLinkClicked() {

	}
}
