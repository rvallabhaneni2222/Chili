package info.yalamanchili.gwt.security.role;

import info.yalamanchili.gwt.beans.TableObj;
import info.yalamanchili.gwt.callback.ALAsyncCallback;
import info.yalamanchili.gwt.composite.ReadAllComposite;
import info.yalamanchili.gwt.security.AdminService.AdminServiceAsync;
import info.yalamanchili.gwt.security.SecurityWelcome;
import info.yalamanchili.gwt.utils.Utils;
import info.yalamanchili.security.jpa.YRole;

import java.util.List;

public class ReadAllRolesPanel extends ReadAllComposite<YRole> {

	public static ReadAllRolesPanel instance;
	private YRoleTableType tableType;

	public enum YRoleTableType {
		READALL, READALL_SEARCH
	}

	public ReadAllRolesPanel(List<YRole> entities) {
		instance = this;
		initTable(new YRole(), entities, null);

	}

	public ReadAllRolesPanel(YRoleTableType tableType) {
		instance = this;
		this.tableType = tableType;
		initTable(new YRole(), null);
	}

	@Override
	public void preFetchTable(int start) {
		if (YRoleTableType.READALL.equals(tableType)) {
			AdminServiceAsync.instance().getTableObjRole(start,
					new ALAsyncCallback<TableObj<YRole>>() {

						@Override
						public void onResponse(TableObj<YRole> arg0) {
							postFetchTable(arg0);
						}

					});
		}
		if (YRoleTableType.READALL_SEARCH.equals(tableType)) {
			AdminServiceAsync.instance().getEntitiesRole(
					RoleSearchPanelGeneric.instance().getEntity(),
					new ALAsyncCallback<List<YRole>>() {

						@Override
						public void onResponse(List<YRole> arg0) {
							postFetchTable(arg0);
						}
					});
		}
	}

	@Override
	public void createTableHeader() {
		table.setText(0, 0, getKeyValue("Table_Action"));
		table.setText(0, 1, getClassValue("Role Name"));
	}

	@Override
	public void fillData(List<YRole> entities) {
		int i = 1;
		for (YRole entity : entities) {
			createViewIcon(i, entity.getRoleId());
			table.setText(i, 1, Utils.entityToString(entity.getRolename()));
			i++;
		}
	}

	@Override
	public void viewClicked(int row, int col) {
		SecurityWelcome.entityPanel.clear();
		SecurityWelcome.entityPanel.add(new ReadRolePanel(getEntityId(row)));
		SecurityWelcome.sidePanelTop.clear();
		SecurityWelcome.sidePanelTop.add(new TreeRolePanel());
	}
}
