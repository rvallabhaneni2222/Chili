package info.yalamanchili.gwt.widgets;

import info.yalamanchili.gwt.fields.PasswordField;
import info.yalamanchili.gwt.fields.StringField;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public abstract class CreateUserAbstract extends PopupPanel implements
		ClickHandler {
	VerticalPanel panel = new VerticalPanel();
	protected StringField firstnameTF = new StringField("first name");
	protected StringField lastnameTF = new StringField("last name");
	protected StringField usernameTF = new StringField("username");
	protected PasswordField passwordTF = new PasswordField("password");
	protected Button create = new Button("create user");

	public CreateUserAbstract() {
		setWidget(panel);
		panel.setSpacing(5);
		panel.add(firstnameTF);
		panel.add(lastnameTF);
		panel.add(usernameTF);
		panel.add(passwordTF);
		panel.add(create);
		create.addClickHandler(this);
	}

	@Override
	public void onClick(ClickEvent event) {
		if (event.getSource().equals(create)) {
			createUserClicked();
		}
	}
	
	public void show() {
		super.show();
	}

	public abstract void createUserClicked();
}
