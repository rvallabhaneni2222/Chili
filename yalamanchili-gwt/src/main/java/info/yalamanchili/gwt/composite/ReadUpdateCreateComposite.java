package info.yalamanchili.gwt.composite;

import info.yalamanchili.gwt.callback.ALAsyncCallback;
import info.yalamanchili.gwt.fields.BooleanField;
import info.yalamanchili.gwt.fields.DataType;
import info.yalamanchili.gwt.fields.DateField;
import info.yalamanchili.gwt.fields.EnumField;
import info.yalamanchili.gwt.fields.FloatField;
import info.yalamanchili.gwt.fields.ImageField;
import info.yalamanchili.gwt.fields.IntegerField;
import info.yalamanchili.gwt.fields.LongField;
import info.yalamanchili.gwt.fields.PasswordField;
import info.yalamanchili.gwt.fields.RichTextField;
import info.yalamanchili.gwt.fields.StringField;
import info.yalamanchili.gwt.rpc.GWTService.GwtServiceAsync;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.gilead.pojo.gwt.LightEntity;

import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;

public abstract class ReadUpdateCreateComposite<T extends LightEntity> extends
		Composite {

	protected T entity;
	protected Long entityId;

	public T getEntity() {
		return entity;
	}

	public Long getEntityId() {
		return entityId;
	}

	protected Boolean readOnly;
	protected String classCanonicalName;

	public ReadUpdateCreateComposite() {
		initWidget(basePanel);
	}

	protected FlowPanel basePanel = new FlowPanel();

	protected CaptionPanel entityCaptionPanel = new CaptionPanel();

	protected FlowPanel entityDisplayWidget = new FlowPanel();
	protected Map<String, Object> fields = new HashMap<String, Object>();

	protected abstract void addListeners();

	protected abstract void configure();

	protected abstract void addWidgets();

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
		if (DataType.FLOAT_FIELD.equals(type)) {
			FloatField floatField = new FloatField(name, readOnly);
			fields.put(name, floatField);
			entityDisplayWidget.add(floatField);
		}
		if (DataType.PASSWORD_FIELD.equals(type)) {
			PasswordField passwordField = new PasswordField(name);
			fields.put(name, passwordField);
			entityDisplayWidget.add(passwordField);
		}
		if (DataType.DROPDOWN_FIELD.equals(type)) {
			StringField dropDownField = new StringField(name, readOnly);
			fields.put(name, dropDownField);
			entityDisplayWidget.add(dropDownField);
		}
		if (DataType.IMAGE_FIELD.equals(type)) {
			ImageField imageField = new ImageField(name, readOnly);
			fields.put(name, imageField);
			entityDisplayWidget.add(imageField);
		}
		if (DataType.RICH_TEXT_AREA.equals(type)) {
			RichTextField richTextField = new RichTextField(name, readOnly);
			fields.put(name, richTextField);
			entityDisplayWidget.add(richTextField);
		}
	}

	protected void addEnumField(String name, Boolean readOnly,
			String attributeName, String className) {
		EnumField enumField = new EnumField(name, readOnly, attributeName,
				className);
		fields.put(name, enumField);
		entityDisplayWidget.add(enumField);
	}

	protected void addDropDown(SelectComposite<?> widget) {
		entityDisplayWidget.add(widget);
	}

	protected void setEnumFeild(String fieldName, String value) {
		EnumField enumField = (EnumField) fields.get(fieldName);
		enumField.setValue(value);
	}

	protected void setDropDownField(String fieldName, Object value) {
		if (value != null) {
			StringField stringField = (StringField) fields.get(fieldName);
			stringField.setText(value.toString());
		}
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

	protected void setField(String fieldName, Float value) {
		FloatField floatField = (FloatField) fields.get(fieldName);
		floatField.setFloat(value);
	}

	protected void setImageField(String fieldName, String url) {
		ImageField imageField = (ImageField) fields.get(fieldName);
		imageField.setImage(url);
	}

	protected void setRichTextField(String fieldName, String value) {
		RichTextField richTextField = (RichTextField) fields.get(fieldName);
		richTextField.setValue(value);
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

	protected Float getFloatField(String fieldName) {
		FloatField floatField = (FloatField) fields.get(fieldName);
		return floatField.getFloat();
	}

	protected String getPasswordField(String fieldName) {
		PasswordField passwordField = (PasswordField) fields.get(fieldName);
		return passwordField.getPassword();
	}

	protected String getImageField(String fieldName) {
		ImageField imageField = (ImageField) fields.get(fieldName);
		return imageField.getImageName();
	}

	protected String getRichTextField(String fieldName) {
		RichTextField richTextField = (RichTextField) fields.get(fieldName);
		return richTextField.getValue();
	}

	protected abstract void addWidgetsBeforeCaptionPanel();

	protected abstract void postValidate();

	protected void preValidate() {
		GwtServiceAsync.instance().validateEntity(entity,
				new ALAsyncCallback<Map<String, List<String>>>() {

					@Override
					public void onResponse(Map<String, List<String>> errors) {
						for (String fieldName : fields.keySet()) {
							BaseField field = (BaseField) fields.get(fieldName);
							if (errors.keySet().contains(fieldName)) {
								field.setValid(false);
								field.setMessage(errors.get(fieldName).get(0));
							} else {
								field.setValid(true);
							}
						}
						postValidate();
					}

				});
	}

	protected Boolean postValidateImpl() {
		Boolean valid = true;
		for (String fieldName : fields.keySet()) {
			BaseField field = (BaseField) fields.get(fieldName);
			if (!field.getValid()) {
				valid = false;
			}
		}
		return valid;
	}

}
