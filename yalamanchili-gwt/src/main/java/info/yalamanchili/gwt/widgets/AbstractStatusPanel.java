package info.yalamanchili.gwt.widgets;

import info.yalamanchili.gwt.composite.ALComposite;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public abstract class AbstractStatusPanel extends ALComposite implements
		ClickHandler {
	private static AbstractStatusPanel statusPanel;

	public static AbstractStatusPanel instance() {
		return statusPanel;
	}

	protected FlexTable statusBar = new FlexTable();
	protected Label userLink = new Label("Welcome Guest");
	Image logo = new Image("images/logo.gif");

	protected ClickableLink loginLink = new ClickableLink("login");
	protected ClickableLink logoutLink = new ClickableLink("logout");

	protected ClickableLink createUserLink = new ClickableLink("create user");

	public AbstractStatusPanel() {
		statusPanel = this;
		init(statusBar);
		setUser();
	}

	protected abstract void setUser();

	
	public abstract void logoutSuccessfull();

	@Override
	protected void addListeners() {
		loginLink.addClickHandler(this);
		createUserLink.addClickHandler(this);
		logoutLink.addClickHandler(this);
	}

	@Override
	protected void configure() {
		statusBar.setStyleName("statusPanel");
		statusBar.getFlexCellFormatter().setRowSpan(0, 0, 2);
		statusBar.getCellFormatter().setStyleName(1, 3, "userLink");
		statusBar.getCellFormatter().setStyleName(1, 2, "loginLink");
		statusBar.getCellFormatter().setStyleName(0, 0, "logoLink");
		statusBar.getCellFormatter().setStyleName(1, 1, "createUserLink");

		statusBar.getCellFormatter().setHorizontalAlignment(0, 3,
				HasHorizontalAlignment.ALIGN_RIGHT);
		statusBar.getCellFormatter().setHorizontalAlignment(1, 2,
				HasHorizontalAlignment.ALIGN_RIGHT);
		statusBar.getCellFormatter().setHorizontalAlignment(1, 1,
				HasHorizontalAlignment.ALIGN_CENTER);

	}

	@Override
	protected void addWidgets() {
		statusBar.setWidget(0, 0, logo);
		statusBar.setWidget(1, 1, createUserLink);
		statusBar.setWidget(0, 3, userLink);
		statusBar.setWidget(1, 2, loginLink);
	}

	protected abstract void loginClicked();

	protected abstract void logoutClicked();

	protected abstract void createUserClicked();

	@Override
	public void onClick(ClickEvent event) {
		if (event.getSource().equals(loginLink)) {
			loginClicked();
		}
		if (event.getSource().equals(logoutLink)) {
			logoutClicked();
		}
		if (event.getSource().equals(createUserLink)) {
			createUserClicked();
		}

	}
}
