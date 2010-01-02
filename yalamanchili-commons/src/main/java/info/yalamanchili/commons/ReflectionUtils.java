package info.yalamanchili.commons;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ReflectionUtils {

	private static final Log log = LogFactory.getLog(ReflectionUtils.class);

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

	public static Map<String, Object> getFieldsDataFromEntity(Object t,
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
						if (fields.get(fieldName).equals(DataType.DATE)) {
							Date result = (Date) method.invoke(t, null);
							attributeValues.put(fieldName, result);
						}
						if (fields.get(fieldName).equals(DataType.BOOLEAN)) {
							Boolean result = (Boolean) method.invoke(t, null);
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

	public static Enum<?>[] getEnumValues(Class<?> entity, String attributeName) {
		Enum<?>[] var = null;
		Field field = getField(entity, attributeName);
		for (Method m : field.getType().getDeclaredMethods()) {
			if (m.getName().equals("values")) {
				try {
					var = (Enum<?>[]) m.invoke(entity, new Object[] {});
				} catch (IllegalArgumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvocationTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for (Enum<?> e : var) {
					log.debug(e.toString());
				}
			}
		}

		return var;
	}

	public static Object getEnumValue(Class<?> entity, String attributeName,
			String value) {
		Object var = null;
		Field field = getField(entity, attributeName);
		for (Method m : field.getType().getDeclaredMethods()) {
			if (m.getName().equals("valueOf")) {
				try {
					var = (Enum<?>) m.invoke(entity, value);
				} catch (IllegalArgumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvocationTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		log.debug(var.toString());
		return var;
	}

	public static Field getField(Class<?> clazz, String attributeName) {
		for (Field field : clazz.getDeclaredFields()) {
			if (field.getName().compareToIgnoreCase(attributeName) == 0) {
				return field;
			}
		}
		return null;
	}

	public static String toStringRef(Object entity) {
		StringBuilder sb = new StringBuilder();
		Map<String, Object> f = ReflectionUtils.getStringFieldsDataFromEntity(
				entity, entity.getClass());
		for (String str : f.keySet()) {
			if (f.get(str) != null) {
				sb.append(f.get(str));
				sb.append(":");
			}
		}
		return sb.toString();
	}
}
