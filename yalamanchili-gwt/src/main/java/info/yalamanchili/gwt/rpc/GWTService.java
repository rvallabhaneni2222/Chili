package info.yalamanchili.gwt.rpc;

import info.yalamanchili.gwt.fields.DataType;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import net.sf.gilead.pojo.java5.LightEntity;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

// TODO: Auto-generated Javadoc
/**
 * The Interface GWTService.
 */
public interface GWTService extends RemoteService {

	/**
	 * Gets the class canonical name.
	 * 
	 * @param t
	 *            the t
	 * 
	 * @return the class canonical name
	 */
	<T extends LightEntity> String getClassCanonicalName(T t);

	/**
	 * Gets the attributes.
	 * 
	 * @param className
	 *            the class name
	 * 
	 * @return the attributes
	 */
	LinkedHashMap<String, DataType> getAttributes(String className);

	/**
	 * Gets the enum values.
	 * 
	 * @param className
	 *            the class name
	 * @param attributeName
	 *            the attribute name
	 * 
	 * @return the enum values
	 */
	Enum<?>[] getEnumValues(String className, String attributeName);

	/**
	 * Validate string field.
	 * 
	 * @param className
	 *            the class name
	 * @param attributeName
	 *            the attribute name
	 * @param value
	 *            the value
	 * 
	 * @return the list< string>
	 */
	List<String> validateStringField(String className, String attributeName,
			String value);

	List<String> validateFloatField(String className, String attributeName,
			Float value);

	/**
	 * Validate integer field.
	 * 
	 * @param className
	 *            the class name
	 * @param attributeName
	 *            the attribute name
	 * @param value
	 *            the value
	 * 
	 * @return the list< string>
	 */
	List<String> validateIntegerField(String className, String attributeName,
			Integer value);

	/**
	 * Validate long field.
	 * 
	 * @param className
	 *            the class name
	 * @param attributeName
	 *            the attribute name
	 * @param value
	 *            the value
	 * 
	 * @return the list< string>
	 */
	List<String> validateLongField(String className, String attributeName,
			Long value);

	/**
	 * Validate date field.
	 * 
	 * @param className
	 *            the class name
	 * @param attributeName
	 *            the attribute name
	 * @param value
	 *            the value
	 * 
	 * @return the list< string>
	 */
	List<String> validateDateField(String className, String attributeName,
			Date value);

	/**
	 * Validate boolean field.
	 * 
	 * @param className
	 *            the class name
	 * @param attributeName
	 *            the attribute name
	 * @param value
	 *            the value
	 * 
	 * @return the list< string>
	 */
	List<String> validateBooleanField(String className, String attributeName,
			Boolean value);

	/**
	 * Validate enum field.
	 * 
	 * @param className
	 *            the class name
	 * @param attributeName
	 *            the attribute name
	 * @param value
	 *            the value
	 * 
	 * @return the list< string>
	 */
	List<String> validateEnumField(String className, String attributeName,
			String value);

	/**
	 * Creates the entity from fields.
	 * 
	 * @param className
	 *            the class name
	 * @param fields
	 *            the fields
	 * 
	 * @return the t
	 */
	<T extends LightEntity> T createEntityFromFields(String className,
			LinkedHashMap<String, Object> fields);

	<T extends LightEntity> T createEntityFromFieldsWithID(String className,
			LinkedHashMap<String, Object> fields);

	/**
	 * Update entity from fields.
	 * 
	 * @param t
	 *            the t
	 * @param fields
	 *            the fields
	 * 
	 * @return the t
	 */
	<T extends LightEntity> T updateEntityFromFields(T t,
			LinkedHashMap<String, Object> fields);

	/**
	 * Gets the fields data from entity.
	 * 
	 * @param t
	 *            the t
	 * 
	 * @return the fields data from entity
	 */
	<T extends LightEntity> LinkedHashMap<String, Object> getFieldsDataFromEntity(
			T t);

	/* Utility class to call Async secure service */
	/**
	 * The Class GwtServiceAsync.
	 */
	public static class GwtServiceAsync {

		/** The gwtservice. */
		private static info.yalamanchili.gwt.rpc.GWTServiceAsync gwtservice;

		/**
		 * Instance.
		 * 
		 * @return the info.yalamanchili.gwt.rpc. gwt service async
		 */
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
