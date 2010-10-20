package info.yalamanchili.gwt.composite;

//TODO move commons stuff to base field
public abstract class GenericFieldComposite extends BaseField {

	public GenericFieldComposite(String labelName) {
		label.setText(labelName);
		configure();
		addWidgets();
		initWidget(panel);
	}

	protected void configure() {
		label.addStyleName("y-gwtFieldHeader");
		message.addStyleName("y-gwt-ErrorMessage");
	}

	protected void addWidgets() {
		panel.add(label);
		fieldPanel.add(message);
		panel.add(fieldPanel);
	}

	public abstract void setup();
}
