package info.yalamanchili.gwt.rpc;

import info.yalamanchili.gwt.fields.DataType;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import net.sf.gilead.pojo.java5.LightEntity;

import com.google.gwt.user.client.rpc.AsyncCallback;

// TODO: Auto-generated Javadoc
/**
 * The Interface GWTServiceAsync.
 */
public interface GWTServiceAsync {

	/**
	 * Gets the attributes.
	 * 
	 * @param className
	 *            the class name
	 * @param attrs
	 *            the attrs
	 * 
	 * @return the attributes
	 */
	void getAttributes(String className,
			AsyncCallback<LinkedHashMap<String, DataType>> attrs);

	/**
	 * Gets the enum values.
	 * 
	 * @param className
	 *            the class name
	 * @param attributeName
	 *            the attribute name
	 * @param values
	 *            the values
	 * 
	 * @return the enum values
	 */
	void getEnumValues(String className, String attributeName,
			AsyncCallback<Enum<?>[]> values);

	void getClassRelations(String className, AsyncCallback<List<String>> errorMessages);

	/**
	 * Validate string field.
	 * 
	 * @param className
	 *            the class name
	 * @param attributeName
	 *            the attribute name
	 * @param value
	 *            the value
	 * @param errorMessages
	 *            the error messages
	 */
	void validateStringField(String className, String attributeName,
			String value, AsyncCallback<List<String>> errorMessages);

	void validateFloatField(String className, String attributeName,
			Float value, AsyncCallback<List<String>> errorMessages);

	/**
	 * Validate integer field.
	 * 
	 * @param className
	 *            the class name
	 * @param attributeName
	 *            the attribute name
	 * @param value
	 *            the value
	 * @param errorMessages
	 *            the error messages
	 */
	void validateIntegerField(String className, String attributeName,
			Integer value, AsyncCallback<List<String>> errorMessages);

	/**
	 * Validate long field.
	 * 
	 * @param className
	 *            the class name
	 * @param attributeName
	 *            the attribute name
	 * @param value
	 *            the value
	 * @param errorMessages
	 *            the error messages
	 */
	void validateLongField(String className, String attributeName, Long value,
			AsyncCallback<List<String>> errorMessages);

	/**
	 * Validate date field.
	 * 
	 * @param className
	 *            the class name
	 * @param attributeName
	 *            the attribute name
	 * @param value
	 *            the value
	 * @param errorMessages
	 *            the error messages
	 */
	void validateDateField(String className, String attributeName, Date value,
			AsyncCallback<List<String>> errorMessages);

	/**
	 * Validate boolean field.
	 * 
	 * @param className
	 *            the class name
	 * @param attributeName
	 *            the attribute name
	 * @param value
	 *            the value
	 * @param errorMessages
	 *            the error messages
	 */
	void validateBooleanField(String className, String attributeName,
			Boolean value, AsyncCallback<List<String>> errorMessages);

	/**
	 * Validate enum field.
	 * 
	 * @param className
	 *            the class name
	 * @param attributeName
	 *            the attribute name
	 * @param value
	 *            the value
	 * @param errorMessages
	 *            the error messages
	 */
	void validateEnumField(String className, String attributeName,
			String value, AsyncCallback<List<String>> errorMessages);

	/**
	 * Creates the entity from fields.
	 * 
	 * @param className
	 *            the class name
	 * @param fields
	 *            the fields
	 * @param entity
	 *            the entity
	 */
	<T extends LightEntity> void createEntityFromFields(String className,
			LinkedHashMap<String, Object> fields, AsyncCallback<T> entity);

	<T extends LightEntity> void createEntityFromFieldsWithID(String className,
			LinkedHashMap<String, Object> fields, AsyncCallback<T> entity);

	/**
	 * Update entity from fields.
	 * 
	 * @param t
	 *            the t
	 * @param fields
	 *            the fields
	 * @param entity
	 *            the entity
	 */
	<T extends LightEntity> void updateEntityFromFields(T t,
			LinkedHashMap<String, Object> fields, AsyncCallback<T> entity);

	/**
	 * Gets the fields data from entity.
	 * 
	 * @param t
	 *            the t
	 * @param data
	 *            the data
	 * 
	 * @return the fields data from entity
	 */
	<T extends LightEntity> void getFieldsDataFromEntity(T t,
			AsyncCallback<LinkedHashMap<String, Object>> data);

}
