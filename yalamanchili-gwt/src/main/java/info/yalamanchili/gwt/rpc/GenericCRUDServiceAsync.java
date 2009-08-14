package info.yalamanchili.gwt.rpc;

import info.yalamanchili.gwt.beans.TableObj;

import java.util.List;

import net.sf.gilead.pojo.java5.LightEntity;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GenericCRUDServiceAsync {
	public <T extends LightEntity> void create(T entity,
			AsyncCallback<T> response);

	public <T extends LightEntity> void read(T entity, Long id,
			AsyncCallback<T> response);

	public <T extends LightEntity> void update(T entity,
			AsyncCallback<T> response);

	public <T extends LightEntity> void delete(T entity,
			AsyncCallback<java.lang.Void> response);

	public <T extends LightEntity> void getEntitySize(T entity,
			AsyncCallback<Long> response);

	public <T extends LightEntity> void getEntities(T entity, int start,
			AsyncCallback<List<T>> response);

	public <T extends LightEntity> void getTableObj(T entity, int start,
			AsyncCallback<TableObj<T>> response);

	public <T extends LightEntity> void getAllEntities(T entity,
			AsyncCallback<List<T>> response);

	public <T extends LightEntity> void getSuggestionsForName(String name,
			T entity, AsyncCallback<List<String>> response);

	public <T extends LightEntity> void getEntities(T entity,
			AsyncCallback<List<T>> response);
}
