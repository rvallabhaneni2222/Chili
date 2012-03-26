package info.yalamanchili.dao;

import java.util.List;

public interface AbstractDao<T> {
	public T findById(Long id);

	public void save(T instance);

	public List<T> query(int start, int limit);

	public void delete(long id);

	public Long size();

}
