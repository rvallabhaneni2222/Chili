package info.yalamanchili.gwt.fields;

import info.yalamanchili.gwt.composite.GenericFieldComposite;

import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.user.client.ui.TextArea;

public class TextAreaField extends GenericFieldComposite {
	TextArea textArea = new TextArea();

	public TextAreaField(String labelName) {
		super(labelName);
		setup();
	}

	public TextAreaField(String labelName, Boolean readOnly) {
		super(labelName);
		textArea.setReadOnly(readOnly);
		setup();
	}

	@Override
	public void setup() {
		fieldPanel.insert(textArea, 0);
		textArea.setVisibleLines(5);
	}

	public String getText() {
		return textArea.getText();
	}

	public void setText(String text) {
		textArea.setText(text);
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
