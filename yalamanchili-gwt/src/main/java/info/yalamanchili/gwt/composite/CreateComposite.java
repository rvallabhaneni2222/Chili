package info.yalamanchili.gwt.composite;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;

// TODO: Auto-generated Javadoc
/**
 * The Class CreateComposite.
 */
public abstract class CreateComposite<T> extends ReadUpdateCreateComposite
		implements ClickListener {


	/** The create. */
	public Button create = new Button("CREATE");

	/**
	 * Inits the create composite.
	 */
	public void initCreateComposite() {
		init();
		panel.add(create);
		create.addClickListener(this);
	}

	/**
	 * Creates the button clicked.
	 */
	protected abstract void createButtonClicked();

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user.client.ui.Widget)
	 */
	public void onClick(Widget widget) {
		if (widget == create)
			createButtonClicked();
	}
}
