package info.yalamanchili.android.test;

import info.yalamanchili.android.http.AsyncHttpGet;
import junit.framework.Assert;
import android.test.AndroidTestCase;

public class AsyncHttpGetTest extends AndroidTestCase {
	public void testSomething() throws Throwable {
		new AsyncHttpGet(null) {
			@Override
			protected void onResponse(String result) {
				System.out.println(result);
			}
		}.execute("http://www.google.com/search?hl=en&q=asdf");
	}
}
