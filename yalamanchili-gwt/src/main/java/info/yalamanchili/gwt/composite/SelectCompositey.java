package info.yalamanchili.gwt.composite;

import info.yalamanchili.gwt.beans.MultiSelectObjy;
import info.yalamanchili.gwt.widgets.MultiListBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.web.bindery.requestfactory.shared.EntityProxy;

public abstract class SelectCompositey<T extends EntityProxy> extends
		ALComposite implements ClickHandler, ChangeHandler {
	private Logger logger = Logger.getLogger(SelectComposite.class.getName());

	protected TreePanelCompositey parent;

	protected T proxy;

	public T getProxy() {
		return proxy;
	}

	protected FlowPanel panel = new FlowPanel();

	protected Label label = new Label();

	protected ListBox listBox;

	protected MultiListBox multiListBox;

	protected Map<Long, String> values = new HashMap<Long, String>();
	protected Map<Long, Integer> valueIndex = new HashMap<Long, Integer>();
	protected Long entityID;

	/** user for create entity panels with no value selected */
	public SelectCompositey(String name) {
		init(panel);
		// select one in (many to one relations)
		listBox = new ListBox();
		listBox.addChangeHandler(this);
		listBox.addItem("          ", "0");
		label.setText(name);
		panel.add(label);
		panel.add(listBox);
		initListBox();
	}

	/**
	 * Multiple select box (many to many & one to many non compositions
	 * relations)
	 */
	public SelectCompositey(String title, TreePanelCompositey parent,
			MultiSelectObjy multiSelect) {
		init(panel);
		this.parent = parent;
		multiListBox = new MultiListBox(title, multiSelect.getAvailable(),
				multiSelect.getSelected());
		multiListBox.selectButton.addClickHandler(this);
		multiListBox.unselectButton.addClickHandler(this);
		panel.add(multiListBox);
	}

	/** used for update panel dropdown with a value(entity) selected */
	public SelectCompositey(String name, T proxy) {
		this.proxy = proxy;
		init(panel);
		listBox = new ListBox();
		listBox.addChangeHandler(this);
		listBox.addItem("          ", "0");
		label.setText(name);
		panel.add(label);
		panel.add(listBox);
		initListBox();
	}

	public void onChange(ChangeEvent event) {
		logger.info("on change event source:" + event.getSource());
		if (event.getSource().equals(listBox)) {
			if (new Long(listBox.getValue(listBox.getSelectedIndex())) != new Long(
					0))
				getSelectedProxy(new Long(listBox.getValue(listBox
						.getSelectedIndex())));
		}
	}

	protected abstract void initListBox();

	protected void populateListBox(Map<Long, String> values) {
		this.values = values;
		int i = 1;
		// ADD_ALL
		for (Long id : values.keySet()) {
			listBox.insertItem(values.get(id), id.toString(), i);
			valueIndex.put(id, i);
			i++;
		}
		// ADD
		if (proxy != null)
			setSelectedProxy(proxy);
	}

	public abstract void setSelectedProxy(T proxy);

	public void onClick(ClickEvent event) {
		if (event.getSource().equals(multiListBox.selectButton)) {
			onAddAll(parent, multiListBox.getSelectedIds());
		}
		if (event.getSource().equals(multiListBox.unselectButton)) {
			logger.info("remove all");
			logger.info(multiListBox.getSelectedIds().toString());
			onRemoveAll(parent, multiListBox.getSelectedIds());
		}
	}

	// TODO return proxy
	public List<Long> getSelectedIds() {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 1; i < listBox.getItemCount(); i++) {
			if (listBox.isItemSelected(i)) {
				ids.add(new Long(listBox.getValue(i)));
			}
		}
		return ids;
	}

	public abstract void getSelectedProxy(Long id);

	public abstract void onAdd();

	public abstract void onAddAll(TreePanelCompositey parent, List<Long> ids);

	public abstract void onRemoveAll(TreePanelCompositey parent, List<Long> ids);
}
