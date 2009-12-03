package info.yalamanchili.commons;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.LinkedHashMap;

public class ReflectionUtils {

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
}
