package info.yalamanchili.gwt.security.role;

import info.yalamanchili.gwt.callback.ALAsyncCallback;
import info.yalamanchili.gwt.composite.SelectComposite;
import info.yalamanchili.gwt.security.AdminService.AdminServiceAsync;
import info.yalamanchili.gwt.security.user.TreeUserPanel;
import info.yalamanchili.gwt.widgets.ResponseStatusWidget;
import info.yalamanchili.security.jpa.YRole;

import java.util.List;
import java.util.Map;

public class SelectRolePanel extends SelectComposite<YRole> {

	public SelectRolePanel(String name, SelectCompositeType type) {
		super(name, type);
	}

	public SelectRolePanel(String name, YRole entity) {
		super(name, entity);
	}

	@Override
	protected void initListBox() {
		String[] columns = { "rolename", };
		AdminServiceAsync.instance().getListBoxValuesRole(columns,
				new ALAsyncCallback<Map<Long, String>>() {

					@Override
					public void onResponse(Map<Long, String> arg0) {
						populateListBox(arg0);

					}
				});
	}

	@Override
	public void getSelectedEntity(Long id) {

	}

	public void setSelectedEntity(YRole entity) {
		if (entity.getRoleId() != null)
			listBox.setItemSelected(valueIndex.get(entity.getRoleId()), true);
	}

	@Override
	public void onAdd() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAddAll(List<Long> ids) {
		AdminServiceAsync.instance().addRoles(
				TreeUserPanel.instance().getEntity(), ids,
				new ALAsyncCallback<Void>() {

					@Override
					public void onResponse(Void arg0) {
						new ResponseStatusWidget().show("added");

					}

				});
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

}
