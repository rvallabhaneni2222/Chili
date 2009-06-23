package info.yalamanchili.gwt.composite;

public abstract class ReadComposite<T> extends ReadUpdateCreateComposite {
	protected T entity;

	public T getEntity() {
		return entity;
	}

	protected void initReadComposite() {
		init();
	}

	protected void initReadComposite(T entity) {
		init();
		readData(entity);
	}

	protected void initReadComposite(Long id) {
		init();
		readData(id);
	}

	protected abstract void readData(Long id);

	protected abstract void readData(T entity);

}