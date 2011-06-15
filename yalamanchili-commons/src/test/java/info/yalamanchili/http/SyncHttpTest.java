package info.yalamanchili.http;

import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

public class SyncHttpTest {

	@Test
	@Ignore
	public void testPost() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("registration_id", "");
		params.put("collapse_key", "");
		// params.put("data.<key>", "");
		// params.put("delay_while_idle", "");
		params.put("Authorization: GoogleLogin auth=[AUTH_TOKEN]", "");
		String res = SyncHttp.httpPost(
				"https://android.apis.google.com/c2dm/send",
				"application/x-www-form-urlencoded", params);
		System.out.println(res);
	}

}