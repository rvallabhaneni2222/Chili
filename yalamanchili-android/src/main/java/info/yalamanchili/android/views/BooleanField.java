package info.yalamanchili.android.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BooleanField extends LinearLayout {

	protected TextView label;
	protected CheckBox booleanBox;
	protected TextView errorMsg;

	public BooleanField(Context context, AttributeSet attrs) {
		super(context, attrs);
		// read custom attributes
		init();
	}

	public BooleanField(Context context) {
		super(context);
		init();
	}

	protected void init() {
		setOrientation(LinearLayout.VERTICAL);
		label = new TextView(getContext());
		booleanBox = new CheckBox(getContext());
		errorMsg = new TextView(getContext());
		int lHeight = LayoutParams.WRAP_CONTENT;
		int lWidth = LayoutParams.FILL_PARENT;
		addView(label, new LinearLayout.LayoutParams(lWidth, lHeight));
		addView(booleanBox, new LinearLayout.LayoutParams(lWidth, lHeight));
		addView(errorMsg, new LinearLayout.LayoutParams(lWidth, lHeight));
		label.setTypeface(Typeface.DEFAULT_BOLD);
	}

	public void setValue(Boolean value) {
		booleanBox.setChecked(value);
	}

	public Boolean getValue() {
		return booleanBox.isChecked();
	}

	public void setLabel(String label) {
		this.label.setText(label);
	}

	public void getLabel() {
		this.label.getText().toString();
	}

	public void setErrorMessage(String message) {
		errorMsg.setText(message);
	}
}
