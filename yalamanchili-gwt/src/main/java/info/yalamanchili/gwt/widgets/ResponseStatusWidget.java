package info.yalamanchili.gwt.widgets;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;

public class ResponseStatusWidget extends PopupPanel {
	Label responseStatus = new Label("");

	
	
	public ResponseStatusWidget() {
		super(true);
		setWidget(responseStatus);
	}

	public void show(String message) {
		int left = Window.getClientWidth() / 3;
		this.setPopupPosition(left, 0);
		super.show();
		responseStatus.setText(message);
		addStyleName("ResponseMessage");
		Timer timer = new Timer() {
			@Override
			public void run() {
				hide();
			}
		};
		timer.schedule(3000);
	}

	public void hide() {
		super.hide();
	}
}
