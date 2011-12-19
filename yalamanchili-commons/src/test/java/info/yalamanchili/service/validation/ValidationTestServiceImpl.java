package info.yalamanchili.service.validation;

import info.yalamanchili.commons.entity.Course;

@ServiceValidator(value = ValidationTestServiceValidator.class)
public class ValidationTestServiceImpl implements ValidationTestService {

	@Override
	public void createCourse(Course course) {

	}

}
