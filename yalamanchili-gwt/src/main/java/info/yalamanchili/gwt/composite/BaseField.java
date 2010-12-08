package info.yalamanchili.gwt.composite;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public abstract class BaseField extends Composite {

	public BaseField(String labelName) {
		label.setText(labelName);
		configure();
		addWidgets();
		initWidget(panel);
	}

	public BaseField(String labelName, Boolean readOnly) {
		label.setText(labelName);
		this.readOnly = readOnly;
		configure();
		addWidgets();
		initWidget(panel);
	}

	protected void configure() {
		label.addStyleName("tfFieldHeader");
		message.addStyleName("tfErrorMessage");
	}

	protected void addWidgets() {
		panel.add(label);
		fieldPanel.add(message);
		panel.add(fieldPanel);
	}

	/* used to add main widget to fieldPanel and add style class info */
	protected abstract void configureAddMainWidget();

	protected FlowPanel panel = new FlowPanel();

	protected HorizontalPanel fieldPanel = new HorizontalPanel();

	protected Label label = new Label();

	protected Label message = new Label();

	protected Boolean isValid = false;

	protected Boolean readOnly = false;

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

	public Boolean getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
	}

}
