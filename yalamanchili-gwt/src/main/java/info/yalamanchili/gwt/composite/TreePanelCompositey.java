package info.yalamanchili.gwt.composite;

import info.yalamanchili.gwt.callback.ALAsyncCallback;
import info.yalamanchili.gwt.rpc.GWTService.GwtServiceAsync;
import info.yalamanchili.gwt.utils.Utils;

import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.web.bindery.requestfactory.shared.EntityProxy;

public abstract class TreePanelCompositey<T extends EntityProxy> extends
		Composite implements SelectionHandler<TreeItem> {
	Logger logger = Logger.getLogger(TreePanelComposite.class.getName());

	protected T proxy;

	protected FlowPanel panel = new FlowPanel();

	protected Tree tree = new Tree();
	protected TreeItem rootItem = new TreeItem("root");

	public T getEntity() {
		return proxy;
	}

	public TreePanelCompositey() {
		initWidget(panel);
	}

	public void initTreePanelComposite(String className) {
		panel.add(tree);
		panel.addStyleName("y-gwt-TreePanel");
		addRooNode(className);
		tree.addSelectionHandler(this);
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

	public void onSelection(SelectionEvent<TreeItem> event) {
		loadProxyAndRequest();
		TreeItem selectedItem = (TreeItem) event.getSelectedItem();
		TreeItem root = tree.getItem(0);
		if (root.equals(selectedItem)) {
			logger.info("root selected");
			showProxy();
		} else {
			logger.info("treemode lsee:" + selectedItem.getTitle());
			treeNodeSelected(selectedItem.getTitle());
		}
	}

	protected void addRooNode(String className) {
		rootItem.setText(Utils.getClassSimpleName(className).toLowerCase());
		rootItem.setTitle(Utils.getClassSimpleName(className));
		rootItem.addStyleName("y-gwt-treePanelComposite-Node");
		tree.addItem(rootItem);
	}

	protected void addFirstChildLink(String name) {
		TreeItem child = new TreeItem(Utils.getClassSimpleName(name)
				.toLowerCase());
		child.addStyleName("y-gwt-treePanelComposite-Node");
		child.setTitle(Utils.getClassSimpleName(name));
		rootItem.addItem(child);
	}

	protected void removeFirstChildLink(String className) {
		TreeItem root = tree.getItem(0);
		for (int i = 0; i < root.getChildCount(); i++) {
			TreeItem child = root.getChild(i);
			if (className.contains(child.getTitle())) {
				child.remove();
			}
		}
	}

	protected abstract void addListeners();

	protected abstract void configure();

	protected abstract void addWidgets();

	// TODO rename to treenode clicked
	public abstract void treeNodeSelected(String entiyName);

	// init the proxy and rf request
	public abstract void loadProxyAndRequest();

	public abstract void showProxy();

}
