package info.yalamanchili.gwt.fields;


import info.yalamanchili.gwt.composite.GenericFieldCompositeWithTextBox;

import com.google.gwt.user.client.Window;

public class IntegerField extends GenericFieldCompositeWithTextBox {

	public IntegerField(String text) {
		super(text);
	}

	public IntegerField(String text, Boolean readOnly) {
		super(text, readOnly);
	}

	public void setInteger(Integer number) {
		textbox.setText(number.toString());
	}

	public Integer getInteger() {
		Integer number = null;
		if (textbox.getText() == null || textbox.getText().trim().equals(""))
			return null;
		try {
			number = new Integer(textbox.getText());
		} catch (NumberFormatException nfe) {
			// TODO need to go to error page
			Window.alert("please enter a valid number");
			return null;
		}
		return number;
	}
}
