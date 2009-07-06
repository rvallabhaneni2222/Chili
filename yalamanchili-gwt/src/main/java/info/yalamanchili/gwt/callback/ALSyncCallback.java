package info.yalamanchili.gwt.callback;

import info.yalamanchili.gwt.widgets.LoadingWidget;
import info.yalamanchili.gwt.widgets.ResponseStatusWidget;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class ALSyncCallback<T> implements AsyncCallback<T> {
	protected LoadingWidget loadingWidget = new LoadingWidget();

	public ALSyncCallback() {
		loadingWidget.show();
	}

	public abstract void onResponse(T response);

	public abstract void postResponse(T response);

	public void onFailure(Throwable arg0) {
		new ResponseStatusWidget().show("call to server failed");
		loadingWidget.hide();
		Log.debug("call failed", arg0);
	}

	public void onSuccess(T arg0) {
		loadingWidget.hide();
		onResponse(arg0);
		postResponse(arg0);
	}

}
