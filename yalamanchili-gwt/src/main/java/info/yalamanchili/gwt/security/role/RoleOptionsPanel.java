package info.yalamanchili.gwt.security.role;

import info.yalamanchili.gwt.composite.CreateComposite.CreateCompositeType;
import info.yalamanchili.gwt.composite.OptionsComposite;
import info.yalamanchili.gwt.composite.SelectComposite.SelectCompositeType;
import info.yalamanchili.gwt.security.SecurityWelcome;
import info.yalamanchili.gwt.utils.Alignment;

import com.google.gwt.user.client.Window;

public class RoleOptionsPanel extends OptionsComposite {

	public RoleOptionsPanel(OptionsCompositeType type) {
		super(type);
	}

	public RoleOptionsPanel(Alignment alignment, OptionsCompositeType type) {
		super(alignment, type);
	}

	@Override
	public void deleteLinkClicked() {
		Window.alert("delete clicked");

	}

	@Override
	public void updateLinkClicked() {
		SecurityWelcome.entityPanel.clear();
		SecurityWelcome.entityPanel.add(new UpdateRolePanel());
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
		SecurityWelcome.entityPanel.clear();
		SecurityWelcome.entityPanel.add(new SelectRolePanel("YRole",
				SelectCompositeType.ALL));
	}
}
