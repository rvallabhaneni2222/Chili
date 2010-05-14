package info.yalamanchili.commons;

import static org.junit.Assert.*;
import info.yalamanchili.mapper.BeanMapper;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ReflectionUtilsTest {
	private static final Log log = LogFactory.getLog(ReflectionUtilsTest.class);
	ReflectionUtils reflectionUtils = new ReflectionUtils();
	BeanMapper mapper = new BeanMapper();

	@Before
	public void init() {

	}

	@Test
	public void testGetGetterMethod() {

	}

	@Test
	public void testSetGetterMethod() {
		TestEntity entity = new TestEntity();
		entity.setId(new Long(1));
		entity.setAge(26);
		entity.setDate(new Date());
		entity.setAmount(new Float("10.03"));
		entity.setIsReal(false);
		TestEntity res = (TestEntity) mapper.mapPrimitiveDataTypes(entity,
				TestEntity.class);
		assertTrue(res.getAge().equals(new Integer(26)));
	}

	@After
	public void destroy() {

	}
}
