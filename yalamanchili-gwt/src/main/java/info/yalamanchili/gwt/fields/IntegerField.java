package info.yalamanchili.gwt.fields;


import info.yalamanchili.gwt.composite.GenericFieldCompositeWithTextBox;

import com.google.gwt.user.client.Window;

// TODO: Auto-generated Javadoc
/**
 * The Class IntegerField.
 */
public class IntegerField extends GenericFieldCompositeWithTextBox {

	/**
	 * Instantiates a new integer field.
	 * 
	 * @param text the text
	 */
	public IntegerField(String text) {
		super(text);
	}

	/**
	 * Instantiates a new integer field.
	 * 
	 * @param text the text
	 * @param readOnly the read only
	 */
	public IntegerField(String text, Boolean readOnly) {
		super(text, readOnly);
	}

	/**
	 * Sets the integer.
	 * 
	 * @param number the new integer
	 */
	public void setInteger(Integer number) {
		textbox.setText(number.toString());
	}

	/**
	 * Gets the integer.
	 * 
	 * @return the integer
	 */
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
