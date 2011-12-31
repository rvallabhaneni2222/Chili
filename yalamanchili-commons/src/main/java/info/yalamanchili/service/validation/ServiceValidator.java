package info.yalamanchili.service.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * annotated any service class with this annotation with the implementation
 * class as the value of the annotation eg:
 * 
 * @ServiceValidator(value=RegistrationServiceValidator.class) 
 *                                                             RegistrationServiceValidator
 *                                                             must implement
 *                                                             RegistrationService
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ServiceValidator {
	Class<?> value();
}
