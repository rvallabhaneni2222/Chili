package info.yalamanchili.android.views;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

public class DateWidget extends Activity {

	protected static final int SUCCESS_RETURN_CODE = 100;
	protected static Map<Integer, Date> dates = new HashMap<Integer, Date>();

	public static Calendar date = Calendar.getInstance();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		showDialog(99);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		int cyear = Calendar.getInstance().get(Calendar.YEAR);
		int cmonth = Calendar.getInstance().get(Calendar.MONTH);
		int cday = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

		
		DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				date.set(year, monthOfYear, dayOfMonth);
				int id = getIntent().getIntExtra("id", 0);
				dates.put(id, date.getTime());
				finish();
			}
		};
		return new DatePickerDialog(this, mDateSetListener, cyear, cmonth, cday);
	}

	public static Date getDate(int id) {
		Date date = dates.get(id);
		dates.remove(id);
		return date;
	}
}
