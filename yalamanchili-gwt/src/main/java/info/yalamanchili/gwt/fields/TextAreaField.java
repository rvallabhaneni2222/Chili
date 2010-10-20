package info.yalamanchili.gwt.fields;

import info.yalamanchili.gwt.composite.BaseField;

import com.google.gwt.user.client.ui.TextArea;

public class TextAreaField extends BaseField {
	TextArea textArea = new TextArea();

	public TextAreaField(String labelName, Boolean readOnly) {
		super(labelName);
		textArea.setReadOnly(readOnly);
	}

	public String getText() {
		return textArea.getText();
	}

	public void setText(String text) {
		textArea.setText(text);
	}

	@Override
	protected void configureAddMainWidget() {
		fieldPanel.insert(textArea, 0);
		textArea.setVisibleLines(5);
	}
}
