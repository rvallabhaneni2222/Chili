package info.yalamanchili.gwt.rpc;

import info.yalamanchili.gwt.fields.DataType;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.gilead.pojo.gwt.LightEntity;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GWTServiceAsync {

	void getAttributes(String className,
			AsyncCallback<LinkedHashMap<String, DataType>> attrs);

	void getEnumValues(String className, String attributeName,
			AsyncCallback<Enum<?>[]> values);

	void getClassRelations(String className,
			AsyncCallback<List<String>> errorMessages);

	<T extends LightEntity> void validateField(T entity, String attributeName,
			AsyncCallback<List<String>> errors);

	<T extends LightEntity> void validateEntity(T entity,
			AsyncCallback<Map<String, List<String>>> errors);

	// <T extends Serializable> void createEntityFromFields(String className,
	// LinkedHashMap<String, Object> fields, AsyncCallback<T> entity);
	//
	// <T extends Serializable> void createEntityFromFieldsWithID(
	// String className, LinkedHashMap<String, Object> fields,
	// AsyncCallback<T> entity);
	//
	// <T extends Serializable> void updateEntityFromFields(T t,
	// LinkedHashMap<String, Object> fields, AsyncCallback<T> entity);
	//
	// <T extends Serializable> void getFieldsDataFromEntity(T t,
	// AsyncCallback<LinkedHashMap<String, Object>> data);

}
