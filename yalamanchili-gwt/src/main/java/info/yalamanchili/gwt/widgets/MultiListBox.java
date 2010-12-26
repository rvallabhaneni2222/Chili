package info.yalamanchili.gwt.widgets;

import info.yalamanchili.gwt.composite.ALComposite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;

public class MultiListBox extends ALComposite implements ClickHandler {
	private Logger logger = Logger.getLogger(MultiListBox.class.getName());

	FlowPanel panel = new FlowPanel();
	Label titleLabel = new Label();
	ListBox availableListBox = new ListBox(true);
	ListBox selectedListBox = new ListBox(true);
	public Button selectButton = new Button("  >>  ");
	public Button unselectButton = new Button("  <<  ");
	Map<Long, String> available;
	List<Long> selected;

	public MultiListBox(String title, Map<Long, String> available,
			List<Long> selected) {
		init(panel);
		titleLabel.setText(title);
		this.available = available;
		this.selected = selected;
		this.popuplateWidget();
	}

	private void popuplateWidget() {
		for (Long id : available.keySet()) {
			if (selected.contains(id)) {
				selectedListBox.insertItem(available.get(id), id.toString(),
						id.intValue());
			} else {
				availableListBox.insertItem(available.get(id), id.toString(),
						id.intValue());
			}
		}
	}

	@Override
	public void addWidgets() {
		panel.add(titleLabel);
		panel.add(availableListBox);
		panel.add(selectButton);
		panel.add(unselectButton);
		panel.add(selectedListBox);
	}

	@Override
	public void addListeners() {
		selectButton.addClickHandler(this);
		unselectButton.addClickHandler(this);
	}

	@Override
	public void configure() {
		availableListBox.setVisibleItemCount(10);
		selectedListBox.setVisibleItemCount(10);
		titleLabel.addStyleName("y-gwt-titleLabel");
		panel.addStyleName("y-gwt-multipleSelectWidget");
		availableListBox
				.addStyleName("y-gwt-multipleSelectWidget-availableListBox");
		selectedListBox
				.addStyleName("y-gwt-multipleSelectWidget-selectedListBox");
		selectButton.addStyleName("y-gwt-multipleSelectWidget-selectButton");
		unselectButton
				.addStyleName("y-gwt-multipleSelectWidget-unselectButton");
	}

	public void onClick(ClickEvent event) {
		if (event.getSource().equals(selectButton)) {
			for (Long id : getSelectedIds(availableListBox)) {
				selectedListBox.insertItem(available.get(id), id.toString(),
						id.intValue());
				removeSelectedItems(availableListBox);
			}
		}
		if (event.getSource().equals(unselectButton)) {
			for (Long id : getSelectedIds(selectedListBox)) {
				availableListBox.insertItem(available.get(id), id.toString(),
						id.intValue());
				removeSelectedItems(selectedListBox);
			}
		}
	}

	public List<Long> getSelectedIds() {
		return getSelectedIds(selectedListBox);
	}

	public List<Long> getSelectedIds(ListBox listBox) {
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < listBox.getItemCount(); i++) {
			if (listBox.isItemSelected(i)) {
				ids.add(new Long(listBox.getValue(i)));
			}
		}
		return ids;
	}

	protected void removeSelectedItems(ListBox listBox) {
		for (int i = 0; i < listBox.getItemCount(); i++) {
			if (listBox.isItemSelected(i)) {
				listBox.removeItem(i);
			}
		}
	}

}
