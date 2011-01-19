package info.yalamanchili.server;

import info.yalamanchili.commons.ReflectionUtils;
import info.yalamanchili.commons.ValidatorUtils;
import info.yalamanchili.gwt.fields.DataType;
import info.yalamanchili.gwt.rpc.GWTService;
import info.yalamanchili.gwt.ui.DisplayType;
import info.yalamanchili.gwt.ui.UIElement;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.gilead.pojo.gwt.LightEntity;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.annotations.remoting.WebRemote;
import org.jboss.seam.log.Log;

/** this class acts as service for all server calls from y-gwt project widgets */
@Transactional
@Scope(ScopeType.SESSION)
@Name("info.yalamanchili.gwt.rpc.GWTService")
public class GWTServiceImpl extends GileadService implements GWTService {

	public GWTServiceImpl() {
		super("java:/yalamanchili");
	}

	private static final long serialVersionUID = 1L;
	@Logger
	private Log log;

	private static HashMap<String, LinkedHashMap<String, DataType>> entity_AttributeData = new HashMap<String, LinkedHashMap<String, DataType>>();
	private static HashMap<String, LinkedHashMap<String, DataType>> entity_AttributeData_CAPS = new HashMap<String, LinkedHashMap<String, DataType>>();

	protected Class<?> getEntityClass(String className) {
		try {
			Class<?> entity = (Class<?>) Class.forName(className);
			return entity;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("class specified is not found", e);
		}
	}

	protected DisplayType getDisplayType(Field field) {
		for (Annotation annotation : field.getAnnotations()) {
			if (annotation instanceof UIElement) {
				UIElement element = ((UIElement) annotation);
				if (element.displayType() != null) {
					log.debug("display type" + element.displayType().toString());
					return element.displayType();
				}
			}
		}
		return DisplayType.DEFAULT;
	}

	@WebRemote
	public LinkedHashMap<String, DataType> getAttributes(String className) {

		if (entity_AttributeData.containsKey(className)) {
			log.debug("class:" + className
					+ " already exits in map... returning....");
			return entity_AttributeData.get(className);
		} else {
			log.debug("class:" + className
					+ " is a new class info adding info to map");
			LinkedHashMap<String, DataType> dataFields = new LinkedHashMap<String, DataType>();
			Class<?> entity = getEntityClass(className);
			for (Field field : GWTServletUtils.getEntityFieldsInOrder(entity)) {
				dataFields.put(field.getName(),
						GWTServletUtils.getDataType(field));
			}
			entity_AttributeData.put(className, dataFields);
			return dataFields;
		}
	}

	public LinkedHashMap<String, DataType> getAttributesCaps(String className) {
		LinkedHashMap<String, DataType> dataFields = new LinkedHashMap<String, DataType>();
		if (entity_AttributeData_CAPS.containsKey(className)) {
			log.debug("class:" + className
					+ " already exits in map_CAPS... returning....");
			return entity_AttributeData_CAPS.get(className);
		} else {
			Class<?> entity = getEntityClass(className);
			for (Field field : GWTServletUtils.getEntityFieldsInOrder(entity)) {
				dataFields.put(field.getName().toUpperCase(),
						GWTServletUtils.getDataType(field));
			}
			log.debug("class:" + className
					+ " is a new class info adding info to map_CAPS");
			entity_AttributeData_CAPS.put(className, dataFields);
			return dataFields;
		}
	}

	@WebRemote
	@Override
	public <T extends LightEntity> List<String> validateField(T entity,
			String attributeName) {
		return ValidatorUtils.validateField(entity, attributeName);
	}

	@WebRemote
	@Override
	public <T extends LightEntity> Map<String, List<String>> validateEntity(
			T entity) {
		return ValidatorUtils.validateEntity(entity);
	}

	@Override
	@WebRemote
	public List<String> validateStringField(String className,
			String attributeName, String value) {
		return ValidatorUtils.validateField(className, attributeName, value);
	}

	public <T extends Serializable> T createEntityFromFields(String className,
			LinkedHashMap<String, Object> fields) {
		LinkedHashMap<String, DataType> attributes = getAttributesCaps(className);
		Class<?> entity = getEntityClass(className);
		Object newObject = null;
		try {
			newObject = entity.newInstance();
			for (String fieldName : fields.keySet()) {
				if (fieldName.compareToIgnoreCase("Id") != 0)
					for (Method method : entity.getMethods()) {
						if (method.getName().compareToIgnoreCase(
								"set" + fieldName) == 0) {
							log.debug("calling method:" + method.getName()
									+ ":with:" + fields.get(fieldName));
							if (DataType.ENUM_FIELD.equals(attributes
									.get(fieldName))) {
								Object e = getEnumValue(className, fieldName,
										(String) fields.get(fieldName));
								method.invoke(newObject, e);
							} else {
								method.invoke(newObject, fields.get(fieldName));
							}
						}
					}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (T) newObject;
	}

	public <T extends Serializable> LinkedHashMap<String, Object> getFieldsDataFromEntity(
			T t) {
		LinkedHashMap<String, Object> flds = new LinkedHashMap<String, Object>();
		LinkedHashMap<String, DataType> fields = getAttributes(t.getClass()
				.getCanonicalName());
		try {
			for (String fieldName : getAttributes(
					t.getClass().getCanonicalName()).keySet()) {
				for (Method method : t.getClass().getMethods()) {
					if (method.getName().compareToIgnoreCase("get" + fieldName) == 0) {
						if (fields.get(fieldName).equals(DataType.STRING_FIELD)) {
							String result = (String) method.invoke(t, null);
							flds.put(fieldName, result);
						}
						if (fields.get(fieldName).equals(
								DataType.TEXT_AREA_FIELD)) {
							String result = (String) method.invoke(t, null);
							flds.put(fieldName, result);
						}
						if (fields.get(fieldName)
								.equals(DataType.INTEGER_FIELD)) {
							Integer result = (Integer) method.invoke(t, null);
							flds.put(fieldName, result);
						}
						if (fields.get(fieldName).equals(DataType.LONG_FIELD)) {
							Long result = (Long) method.invoke(t, null);
							flds.put(fieldName, result);
						}
						if (fields.get(fieldName).equals(DataType.FLOAT_FIELD)) {
							Float result = (Float) method.invoke(t, null);
							flds.put(fieldName, result);
						}
						if (fields.get(fieldName).equals(DataType.DATE_FIELD)) {
							Date result = (Date) method.invoke(t, null);
							flds.put(fieldName, result);
						}
						if (fields.get(fieldName)
								.equals(DataType.BOOLEAN_FIELD)) {
							Boolean result = (Boolean) method.invoke(t, null);
							flds.put(fieldName, result);
						}
						if (fields.get(fieldName).equals(DataType.ENUM_FIELD)) {
							Object value = method.invoke(t, null);
							if (value != null) {
								String result = method.invoke(t, null)
										.toString();
								flds.put(fieldName, result);
							}
						}
					}
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return flds;
	}

	public <T extends Serializable> T updateEntityFromFields(T entity,
			LinkedHashMap<String, Object> fields) {
		String className = entity.getClass().getCanonicalName();
		LinkedHashMap<String, DataType> attributes = getAttributesCaps(className);
		try {
			for (String fieldName : fields.keySet()) {
				if (fieldName.compareToIgnoreCase("Id") != 0)
					for (Method method : entity.getClass().getMethods()) {
						if (method.getName().compareToIgnoreCase(
								"set" + fieldName) == 0) {
							log.debug("calling method:" + method.getName()
									+ ":with:" + fields.get(fieldName));
							if (DataType.ENUM_FIELD.equals(attributes
									.get(fieldName))) {
								Object e = getEnumValue(className, fieldName,
										(String) fields.get(fieldName));
								method.invoke(entity, e);
							} else {
								method.invoke(entity, fields.get(fieldName));
							}
						}
					}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entity;
	}

	@WebRemote
	public Enum<?>[] getEnumValues(String className, String attributeName) {
		Enum<?>[] var = null;
		Field field = ReflectionUtils.getField(getEntityClass(className),
				attributeName);
		Class<?> entity = getEntityClass(className);
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

	public Object getEnumValue(String className, String attributeName,
			String value) {
		Object var = null;
		Field field = ReflectionUtils.getField(getEntityClass(className),
				attributeName);
		Class<?> entity = getEntityClass(className);
		for (Method m : field.getType().getDeclaredMethods()) {
			log.debug(m.getName());
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

	public <T extends Serializable> T createEntityFromFieldsWithID(
			String className, LinkedHashMap<String, Object> fields) {

		LinkedHashMap<String, DataType> attributes = getAttributesCaps(className);
		Class<?> entity = getEntityClass(className);
		Object newObject = null;
		try {
			newObject = entity.newInstance();
			for (String fieldName : fields.keySet()) {
				for (Method method : entity.getMethods()) {
					if (method.getName().compareToIgnoreCase("set" + fieldName) == 0) {
						log.debug("calling method:" + method.getName()
								+ ":with:" + fields.get(fieldName));
						if (DataType.ENUM_FIELD.equals(attributes
								.get(fieldName))) {
							Object e = getEnumValue(className, fieldName,
									(String) fields.get(fieldName));
							method.invoke(newObject, e);
						} else {
							method.invoke(newObject, fields.get(fieldName));
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (T) newObject;

	}

	@Override
	@WebRemote
	public List<String> getClassRelations(String className) {
		Class clazz = getEntityClass(className);
		List<String> classRelations = new ArrayList<String>();
		do {
			for (Field field : clazz.getDeclaredFields()) {
				if (field.getType().equals(java.util.List.class)
						|| field.getType().equals(java.util.Set.class)) {
					ParameterizedType type = (ParameterizedType) field
							.getGenericType();
					Type[] typeArguments = type.getActualTypeArguments();
					for (Type typeArgument : typeArguments) {
						classRelations.add(typeArgument.toString());
					}

				}
			}
			clazz = clazz.getSuperclass();
		} while (!clazz.equals(LightEntity.class));
		return classRelations;
	}

}
