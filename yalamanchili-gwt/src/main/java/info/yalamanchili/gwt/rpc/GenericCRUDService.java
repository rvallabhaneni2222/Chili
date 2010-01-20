package info.yalamanchili.gwt.rpc;

import info.yalamanchili.gwt.beans.TableObj;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public interface GenericCRUDService extends RemoteService {
	public <T extends Serializable> T create(T entity);

	public <T extends Serializable> void merge(T entity, T parent);

	public <T extends Serializable> T read(T entity, Long id);

	public <T extends Serializable> T update(T entity);

	public <T extends Serializable> void delete(T entity);

	public <T extends Serializable> TableObj<T> getTableObj(T entity, int start);

	public <T extends Serializable> List<T> getAllEntities(T entity);

	public <T extends Serializable> List<T> getRelatedEntities(Long entityID,
			String sourceClass, T target);

	public <T extends Serializable> List<String> getSuggestionsForName(
			String name, T entity);

	public <T extends Serializable> List<T> getEntities(T entity);

	public <T extends Serializable> Map<Long, String> getListBoxValues(T entity,
			String[] columns);

	public <T extends Serializable> void addAll(T parent, T child,
			List<Long> children);

	public static class GenericCRUDServiceAsync {

		private static info.yalamanchili.gwt.rpc.GenericCRUDServiceAsync genericcrud;

		public static synchronized info.yalamanchili.gwt.rpc.GenericCRUDServiceAsync instance() {
			if (genericcrud == null) {
				genericcrud = (info.yalamanchili.gwt.rpc.GenericCRUDServiceAsync) GWT
						.create(GenericCRUDService.class);
				ServiceDefTarget endpoint = (ServiceDefTarget) genericcrud;
				String moduleRelativeURL = GWT.getModuleBaseURL()
						+ "genericcrud";
				endpoint.setServiceEntryPoint(moduleRelativeURL);
			}
			return genericcrud;
		}
	}

}
