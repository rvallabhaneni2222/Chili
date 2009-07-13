package info.yalamanchili.gwt.fields;

import info.yalamanchili.gwt.composite.GenericFieldComposite;

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

	}

	@Override
	public void setup() {
		fieldPanel.insert(textArea, 0);
		textArea.setVisibleLines(5);
	}
}
