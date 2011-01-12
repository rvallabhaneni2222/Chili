package info.yalamanchili.gwt.fields;

import info.yalamanchili.gwt.composite.BaseFieldWithTextBox;

import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class StringField.
 * 
 * @author ayalamanchili
 */
public class StringField extends BaseFieldWithTextBox {

	public StringField(String labelName, Boolean readOnly, Boolean required) {
		super(labelName, readOnly, required);
	}

	public String getText() {
		return textbox.getText();
	}

	public void setText(String text) {
		textbox.setText(text);
	}

	// TODO can these be moved to a top level???
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
