package info.yalamanchili.gwt.security.user;

import info.yalamanchili.gwt.beans.TableObj;
import info.yalamanchili.gwt.callback.ALAsyncCallback;
import info.yalamanchili.gwt.composite.ReadAllComposite;
import info.yalamanchili.gwt.rpc.GWTService.GwtServiceAsync;
import info.yalamanchili.gwt.utils.Utils;
import info.yalamanchili.security.jpa.YUser;

import java.util.List;

import com.google.gwt.user.client.ui.FlowPanel;

public class ReadAllUsersPanel extends ReadAllComposite<YUser> {

	public static ReadAllUsersPanel instance;
	protected static FlowPanel entityPanel;
	protected static FlowPanel sidePanelTop;
	protected static FlowPanel sidePanelBottom;
	private YUserTableType tableType;

	public enum YUserTableType {
		READALL, READALL_SEARCH
	}

	public ReadAllUsersPanel(List<YUser> entities) {
		instance = this;
		initTable(new YUser(), entities, null);

	}

	public ReadAllUsersPanel(YUserTableType tableType) {
		instance = this;
		this.tableType = tableType;
		initTable(new YUser(), null);
	}

	public ReadAllUsersPanel(YUserTableType tableType, FlowPanel entityPanel,
			FlowPanel sidePanelTop, FlowPanel sidePanelBottom) {
		instance = this;
		ReadAllUsersPanel.entityPanel = entityPanel;
		ReadAllUsersPanel.sidePanelTop = sidePanelTop;
		ReadAllUsersPanel.sidePanelBottom = sidePanelBottom;
		this.tableType = tableType;
		initTable(new YUser(), null);
	}

	@Override
	public void preFetchTable(int start) {
		if (YUserTableType.READALL.equals(tableType)) {
			GwtServiceAsync.instance().getTableObjUser(start,
					new ALAsyncCallback<TableObj<YUser>>() {

						@Override
						public void onResponse(TableObj<YUser> arg0) {
							postFetchTable(arg0);
						}

					});
		}
		if (YUserTableType.READALL_SEARCH.equals(tableType)) {
			GwtServiceAsync.instance().getEntitiesUser(
					UserSearchPanelGeneric.instance().getEntity(),
					new ALAsyncCallback<List<YUser>>() {

						@Override
						public void onResponse(List<YUser> arg0) {
							postFetchTable(arg0);
						}
					});
		}
	}

	@Override
	public void createTableHeader() {
		table.setText(0, 0, getKeyValue("Table_Action"));
		table.setText(0, 1, getClassValue("Username"));
	}

	@Override
	public void fillData(List<YUser> entities) {
		int i = 1;
		for (YUser entity : entities) {
			createViewIcon(i, entity.getUserId());
			table.setText(i, 1, Utils.entityToString(entity.getUsername()));
			i++;
		}
	}

	@Override
	public void viewClicked(int row, int col) {
		entityPanel.clear();
		entityPanel.add(new ReadUserPanel(getEntityId(row)));
	}
}
