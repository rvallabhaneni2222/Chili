package info.yalamanchili.gwt.fields;


import info.yalamanchili.gwt.composite.GenericFieldComposite;

import com.google.gwt.user.client.ui.PasswordTextBox;

public class PasswordField extends GenericFieldComposite {
	protected PasswordTextBox password = new PasswordTextBox();

	public PasswordField(String name) {
		super(name);
		fieldPanel.insert(password, 1);
	}

	public String getPassword() {
		return password.getText();
	}
}
