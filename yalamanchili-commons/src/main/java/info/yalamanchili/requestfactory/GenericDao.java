package info.yalamanchili.requestfactory;

import java.util.List;

/** Generic Dao for all gwt server entities should implement */
public abstract class GenericDao<T> {

	public abstract T findById(Long id);

	public abstract void save(T instance);

	public abstract List<T> query(int start, int limit);

	public abstract void delete(long id);

	public abstract Long size();

	public abstract List<T> search(String searchText);

	public abstract List<T> search(T entity);
}
