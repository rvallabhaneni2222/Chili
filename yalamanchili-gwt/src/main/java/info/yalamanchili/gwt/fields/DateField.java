package info.yalamanchili.gwt.fields;


import info.yalamanchili.gwt.composite.GenericFieldComposite;
import info.yalamanchili.gwt.utils.Utils;

import java.util.Date;

import org.zenika.widget.client.datePicker.DatePicker;

import com.google.gwt.user.client.Window;

public class DateField extends GenericFieldComposite {

	protected DatePicker datePicker = new DatePicker();

	public DatePicker getDatePicker() {
		return datePicker;
	}

	public DateField(String labelName) {
		super(labelName);
		fieldPanel.insert(datePicker, 1);
	}

	public DateField(String labelName, Boolean readOnly) {
		super(labelName);
		fieldPanel.insert(datePicker, 0);
		setReadOnly(readOnly);
	}

	public Date getDate() {
		Date dt = null;
		if (datePicker.getSelectedDate() == null)
			return null;
		try {
			dt = datePicker.getSelectedDate();
		} catch (Exception e) {
			Window.alert("enter valid date");
		}
		return dt;
	}

	public void setDate(Date date) {
		if (date != null)
			datePicker.setText(Utils.getShortDate(date));
	}

	public void setReadOnly(Boolean readOnly) {
		datePicker.setReadOnly(readOnly);
	}

}
