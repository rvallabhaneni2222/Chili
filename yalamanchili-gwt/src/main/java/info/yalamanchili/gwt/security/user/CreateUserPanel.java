package info.yalamanchili.gwt.security.user;

import info.yalamanchili.gwt.callback.ALAsyncCallback;
import info.yalamanchili.gwt.composite.CreateComposite;
import info.yalamanchili.gwt.fields.DataType;
import info.yalamanchili.gwt.rpc.GWTService.GwtServiceAsync;
import info.yalamanchili.gwt.widgets.ResponseStatusWidget;
import info.yalamanchili.security.jpa.YUser;

public class CreateUserPanel extends CreateComposite<YUser> {

	public CreateUserPanel(CreateCompositeType type) {
		super(type);
		initCreateComposite(YUser.class.getName(), null);
	}

	@Override
	protected void createButtonClicked() {
		GwtServiceAsync.instance().createUser(entity,
				new ALAsyncCallback<YUser>() {

					@Override
					public void onResponse(YUser user) {
						new ResponseStatusWidget().show("create");
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
	protected YUser populateEntityFromFields() {
		YUser user = new YUser();
		user.setUsername(getStringField("username"));
		user.setPasswordHash(getStringField("passwordHash"));
		return user;
	}

	@Override
	protected void configure() {

	}

	@Override
	protected void addButtonClicked() {
	}

}
