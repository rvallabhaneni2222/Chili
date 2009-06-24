package info.yalamanchili.gwt.widgets;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;

public class ResponceStatusBar extends Label {

	public void setText(String text) {
		setText(text);
		addStyleName("ResponseMessage");
	}

	public void setDelayedMessage(String message) {
		setText(message);
		addStyleName("ResponseMessage");
		Timer timer = new Timer() {
			@Override
			public void run() {
				removeText();
			}
		};
		timer.schedule(3000);
	}

	public void removeText() {
		setText("");
		removeStyleName("ResponseMessage");
	}
}
