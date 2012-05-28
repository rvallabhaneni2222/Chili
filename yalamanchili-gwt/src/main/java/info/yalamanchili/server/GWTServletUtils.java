package info.yalamanchili.server;

import info.yalamanchili.gwt.fields.DataType;
import info.yalamanchili.gwt.ui.DisplayType;
import info.yalamanchili.gwt.ui.Position;
import info.yalamanchili.gwt.ui.UIElement;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//TODO can merge the class methods into Gileadservice
//TODO rename to more appriorate one like JPAQuery hlper
public class GWTServletUtils {
	private static final Log log = LogFactory.getLog(GWTServletUtils.class);

	public static DataType getDataType(Field field) {
		Class<?> clazz = field.getType();
		DisplayType displayType = getDisplayType(field);
		if (!DisplayType.DEFAULT.equals(displayType)) {

			switch (displayType) {
			case TEXT_AREA:
				return DataType.TEXT_AREA_FIELD;
			}
		}
		if (clazz.equals(Integer.class)) {
			return DataType.INTEGER_FIELD;
		}
		if (clazz.equals(String.class)) {
			return DataType.STRING_FIELD;
		}
		if (clazz.equals(Long.class)) {
			return DataType.LONG_FIELD;
		}
		if (clazz.equals(Boolean.class)) {
			return DataType.BOOLEAN_FIELD;
		}
		if (clazz.equals(Date.class)) {
			return DataType.DATE_FIELD;
		}
		if (clazz.equals(Float.class)) {
			return DataType.FLOAT_FIELD;
		}
		if (clazz.isEnum()) {
			return DataType.ENUM_FIELD;
		}
		return DataType.DUMMY_FEILD;
	}

	public static DisplayType getDisplayType(Field field) {
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

	public static <T extends Serializable> String getSearchQueryString(T entity) {
		String query = "FROM " + entity.getClass().getCanonicalName() + " WHERE ";
		List<String> filters = new ArrayList<String>();
		for (Field field : entity.getClass().getDeclaredFields()) {
			if (!DataType.DUMMY_FEILD.equals(GWTServletUtils.getDataType(field))) {
				for (Method method : entity.getClass().getMethods()) {
					if (method.getName().compareToIgnoreCase("get" + field.getName()) == 0) {
						try {
							Object o = method.invoke(entity, null);
							if (o instanceof String && o != null) {
								filters.add(field.getName() + " LIKE '%" + o.toString().trim() + "%'");
							}
							if (o instanceof Long && o != null) {
								filters.add(field.getName() + " = " + o.toString().trim());
							}
							if (o instanceof Integer && o != null) {
								filters.add(field.getName() + " = " + o.toString().trim());
							}
							if (o instanceof Float && o != null) {
								filters.add(field.getName() + " = " + o.toString().trim());
							}
						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				}
			}
		}
		int i = 0;
		for (String filter : filters) {
			query = query.concat(filter);
			i++;
			if (i < filters.size()) {
				query = query.concat(" AND ");
			}
		}
		log.debug("query String" + query);
		return query;
	}

	public static <T extends Serializable> String getSuggestionsQueryForName(String attributeName, T entity) {
		String query = "SELECT " + attributeName + " FROM " + entity.getClass().getCanonicalName();
		log.debug(query);
		return query;
	}

	public static String getListBoxResultsQueryString(String className, String[] columns) {
		String query = "SELECT id,";
		for (String column : columns) {
			query = query.concat(column + ",");
		}
		query = query.substring(0, query.length() - 1);
		query = query.concat(" FROM " + className);
		return query;
	}

	public static Map<Long, String> getListBoxValues(Class<?> clazz, String[] columns, EntityManager em) {
		String query = GWTServletUtils.getListBoxResultsQueryString(clazz.getCanonicalName(), columns);
		Map<Long, String> values = new HashMap<Long, String>();
		Query getListBoxValues = em.createQuery(query);
		for (Object obj : getListBoxValues.getResultList()) {
			Object[] obs = (Object[]) obj;
			values.put((Long) obs[0], (String) obs[1]);
		}
		return values;
	}

	public static List<Field> getEntityFieldsInOrder(Class<?> clazz) {
		List<Field> fields = new ArrayList<Field>();
		Map<Integer, Field> fieldsMap = new HashMap<Integer, Field>();
		for (Field field : clazz.getDeclaredFields()) {
			if (!DataType.DUMMY_FEILD.equals(GWTServletUtils.getDataType(field))) {
				log.debug("putting at: " + getLayoutPosition(field) + ":field :" + field.getName());
				fieldsMap.put(getLayoutPosition(field), field);
			}
		}
		for (int i = 0; i < fieldsMap.size(); i++) {
			if (fieldsMap.keySet().contains(Integer.valueOf(i))) {
				fields.add(fieldsMap.get(Integer.valueOf(i)));
			}
		}
		return fields;
	}

	public static int getLayoutPosition(Field field) {
		int value = 0;
		for (Annotation annotation : field.getAnnotations()) {
			if (annotation instanceof UIElement) {
				UIElement element = ((UIElement) annotation);
				value = Position.getValue(element.position());
			}
		}
		return value;
	}

}
