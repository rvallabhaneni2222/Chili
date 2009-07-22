package info.yalamanchili.gwt.composite;

// TODO: Auto-generated Javadoc
/**
 * The Class ReadComposite.
 */
public abstract class ReadComposite<T> extends ReadUpdateCreateComposite<T> {

	/** The entity. */
	protected T entity;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * info.yalamanchili.gwt.composite.ReadUpdateCreateComposite#getEntity()
	 */
	public T getEntity() {
		return entity;
	}

	/**
	 * Inits the read composite.
	 */
	protected void initReadComposite() {
		init();
	}

	/**
	 * Inits the read composite.
	 * 
	 * @param entity
	 *            the entity
	 */
	protected void initReadComposite(T entity) {
		init();
		readData(entity);
	}

	/**
	 * Inits the read composite.
	 * 
	 * @param id
	 *            the id
	 */
	protected void initReadComposite(Long id) {
		init();
		readData(id);
	}

	/**
	 * Read data.
	 * 
	 * @param id
	 *            the id
	 */
	protected abstract void readData(Long id);

	/**
	 * Read data.
	 * 
	 * @param entity
	 *            the entity
	 */
	protected abstract void readData(T entity);

}