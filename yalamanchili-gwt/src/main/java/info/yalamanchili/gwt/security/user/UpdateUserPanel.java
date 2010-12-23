package info.yalamanchili.gwt.security.user;

import info.yalamanchili.gwt.callback.ALAsyncCallback;
import info.yalamanchili.gwt.composite.UpdateComposite;
import info.yalamanchili.gwt.fields.DataType;
import info.yalamanchili.gwt.rpc.GWTService.GwtServiceAsync;
import info.yalamanchili.gwt.widgets.ResponseStatusWidget;
import info.yalamanchili.security.jpa.YUser;

public class UpdateUserPanel extends UpdateComposite<YUser> {

	public UpdateUserPanel() {
		initUpdateComposite(YUser.class.getName(), null);
	}

	@Override
	public YUser populateEntityOnRender() {
		entity = ReadUserPanel.instance().getEntity();
		setField("username", entity.getUsername());
		setField("passwordHash", entity.getPasswordHash());
		return entity;
	}

	@Override
	public YUser populateEntityOnUpdate() {
		entity.setUsername(getStringField("username"));
		entity.setPasswordHash(getStringField("passwordHash"));
		return entity;
	}

	@Override
	public void updateButtonClicked() {
		GwtServiceAsync.instance().updateUser(entity,
				new ALAsyncCallback<YUser>() {

					@Override
					public void onResponse(YUser user) {
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
		addField("username", false, true, DataType.STRING_FIELD);
		addField("passwordHash", false, false, DataType.STRING_FIELD);
	}

	@Override
	protected void configure() {

	}

}
