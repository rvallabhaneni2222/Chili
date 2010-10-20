package info.yalamanchili.gwt.composite;

import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public abstract class BaseField extends Composite implements KeyPressHandler,
		KeyUpHandler, KeyDownHandler {
	protected FlowPanel panel = new FlowPanel();

	protected HorizontalPanel fieldPanel = new HorizontalPanel();

	protected Label label = new Label();

	protected Label message = new Label();

	protected Boolean isValid = false;

	public FlowPanel getPanel() {
		return panel;
	}

	public Label getLabel() {
		return label;
	}
	
	public void setMessage(String text) {
		message.setText(text);
	}
	
	public void clearMessage() {
		message.setText("");
	}

	public Boolean getValid() {
		return isValid;
	}

	public void setValid(Boolean valid) {
		this.isValid = valid;
	}

}
