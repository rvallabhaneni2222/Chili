package info.chili.gwt.widgets;

import info.chili.gwt.composite.ALComposite;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import info.chili.gwt.resources.ChiliImages;

public abstract class AbstractStatusPanel extends ALComposite implements ClickHandler {

    private static AbstractStatusPanel statusPanel;

    public static AbstractStatusPanel instance() {
        return statusPanel;
    }
    protected FlexTable statusBar = new FlexTable();
    protected Label userLink = new Label("Welcome Guest");
    // TODO use cleint bundle? and ui binder
    protected Image logo = new Image();
    protected ClickableLink loginLink = new ClickableLink("login");
    protected ClickableLink logoutLink = new ClickableLink("logout");
    protected ClickableLink createUserLink = new ClickableLink("create user");
    public HTML notificationWidget = new HTML();
    public HTML tasksWidget = new HTML();

    
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
        notificationWidget.addClickHandler(this);
        tasksWidget.addClickHandler(this);
    }

    @Override
    protected void configure() {
        statusBar.setStyleName("y-gwt-AbstracttatusPanel");
        logo.setStyleName("y-gwt-AbstractStatusPanel-LogoImage");
        userLink.addStyleName("userLink");
        statusBar.getFlexCellFormatter().setRowSpan(0, 0, 2);
        statusBar.getFlexCellFormatter().setColSpan(0, 1, 4);
        statusBar.getCellFormatter().addStyleName(1, 3, "y-gwt-AbstractStatusPanel-UserLink");
        statusBar.getCellFormatter().addStyleName(1, 2, "y-gwt-AbstractStatusPanel-LoginLink");
        statusBar.getCellFormatter().addStyleName(0, 0, "y-gwt-AbstractStatusPanel-LogoLink");

        statusBar.getCellFormatter().setHorizontalAlignment(0, 7, HasHorizontalAlignment.ALIGN_RIGHT);
        statusBar.getCellFormatter().setHorizontalAlignment(1, 2, HasHorizontalAlignment.ALIGN_RIGHT);
        statusBar.getCellFormatter().setHorizontalAlignment(1, 1, HasHorizontalAlignment.ALIGN_CENTER);
        statusBar.getCellFormatter().setHorizontalAlignment(1, 9, HasHorizontalAlignment.ALIGN_RIGHT);
        logo.setResource(ChiliImages.INSTANCE.logo());
    }

    @Override
    protected void addWidgets() {
        statusBar.setWidget(0, 0, logo);
        statusBar.setWidget(0, 1, notificationWidget);
        statusBar.setWidget(0, 6, tasksWidget);
        statusBar.setWidget(0, 7, userLink);
        statusBar.setWidget(1, 2, loginLink);
    }

    protected abstract void loginClicked();

    protected abstract void logoutClicked();

    protected abstract void createUserClicked();
    
    protected abstract void notificationLinkClicked();
    
    protected abstract void tasksLinkClicked();

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
        if (event.getSource().equals(notificationWidget)) {
            notificationLinkClicked();
        }
        if (event.getSource().equals(tasksWidget)) {
            tasksLinkClicked();
        }

    }

    public void setLogo(ImageResource imageResource) {
        logo.setResource(imageResource);
    }
}
