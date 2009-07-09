package info.yalamanchili.gwt.fields;

import info.yalamanchili.gwt.composite.GenericFieldCompositeWithTextBox;

// TODO: Auto-generated Javadoc
/**
 * The Class StringField.
 * 
 * @author ayalamanchili
 */
public class StringField extends GenericFieldCompositeWithTextBox {

	/**
	 * Instantiates a new string field.
	 * 
	 * @param labelName
	 *            the label name
	 */
	public StringField(String labelName) {
		super(labelName);
	}

	/**
	 * Instantiates a new string field.
	 * 
	 * @param labelName
	 *            the label name
	 * @param readOnly
	 *            the read only
	 */
	public StringField(String labelName, Boolean readOnly) {
		super(labelName, readOnly);
	}

	/**
	 * Gets the text.
	 * 
	 * @return the text
	 */
	public String getText() {
		return textbox.getText();
	}

	/**
	 * Sets the text.
	 * 
	 * @param text
	 *            the new text
	 */
	public void setText(String text) {
		textbox.setText(text);
	}

}
