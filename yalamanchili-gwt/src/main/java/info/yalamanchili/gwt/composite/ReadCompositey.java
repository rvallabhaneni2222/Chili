package info.yalamanchili.gwt.composite;

import info.yalamanchili.gwt.rf.GenericRequest;
import info.yalamanchili.gwt.widgets.LoadingWidget;

import java.util.logging.Logger;

import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public abstract class ReadCompositey<T extends EntityProxy> extends
		CRUDComposite<T> {
	private Logger logger = Logger.getLogger(ReadCompositey.class.getName());

	protected void initReadComposite(T proxy, String className,
			final ConstantsWithLookup constants) {
		init(className, true, constants);
		entityCaptionPanel.addStyleName("y-gwt-ReadEntityCaptionPanel");
		entityDisplayWidget.addStyleName("y-gwt-ReadEntityDisplayWidget");
		basePanel.addStyleName("y-gwt-ReadBasePanel");
		populateFieldsFromProxy(proxy);
	}

	protected void initReadComposite(Long id, String className,
			final ConstantsWithLookup constants) {
		init(className, true, constants);
		entityCaptionPanel.addStyleName("y-gwt-ReadEntityCaptionPanel");
		entityDisplayWidget.addStyleName("y-gwt-ReadEntityDisplayWidget");
		basePanel.addStyleName("y-gwt-ReadBasePanel");
		request = getRequest();
		readData(id);
	}

	public abstract GenericRequest<T> getRequest();

	protected abstract void populateFieldsFromProxy(T proxy);

	protected void readData(Long id) {
		request.findById(id).fire(new Receiver() {

			@Override
			public void onResponse(T result) {
				proxy = result;
				populateFieldsFromProxy(result);
			}

		});
	}

	public abstract class Receiver extends
			com.google.web.bindery.requestfactory.shared.Receiver<T> {
		// TODO add loading indicator
		LoadingWidget loadingWidget = new LoadingWidget();

		public Receiver() {
			loadingWidget.show();
		}

		public abstract void onResponse(T proxy);

		public void onSuccess(T proxy) {
			loadingWidget.hide();
			onResponse(proxy);
		}

		/* override these methods in create entity panels as needed */
		@Override
		public void onFailure(ServerFailure error) {
			logger.info(error.getMessage());
			// TODO call to server failed
			loadingWidget.hide();
		}
	}
}
