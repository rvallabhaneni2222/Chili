package info.yalamanchili.gwt.fields;


import info.yalamanchili.gwt.composite.ALComposite;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

public class RichTextAreaField extends ALComposite {
	protected VerticalPanel panel = new VerticalPanel();
	protected Label label = new Label();
	protected RichTextArea textArea = new RichTextArea();
	protected Boolean isValid = false;

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}
	public VerticalPanel getPanel() {
		return panel;
	}

	public Label getLabel() {
		return label;
	}

	public RichTextArea getTextArea() {
		return textArea;
	}

	public RichTextAreaField(String text) {
		init(panel);
		label.setText(text);
	}

	public RichTextAreaField(String text, Boolean readOnly) {
		init(panel);
		label.setText(text);
	}

	@Override
	protected void addListeners() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void addWidgets() {
		panel.add(label);
		panel.add(textArea);
	}

	@Override
	protected void configure() {
		panel.setSpacing(5);
	}

	public String getText() {
		return textArea.getText();
	}

	public void setText(String text) {
		textArea.setText(text);
	}
}
