package info.yalamanchili.gwt.rpc;

import info.yalamanchili.gwt.beans.TableObj;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 * This is a GWT service, which will be implemented as a Seam component on the
 * server (see the server package). GWT uses strongly typed RPC interfaces.
 */
public interface MyService extends RemoteService {
	public String askIt(String question);

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

	public <T extends Serializable> Map<Long, String> getListBoxValues(
			T entity, String[] columns);

	public <T extends Serializable> void addAll(T parent, T child,
			List<Long> children);

	public static class MyServiceAsync {
		private static info.yalamanchili.gwt.rpc.MyServiceAsync service;

		public static synchronized info.yalamanchili.gwt.rpc.MyServiceAsync instance() {
			if (service == null) {
				service = (info.yalamanchili.gwt.rpc.MyServiceAsync) GWT
						.create(MyService.class);
				ServiceDefTarget endpoint = (ServiceDefTarget) service;
				String moduleRelativeURL = GWT.getModuleBaseURL()
						+ "seam/resource/gwt";
				endpoint.setServiceEntryPoint(moduleRelativeURL);
			}
			return service;
		}
	}

}
