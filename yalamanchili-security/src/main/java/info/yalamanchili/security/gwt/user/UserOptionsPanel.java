package info.yalamanchili.security.gwt.user;

import info.yalamanchili.gwt.callback.ALAsyncCallback;
import info.yalamanchili.gwt.composite.CreateComposite.CreateCompositeType;
import info.yalamanchili.gwt.composite.OptionsComposite;
import info.yalamanchili.gwt.utils.Alignment;
import info.yalamanchili.gwt.widgets.ResponseStatusWidget;
import info.yalamanchili.security.gwt.AdminService.AdminServiceAsync;
import info.yalamanchili.security.gwt.SecurityWelcome;

public class UserOptionsPanel extends OptionsComposite {

	public UserOptionsPanel(OptionsCompositeType type) {
		super(type);
	}

	public UserOptionsPanel(Alignment alignment, OptionsCompositeType type) {
		super(alignment, type);
	}

	@Override
	public void deleteLinkClicked() {
		AdminServiceAsync.instance().deleteUser(
				ReadUserPanel.instance().getEntity(),
				new ALAsyncCallback<Void>() {

					@Override
					public void onResponse(Void arg0) {
						new ResponseStatusWidget().show("deleted");
					}

				});

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
