package info.yalamanchili.service.validation;

import info.yalamanchili.commons.entity.Course;
import info.yalamanchili.service.types.Error;

/**
 * Sample Test validator to test the valdiation interceptor with complex
 * valdiations. Each Service will have a corresponding validator and is
 * decorated wtih @ServiceValidator which causes it to trigger. Make sure the
 * validation methods return null
 */

public class ValidationTestServiceValidator implements ValidationTestService {

	@Override
	public void createCourse(Course course) {
		if (course.getRating() < new Float("3.00")
				&& course.getNoOfStuents() > 30) {
			Error validationError = new Error();
			validationError.setReasonCode("invalid.creteria");
			validationError
					.setDescription("course failed a required creteria for max number of students for low rating course");
			ValidationMessages.instance().addError(validationError);
		}

	}

}
