package info.yalamanchili.gwt.widgets;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;

public class LoadingWidget extends PopupPanel {
	Label loading = new Label("Loading...");

	public LoadingWidget() {
		super(true);
		setWidget(loading);
	}

	public void show() {
		int left = Window.getClientWidth() / 3;
		this.setPopupPosition(left, 0);
		addStyleName("ResponseMessage");
		super.show();
	}

	public void hide() {
		removeStyleName("ResponseMessage");
		super.hide();
	}
}
