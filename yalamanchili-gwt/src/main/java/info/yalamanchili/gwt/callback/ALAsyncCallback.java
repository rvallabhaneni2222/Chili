package info.yalamanchili.gwt.callback;

import info.yalamanchili.gwt.widgets.LoadingWidget;
import info.yalamanchili.gwt.widgets.ResponseStatusWidget;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class ALAsyncCallback<T> implements AsyncCallback<T> {
	protected Boolean isCompleted = false;
	LoadingWidget loadingWidget = new LoadingWidget();

	public ALAsyncCallback() {
		loadingWidget.show();
	}

	public void onFailure(Throwable arg0) {
		loadingWidget.hide();
		new ResponseStatusWidget().show("call to server failed");
	}

	public abstract void onResponse(T arg0);

	public void onSuccess(T arg0) {
		loadingWidget.hide();
		onResponse(arg0);
	}

	public Boolean getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(Boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

}
