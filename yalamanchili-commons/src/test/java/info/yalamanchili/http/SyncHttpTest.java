package info.yalamanchili.http;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class SyncHttpTest {

	@Test
	public void testPost() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("p", "yalamanchili");
		SyncHttp.httpPost("http://search.yahoo.com/search",
				"application/x-www-form-urlencoded", params);
	}

}