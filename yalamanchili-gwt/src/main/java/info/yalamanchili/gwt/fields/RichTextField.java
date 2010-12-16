package info.yalamanchili.gwt.fields;

import info.yalamanchili.gwt.composite.BaseField;

import com.smartgwt.client.widgets.RichTextEditor;

public class RichTextField extends BaseField {
	RichTextEditor editor = new RichTextEditor();

	public RichTextField(String labelName, Boolean readOnly, Boolean required) {
		super(labelName, readOnly, required);
		configureAddMainWidget();
	}

	@Override
	protected void configureAddMainWidget() {
		editor.addStyleName("y-gwt-RichTextEditor");
		editor.setBorder("2px");
		editor.setCanDragResize(true);
		fieldPanel.insert(editor, 0);
	}

	public String getValue() {
		return editor.getValue();
	}

	public void setValue(String value) {
		editor.setValue(value);
	}
}
