package info.yalamanchili.google;

import info.yalamanchili.http.SyncHttp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class GoogleService {
	public static final String GOOGLE_LOGIN_URI = "https://www.google.com/accounts/ClientLogin";
	public static final String GOOGLE_C2DM_SEND_URI = "http://android.apis.google.com/c2dm/send";

	public static final String PARAM_REGISTRATION_ID = "registration_id";

	public static final String PARAM_DELAY_WHILE_IDLE = "delay_while_idle";

	public static final String PARAM_COLLAPSE_KEY = "collapse_key";

	private static final String UTF8 = "UTF-8";
	private static final String UPDATE_CLIENT_AUTH = "Update-Client-Auth";

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

	public static String getAuthToken(String str) {
		int start = str.indexOf("Auth=");
		return str.substring(start + 5);
	}

	public static String sendC2DMMessage(String message, String authKey,
			String regId, String collapseKey) throws Exception {
		StringBuilder postDataBuilder = new StringBuilder();
		postDataBuilder.append(PARAM_REGISTRATION_ID).append("=").append(regId);
		postDataBuilder.append("&").append(PARAM_COLLAPSE_KEY).append("=")
				.append(collapseKey);

		postDataBuilder.append("&").append("data.payload").append("=")
				.append(URLEncoder.encode(message, UTF8));

		byte[] postData = postDataBuilder.toString().getBytes(UTF8);

		URL url = new URL(GOOGLE_C2DM_SEND_URI);

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded;charset=UTF-8");
		conn.setRequestProperty("Content-Length",
				Integer.toString(postData.length));
		conn.setRequestProperty("Authorization",
				"GoogleLogin auth=" + authKey.trim());

		OutputStream out = conn.getOutputStream();
		out.write(postData);
		out.close();

		int responseCode = conn.getResponseCode();
		if (responseCode == 401 || responseCode == 403) {
			System.out.println("authentication failed");
		}
		String updatedAuthToken = conn.getHeaderField(UPDATE_CLIENT_AUTH);
		if (updatedAuthToken != null && !authKey.equals(updatedAuthToken)) {
			System.out
					.println("Got updated auth token from datamessaging servers: "
							+ updatedAuthToken);
			// save token
		}
		String responseLine = new BufferedReader(new InputStreamReader(
				conn.getInputStream())).readLine();
		System.out.println(responseLine);
		if (!responseLine.contains("id=")) {
			throw new RuntimeException("Error sending message");
		}
		return responseLine.substring(responseLine.indexOf("id=") + 3);
	}

}
