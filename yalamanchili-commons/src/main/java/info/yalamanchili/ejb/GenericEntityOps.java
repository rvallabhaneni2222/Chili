package info.yalamanchili.ejb;

import java.util.List;

public interface GenericEntityOps<T> {
	public T create(T entity);

	public T Read(Long id);

	public T Update(T entity);

	public void Delete(T entity);

	public Long getEntitiesSize();

	public List<T> getEntities(int start);

	public List<String> getSuggestionsForName(String name, T entity);

	public List<T> getEntities(T entity);

}
