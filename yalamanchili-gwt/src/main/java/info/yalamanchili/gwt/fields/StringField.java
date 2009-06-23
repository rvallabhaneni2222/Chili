package info.yalamanchili.gwt.fields;

import info.yalamanchili.gwt.composite.GenericFieldCompositeWithTextBox;


public class StringField extends GenericFieldCompositeWithTextBox {

	public StringField(String labelName) {
		super(labelName);
	}

	public StringField(String labelName, Boolean readOnly) {
		super(labelName, readOnly);
	}

	public String getText() {
		return textbox.getText();
	}

	public void setText(String text) {
		textbox.setText(text);
	}

}
