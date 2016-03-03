package info.chili.gwt.widgets;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class LoadingWidget.
 */
public class LoadingWidget extends PopupPanel {

    /**
     * The loading.
     */
//    Label loading = new Label("Loading...");
    Image loading = new Image("https://apps.sstech.us/loading-icon.gif");

    /**
     * Instantiates a new loading widget.
     */
    public LoadingWidget() {
        super(true);
        setWidget(loading);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.google.gwt.user.client.ui.PopupPanel#show()
     */
    public void show() {
        int left = Window.getClientWidth() / 2;
        int top = Window.getClientHeight() / 2;
        this.setPopupPosition(left, top);
        addStyleName("y-gwt-loadingimage");
        super.show();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.google.gwt.user.client.ui.PopupPanel#hide()
     */
    public void hide() {
        removeStyleName("y-gwt-loadingimage");
        super.hide();
    }
}