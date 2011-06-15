package info.yalamanchili.google;

import org.junit.Test;

public class GoogleServiceTest {
	@Test
	public void testGoogleLogin() {
		String res = GoogleService.login("dummy1833@gmail.com",
				"dummypassword", "HOSTED_OR_GOOGLE", "ac2dm",
				"com.dante.catalog.android-1.0", null, null);
		System.out.println(res);
	}
}
