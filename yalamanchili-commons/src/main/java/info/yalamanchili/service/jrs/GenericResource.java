package info.yalamanchili.service.jrs;

import info.yalamanchili.service.exception.ServiceException;

import java.util.List;

//TODO add jrs annotations
public interface GenericResource<T> {

	public T read(Long id) throws ServiceException;

	public void create(T entity) throws ServiceException;

	public void update(T object) throws ServiceException;

	public void delete(Long id) throws ServiceException;

	public List<T> readAll(int offset, int limit) throws ServiceException;

	public Long count() throws ServiceException;

	public List<T> search(String searchText) throws ServiceException;
}
