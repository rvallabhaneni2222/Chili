package info.yalamanchili.gwt.composite;

import net.sf.gilead.pojo.gwt.LightEntity;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.user.client.ui.Button;

public abstract class UpdateComposite<T extends LightEntity> extends
		ReadUpdateCreateComposite<T> implements ClickHandler {

	protected Button update = new Button("update");

	public void initUpdateComposite(String className,
			final ConstantsWithLookup constants) {
		init(className, false, constants);
		entityCaptionPanel.addStyleName("y-gwt-UpdateEntityCaptionPanel");
		entityDisplayWidget.addStyleName("y-gwt-UpdateEntityDisplayWidget");
		basePanel.addStyleName("y-gwt-UpdateBasePanel");
		entityDisplayWidget.add(update);
		update.addClickHandler(this);
		populateEntityOnRender();
	}

	public abstract T populateEntityOnRender();

	public abstract void updateButtonClicked();

	public abstract T populateEntityOnUpdate();

	public void onClick(ClickEvent event) {
		entity = populateEntityOnUpdate();
		if (event.getSource() == update) {
			preValidate();
		}
	}

	protected void preUpdateButtonClicked() {
		updateButtonClicked();
	}

	protected void postValidate() {
		if (postValidateImpl())
			preUpdateButtonClicked();
	}
}
