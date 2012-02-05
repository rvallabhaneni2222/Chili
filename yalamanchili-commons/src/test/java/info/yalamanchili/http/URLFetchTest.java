package info.yalamanchili.http;

import static org.junit.Assert.*;

import org.junit.Test;

public class URLFetchTest {
	@Test
	public void testURLFetch() {
		assertTrue(URLFetch.getResponseString("http://www.google.com").length() > 10);
	}
}
