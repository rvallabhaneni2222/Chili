package info.yalamanchili.gwt.security;

import info.yalamanchili.gwt.security.role.ReadAllRolesPanel;
import info.yalamanchili.gwt.security.role.ReadAllRolesPanel.YRoleTableType;
import info.yalamanchili.gwt.security.role.RoleSideBar;
import info.yalamanchili.gwt.security.user.ReadAllUsersPanel;
import info.yalamanchili.gwt.security.user.ReadAllUsersPanel.YUserTableType;
import info.yalamanchili.gwt.security.user.UserSideBar;

import com.google.gwt.user.client.ui.FlowPanel;

public class SecurityWelcome {
	public static FlowPanel entityPanel;
	public static FlowPanel sidePanelTop;
	public static FlowPanel sidePanelBottom;

	public enum CMD_TYPE {
		USER, ROLE
	}

	private CMD_TYPE cmd;

	public SecurityWelcome(CMD_TYPE cmd, FlowPanel entityPanel,
			FlowPanel sidePanelTop, FlowPanel sidePanelBottom) {
		this.cmd = cmd;
		SecurityWelcome.entityPanel = entityPanel;
		SecurityWelcome.sidePanelTop = sidePanelTop;
		SecurityWelcome.sidePanelBottom = sidePanelBottom;
		init();
	}

	protected void init() {
		if (CMD_TYPE.USER.equals(cmd)) {
			entityPanel.clear();
			entityPanel.add(new ReadAllUsersPanel(YUserTableType.READALL));
			sidePanelTop.clear();
			sidePanelTop.add(new UserSideBar());
		}
		if (CMD_TYPE.ROLE.equals(cmd)) {
			entityPanel.clear();
			entityPanel.add(new ReadAllRolesPanel(YRoleTableType.READALL));
			sidePanelTop.clear();
			sidePanelTop.add(new RoleSideBar());
		}
	}
}
