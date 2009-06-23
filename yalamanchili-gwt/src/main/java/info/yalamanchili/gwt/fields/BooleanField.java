package info.yalamanchili.gwt.fields;


import info.yalamanchili.gwt.composite.GenericFieldComposite;

import com.google.gwt.user.client.ui.CheckBox;

public class BooleanField extends GenericFieldComposite {
	protected CheckBox box = new CheckBox();

	public CheckBox getBox() {
		return box;
	}

	public BooleanField(String text) {
		super(text);
		box.setText(text);
	}

	public BooleanField(String text, Boolean readOnly) {
		super(text);
		if (readOnly)
			box.setEnabled(readOnly);
		box.setText(text);
	}

	public void setValue(Boolean value) {
		if (value == null) {
			box.setChecked(false);
		} else {
			box.setChecked(value);
		}
	}

	public Boolean getValue() {
		if (box.isChecked())
			return true;
		else
			return false;
	}

}
