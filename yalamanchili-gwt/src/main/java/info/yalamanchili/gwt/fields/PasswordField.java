package info.yalamanchili.gwt.fields;

import info.yalamanchili.gwt.composite.BaseField;

import com.google.gwt.user.client.ui.PasswordTextBox;

public class PasswordField extends BaseField {

	protected PasswordTextBox password = new PasswordTextBox();

	public PasswordField(String labelName, String attributeName,
			String className) {
		super(labelName, attributeName, className, false, false);
		fieldPanel.insert(password, 1);
	}

	public String getPassword() {
		return password.getText();
	}

	@Override
	protected void configureAddMainWidget() {
		// TODO Auto-generated method stub

	}

	@Override
	public void validate() {
		// TODO Auto-generated method stub

	}

}
