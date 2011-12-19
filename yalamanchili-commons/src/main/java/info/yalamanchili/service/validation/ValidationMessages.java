package info.yalamanchili.service.validation;

import info.yalamanchili.inject.Locator;
import info.yalamanchili.service.types.Error;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("validationErrors")
@Scope(ScopeType.EVENT)
@AutoCreate
public class ValidationMessages {
	private List<Error> errors = new ArrayList<Error>();

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

	public void addError(Error error) {
		errors.add(error);
	}

	public void clear() {
		errors.clear();
	}

	public boolean isNotEmpty() {
		return !errors.isEmpty();
	}

	public static ValidationMessages instance() {
		return Locator.getInstance(ValidationMessages.class);
	}
}
