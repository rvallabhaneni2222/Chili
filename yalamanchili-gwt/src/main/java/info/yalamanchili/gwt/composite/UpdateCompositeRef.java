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
 * The Class UpdateCompositeRef.
 */
public abstract class UpdateCompositeRef<T extends LightEntity> extends
		ReadUpdateCreateCompositeRef<T> implements ClickHandler {

	/** The update. */
	protected Button update = new Button("Update");

	/**
	 * Inits the update composite.
	 * 
	 * @param t
	 *            the t
	 * @param constants
	 *            the constants
	 * @param messages
	 *            the messages
	 */
	public void initUpdateComposite(T t, ConstantsWithLookup constants,
			Messages messages) {
		init(t, false, constants);
		entityCaptionPanel.addStyleName("updateEntityCaptionPanel");
		entityDisplayWidget.addStyleName("updateEntityDisplayWidget");
		basePanel.addStyleName("updateBasePanel");
	}

	/**
	 * Populate fields.
	 */
	public abstract void populateFields();

	/**
	 * Pre update button clicked.
	 */
	protected void preUpdateButtonClicked() {
		GwtServiceAsync.instance().updateEntityFromFields(entity,
				populateEntityFromFields(), new ALAsyncCallback<T>() {

					@Override
					public void onResponse(T data) {
						entity = data;
						updateButtonClicked();

					}

				});
	}

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
			preValidate();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * info.yalamanchili.gwt.composite.ReadUpdateCreateCompositeRef#postInit()
	 */
	@Override
	protected void postInit() {
		update.addClickHandler(this);
		entityDisplayWidget.add(update);
		removeField("ID");
		populateFields();
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
			preUpdateButtonClicked();
	}

}
