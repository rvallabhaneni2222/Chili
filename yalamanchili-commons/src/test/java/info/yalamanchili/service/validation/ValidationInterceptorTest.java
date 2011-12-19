package info.yalamanchili.service.validation;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertTrue;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replayAll;
import info.yalamanchili.commons.DateUtils;
import info.yalamanchili.commons.entity.Course;
import info.yalamanchili.service.validation.ValidationInterceptor;
import info.yalamanchili.service.validation.ValidationMessages;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/* http://code.google.com/p/powermock/wiki/MockStatic*/

@RunWith(PowerMockRunner.class)
@PrepareForTest({ ValidationMessages.class })
public class ValidationInterceptorTest {
	private static ValidationInterceptor validationInterceptor;
	private static ValidationMessages validationMessage;

	@Before
	public void init() {
		validationInterceptor = new ValidationInterceptor();
		validationMessage = new ValidationMessages();
		mockStatic(ValidationMessages.class);
		// TODO see there is a better way with easy mock
		expect(ValidationMessages.instance()).andReturn(validationMessage);
		expect(ValidationMessages.instance()).andReturn(validationMessage);
		expect(ValidationMessages.instance()).andReturn(validationMessage);
		expect(ValidationMessages.instance()).andReturn(validationMessage);
		expect(ValidationMessages.instance()).andReturn(validationMessage);
		expect(ValidationMessages.instance()).andReturn(validationMessage);
		expect(ValidationMessages.instance()).andReturn(validationMessage);
		replayAll();
	}

	@Test
	public void testInputs() {
		Object[] inputs = new Object[1];
		Course c = new Course();
		c.setEndDate(DateUtils.getNextDay(new Date(), -1));
		c.setStartDate(DateUtils.getNextDay(new Date(), 1));
		c.setTution(new BigDecimal("100.011"));
		c.setNoOfStuents(61);
		inputs[0] = c;
		validationInterceptor.validateInputs(inputs);
		for (info.yalamanchili.service.types.Error err : ValidationMessages
				.instance().getErrors()) {
			// System.out.println("----attribute:" + err.getReasonCode());
			// System.out.println("----message:" + err.getDescription());
		}
		assertTrue(ValidationMessages.instance().getErrors().size() == 5);
	}

	@After
	public void destroy() {
		validationInterceptor = null;
	}
}
