package info.yalamanchili.inject;

import org.jboss.seam.Component;

public class Locator {

	public static <T> T getInstance(Class<T> type) {
		return (T) Component.getInstance(type, true);
	}

	public static <T> T getInstance(Class<?> factory, Class<T> type) {
		return (T) getInstance(factory);
	}

	public static <T> T getInstance(String name, Class<T> type) {
		return (T) getInstance(name);
	}

	public static Object getInstance(String name) {
		return Component.getInstance(name, true);
	}
}