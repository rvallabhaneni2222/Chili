package info.yalamanchili.security.gwt.role;

import info.yalamanchili.gwt.callback.ALAsyncCallback;
import info.yalamanchili.gwt.composite.UpdateComposite;
import info.yalamanchili.gwt.fields.DataType;
import info.yalamanchili.security.gwt.YRole;
import info.yalamanchili.security.gwt.YUser;
import info.yalamanchili.security.gwt.AdminService.AdminServiceAsync;
import info.yalamanchili.gwt.widgets.ResponseStatusWidget;

public class UpdateRolePanel extends UpdateComposite<YRole> {

	public UpdateRolePanel() {
		initUpdateComposite(YUser.class.getName(), null);
	}

	@Override
	public YRole populateEntityOnRender() {
		entity = ReadRolePanel.instance().getEntity();
		setField("rolename", entity.getRolename());
		return entity;
	}

	@Override
	public YRole populateEntityOnUpdate() {
		entity.setRolename(getStringField("rolename"));
		return entity;
	}

	@Override
	public void updateButtonClicked() {
		AdminServiceAsync.instance().updateRole(entity,
				new ALAsyncCallback<YRole>() {

					@Override
					public void onResponse(YRole user) {
						new ResponseStatusWidget().show("updated");
					}

				});
	}

	@Override
	protected void addListeners() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void addWidgetsBeforeCaptionPanel() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void addWidgets() {
		addField("rolename", false, true, DataType.STRING_FIELD);
	}

	@Override
	protected void configure() {

	}

}
