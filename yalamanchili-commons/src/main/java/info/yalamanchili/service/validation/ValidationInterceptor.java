package info.yalamanchili.service.validation;

import info.yalamanchili.commons.ReflectionUtils;
import info.yalamanchili.commons.ValidatorUtils;
import info.yalamanchili.service.exception.ServiceException;
import info.yalamanchili.service.exception.ServiceException.StatusCode;
import info.yalamanchili.service.types.Error;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class ValidationInterceptor {

	@AroundInvoke
	public Object transformReturn(InvocationContext context) throws Exception {
		validateInputs(context.getParameters());
		processCommonValidators(context.getParameters());
		processServiceValidators(context);
		checkForErrors();
		Object result = null;
		try {
			result = context.proceed();
		} catch (Exception e) {
			throw e;
		}
		checkForErrors();
		return result;
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

	protected void processCommonValidators(Object[] args) throws Exception {
		for (Object input : args) {
			if (input != null) {
				Map<Method, Object> methods = new HashMap<Method, Object>();
				findValidationMethods(input, methods);
				for (Method method : methods.keySet()) {
					Object ref = ReflectionUtils.callGetterMethod(method,
							methods.get(method));
					Validator validator = getValidator(method);
					if (validator != null) {
						GenericValidator validatorIntf = (GenericValidator) validator
								.value().newInstance();
						validatorIntf.validate(ref);
					}
				}
			}
		}
	}

	protected void processServiceValidators(InvocationContext ctx) throws Exception {
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

	// returns all the getters that need to be validated
	public static void findValidationMethods(Object input,
			Map<Method, Object> result) {
		List<Method> methods = ReflectionUtils.getGetterMethods(input
				.getClass());
		for (Method method : methods) {
			if (method.isAnnotationPresent(Validator.class)) {
				result.put(method, input);
			}
			if (method.isAnnotationPresent(Valid.class)
					|| method.isAnnotationPresent(javax.validation.Valid.class)) {
				Object ref = ReflectionUtils.callGetterMethod(method, input);
				findValidationMethods(ref, result);
			}
		}
	}

	protected Validator getValidator(Method method) {
		for (Annotation annotation : method.getAnnotations()) {
			if (annotation.annotationType().equals(Validator.class)) {
				return (Validator) annotation;
			}
		}
		return null;
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
				validationError.setSource(attributeName);
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