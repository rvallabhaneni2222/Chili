package info.yalamanchili.gwt.fields;

import info.yalamanchili.gwt.composite.GenericFieldCompositeWithTextBox;

import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.user.client.Window;

// TODO: Auto-generated Javadoc
/**
 * The Class LongField.
 */
public class LongField extends GenericFieldCompositeWithTextBox {

	/**
	 * Instantiates a new long field.
	 * 
	 * @param labelName
	 *            the label name
	 */
	public LongField(String labelName) {
		super(labelName);
	}

	/**
	 * Instantiates a new long field.
	 * 
	 * @param labelName
	 *            the label name
	 * @param readOnly
	 *            the read only
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
	 * @param number
	 *            the new long
	 */
	public void setLong(Long number) {
		if (number != null)
			textbox.setText(number.toString());
	}

	@Override
	public void onKeyPress(KeyPressEvent event) {
		if (!Character.isDigit(event.getCharCode())) {
			setMessage("invalid value");
			textbox.cancelKey();
		} else {
			clearMessage();
		}

	}

	@Override
	public void onKeyUp(KeyUpEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onKeyDown(KeyDownEvent arg0) {
		// TODO Auto-generated method stub

	}

}
