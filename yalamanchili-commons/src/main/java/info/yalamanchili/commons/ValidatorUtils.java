package info.yalamanchili.commons;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.validator.Digits;
import org.hibernate.validator.DigitsValidator;
import org.hibernate.validator.EmailValidator;
import org.hibernate.validator.Future;
import org.hibernate.validator.FutureValidator;
import org.hibernate.validator.Length;
import org.hibernate.validator.LengthValidator;
import org.hibernate.validator.Max;
import org.hibernate.validator.MaxValidator;
import org.hibernate.validator.Min;
import org.hibernate.validator.MinValidator;
import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;
import org.hibernate.validator.Past;
import org.hibernate.validator.PastValidator;
import org.hibernate.validator.Range;
import org.hibernate.validator.RangeValidator;

// TODO: Auto-generated Javadoc
/**
 * The Class ValidatorUtils.
 */
public class ValidatorUtils {

	/** The Constant log. */
	private static final Log log = LogFactory.getLog(ValidatorUtils.class);

	/** The properties. */
	protected static Properties validatorProperties = new Properties();

	/**
	 * Gets the properties.
	 * 
	 * @return the properties
	 */
	public static Properties getValidatorProperties() {
		return validatorProperties;
	}

	static {
		loadProperties();
	}

	/**
	 * Load properties.
	 */
	public static Properties loadProperties() {
		try {
			validatorProperties = PropertyFileLoader
					.loadProperties("yalamanchili-validator.properties");
			log.debug("loaded yalamanchili-validator.properties");
		} catch (IllegalArgumentException exception) {
			validatorProperties = PropertyFileLoader
					.loadProperties("default-yalamanchili-validator.properties");
			log.debug("loaded default-yalamanchili-validator.properties");
		}
		return validatorProperties;
	}

	/*
	 * @Length hibernate validator
	 */
	/**
	 * Validate length.
	 * 
	 * @param annotation
	 *            the annotation
	 * @param value
	 *            the value
	 * @param errorMessages
	 *            the error messages
	 */
	public static void validateLength(Length annotation, String value,
			List<String> errorMessages) {
		LengthValidator validator = new LengthValidator();
		validator.initialize(annotation);
		if (!validator.isValid(value)) {
			log.debug(annotation.message());
			errorMessages.add(getValue(annotation.message()));
		}
	}

	/*
	 * @NotNull hibernate validator
	 */
	/**
	 * Validate not null.
	 * 
	 * @param annotation
	 *            the annotation
	 * @param value
	 *            the value
	 * @param errorMessages
	 *            the error messages
	 */
	public static void validateNotNull(NotNull annotation, Object value,
			List<String> errorMessages) {
		if (value == null) {
			errorMessages.add(getValue(annotation.message()));
		}
	}

	public static void validateNotEmpty(NotEmpty annotation, Object value,
			List<String> errorMessages) {
		if (value == null || value.toString().trim().equals("")) {
			errorMessages.add(getValue(annotation.message()));
		}
	}

	/*
	 * @Email hibernate validator
	 */
	/**
	 * Validate email.
	 * 
	 * @param annotation
	 *            the annotation
	 * @param value
	 *            the value
	 * @param errorMessages
	 *            the error messages
	 */
	public static void validateEmail(org.hibernate.validator.Email annotation,
			String value, List<String> errorMessages) {
		EmailValidator validator = new EmailValidator();
		validator.initialize(null);
		if (!validator.isValid(value)) {
			log.debug(annotation.message());
			errorMessages.add(getValue(annotation.message()));
		}
	}

	/*
	 * @Max hibernate validator
	 */
	/**
	 * Validate max.
	 * 
	 * @param annotation
	 *            the annotation
	 * @param value
	 *            the value
	 * @param errorMessages
	 *            the error messages
	 */
	public static void validateMax(Max annotation, Object value,
			List<String> errorMessages) {
		MaxValidator validator = new MaxValidator();
		validator.initialize(annotation);
		if (!validator.isValid(value)) {
			log.debug(annotation.message());
			errorMessages.add(getValue(annotation.message()));
		}
	}

	/*
	 * @Min hibernate validator
	 */
	/**
	 * Validate min.
	 * 
	 * @param annotation
	 *            the annotation
	 * @param value
	 *            the value
	 * @param errorMessages
	 *            the error messages
	 */
	public static void validateMin(Min annotation, Object value,
			List<String> errorMessages) {
		MinValidator validator = new MinValidator();
		validator.initialize(annotation);
		if (!validator.isValid(value)) {
			log.debug(annotation.message());
			errorMessages.add(annotation.message());
		}
	}

	/*
	 * @Range hibernate validator
	 */
	/**
	 * Validate range.
	 * 
	 * @param annotation
	 *            the annotation
	 * @param value
	 *            the value
	 * @param errorMessages
	 *            the error messages
	 */
	public static void validateRange(Range annotation, Object value,
			List<String> errorMessages) {
		RangeValidator validator = new RangeValidator();
		validator.initialize(annotation);
		if (!validator.isValid(value)) {
			log.debug(annotation.message());
			errorMessages.add(getValue(annotation.message()));
		}
	}

	/*
	 * @Range hibernate validator
	 */
	/**
	 * Validate digits.
	 * 
	 * @param annotation
	 *            the annotation
	 * @param value
	 *            the value
	 * @param errorMessages
	 *            the error messages
	 */
	public static void validateDigits(Digits annotation, Object value,
			List<String> errorMessages) {
		DigitsValidator validator = new DigitsValidator();
		validator.initialize(annotation);
		if (!validator.isValid(value)) {
			log.debug(annotation.message());
			errorMessages.add(getValue(annotation.message()));
		}
	}

	/*
	 * @Past hibernate validator
	 */
	/**
	 * Validate past.
	 * 
	 * @param annotation
	 *            the annotation
	 * @param value
	 *            the value
	 * @param errorMessages
	 *            the error messages
	 */
	public static void validatePast(Past annotation, Date value,
			List<String> errorMessages) {
		PastValidator validator = new PastValidator();
		validator.initialize(annotation);
		if (!validator.isValid(value)) {
			log.debug(annotation.message());
			errorMessages.add(annotation.message());
		}
	}

	/*
	 * @Future hibernate validator
	 */
	/**
	 * Validate future.
	 * 
	 * @param annotation
	 *            the annotation
	 * @param value
	 *            the value
	 * @param errorMessages
	 *            the error messages
	 */
	public static void validateFuture(Future annotation, Date value,
			List<String> errorMessages) {
		FutureValidator validator = new FutureValidator();
		validator.initialize(annotation);
		if (!validator.isValid(value)) {
			log.debug(annotation.message());
			errorMessages.add(getValue(annotation.message()));
		}
	}

	/**
	 * Gets the value.
	 * 
	 * @param string
	 *            the string
	 * 
	 * @return the value
	 */
	public static String getValue(String string) {
		log.debug("get value:" + string);
		String value = (String) validatorProperties.get(string);
		log.debug("property value:" + string + ":" + value);
		if (value == null)
			return string;
		return value;
	}

}
