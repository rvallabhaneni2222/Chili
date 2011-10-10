package info.yalamanchili.gwt.composite;

import info.yalamanchili.gwt.fields.BooleanField;
import info.yalamanchili.gwt.fields.CurrencyField;
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
import info.yalamanchili.gwt.rf.GenericRequest;
import info.yalamanchili.gwt.utils.Utils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.validation.ConstraintViolation;

import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.web.bindery.requestfactory.shared.EntityProxy;

public abstract class CRUDComposite<T extends EntityProxy> extends Composite {
	private Logger logger = Logger.getLogger(CRUDComposite.class.getName());
	protected T proxy;
	protected GenericRequest<T> request;
	protected ConstantsWithLookup constants;

	public T getEntity() {
		return proxy;
	}

	protected Boolean readOnly;

	protected String classCanonicalName;

	public CRUDComposite() {
		initWidget(basePanel);
	}

	protected FlowPanel basePanel = new FlowPanel();

	protected CaptionPanel entityCaptionPanel = new CaptionPanel();

	protected FlowPanel entityDisplayWidget = new FlowPanel();

	protected Map<String, BaseField> fields = new HashMap<String, BaseField>();

	protected abstract void addListeners();

	protected abstract void configure();

	protected abstract void addWidgets();

	protected void init(String className, final Boolean readOnly,
			ConstantsWithLookup constants) {
		this.readOnly = readOnly;
		this.classCanonicalName = className;
		this.constants = constants;
		addWidgetsBeforeCaptionPanel();
		entityCaptionPanel.setContentWidget(entityDisplayWidget);
		basePanel.add(entityCaptionPanel);
		entityCaptionPanel.setCaptionHTML(className);
		addListeners();
		configure();
		addWidgets();
	}

	protected void addField(String attributeName, Boolean readOnly,
			Boolean isRequired, DataType type) {
		if (DataType.LONG_FIELD.equals(type)) {
			LongField longField = new LongField(Utils.getAttributeLabel(
					attributeName, classCanonicalName, constants),
					attributeName, classCanonicalName, readOnly, isRequired);
			fields.put(attributeName, longField);
			entityDisplayWidget.add(longField);
		}
		if (DataType.INTEGER_FIELD.equals(type)) {
			IntegerField integerField = new IntegerField(
					Utils.getAttributeLabel(attributeName, classCanonicalName,
							constants), attributeName, classCanonicalName,
					readOnly, isRequired);
			fields.put(attributeName, integerField);
			entityDisplayWidget.add(integerField);
		}
		if (DataType.STRING_FIELD.equals(type)) {
			StringField stringField = new StringField(Utils.getAttributeLabel(
					attributeName, classCanonicalName, constants),
					attributeName, classCanonicalName, readOnly, isRequired);
			fields.put(attributeName, stringField);
			entityDisplayWidget.add(stringField);
		}
		if (DataType.DATE_FIELD.equals(type)) {
			DateField dateField = new DateField(Utils.getAttributeLabel(
					attributeName, classCanonicalName, constants),
					attributeName, classCanonicalName, readOnly, isRequired);
			fields.put(attributeName, dateField);
			entityDisplayWidget.add(dateField);
		}
		if (DataType.BOOLEAN_FIELD.equals(type)) {
			BooleanField booleanField = new BooleanField(
					Utils.getAttributeLabel(attributeName, classCanonicalName,
							constants), attributeName, classCanonicalName,
					readOnly, isRequired);
			fields.put(attributeName, booleanField);
			entityDisplayWidget.add(booleanField);
		}
		if (DataType.FLOAT_FIELD.equals(type)) {
			FloatField floatField = new FloatField(Utils.getAttributeLabel(
					attributeName, classCanonicalName, constants),
					attributeName, classCanonicalName, readOnly, isRequired);
			fields.put(attributeName, floatField);
			entityDisplayWidget.add(floatField);
		}
		if (DataType.PASSWORD_FIELD.equals(type)) {
			PasswordField passwordField = new PasswordField(
					Utils.getAttributeLabel(attributeName, classCanonicalName,
							constants), attributeName, classCanonicalName);
			fields.put(attributeName, passwordField);
			entityDisplayWidget.add(passwordField);
		}
		if (DataType.DROPDOWN_FIELD.equals(type)) {
			StringField dropDownField = new StringField(
					Utils.getAttributeLabel(attributeName, classCanonicalName,
							constants), attributeName, classCanonicalName,
					readOnly, isRequired);
			fields.put(attributeName, dropDownField);
			entityDisplayWidget.add(dropDownField);
		}
		if (DataType.IMAGE_FIELD.equals(type)) {
			ImageField imageField = new ImageField(Utils.getAttributeLabel(
					attributeName, classCanonicalName, constants),
					attributeName, classCanonicalName, readOnly, isRequired);
			fields.put(attributeName, imageField);
			entityDisplayWidget.add(imageField);
		}
		if (DataType.RICH_TEXT_AREA.equals(type)) {
			RichTextField richTextField = new RichTextField(
					Utils.getAttributeLabel(attributeName, classCanonicalName,
							constants), attributeName, classCanonicalName,
					readOnly, isRequired);
			richTextField.addStyleName("y-gwt-RichTextField");
			fields.put(attributeName, richTextField);
			entityDisplayWidget.add(richTextField);
		}
		if (DataType.CURRENCY_FIELD.equals(type)) {
			CurrencyField currencyField = new CurrencyField(
					Utils.getAttributeLabel(attributeName, classCanonicalName,
							constants), attributeName, classCanonicalName,
					readOnly, isRequired);
			currencyField.addStyleName("y-gwt-CurrencyField");
			fields.put(attributeName, currencyField);
			entityDisplayWidget.add(currencyField);
		}
	}

	protected void addEnumField(String name, Boolean readOnly,
			Boolean isRequired, String attributeName, String className) {
		EnumField enumField = new EnumField(Utils.getAttributeLabel(name,
				classCanonicalName, constants), attributeName, className,
				readOnly, isRequired);
		fields.put(name, enumField);
		entityDisplayWidget.add(enumField);
	}

	protected void addDropDown(SelectCompositey<?> widget) {
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

	protected void setImageField(String fieldName, String url, String width,
			String height) {
		ImageField imageField = (ImageField) fields.get(fieldName);
		imageField.setPixelSize(width, height);
		imageField.setImage(url);
	}

	protected void setRichTextField(String fieldName, String value) {
		RichTextField richTextField = (RichTextField) fields.get(fieldName);
		richTextField.setValue(value);
	}

	protected void setField(String fieldName, BigDecimal value, boolean format) {
		CurrencyField curencyField = (CurrencyField) fields.get(fieldName);
		curencyField.setValue(value, format);
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

	protected BigDecimal getCurrencyField(String fieldName) {
		CurrencyField currencyField = (CurrencyField) fields.get(fieldName);
		return currencyField.getValue();
	}

	/* this is to add widgets like more buttons...in the entity poanel */
	protected abstract void addWidgetsBeforeCaptionPanel();

	protected void handleValidations(Set<ConstraintViolation<?>> violations) {
		logger.info("handle validations");
		for (ConstraintViolation<?> violation : violations) {
			String property = violation.getPropertyPath().toString();
			logger.info("violatios" + property);
			for (String fieldName : fields.keySet()) {
				logger.info("field" + fieldName);
				if (fieldName.equalsIgnoreCase(property)) {
					BaseField field = (BaseField) fields.get(fieldName);

					field.setMessage(violation.getMessage().toString());
					logger.info(violation.getMessage().toString());
					logger.info(violation.getMessageTemplate());
					logger.info(violation.getInvalidValue().toString());
					logger.info(violation.getRootBean().getClass().getName());
				}
			}
		}
	}

}
