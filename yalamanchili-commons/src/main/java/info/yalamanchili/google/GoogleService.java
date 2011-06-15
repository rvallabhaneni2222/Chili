package info.yalamanchili.google;

import info.yalamanchili.http.HttpHelper;
import info.yalamanchili.http.SyncHttp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

public class GoogleService {
	public static final String GOOGLE_LOGIN_URI = "https://www.google.com/accounts/ClientLogin";
	public static final String GOOGLE_C2DM_SEND_URI = "http://android.apis.google.com/c2dm/send";

	private static final String AUTH_KEY = "DQAAALAAAACzAaxF1y0oguyfPzGiO4iVmbXannLlvhN2Dx-sN4QB6Flv8v6HSd5h4nC3ksTu1EShFbw2TMlNO6R1wqqRd7G3kI3xyCiyz5SpSVo1aEtckHHXPClFcksV9OU4mOJ8Z3LGDl6GAtt3gDAaQA7V1Q5GaEf9g0l_oIU2s-lHawCPjLJYv0FXGw8bc4YvDoV7MJJPoygXYxMGl5YD7qFN0ritbuvNExyRZzsDANxAnz72kw";

	public static final String PARAM_REGISTRATION_ID = "registration_id";

	public static final String PARAM_DELAY_WHILE_IDLE = "delay_while_idle";

	public static final String PARAM_COLLAPSE_KEY = "collapse_key";

	private static final String UTF8 = "UTF-8";

	// Registration is currently hardcoded
	private final static String YOUR_REGISTRATION_STRING = "APA91bFZZXc5Iakv8hk2pH41hhVdzrpuVlJqaDXbKwqozC0u7ZkhfR5vrakMKpKmKgAA4kqxxWIrtKfH5IiR2ChFuqUl0kcNjps5LGMImjRheMSmu93LffY";

	public static String login(String email, String password,
			String accountType, String service, String source,
			String logintoken, String logincaptcha) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("accountType", accountType);
		params.put("Email", email);
		params.put("Passwd", password);
		params.put("service", service);
		params.put("source", source);
		if (logintoken != null)
			params.put("logintoken", logintoken);
		if (logincaptcha != null)
			params.put("logincaptcha", logincaptcha);
		String response = SyncHttp.httpPost(GOOGLE_LOGIN_URI,
				"application/x-www-form-urlencoded", params);
		System.out.println("login response:" + response);
		return getAuthToken(response);
	}

	public static String sendC2DMMessage(String regId, String collapseKey,
			String message, String delay, String authKey) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("registration_id", regId);
		params.put("data.key1", message);
		params.put("collapse_key", collapseKey);
		params.put("delay_while_idle", delay);

		Map<String, String> headerParams = new HashMap<String, String>();
		headerParams.put("Authorization: GoogleLogin auth", authKey);

		return httpPost(GOOGLE_C2DM_SEND_URI,
				"application/x-www-form-urlencoded", params, headerParams);
	}

	public static String getAuthToken(String str) {
		int start = str.indexOf("Auth=");
		return str.substring(start + 5);
	}

	public static void sendTest() throws Exception {
		StringBuilder postDataBuilder = new StringBuilder();
		postDataBuilder.append(PARAM_REGISTRATION_ID).append("=")
				.append(YOUR_REGISTRATION_STRING);
		postDataBuilder.append("&").append(PARAM_COLLAPSE_KEY).append("=")
				.append("0");

		postDataBuilder.append("&").append("data.payload").append("=")
				.append(URLEncoder.encode("Lars war hier", UTF8));

		byte[] postData = postDataBuilder.toString().getBytes(UTF8);

		// Hit the dm URL.

		URL url = new URL(GOOGLE_C2DM_SEND_URI);

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded;charset=UTF-8");
		conn.setRequestProperty("Content-Length",
				Integer.toString(postData.length));
		conn.setRequestProperty("Authorization", "GoogleLogin auth=" + AUTH_KEY);

		OutputStream out = conn.getOutputStream();
		out.write(postData);
		out.close();

		System.out.println(conn.getResponseCode());

	}

	public static String httpPost(String uri, String contentType,
			Map<String, String> params, Map<String, String> headerParams) {
		HttpResponse response;
		HttpPost post = new HttpPost(uri);
		post.addHeader("Content-Type", contentType);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		for (String key : params.keySet()) {
			nvps.add(new BasicNameValuePair(key, params.get(key)));
		}
		for (String key : headerParams.keySet()) {
			System.out.println(key);
			System.out.println(headerParams.get(key).trim());
			Header header = new BasicHeader(key.trim(), headerParams.get(key)
					.trim());
			post.addHeader(header);
		}
		try {
			UrlEncodedFormEntity e = new UrlEncodedFormEntity(nvps, HTTP.UTF_8);
			System.out.println("http post body:"
					+ new BufferedReader(new InputStreamReader(e.getContent()))
							.readLine());
			post.setEntity(e);
			for (Header h : post.getAllHeaders()) {
				System.out.println(h.getName());
				System.out.println(h.getValue());
			}

			response = HttpHelper.getHttpClient().execute(post);
		} catch (Exception e) {
			throw new RuntimeException("Http Post called failed for uri:" + uri
					+ e);
		}
		if (response != null) {
			return HttpHelper.request(response);
		} else
			return null;
	}
}
