package info.yalamanchili.gwt.rf;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;

public interface GenericRequest<T extends EntityProxy> extends RequestContext {
	Request<T> findById(Long id);

	Request<Void> save(T entity);

	Request<List<T>> query(int start, int limit);

	Request<Long> size();
}
