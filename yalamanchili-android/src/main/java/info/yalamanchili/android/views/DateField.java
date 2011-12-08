package info.yalamanchili.android.views;

import info.yalamanchili.android.R;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
//TODO not ready 
//How to show a activity in side a avtivity

public abstract class DateField extends LinearLayout {

	protected TextView label;
	protected TextView dateView;
	protected Button dateButton;
	protected TextView errorMsg;

	protected boolean readOnly = false;

	private int mYear;
	private int mMonth;
	private int mDay;

	static final int DATE_DIALOG_ID = 0;

	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			updateDisplay();
		}
	};

	public DateField(Context context, AttributeSet attrs) {
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
		// TODO handle readOnly logic
		label = new TextView(getContext());
		dateView = new TextView(getContext());
		errorMsg = new TextView(getContext());
		int lHeight = LayoutParams.WRAP_CONTENT;
		int lWidth = LayoutParams.FILL_PARENT;
		addView(label, new LinearLayout.LayoutParams(lWidth, lHeight));
		addView(dateView, new LinearLayout.LayoutParams(lWidth, lHeight));
		addView(dateButton, new LinearLayout.LayoutParams(lWidth, lHeight));
		addView(errorMsg, new LinearLayout.LayoutParams(lWidth, lHeight));
		label.setTypeface(Typeface.DEFAULT_BOLD);

		// data button listener
		dateButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
//				showDialog(DATE_DIALOG_ID);
			}
		});
		// get the current date
		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);

		// display the current date (this method is below)
		updateDisplay();
	}

//	@Override
//	protected Dialog onCreateDialog(int id) {
//		switch (id) {
//		case DATE_DIALOG_ID:
//			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
//					mDay);
//		}
//		return null;
//	}

	public void setValue(String value) {
		dateView.setText(value);
	}

	public String getValue() {
		return dateView.getText().toString();
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

	private void updateDisplay() {
		dateView.setText(new StringBuilder()
				// Month is 0 based so add 1
				.append(mMonth + 1).append("-").append(mDay).append("-")
				.append(mYear).append(" "));
	}
}