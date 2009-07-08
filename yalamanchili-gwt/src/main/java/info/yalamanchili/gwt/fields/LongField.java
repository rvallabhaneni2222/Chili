package info.yalamanchili.gwt.fields;


import info.yalamanchili.gwt.composite.GenericFieldCompositeWithTextBox;

import com.google.gwt.user.client.Window;

// TODO: Auto-generated Javadoc
/**
 * The Class LongField.
 */
public class LongField extends GenericFieldCompositeWithTextBox {

	/**
	 * Instantiates a new long field.
	 * 
	 * @param labelName the label name
	 */
	public LongField(String labelName) {
		super(labelName);
	}

	/**
	 * Instantiates a new long field.
	 * 
	 * @param labelName the label name
	 * @param readOnly the read only
	 */
	public LongField(String labelName, Boolean readOnly) {
		super(labelName, readOnly);
	}

	/**
	 * Gets the long.
	 * 
	 * @return the long
	 */
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

	/**
	 * Sets the long.
	 * 
	 * @param number the new long
	 */
	public void setLong(Long number) {
		textbox.setText(number.toString());
	}

}
