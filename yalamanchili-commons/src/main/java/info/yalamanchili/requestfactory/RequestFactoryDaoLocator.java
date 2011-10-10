package info.yalamanchili.requestfactory;

import org.jboss.seam.Component;

import com.google.web.bindery.requestfactory.shared.ServiceLocator;

/**
 * This is gwt request factory class used by gwt client code(request context) to
 * get the server side components that implement the interfaces
 * http://code.google.com/webtoolkit/doc/latest/DevGuideRequestFactory.html#impl
 */
public class RequestFactoryDaoLocator implements ServiceLocator {
	@Override
	public Object getInstance(Class<?> clazz) {
		Object instantiatedClass = Component.getInstance(clazz);
		if (instantiatedClass == null) {
			throw new RuntimeException("Could not instantiate request factory"
					+ clazz.getSimpleName());
		}
		return instantiatedClass;
	}

}
