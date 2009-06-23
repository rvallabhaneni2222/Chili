package info.yalamanchili.gwt.composite;

import info.yalamanchili.gwt.fields.BooleanField;
import info.yalamanchili.gwt.fields.DataType;
import info.yalamanchili.gwt.fields.DateField;
import info.yalamanchili.gwt.fields.EnumField;
import info.yalamanchili.gwt.fields.IntegerField;
import info.yalamanchili.gwt.fields.LongField;
import info.yalamanchili.gwt.fields.PasswordField;
import info.yalamanchili.gwt.fields.StringField;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

public abstract class ReadUpdateCreateComposite extends Composite {

	public ReadUpdateCreateComposite() {
		initWidget(panel);
	}

	protected VerticalPanel panel = new VerticalPanel();

	protected Map<String, Object> fields = new HashMap<String, Object>();

	protected abstract void addListeners();

	protected abstract void configure();

	protected abstract void addWidgets();

	protected void init() {
		addListeners();
		configure();
		addWidgets();
	}

	protected void addField(String name, Boolean readOnly, DataType type) {
		if (DataType.LONG_FIELD.equals(type)) {
			LongField longField = new LongField(name, readOnly);
			fields.put(name, longField);
			panel.add(longField);
		}
		if (DataType.INTEGER_FIELD.equals(type)) {
			IntegerField integerField = new IntegerField(name, readOnly);
			fields.put(name, integerField);
			panel.add(integerField);
		}
		if (DataType.STRING_FIELD.equals(type)) {
			StringField stringField = new StringField(name, readOnly);
			fields.put(name, stringField);
			panel.add(stringField);
		}
		if (DataType.DATE_FIELD.equals(type)) {
			DateField dateField = new DateField(name, readOnly);
			fields.put(name, dateField);
			panel.add(dateField);
		}
		if (DataType.BOOLEAN_FIELD.equals(type)) {
			BooleanField booleanField = new BooleanField(name, readOnly);
			fields.put(name, booleanField);
			panel.add(booleanField);
		}
		if (DataType.PASSWORD_FIELD.equals(type)) {
			PasswordField passwordField = new PasswordField(name);
			fields.put(name, passwordField);
			panel.add(passwordField);
		}
	}

	protected void setEnumFeild(String fieldName, String value) {
		EnumField enumField = (EnumField) fields.get(fieldName);
		enumField.setValue(value);
	}

	protected void setField(String fieldName, Long number) {
		LongField longField = (LongField) fields.get(fieldName);
		longField.setLong(number);
	}

	protected void setField(String fieldName, Integer number) {
		IntegerField integerField = (IntegerField) fields.get(fieldName);
		integerField.setInteger(number);
	}

	protected void setField(String fieldName, String text) {
		StringField stringField = (StringField) fields.get(fieldName);
		stringField.setText(text);
	}

	protected void setField(String fieldName, Boolean value) {
		BooleanField booleanField = (BooleanField) fields.get(fieldName);
		booleanField.setValue(value);
	}

	protected void setField(String fieldName, Date date) {
		DateField dateField = (DateField) fields.get(fieldName);
		dateField.setDate(date);
	}

	protected String getEnumField(String fieldName) {
		EnumField enumField = (EnumField) fields.get(fieldName);
		return enumField.getValue();
	}

	protected Integer getIntegerField(String fieldName) {
		IntegerField integerField = (IntegerField) fields.get(fieldName);
		return integerField.getInteger();
	}

	protected Long getLongField(String fieldName) {
		LongField longField = (LongField) fields.get(fieldName);
		return longField.getLong();
	}

	protected String getStringField(String fieldName) {
		StringField stringField = (StringField) fields.get(fieldName);
		return stringField.getText();
	}

	protected Date getDateField(String fieldName) {
		DateField dateField = (DateField) fields.get(fieldName);
		return dateField.getDate();
	}

	protected Boolean getBooleanField(String fieldName) {
		BooleanField booleanField = (BooleanField) fields.get(fieldName);
		return booleanField.getValue();
	}

	protected String getPasswordField(String fieldName) {
		PasswordField passwordField = (PasswordField) fields.get(fieldName);
		return passwordField.getPassword();
	}

}
