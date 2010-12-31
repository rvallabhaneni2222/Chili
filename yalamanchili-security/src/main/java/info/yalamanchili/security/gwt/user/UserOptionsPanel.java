package info.yalamanchili.security.gwt.user;

import info.yalamanchili.gwt.composite.CreateComposite.CreateCompositeType;
import info.yalamanchili.gwt.composite.OptionsComposite;

import info.yalamanchili.gwt.utils.Alignment;
import info.yalamanchili.security.gwt.SecurityWelcome;

import com.google.gwt.user.client.Window;

public class UserOptionsPanel extends OptionsComposite {

	public UserOptionsPanel(OptionsCompositeType type) {
		super(type);
	}

	public UserOptionsPanel(Alignment alignment, OptionsCompositeType type) {
		super(alignment, type);
	}

	@Override
	public void deleteLinkClicked() {
		Window.alert("delete clicked");

	}

	@Override
	public void updateLinkClicked() {
		SecurityWelcome.entityPanel.clear();
		SecurityWelcome.entityPanel.add(new UpdateUserPanel());
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
		SecurityWelcome.entityPanel.add(new CreateUserPanel(
				CreateCompositeType.CREATE));
	}

	@Override
	public void addLinkClicked() {

	}

	@Override
	public void addAllLinkClicked() {

	}
}
