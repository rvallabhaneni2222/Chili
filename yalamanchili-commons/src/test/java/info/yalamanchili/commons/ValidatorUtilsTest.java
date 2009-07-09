package info.yalamanchili.commons;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ValidatorUtilsTest {
	private static final Log log = LogFactory.getLog(ValidatorUtilsTest.class);

	@Before
	public void init() {

	}

	@Test
	public void testLoadProperties() {
		try {
			ValidatorUtils.loadProperties();
		} catch (Exception e) {
			fail();
			log.error(e);
		}
	}

	@Test
	public void testGetNotNullKey() {
		assertNotNull(ValidatorUtils.loadProperties()
				.get("{validator.notNull}"));
	}

	@After
	public void destroy() {

	}
}
