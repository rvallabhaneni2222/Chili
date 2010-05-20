package info.yalamanchili.mapper;

import info.yalamanchili.commons.DataType;
import info.yalamanchili.commons.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class BeanMapper {

	public Object clonePrimitiveDataTypes(Object source, Class<?> clazz) {
		Object target;
		try {
			target = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("Could not create new Instance", e);
		}
		if (source == null)
			return null;
		for (Field field : ReflectionUtils.getAllFields(source
				.getClass())) {
			if (!DataType.DEFAULT.equals(ReflectionUtils.getDataType(field))
					&& !DataType.ENUM
							.equals(ReflectionUtils.getDataType(field))) {
				cloneField(source, target, field);
			}
		}
		return target;
	}

	public void cloneField(Object source, Object target, Field field) {
		Object value = null;
		Method getterMethod = ReflectionUtils.getGetterMethod(field, source
				.getClass());
		if (getterMethod != null) {
			value = ReflectionUtils.callGetterMethod(getterMethod, source);
		}
		Method setterMethod = ReflectionUtils.getSetterMethod(field, source
				.getClass());
		if (setterMethod != null) {
			ReflectionUtils.callSetterMethod(setterMethod, target, value);
		}
	}
}
