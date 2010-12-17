package info.yalamanchili.commons;

import info.yalamanchili.commons.entity.Course;

import java.util.List;
import java.util.Map;

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
	public void testNotNull() {
		Course c = new Course();
		c.setWeight(new Float(10.00));
		Map<String, List<String>> values = ValidatorUtils.validateEntity(c);
		for (String p : values.keySet()) {
			System.out.println(p);
			System.out.println(values.get(p));
		}
	}

	@After
	public void destroy() {

	}
}
