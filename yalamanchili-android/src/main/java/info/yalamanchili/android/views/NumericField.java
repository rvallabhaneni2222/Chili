package info.yalamanchili.android.views;

import info.yalamanchili.android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.InputType;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NumericField extends LinearLayout {

	protected TextView label;
	protected TextView text;
	protected TextView errorMsg;

	protected boolean readOnly = false;

	public NumericField(Context context, AttributeSet attrs) {
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

	public void init() {
		setOrientation(LinearLayout.VERTICAL);
		label = new TextView(getContext());
		if (readOnly) {
			text = new TextView(getContext());
		} else {
			text = new NumericView(getContext());
			text.setInputType(InputType.TYPE_CLASS_NUMBER);
		}
		errorMsg = new TextView(getContext());
		int lHeight = LayoutParams.WRAP_CONTENT;
		int lWidth = LayoutParams.FILL_PARENT;
		addView(label, new LinearLayout.LayoutParams(lWidth, lHeight));
		addView(text, new LinearLayout.LayoutParams(lWidth, lHeight));
		addView(errorMsg, new LinearLayout.LayoutParams(lWidth, lHeight));
		label.setTypeface(Typeface.DEFAULT_BOLD);
	}

	public void setValue(String value) {
		text.setText(value);
	}

	public String getValue() {
		return text.getText().toString();
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

	public class NumericView extends EditText {
		public NumericView(Context context) {
			super(context);
		}

		public NumericView(Context context, AttributeSet attrs) {
			super(context, attrs);
		}

		public NumericView(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
		}
		// TODO having issue with this the keyCode does nto seemt o be
		// consistent or being fired at all times.
		// public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
		// // numbers and back space
		// Log.d("chili-android", new Integer(keyCode).toString());
		// if ((keyCode >= 7 && keyCode <= 16) || keyCode == 67) {
		// return super.onKeyDown(keyCode, keyEvent);
		// }
		// return false;
		// }
	}

}
