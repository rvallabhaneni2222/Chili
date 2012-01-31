package info.yalamanchili.gwt.widgets;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;

// TODO: Auto-generated Javadoc
/**
 * The Class ResponceStatusBar.
 */
public class ResponceStatusBar extends Label {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.Label#setText(java.lang.String)
	 */
	public void setText(String text) {
		setText(text);
		setTitle(text);
		addStyleName("y-gwt-ResponseStatusBar");
	}

	/**
	 * Sets the delayed message.
	 * 
	 * @param message
	 *            the new delayed message
	 */
	public void setDelayedMessage(String message) {
		setText(message);
		setTitle(message);
		addStyleName("y-gwt-ResponseStatusBar");
		Timer timer = new Timer() {
			@Override
			public void run() {
				removeText();
			}
		};
		timer.schedule(3000);
	}

	/**
	 * Removes the text.
	 */
	public void removeText() {
		setText("");
		removeStyleName("y-gwt-ResponseStatusBar");
	}
}
