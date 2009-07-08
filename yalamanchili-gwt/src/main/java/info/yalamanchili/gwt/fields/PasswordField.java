package info.yalamanchili.gwt.fields;


import info.yalamanchili.gwt.composite.GenericFieldComposite;

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
}
