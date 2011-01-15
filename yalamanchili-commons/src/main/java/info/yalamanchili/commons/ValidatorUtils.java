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

	// TODO use hashmap to cache validators for entities
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

	public static List<String> validateField(Object entity, String attributeName) {
		List<String> errorMessages = new ArrayList<String>();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		for (ConstraintViolation v : validator.validateProperty(entity,
				attributeName)) {
			errorMessages.add(v.getMessage());
		}
		return errorMessages;
	}

	// ** validates field attribute by creating the object */
	public static List<String> validateField(String className,
			String attributeName, Object value) {
		if (className == null || className.trim().equals("")
				|| attributeName == null || attributeName.trim().equals("")) {
			return new ArrayList<String>();
		}
		try {
			Class entityClass = Class.forName(className);
			Object entity = entityClass.newInstance();
			ReflectionUtils.callSetter(entity, attributeName, value);
			return validateField(entity, attributeName);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
