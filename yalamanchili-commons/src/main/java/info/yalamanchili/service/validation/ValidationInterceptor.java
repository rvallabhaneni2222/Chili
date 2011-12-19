package info.yalamanchili.service.validation;

import info.yalamanchili.commons.ValidatorUtils;
import info.yalamanchili.service.exception.ServiceException;
import info.yalamanchili.service.exception.ServiceException.StatusCode;
import info.yalamanchili.service.types.Error;

import java.util.List;
import java.util.Map;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class ValidationInterceptor {

	@AroundInvoke
	public Object transformReturn(InvocationContext context) throws Exception {
		validateInputs(context.getParameters());
		// TODO complex validations
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
}