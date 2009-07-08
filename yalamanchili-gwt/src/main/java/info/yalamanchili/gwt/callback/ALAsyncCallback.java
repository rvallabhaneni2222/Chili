package info.yalamanchili.gwt.callback;

import info.yalamanchili.gwt.widgets.LoadingWidget;
import info.yalamanchili.gwt.widgets.ResponseStatusWidget;

import com.google.gwt.user.client.rpc.AsyncCallback;

// TODO: Auto-generated Javadoc
/**
 * The Class ALAsyncCallback.
 */
public abstract class ALAsyncCallback<T> implements AsyncCallback<T> {
	
	/** The is completed. */
	protected Boolean isCompleted = false;
	
	/** The loading widget. */
	LoadingWidget loadingWidget = new LoadingWidget();

	/**
	 * Instantiates a new aL async callback.
	 */
	public ALAsyncCallback() {
		loadingWidget.show();
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.rpc.AsyncCallback#onFailure(java.lang.Throwable)
	 */
	public void onFailure(Throwable arg0) {
		loadingWidget.hide();
		new ResponseStatusWidget().show("call to server failed");
	}

	/**
	 * On response.
	 * 
	 * @param arg0 the arg0
	 */
	public abstract void onResponse(T arg0);

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.rpc.AsyncCallback#onSuccess(java.lang.Object)
	 */
	public void onSuccess(T arg0) {
		loadingWidget.hide();
		onResponse(arg0);
	}

	/**
	 * Gets the checks if is completed.
	 * 
	 * @return the checks if is completed
	 */
	public Boolean getIsCompleted() {
		return isCompleted;
	}

	/**
	 * Sets the checks if is completed.
	 * 
	 * @param isCompleted the new checks if is completed
	 */
	public void setIsCompleted(Boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

}
