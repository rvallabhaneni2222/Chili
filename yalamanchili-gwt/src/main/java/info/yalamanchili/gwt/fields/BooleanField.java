package info.yalamanchili.gwt.fields;

import info.yalamanchili.gwt.composite.GenericFieldComposite;

import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.user.client.ui.CheckBox;

// TODO: Auto-generated Javadoc
/**
 * The Class BooleanField.
 */
public class BooleanField extends GenericFieldComposite {

	/** The box. */
	protected CheckBox box = new CheckBox();

	/**
	 * Gets the box.
	 * 
	 * @return the box
	 */
	public CheckBox getBox() {
		return box;
	}

	/**
	 * Instantiates a new boolean field.
	 * 
	 * @param text
	 *            the text
	 */
	public BooleanField(String text) {
		super(text);
		box.setText(text);
	}

	/**
	 * Instantiates a new boolean field.
	 * 
	 * @param text
	 *            the text
	 * @param readOnly
	 *            the read only
	 */
	public BooleanField(String text, Boolean readOnly) {
		super(text);
		if (readOnly)
			box.setEnabled(readOnly);
		box.setText(text);
	}

	/**
	 * Sets the value.
	 * 
	 * @param value
	 *            the new value
	 */
	public void setValue(Boolean value) {
		if (value == null) {
			box.setChecked(false);
		} else {
			box.setChecked(value);
		}
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public Boolean getValue() {
		if (box.isChecked())
			return true;
		else
			return false;
	}

	@Override
	public void setup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyPress(KeyPressEvent arg0) {
		// TODO Auto-generated method stub
		
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
