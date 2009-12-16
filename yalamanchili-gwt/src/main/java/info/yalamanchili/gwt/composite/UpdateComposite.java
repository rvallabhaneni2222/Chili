package info.yalamanchili.gwt.composite;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.user.client.ui.Button;

// TODO: Auto-generated Javadoc
/**
 * The Class UpdateComposite.
 */
public abstract class UpdateComposite<T> extends ReadUpdateCreateComposite<T>
		implements ClickHandler {

	protected Button update = new Button("update");

	/**
	 * Inits the update composite.
	 */
	public void initUpdateComposite(String className,
			final ConstantsWithLookup constants) {
		init(className, false, constants);
		entityCaptionPanel.addStyleName("y-gwt-UpdateEntityCaptionPanel");
		entityDisplayWidget.addStyleName("y-gwt-UpdateEntityDisplayWidget");
		basePanel.addStyleName("y-gwt-UpdateBasePanel");
		entityDisplayWidget.add(update);
		update.addClickHandler(this);
		populateFields();
	}

	/**
	 * Populate fields.
	 */
	public abstract void populateFields();

	/**
	 * Update button clicked.
	 */
	public abstract void updateButtonClicked();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user
	 * .client.ui.Widget)
	 */
	public void onClick(ClickEvent event) {
		if (event.getSource() == update) {
			updateButtonClicked();
		}
	}
}
