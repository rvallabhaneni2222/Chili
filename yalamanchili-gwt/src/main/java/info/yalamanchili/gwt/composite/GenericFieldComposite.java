package info.yalamanchili.gwt.composite;

import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class GenericFieldComposite.
 */
public abstract class GenericFieldComposite extends Composite implements
		KeyPressHandler, KeyUpHandler, KeyDownHandler {

	/** The panel. */
	protected VerticalPanel panel = new VerticalPanel();

	/** The field panel. */
	protected HorizontalPanel fieldPanel = new HorizontalPanel();

	/** The label. */
	protected Label label = new Label();

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
	 * Instantiates a new generic field composite.
	 * 
	 * @param labelName
	 *            the label name
	 */
	public GenericFieldComposite(String labelName) {
		label.setText(labelName);
		configure();
		addWidgets();
		initWidget(panel);
	}

	/**
	 * Configure.
	 */
	protected void configure() {
		panel.setSpacing(5);
		label.addStyleName("FieldHeader");
		message.addStyleName("ErrorMessage");
	}

	/**
	 * Adds the widgets.
	 */
	protected void addWidgets() {
		panel.add(label);
		fieldPanel.add(message);
		panel.add(fieldPanel);
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
	 * @param valid
	 *            the new valid
	 */
	public void setValid(Boolean valid) {
		this.isValid = valid;
	}

	public abstract void setup();
}
