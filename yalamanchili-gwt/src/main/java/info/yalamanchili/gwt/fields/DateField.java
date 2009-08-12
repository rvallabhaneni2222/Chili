package info.yalamanchili.gwt.fields;

import info.yalamanchili.gwt.composite.GenericFieldComposite;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DatePicker;

// TODO: Auto-generated Javadoc
/**
 * The Class DateField.
 */
public class DateField extends GenericFieldComposite {

	/** The date picker. */

	final TextBox dateField = new TextBox();
	final Button dateButton = new Button("Date");
	final DatePicker datePicker = new DatePicker();

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
		if (datePicker.getValue() == null)
			return null;
		try {
			dt = datePicker.getValue();
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
			datePicker.setValue(date);
	}

	/**
	 * Sets the read only.
	 * 
	 * @param readOnly
	 *            the new read only
	 */
	public void setReadOnly(Boolean readOnly) {
		// datePicker.setReadOnly(readOnly);
	}

	@Override
	public void setup() {

		datePicker.addValueChangeHandler(new ValueChangeHandler() {
			public void onValueChange(ValueChangeEvent event) {
				Date date = (Date) event.getValue();
				String dateString = DateTimeFormat.getFormat("d MMMM yyyy")
						.format(date);
				dateField.setText(dateString);
			}
		});

		ClickHandler dateButtonHandler = new ClickHandler() {
			public void onClick(ClickEvent event) {
				PopupPanel popupPanel = new PopupPanel(true);
				popupPanel.add(datePicker);
				popupPanel.setPopupPosition(dateButton.getAbsoluteLeft()
						+ dateButton.getOffsetWidth(), dateButton
						.getAbsoluteTop()
						+ dateButton.getOffsetHeight());
				popupPanel.show();
			}
		};
		dateButton.addClickHandler(dateButtonHandler);
		fieldPanel.insert(dateField, 0);
		fieldPanel.insert(dateButton, 1);
		// fieldPanel.insert(datePicker, 2);

	}

	@Override
	public void onKeyPress(KeyPressEvent event) {

	}

	@Override
	public void onKeyUp(KeyUpEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onKeyDown(KeyDownEvent arg0) {
		// TODO Auto-generated method stub

	}

}
