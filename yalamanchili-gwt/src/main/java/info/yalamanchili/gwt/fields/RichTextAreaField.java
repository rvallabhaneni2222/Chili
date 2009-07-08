package info.yalamanchili.gwt.fields;


import info.yalamanchili.gwt.composite.ALComposite;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class RichTextAreaField.
 */
public class RichTextAreaField extends ALComposite {
	
	/** The panel. */
	protected VerticalPanel panel = new VerticalPanel();
	
	/** The label. */
	protected Label label = new Label();
	
	/** The text area. */
	protected RichTextArea textArea = new RichTextArea();
	
	/** The is valid. */
	protected Boolean isValid = false;

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
	 * @param isValid the new checks if is valid
	 */
	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}
	
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
	 * Gets the text area.
	 * 
	 * @return the text area
	 */
	public RichTextArea getTextArea() {
		return textArea;
	}

	/**
	 * Instantiates a new rich text area field.
	 * 
	 * @param text the text
	 */
	public RichTextAreaField(String text) {
		init(panel);
		label.setText(text);
	}

	/**
	 * Instantiates a new rich text area field.
	 * 
	 * @param text the text
	 * @param readOnly the read only
	 */
	public RichTextAreaField(String text, Boolean readOnly) {
		init(panel);
		label.setText(text);
	}

	/* (non-Javadoc)
	 * @see info.yalamanchili.gwt.composite.ALComposite#addListeners()
	 */
	@Override
	protected void addListeners() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see info.yalamanchili.gwt.composite.ALComposite#addWidgets()
	 */
	@Override
	protected void addWidgets() {
		panel.add(label);
		panel.add(textArea);
	}

	/* (non-Javadoc)
	 * @see info.yalamanchili.gwt.composite.ALComposite#configure()
	 */
	@Override
	protected void configure() {
		panel.setSpacing(5);
	}

	/**
	 * Gets the text.
	 * 
	 * @return the text
	 */
	public String getText() {
		return textArea.getText();
	}

	/**
	 * Sets the text.
	 * 
	 * @param text the new text
	 */
	public void setText(String text) {
		textArea.setText(text);
	}
}
