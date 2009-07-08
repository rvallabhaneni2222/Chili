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

// TODO: Auto-generated Javadoc
/**
 * The Class ReadUpdateCreateComposite.
 */
public abstract class ReadUpdateCreateComposite<T> extends Composite {

	/** The entity. */
	protected T entity;
	
	/** The entity id. */
	protected Long entityId;

	/**
	 * Instantiates a new read update create composite.
	 */
	public ReadUpdateCreateComposite() {
		initWidget(panel);
	}

	/** The panel. */
	protected VerticalPanel panel = new VerticalPanel();

	/** The fields. */
	protected Map<String, Object> fields = new HashMap<String, Object>();

	/**
	 * Adds the listeners.
	 */
	protected abstract void addListeners();

	/**
	 * Configure.
	 */
	protected abstract void configure();

	/**
	 * Adds the widgets.
	 */
	protected abstract void addWidgets();

	/**
	 * Inits the.
	 */
	protected void init() {
		addListeners();
		configure();
		addWidgets();
	}

	/**
	 * Adds the field.
	 * 
	 * @param name the name
	 * @param readOnly the read only
	 * @param type the type
	 */
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

	/**
	 * Sets the enum feild.
	 * 
	 * @param fieldName the field name
	 * @param value the value
	 */
	protected void setEnumFeild(String fieldName, String value) {
		EnumField enumField = (EnumField) fields.get(fieldName);
		enumField.setValue(value);
	}

	/**
	 * Sets the field.
	 * 
	 * @param fieldName the field name
	 * @param number the number
	 */
	protected void setField(String fieldName, Long number) {
		LongField longField = (LongField) fields.get(fieldName);
		longField.setLong(number);
	}

	/**
	 * Sets the field.
	 * 
	 * @param fieldName the field name
	 * @param number the number
	 */
	protected void setField(String fieldName, Integer number) {
		IntegerField integerField = (IntegerField) fields.get(fieldName);
		integerField.setInteger(number);
	}

	/**
	 * Sets the field.
	 * 
	 * @param fieldName the field name
	 * @param text the text
	 */
	protected void setField(String fieldName, String text) {
		StringField stringField = (StringField) fields.get(fieldName);
		stringField.setText(text);
	}

	/**
	 * Sets the field.
	 * 
	 * @param fieldName the field name
	 * @param value the value
	 */
	protected void setField(String fieldName, Boolean value) {
		BooleanField booleanField = (BooleanField) fields.get(fieldName);
		booleanField.setValue(value);
	}

	/**
	 * Sets the field.
	 * 
	 * @param fieldName the field name
	 * @param date the date
	 */
	protected void setField(String fieldName, Date date) {
		DateField dateField = (DateField) fields.get(fieldName);
		dateField.setDate(date);
	}

	/**
	 * Gets the enum field.
	 * 
	 * @param fieldName the field name
	 * 
	 * @return the enum field
	 */
	protected String getEnumField(String fieldName) {
		EnumField enumField = (EnumField) fields.get(fieldName);
		return enumField.getValue();
	}

	/**
	 * Gets the integer field.
	 * 
	 * @param fieldName the field name
	 * 
	 * @return the integer field
	 */
	protected Integer getIntegerField(String fieldName) {
		IntegerField integerField = (IntegerField) fields.get(fieldName);
		return integerField.getInteger();
	}

	/**
	 * Gets the long field.
	 * 
	 * @param fieldName the field name
	 * 
	 * @return the long field
	 */
	protected Long getLongField(String fieldName) {
		LongField longField = (LongField) fields.get(fieldName);
		return longField.getLong();
	}

	/**
	 * Gets the string field.
	 * 
	 * @param fieldName the field name
	 * 
	 * @return the string field
	 */
	protected String getStringField(String fieldName) {
		StringField stringField = (StringField) fields.get(fieldName);
		return stringField.getText();
	}

	/**
	 * Gets the date field.
	 * 
	 * @param fieldName the field name
	 * 
	 * @return the date field
	 */
	protected Date getDateField(String fieldName) {
		DateField dateField = (DateField) fields.get(fieldName);
		return dateField.getDate();
	}

	/**
	 * Gets the boolean field.
	 * 
	 * @param fieldName the field name
	 * 
	 * @return the boolean field
	 */
	protected Boolean getBooleanField(String fieldName) {
		BooleanField booleanField = (BooleanField) fields.get(fieldName);
		return booleanField.getValue();
	}

	/**
	 * Gets the password field.
	 * 
	 * @param fieldName the field name
	 * 
	 * @return the password field
	 */
	protected String getPasswordField(String fieldName) {
		PasswordField passwordField = (PasswordField) fields.get(fieldName);
		return passwordField.getPassword();
	}

	/**
	 * Gets the entity.
	 * 
	 * @return the entity
	 */
	public T getEntity() {
		return entity;
	}

	/**
	 * Sets the entity.
	 * 
	 * @param entity the new entity
	 */
	public void setEntity(T entity) {
		this.entity = entity;
	}

	/**
	 * Gets the entity id.
	 * 
	 * @return the entity id
	 */
	public Long getEntityId() {
		return entityId;
	}

	/**
	 * Sets the entity id.
	 * 
	 * @param entityId the new entity id
	 */
	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

}
