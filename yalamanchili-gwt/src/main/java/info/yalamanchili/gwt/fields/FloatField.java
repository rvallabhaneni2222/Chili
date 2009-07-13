package info.yalamanchili.gwt.fields;

import info.yalamanchili.gwt.composite.GenericFieldCompositeWithTextBox;

import com.google.gwt.user.client.Window;

public class FloatField extends GenericFieldCompositeWithTextBox {

	public FloatField(String labelName) {
		super(labelName);
	}

	public FloatField(String labelName, Boolean readOnly) {
		super(labelName, readOnly);
	}

	public Float getFloat() {
		Float value = null;
		if (textbox.getText() == null || textbox.getText().trim().equals(""))
			return null;
		try {
			value = new Float(textbox.getText());
		} catch (NumberFormatException e) {
			Window.alert("please enter a valid decimal amount");
			return null;
		}
		return value;
	}

	public void setFloat(Float var) {
		if (var != null)
			textbox.setText(var.toString());
	}
}
