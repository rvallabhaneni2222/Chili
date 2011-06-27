package info.yalamanchili.android;

import info.yalamanchili.android.http.AsyncHttpGet;

import org.junit.Test;

public class AsyncHttpGetTest {
	@Test
	public void test() {
		new AsyncHttpGet(null) {
			@Override
			protected void onResponse(String result) {
				System.out.println(result);
			}
		}.execute("http://www.google.com/search?hl=en&q=asdf");
	}
}
