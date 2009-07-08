package info.yalamanchili.gwt.composite;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class GenericFieldCompositeWithTextBox.
 */
public abstract class GenericFieldCompositeWithTextBox extends Composite {

	/** The panel. */
	protected VerticalPanel panel = new VerticalPanel();
	
	/** The field panel. */
	protected HorizontalPanel fieldPanel = new HorizontalPanel();
	
	/** The label. */
	protected Label label = new Label();
	
	/** The textbox. */
	protected TextBox textbox = new TextBox();
	
	/** The message. */
	protected Label message = new Label();
	
	/** The is valid. */
	protected Boolean isValid = false;

	/**
	 * Gets the panel.
	 * 
	 * @return the panel
	 */
	public VerticalPanel getPanel() {
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
	 * Instantiates a new generic field composite with text box.
	 * 
	 * @param labelName the label name
	 */
	public GenericFieldCompositeWithTextBox(String labelName) {
		label.setText(labelName);
		configure();
		addWidgets();
		initWidget(panel);
	}

	/**
	 * Instantiates a new generic field composite with text box.
	 * 
	 * @param labelName the label name
	 * @param readOnly the read only
	 */
	public GenericFieldCompositeWithTextBox(String labelName, Boolean readOnly) {
		label.setText(labelName);
		if (readOnly)
			setReadOnly(readOnly);
		configure();
		addWidgets();
		initWidget(panel);
	}

	/**
	 * Configure.
	 */
	protected void configure() {
		panel.setSpacing(5);
		message.addStyleName("ErrorMessage");
	}

	/**
	 * Adds the widgets.
	 */
	protected void addWidgets() {
		panel.add(label);
		fieldPanel.add(textbox);
		fieldPanel.add(message);
		panel.add(fieldPanel);
	}

	/**
	 * Sets the read only.
	 * 
	 * @param readOnly the new read only
	 */
	public void setReadOnly(Boolean readOnly) {
		textbox.setReadOnly(true);
	}

	/**
	 * Sets the message.
	 * 
	 * @param text the new message
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
	 * Gets the valid.
	 * 
	 * @return the valid
	 */
	public Boolean getValid() {
		return isValid;
	}

	/**
	 * Sets the valid.
	 * 
	 * @param valid the new valid
	 */
	public void setValid(Boolean valid) {
		this.isValid = valid;
	}

}
