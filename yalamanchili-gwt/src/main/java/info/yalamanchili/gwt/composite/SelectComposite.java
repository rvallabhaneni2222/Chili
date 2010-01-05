package info.yalamanchili.gwt.composite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public abstract class SelectComposite<T> extends ALComposite implements
		ClickHandler, ChangeHandler {
	protected T entity;

	public T getEntity() {
		return entity;
	}

	/** The panel. */
	protected VerticalPanel panel = new VerticalPanel();

	/** The label. */
	protected Label label = new Label();

	/** The list box. */
	protected ListBox listBox;

	public Button addButton = new Button("add");

	public enum SelectCompositeType {
		ONE, ALL
	}

	protected Map<Long, String> values = new HashMap<Long, String>();
	protected Map<Long, Integer> valueIndex = new HashMap<Long, Integer>();
	protected Long entityID;

	protected SelectCompositeType type;

	/**
	 * Instantiates a new select composite.
	 * 
	 * @param text
	 *            the text
	 */
	public SelectComposite(String name, SelectCompositeType type) {
		this.type = type;
		init(panel);
		if (SelectCompositeType.ALL.equals(type)) {
			listBox = new ListBox(true);
			panel.add(addButton);
			addButton.addClickHandler(this);
			listBox.setVisibleItemCount(10);
		}
		if (SelectCompositeType.ONE.equals(type)) {
			listBox = new ListBox();
			listBox.addChangeHandler(this);
		}
		listBox.addItem("          ", "0");
		label.setText(name);
		panel.add(label);
		panel.add(listBox);
		panel.setSpacing(5);
		initListBox();
	}

	public SelectComposite(String name, T entity) {
		this.entity = entity;
		this.type = type;
		init(panel);
		listBox = new ListBox();
		listBox.addChangeHandler(this);
		listBox.addItem("          ", "0");
		label.setText(name);
		panel.add(label);
		panel.add(listBox);
		panel.setSpacing(5);
		initListBox();
	}

	@Override
	public void onChange(ChangeEvent event) {
		if (new Long(listBox.getValue(listBox.getSelectedIndex())) != new Long(
				0))
			getSelectedEntity(new Long(listBox.getValue(listBox
					.getSelectedIndex())));
	}

	protected abstract void initListBox();

	protected void populateListBox(Map<Long, String> values) {
		this.values = values;
		int i = 1;
		for (Long id : values.keySet()) {
			listBox.insertItem(values.get(id), id.toString(), i);
			valueIndex.put(id, i);
			i++;
		}
		if (entity != null)
			setSelectedEntity(entity);
	}

	public abstract void setSelectedEntity(T entity);

	@Override
	public void onClick(ClickEvent event) {
		if (event.getSource().equals(addButton)) {
			onAddAll(getSelectedIds());
		}
	}

	public List<Long> getSelectedIds() {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 1; i < listBox.getItemCount(); i++) {
			if (listBox.isItemSelected(i)) {
				ids.add(new Long(listBox.getValue(i)));
			}
		}
		return ids;
	}

	public abstract void getSelectedEntity(Long id);

	public abstract void onAdd();

	public abstract void onAddAll(List<Long> ids);
}
