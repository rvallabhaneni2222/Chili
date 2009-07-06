package info.yalamanchili.gwt.widgets;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class JSONTestWidget extends Composite {
	VerticalPanel panel = new VerticalPanel();

	public JSONTestWidget() {
		initWidget(panel);
		final Label label = new Label();
		panel.add(label);
		String url = "http://localhost:8080/yalamanchili-servlet-1.0/gwt_jason/jason";
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL
				.encode(url));

		try {
			Request request = builder.sendRequest(null, new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					// Couldn't connect to server (could be timeout, SOP
					// violation, etc.)
				}

				public void onResponseReceived(Request request,
						Response response) {
					if (200 == response.getStatusCode()) {
						label.setText(response.getText());
					} else {
						label.setText(response.getText());
					}
				}
			});
		} catch (RequestException e) {
			label.setText(e.getLocalizedMessage());
		}
	}
}
