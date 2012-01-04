package info.yalamanchili.android.views;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DateField extends BaseField {

	protected static final int DATE_ACTIVITY_REQUEST_CODE = 100;

	Calendar date = Calendar.getInstance();

	protected TextView dateView;
	protected Button dateButton;

	private int mYear;
	private int mMonth;
	private int mDay;

	static final int DATE_DIALOG_ID = 999;

	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			date.set(year, monthOfYear, dayOfMonth);
			updateDisplay();
		}
	};

	public DateField(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void init() {
		// TODO handle readOnly logic
		label = new TextView(getContext());
		dateView = new TextView(getContext());
		errorMsg = new TextView(getContext());
		dateButton = new Button(getContext());
		dateButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Log.d("----------------", "on onclick-----------------");
				Intent i = new Intent(getContext(), DateWidget.class);
				((Activity) getContext()).startActivityForResult(i,
						DATE_ACTIVITY_REQUEST_CODE);
			}
		});
	}

	@Override
	protected void layout() {
		setOrientation(LinearLayout.VERTICAL);
		int lHeight = LayoutParams.WRAP_CONTENT;
		int lWidth = LayoutParams.FILL_PARENT;
		addView(label, new LinearLayout.LayoutParams(lWidth, lHeight));
		addView(dateView, new LinearLayout.LayoutParams(lWidth, lHeight));
		addView(dateButton, new LinearLayout.LayoutParams(lWidth, lHeight));
		addView(errorMsg, new LinearLayout.LayoutParams(lWidth, lHeight));
		label.setTypeface(Typeface.DEFAULT_BOLD);
		// get the current date
		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);

		// display the current date (this method is below)
		updateDisplay();
	}

	@Override
	protected void customize() {
		dateButton.setText("asdf");
		// TODO Auto-generated method stub

	}

	// @Override
	// protected Dialog onCreateDialog(int id) {
	// switch (id) {
	// case DATE_DIALOG_ID:
	// return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
	// mDay);
	// }
	// return null;
	// }

	public void setValue(String value) {
		dateView.setText(value);
	}

	public String getValue() {
		return dateView.getText().toString();
	}

	public Date getDate() {
		return DateWidget.getDate();
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

	public Button getDateButton() {
		return dateButton;
	}

	public Integer getDateId() {
		return DATE_DIALOG_ID;
	}

	public OnDateSetListener getDateSetListener() {
		return mDateSetListener;
	}
}