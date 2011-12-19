package info.yalamanchili.service.validation;

import info.yalamanchili.commons.entity.Course;

/**
 * Sample service interface to test validation interceptor with complex
 * validations
 */
public interface ValidationTestService {
	public void createCourse(Course course);
}
