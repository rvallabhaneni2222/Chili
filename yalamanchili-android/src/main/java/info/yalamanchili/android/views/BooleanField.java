package info.yalamanchili.android.views;

import info.yalamanchili.android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BooleanField extends LinearLayout {

	protected TextView label;
	protected CheckBox booleanBox;
	protected TextView errorMsg;
	protected boolean readOnly = false;

	public BooleanField(Context context, AttributeSet attrs) {
		super(context, attrs);
		// read custom attributes
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.BooleanField);
		final int N = a.getIndexCount();
		for (int i = 0; i < N; ++i) {
			int attr = a.getIndex(i);
			switch (attr) {
			case R.styleable.BooleanField_readOnly:
				readOnly = a.getBoolean(attr, false);
				break;
			}
		}
		a.recycle();
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
		booleanBox.setEnabled(!readOnly);
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
