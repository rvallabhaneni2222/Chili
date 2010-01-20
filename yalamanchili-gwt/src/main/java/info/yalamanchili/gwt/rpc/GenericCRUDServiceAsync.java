package info.yalamanchili.gwt.rpc;

import info.yalamanchili.gwt.beans.TableObj;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GenericCRUDServiceAsync {
	public <T extends Serializable> void create(T entity,
			AsyncCallback<T> response);

	public <T extends Serializable> void merge(T entity, T parent,
			AsyncCallback<java.lang.Void> response);

	public <T extends Serializable> void read(T entity, Long id,
			AsyncCallback<T> response);

	public <T extends Serializable> void update(T entity,
			AsyncCallback<T> response);

	public <T extends Serializable> void delete(T entity,
			AsyncCallback<java.lang.Void> response);

	public <T extends Serializable> void getTableObj(T entity, int start,
			AsyncCallback<TableObj<T>> response);

	public <T extends Serializable> void getAllEntities(T entity,
			AsyncCallback<List<T>> response);

	public <T extends Serializable> void getSuggestionsForName(String name,
			T entity, AsyncCallback<List<String>> response);

	public <T extends Serializable> void getEntities(T entity,
			AsyncCallback<List<T>> response);

	public <T extends Serializable> void getRelatedEntities(Long entityID,
			String sourceClass, T target, AsyncCallback<List<T>> response);

	public <T extends Serializable> void getListBoxValues(T entity,
			String[] columns, AsyncCallback<Map<Long, String>> response);

	public <T extends Serializable> void addAll(T parent, T child,
			List<Long> children, AsyncCallback<java.lang.Void> response);
}
