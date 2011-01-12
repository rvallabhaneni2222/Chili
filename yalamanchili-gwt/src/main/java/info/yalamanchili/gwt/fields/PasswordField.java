package info.yalamanchili.gwt.fields;

import info.yalamanchili.gwt.composite.BaseField;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.user.client.ui.PasswordTextBox;

public class PasswordField extends BaseField {

	protected PasswordTextBox password = new PasswordTextBox();

	public PasswordField(String name) {
		super(name, false, false);
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
	public void onChange(ChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
