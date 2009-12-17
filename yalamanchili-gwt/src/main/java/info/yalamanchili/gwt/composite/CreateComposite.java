package info.yalamanchili.gwt.composite;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.user.client.ui.Button;

// TODO: Auto-generated Javadoc
/**
 * The Class CreateComposite.
 */
public abstract class CreateComposite<T> extends ReadUpdateCreateComposite<T>
		implements ClickHandler {

	/** The create. */
	public Button create = new Button("create");

	/**
	 * Inits the create composite.
	 */
	public void initCreateComposite(String className,
			final ConstantsWithLookup constants) {
		init(className, false, constants);
		entityDisplayWidget.add(create);
		create.addClickHandler(this);
		entityCaptionPanel.addStyleName("y-gwt-CreateEntityCaptionPanel");
		entityDisplayWidget.addStyleName("y-gwt-CreateEntityDisplayWidget");
		basePanel.addStyleName("y-gwt-CreateBasePanel");
	}

	/**
	 * Creates the button clicked.
	 */
	protected abstract void createButtonClicked();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user
	 * .client.ui.Widget)
	 */
	public void onClick(ClickEvent event) {
		if (event.getSource() == create)
			createButtonClicked();
	}
	
}
