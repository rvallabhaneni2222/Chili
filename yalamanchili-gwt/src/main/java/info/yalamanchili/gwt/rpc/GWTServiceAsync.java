package info.yalamanchili.gwt.rpc;

import info.yalamanchili.gwt.beans.TableObj;
import info.yalamanchili.gwt.fields.DataType;
import info.yalamanchili.security.jpa.YUser;

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
	/* sercurity services async */
	public void createUser(YUser entity, AsyncCallback<YUser> response);

	public void readUser(Long id, AsyncCallback<YUser> response);

	public void updateUser(YUser entity, AsyncCallback<YUser> response);

	public void deleteUser(YUser entity, AsyncCallback<java.lang.Void> response);

	public void getTableObjUser(int start,
			AsyncCallback<TableObj<YUser>> response);

	public void getSuggestionsForNameUser(String name, YUser entity,
			AsyncCallback<List<String>> response);

	public void getEntitiesUser(YUser entity,
			AsyncCallback<List<YUser>> response);

	public void getListBoxValues(String[] columns,
			AsyncCallback<Map<Long, String>> response);

	public void searchUser(String searchText,
			AsyncCallback<List<YUser>> response);

}
