package info.yalamanchili.gwt.rpc;

import info.yalamanchili.gwt.beans.TableObj;

import java.util.List;
import java.util.Map;

import net.sf.gilead.pojo.java5.LightEntity;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GenericCRUDServiceAsync {
	public <T extends LightEntity> void create(T entity,
			AsyncCallback<T> response);

	public <T extends LightEntity> void merge(T entity, T parent,
			AsyncCallback<java.lang.Void> response);

	public <T extends LightEntity> void read(T entity, Long id,
			AsyncCallback<T> response);

	public <T extends LightEntity> void update(T entity,
			AsyncCallback<T> response);

	public <T extends LightEntity> void delete(T entity,
			AsyncCallback<java.lang.Void> response);

	public <T extends LightEntity> void getTableObj(T entity, int start,
			AsyncCallback<TableObj<T>> response);

	public <T extends LightEntity> void getAllEntities(T entity,
			AsyncCallback<List<T>> response);

	public <T extends LightEntity> void getSuggestionsForName(String name,
			T entity, AsyncCallback<List<String>> response);

	public <T extends LightEntity> void getEntities(T entity,
			AsyncCallback<List<T>> response);

	public <T extends LightEntity> void getRelatedEntities(Long entityID,
			String sourceClass, T target, AsyncCallback<List<T>> response);

	public <T extends LightEntity> void getListBoxValues(T entity,
			String[] columns, AsyncCallback<Map<Long, String>> response);
}
