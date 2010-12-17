package info.yalamanchili.commons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class ValidatorUtils.
 */
public class ValidatorUtils {

	/** The Constant log. */
	private static final Log log = LogFactory.getLog(ValidatorUtils.class);

	/** The properties. */
	protected static Properties validatorProperties = new Properties();

	// protected static Map<String, ClassValidator> validators = new
	// HashMap<String, ClassValidator>();

	public static Properties getValidatorProperties() {
		return validatorProperties;
	}

	static {
		loadProperties();
	}

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

	public static Map<String, List<String>> validateEntity(Object entity) {
		Map<String, List<String>> errors = new HashMap<String, List<String>>();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Object>> constraintViolations = validator
				.validate(entity);
		for (ConstraintViolation val : constraintViolations) {
			if (errors.containsKey(val.getPropertyPath().toString())) {
				errors.get(val.getPropertyPath()).add(val.getMessage());
			} else {
				List<String> err = new ArrayList<String>();
				err.add(val.getMessage());
				errors.put(val.getPropertyPath().toString(), err);
			}
		}
		return errors;

	}

	public static String getValue(String string) {
		log.debug("get value:" + string);
		String value = (String) validatorProperties.get(string);
		log.debug("property value:" + string + ":" + value);
		if (value == null)
			return string;
		return value;
	}

}
