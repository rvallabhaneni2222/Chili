package info.yalamanchili.android.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;

public class NumberEditText extends EditText {

	public NumberEditText(Context context) {
		super(context);

	}

	public NumberEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NumberEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
		Log.d("D", new Integer(keyCode).toString());
		Log.d("D", new Integer(keyEvent.getKeyCode()).toString());
		if (keyCode >= 7 && keyCode <= 16) {
			return super.onKeyDown(keyCode, keyEvent);
		}
		return false;
	}
}
