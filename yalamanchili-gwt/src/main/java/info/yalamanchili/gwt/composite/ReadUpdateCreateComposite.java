package info.yalamanchili.gwt.composite;

import info.yalamanchili.gwt.callback.ALSyncCallback;
import info.yalamanchili.gwt.fields.BooleanField;
import info.yalamanchili.gwt.fields.DataType;
import info.yalamanchili.gwt.fields.DateField;
import info.yalamanchili.gwt.fields.EnumField;
import info.yalamanchili.gwt.fields.FloatField;
import info.yalamanchili.gwt.fields.IntegerField;
import info.yalamanchili.gwt.fields.LongField;
import info.yalamanchili.gwt.fields.PasswordField;
import info.yalamanchili.gwt.fields.StringField;
import info.yalamanchili.gwt.fields.TextAreaField;
import info.yalamanchili.gwt.rpc.GWTService.GwtServiceAsync;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.user.client.ui.CaptionPanel;
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

	public T getEntity() {
		return entity;
	}

	public Long getEntityId() {
		return entityId;
	}

	protected Boolean readOnly;

	protected String classCanonicalName;

	/**
	 * Instantiates a new read update create composite.
	 */
	public ReadUpdateCreateComposite() {
		initWidget(basePanel);
	}

	protected VerticalPanel basePanel = new VerticalPanel();

	protected CaptionPanel entityCaptionPanel = new CaptionPanel();

	/** The panel. */
	protected VerticalPanel entityDisplayWidget = new VerticalPanel();

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
	protected void init(String className, final Boolean readOnly,
			final ConstantsWithLookup constants) {
		this.readOnly = readOnly;
		this.classCanonicalName = className;
		addWidgetsBeforeCaptionPanel();
		entityCaptionPanel.setContentWidget(entityDisplayWidget);
		basePanel.add(entityCaptionPanel);
		entityCaptionPanel.setCaptionHTML(className);
		addListeners();
		configure();
		addWidgets();
	}

	/**
	 * Adds the field.
	 * 
	 * @param name
	 *            the name
	 * @param readOnly
	 *            the read only
	 * @param type
	 *            the type
	 */
	protected void addField(String name, Boolean readOnly, DataType type) {
		if (DataType.LONG_FIELD.equals(type)) {
			LongField longField = new LongField(name, readOnly);
			fields.put(name, longField);
			entityDisplayWidget.add(longField);
		}
		if (DataType.INTEGER_FIELD.equals(type)) {
			IntegerField integerField = new IntegerField(name, readOnly);
			fields.put(name, integerField);
			entityDisplayWidget.add(integerField);
		}
		if (DataType.STRING_FIELD.equals(type)) {
			StringField stringField = new StringField(name, readOnly);
			fields.put(name, stringField);
			entityDisplayWidget.add(stringField);
		}
		if (DataType.DATE_FIELD.equals(type)) {
			DateField dateField = new DateField(name, readOnly);
			fields.put(name, dateField);
			entityDisplayWidget.add(dateField);
		}
		if (DataType.BOOLEAN_FIELD.equals(type)) {
			BooleanField booleanField = new BooleanField(name, readOnly);
			fields.put(name, booleanField);
			entityDisplayWidget.add(booleanField);
		}
		if (DataType.PASSWORD_FIELD.equals(type)) {
			PasswordField passwordField = new PasswordField(name);
			fields.put(name, passwordField);
			entityDisplayWidget.add(passwordField);
		}
	}

	protected void addDropDown(SelectComposite<?> widget) {
		entityDisplayWidget.add(widget);
	}

	/**
	 * Sets the enum feild.
	 * 
	 * @param fieldName
	 *            the field name
	 * @param value
	 *            the value
	 */
	protected void setEnumFeild(String fieldName, String value) {
		EnumField enumField = (EnumField) fields.get(fieldName);
		enumField.setValue(value);
	}

	/**
	 * Sets the field.
	 * 
	 * @param fieldName
	 *            the field name
	 * @param number
	 *            the number
	 */
	protected void setField(String fieldName, Long number) {
		LongField longField = (LongField) fields.get(fieldName);
		longField.setLong(number);
	}

	/**
	 * Sets the field.
	 * 
	 * @param fieldName
	 *            the field name
	 * @param number
	 *            the number
	 */
	protected void setField(String fieldName, Integer number) {
		IntegerField integerField = (IntegerField) fields.get(fieldName);
		integerField.setInteger(number);
	}

	/**
	 * Sets the field.
	 * 
	 * @param fieldName
	 *            the field name
	 * @param text
	 *            the text
	 */
	protected void setField(String fieldName, String text) {
		StringField stringField = (StringField) fields.get(fieldName);
		stringField.setText(text);
	}

	/**
	 * Sets the field.
	 * 
	 * @param fieldName
	 *            the field name
	 * @param value
	 *            the value
	 */
	protected void setField(String fieldName, Boolean value) {
		BooleanField booleanField = (BooleanField) fields.get(fieldName);
		booleanField.setValue(value);
	}

	/**
	 * Sets the field.
	 * 
	 * @param fieldName
	 *            the field name
	 * @param date
	 *            the date
	 */
	protected void setField(String fieldName, Date date) {
		DateField dateField = (DateField) fields.get(fieldName);
		dateField.setDate(date);
	}

	/**
	 * Gets the enum field.
	 * 
	 * @param fieldName
	 *            the field name
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
	 * @param fieldName
	 *            the field name
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
	 * @param fieldName
	 *            the field name
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
	 * @param fieldName
	 *            the field name
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
	 * @param fieldName
	 *            the field name
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
	 * @param fieldName
	 *            the field name
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
	 * @param fieldName
	 *            the field name
	 * 
	 * @return the password field
	 */
	protected String getPasswordField(String fieldName) {
		PasswordField passwordField = (PasswordField) fields.get(fieldName);
		return passwordField.getPassword();
	}

	protected abstract void addWidgetsBeforeCaptionPanel();

	protected abstract void postValidate();

	protected void preValidate() {
		validateAllFields(fields.keySet().iterator());
	}

	protected Boolean postValidateImpl() {
		Boolean valid = true;
		for (String fieldId : fields.keySet()) {
			if (fields.get(fieldId) instanceof StringField) {
				StringField field = (StringField) fields.get(fieldId);
				if (!field.getValid()) {
					valid = false;
				}
			}
			if (fields.get(fieldId) instanceof TextAreaField) {
				TextAreaField field = (TextAreaField) fields.get(fieldId);
				if (!field.getValid()) {
					valid = false;
				}
			}
			if (fields.get(fieldId) instanceof PasswordField) {
				PasswordField field = (PasswordField) fields.get(fieldId);
				if (!field.getValid()) {
					valid = false;
				}
			}
			if (fields.get(fieldId) instanceof IntegerField) {
				IntegerField field = (IntegerField) fields.get(fieldId);
				if (!field.getValid()) {
					valid = false;
				}
			}
			if (fields.get(fieldId) instanceof LongField) {
				LongField field = (LongField) fields.get(fieldId);
				if (!field.getValid()) {
					valid = false;
				}
			}
			if (fields.get(fieldId) instanceof DateField) {
				DateField field = (DateField) fields.get(fieldId);
				if (!field.getValid()) {
					valid = false;
				}
			}
			if (fields.get(fieldId) instanceof BooleanField) {
				BooleanField field = (BooleanField) fields.get(fieldId);
				if (!field.getValid()) {
					valid = false;
				}
			}
			if (fields.get(fieldId) instanceof FloatField) {
				FloatField field = (FloatField) fields.get(fieldId);
				if (!field.getValid()) {
					valid = false;
				}
			}
			if (fields.get(fieldId) instanceof EnumField) {
				EnumField field = (EnumField) fields.get(fieldId);
				if (!field.getIsValid()) {
					valid = false;
				}
			}
		}
		return valid;
	}

	protected void validateEnumField(String fieldId, final Iterator<String> itr) {
		final EnumField enumf = (EnumField) fields.get(fieldId);
		GwtServiceAsync.instance().validateEnumField(classCanonicalName,
				fieldId, enumf.getValue(), new ALSyncCallback<List<String>>() {

					@Override
					public void onResponse(List<String> response) {
						if (response.size() < 1) {
							enumf.setIsValid(true);
						} else {
							enumf.setIsValid(true);
							enumf.setMessage(getErrorMessages(response));
						}

					}

					@Override
					public void postResponse(List<String> response) {
						validateAllFields(itr);

					}

				});
	}

	/**
	 * Validate boolean field.
	 * 
	 * @param fieldId
	 *            the field id
	 * @param itr
	 *            the itr
	 */
	protected void validateBooleanField(String fieldId,
			final Iterator<String> itr) {
		final BooleanField booleanf = (BooleanField) fields.get(fieldId);
		GwtServiceAsync.instance().validateBooleanField(classCanonicalName,
				fieldId, booleanf.getValue(),
				new ALSyncCallback<List<String>>() {

					@Override
					public void onResponse(List<String> response) {
						if (response.size() < 1) {
							booleanf.setValid(true);
						} else {
							booleanf.setValid(false);
							booleanf.setMessage(getErrorMessages(response));
						}

					}

					@Override
					public void postResponse(List<String> response) {
						validateAllFields(itr);

					}

				});
	}

	/**
	 * Validate date field.
	 * 
	 * @param fieldId
	 *            the field id
	 * @param itr
	 *            the itr
	 */
	protected void validateDateField(String fieldId, final Iterator<String> itr) {
		final DateField datef = (DateField) fields.get(fieldId);
		GwtServiceAsync.instance().validateDateField(classCanonicalName,
				fieldId, datef.getDate(), new ALSyncCallback<List<String>>() {

					@Override
					public void onResponse(List<String> response) {
						if (response.size() < 1) {
							datef.setValid(true);
						} else {
							datef.setValid(false);
							datef.setMessage(getErrorMessages(response));
						}

					}

					@Override
					public void postResponse(List<String> response) {
						validateAllFields(itr);

					}

				});
	}

	/**
	 * Validate integer field.
	 * 
	 * @param fieldId
	 *            the field id
	 * @param itr
	 *            the itr
	 */
	protected void validateIntegerField(String fieldId,
			final Iterator<String> itr) {
		final IntegerField intf = (IntegerField) fields.get(fieldId);
		GwtServiceAsync.instance().validateIntegerField(classCanonicalName,
				fieldId, intf.getInteger(), new ALSyncCallback<List<String>>() {

					@Override
					public void onResponse(List<String> response) {
						if (response.size() < 1) {
							intf.setValid(true);
						} else {
							intf.setValid(false);
							intf.setMessage(getErrorMessages(response));
						}

					}

					@Override
					public void postResponse(List<String> response) {
						validateAllFields(itr);

					}

				});
	}

	/**
	 * Validate long field.
	 * 
	 * @param fieldId
	 *            the field id
	 * @param itr
	 *            the itr
	 */
	protected void validateLongField(String fieldId, final Iterator<String> itr) {
		final LongField longf = (LongField) fields.get(fieldId);
		GwtServiceAsync.instance().validateLongField(classCanonicalName,
				fieldId, longf.getLong(), new ALSyncCallback<List<String>>() {

					@Override
					public void onResponse(List<String> response) {
						if (response.size() < 1) {
							longf.setValid(true);
						} else {
							longf.setValid(false);
							longf.setMessage(getErrorMessages(response));
						}

					}

					@Override
					public void postResponse(List<String> response) {
						validateAllFields(itr);

					}

				});
	}

	/**
	 * Validate string field.
	 * 
	 * @param fieldId
	 *            the field id
	 * @param itr
	 *            the itr
	 */
	protected void validateStringField(String fieldId,
			final Iterator<String> itr) {
		final StringField sf = (StringField) fields.get(fieldId);
		GwtServiceAsync.instance().validateStringField(classCanonicalName,
				fieldId, sf.getText(), new ALSyncCallback<List<String>>() {

					@Override
					public void onResponse(List<String> response) {
						if (response.size() < 1) {
							sf.setValid(true);
						} else {
							sf.setValid(false);
							sf.setMessage(getErrorMessages(response));
						}
					}

					@Override
					public void postResponse(List<String> response) {
						validateAllFields(itr);
					}
				});
	}

	protected void validateTextAreaField(String fieldId,
			final Iterator<String> itr) {
		final TextAreaField sf = (TextAreaField) fields.get(fieldId);
		GwtServiceAsync.instance().validateStringField(classCanonicalName,
				fieldId, sf.getText(), new ALSyncCallback<List<String>>() {

					@Override
					public void onResponse(List<String> response) {
						if (response.size() < 1) {
							sf.setValid(true);
						} else {
							sf.setValid(false);
							sf.setMessage(getErrorMessages(response));
						}
					}

					@Override
					public void postResponse(List<String> response) {
						validateAllFields(itr);
					}
				});
	}

	protected void validateFloatField(String fieldId, final Iterator<String> itr) {
		final FloatField floatf = (FloatField) fields.get(fieldId);
		GwtServiceAsync.instance().validateFloatField(classCanonicalName,
				fieldId, floatf.getFloat(), new ALSyncCallback<List<String>>() {

					@Override
					public void onResponse(List<String> response) {
						if (response.size() < 1) {
							floatf.setValid(true);
						} else {
							floatf.setValid(false);
							floatf.setMessage(getErrorMessages(response));
						}

					}

					@Override
					public void postResponse(List<String> response) {
						validateAllFields(itr);

					}

				});
	}

	/**
	 * Gets the error messages.
	 * 
	 * @param messages
	 *            the messages
	 * 
	 * @return the error messages
	 */
	protected String getErrorMessages(List<String> messages) {
		String message = new String();
		for (String str : messages) {
			message = message.concat(str);
			message = message.concat(", ");
		}
		return message;
	}

	protected void validateAllFields(final Iterator<String> itr) {
		while (itr.hasNext()) {
			String fieldId = itr.next();
			if (fields.get(fieldId) instanceof StringField) {
				validateStringField(fieldId, itr);
				return;
			}
			if (fields.get(fieldId) instanceof TextAreaField) {
				validateTextAreaField(fieldId, itr);
				return;
			}
			if (fields.get(fieldId) instanceof PasswordField) {
				validateStringField(fieldId, itr);
				return;
			}
			if (fields.get(fieldId) instanceof IntegerField) {
				validateIntegerField(fieldId, itr);
				return;
			}
			if (fields.get(fieldId) instanceof LongField) {
				validateLongField(fieldId, itr);
				return;
			}
			if (fields.get(fieldId) instanceof DateField) {
				validateDateField(fieldId, itr);
				return;
			}
			if (fields.get(fieldId) instanceof FloatField) {
				validateFloatField(fieldId, itr);
				return;
			}
			if (fields.get(fieldId) instanceof BooleanField) {
				validateBooleanField(fieldId, itr);
				return;
			}

			if (fields.get(fieldId) instanceof EnumField) {
				validateEnumField(fieldId, itr);
				return;
			}
		}
		postValidate();
	}

}
