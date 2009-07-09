package info.yalamanchili.commons;

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

	@Before
	public void init() {

	}

	@Test
	public void testGetSecureInitialContext() {
		try {
			Context context = JNDIUtils.getSecureInitialContext();

		} catch (NamingException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void testGetInitialContext() {
		try {
			Context context = JNDIUtils.getInitialContext();

		} catch (NamingException e) {
			fail();
			e.printStackTrace();
		}
	}

	@After
	public void destroy() {

	}
}
