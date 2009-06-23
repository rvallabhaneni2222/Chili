package info.yalamanchili.gwt.fields;


import info.yalamanchili.gwt.utils.Alignment;

import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ListBoxField extends Composite {
	Panel panel = new VerticalPanel();

	Label label = new Label();
	ListBox listbox = new ListBox();

	public ListBoxField(String labelName) {
		initWidget(panel);
		label.setText(labelName);
		configure();
		addWidgets();
	}

	public ListBoxField(String labelName, Alignment alignment) {
		panel = new HorizontalPanel();
		initWidget(panel);
		label.setText(labelName);
		configure();
		addWidgets();
	}

	protected void configure() {
	}

	protected void addWidgets() {
		panel.add(label);
		panel.add(listbox);
	}

	public Long getValue() {
		return new Long(listbox.getValue(listbox.getSelectedIndex()));
	}

	public void addValue(Long value, String item) {
		listbox.addItem(item, value.toString());
	}

	public void addChangeListener(ChangeListener changeListener) {
		listbox.addChangeListener(changeListener);
	}

	public ListBox getListBox() {
		return listbox;
	}
}
