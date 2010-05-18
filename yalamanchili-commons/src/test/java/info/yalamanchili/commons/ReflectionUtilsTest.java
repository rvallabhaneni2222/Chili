package info.yalamanchili.commons;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ReflectionUtilsTest {
	private static final Log log = LogFactory.getLog(ReflectionUtilsTest.class);
	protected ReflectionUtils reflectionUtils = new ReflectionUtils();

	@Before
	public void init() {

	}

	@Test
	public void testGetGetterMethod() {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		Field integerField = null;
		Field StringField = null;
		Field floatField = null;
		Field longField = null;
		Field dateField = null;
		try {
			integerField = TestEntity.class.getDeclaredField("integerField");
			StringField = TestEntity.class.getDeclaredField("stringField");
			floatField = TestEntity.class.getDeclaredField("floatField");
			longField = TestEntity.class.getDeclaredField("longField");
			dateField = TestEntity.class.getDeclaredField("dateField");
		} catch (Exception e) {
			fail("cannot find one or more field in the object specified");
		}
		Method integerMethod = ReflectionUtils.getGetterMethod(integerField,
				TestEntity.class);
		assertTrue(integerMethod.getReturnType()
				.isAssignableFrom(Integer.class));
	}

	@Test
	public void testCallGetterMethod() {
		TestEntity entity = new TestEntity();
		entity.setIntegerField(new Integer(10));
		entity.setStringField("test");
		entity.setLongField(new Long(10));
		entity.setFloatField(new Float(10.99));
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		entity.setDateField(date);

		Field integerField = null;
		Field StringField = null;
		Field floatField = null;
		Field longField = null;
		Field dateField = null;
		try {
			integerField = entity.getClass().getDeclaredField("integerField");
			StringField = entity.getClass().getDeclaredField("stringField");
			floatField = entity.getClass().getDeclaredField("floatField");
			longField = entity.getClass().getDeclaredField("longField");
			dateField = entity.getClass().getDeclaredField("dateField");
		} catch (Exception e) {
			fail("cannot find one or more field in the object specified");
		}
		assertTrue(new Integer(10).equals(ReflectionUtils.callGetterMethod(
				entity, integerField)));
		assertTrue("test".equals(ReflectionUtils.callGetterMethod(entity,
				StringField)));
		assertTrue(new Float(10.99).equals(ReflectionUtils.callGetterMethod(
				entity, floatField)));
		assertTrue(new Long(10).equals(ReflectionUtils.callGetterMethod(entity,
				longField)));
		assertTrue(date.equals(ReflectionUtils.callGetterMethod(entity,
				dateField)));

	}

	@Test
	public void testGetSetterMethod() {
		Field integerField = null;
		Field StringField = null;
		Field floatField = null;
		Field longField = null;
		Field dateField = null;
		try {
			integerField = TestEntity.class.getDeclaredField("integerField");
			StringField = TestEntity.class.getDeclaredField("stringField");
			floatField = TestEntity.class.getDeclaredField("floatField");
			longField = TestEntity.class.getDeclaredField("longField");
			dateField = TestEntity.class.getDeclaredField("dateField");
		} catch (Exception e) {
			fail("cannot find one or more field in the object specified");
		}
		Method integerMethod = ReflectionUtils.getSetterMethod(integerField,
				TestEntity.class);
		assertTrue(integerMethod.getParameterTypes()[0]
				.isAssignableFrom(Integer.class));
	}

	@Test
	public void testCallSetterMethod() {
		TestEntity entity = new TestEntity();
		Field integerField = null;
		Field StringField = null;
		Field floatField = null;
		Field longField = null;
		Field dateField = null;
		try {
			integerField = entity.getClass().getDeclaredField("integerField");
			StringField = entity.getClass().getDeclaredField("stringField");
			floatField = entity.getClass().getDeclaredField("floatField");
			longField = entity.getClass().getDeclaredField("longField");
			dateField = entity.getClass().getDeclaredField("dateField");
		} catch (Exception e) {
			fail("cannot find one or more field in the object specified");
		}
		ReflectionUtils.callSetterMethod(entity, integerField, new Integer(10));
		ReflectionUtils.callSetterMethod(entity, StringField, "test");
		ReflectionUtils.callSetterMethod(entity, floatField, new Float(10.99));
		ReflectionUtils.callSetterMethod(entity, longField, new Long(10));
		ReflectionUtils.callSetterMethod(entity, dateField, new Date());

		assertTrue(entity.getIntegerField().equals(new Integer(10)));
		assertTrue(entity.getStringField().equals("test"));
		assertTrue(entity.getFloatField().equals(new Float(10.99)));
		assertTrue(entity.getLongField().equals(new Long(10)));
		assertTrue(entity.getDateField().toString().equals(
				new Date().toString()));
	}

	@Test
	public void testGetAllDeclaredFields() {
		ReflectionUtils.getAllDeclaredFelds(TestEntity.class);
	}

	@After
	public void destroy() {

	}
}
