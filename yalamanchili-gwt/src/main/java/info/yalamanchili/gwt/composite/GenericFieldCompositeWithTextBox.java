package info.yalamanchili.gwt.composite;

import com.google.gwt.user.client.ui.TextBox;

//TODO move commons stuff to base field
public abstract class GenericFieldCompositeWithTextBox extends BaseField {

	protected TextBox textbox = new TextBox();

	public TextBox getTextbox() {
		return textbox;
	}

	public GenericFieldCompositeWithTextBox(String labelName) {
		label.setText(labelName);
		textbox.setTitle(labelName);
		configure();
		addListeners();
		addWidgets();
		initWidget(panel);
	}

	public GenericFieldCompositeWithTextBox(String labelName, Boolean readOnly) {
		label.setText(labelName);
		textbox.setTitle(labelName);
		if (readOnly)
			setReadOnly(readOnly);
		configure();
		addWidgets();
		addListeners();
		initWidget(panel);
	}

	protected void configure() {
		label.addStyleName("tfFieldHeader");
		message.addStyleName("tfErrorMessage");
		textbox.addStyleName("tfTextBox");

	}

	protected void addWidgets() {
		panel.add(label);
		fieldPanel.add(textbox);
		fieldPanel.add(message);
		panel.add(fieldPanel);
	}

	protected void addListeners() {
		textbox.addKeyPressHandler(this);
		textbox.addKeyUpHandler(this);
		textbox.addKeyDownHandler(this);
	}

	public void setReadOnly(Boolean readOnly) {
		textbox.setReadOnly(true);
	}

}
