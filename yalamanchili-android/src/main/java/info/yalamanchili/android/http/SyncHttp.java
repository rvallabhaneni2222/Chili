package info.yalamanchili.android.http;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;

public class SyncHttp {
	public static HttpResponse response;

	public static String httpGet(String url) {
		try {
			response = HttpHelper.getHttpClient().execute(new HttpGet(url));
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (response != null)
			return HttpHelper.request(response);
		else
			return null;
	}
}
