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

public abstract class CreateCompositey<T extends EntityProxy> extends
		CRUDComposite<T> implements ClickHandler {
	private Logger logger = Logger.getLogger(CreateCompositey.class.getName());

	public enum CreateCompositeType {
		CREATE, ADD
	}

	CreateCompositeType type;

	public CreateCompositey(CreateCompositeType type) {
		this.type = type;
	}

	// TODO get button names from resource bundle
	public Button create = new Button("create");

	public Button add = new Button("add");

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

	protected void createButtonClicked() {
		request.save(proxy).fire(new Receiver() {

			@Override
			public void onResponse(Void arg0) {
				logger.info("on response----");
				// TODO get message from bundle for specifific entity created
				// TODO use singleton for responsestatus widget
				new ResponseStatusWidget().show("created");
				successfullyCreated();
			}

		});
	}

	protected abstract void successfullyCreated();

	protected abstract void addButtonClicked();

	public void onClick(ClickEvent event) {
		if (event.getSource() == create || event.getSource() == add) {
			proxy = populateEntityFromFields();
		}
		if (CreateCompositeType.CREATE.equals(type)) {
			logger.info("on create");
			createButtonClicked();
		}
		if (CreateCompositeType.ADD.equals(type)) {
			addButtonClicked();
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
}
