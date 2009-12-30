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

	Map<Long, String> values = new HashMap<Long, String>();

	SelectCompositeType type;

	/**
	 * Instantiates a new select composite.
	 * 
	 * @param text
	 *            the text
	 */
	public SelectComposite(SelectCompositeType type) {
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
		label.setText("select all");
		panel.add(label);
		panel.add(listBox);
		panel.setSpacing(5);
		initListBox();
	}

	@Override
	public void onChange(ChangeEvent arg0) {
		itemSelected();
	}

	protected abstract void initListBox();

	protected void populateListBox(Map<Long, String> values) {
		this.values = values;
		for (Long id : values.keySet()) {
			listBox.addItem(values.get(id), id.toString());
		}
	}

	@Override
	public void onClick(ClickEvent event) {
		if (event.getSource().equals(addButton)) {
			onAddAll(getSelectedIds());
		}
	}

	public List<Long> getSelectedIds() {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < listBox.getItemCount(); i++) {
			if (listBox.isItemSelected(i)) {
				ids.add(new Long(listBox.getValue(i)));
			}
		}
		return ids;
	}

	public abstract void itemSelected();

	public abstract void onAdd();

	public abstract void onAddAll(List<Long> ids);
}
