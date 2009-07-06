package info.yalamanchili.gwt.rpc;

import info.yalamanchili.gwt.fields.DataType;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import net.sf.gilead.pojo.java5.LightEntity;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public interface GWTService extends RemoteService {

	<T extends LightEntity> String getClassCanonicalName(T t);

	LinkedHashMap<String, DataType> getAttributes(String className);

	Enum<?>[] getEnumValues(String className, String attributeName);

	List<String> validateStringField(String className, String attributeName,
			String value);

	List<String> validateIntegerField(String className, String attributeName,
			Integer value);

	List<String> validateLongField(String className, String attributeName,
			Long value);

	List<String> validateDateField(String className, String attributeName,
			Date value);

	List<String> validateBooleanField(String className, String attributeName,
			Boolean value);

	List<String> validateEnumField(String className, String attributeName,
			String value);

	<T extends LightEntity> T createEntityFromFields(String className,
			LinkedHashMap<String, Object> fields);

	<T extends LightEntity> T updateEntityFromFields(T t,
			LinkedHashMap<String, Object> fields);

	<T extends LightEntity> LinkedHashMap<String, Object> getFieldsDataFromEntity(
			T t);

	/* Utility class to call Async secure service */
	public static class GwtServiceAsync {
		private static info.yalamanchili.gwt.rpc.GWTServiceAsync gwtservice;

		public static synchronized info.yalamanchili.gwt.rpc.GWTServiceAsync instance() {
			if (gwtservice == null) {
				gwtservice = (info.yalamanchili.gwt.rpc.GWTServiceAsync) GWT
						.create(GWTService.class);
				ServiceDefTarget endpoint = (ServiceDefTarget) gwtservice;
				String moduleRelativeURL = GWT.getModuleBaseURL()
						+ "gwtservices";
				endpoint.setServiceEntryPoint(moduleRelativeURL);
			}
			return gwtservice;
		}
	}

}
