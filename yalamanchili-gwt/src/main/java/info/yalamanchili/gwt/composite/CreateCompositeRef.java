package info.yalamanchili.gwt.composite;

import info.yalamanchili.gwt.callback.ALAsyncCallback;
import info.yalamanchili.gwt.rpc.GWTService.GwtServiceAsync;
import net.sf.gilead.pojo.java5.LightEntity;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.i18n.client.Messages;
import com.google.gwt.user.client.ui.Button;

// TODO: Auto-generated Javadoc
/**
 * The Class CreateCompositeRef.
 */
public abstract class CreateCompositeRef<T extends LightEntity> extends
		ReadUpdateCreateCompositeRef<T> implements ClickHandler {

	/** The create. */
	public Button create = new Button("CREATE");

	/**
	 * Inits the create composite.
	 * 
	 * @param t
	 *            the t
	 * @param constants
	 *            the constants
	 * @param messages
	 *            the messages
	 */
	public void initCreateComposite(T t, ConstantsWithLookup constants,
			Messages messages) {
		init(t, false, constants);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * info.yalamanchili.gwt.composite.ReadUpdateCreateCompositeRef#postInit()
	 */
	protected void postInit() {
		entityDataWidget.setSpacing(5);
		entityDataWidget.add(create);
		removeField("ID");
		create.addClickHandler(this);
	}

	/**
	 * Pre create button clicked.
	 */
	protected void preCreateButtonClicked() {
		GwtServiceAsync.instance().createEntityFromFields(classCanonicalName,
				populateEntityFromFields(), new ALAsyncCallback<T>() {

					@Override
					public void onResponse(T data) {
						entity = data;
						createButtonClicked();

					}

				});
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
		if (event.getSource() == create) {
			preValidate();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * info.yalamanchili.gwt.composite.ReadUpdateCreateCompositeRef#postValidate
	 * ()
	 */
	protected void postValidate() {
		if (postValidateImpl())
			preCreateButtonClicked();
	}

	/**
	 * Creates the entity from fields.
	 * 
	 * @return the t
	 */
	protected T createEntityFromFields() {
		return null;
	}
}
