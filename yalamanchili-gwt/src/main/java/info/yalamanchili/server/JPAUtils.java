package info.yalamanchili.server;

import info.yalamanchili.commons.DataType;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class JPAUtils {

	public static String toStringRef(Object entity) {
		StringBuilder sb = new StringBuilder();
		Map<String, Object> f = getStringFieldsDataFromEntity(entity, entity
				.getClass());
		for (String str : f.keySet()) {
			if (f.get(str) != null) {
				sb.append(f.get(str));
				sb.append(":");
			}
		}
		return sb.toString();
	}

	public static LinkedHashMap<String, DataType> getEntityFieldsInfo(
			Class<?> clazz) {
		LinkedHashMap<String, DataType> dataFields = new LinkedHashMap<String, DataType>();
		for (Field field : clazz.getDeclaredFields()) {
			if (!DataType.DEFAULT.equals(getDataType(field))) {
				dataFields.put(field.getName(), getDataType(field));
			}
		}

		return dataFields;
	}

	public static DataType getDataType(Field field) {
		Class<?> clazz = field.getType();
		if (clazz.equals(Integer.class)) {
			return DataType.INTEGER;
		}
		if (clazz.equals(String.class)) {
			return DataType.STRING;
		}
		if (clazz.equals(Long.class)) {
			return DataType.LONG;
		}
		if (clazz.equals(Boolean.class)) {
			return DataType.BOOLEAN;
		}
		if (clazz.equals(Date.class)) {
			return DataType.DATE;
		}
		if (clazz.equals(Float.class)) {
			return DataType.FLOAT;
		}
		if (clazz.isEnum()) {
			return DataType.ENUM;
		}
		return DataType.DEFAULT;
	}

	public static Map<String, Object> getStringFieldsDataFromEntity(Object t,
			Class cls) {
		Map<String, Object> attributeValues = new HashMap<String, Object>();
		LinkedHashMap<String, DataType> fields = getEntityFieldsInfo(cls);
		try {
			for (String fieldName : getEntityFieldsInfo(cls).keySet()) {
				for (Method method : t.getClass().getMethods()) {
					if (method.getName().compareToIgnoreCase("get" + fieldName) == 0) {
						if (fields.get(fieldName).equals(DataType.STRING)) {
							String result = (String) method.invoke(t, null);
							attributeValues.put(fieldName, result);
						}
						if (fields.get(fieldName).equals(DataType.INTEGER)) {
							Integer result = (Integer) method.invoke(t, null);
							attributeValues.put(fieldName, result);
						}
						if (fields.get(fieldName).equals(DataType.LONG)) {
							Long result = (Long) method.invoke(t, null);
							attributeValues.put(fieldName, result);
						}
						if (fields.get(fieldName).equals(DataType.FLOAT)) {
							Float result = (Float) method.invoke(t, null);
							attributeValues.put(fieldName, result);
						}
						if (fields.get(fieldName).equals(DataType.ENUM)) {
							Object value = method.invoke(t, null);
							if (value != null) {
								String result = method.invoke(t, null)
										.toString();
								attributeValues.put(fieldName, result);
							}
						}
					}
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return attributeValues;
	}

}
