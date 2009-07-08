package info.yalamanchili.gwt.fields;


import info.yalamanchili.gwt.utils.Alignment;

import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class ListBoxField.
 */
public class ListBoxField extends Composite {
	
	/** The panel. */
	Panel panel = new VerticalPanel();

	/** The label. */
	Label label = new Label();
	
	/** The listbox. */
	ListBox listbox = new ListBox();

	/**
	 * Instantiates a new list box field.
	 * 
	 * @param labelName the label name
	 */
	public ListBoxField(String labelName) {
		initWidget(panel);
		label.setText(labelName);
		configure();
		addWidgets();
	}

	/**
	 * Instantiates a new list box field.
	 * 
	 * @param labelName the label name
	 * @param alignment the alignment
	 */
	public ListBoxField(String labelName, Alignment alignment) {
		panel = new HorizontalPanel();
		initWidget(panel);
		label.setText(labelName);
		configure();
		addWidgets();
	}

	/**
	 * Configure.
	 */
	protected void configure() {
	}

	/**
	 * Adds the widgets.
	 */
	protected void addWidgets() {
		panel.add(label);
		panel.add(listbox);
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public Long getValue() {
		return new Long(listbox.getValue(listbox.getSelectedIndex()));
	}

	/**
	 * Adds the value.
	 * 
	 * @param value the value
	 * @param item the item
	 */
	public void addValue(Long value, String item) {
		listbox.addItem(item, value.toString());
	}

	/**
	 * Adds the change listener.
	 * 
	 * @param changeListener the change listener
	 */
	public void addChangeListener(ChangeListener changeListener) {
		listbox.addChangeListener(changeListener);
	}

	/**
	 * Gets the list box.
	 * 
	 * @return the list box
	 */
	public ListBox getListBox() {
		return listbox;
	}
}
