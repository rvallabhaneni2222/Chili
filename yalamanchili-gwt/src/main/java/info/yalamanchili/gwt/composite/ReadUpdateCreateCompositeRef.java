package info.yalamanchili.gwt.composite;

import info.yalamanchili.gwt.callback.ALAsyncCallback;
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
import info.yalamanchili.gwt.widgets.ALSuggestBox;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;

import net.sf.gilead.pojo.java5.LightEntity;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

// TODO: Auto-generated Javadoc
/**
 * The Class ReadUpdateCreateCompositeRef.
 */
public abstract class ReadUpdateCreateCompositeRef<T extends LightEntity>
		extends Composite {
	protected VerticalPanel basePanel = new VerticalPanel();

	protected CaptionPanel entityCaptionPanel = new CaptionPanel();

	/** The panel. */
	protected VerticalPanel entityDisplayWidget = new VerticalPanel();

	/** The fields. */
	protected Map<String, Object> fields = new HashMap<String, Object>();

	/** The class canonical name. */
	protected String classCanonicalName;

	/** The read only. */
	protected Boolean readOnly;

	/** The entity. */
	protected T entity;

	public T getEntity() {
		return entity;
	}

	/** The entity id. */
	protected Long entityId;

	public Map<String, Object> getFields() {
		return fields;
	}

	public void setFields(Map<String, Object> fields) {
		this.fields = fields;
	}

	/**
	 * Instantiates a new read update create composite ref.
	 */
	public ReadUpdateCreateCompositeRef() {
		initWidget(basePanel);
	}

	/**
	 * Post init.
	 */
	protected abstract void postInit();

	/**
	 * Inits the.
	 * 
	 * @param t
	 *            the t
	 * @param readOnly
	 *            the read only
	 */
	public void init(T t, final Boolean readOnly) {
		this.readOnly = readOnly;
		classCanonicalName = t.getClass().getName();
		addWidgetsBeforeCaptionPanel();
		entityCaptionPanel.setContentWidget(entityDisplayWidget);
		basePanel.add(entityCaptionPanel);
		getAttributes();
		entityCaptionPanel
				.setCaptionHTML(getClassSimpleName(classCanonicalName));
	}

	/**
	 * Gets the attributes.
	 * 
	 * @return the attributes
	 */
	protected void getAttributes() {
		GwtServiceAsync.instance().getAttributes(classCanonicalName,
				new ALAsyncCallback<LinkedHashMap<String, DataType>>() {

					@Override
					public void onResponse(
							LinkedHashMap<String, DataType> attributes) {
						addFields(attributes);
						addListeners();
						configure();
						addWidgets();
						postInit();
					}
				});
	}

	/**
	 * Inits the.
	 * 
	 * @param t
	 *            the t
	 * @param readOnly
	 *            the read only
	 * @param constants
	 *            the constants
	 */
	public void init(T t, final Boolean readOnly,
			final ConstantsWithLookup constants) {
		this.readOnly = readOnly;
		classCanonicalName = t.getClass().getName();
		addWidgetsBeforeCaptionPanel();
		entityCaptionPanel.setContentWidget(entityDisplayWidget);
		basePanel.add(entityCaptionPanel);
		entityCaptionPanel
				.setCaptionHTML(getClassSimpleName(classCanonicalName));
		getAttributesWithConstants(constants);
	}

	/**
	 * Gets the attributes with constants.
	 * 
	 * @param constants
	 *            the constants
	 * 
	 * @return the attributes with constants
	 */
	protected void getAttributesWithConstants(
			final ConstantsWithLookup constants) {
		GwtServiceAsync.instance().getAttributes(classCanonicalName,
				new ALAsyncCallback<LinkedHashMap<String, DataType>>() {

					@Override
					public void onResponse(
							LinkedHashMap<String, DataType> attributes) {
						addFields(attributes, constants);
						addListeners();
						configure();
						addWidgets();
						postInit();
					}
				});
	}

	/**
	 * Adds the listeners.
	 */
	protected abstract void addListeners();

	/**
	 * Configure.
	 */
	protected abstract void configure();

	protected abstract void addWidgetsBeforeCaptionPanel();

	/**
	 * Adds the widgets.
	 */
	protected abstract void addWidgets();

	/**
	 * Adds the fields.
	 * 
	 * @param attributes
	 *            the attributes
	 */
	protected void addFields(LinkedHashMap<String, DataType> attributes) {
		for (String fieldName : attributes.keySet()) {
			addField(fieldName.toUpperCase(), fieldName.toUpperCase(),
					attributes.get(fieldName));
		}
	}

	/**
	 * Adds the fields.
	 * 
	 * @param attributes
	 *            the attributes
	 * @param constants
	 *            the constants
	 */
	protected void addFields(LinkedHashMap<String, DataType> attributes,
			ConstantsWithLookup constants) {
		for (String fieldName : attributes.keySet()) {
			try {
				String property = classCanonicalName.replace(".", "_") + "_"
						+ fieldName;
				String value = constants.getString(property);
				// if a different text is specified in the properties file
				addField(fieldName.toUpperCase(), value, attributes
						.get(fieldName));
			} catch (MissingResourceException e) {
				addField(fieldName.toUpperCase(), fieldName.toUpperCase(),
						attributes.get(fieldName));
			}
		}
	}

	/**
	 * Adds the field.
	 * 
	 * @param id
	 *            the id
	 * @param text
	 *            the text
	 * @param type
	 *            the type
	 */
	protected void addField(String id, String text, DataType type) {
		if (DataType.LONG_FIELD.equals(type)) {
			LongField longField = new LongField(text, readOnly);
			fields.put(id, longField);
			entityDisplayWidget.add(longField);
		}
		if (DataType.INTEGER_FIELD.equals(type)) {
			IntegerField integerField = new IntegerField(text, readOnly);
			fields.put(id, integerField);
			entityDisplayWidget.add(integerField);
		}
		if (DataType.STRING_FIELD.equals(type)) {
			StringField stringField = new StringField(text, readOnly);
			fields.put(id, stringField);
			entityDisplayWidget.add(stringField);
		}
		if (DataType.TEXT_AREA_FIELD.equals(type)) {
			TextAreaField textAreaField = new TextAreaField(text, readOnly);
			fields.put(id, textAreaField);
			entityDisplayWidget.add(textAreaField);
		}
		if (DataType.DATE_FIELD.equals(type)) {
			DateField dateField = new DateField(text, readOnly);
			fields.put(id, dateField);
			entityDisplayWidget.add(dateField);
		}
		if (DataType.BOOLEAN_FIELD.equals(type)) {
			BooleanField booleanField = new BooleanField(text, readOnly);
			fields.put(id, booleanField);
			entityDisplayWidget.add(booleanField);
		}
		if (DataType.ENUM_FIELD.equals(type)) {
			EnumField enumField = new EnumField(text, readOnly);
			fields.put(id, enumField);
			if (!readOnly)
				populateEnumFields(enumField, id);
			entityDisplayWidget.add(enumField);
		}
		if (DataType.PASSWORD_FIELD.equals(type)) {
			PasswordField passwordField = new PasswordField(text);
			fields.put(id, passwordField);
			entityDisplayWidget.add(passwordField);
		}
		if (DataType.FLOAT_FEILD.equals(type)) {
			FloatField floatField = new FloatField(text, readOnly);
			fields.put(id, floatField);
			entityDisplayWidget.add(floatField);
		}

	}

	/**
	 * Sets the field.
	 * 
	 * @param fieldName
	 *            the field name
	 * @param var
	 *            the var
	 */
	protected void setField(String fieldName, Enum<?>[] var) {
		EnumField enumField = (EnumField) getField(fieldName);
		enumField.setValue(var);
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
		LongField longField = (LongField) getField(fieldName);
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
		IntegerField integerField = (IntegerField) getField(fieldName);
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
		if (getField(fieldName) instanceof TextAreaField) {
			TextAreaField textAreaField = (TextAreaField) getField(fieldName);
			textAreaField.setText(text);
		} else {
			StringField stringField = (StringField) getField(fieldName);
			stringField.setText(text);
		}
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
		BooleanField booleanField = (BooleanField) getField(fieldName);
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
		DateField dateField = (DateField) getField(fieldName);
		dateField.setDate(date);
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
		EnumField enumField = (EnumField) getField(fieldName);
		enumField.setValue(value);
	}

	protected void setField(String fieldName, Float var) {
		FloatField floatField = (FloatField) getField(fieldName);
		floatField.setFloat(var);
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
		EnumField enumField = (EnumField) getField(fieldName);
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
		IntegerField integerField = (IntegerField) getField(fieldName);
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
		LongField longField = (LongField) getField(fieldName);
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
		if (getField(fieldName) instanceof TextAreaField) {
			TextAreaField textAreaField = (TextAreaField) getField(fieldName);
			return textAreaField.getText();
		} else {
			StringField stringField = (StringField) getField(fieldName);
			return stringField.getText();
		}

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
		DateField dateField = (DateField) getField(fieldName);
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
		BooleanField booleanField = (BooleanField) getField(fieldName);
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
		PasswordField passwordField = (PasswordField) getField(fieldName);
		return passwordField.getPassword();
	}

	protected Float getFloatField(String fieldName) {
		FloatField floatField = (FloatField) getField(fieldName);
		return floatField.getFloat();
	}

	/**
	 * Populate enum fields.
	 * 
	 * @param enumField
	 *            the enum field
	 * @param attributeName
	 *            the attribute name
	 */
	protected void populateEnumFields(final EnumField enumField,
			String attributeName) {
		GwtServiceAsync.instance().getEnumValues(classCanonicalName,
				attributeName, new ALAsyncCallback<Enum<?>[]>() {

					@Override
					public void onResponse(Enum<?>[] var) {
						enumField.setValue(var);
					}

				});
	}

	/**
	 * Removes the field.
	 * 
	 * @param id
	 *            the id
	 */
	protected void removeField(String id) {
		if (fields.get(id) != null)
			entityDisplayWidget.remove((Widget) fields.get(id));
	}

	/**
	 * Gets the field.
	 * 
	 * @param id
	 *            the id
	 * 
	 * @return the field
	 */
	protected Object getField(String id) {
		if (fields.containsKey(id)) {
			return fields.get(id);
		} else {
			Log.debug("Field with Id:" + id + ": not present");
			return null;
		}

	}

	/**
	 * Pre validate.
	 */
	protected void preValidate() {
		validateAllFields(fields.keySet().iterator());
	}

	/**
	 * Post validate.
	 */
	protected abstract void postValidate();

	/**
	 * Post validate impl.
	 * 
	 * @return the boolean
	 */
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

	/**
	 * Validate all fields.
	 * 
	 * @param itr
	 *            the itr
	 */
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

	/**
	 * Validate enum field.
	 * 
	 * @param fieldId
	 *            the field id
	 * @param itr
	 *            the itr
	 */
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

	/**
	 * Populate entity from fields.
	 * 
	 * @return the linked hash map< string, object>
	 */
	protected LinkedHashMap<String, Object> populateEntityFromFields() {
		LinkedHashMap<String, Object> flds = new LinkedHashMap<String, Object>();
		for (String fieldName : fields.keySet()) {
			if (fields.get(fieldName) instanceof StringField) {
				StringField sf = (StringField) fields.get(fieldName);
				flds.put(fieldName, sf.getText());
			}
			if (fields.get(fieldName) instanceof TextAreaField) {
				TextAreaField sf = (TextAreaField) fields.get(fieldName);
				flds.put(fieldName, sf.getText());
			}
			if (fields.get(fieldName) instanceof IntegerField) {
				IntegerField intf = (IntegerField) fields.get(fieldName);
				flds.put(fieldName, intf.getInteger());
			}
			if (fields.get(fieldName) instanceof LongField) {
				LongField longf = (LongField) fields.get(fieldName);
				flds.put(fieldName, longf.getLong());
			}
			if (fields.get(fieldName) instanceof DateField) {
				DateField datef = (DateField) fields.get(fieldName);
				flds.put(fieldName, datef.getDate());
			}
			if (fields.get(fieldName) instanceof BooleanField) {
				BooleanField bf = (BooleanField) fields.get(fieldName);
				flds.put(fieldName, bf.getValue());
			}
			if (fields.get(fieldName) instanceof FloatField) {
				FloatField bf = (FloatField) fields.get(fieldName);
				flds.put(fieldName, bf.getFloat());
			}
			if (fields.get(fieldName) instanceof EnumField) {
				EnumField enumf = (EnumField) fields.get(fieldName);
				flds.put(fieldName, enumf.getValue());
			}
			if (fields.get(fieldName) instanceof ALSuggestBox) {
				ALSuggestBox suggestBox = (ALSuggestBox) fields.get(fieldName);
				flds.put(fieldName, suggestBox.getText());
			}
		}
		return flds;
	}

	/**
	 * Populate fields with data.
	 * 
	 * @param t
	 *            the t
	 */
	protected void populateFieldsWithData(T t) {
		GwtServiceAsync.instance().getFieldsDataFromEntity(t,
				new ALAsyncCallback<LinkedHashMap<String, Object>>() {

					@Override
					public void onResponse(LinkedHashMap<String, Object> data) {
						for (String str : data.keySet()) {
							if (fields.get(str.toUpperCase()) instanceof StringField) {
								setField(str.toUpperCase(), (String) data
										.get(str));
							}
							if (fields.get(str.toUpperCase()) instanceof TextAreaField) {
								setField(str.toUpperCase(), (String) data
										.get(str));
							}
							if (fields.get(str.toUpperCase()) instanceof IntegerField) {
								setField(str.toUpperCase(), (Integer) data
										.get(str));
							}
							if (fields.get(str.toUpperCase()) instanceof LongField) {
								setField(str.toUpperCase(), (Long) data
										.get(str));
							}
							if (fields.get(str.toUpperCase()) instanceof DateField) {
								setField(str.toUpperCase(), (Date) data
										.get(str));
							}
							if (fields.get(str.toUpperCase()) instanceof BooleanField) {
								setField(str.toUpperCase(), (Boolean) data
										.get(str));
							}
							if (fields.get(str.toUpperCase()) instanceof FloatField) {
								setField(str.toUpperCase(), (Float) data
										.get(str));
							}
							if (fields.get(str.toUpperCase()) instanceof EnumField) {
								setEnumFeild(str.toUpperCase(), (String) data
										.get(str));
							}
						}
					}

				});
	}

	protected String getClassSimpleName(String name) {
		return name.substring(name.lastIndexOf(".") + 1);
	}
}
