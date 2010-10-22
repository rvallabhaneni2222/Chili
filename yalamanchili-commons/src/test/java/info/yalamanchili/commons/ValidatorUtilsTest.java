package info.yalamanchili.commons;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import info.yalamanchili.commons.entity.Sex;
import info.yalamanchili.commons.entity.Student;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class ValidatorUtilsTest {
	private static final Log log = LogFactory.getLog(ValidatorUtilsTest.class);

	@Before
	public void init() {
	}

	@Test
	@Ignore
	public void testLoadProperties() {
		try {
			ValidatorUtils.loadProperties();
		} catch (Exception e) {
			fail();
			log.error(e);
		}
	}

	@Test
	public void testNotEmpty() {
		Student s = new Student();
		s.setSid("12");
		s.setFirstName("");
		List<String> values = ValidatorUtils.validateField(s, "firstName");
		assertTrue(values.contains("may not be null or empty"));
		s.setFirstName("jack");
		values = ValidatorUtils.validateField(s, "firstName");
		assertTrue(values.size() == 0);
	}

	@Test
	public void testLength() {
		Student s = new Student();
		s.setSid("12");
		List<String> values = ValidatorUtils.validateField(s, "sid");
		assertTrue(values.contains("length must be between 4 and 4"));
		s.setSid("1234");
		values = ValidatorUtils.validateField(s, "sid");

	}

	@Test
	public void testNotNull() {
		Student s = new Student();
		List<String> values = ValidatorUtils.validateField(s, "exists");
		assertTrue(values.contains("may not be null"));
	}

	@Test
	public void testRange() {
		Student s = new Student();
		s.setAge(101);
		List<String> values = ValidatorUtils.validateField(s, "age");
		assertTrue(values.contains("must be between 0 and 100"));
		s.setAge(23);
		values = ValidatorUtils.validateField(s, "age");
		assertTrue(values.size() == 0);
		s.setHeight(new Float(2));
		values = ValidatorUtils.validateField(s, "height");
		assertTrue(values.contains("must be between 3 and 8"));
		s.setHeight(new Float(5.11));
		values = ValidatorUtils.validateField(s, "height");
		assertTrue(values.size() == 0);
	}

	@Test
	public void testPast() {
		Student s = new Student();
		s.setDateOfBirth(new Date());
		List<String> values = ValidatorUtils.validateField(s, "dateOfBirth");
		assertTrue(values.contains("must be a past date"));
		s.setDateOfBirth(DateUtils.getNextDay(new Date(), -5));
		values = ValidatorUtils.validateField(s, "dateOfBirth");
		assertTrue(values.size() == 0);
	}

	@Test
	public void testFuture() {
		Student s = new Student();
		s.setGraduationDate(new Date());
		List<String> values = ValidatorUtils.validateField(s, "graduationDate");
		assertTrue(values.contains("must be a future date"));
		assertTrue(values.size() > 0);
		s.setGraduationDate(DateUtils.getNextYear(new Date(), 2));
		values = ValidatorUtils.validateField(s, "graduationDate");
		assertTrue(values.size() == 0);

	}

	@Test
	public void testEmail() {
		Student s = new Student();
		s.setEmail("asdf");
		List<String> values = ValidatorUtils.validateField(s, "email");
		assertTrue(values.contains("not a well-formed email address"));
		s.setEmail("asdf@df.com");
		values = ValidatorUtils.validateField(s, "email");
		assertTrue(values.size() == 0);
	}

	@Test
	public void testValidateEntity() {
		Student s = new Student();
		s.setFirstName("tom");
		s.setLastName("");
		s.setAge(222);
		s.setDateOfBirth(new Date());
		s.setEmail("asdf");
		s.setExists(true);
		s.setHeight(new Float(9.0));
		Map<String, List<String>> values = ValidatorUtils.validateEntity(s);
		assertTrue(values.size() > 0);
		s.setLastName("harry");
		s.setAge(34);
		s.setHeight(new Float(5.11));
		s.setSex(Sex.MALE);
		// issues with email validator
		s.setEmail("asdf@asdfcom");
		s.setGraduationDate(DateUtils.getNextDay(new Date(), 2));
		values = ValidatorUtils.validateEntity(s);
		assertTrue(values.size() == 0);
	}

	@Test
	@Ignore
	public void testGetNotNullKey() {
		assertNotNull(ValidatorUtils.loadProperties()
				.get("{validator.notNull}"));
	}

	@After
	public void destroy() {

	}
}
