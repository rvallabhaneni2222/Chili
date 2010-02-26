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

	public enum CreateCompositeType {
		CREATE, ADD
	}

	CreateCompositeType type;

	/** The create. */

	public CreateComposite(CreateCompositeType type) {
		this.type = type;
	}

	public Button create = new Button("create");

	public Button add = new Button("add");

	/**
	 * Inits the create composite.
	 */
	public void initCreateComposite(String className,
			final ConstantsWithLookup constants) {
		init(className, false, constants);
		if (CreateCompositeType.CREATE.equals(type)) {
			entityDisplayWidget.add(create);
			create.addClickHandler(this);
		}
		if (CreateCompositeType.ADD.equals(type)) {
			entityDisplayWidget.add(add);
			add.addClickHandler(this);
		}

		entityCaptionPanel.addStyleName("y-gwt-CreateEntityCaptionPanel");
		entityDisplayWidget.addStyleName("y-gwt-CreateEntityDisplayWidget");
		basePanel.addStyleName("y-gwt-CreateBasePanel");
	}

	protected abstract T populateEntityFromFields();

	/**
	 * Creates the button clicked.
	 */
	protected abstract void createButtonClicked();

	protected abstract void addButtonClicked();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user
	 * .client.ui.Widget)
	 */
	public void onClick(ClickEvent event) {
		if (event.getSource() == create) {
			preValidate();
		}
		if (event.getSource() == add) {
			preValidate();
		}
	}

	protected void preCreateButtonClicked() {
		entity = populateEntityFromFields();
		if (CreateCompositeType.CREATE.equals(type)) {
			createButtonClicked();
		}
		if (CreateCompositeType.ADD.equals(type)) {
			addButtonClicked();
		}
	}

	protected void postValidate() {
		if (postValidateImpl())
			preCreateButtonClicked();
	}
}
