package info.yalamanchili.service.validation;

/**
 * @author ayalamanchili Extend this class to implement the common validations
 *         which is called by valdiation interceptor
 */
public abstract class GenericValidator<T> {
	public abstract void validate(T value);
}
