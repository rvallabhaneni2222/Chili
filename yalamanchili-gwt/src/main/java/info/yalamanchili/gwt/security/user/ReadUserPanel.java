package info.yalamanchili.gwt.security.user;

import info.yalamanchili.gwt.callback.ALAsyncCallback;
import info.yalamanchili.gwt.composite.OptionsComposite.OptionsCompositeType;
import info.yalamanchili.gwt.composite.ReadComposite;
import info.yalamanchili.gwt.fields.DataType;
import info.yalamanchili.gwt.security.AdminService.AdminServiceAsync;
import info.yalamanchili.security.jpa.YUser;

public class ReadUserPanel extends ReadComposite<YUser> {
	private static ReadUserPanel readUserPanel;

	UserOptionsPanel options = new UserOptionsPanel(OptionsCompositeType.CRUD);

	public static ReadUserPanel instance() {
		return readUserPanel;
	}

	public ReadUserPanel(Long id) {
		readUserPanel = this;
		initReadComposite(id, YUser.class.getName(), null);
	}

	@Override
	protected void readData(Long id) {
		AdminServiceAsync.instance().readUser(id, new ALAsyncCallback<YUser>() {

			@Override
			public void onResponse(YUser user) {
				entity = user;
				setField("username", user.getUsername());
				setField("passwordHash", user.getPasswordHash());
			}

		});
	}

	@Override
	protected void addListeners() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void addWidgetsBeforeCaptionPanel() {
		basePanel.add(options);

	}

	@Override
	protected void addWidgets() {
		addField("username", true, true, DataType.STRING_FIELD);
		addField("passwordHash", true, false, DataType.PASSWORD_FIELD);

	}

	@Override
	protected void configure() {

	}

	@Override
	protected void readData(YUser entity) {
		// TODO Auto-generated method stub

	}

	protected void postValidate() {

	}
}
