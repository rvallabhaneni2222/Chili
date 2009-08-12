package info.yalamanchili.gwt.fields;


import info.yalamanchili.gwt.composite.GenericFieldComposite;

import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.user.client.ui.PasswordTextBox;

// TODO: Auto-generated Javadoc
/**
 * The Class PasswordField.
 */
public class PasswordField extends GenericFieldComposite {
	
	/** The password. */
	protected PasswordTextBox password = new PasswordTextBox();

	/**
	 * Instantiates a new password field.
	 * 
	 * @param name the name
	 */
	public PasswordField(String name) {
		super(name);
		fieldPanel.insert(password, 1);
	}

	/**
	 * Gets the password.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password.getText();
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
