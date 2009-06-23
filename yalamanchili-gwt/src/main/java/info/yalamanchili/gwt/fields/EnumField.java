package info.yalamanchili.gwt.fields;

import info.yalamanchili.gwt.composite.ALComposite;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EnumField extends ALComposite {
	protected VerticalPanel panel = new VerticalPanel();
	protected HorizontalPanel fieldPanel = new HorizontalPanel();
	protected Label message = new Label();
	protected Label label = new Label();
	protected TextBox textbox = new TextBox();
	protected ListBox listBox = new ListBox();
	protected Boolean readOnly;
	protected Boolean isValid = false;

	public EnumField(String text, Boolean readOnly) {
		this.readOnly = readOnly;
		init(panel);
		if (readOnly) {
			label.setText(text);
			textbox.setReadOnly(readOnly);
			fieldPanel.add(textbox);
		} else {
			label.setText(text);
			fieldPanel.add(listBox);
		}
	}

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public VerticalPanel getPanel() {
		return panel;
	}

	public Label getLabel() {
		return label;
	}

	public TextBox getTextbox() {
		return textbox;
	}

	public Boolean getReadOnly() {
		return readOnly;
	}

	@Override
	protected void addListeners() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void addWidgets() {
		panel.add(label);
		fieldPanel.add(message);
		panel.add(fieldPanel);
	}

	@Override
	protected void configure() {
		panel.setSpacing(5);

	}

	public void setValue(Enum<?>[] enums) {
		for (Enum<?> var : enums) {
			listBox.addItem(var.toString(), var.toString());
		}
	}

	public void setValue(String text) {
		textbox.setText(text);
	}

	public void setMessage(String text) {
		message.setText(text);
	}

	public void clearMessage() {
		message.setText("");
	}

	public String getValue() {
		if (readOnly) {
			return textbox.getText();
		} else {
			return listBox.getItemText(listBox.getSelectedIndex());
		}
	}
}
