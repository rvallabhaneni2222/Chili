package info.yalamanchili.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

public class SyncHttp {
	private final static Logger logger = Logger.getLogger(SyncHttp.class
			.getName());

	public static HttpResponse response;

	public static String httpGet(String url, Map<String, String> headers) {
		logger.info("http get url:" + url);
		HttpGet request = new HttpGet(url);
		if (headers != null) {
			for (String header : headers.keySet()) {
				logger.info("header name:" + header);
				logger.info("header value :" + headers.get(header));
				request.setHeader(header, headers.get(header));
			}
		}
		try {
			response = HttpHelper.getHttpClient().execute(request);
		} catch (Exception e) {
			logger.warning("htt get call failed" + e.getLocalizedMessage());
			HttpHelper.reset();
			throw new RuntimeException("Http Get called failed for uri:" + url
					+ e);
		}
		if (response != null)
			return HttpHelper.convertResponse(response);
		else {
			logger.info("http call returning null");
			return null;
		}
	}

	public static InputStream httpGetAsStream(String url) {
		try {
			response = HttpHelper.getHttpClient().execute(new HttpGet(url));
		} catch (Exception e) {
			throw new RuntimeException("Http Get called failed for uri:" + url
					+ e);
		}
		if (response == null) {
			return null;
		}
		if (response.getStatusLine().getStatusCode() >= 200
				&& response.getStatusLine().getStatusCode() <= 399) {
			try {
				return response.getEntity().getContent();
			} catch (Exception e) {
				HttpHelper.reset();
				throw new RuntimeException(e);
			}
		} else
			return null;
	}

	public static String httpPut(String uri, String body,
			Map<String, String> headers) {
		HttpPut put = new HttpPut(uri);
		return executeHttpCall(put, body, headers);
	}

	public static String httpPost(String uri, String body,
			Map<String, String> headers) {
		HttpPost post = new HttpPost(uri);
		return executeHttpCall(post, body, headers);
	}

	protected static String executeHttpCall(
			HttpEntityEnclosingRequestBase request, String body,
			Map<String, String> headers) {
		if (headers != null) {
			for (String header : headers.keySet()) {
				logger.info("header name:" + header);
				logger.info("header value :" + headers.get(header));
				request.setHeader(header, headers.get(header));
			}
		}
		try {
			request.setEntity(new StringEntity(body));
			logger.info("http body:" + body);
			response = HttpHelper.getHttpClient().execute(request);
		} catch (Exception e) {
			logger.warning("http call failed:" + e.getLocalizedMessage());
			HttpHelper.reset();
			throw new RuntimeException("Http call failed for uri:"
					+ request.getURI().getRawPath() + e);
		}
		if (response != null) {
			return HttpHelper.convertResponse(response);
		} else {
			logger.info("http call returning null");
			return null;
		}
	}

	// used by google service
	public static String httpUrlEncodedFormEntityPost(String uri,
			String contentType, Map<String, String> params) {
		HttpPost post = new HttpPost(uri);
		post.setHeader("Content-Type", contentType);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		for (String key : params.keySet()) {
			nvps.add(new BasicNameValuePair(key, params.get(key)));
		}
		try {
			UrlEncodedFormEntity e = new UrlEncodedFormEntity(nvps, HTTP.UTF_8);
			logger.info("http post body:"
					+ new BufferedReader(new InputStreamReader(e.getContent()))
							.readLine());
			post.setEntity(e);
			response = HttpHelper.getHttpClient().execute(post);
		} catch (Exception e) {
			throw new RuntimeException("Http Post called failed for uri:" + uri
					+ e);
		}
		if (response != null) {
			return HttpHelper.convertResponse(response);
		} else
			return null;
	}
}
