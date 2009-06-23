package info.yalamanchili.gwt.fields;


import info.yalamanchili.gwt.composite.GenericFieldCompositeWithTextBox;

import com.google.gwt.user.client.Window;

public class LongField extends GenericFieldCompositeWithTextBox {

	public LongField(String labelName) {
		super(labelName);
	}

	public LongField(String labelName, Boolean readOnly) {
		super(labelName, readOnly);
	}

	public Long getLong() {
		Long value = null;
		if (textbox.getText() == null || textbox.getText().trim().equals(""))
			return null;
		try {
			value = new Long(textbox.getText());
		} catch (NumberFormatException e) {
			Window.alert("please enter a valid number");
			return null;
		}
		return value;
	}

	public void setLong(Long number) {
		textbox.setText(number.toString());
	}

}
