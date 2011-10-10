package info.yalamanchili.server;

import info.yalamanchili.gwt.beans.TableObj;
import info.yalamanchili.requestfactory.GenericDao;

import java.util.List;
import java.util.Map;

/*has methods specific to GWT client webapp not in genric dao*/
public abstract class GenericGWTDao<T> extends GenericDao<T> {
	public abstract List<String> getSuggestionsForName(String name, T entity);

	public abstract Map<Long, String> getListBoxValues(String[] columns);

	public abstract TableObj<?> getTableObj(int start);
}
