package info.yalamanchili.gwt.composite;

import com.google.gwt.i18n.client.ConstantsWithLookup;

// TODO: Auto-generated Javadoc
/**
 * The Class ReadComposite.
 */
public abstract class ReadComposite<T> extends ReadUpdateCreateComposite<T> {

	protected void initReadComposite(T entity, String className,
			final ConstantsWithLookup constants) {
		init(className, true, constants);
		entityCaptionPanel.addStyleName("y-gwt-ReadEntityCaptionPanel");
		entityDisplayWidget.addStyleName("y-gwt-ReadEntityDisplayWidget");
		basePanel.addStyleName("y-gwt-ReadBasePanel");
		readData(entity);
	}

	/**
	 * Inits the read composite.
	 * 
	 * @param id
	 *            the id
	 */
	protected void initReadComposite(Long id, String className,
			final ConstantsWithLookup constants) {
		init(className, true, constants);
		entityCaptionPanel.addStyleName("y-gwt-ReadEntityCaptionPanel");
		entityDisplayWidget.addStyleName("y-gwt-ReadEntityDisplayWidget");
		basePanel.addStyleName("y-gwt-ReadBasePanel");
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