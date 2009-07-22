package info.yalamanchili.gwt.composite;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.VerticalPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class TreePanelComposite.
 */
public abstract class TreePanelComposite<T> extends Composite implements
		ClickHandler {

	/** The entity. */
	protected T entity;

	/** The panel. */
	protected VerticalPanel panel = new VerticalPanel();

	/** The tree. */
	protected Tree tree = new Tree();

	/**
	 * Gets the entity.
	 * 
	 * @return the entity
	 */
	public T getEntity() {
		return entity;
	}

	/**
	 * Inits the tree panel composite.
	 * 
	 * @param id
	 *            the id
	 */
	public void initTreePanelComposite(Long id) {
		initWidget(panel);
		addListeners();
		configure();
		addWidgets();
		loadEntity(id);
		panel.add(tree);
	}

	/**
	 * Inits the tree panel composite.
	 */
	public void initTreePanelComposite() {
		initWidget(panel);
		addListeners();
		configure();
		addWidgets();
		panel.add(tree);
	}

	/**
	 * Adds the listeners.
	 */
	protected abstract void addListeners();

	/**
	 * Configure.
	 */
	protected abstract void configure();

	/**
	 * Adds the widgets.
	 */
	protected abstract void addWidgets();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user
	 * .client.ui.Widget)
	 */
	public abstract void onClick(ClickEvent event);

	/**
	 * Load entity.
	 * 
	 * @param id
	 *            the id
	 */
	public abstract void loadEntity(Long id);
}
