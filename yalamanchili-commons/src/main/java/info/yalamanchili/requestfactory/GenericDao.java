package info.yalamanchili.requestfactory;

/** Generic Dao for all gwt server entities should implement */
public abstract class GenericDao<T> {

	public abstract T findById(Long id);

	public abstract void save(T instance);
}
