package info.yalamanchili.gwt.rpc;

import info.yalamanchili.gwt.beans.TableObj;
import info.yalamanchili.gwt.fields.DataType;
import info.yalamanchili.security.jpa.YUser;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.gilead.pojo.gwt.LightEntity;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public interface GWTService extends RemoteService {

	LinkedHashMap<String, DataType> getAttributes(String className);

	Enum<?>[] getEnumValues(String className, String attributeName);

	List<String> getClassRelations(String className);

	<T extends LightEntity> List<String> validateField(T entity,
			String attributeName);

	<T extends LightEntity> Map<String, List<String>> validateEntity(T entity);

	// <T extends Serializable> T createEntityFromFields(String className,
	// LinkedHashMap<String, Object> fields);
	//
	// <T extends Serializable> T createEntityFromFieldsWithID(String className,
	// LinkedHashMap<String, Object> fields);
	//
	// <T extends Serializable> T updateEntityFromFields(T t,
	// LinkedHashMap<String, Object> fields);
	//
	// <T extends Serializable> LinkedHashMap<String, Object>
	// getFieldsDataFromEntity(
	// T t);

	/* services for security maintaincence */
	public YUser createUser(YUser entity);

	public YUser readUser(Long id);

	public YUser updateUser(YUser entity);

	public void deleteUser(YUser entity);

	public TableObj<YUser> getTableObjUser(int start);

	public List<String> getSuggestionsForNameUser(String name, YUser entity);

	public List<YUser> getEntitiesUser(YUser entity);

	public Map<Long, String> getListBoxValues(String[] columns);

	public List<YUser> searchUser(String searchText);

	public static class GwtServiceAsync {

		/** The gwtservice. */
		private static info.yalamanchili.gwt.rpc.GWTServiceAsync gwtservice;

		public static synchronized info.yalamanchili.gwt.rpc.GWTServiceAsync instance() {
			if (gwtservice == null) {
				gwtservice = (info.yalamanchili.gwt.rpc.GWTServiceAsync) GWT
						.create(GWTService.class);
				ServiceDefTarget endpoint = (ServiceDefTarget) gwtservice;
				String moduleRelativeURL = GWT.getModuleBaseURL()
						+ "seam/resource/gwt2";
				endpoint.setServiceEntryPoint(moduleRelativeURL);
			}
			return gwtservice;
		}
	}

}
