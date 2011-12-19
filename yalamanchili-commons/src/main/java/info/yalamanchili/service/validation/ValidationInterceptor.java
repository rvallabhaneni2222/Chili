package info.yalamanchili.service.validation;

import info.yalamanchili.commons.ValidatorUtils;
import info.yalamanchili.service.exception.ServiceException;
import info.yalamanchili.service.exception.ServiceException.StatusCode;
import info.yalamanchili.service.types.Error;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class ValidationInterceptor {

	@AroundInvoke
	public Object transformReturn(InvocationContext context) throws Exception {
		validateInputs(context.getParameters());
		complexValidations(context);
		checkForErrors();
		return context.proceed();
	}

	protected void validateInputs(Object[] inputs) {
		if (inputs != null) {
			for (Object input : inputs) {
				validateInput(input);
			}
		}
	}

	protected void validateInput(Object input) {
		Map<String, List<String>> validations = ValidatorUtils
				.validateEntity(input);
		addMessages(validations);
	}

	protected void complexValidations(InvocationContext ctx) throws Exception {
		Object[] args = ctx.getParameters();
		ServiceValidator validator = getServiceValidator(ctx);
		if (validator != null) {
			Object validatorObject = null;
			try {
				validatorObject = validator.value().newInstance();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			// check if this class implements the actual service interface
			if (implementsInterface(ctx.getTarget(), validatorObject)) {
				try {
					Method targetMethod = ctx.getMethod();
					Method method = validatorObject.getClass().getMethod(
							targetMethod.getName(),
							targetMethod.getParameterTypes());
					method.invoke(validatorObject, args);
				} catch (Exception e) {
					throw new RuntimeException(
							"Unable to invoke ServiceValdiator: "
									+ validatorObject, e);
				}
			} else {
				throw new RuntimeException("The ServiceValdiator: "
						+ validatorObject.getClass()
						+ " does not implement service interface.");
			}
		}
	}

	protected ServiceValidator getServiceValidator(InvocationContext ctx) {
		for (Annotation annotation : ctx.getTarget().getClass()
				.getAnnotations()) {
			if (annotation.annotationType().equals(ServiceValidator.class)) {
				return (ServiceValidator) annotation;
			}
		}
		return null;
	}

	protected void addMessages(Map<String, List<String>> validations) {
		for (String attributeName : validations.keySet()) {
			for (String message : validations.get(attributeName)) {
				Error validationError = new Error();
				validationError.setReasonCode(attributeName);
				validationError.setDescription(message);
				ValidationMessages.instance().addError(validationError);
			}
		}
	}

	protected void checkForErrors() throws Exception {
		if (ValidationMessages.instance().isNotEmpty()) {
			throw new ServiceException(StatusCode.INVALID_REQUEST,
					ValidationMessages.instance().getErrors());
		}
	}

	public static Boolean implementsInterface(Object service, Object validator) {
		for (Class<?> validatorInterface : validator.getClass().getInterfaces()) {
			if (validatorInterface.isAssignableFrom(service.getClass())) {
				return true;
			}
		}
		return false;
	}
}