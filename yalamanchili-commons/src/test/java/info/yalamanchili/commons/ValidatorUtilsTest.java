package info.yalamanchili.commons;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import info.yalamanchili.commons.entity.Course;

import java.math.BigDecimal;
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

	/** tests notnull for course.name */
	@Test
	public void testNotNull() {
		Course c = new Course();
		Map<String, List<String>> values = ValidatorUtils.validateEntity(c);
		assertTrue(values.keySet().contains("name"));
		assertEquals(values.get("name").get(0).toString(), "may not be null");
	}

	/** tests DecimalMin,Max for course.weight is between 0.0 and 5.0 */
	@Test
	public void testDecialMaxMin() {
		Course c = new Course();
		c.setWeight(new Float("22.9"));
		Map<String, List<String>> values = ValidatorUtils.validateEntity(c);
		assertTrue(values.keySet().contains("weight"));
		assertEquals(values.get("weight").get(0).toString(),
				"must be less than or equal to 5.0");

		c.setWeight(new Float("-2.9"));
		Map<String, List<String>> values2 = ValidatorUtils.validateEntity(c);
		assertTrue(values2.keySet().contains("weight"));
		assertEquals(values2.get("weight").get(0).toString(),
				"must be greater than or equal to 0.0");
	}

	@Test
	public void testValidateProperty() {
		Course c = new Course();
		c.setTution(new BigDecimal("12.121"));
		assertEquals(ValidatorUtils.validateField(c, "tution").get(0),
				"numeric value out of bounds (<10 digits>.<2 digits> expected)");
	}

	/** this is to test the GWT per field validation on loose focus */
	@Test
	public void testValidateField() {
		System.out.println(ValidatorUtils.validateField(Course.class.getName(),
				"weight", new Float("1212.3")));
	}

	@After
	public void destroy() {

	}
}
