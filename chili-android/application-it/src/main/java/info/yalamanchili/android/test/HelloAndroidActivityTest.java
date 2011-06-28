package info.yalamanchili.android.test;

import info.yalamanchili.android.HelloAndroidActivity;
import info.yalamanchili.android.http.AsyncHttpGet;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

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
		final CountDownLatch signal = new CountDownLatch(4);
		new AsyncHttpGet(activity) {
			@Override
			protected void onResponse(String result) {
				
				Log.d("resutl---------", result);
				signal.countDown();
			}
		}.execute("http://search.yahooapis.com/WebSearchService/V1/webSearch?appid=YahooDemo&query=persimmon&results=10");
		signal.await(5, TimeUnit.SECONDS);
	}
}
