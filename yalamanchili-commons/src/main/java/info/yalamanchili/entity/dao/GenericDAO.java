package info.yalamanchili.entity.dao;

import info.yalamanchili.service.exception.ServiceException;

import java.util.List;

public interface GenericDAO<T> {
	public T findById(Long id);

	public void save(T instance) throws ServiceException;

	public List<T> query(int start, int limit) throws ServiceException;

	public void delete(long id) throws ServiceException;

	public Long size() throws ServiceException;

	public List<T> search(String searchText) throws ServiceException;

	public List<T> search(T entity) throws ServiceException;
}
