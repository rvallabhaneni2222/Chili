package info.yalamanchili.requestfactory;

import info.yalamanchili.jpa.AbstractEntity;

import com.google.web.bindery.requestfactory.shared.Locator;

public abstract class GenericEntityLocator<T> extends Locator<T, Long> {
	protected T entity;

	@Override
	public T create(Class<? extends T> clazz) {
		try {
			return clazz.newInstance();
		} catch (Exception e) {
			new RuntimeException("Could not instanciate class", e);
		}
		return null;
	}

	@Override
	public abstract T find(Class<? extends T> clazz, Long id);

	@Override
	public Class<T> getDomainType() {
		return (Class<T>) entity.getClass();
	}

	@Override
	public Long getId(T instance) {
		return ((AbstractEntity) instance).getId();
	}

	@Override
	public Class<Long> getIdType() {
		return Long.class;
	}

	@Override
	public Object getVersion(T instance) {
		return ((AbstractEntity) instance).getVersion();
	}

}
