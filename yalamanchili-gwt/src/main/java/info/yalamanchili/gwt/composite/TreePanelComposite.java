package info.yalamanchili.gwt.composite;

import info.yalamanchili.gwt.callback.ALAsyncCallback;
import info.yalamanchili.gwt.rpc.GWTService.GwtServiceAsync;
import info.yalamanchili.gwt.widgets.ClickableLink;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class TreePanelComposite.
 */
public abstract class TreePanelComposite<T> extends Composite implements
		ClickHandler {
	protected Map<String, ClickableLink> links = new HashMap<String, ClickableLink>();
	/** The entity. */
	protected T entity;

	/** The panel. */
	protected VerticalPanel panel = new VerticalPanel();

	/** The tree. */
	protected Tree tree = new Tree();
	protected TreeItem rootNode = new TreeItem("root");

	/**
	 * Gets the entity.
	 * 
	 * @return the entity
	 */
	public T getEntity() {
		return entity;
	}

	public TreePanelComposite() {
		initWidget(panel);
	}

	public void initTreePanelComposite(String className) {
		panel.add(tree);
		panel.addStyleName("y-gwt-TreePanel");
		rootNode.setText(getClassSimpleName(className));
		tree.addItem(rootNode);
		GwtServiceAsync.instance().getClassRelations(className,
				new ALAsyncCallback<List<String>>() {

					@Override
					public void onResponse(List<String> classes) {
						for (String className : classes) {
							addFirstChildLink(className);
						}
					}

				});
		addListeners();
		configure();
		addWidgets();

	}

	protected void addFirstChildLink(String name) {
		ClickableLink link = new ClickableLink(getClassSimpleName(name));
		rootNode.addItem(link);
		links.put(name, link);
		link.addClickHandler(this);
	}

	protected void removeFirstChildLink(String className) {
		if (links.containsKey(className)) {
			links.remove(className);
		} else {
			Log.debug("link not present:" + className);
		}
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

	public void onClick(ClickEvent event) {
		entity = loadEntity();
		for (String link : links.keySet()) {
			if (event.getSource() == links.get(link)) {
				Window.alert(link + "clicked");
			}
		}

	}

	public abstract void linkClicked(String name);

	/**
	 * Load entity.
	 * 
	 * @param id
	 *            the id
	 */
	public abstract T loadEntity();

	protected String getClassSimpleName(String name) {
		return (name.substring(name.lastIndexOf(".") + 1)).toLowerCase();
	}
}
