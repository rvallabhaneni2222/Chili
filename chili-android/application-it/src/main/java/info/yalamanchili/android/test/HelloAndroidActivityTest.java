package info.yalamanchili.android.test;

import info.yalamanchili.android.HelloAndroidActivity;
import info.yalamanchili.android.http.AsyncHttpGet;

import java.util.concurrent.CountDownLatch;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

public class HelloAndroidActivityTest extends
		ActivityInstrumentationTestCase2<HelloAndroidActivity> {

	public HelloAndroidActivityTest() {
		super(HelloAndroidActivity.class);
	}

	public void testActivity() throws InterruptedException {
		Log.d("--------------------------", "---------------------");
		HelloAndroidActivity activity = getActivity();
		final CountDownLatch signal = new CountDownLatch(1);
		new AsyncHttpGet(activity) {
			@Override
			protected void onResponse(String result) {
				signal.countDown();
				Log.d("--------------------------", result);
			}
		}.execute("http://search.yahooapis.com/WebSearchService/V1/webSearch?appid=YahooDemo&query=persimmon&results=10");
		signal.await();
	}
}
