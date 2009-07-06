package info.yalamanchili.gwt.rpc;

import info.yalamanchili.gwt.fields.DataType;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import net.sf.gilead.pojo.java5.LightEntity;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GWTServiceAsync {
	<T extends LightEntity> void getClassCanonicalName(T t,
			AsyncCallback<String> canonicalName);

	void getAttributes(String className,
			AsyncCallback<LinkedHashMap<String, DataType>> attrs);

	void getEnumValues(String className, String attributeName,
			AsyncCallback<Enum<?>[]> values);

	void validateStringField(String className, String attributeName,
			String value, AsyncCallback<List<String>> errorMessages);

	void validateIntegerField(String className, String attributeName,
			Integer value, AsyncCallback<List<String>> errorMessages);

	void validateLongField(String className, String attributeName, Long value,
			AsyncCallback<List<String>> errorMessages);

	void validateDateField(String className, String attributeName, Date value,
			AsyncCallback<List<String>> errorMessages);

	void validateBooleanField(String className, String attributeName,
			Boolean value, AsyncCallback<List<String>> errorMessages);

	void validateEnumField(String className, String attributeName,
			String value, AsyncCallback<List<String>> errorMessages);

	<T extends LightEntity> void createEntityFromFields(String className,
			LinkedHashMap<String, Object> fields, AsyncCallback<T> entity);

	<T extends LightEntity> void updateEntityFromFields(T t,
			LinkedHashMap<String, Object> fields, AsyncCallback<T> entity);

	<T extends LightEntity> void getFieldsDataFromEntity(T t,
			AsyncCallback<LinkedHashMap<String, Object>> data);
}
