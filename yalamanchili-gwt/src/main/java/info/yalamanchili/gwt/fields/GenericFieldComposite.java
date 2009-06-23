package info.yalamanchili.gwt.fields;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class GenericFieldComposite extends Composite {
	protected VerticalPanel panel = new VerticalPanel();
	protected HorizontalPanel fieldPanel = new HorizontalPanel();
	protected Label label = new Label();
	protected Label message = new Label();
	protected Boolean isValid = false;

	public VerticalPanel getPanel() {
		return panel;
	}

	public Label getLabel() {
		return label;
	}

	public GenericFieldComposite(String labelName) {
		label.setText(labelName);
		configure();
		addWidgets();
		initWidget(panel);
	}

	protected void configure() {
		panel.setSpacing(5);
		message.addStyleName("ErrorMessage");
	}

	protected void addWidgets() {
		panel.add(label);
		fieldPanel.add(message);
		panel.add(fieldPanel);
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
