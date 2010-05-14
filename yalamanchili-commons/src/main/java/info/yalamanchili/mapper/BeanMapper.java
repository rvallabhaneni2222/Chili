package info.yalamanchili.mapper;

import info.yalamanchili.commons.DataType;
import info.yalamanchili.commons.ReflectionUtils;

import java.lang.reflect.Field;

public class BeanMapper {

	public Object mapPrimitiveDataTypes(Object source, Class<?> clazz) {
		Object target;
		try {
			target = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Could not create new Instance", e);
		}
		for (Field field : source.getClass().getDeclaredFields()) {
			if (!DataType.DEFAULT.equals(ReflectionUtils.getDataType(field))
					|| !DataType.ENUM
							.equals(ReflectionUtils.getDataType(field))) {
				mapField(source, target, field);
			}
		}
		return target;
	}

	public void mapField(Object source, Object target, Field field) {
		Object value = ReflectionUtils.callGetterMethod(source, field);
		ReflectionUtils.callSetterMethod(target, field, value);
	}
}
