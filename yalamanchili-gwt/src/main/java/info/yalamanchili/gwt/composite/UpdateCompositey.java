package info.yalamanchili.gwt.composite;

import info.yalamanchili.gwt.widgets.LoadingWidget;
import info.yalamanchili.gwt.widgets.ResponseStatusWidget;

import java.util.Set;
import java.util.logging.Logger;

import javax.validation.ConstraintViolation;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.user.client.ui.Button;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public abstract class UpdateCompositey<T extends EntityProxy> extends
		CRUDComposite<T> implements ClickHandler {
	Logger logger = Logger.getLogger(UpdateComposite.class.getName());
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

	protected abstract T populateEntityOnUpdate();

	protected void updateButtonClicked() {
		request.save(proxy).fire(new Receiver() {

			@Override
			public void onResponse(Void arg0) {
				logger.info("on response----");
				// TODO get message from bundle for specifific entity created
				// TODO use singleton for responsestatus widget
				new ResponseStatusWidget().show("updated");
				successfullyUpdated();
			}

		});
	}

	protected abstract void successfullyUpdated();

	public void onClick(ClickEvent event) {
		if (event.getSource() == update) {
			proxy = populateEntityOnUpdate();
			updateButtonClicked();
		}

	}

	// TODO move to CREATEUPDATEComposite
	public abstract class Receiver extends
			com.google.web.bindery.requestfactory.shared.Receiver<Void> {
		// TODO add loading indicator
		LoadingWidget loadingWidget = new LoadingWidget();

		public Receiver() {
			loadingWidget.show();
		}

		public abstract void onResponse(Void res);

		public void onSuccess(Void arg0) {
			loadingWidget.hide();
			onResponse(arg0);
		}

		/* override these methods in create entity panels as needed */
		@Override
		public void onFailure(ServerFailure error) {
			logger.info(error.getMessage());
			loadingWidget.hide();
		}

		@Override
		public void onConstraintViolation(Set<ConstraintViolation<?>> violations) {
			loadingWidget.hide();
			handleValidations(violations);
		}
	}

	public abstract T populateEntityOnRender();

}
