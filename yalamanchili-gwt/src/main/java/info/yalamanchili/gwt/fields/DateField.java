package info.yalamanchili.gwt.fields;

import info.yalamanchili.gwt.composite.GenericFieldComposite;
import info.yalamanchili.gwt.utils.Utils;

import java.util.Date;

import org.zenika.widget.client.datePicker.DatePicker;

import com.google.gwt.user.client.Window;

// TODO: Auto-generated Javadoc
/**
 * The Class DateField.
 */
public class DateField extends GenericFieldComposite {

	/** The date picker. */
	protected DatePicker datePicker = new DatePicker();

	/**
	 * Gets the date picker.
	 * 
	 * @return the date picker
	 */
	public DatePicker getDatePicker() {
		return datePicker;
	}

	/**
	 * Instantiates a new date field.
	 * 
	 * @param labelName
	 *            the label name
	 */
	public DateField(String labelName) {
		super(labelName);
		setup();
	}

	/**
	 * Instantiates a new date field.
	 * 
	 * @param labelName
	 *            the label name
	 * @param readOnly
	 *            the read only
	 */
	public DateField(String labelName, Boolean readOnly) {
		super(labelName);
		setup();
		setReadOnly(readOnly);
	}

	/**
	 * Gets the date.
	 * 
	 * @return the date
	 */
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

	/**
	 * Sets the date.
	 * 
	 * @param date
	 *            the new date
	 */
	public void setDate(Date date) {
		if (date != null)
			datePicker.setText(Utils.getShortDate(date));
	}

	/**
	 * Sets the read only.
	 * 
	 * @param readOnly
	 *            the new read only
	 */
	public void setReadOnly(Boolean readOnly) {
		datePicker.setReadOnly(readOnly);
	}

	@Override
	public void setup() {
		fieldPanel.insert(datePicker, 0);

	}

}
