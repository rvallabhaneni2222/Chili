package info.yalamanchili.commons;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.naming.Context;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JNDIUtilsTest {
	private static final Log log = LogFactory.getLog(JNDIUtilsTest.class);
	private JNDIUtils utils;

	@Before
	public void initJNDIUtils() {
		utils = new JNDIUtils();
	}

	@Test
	public void testGetSecureInitialContext() {
		try {
			Context context = JNDIUtils.getSecureInitialContext();
			assertNotNull(context.getEnvironment().get(
					Context.INITIAL_CONTEXT_FACTORY));
			assertNotNull(context.getEnvironment()
					.get(Context.URL_PKG_PREFIXES));
			assertNotNull(context.getEnvironment().get(Context.PROVIDER_URL));
			assertNotNull(context.getEnvironment().get(
					Context.SECURITY_PRINCIPAL));
			assertNotNull(context.getEnvironment().get(
					Context.SECURITY_CREDENTIALS));
		} catch (NamingException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void testGetInitialContext() {
		try {
			Context context = JNDIUtils.getInitialContext();
			assertNotNull(context.getEnvironment()
					.get(Context.URL_PKG_PREFIXES));
			assertNotNull(context.getEnvironment().get(Context.PROVIDER_URL));
		} catch (NamingException e) {
//			fail();
			e.printStackTrace();
		}
	}

	@After
	public void destroyJNDIUtils() {

	}
}
