package info.yalamanchili.gwt.security.role;

import info.yalamanchili.gwt.callback.ALAsyncCallback;
import info.yalamanchili.gwt.composite.OptionsComposite.OptionsCompositeType;
import info.yalamanchili.gwt.composite.ReadComposite;
import info.yalamanchili.gwt.fields.DataType;
import info.yalamanchili.gwt.security.AdminService.AdminServiceAsync;
import info.yalamanchili.security.jpa.YRole;

public class ReadRolePanel extends ReadComposite<YRole> {
	private static ReadRolePanel readRolePanel;

	RoleOptionsPanel options = new RoleOptionsPanel(OptionsCompositeType.CRUD);

	public static ReadRolePanel instance() {
		return readRolePanel;
	}

	public ReadRolePanel(Long id) {
		readRolePanel = this;
		initReadComposite(id, YRole.class.getName(), null);
	}

	@Override
	protected void readData(Long id) {
		AdminServiceAsync.instance().readRole(id, new ALAsyncCallback<YRole>() {

			@Override
			public void onResponse(YRole role) {
				entity = role;
				setField("rolename", role.getRolename());
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
		addField("rolename", true, true, DataType.STRING_FIELD);
	}

	@Override
	protected void configure() {

	}

	@Override
	protected void readData(YRole entity) {
		// TODO Auto-generated method stub

	}

	protected void postValidate() {

	}
}
