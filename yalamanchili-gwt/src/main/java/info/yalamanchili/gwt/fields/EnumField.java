package info.yalamanchili.gwt.fields;

import info.yalamanchili.gwt.composite.ALComposite;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

// TODO: Auto-generated Javadoc
/**
 * The Class EnumField.
 */
public class EnumField extends ALComposite {

	/** The panel. */
	protected FlowPanel panel = new FlowPanel();

	/** The field panel. */
	protected HorizontalPanel fieldPanel = new HorizontalPanel();

	/** The message. */
	protected Label message = new Label();

	/** The label. */
	protected Label label = new Label();

	/** The textbox. */
	protected TextBox textbox = new TextBox();

	/** The list box. */
	protected ListBox listBox = new ListBox();

	/** The read only. */
	protected Boolean readOnly;

	/** The is valid. */
	protected Boolean isValid = false;

	/**
	 * Instantiates a new enum field.
	 * 
	 * @param text
	 *            the text
	 * @param readOnly
	 *            the read only
	 */
	public EnumField(String text, Boolean readOnly) {
		this.readOnly = readOnly;
		init(panel);
		if (readOnly) {
			label.setText(text);
			textbox.setReadOnly(readOnly);
			fieldPanel.add(textbox);
		} else {
			label.setText(text);
			fieldPanel.add(listBox);
		}
	}

	/**
	 * Gets the checks if is valid.
	 * 
	 * @return the checks if is valid
	 */
	public Boolean getIsValid() {
		return isValid;
	}

	/**
	 * Sets the checks if is valid.
	 * 
	 * @param isValid
	 *            the new checks if is valid
	 */
	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	/**
	 * Gets the panel.
	 * 
	 * @return the panel
	 */
	public FlowPanel getPanel() {
		return panel;
	}

	/**
	 * Gets the label.
	 * 
	 * @return the label
	 */
	public Label getLabel() {
		return label;
	}

	/**
	 * Gets the textbox.
	 * 
	 * @return the textbox
	 */
	public TextBox getTextbox() {
		return textbox;
	}

	/**
	 * Gets the read only.
	 * 
	 * @return the read only
	 */
	public Boolean getReadOnly() {
		return readOnly;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see info.yalamanchili.gwt.composite.ALComposite#addListeners()
	 */
	@Override
	protected void addListeners() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see info.yalamanchili.gwt.composite.ALComposite#addWidgets()
	 */
	@Override
	protected void addWidgets() {
		panel.add(label);
		fieldPanel.add(message);
		panel.add(fieldPanel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see info.yalamanchili.gwt.composite.ALComposite#configure()
	 */
	@Override
	protected void configure() {

	}

	/**
	 * Sets the value.
	 * 
	 * @param enums
	 *            the new value
	 */
	public void setValue(Enum<?>[] enums) {
		for (Enum<?> var : enums) {
			listBox.addItem(var.toString(), var.toString());
		}
	}

	/**
	 * Sets the value.
	 * 
	 * @param text
	 *            the new value
	 */
	public void setValue(String text) {
		textbox.setText(text);
	}

	/**
	 * Sets the message.
	 * 
	 * @param text
	 *            the new message
	 */
	public void setMessage(String text) {
		message.setText(text);
	}

	/**
	 * Clear message.
	 */
	public void clearMessage() {
		message.setText("");
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public String getValue() {
		if (readOnly) {
			return textbox.getText();
		} else {
			return listBox.getItemText(listBox.getSelectedIndex());
		}
	}
}
