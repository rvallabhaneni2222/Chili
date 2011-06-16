package info.yalamanchili.google;

import org.junit.Test;

public class GoogleServiceTest {

	private static final String DEVICE_REG_ID = "APA91bE2msaGs4PKafzm123wD3jhP0oVyeodpsYnqmDSVJPgsKIoA5kE8ZzS3KKClTCcKZjqAW79aA-nPAsi7eZs3cuDF5Er1pr8B0U5eWsMiU6TJgfGsvw";
	private static final String SENDER_EMAIL = "dummy1833@gmail.com";
	private static final String PASSWORD = "dummypassword";
	private static final String SERVICE = "ac2dm";
	private static final String ACCOUNT_TYPE = "HOSTED_OR_GOOGLE";
	private static final String SOURCE_ID = "com.dante.catalog.android-1.0";

	// @Test
	public void testGoogleLogin() {
		String res = GoogleService.login(SENDER_EMAIL, PASSWORD, ACCOUNT_TYPE,
				SERVICE, SOURCE_ID, null, null);
		System.out.println(res);
	}

	@Test
	public void testC2DMSend() {
		try {
			String authKey = GoogleService.login(SENDER_EMAIL, PASSWORD,
					ACCOUNT_TYPE, SERVICE, SOURCE_ID, null, null);
			GoogleService.sendC2DMMessage("test message", authKey.trim(),
					DEVICE_REG_ID, "q3");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String auth = GoogleService.login(SENDER_EMAIL, PASSWORD,
		// ACCOUNT_TYPE,
		// SERVICE, SOURCE_ID, null, null);
		// String res = GoogleService.sendC2DMMessage(DEVICE_REG_ID, "q22",
		// "testmsg", "1", auth);
		// System.out.println(res);
	}
}
