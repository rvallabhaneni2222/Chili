package info.yalamanchili.gwt.composite;

import info.yalamanchili.gwt.callback.ALAsyncCallback;
import info.yalamanchili.gwt.callback.ALSyncCallback;
import info.yalamanchili.gwt.fields.BooleanField;
import info.yalamanchili.gwt.fields.DataType;
import info.yalamanchili.gwt.fields.DateField;
import info.yalamanchili.gwt.fields.EnumField;
import info.yalamanchili.gwt.fields.IntegerField;
import info.yalamanchili.gwt.fields.LongField;
import info.yalamanchili.gwt.fields.PasswordField;
import info.yalamanchili.gwt.fields.StringField;
import info.yalamanchili.gwt.rpc.GWTService.GwtServiceAsync;

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
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class ReadUpdateCreateCompositeRef<T extends LightEntity>
		extends Composite {
	protected VerticalPanel panel = new VerticalPanel();
	protected Map<String, Object> fields = new HashMap<String, Object>();
	protected String classCanonicalName;
	protected Boolean readOnly;
	protected T entity;
	protected Long entityId;

	public T getEntity() {
		return entity;
	}

	public Long getId() {
		return entityId;
	}

	public ReadUpdateCreateCompositeRef() {
		initWidget(panel);
	}

	protected abstract void postInit();

	public void init(T t, final Boolean readOnly) {
		this.readOnly = readOnly;
		GwtServiceAsync.instance().getClassCanonicalName(t,
				new ALAsyncCallback<String>() {
					@Override
					public void onResponse(String name) {
						classCanonicalName = name;
						getAttributes();
					}
				});
	}

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

	public void init(T t, final Boolean readOnly,
			final ConstantsWithLookup constants) {
		this.readOnly = readOnly;
		GwtServiceAsync.instance().getClassCanonicalName(t,
				new ALAsyncCallback<String>() {
					@Override
					public void onResponse(String name) {
						classCanonicalName = name;
						getAttributesWithConstants(constants);
					}
				});

	}

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

	protected abstract void addListeners();

	protected abstract void configure();

	protected abstract void addWidgets();

	protected void addFields(LinkedHashMap<String, DataType> attributes) {
		for (String fieldName : attributes.keySet()) {
			addField(fieldName.toUpperCase(), fieldName.toUpperCase(),
					attributes.get(fieldName));
		}
	}

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

	protected void addField(String id, String text, DataType type) {
		if (DataType.LONG_FIELD.equals(type)) {
			LongField longField = new LongField(text, readOnly);
			fields.put(id, longField);
			panel.add(longField);
		}
		if (DataType.INTEGER_FIELD.equals(type)) {
			IntegerField integerField = new IntegerField(text, readOnly);
			fields.put(id, integerField);
			panel.add(integerField);
		}
		if (DataType.STRING_FIELD.equals(type)) {
			StringField stringField = new StringField(text, readOnly);
			fields.put(id, stringField);
			panel.add(stringField);
		}
		if (DataType.DATE_FIELD.equals(type)) {
			DateField dateField = new DateField(text, readOnly);
			fields.put(id, dateField);
			panel.add(dateField);
		}
		if (DataType.BOOLEAN_FIELD.equals(type)) {
			BooleanField booleanField = new BooleanField(text, readOnly);
			fields.put(id, booleanField);
			panel.add(booleanField);
		}
		if (DataType.ENUM_FIELD.equals(type)) {
			EnumField enumField = new EnumField(text, readOnly);
			fields.put(id, enumField);
			if (!readOnly)
				populateEnumFields(enumField, id);
			panel.add(enumField);
		}
		if (DataType.PASSWORD_FIELD.equals(type)) {
			PasswordField passwordField = new PasswordField(text);
			fields.put(id, passwordField);
			panel.add(passwordField);
		}
	}

	protected void setField(String fieldName, Enum<?>[] var) {
		EnumField enumField = (EnumField) getField(fieldName);
		enumField.setValue(var);
	}

	protected void setField(String fieldName, Long number) {
		LongField longField = (LongField) getField(fieldName);
		longField.setLong(number);
	}

	protected void setField(String fieldName, Integer number) {
		IntegerField integerField = (IntegerField) getField(fieldName);
		integerField.setInteger(number);
	}

	protected void setField(String fieldName, String text) {
		StringField stringField = (StringField) getField(fieldName);
		stringField.setText(text);
	}

	protected void setField(String fieldName, Boolean value) {
		BooleanField booleanField = (BooleanField) getField(fieldName);
		booleanField.setValue(value);
	}

	protected void setField(String fieldName, Date date) {
		DateField dateField = (DateField) getField(fieldName);
		dateField.setDate(date);
	}

	protected void setEnumFeild(String fieldName, String value) {
		EnumField enumField = (EnumField) getField(fieldName);
		enumField.setValue(value);
	}

	protected String getEnumField(String fieldName) {
		EnumField enumField = (EnumField) getField(fieldName);
		return enumField.getValue();
	}

	protected Integer getIntegerField(String fieldName) {
		IntegerField integerField = (IntegerField) getField(fieldName);
		return integerField.getInteger();
	}

	protected Long getLongField(String fieldName) {
		LongField longField = (LongField) getField(fieldName);
		return longField.getLong();
	}

	protected String getStringField(String fieldName) {
		StringField stringField = (StringField) getField(fieldName);
		return stringField.getText();
	}

	protected Date getDateField(String fieldName) {
		DateField dateField = (DateField) getField(fieldName);
		return dateField.getDate();
	}

	protected Boolean getBooleanField(String fieldName) {
		BooleanField booleanField = (BooleanField) getField(fieldName);
		return booleanField.getValue();
	}

	protected String getPasswordField(String fieldName) {
		PasswordField passwordField = (PasswordField) getField(fieldName);
		return passwordField.getPassword();
	}

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

	protected void removeField(String id) {
		if (fields.get(id) != null)
			panel.remove((Widget) fields.get(id));
	}

	protected Object getField(String id) {
		if (fields.containsKey(id)) {
			return fields.get(id);
		} else {
			Log.debug("Field with Id:" + id + ": not present");
			return null;
		}

	}

	protected void preValidate() {
		validateAllFields(fields.keySet().iterator());
	}

	protected abstract void postValidate();

	protected Boolean postValidateImpl() {
		Boolean valid = true;
		for (String fieldId : fields.keySet()) {
			if (fields.get(fieldId) instanceof StringField) {
				StringField field = (StringField) fields.get(fieldId);
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
			if (fields.get(fieldId) instanceof EnumField) {
				EnumField field = (EnumField) fields.get(fieldId);
				if (!field.getIsValid()) {
					valid = false;
				}
			}
		}
		return valid;
	}

	protected void validateAllFields(final Iterator<String> itr) {
		while (itr.hasNext()) {
			String fieldId = itr.next();
			if (fields.get(fieldId) instanceof StringField) {
				validateStringField(fieldId, itr);
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

	protected String getErrorMessages(List<String> messages) {
		String message = ":";
		for (String str : messages) {
			message = message.concat(str);
			message = message.concat(", ");
		}
		return message;
	}

	protected LinkedHashMap<String, Object> populateEntityFromFields() {
		LinkedHashMap<String, Object> flds = new LinkedHashMap<String, Object>();
		for (String fieldName : fields.keySet()) {
			if (fields.get(fieldName) instanceof StringField) {
				StringField sf = (StringField) fields.get(fieldName);
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
			if (fields.get(fieldName) instanceof EnumField) {
				EnumField enumf = (EnumField) fields.get(fieldName);
				flds.put(fieldName, enumf.getValue());
			}
		}
		return flds;
	}

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
							if (fields.get(str.toUpperCase()) instanceof EnumField) {
								setEnumFeild(str.toUpperCase(), (String) data
										.get(str));
							}
						}
					}

				});
	}
}
