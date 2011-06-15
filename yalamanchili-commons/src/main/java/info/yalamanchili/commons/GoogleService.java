package info.yalamanchili.commons;

import info.yalamanchili.http.SyncHttp;

import java.util.HashMap;
import java.util.Map;

public class GoogleService {
	public static final String GOOGLE_LOGIN_URI = "https://www.google.com/accounts/ClientLogin";

	public static String login(String email, String password,
			String accountType, String service, String source,
			String logintoken, String logincaptcha) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("accountType", accountType);
		params.put("Email", email);
		params.put("Passwd	", password);
		params.put("service", service);
		params.put("source", source);
		if (logintoken != null)
			params.put("logintoken", logintoken);
		if (logincaptcha != null)
			params.put("logincaptcha", logincaptcha);
		return SyncHttp.httpPost(GOOGLE_LOGIN_URI,
				"application/x-www-form-urlencoded", params);
	}
}
